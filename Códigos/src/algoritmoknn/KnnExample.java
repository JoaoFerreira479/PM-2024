package algoritmoknn;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

class DataPoint {
	private double calorias;
	private double horasSono;
	private String estado;

	public DataPoint(double calorias, double horasSono, String estado) {
		this.calorias = calorias;
		this.horasSono = horasSono;
		this.estado = estado;
	}

	public double getCalorias() {
		return calorias;
	}

	public double getHorasSono() {
		return horasSono;
	}

	public String getEstado() {
		return estado;
	}
}

class Dataset {
	private List<DataPoint> dataPoints;
	private static final double MAX_CALORIAS = 3000.0;
	private static final double MAX_HORAS_SONO = 10.0;

	public Dataset() {
		this.dataPoints = new ArrayList<>();
	}

	public void loadDataFromCSV(String filename) throws IOException {
		File file = new File(filename);
		if (!file.exists()) {
			throw new FileNotFoundException("Arquivo CSV não encontrado.");
		}

		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
			String line;
			br.readLine();
			while ((line = br.readLine()) != null) {
				String[] values = line.split(";");
				double calorias = Double.parseDouble(values[0]);
				double horasSono = Double.parseDouble(values[1]);
				String estado = values[2];

				if (calorias < 0 || calorias > MAX_CALORIAS || horasSono < 0 || horasSono > MAX_HORAS_SONO) {
					throw new IllegalArgumentException("Valores fora do intervalo permitido no arquivo CSV.");
				}

				dataPoints.add(new DataPoint(calorias, horasSono, estado));
			}
		}
	}

	public List<DataPoint> getDataPoints() {
		return dataPoints;
	}

	public int getLength() {
		return dataPoints.size();
	}
}

class Knn {
	private Dataset dataset;
	private int k;

	public Knn(Dataset dataset, int k) {
		this.dataset = dataset;
		this.k = k;
	}

	private double calcularDistancia(DataPoint p1, double calorias, double horasSono) {
		double caloriasNormalizada = Math.abs(p1.getCalorias() / 3000 - calorias / 3000);
		double sonoNormalizado = Math.abs(p1.getHorasSono() / 10 - horasSono / 10);
		return Math.sqrt(Math.pow(caloriasNormalizada, 2) + Math.pow(sonoNormalizado, 2));
	}

	public String classify(double horasSono, double calorias) {
		if (calorias < 0 || calorias > 3000 || horasSono < 0 || horasSono > 10) {
			throw new IllegalArgumentException("Entradas fora do intervalo permitido.");
		}

		List<DataPoint> vizinhos = dataset.getDataPoints().parallelStream()
				.sorted(Comparator.comparingDouble(p -> calcularDistancia(p, calorias, horasSono))).limit(k)
				.collect(Collectors.toList());

		Map<String, Long> frequencias = vizinhos.stream()
				.collect(Collectors.groupingBy(DataPoint::getEstado, Collectors.counting()));

		return frequencias.entrySet().stream().max(Map.Entry.comparingByValue()).get().getKey();
	}

	public String determineHappiness(double horasSono, double calorias) {
		return classify(horasSono, calorias);
	}
}

public class KnnExample {
	public static void main(String[] args) {
		try {
			Dataset dataset = new Dataset();
			dataset.loadDataFromCSV("feliz.csv");

			Knn knn = new Knn(dataset, 3);

			Scanner scanner = new Scanner(System.in);
			System.out.print("Digite as horas de sono (0 a 10): ");
			double horasSono = scanner.nextDouble();
			System.out.print("Digite a quantidade de calorias (0 a 3000): ");
			double calorias = scanner.nextDouble();

			String estado = knn.determineHappiness(horasSono, calorias);
			System.out.println("Classificação: " + estado);

			scanner.close();

		} catch (FileNotFoundException e) {
			System.out.println("Erro: Arquivo CSV não encontrado.");
		} catch (IOException e) {
			System.out.println("Erro ao carregar o arquivo CSV: " + e.getMessage());
		} catch (IllegalArgumentException e) {
			System.out.println("Erro: " + e.getMessage());
		}

	}
}

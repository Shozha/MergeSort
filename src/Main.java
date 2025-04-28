import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private static final String INPUT_DIR = "src/files/";
    private static final String OUTPUT_FILE = "src/results.csv";
    private static final int NUM_DATASETS = 50;

    public static void main(String[] args) {
        new InputData().generateAllData(100, 10000, 100);
        processAllSizes();
    }

    private static void processAllSizes() {
        try (FileWriter writer = new FileWriter(OUTPUT_FILE)) {
            writer.write("Размер;Среднее время (мкс);Средние итерации\n");

            for (int size = 100; size <= 10000; size += 100) {
                long totalTime = 0;
                long totalIterations = 0;

                for (int datasetNum = 1; datasetNum <= NUM_DATASETS; datasetNum++) {
                    String fileName = String.format("input_%d_%d.txt", size, datasetNum);
                    int[] arr = readArrayFromFile(INPUT_DIR + fileName);
                    SortResult result = MergeSort.mergeSort(arr);

                    totalTime += result.getTimeMicros();
                    totalIterations += result.getIterations();
                }

                double avgTime = (double) totalTime / NUM_DATASETS;
                double avgIterations = (double) totalIterations / NUM_DATASETS;

                writer.write(String.format(
                        "%d;%.2f;%.2f\n",
                        size, avgTime, avgIterations
                ));
            }
        } catch (Exception e) {
            System.err.println("Ошибка записи: " + e.getMessage());;
        }
    }

    private static int[] readArrayFromFile(String filePath) {
        ArrayList<Integer> list = new ArrayList<>();
        try (Scanner scanner = new Scanner(new File(filePath))) {
            while (scanner.hasNextInt()) {
                list.add(scanner.nextInt());
            }
        } catch (Exception e) {
            System.err.println("Ошибка чтения: " + e.getMessage());
        }
        return list.stream().mapToInt(i -> i).toArray();
    }

}
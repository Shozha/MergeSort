import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class InputData {
    private static final String BASE_PATH = "src/files/";
    private static final int NUM_DATASETS_PER_SIZE = 50;

    public void generateAllData(int minSize, int maxSize, int step) {
        for (int size = minSize; size <= maxSize; size += step) {
            for (int datasetNum = 1; datasetNum <= NUM_DATASETS_PER_SIZE; datasetNum++) {
                int[] arr = generateShuffledArray(size);
                saveToFile(arr, size, datasetNum);
            }
        }
    }

    private int[] generateShuffledArray(int size) {
        int[] arr = new int[size];
        Random random = new Random();

        for (int i = 0; i < size; i++) arr[i] = i + 1;
        for (int i = size - 1; i > 0; i--) swap(arr, i, random.nextInt(i + 1));

        return arr;
    }

    private void saveToFile(int[] arr, int size, int datasetNum) {
        String filePath = String.format(
                "%sinput_%d_%d.txt",
                BASE_PATH, size, datasetNum
        );

        try (FileWriter writer = new FileWriter(filePath)) {
            for (int num : arr) writer.write(num + " ");
        } catch (IOException e) {
            System.err.println("Ошибка записи: " + e.getMessage());
        }
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
public class MergeSort {

    public static SortResult mergeSort(int[] array) {
        if (array == null || array.length <= 1) {
            return new SortResult(0, 0, array != null ? array.length : 0);
        }

        int[] temp = new int[array.length];
        Counter counter = new Counter();

        long start = System.nanoTime();
        mergeSort(array, temp, 0, array.length - 1, counter);
        long end = System.nanoTime();

        return new SortResult(
                (end - start) / 1000,
                counter.getCount(),
                array.length
        );
    }

    private static void mergeSort(int[] array, int[] temp, int left, int right, Counter counter) {
        if (left < right) {
            int mid = left + (right - left) / 2;
            mergeSort(array, temp, left, mid, counter);
            mergeSort(array, temp, mid + 1, right, counter);
            merge(array, temp, left, mid, right, counter);
        }
    }

    private static void merge(int[] array, int[] temp, int left, int mid, int right, Counter counter) {
        System.arraycopy(array, left, temp, left, right - left + 1);

        int i = left;
        int j = mid + 1;
        int k = left;

        while (i <= mid && j <= right) {
            counter.increment();
            if (temp[i] <= temp[j]) {
                array[k++] = temp[i++];
            } else {
                array[k++] = temp[j++];
            }
        }

        while (i <= mid) {
            array[k++] = temp[i++];
            counter.increment();
        }

        while (j <= right) {
            array[k++] = temp[j++];
            counter.increment();
        }
    }

    private static class Counter {
        private int count = 0;

        public void increment() {
            count++;
        }

        public int getCount() {
            return count;
        }
    }
}
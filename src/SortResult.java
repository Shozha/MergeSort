public class SortResult {

    private final long time;
    private final int iterations;
    private final int size;

    public SortResult(long time, int iterations, int size) {
        this.time = time;
        this.iterations = iterations;
        this.size = size;
    }

    public long getTimeMicros() {
        return time;
    }

    public int getIterations() {
        return iterations;
    }

    public int getSize() {
        return size;
    }

    @Override
    public String toString() {
        return String.format("Размер: %d Время: %d мкс, Итераций: %d", size, time, iterations);
    }


}

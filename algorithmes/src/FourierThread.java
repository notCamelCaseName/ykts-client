import java.util.Vector;
import java.util.concurrent.BlockingQueue;

public class FourierThread extends Thread {
    private Thread t;
    private final BlockingQueue<numberedSample> out;

    private final numberedSample sample;

    public FourierThread(BlockingQueue<numberedSample> out, numberedSample sample) {
        this.out = out;
        this.sample = sample;
    }

    @Override
    public void run() {
        sample.data = FrequencyCalculator.fftReal(sample.data);
        out.add(sample);
    }
}
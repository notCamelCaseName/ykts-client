import java.util.Vector;
import java.util.concurrent.BlockingQueue;

/**
 * Class used to parallelize computation of the Fourier transform in real time
 */
public class FourierThread extends Thread {
    private Thread t;
    private final Spectrogram out;
    private final numberedSample sample;

    /**
     * Constructs a new FourierThread given the sample it has to compute the Fourier transform of and an output Spectrogram
     * @param out Output Spectrogram
     * @param sample Sample to compute, with its id (position in the spectrogram)
     */
    public FourierThread(Spectrogram out, numberedSample sample) {
        this.out = out;
        this.sample = sample;
    }

    /**
     * Main function of the thread, used to compute the Fourier transform
     */
    @Override
    public void run() {
        this.sample.data = FrequencyCalculator.fftReal(sample.data);
        // Using synchronized to avoid concurrency problem such as modifying the length of the vector in the Spectrogram class
        synchronized (this.out) {
            out.addSample(sample);
        }
    }
}
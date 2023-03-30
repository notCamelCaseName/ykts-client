import UtilityClasses.Point;
import UtilityClasses.numberedSample;

import java.util.Vector;

/**
 * 3 dimensional array containing data about an audio track
 * 1st dimension is time
 * 2nd dimension is frequency
 * 3rd dimension is amplitude of the associated frequency at the associated time
 * Hence, it can be considered a function of a 2D space mapping time and frequency to amplitude ( Amp(t,f) )
 */
public class Spectrogram {
    private final Vector<double[]> data;
    private final double sampleTime;

    /**
     * Constructing an empty Spectrogram
     */
    public Spectrogram(double sampleTime) {
        this.data = new Vector<>();
        this.sampleTime = sampleTime;
    }

    public Vector<double[]> getData() {
        return data;
    }

    public double getSampleTime() {
        return sampleTime;
    }

    /**
     * Adds a sample to the spectrogram
     * @param sample The sample and its associated time
     */
    public void addSample(numberedSample sample) {
        if (sample.id > this.data.size()) {
            this.data.setSize(sample.id);
        }
        this.data.set(sample.id, sample.data);
    }

    /**
     * Self-explanatory
     * @param time Real time, not index
     * @param frequency Real frequency, not index
     * @return The amplitude in the spectrogram at given time and frequency
     */
    public double getAmplitude(double time, double frequency) {
        int t = (int) (time/sampleTime);
        int f = (int) (frequency*sampleTime);
        return this.data.get(t)[f];
    }

    /**
     * Normalizes the Spectrogram, i.e. make the largest element equal to 1.
     */
    private void normalize() {
        double m = 0;
        for (double[] sampleFrequencies : this.data) {
            for (double amplitude : sampleFrequencies) {
                if (amplitude > m) {
                    m = amplitude;
                }
            }
        }
        for (double[] sampleFrequencies : this.data) {
            for (int f = 0; f < sampleFrequencies.length; f++) {
                sampleFrequencies[f] /= m;
            }
        }
    }

    /**
     * Find key points in the spectrogram
     * @param threshold Threshold to filter points
     * @return The list of key points as couples (time, frequency)
     */
    public Vector<Point> getKeyPoints(double threshold) {
        Vector<Point> res = new Vector<>();
        // Iterate through the array and extracting into res coordinates that match the property
        for (int t = 0; t<this.data.size(); t++) {
            double[] sampleFrequencies = this.data.get(t);
            for (int f = 0; f<sampleFrequencies.length; f++) {
                if (sampleFrequencies[f]>= threshold) {
                    res.add(new Point(t*sampleTime,f/sampleTime, this.data.get(t)[f]));
                }
            }
        }
        return res;
    }

}

import java.util.Arrays;

import static java.lang.Math.PI;
import static java.lang.Math.cos;

public class Main {
    public static void main(String[] args) {
        int sampleSize = 65536;
        double f = 64.;
        double sampleTime = 1.;
        double[] data = new double[sampleSize];
        for (int i = 0; i<sampleSize; i++) {
            data[i] = cos(2*PI*f*i*sampleTime/sampleSize);
        }
        double[] dataf = FrequencyCalculator.fftReal(data);
        System.out.println(Arrays.toString(dataf));
        for (int i = 0; i<sampleSize; i++) {
            if (dataf[i] == 1.) {
                System.out.println(i);
            }
        }
        System.out.println(dataf[(int)(f*sampleTime)]);
    }
}
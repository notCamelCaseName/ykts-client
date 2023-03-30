import UtilityClasses.Complex;

/**
 * Collection of methods to compute th Fourier transform of a signal
 */
public class FrequencyCalculator {

    /**
     * Fast discrete fourier transform (fft) using the Cooley-Tukey algorithm
     * @param x Original complex signal
     * @return The Fourier transform of x
     */
    public static Complex[] fft(Complex[] x) {
        int n = x.length;

        // base case
        if (n == 1) return new Complex[] { x[0] };

        // radix 2 Cooley-Tukey FFT
        if (n % 2 != 0) {
            throw new IllegalArgumentException("n is not a power of 2");
        }

        // compute FFT of even terms
        Complex[] temp = new Complex[n/2];
        for (int k = 0; k < n/2; k++) {
            temp[k] = x[2*k];
        }
        Complex[] evenFFT = fft(temp);

        // compute FFT of odd terms
        for (int k = 0; k < n/2; k++) {
            temp[k] = x[2*k + 1];
        }
        Complex[] oddFFT = fft(temp);

        // combine
        Complex[] y = new Complex[n];
        for (int k = 0; k < n/2; k++) {
            double kth = -2 * k * Math.PI / n;
            Complex wk = new Complex(Math.cos(kth), Math.sin(kth));
            y[k]       = evenFFT[k].plus (wk.times(oddFFT[k]));
            y[k + n/2] = evenFFT[k].minus(wk.times(oddFFT[k]));
        }
        return y;
    }

    /**
     * Wrapper for the fft function to act on real data
     * @param x Initial data
     * @return Real fourier transform of x
     */
    public static double[] fftReal(double[] x) {
        Complex[] x_complex = toComplexArray(x);
        Complex[] f_complex = fft(x_complex);
        return getFrequencies(f_complex);
    }

    /**
     * Converts double array to MathClasses.Complex array
     * @param f double array
     * @return f converted to MathClasses.Complex array
     */
    private static Complex[] toComplexArray(double[] f) {
        Complex[] c = new Complex[f.length];
        for (int i=0; i<f.length; i++) {
            c[i] = new Complex(f[i], 0);
        }
        return c;
    }

    /**
     * Extract frequencies from the result of a complex fourier transform
     * @param data MathClasses.Complex array, usually result of a fourier transform
     * @return Array of data's element's module
     */
    private static double[] getFrequencies(Complex[] data) {
        double[] f = new double[data.length];
        for (int i=0; i<data.length; i++) {
            f[i] = data[i].getMod();
        }
        return f;
    }
}

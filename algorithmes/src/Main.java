import java.util.Arrays;

import static java.lang.Math.PI;
import static java.lang.Math.cos;

public class Main {
    public static void main(String[] args) {
        int size = 4;
        double[] data = new double[size];
        for (int i = 0; i<size; i++) {
            data[i] = cos(2. * PI * i / size);
        }
        System.out.println(Arrays.toString(FrequencyCalculator.fftReal(data)));
        
    }
}
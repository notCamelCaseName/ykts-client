package UtilityClasses;

import static java.lang.Math.*;

/**
 * Class representing complex numbers
 */
public record Complex(double re, double im) {

    /**
     * Construct complex using complex exponential
     *
     * @param r     Radius of the complex
     * @param theta Argument of the complex
     * @return A complex = r*exp(i*theta)
     */
    public static Complex exp(double r, double theta) {
        return new Complex(r * cos(theta), r * sin(theta));
    }

    /**
     * @return Module of the complex
     */
    public double getMod() {
        return sqrt(re * re + im * im);
    }

    public Complex plus(Complex other) {
        return new Complex(this.re + other.re, this.im + other.im);
    }

    public Complex minus(Complex other) {
        return new Complex(this.re - other.re, this.im - other.im);
    }

    public Complex times(Complex other) {
        return new Complex(this.re * other.re - this.im * other.im, this.re * other.im + this.im * other.re);
    }
}

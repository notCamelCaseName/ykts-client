import static java.lang.Math.*;

public class Complex {
    private double re;
    private double im;

    public Complex(double re, double im) {
        this.re = re;
        this.im = im;
    }

    public static Complex exp(double r, double theta) {
        return new Complex(r*cos(theta), r*sin(theta));
    }

    public double getIm() {
        return im;
    }
    public double getRe() {
        return re;
    }

    public double getMod() {
        return sqrt(re*re + im*im);
    }

    public Complex plus(Complex other) {
        return new Complex(this.re + other.re, this.im + other.im);
    }

    public Complex minus(Complex other) {
        return new Complex(this.re - other.re, this.im - other.im);
    }

    public Complex times(Complex other) {
        return new Complex(this.re*other.re - this.im*other.im, this.re*other.im + this.im*other.re);
    }
}

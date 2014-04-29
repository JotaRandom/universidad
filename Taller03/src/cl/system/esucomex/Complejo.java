package cl.system.esucomex;

public class Complejo {
	private double re, im;

	public Complejo(double re, double im) {
		this.re = re;
		this.im = im;
	}

	public Complejo() {}

	public Complejo sumar(Complejo c) {
		Complejo salida = new Complejo(re + c.re, im + c.im);
		return salida;
	}

	public Complejo restar(Complejo c) {
		Complejo salida = new Complejo(re - c.re, im - c.im);
		return salida;
	}

	public Complejo multiplicar(Complejo c) {
		Complejo salida = new Complejo(re * c.re - im * c.im, 
										re * c.im + im * c.re);
		return salida;
	}

	public Complejo dividir(Complejo c) {
		double d = c.re * c.re + c.im * c.im;
		Complejo salida = new Complejo((re * c.re + im * c.im) / d,
				(im * c.re - re * c.im) / d);
		return salida;
	}


	@Override
	public String toString() {
		return ("(" + re + "," + im + ")");
	}
}























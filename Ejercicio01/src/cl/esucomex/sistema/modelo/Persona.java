package cl.esucomex.sistema.modelo;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Persona {
	private String rut;
	private String nombre;
	private String direccion;
	private Date fechaNacimiento;
	private static int tamanoPoblacionMax = 100;
	private static int tamanoPoblacion = -1;
	private static Persona[] poblacion = new Persona[tamanoPoblacionMax];
	    

	public Persona(String rut, String nombre, String direccion,
			Date fechaNacimiento) {
		this.rut = rut;
		this.nombre = nombre;
		this.direccion = direccion;
		this.fechaNacimiento = fechaNacimiento;
	}

	public static int getTamanoPoblacion(){
		return tamanoPoblacion + 1;
	}
	
	public static void putInRegistroCivil(Persona p){
		if (getTamanoPoblacion() < tamanoPoblacionMax){
			poblacion[++tamanoPoblacion] = p;
		}
	}
	
	public static boolean addPersona(String rut, String nombre, String direccion, String fechaNacimiento){
		boolean fechaCorrecta = true;
		Date inDate = null;
		boolean resultado = false;
		try {
			inDate = (new SimpleDateFormat("dd/MM/yyyy")).parse(fechaNacimiento);
		} catch (ParseException e) {
			fechaCorrecta = false;
		}
		if (!rut.equals("") && 	!nombre.equals("") && 
				!direccion.equals("") && fechaCorrecta) {
			Persona.putInRegistroCivil(new Persona(rut, nombre,	direccion, inDate));
			resultado = true;
		} 
		return resultado;
	}
	
	public static void removeFromRegistroCivil(Persona p){
		for (int i = 0; i < getTamanoPoblacion(); i++){
			if (p.getRut().equals(poblacion[i].getRut())){
				poblacion[i] = poblacion[getTamanoPoblacion() - 1];
				poblacion[getTamanoPoblacion() - 1] = null;
				tamanoPoblacion--;
				break;
			}
		}
	}
	
	public static void showPoblacion(){
		System.out.format("La poblaci�n actual es de %d habitantes %n", getTamanoPoblacion());
		if (getTamanoPoblacion() > 0) {
			System.out.format("El ciudadano(a) de m�s edad es %s, que tiene %d a�os %n",personaMayorEdad().getNombre(), personaMayorEdad().getEdad());
			System.out.format("El ciudadano(a) de menor edad es %s, que tiene %d a�os %n",personaMenorEdad().getNombre(), personaMenorEdad().getEdad());
			System.out.format("El promedio de las edades es %.1f %n",promedioEdades());
			System.out.println("Los habitantes actuales son:");
			for (int i = 0; i < getTamanoPoblacion(); i++) {
				System.out.print(poblacion[i]);
			}
		} else {
			System.out.println("No hay estad�sticas");
		}
	}
	
	public static Persona personaMayorEdad(){
		Persona resultado = null;
		int edad = -1;
		for (int i = 0; i < getTamanoPoblacion(); i++){
			if (poblacion[i].getEdad() > edad){
				edad = poblacion[i].getEdad();
				resultado = poblacion[i];
			}
		}
		return resultado;
	}
	
	public static Persona personaMenorEdad(){ 
		Persona resultado = null;
		int edad = Integer.MAX_VALUE;
		for (int i = 0; i < getTamanoPoblacion(); i++){
			if (poblacion[i].getEdad() < edad){
				edad = poblacion[i].getEdad();
				resultado = poblacion[i];
			}
		}
		return resultado;
	}
	
	public static float promedioEdades(){
		float resultado = 0;
		int sumaEdades = 0;
		for (int i = 0; i < getTamanoPoblacion(); i++){
			sumaEdades = sumaEdades + poblacion[i].getEdad();
		}
		if (getTamanoPoblacion() > 0){
			resultado = (float)sumaEdades/getTamanoPoblacion();
		}
		return resultado;
	}

	public static Persona getPersona(String rut){
		Persona resultado = null;
		for (int i = 0; i < getTamanoPoblacion(); i++){
			if (rut.equals(poblacion[i].getRut())){
				resultado = poblacion[i];
				break;
			}
		}
		return resultado;
	}
	
	
	public String getRut() {
		return rut;
	}

	public void setRut(String rut) {
		this.rut = rut;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	public int getEdad(){
		// getTime() entrega la cantidad de milisegundos desde enero 1, 1970
		long actual = new Date().getTime();
		long dias = (actual - getFechaNacimiento().getTime()) / (24*60*60*1000);
		long edad = dias/365;
		return (int) edad;
		
	}
	
	public String getFechaNacimientoAsString() {
		return DateFormat.getDateInstance(DateFormat.LONG).format(this.getFechaNacimiento());
	}
	
	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public void actualizar(String nombre, String direccion,	String fechaNacimiento) {
		boolean fechaCorrecta = true;;
		Date inDate = null;
		if (!nombre.equals("")){
			this.setNombre(nombre);
		}
		if (!direccion.equals("")){
			this.setDireccion(direccion);
		}
		try {
			inDate = (new SimpleDateFormat("dd/MM/yyyy")).parse(fechaNacimiento);
		} catch (ParseException e) {
			fechaCorrecta = false;
		}
		if (fechaCorrecta) {
			this.setFechaNacimiento(inDate);
		}
	}
	
	
	@Override
	public String toString() {
		return String.format("Persona %n Rut: %s %n Nombre: %s %n Direcci�n: %s %n" +
				             " Fecha de Nacimiento: %s %n Edad: %s a�os %n", 
				             getRut(), getNombre(), getDireccion(), 
				             getFechaNacimientoAsString(), getEdad());
	}

	
}

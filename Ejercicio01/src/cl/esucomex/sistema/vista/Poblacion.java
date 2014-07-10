package cl.esucomex.sistema.vista;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cl.esucomex.sistema.modelo.Persona;

public class Poblacion {

	public static void main(String[] args) throws IOException {
		String inRut, inNombre, inDireccion, inFechaNacimiento, opcion;
		int intOpcion;
		boolean personaIngresada;;
		Persona persona = null;
		poblar();
		BufferedReader bf = new BufferedReader (new InputStreamReader(System.in));
		Pattern patternMenu = Pattern.compile("[1-6]");
		Pattern patternSiNo = Pattern.compile("[SsNn]");
		Matcher m = null;

		while (true) {
		
			System.out.print("\n M E N U \n");
			System.out.print("\n 1) Ver Estad�sticas \n");
			System.out.print("\n 2) Consultar datos de una Persona \n");
			System.out.print("\n 3) Crear Persona \n");
			System.out.print("\n 4) Modificar datos de Persona \n");
			System.out.print("\n 5) Eliminar una Persona \n");
			System.out.print("\n 6) Salir \n");
			
			do {	
				System.out.print("\nPor favor, ingrese opci�n (1 .. 6): ");
				opcion = bf.readLine();
				m = patternMenu.matcher(opcion);
			} while (!m.matches());
			System.out.println();
			
			intOpcion = Integer.parseInt(opcion);
			switch (intOpcion) {
			case 1: // Consultar Estad�sticas de la Poblaci�n
				Persona.showPoblacion();
				break;
			case 2: // Consultar los datos de una Persona
				System.out.print("\nPor favor, ingrese el Rut de la Persona cuyos datos desea consultar: ");
				inRut = bf.readLine();
				if (inRut == null || inRut.equals("")) {
					System.out.println("\nRut no ingresado, no es v�lido");
				}else if ((persona = Persona.getPersona(inRut)) != null) {
					System.out.format("%n%s", persona);
				} else {
					System.out.format("%nNo existen datos de una persona con Rut=%s %n", inRut);
				}
				break;
			case 3: // Crear una Persona. Debe tener un Rut que no exista en la Poblaci�n. 
				System.out.println("Indique los datos de la Persona a crear:");
				System.out.print("Rut:");
				inRut = bf.readLine();
				System.out.print("Nombre:");
				inNombre = bf.readLine();
				System.out.print("Direcci�n:");
				inDireccion = bf.readLine();
				System.out.print("Fecha de Nacimiento (dd/mm/aaaa):");
				inFechaNacimiento = bf.readLine();
				
				if (inRut == null || inRut.equals("")) {
					System.out.println("\nRut no ingresado, no es válido");
				}else if ((persona = Persona.getPersona(inRut)) != null){
					System.out.format("%nLa persona con Rut= %s ya existe, sus datos son:%n %s",inRut, persona);
				} else {
					personaIngresada = Persona.addPersona(inRut, inNombre, inDireccion, inFechaNacimiento);// nueva operaci�n
					if (personaIngresada) {
						System.out.format("%n%s con Rut= %s fue incorporado(a) a la población %n",inNombre, inRut);
					} else {
						System.out.println("\nDatos incorrectos, la persona no fue creada");
					}
				}
	            break;
			case 4: // Modificar los datos de una Persona, excepto su Rut.
			    // Si el usuario no ingresa una valor para un atributo, se mantiene el anterior
				
				System.out.print("Indique el Rut de la Persona cuyos datos desea modificar: ");
				inRut = bf.readLine();
				//TODO				

	            break;
			case 5: // Eliminar una Persona de la Poblaci�n.
				
				System.out.print("Indique el Rut de la Persona a eliminar: ");
				inRut = bf.readLine();
				//TODO
				
				
				// ESTE
				if (inRut == null || inRut.equals("")) {
					System.out.println("\nRut no ingresado, no es v�lido");
				}else if ((persona = Persona.getPersona(inRut)) != null) {
					//System.out.format("%n%s", persona);
					System.removeALL("%n%s", persona);
				} else {
					System.out.format("%nNo existen datos de una persona con Rut=%s %n", inRut);
				}
				
				break;   
			case 6: // Salir del sistema
				System.out.println("\nGracias por usar el sistema!");
				System.out.println("\nPresione <enter> para finalizar.");
				bf.readLine();
	        default:
	        	bf.close();
	        	System.exit(0);
			}
			System.out.println("\nPresione <enter> para continuar.");
			bf.readLine();
		}
	}
	
	private static void poblar(){
		Persona.putInRegistroCivil(new Persona("1-1", "kemal ataturk", "Esmirna", new Date(81,00,15)));
		Persona.putInRegistroCivil(new Persona("1-2", "isidoro silva", "Palermo", new Date(71,01,15)));
//		Persona.putInRegistroCivil(new Persona("1-3", "alarico m�ndez", "Donostia", new Date(61,02,15)));
//		Persona.putInRegistroCivil(new Persona("1-4", "teodorico pezoa", "R�vena", new Date(51,11,15)));
//		Persona.putInRegistroCivil(new Persona("1-5", "atanasio moncada", "Toledo", new Date(41,11,15)));
	}
}

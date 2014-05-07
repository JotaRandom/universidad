package cl.esucomex.sistema.vista;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;

import cl.esucomex.sistema.modelo.* ;

public class Poblacion {
	public static void main(String[] args) throws IOException{ String rut;
	
	Persona persona = new Persona("1-1", "kemal ataturk", "Esmirna", new Date(81,11,23));
	BufferedReader bf = new BufferedReader (new InputStreamReader(System.in));

	System.out.print("Por favor, ingrese el Rut: ");
	rut = bf.readLine();

	if (rut.equals(persona.getRut())) {
		System.out.format("%s", persona);
	} else {
		System.out.format("No existen datos de una persona con Rut=%s %n", rut);
	}

	}
}

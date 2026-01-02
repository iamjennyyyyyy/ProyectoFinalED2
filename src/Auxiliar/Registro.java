package Auxiliar;

import java.time.LocalDate;
import java.time.Year;
import java.util.ArrayList;

public class Registro {
 private int anno;
 private ArrayList<Mes> meses ;
 
 public int getAnno() {
	return anno;
}


public ArrayList<Mes> getMeses() {
	return meses;
}




public void anadirCaso(){
	String m = LocalDate.now().getMonth().toString();
	
	switch(m){
	case "JANUARY" : meses.get(0).aumentarCasos();;break;
	case "FEBRUARY" : meses.get(1).aumentarCasos();break;
	case "MARCH" : meses.get(2).aumentarCasos();break;
	case "APRIL" : meses.get(3).aumentarCasos();break;
	case "MAY" : meses.get(4).aumentarCasos();break;
	case "JUNE" : meses.get(5).aumentarCasos();break;
	case "JULY" : meses.get(6).aumentarCasos();break;
	case "AUGUST" : meses.get(7).aumentarCasos();break;
	case "SEPTEMBER" : meses.get(8).aumentarCasos();break;
	case "OCTOBER" : meses.get(9).aumentarCasos();break;
	case "NOVEMEBER" : meses.get(10).aumentarCasos();break;
	case "DECEMBER" : meses.get(11).aumentarCasos();break;
	}
}

public Registro() {
	anno= LocalDate.now().getYear();
	meses = new ArrayList<Mes>(12);// crear los 12 meses aqui en el constructor
}




}


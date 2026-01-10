package Auxiliar;

import java.time.LocalDate;
import java.time.Month;
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
	meses.add(new Mes(Month.JANUARY.toString()));
	meses.add(new Mes(Month.FEBRUARY.toString()));
	meses.add(new Mes(Month.MARCH.toString()));
	meses.add(new Mes(Month.APRIL.toString()));
	meses.add(new Mes(Month.MAY.toString()));
	meses.add(new Mes(Month.JUNE.toString()));
	meses.add(new Mes(Month.JULY.toString()));
	meses.add(new Mes(Month.AUGUST.toString()));
	meses.add(new Mes(Month.SEPTEMBER.toString()));
	meses.add(new Mes(Month.OCTOBER.toString()));
	meses.add(new Mes(Month.NOVEMBER.toString()));
	meses.add(new Mes(Month.DECEMBER.toString()));
}


public Registro(int anno, ArrayList<Mes> meses) {
	super();
	this.anno = anno;
	this.meses = meses;
}




}


package br.acme.tests;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TesteDatas {

	public static void main(String[] args) throws ParseException{
		Calendar c = Calendar.getInstance();
		Date data = c.getTime();
		DateFormat f = DateFormat.getDateInstance();
					
		 f = DateFormat.getDateInstance(DateFormat.FULL); //Data COmpleta
		System.out.println("Data brasileira: "+f.format(data));

        f = DateFormat.getDateInstance(DateFormat.LONG);
		System.out.println("Data sem o dia descrito: "+f.format(data));
		
		f = DateFormat.getDateInstance(DateFormat.MEDIUM);
		System.out.println("Data resumida 1: "+f.format(data));
		
		f = DateFormat.getDateInstance(DateFormat.SHORT);
		System.out.println("Data resumida 2: "+f.format(data));
		
		//-------------------------
		String dia = "13", mes="10", ano="2016";
		
		Date data2 = f.parse(dia+"/"+mes+"/"+ano);
		System.out.println(data2);
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		System.out.println("Data formatada: "+sdf.format(data2));
		
		//Converte Objetos
		System.out.println("Data convertida: "+sdf.parse("02/08/1970"));  
	}
}

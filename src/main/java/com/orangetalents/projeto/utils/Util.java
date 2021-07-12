package com.orangetalents.projeto.utils;

import java.text.DateFormatSymbols;
import java.util.Calendar;

public class Util {
	public static String getDiaSemana(){
        return new DateFormatSymbols().getWeekdays()[Calendar.getInstance().get(Calendar.DAY_OF_WEEK)];
    }
	
	public static String getDiaDesconto(String isbn) {
		String diaSemana = "";
		String ultimoDigitoIsbn = isbn.substring(isbn.length() - 1);

		switch (ultimoDigitoIsbn) {
			case "0":
			case "1":
				diaSemana = "segunda-feira";
				break;
	
			case "2":
			case "3":
				
				diaSemana = "terça-feira";
				break;
	
			case "4":
			case "5":
				diaSemana = "quarta-feira";
				break;
	
			case "6":
			case "7":
				diaSemana = "quinta-feira";
				break;
	
			case "8":
			case "9":
				diaSemana = "sexta-feira";
				break;
		}

		return diaSemana;
	}

}

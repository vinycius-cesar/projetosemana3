package com.projetosemana3.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DataAtual {

	static Date dataAtual = new Date();
	
	public String retornarData() {
		String data = new SimpleDateFormat("dd/mm/yyyy").format(dataAtual);
		return data;
	}
}

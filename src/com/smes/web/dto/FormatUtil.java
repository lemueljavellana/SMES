package com.smes.web.dto;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FormatUtil {
	public static String format (Date date) {
		SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
		return format.format(date);
	}
	
	public static String format (double value) {
		NumberFormat format = NumberFormat.getInstance();
		return format.format(value);
	}
}

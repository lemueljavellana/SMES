package com.smes.web;

import java.util.Calendar;
import java.util.Date;

public class ControllerUtil {
	public static Date setUpDate (Date date){
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		Calendar c1 = Calendar.getInstance();
		c.set(Calendar.HOUR, c1.get(Calendar.HOUR));
		c.set(Calendar.MINUTE, c1.get(Calendar.MINUTE));
		c.set(Calendar.SECOND, c1.get(Calendar.SECOND));
		c.set(Calendar.MILLISECOND, c1.get(Calendar.MILLISECOND));
		return c.getTime();
		
	}
}

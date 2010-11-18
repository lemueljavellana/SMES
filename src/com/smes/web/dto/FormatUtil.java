package com.smes.web.dto;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Utility class that handles the formating.
 * @author Lemuel Javellana
 *
 */
public class FormatUtil {
	private static final SimpleDateFormat datFormat = new SimpleDateFormat("MM/dd/yyyy");
	/**
	 * Format date (MM/dd/yyyy) 
	 * @param date the date to be formated.
	 * @return the formated dater.
	 */
	public static String format (Date date) {
		return datFormat.format(date);
	}

	/**
	 * Format double #0,000.00
	 * @param value The double to be formated.
	 * @return The formatted value.
	 */
	public static String format (double value) {
		DecimalFormat format = new DecimalFormat ("#,###.##");
		return format.format(value);
	}

}

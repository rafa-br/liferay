package farias.rafael.liferay.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class MathUtils {

	
	public static BigDecimal formatValue(BigDecimal value) {
		return value.setScale(2, RoundingMode.HALF_UP);
	}
	
	public static BigDecimal formatTaxValue(BigDecimal value) {
		double roundedValue = formatValue(BigDecimal.valueOf(value.doubleValue())).doubleValue();
		return formatValue(BigDecimal.valueOf(Math.rint(20*roundedValue)/20));
	}
}

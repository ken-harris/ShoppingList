package com.goesll.shoppinglist.helper;

public class Conversion {

	// Gallon > Liter > Quart > Pint > Cup > Ounce > TableSpoon > Teaspoon
	public static String largestQuantity(String quantity1, String quantity2){
		
		// Gallon is the largest
		if(quantity1.contains("Gallon")){
			return quantity1;
		} else if(quantity2.contains("Gallon")){
			return quantity2;
		}
		
		// Liter is the next largest
		if(quantity1.contains("Liter")){
			return quantity1;
		} else if(quantity2.contains("Liter")){
			return quantity2;
		}
		
		// Quart is the next largest
		if(quantity1.contains("Quart")){
			return quantity1;
		} else if(quantity2.contains("Quart")){
			return quantity2;
		}
		
		// Pint is the next largest
		if(quantity1.contains("Pint")){
			return quantity1;
		} else if(quantity2.contains("Pint")){
			return quantity2;
		}
		
		// Cup is the next largest
		if(quantity1.contains("Cup")){
			return quantity1;
		} else if(quantity2.contains("Cup")){
			return quantity2;
		}
		
		// Ounce is the next largest
		if(quantity1.contains("Ounce")){
			return quantity1;
		} else if(quantity2.contains("Ounce")){
			return quantity2;
		}
		
		// Tablespoon is the next largest
		if(quantity1.contains("Tablespoon")){
			return quantity1;
		} else if(quantity2.contains("Tablespoon")){
			return quantity2;
		}
		
		// Teaspoon is the smallest
		if(quantity1.contains("Teaspoon")){
			return quantity1;
		} else if(quantity2.contains("Teaspoon")){
			return quantity2;
		}
		
		return "";
	}
	
	// Gallon > Liter > Quart > Pint > Cup > Ounce > TableSpoon > Teaspoon
	public static double convertTo(String quantityName, String quantityValue, String largerQuantity){
		if(quantityName.contains("Liter")){
			return litersToGallon(Double.parseDouble(quantityValue));
		} else if(quantityName.contains("Quart")){
			if(largerQuantity.contains("Liter")){
				return quartsToLiter(Double.parseDouble(quantityValue));
			} else {
				return quartsToGallon(Double.parseDouble(quantityValue));
			}
		} else if(quantityName.contains("Pint")){
			if(largerQuantity.contains("Quart")){
				return pintsToQuart(Double.parseDouble(quantityValue));
			} else if(largerQuantity.contains("Liter")){
				return pintsToLiter(Double.parseDouble(quantityValue));
			} else {
				return pintsToGallon(Double.parseDouble(quantityValue));
			}
		} else if(quantityName.contains("Cup")){
			if(largerQuantity.contains("Pint")){
				return cupsToPint(Double.parseDouble(quantityValue));
			} else if(largerQuantity.contains("Quart")){
				return cupsToQuart(Double.parseDouble(quantityValue));
			} else if(largerQuantity.contains("Liter")){
				return cupsToLiter(Double.parseDouble(quantityValue));
			} else {
				return cupsToGallon(Double.parseDouble(quantityValue));
			}
		} else if(quantityName.contains("Ounce")){
			if(largerQuantity.contains("Cup")){
				return ouncesToCup(Double.parseDouble(quantityValue));
			} else if(largerQuantity.contains("Pint")){
				return ouncesToPint(Double.parseDouble(quantityValue));
			} else if(largerQuantity.contains("Quart")){
				return ouncesToQuart(Double.parseDouble(quantityValue));
			} else if(largerQuantity.contains("Liter")){
				return ouncesToLiter(Double.parseDouble(quantityValue));
			} else {
				return ouncesToGallon(Double.parseDouble(quantityValue));
			}
		} else if(quantityName.contains("Tablespoon")){
			if(largerQuantity.contains("Ounce")){
				return tablespoonsToOunce(Double.parseDouble(quantityValue));
			} else if(largerQuantity.contains("Cup")){
				return tablespoonsToCup(Double.parseDouble(quantityValue));
			} else if(largerQuantity.contains("Pint")){
				return tablespoonsToPint(Double.parseDouble(quantityValue));
			} else if(largerQuantity.contains("Quart")){
				return tablespoonsToQuart(Double.parseDouble(quantityValue));
			} else if(largerQuantity.contains("Liter")){
				return tablespoonsToLiter(Double.parseDouble(quantityValue));
			} else {
				return tablespoonsToGallon(Double.parseDouble(quantityValue));
			}
		} else {
			if(largerQuantity.contains("Tablespoon")){
				return teaspoonsToTableSpoon(Double.parseDouble(quantityValue));
			} else if(largerQuantity.contains("Ounce")){
				return teaspoonsToOunce(Double.parseDouble(quantityValue));
			} else if(largerQuantity.contains("Cup")){
				return teaspoonsToCup(Double.parseDouble(quantityValue));
			} else if(largerQuantity.contains("Pint")){
				return teaspoonsToPint(Double.parseDouble(quantityValue));
			} else if(largerQuantity.contains("Quart")){
				return teaspoonsToQuart(Double.parseDouble(quantityValue));
			} else if(largerQuantity.contains("Liter")){
				return teaspoonsToLiter(Double.parseDouble(quantityValue));
			} else {
				return teaspoonsToGallon(Double.parseDouble(quantityValue));
			}
		}		
	}
	
	// CUPS to --
	
	public static double cupsToGallon(double cups){
		return cups * 0.0625;
	}
	
	public static double cupsToQuart(double cups){
		return cups * 0.25;
	}
	
	public static double cupsToOunce(double cups){
		return cups * 8;
	}
	
	public static double cupsToPint(double cups){
		return cups * 0.5;
	}
	
	public static double cupsToMilliliter(double cups){
		return cups * 236.588237;
	}
	
	public static double cupsToLiter(double cups){
		return cups * 0.236588;
	}
	
	public static double cupsToTableSpoon(double cups){
		return cups * 16;
	}
	
	public static double cupsToTeaspoon(double cups){
		return cups * 48;
	}
	
	// OUNCES to --
	
	public static double ouncesToPound(double ounces){
		return ounces * 0.0625;
	}
	
	public static double ouncesToCup(double ounces){
		return ounces * 0.125;
	}
	
	public static double ouncesToLiter(double ounces){
		return ounces * 0.0295735;
	}
	
	public static double ouncesToTeaSpoon(double ounces){
		return ounces * 6;
	}
	
	public static double ouncesToTableSpoon(double ounces){
		return ounces * 2;
	}
	
	public static double ouncesToGallon(double ounces){
		return ounces * 0.0078125;
	}
	
	public static double ouncesToQuart(double ounces){
		return ounces * 0.03125;
	}
	
	public static double ouncesToPint(double ounces){
		return ounces * 0.0625;
	}
	
	// PINTS to --
	
	public static double pintsToGallon(double pints){
		return pints * 0.125;
	}
	public static double pintsToQuart(double pints){
		return pints * 0.5;
	}
	public static double pintsToCup(double pints){
		return pints * 2;
	}
	public static double pintsToOunce(double pints){
		return pints * 16;
	}
	public static double pintsToTableSpoon(double pints){
		return pints * 32;
	}
	public static double pintsToTeaSpoon(double pints){
		return pints * 96;
	}
	public static double pintsToLiter(double pints){
		return pints * 0.473176;
	}
	
	// POUNDS to --
	
	public static double poundsToOunce(double pounds){
		return pounds * 16;
	}
	
	// LITERS to --
	
	public static double litersToGallon(double liters){
		return liters * 0.264172;
	}
	
	public static double litersToQuart(double liters){
		return liters * 1.05669;
	}
	
	public static double litersToPint(double liters){
		return liters * 2.11338;
	}
	
	public static double litersToCup(double liters){
		return liters * 4.22675;
	}
	
	public static double litersToOunce(double liters){
		return liters * 33.814;
	}
	
	public static double litersToTableSpoon(double liters){
		return liters * 67.628;
	}
	
	public static double litersToTeaspoon(double liters){
		return liters * 202.884;
	}
	
	// QUARTS to --
	
	public static double quartsToGallon(double quarts){
		return quarts * 0.25;
	}
	
	public static double quartsToPint(double quarts){
		return quarts * 2;
	}
	
	public static double quartsToCup(double quarts){
		return quarts * 4;
	}
	
	public static double quartsToOunce(double quarts){
		return quarts * 32;
	}
	
	public static double quartsToTableSpoon(double quarts){
		return quarts * 64;
	}
	
	public static double quartsToTeaSpoon(double quarts){
		return quarts * 192;
	}
	
	public static double quartsToLiter(double quarts){
		return quarts * 0.946353;
	}
	
	// GALLONS TO --
	

	
	public static double gallonsToQuart(double gallons){
		return gallons * 4;
	}
	
	public static double gallonsToPint(double gallons){
		return gallons * 8;
	}
	
	public static double gallonsToCup(double gallons){
		return gallons * 16;
	}
	
	public static double gallonsToOunce(double gallons){
		return gallons * 128;
	}
	
	public static double gallonsToTableSpoon(double gallons){
		return gallons * 256;
	}
	
	public static double gallonsToTeaSpoon(double gallons){
		return gallons * 768;
	}
	
	public static double gallonsToLiter(double gallons){
		return gallons * 3.78541;
	}
	
	// TEASPOON TO --
	
	public static double teaspoonsToGallon(double teaspoons){
		return teaspoons * 0.00130208;
	}
	
	public static double teaspoonsToQuart(double teaspoons){
		return teaspoons * 0.00520833;
	}
	
	public static double teaspoonsToPint(double teaspoons){
		return teaspoons * 0.0104167;
	}
	
	public static double teaspoonsToCup(double teaspoons){
		return teaspoons * 0.0208333;
	}
	
	public static double teaspoonsToOunce(double teaspoons){
		return teaspoons * 0.166667;
	}
	
	public static double teaspoonsToTableSpoon(double teaspoons){
		return teaspoons * 0.333333;
	}
	
	public static double teaspoonsToLiter(double teaspoons){
		return teaspoons * 0.00492892;
	}
	
	
	// TABLESPOON TO --
	
	public static double tablespoonsToGallon(double tablespoons){
		return tablespoons * 0.00390625;
	}
	
	public static double tablespoonsToQuart(double tablespoons){
		return tablespoons * 0.015625;
	}
	
	public static double tablespoonsToPint(double tablespoons){
		return tablespoons * 0.03125;
	}
	
	public static double tablespoonsToCup(double tablespoons){
		return tablespoons * 0.0625;
	}
	
	public static double tablespoonsToOunce(double tablespoons){
		return tablespoons * 0.5;
	}
	
	public static double tablespoonsToTeaSpoon(double tablespoons){
		return tablespoons * 3;
	}
	
	public static double tablespoonsToLiter(double tablespoons){
		return tablespoons * 0.0147868;
	}
	
	
	
	
	
	
}

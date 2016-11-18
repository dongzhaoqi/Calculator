package sky.com.calculator;


import java.math.BigDecimal;
import java.math.RoundingMode;

public class MyMath {
    private static String str;
    private static BigDecimal b1, b2;

    public static String add(double operand1, double operand2){
        b1 = new BigDecimal(Double.toString(operand1));
        b2 = new BigDecimal(Double.toString(operand2));
        str = b1.add(b2).toString();
        return remove(str);
    }

    public static String subtract(double operand1, double operand2){
        b1 = new BigDecimal(Double.toString(operand1));
        b2 = new BigDecimal(Double.toString(operand2));
        str = b1.subtract(b2).toString();
        return remove(str);
    }

    public static String multiply(double operand1, double operand2){
        b1 = new BigDecimal(Double.toString(operand1));
        b2 = new BigDecimal(Double.toString(operand2));
        str = b1.multiply(b2).toString();
        return remove(str);
    }

    public static String divide(double operand1, double operand2){
        if(operand1 == 0){
            return "0";
        }
        if(operand2 == 0){
            return "error";
        }
        b1 = new BigDecimal(Double.toString(operand1));
        b2 = new BigDecimal(Double.toString(operand2));
        str = b1.divide(b2, 8, RoundingMode.HALF_UP).toString();
        return remove(str);
    }

    /**
     * remove redundant 0 from str
     * eg. 12.0 -> 12
     * @param str
     * @return
     */
    public static String remove(String str){
        //reference: https://codingbubble.github.io/2015/07/04/java-del-num-zero/
        if(str.indexOf(".") > 0){
            str = str.replaceAll("0+?$", "");    //remove redundant 0
            str = str.replaceAll("[.]$", "");    //remove the last "."
        }
        return str;
    }

    /**
     * judge if str can be cast to a number
     * Ctrl + Alt + h to see where this method is used
     * @param str
     * @return
     */
    public static boolean isNumeric(String str){
        try {
            double num=Double.valueOf(str);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}

package hoo.android.hooutil.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


//转换类
public class ParseUtils {
	private static final String TAG = "Tools";

    //将字符转换成int
	/**
     * str=>int
     * @param strNumber
     * @return
     */
    public static int convertStringToInt(String strNumber) {
        int intNumber = 0;

        if (strNumber != null && !strNumber.equals("")) {
            try {
                intNumber = new Integer(strNumber).intValue();
            } catch (NumberFormatException e) {
//            	if(Constant.DEBUG) Log.e(TAG,"NumberFormatException:>>" + e.toString() + "<<str>>" + strNumber);
            }
            return intNumber;
        } else {
            return 0;
        }
    }

    // 将字符转换成long
    /**
     * str=>long
     * @param strNumber
     * @return
     */
    public static long convertStringToLong(String strNumber) {
        long lNumber = 0;

        if (strNumber != null && !strNumber.equals("")) {
            try {
                lNumber = Long.parseLong(strNumber);
            } catch (NumberFormatException e) {
                lNumber = 0;
            }
            return lNumber;
        } else {
            return 0;
        }
    }

    //将字符转换成double
    /**
     * str=>double
     * @param strNumber
     * @return
     */
    public static double convertStringToDouble(String strNumber) {
        double dNumber = 0;

        if (strNumber != null && !strNumber.equals("")) {
            try {
                dNumber = Double.parseDouble(strNumber);
            } catch (NumberFormatException e) {
                dNumber = 0;
//                if(Constant.DEBUG) Log.e(TAG,"NumberFormatException:>>" + e.toString() + "<<str>>" + strNumber);
                throw e;
            }
            return dNumber;
        } else {
            return 0;
        }
    }

    //* 将object转换成为字符
    /**
     * obj=>str
     * @param o
     * @return
     */
    public static String convertObject(Object o) {
        String s = "";
        if (o == null) {
            return formatString(s);
        }else {
        	return formatString(o.toString());
        }
    }
    
    public static String formatString(Object o) {
        String s = "";
        if (o == null) {
            return s;
        }else {
        	Pattern p = Pattern.compile("\\s*|\t|\r|\n");
        	Matcher m = p.matcher(o.toString());
        	return m.replaceAll("");
        }
    }

    public static String formatTeleString(Object o) {
        String s = "";
        if (o == null) {
            return s;
        }else {
        	Pattern p = Pattern.compile(";|,| ");
        	Matcher m = p.matcher(o.toString());
        	return m.replaceAll(" ");
        }
    }
}

package hoo.android.hooutil.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 正则工具类
 */
public class RegularUtil {

    /*
     * 判别手机是否为正确手机号码； 号码段分配如下：
	 * 移动：134、135、136、137、138、139、150、151、157(TD)、158、159、187、188
	 * 联通：130、131、132、152、155、156、185、186
	 * 电信：133、153、180、189、（1349卫通） 182 147
	 * 虚拟：170-9
     */
	/**
     RegularPhoneNum
	 */
	public static boolean validatePhone(String num) {
		boolean flag=false;
		if (num != null) {
			Pattern p = Pattern
					.compile("^[1][34578]\\d{9}$"); 
//					.compile("^((13[0-9])|(15[^4,//D])|(18[0,5-9]))//d{8}$"); 
			Matcher m = p.matcher(num);
			flag= m.matches() && (num.length() == 11);
//			Log.e("RegularUtil", "flag:"+flag+"   num:"+num+"   length:"+ num.length() +"   "+ (num.length() == 11)  +"    "+m.matches());
			return flag;
		}
		return flag;
	}
	

    /*
    * * 验证邮箱
	 * 合法E-mail地址：
		1. 必须包含一个并且只有一个符号“@”
		2. 第一个字符不得是“@”或者“.”
		3. 不允许出现“@.”或者.@
		4. 结尾不得是字符“@”或者“.”
		5. 允许“@”前的字符中出现“＋”
		6. 不允许“＋”在最前面，或者“＋@”
    * */
	/**
	 * @param num
	 * @return
	 */
	public static boolean validateEmail(String num) {
		if(BaseStringUtil.isNotEmpty(num)){
			String strPattern = "^[a-zA-Z0-9][\\w\\.-]*[a-zA-Z0-9]@[a-zA-Z0-9][\\w\\.-]*[a-zA-Z0-9]\\.[a-zA-Z][a-zA-Z\\.]*[a-zA-Z]$";
		    Pattern p = Pattern.compile(strPattern);
		    Matcher m = p.matcher(num);
		    return m.matches();  
		}else{
			return false;
		}
		
	}
	//验证邮编
	/**
	 *
	 */
	public static boolean validatePostalCode(String num) {
		String strPattern ="^[1-9]\\d{5}(?!\\d)$";
	    Pattern p = Pattern.compile(strPattern);
	    Matcher m = p.matcher(num);
	    return m.matches();  
	}
	
	//验证身份证
	/**
	 *
	 */
	public static boolean VerifyIdCard(String num) {
//		"^\d{15}$)|(^\d{17}([0-9]|X)$";
		String strPattern ="^(\\d{14}|\\d{17})(\\d|[xX])$";
	    Pattern p = Pattern.compile(strPattern);
	    Matcher m = p.matcher(num);
	    return m.matches()&&(num.length()==18||num.length()==15);  
	}
	
	//身份证验证类
	/**
	 *
	 * @author h
	 */
	class VerifyIdCard {  
	    // wi =2(n-1)(mod 11);加权因子  
	    final int[] wi = { 7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2, 1 };  
	    // 校验码  
	    final int[] vi = { 1, 0, 'X', 9, 8, 7, 6, 5, 4, 3, 2 };  
	    private int[] ai = new int[18];  
	  
	    public VerifyIdCard() {  
	    }  
	  
	    // 校验身份证的校验码  
	    public boolean verify(String idcard) {
	        if (idcard.length() == 15) {  
	            idcard = uptoeighteen(idcard);  
	        }  
	        if (idcard.length() != 18) {  
	            return false;  
	        }  
	        String verify = idcard.substring(17, 18);
	        if (verify.equals(getVerify(idcard))) {  
	            return true;  
	        }  
	        return false;  
	    }  
	  
	    // 15位转18位  
	    public String uptoeighteen(String fifteen) {
	        StringBuffer eighteen = new StringBuffer(fifteen);
	        eighteen = eighteen.insert(6, "19");  
	        return eighteen.toString();  
	    }  
	    // 计算最后一位校验值  
	    public String getVerify(String eighteen) {
	        int remain = 0;  
	        if (eighteen.length() == 18) {  
	            eighteen = eighteen.substring(0, 17);  
	        }  
	        if (eighteen.length() == 17) {  
	            int sum = 0;  
	            for (int i = 0; i < 17; i++) {  
	                String k = eighteen.substring(i, i + 1);
	                ai[i] = Integer.valueOf(k);
	            }
	            for (int i = 0; i < 17; i++) {  
	                sum += wi[i] * ai[i];  
	            }
	            remain = sum % 11;  
	        }  
	        return remain == 2 ? "X" : String.valueOf(vi[remain]);
	  
	    }  
	}
    //判断是否全是数字
    /**
     *
     * @param str
     * @return true
     */
    public static boolean isNumeric(String str) {
        Pattern pattern = Pattern.compile("[0-9]*");
        return pattern.matcher(str).matches();
    }


    //判断是否是电话号码
    /**
     * @param str
     * @return true  false
     */
    public static boolean isNumber(String str) {
        str = str.replaceAll("-", "");
        return isNumeric(str);
    }

    //判断是否是手机号
    /**
     *
     * @param phoneNumber
     */
    public static boolean isMobile(String phoneNumber){
        return phoneNumber.matches("^(13|14|15|18)\\d{9}$");
    }


    //判断是否是格式良好的电话号码
    /**
     * +86
     * @param number1
     * @return
     */
    public static boolean isGoodNumber(String number1) {
        if (number1 == null) {
            return false;
        }
        String expr = "^(([\\+|0]{0,1}86){0,1})1\\d{10}";
        return number1.trim().matches(expr);
    }


    //判断是否是合理的电话号码
    /**
     * 400 800
     * @param phoneNumber
     */
    public static boolean isPhoneNumber(String phoneNumber){
        if(isNumber(phoneNumber) || isTelephone(phoneNumber)){
            return true;
        } else if(phoneNumber.matches("^(400|800)\\d{7}$")) {
            return true;
        } else if(phoneNumber.matches("^(955|100)\\d{2}$")){
            return true;
        } else {
            return false;
        }
    }


    //判断是否是固定电话
    /**
     * @param phoneNumber
     */
    public static boolean isTelephone(String phoneNumber){
        boolean boo = phoneNumber.matches("^0(([1,2]\\d)|([3-9]\\d{2}))\\d{8}$");
        if(boo==false){
            boo = phoneNumber.matches("^0(([1,2]\\d)|([3-9]\\d{2}))\\d{7}$");
        }
        return boo;
    }


    public static boolean isURL(String url){
        Pattern pattern = Pattern.compile("http(s)?:\\/\\/([\\w-]+\\.)+[\\w-]+(\\/[\\w- .\\/?%&=]*)?");
        return pattern.matcher(url).matches();
    }
}

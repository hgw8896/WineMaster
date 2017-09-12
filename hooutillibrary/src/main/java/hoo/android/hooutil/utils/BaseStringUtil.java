package hoo.android.hooutil.utils;

import android.content.Context;
import android.text.InputFilter;
import android.text.Spanned;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

//字符串处理类
public class BaseStringUtil {
	/**
	 * null ""
	 * @param s
	 * @return
	 */
	public static boolean isNotEmpty(String s){
		if (s != null && s.trim().length() > 0&&!"".equals(s)&&!"\"\"".equals(s)&&!"null".equals(s.trim())&&!"\"null\"".equals(s.trim())) {
			return true;
		}
		return false;
	}

    public static boolean isNotNull(String s){
        if (s != null) {
            return true;
        }
        return false;
    }
	
	public static boolean isNotEmpty(Object obj){
		if(obj!=null&&!"".equals(obj)&&!"\"\"".equals(obj)&&!"null".equals(obj.toString())){
			return true;
		}
		return false;
	}
    public static boolean isNotEmpty(String[] strings){
        if (isNotEmpty(strings)&&strings.length>0){
            return true;
        }
        return false;
    }

    public static boolean isNotEmpty(List strings){
        if (isNotEmpty(strings)&&strings.size()>0){
            return true;
        }
        return false;
    }

        /*
         * limit str whether length
         * 是否符合长度限制
         * @param str string
         * @param lesser lesser
         * @param large  large
         * @return ture is /false is not
         */
	public static boolean isLengthLimit(String str,int lesser,int large){
		if(isNotEmpty(str)&&str.length()>=lesser&&str.length()<=large){
			return true;
		}
		return false;
	}
	
	/**
     *
     * compare two String 1<2
	 * @return true/false
	 */
	public static boolean compareInteger(String str1,String str2){
		Log.e("sringutil", "str1:" + str1 + "str2:" + str2);
		if(isNotEmpty(str1)&&isNotEmpty(str2)){
			if(Integer.parseInt(str1)< Integer.parseInt(str2)){
				return true;
			}
		}
		return false;
	}
	
	public static boolean compareIntegerEqual(String str1,String str2){
		if(isNotEmpty(str1)&&isNotEmpty(str2)){
			if(Integer.parseInt(str1)== Integer.parseInt(str2)){
				return true;
			}
		}
		return false;
	}
	
	public static String toMD5String(String string){
		char md5String[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
				'a', 'b', 'c', 'd', 'e', 'f' };
		byte[] btInput = string.getBytes();
		try {
			MessageDigest mdInst = MessageDigest.getInstance("MD5");
			
			mdInst.update(btInput);

			byte[] md = mdInst.digest();

			int j = md.length;
			char str[] = new char[j * 2];
			int k = 0;
			for (int i = 0; i < j; i++) {
				byte byte0 = md[i];
				str[k++] = md5String[byte0 >>> 4 & 0xf];
				str[k++] = md5String[byte0 & 0xf];
			}
			return new String(str);
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}

	}

    //判断数量大于standard标准是显示类型
	public static String genNewMsgCountString(int newMsgCount,int standard,String sign) {
		if (newMsgCount != 0) {
			if (newMsgCount > standard) {
				return "99"+sign;
			} else {
				return "" + newMsgCount;
			}
		} else {
			return "";
		}
	}

    /**
     * char num
     *
     * @param context context
     * @param editText et
     * @param max_length max length
     * @param err_msg errMessage
     * @param title hint
     * @param defaultText defaultText
     */
    public static void lengthFilter( final Context context, EditText editText,
                                     final int max_length, final String err_msg,final TextView title,String defaultText){

        InputFilter[] filters = new InputFilter[1];
        filters[0] = new InputFilter.LengthFilter(max_length){

            @Override
            public CharSequence filter(CharSequence source, int start, int end,
                                       Spanned dest, int dstart, int dend) {
                // TODO Auto-generated method stub
                //获取字符个数(一个中文算2个字符)
                int destLen = 0;
                int sourceLen = getCharacterNum(source.toString());
                if (!BaseStringUtil.isNotEmpty(source)){
                    String dest_temp = dest.toString();
                    String dest_temp1 = dest_temp.substring(0,dest_temp.length()-(dend-dstart));
                    destLen = getCharacterNum(dest_temp1.toString());
                }else{

                    destLen = getCharacterNum(dest.toString());
                }
                if(destLen + sourceLen > max_length){
                    int del;
                    CharSequence temp_max_str;
                    if (destLen == 0){
                        temp_max_str = getMaxLengthSource(source.toString(),max_length);
                        del =max_length-getCharacterNum(temp_max_str.toString());
                    }else{
                        temp_max_str = getMaxLengthSource(source.toString(),max_length-destLen);
                        del =max_length-getCharacterNum(temp_max_str.toString())-destLen;
                    }
                    if (title!=null)
                    title.setText(del+"");
//                    Toast.makeText(context, err_msg, Toast.LENGTH_SHORT).show();
                    return temp_max_str;
                }else{
                    if (title!=null)
                    title.setText((max_length-destLen - sourceLen)+"");
                }
                return source;
            }

        };
        editText.setFilters(filters);
        if (isNotEmpty(editText)){
            editText.setText(getMaxLengthSource(defaultText,max_length));
            editText.setSelection( editText.length());
        }

    }




    /**
     *
     * @param content
     * @return
     */
    public static int getCharacterNum(String content){
        if(content.equals("")||null == content){
            return 0;
        }else {
            return content.length()+getChineseNum(content);
        }

    }

    public static CharSequence getMaxLengthSource(String src,int num){
        int count = 0;
        int sindex = 0;
        while (count <= num && sindex < src.length()) {
            char c = src.charAt(sindex++);
            if (c < 128) {
                count = count + 1;
            } else {
                count = count + 2;
            }
        }
        if (count > num) {
            sindex--;
        }
        return src.subSequence(0, sindex);
    }

    /**
     * chinese length
     * @param s String
     * @return num
     */
    public static int getChineseNum(String s){
        int num = 0;
        char[] myChar = s.toCharArray();
        for(int i=0;i<myChar.length;i++){
            if((char)(byte)myChar[i] != myChar[i]){
                num++;
            }
        }
        return num;
    }
}

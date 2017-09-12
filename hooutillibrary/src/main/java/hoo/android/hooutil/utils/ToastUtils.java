package hoo.android.hooutil.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by h on 2017/5/25.
 */
public class ToastUtils {
    public static void showShort(Context mContext,String text){
        Toast.makeText(mContext,text,Toast.LENGTH_SHORT).show();
    }

    public static void showLong(Context mContext,String text){
        Toast.makeText(mContext,text,Toast.LENGTH_LONG).show();
    }
}

package hoo.android.hooutil.utils;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

/**
 * @Author HOO
 * @Description 控制软键盘的工具类
 * @Date 2013-1-21
 */
public class SoftInputUtil {

	/**
	 * @Description 软件盘始终隐藏
	 * @param activity
	 */
	public static void setSoftInputAlwaysHidden(Activity activity) {
		activity.getWindow()
				.setSoftInputMode(
						WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE
								| WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
	}

	/**
	 * @Description 软件盘始终显示
	 * @param activity
	 */
	public static void setSoftInputAlwaysVisiable(Activity activity) {
		activity.getWindow()
				.setSoftInputMode(
						WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE
								| WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
	}

	/**
	 * @Description 隐藏软键盘
	 */
	public static void hideSoftKeyboard(EditText editText) {
		InputMethodManager localInputMethodManager = (InputMethodManager) editText
				.getContext().getSystemService("input_method");
		localInputMethodManager.hideSoftInputFromWindow(
				editText.getWindowToken(),
				InputMethodManager.RESULT_UNCHANGED_SHOWN);
	}
	
	public static void showSoftKeyboard(EditText editText) {
		InputMethodManager localInputMethodManager = (InputMethodManager) editText
				.getContext().getSystemService("input_method");
//		localInputMethodManager.showSoftInput(editText, 2);
		localInputMethodManager.toggleSoftInputFromWindow(editText.getWindowToken(), 0, 0);
	}


    /**
     * 根据EditText所在坐标和用户点击的坐标相对比，来判断是否隐藏键盘，因为当用户点击EditText时则不能隐藏
     *
     * @param v
     * @param event
     * @return
     */
    public static boolean isShouldHideKeyboard(View v, MotionEvent event) {
        if (v != null && (v instanceof EditText)) {
            int[] l = {0, 0};
            v.getLocationInWindow(l);
            int left = l[0],
                    top = l[1],
                    bottom = top + v.getHeight(),
                    right = left + v.getWidth();
            if (event.getX() > left && event.getX() < right
                    && event.getY() > top && event.getY() < bottom) {
                // 点击EditText的事件，忽略它。
                return false;
            } else {
                return true;
            }
        }
        return false;
    }

    /**
     * 隐藏键盘
     */
    public static void keyBoxGone(Context ctx,View v){
        InputMethodManager imm = (InputMethodManager)ctx.getSystemService(Context.INPUT_METHOD_SERVICE);
        Log.e("Tools====>keyBoxGone", "isActive:" + imm.isActive());
        imm.hideSoftInputFromWindow(v.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS );
    }

    /**
     * 隐藏键盘
     */
    public static void keyBoxGone2(Context ctx,View v){
        InputMethodManager imm = (InputMethodManager)ctx.getSystemService(Context.INPUT_METHOD_SERVICE);
        Log.e("Tools====>keyBoxGone", "isActive:"+imm.isActive());
        //如果开启
        imm.hideSoftInputFromWindow(v.getWindowToken(), 0); //强制隐藏键盘
        //关闭软键盘，开启方法相同，这个方法是切换开启与关闭状态的
    }
    /**
     * 弹出键盘
     */
    public static void keyBoxShow(Context ctx,View v){
        InputMethodManager imm = (InputMethodManager)ctx.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(v, InputMethodManager.SHOW_FORCED);
    }

}

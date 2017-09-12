package hoo.android.hooutil.utils;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;

public class DensityUtil {

	private static final int LOW_DPI_STATUS_BAR_HEIGHT = 19;

	private static final int MEDIUM_DPI_STATUS_BAR_HEIGHT = 25;

	private static final int HIGH_DPI_STATUS_BAR_HEIGHT = 38;

	public static int dip2px(Context context, float dpValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (dpValue * scale + 0.5f);
	}

	public static int px2dip(Context context, float pxValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (pxValue / scale + 0.5f);
	}

	public static int getStatusBarHeight(Context context) {
		DisplayMetrics displayMetrics = new DisplayMetrics();
		((WindowManager) context.getSystemService(Context.WINDOW_SERVICE))
				.getDefaultDisplay().getMetrics(displayMetrics);

		switch (displayMetrics.densityDpi) {
		case DisplayMetrics.DENSITY_HIGH:
			return HIGH_DPI_STATUS_BAR_HEIGHT;
		case DisplayMetrics.DENSITY_MEDIUM:
			return MEDIUM_DPI_STATUS_BAR_HEIGHT;
		case DisplayMetrics.DENSITY_LOW:
			return LOW_DPI_STATUS_BAR_HEIGHT;
		default:
			return HIGH_DPI_STATUS_BAR_HEIGHT;
		}
	}

}

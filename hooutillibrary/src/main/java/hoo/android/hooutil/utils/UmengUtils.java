package hoo.android.hooutil.utils;

import android.content.Context;

import com.umeng.analytics.MobclickAgent;


/**
 * @date 2015-1-5 上午10:48:48
 */
public class UmengUtils {

    private static boolean mIsDebug = false;

    /**
     *  set debug model,not add data
     * @param debug
     */
    public static void setDebug(boolean debug) {
        mIsDebug = debug;
    }

    public static void setCatchUncaughtExceptions(boolean isCatch) {
        if (mIsDebug) {
            MobclickAgent.setCatchUncaughtExceptions(false);
            return;
        }
        MobclickAgent.setCatchUncaughtExceptions(isCatch);
    }

    public static void openActivityDurationTrack(boolean isOpenActivityDurationTrack) {
        if (mIsDebug)
            return;
        MobclickAgent.openActivityDurationTrack(isOpenActivityDurationTrack);
    }

    public static void onResume(Context context) {
        if (mIsDebug)
            return;
        MobclickAgent.onResume(context);
    }

    public static void onPause(Context context) {
        if (mIsDebug)
            return;
        MobclickAgent.onPause(context);
    }

    public static void onPageStart(String tag) {
        if (mIsDebug)
            return;
        MobclickAgent.onPageStart(tag);
    }

    public static void onPageEnd(String tag) {
        if (mIsDebug)
            return;
        MobclickAgent.onPageEnd(tag);
    }

    /**
     * exit
     * @param context
     */
    public static void onKillProcess(Context context) {
        if (mIsDebug)
            return;
        MobclickAgent.onKillProcess(context);
    }

    public static void reportError(Context context, String error) {
        if (mIsDebug)
            return;
        MobclickAgent.reportError(context, error);
    }

    public static void onEventBegin(Context context, String event) {
        if (mIsDebug)
            return;
        MobclickAgent.onEventBegin(context, event);
    }

    public static void onEventEnd(Context context, String event) {
        if (mIsDebug)
            return;
        MobclickAgent.onEventEnd(context, event);
    }

    public static void onEvent(Context context, String event_id) {
        if (mIsDebug)
            return;
        MobclickAgent.onEvent(context, event_id);
    }
}

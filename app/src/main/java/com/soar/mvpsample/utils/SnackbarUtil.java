package com.soar.mvpsample.utils;

import android.graphics.Color;
import android.support.annotation.ColorInt;
import android.support.annotation.IntDef;
import android.support.annotation.IntRange;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.soar.mvpsample.R;


/**
 * NAME：YONG_
 * Created at: 2018/3/1 10
 * Describe: http://blog.csdn.net/xfhy_/article/details/78239450
 * https://www.jianshu.com/p/cd1e80e64311/
 */

public class SnackbarUtil {
    /**
     * 信息类型
     */
    public static final int INFO = 1;
    /**
     * 确认信息类型
     */
    public static final int CONFIRM = 2;
    /**
     * 警告类型
     */
    public static final int WARNING = 3;
    /**
     * 错误类型
     */
    public static final int ALERT = 4;

    /**
     * 信息类型的背景颜色
     */
    public final static int BLUE = 0xff2195f3;
    /**
     * 确认信息类型背景颜色
     */
    public final static int GREEN = 0xff4caf50;
    /**
     * 警告类型背景颜色
     */
    public final static int ORANGE = 0xffffc107;
    /**
     * 错误类型背景颜色
     */
    public final static int RED = 0xfff44336;
    /**
     * action文本颜色  白色
     */
    public final static int WHITE = 0xffFFFFFF;

    /**
     * 消息类型   替代Java中的枚举类型
     */
    @IntDef({INFO, CONFIRM, WARNING, ALERT})
    private @interface MessageType {
    }

    /**
     * 基本普通Snackbar
     *
     * @param view 随意传入一个当前界面View（底层判断父类有没CoordinatorLayout,没就获取最外层view）
     */
    public static void showSnackbar(@NonNull View view, @NonNull String message, int duration) {
        Snackbar.make(view, message, duration).show();
    }

    /**
     * 显示Snackbar,时长:短时间(1570ms)，可选预设类型MessageType
     */
    public static void showShortSnackbar(@NonNull View view, @NonNull String message, @MessageType int type) {
        Snackbar snackbar = Snackbar.make(view, message, Snackbar.LENGTH_SHORT);
        switchType(snackbar, type);
        snackbar.show();
    }

    /**
     * 显示Snackbar,时长:短时间(1570ms)，可自定义颜色
     *
     * @param messageColor    消息文本颜色
     * @param backgroundColor 背景颜色
     */
    public static void showShortSnackbar(@NonNull View view, @NonNull String message, @ColorInt int
            messageColor, @ColorInt int backgroundColor) {
        Snackbar snackbar = Snackbar.make(view, message, Snackbar.LENGTH_SHORT);
        setSnackbarColor(snackbar, messageColor, backgroundColor);
        snackbar.show();
    }

    public static void showShortSnackbar(@NonNull View view, @NonNull String message, @MessageType
            int type, @Nullable CharSequence text, @NonNull View.OnClickListener listener) {
        Snackbar snackbar = Snackbar.make(view, message, Snackbar.LENGTH_SHORT).setAction(text, listener).setActionTextColor(WHITE);
        switchType(snackbar, type);
        snackbar.show();
    }

    /**
     * 显示Snackbar,时长:长时间(2750ms) 可选预设类型MessageType
     */
    public static void showLongSnackbar(@NonNull View view, @NonNull String message, @MessageType int type) {
        Snackbar snackbar = Snackbar.make(view, message, Snackbar.LENGTH_LONG);
        switchType(snackbar, type);
        snackbar.show();
    }

    public static void showLongSnackbar(@NonNull View view, @NonNull String message, @MessageType int type, @Nullable CharSequence text, @NonNull View.OnClickListener listener) {
        Snackbar snackbar = Snackbar.make(view, message, Snackbar.LENGTH_LONG).setAction(text, listener).setActionTextColor(WHITE);
        switchType(snackbar, type);
        snackbar.show();
    }

    /**
     * 显示Snackbar,时长:长时间(2750ms)，可自定义颜色
     */
    public static void showLongSnackbar(@NonNull View view, @NonNull String message, @ColorInt int messageColor, @ColorInt int backgroundColor) {
        Snackbar snackbar = Snackbar.make(view, message, Snackbar.LENGTH_LONG);
        setSnackbarColor(snackbar, messageColor, backgroundColor);
        snackbar.show();
    }

    /**
     * 自定义时长显示Snackbar，自定义颜色
     */
    public static void showCustomTimeSnackbar(@NonNull View view, @NonNull String message, @IntRange(from = 1) int duration,
                                              @ColorInt int messageColor, @ColorInt int backgroundColor) {
        Snackbar snackbar = Snackbar.make(view, message, Snackbar.LENGTH_INDEFINITE).setDuration(duration);
        setSnackbarColor(snackbar, messageColor, backgroundColor);
        snackbar.show();
    }

    /**
     * 自定义时长显示Snackbar，可选预设类型MessageType
     *
     * @param duration 显示时长   单位:ms
     */
    public static void showCustomTimeSnackbar(@NonNull View view, @NonNull String message, @IntRange(from = 1) int duration, @MessageType int type) {
        Snackbar snackbar = Snackbar.make(view, message, Snackbar.LENGTH_INDEFINITE).setDuration(duration);
        switchType(snackbar, type);
        snackbar.show();
    }

    /**
     * 向Snackbar中添加view
     *
     * @param index 新加布局在Snackbar中的位置
     */
    public static void SnackbarAddView(Snackbar snackbar, @LayoutRes int layoutId, int index) {
        View snackbarView = snackbar.getView();
        Snackbar.SnackbarLayout snackbarLayout = (Snackbar.SnackbarLayout) snackbarView;
        View addView = LayoutInflater.from(snackbarView.getContext()).inflate(layoutId, null);
        LinearLayout.LayoutParams p = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        p.gravity = Gravity.CENTER_VERTICAL;
        snackbarLayout.addView(addView, index, p);
    }

    /**
     * 设置Snackbar背景颜色
     */
    private static void setSnackbarBgColor(Snackbar snackbar, @ColorInt int backgroundColor) {
        View view = snackbar.getView();
        view.setBackgroundColor(backgroundColor);
    }

    /**
     * 设置Snackbar文字和背景颜色
     */
    private static void setSnackbarColor(Snackbar snackbar, @ColorInt int messageColor, @ColorInt int backgroundColor) {
        View view = snackbar.getView();  //获取Snackbar自己的布局
        //设置Snackbar自己的布局的背景颜色
        view.setBackgroundColor(backgroundColor);
        //设置Snackbar自己的布局中的TextView的颜色
        ((TextView) view.findViewById(R.id.snackbar_text)).setTextColor(messageColor);
    }

    /**
     * 切换预设消息类型
     */
    private static void switchType(Snackbar snackbar, @MessageType int type) {
        switch (type) {
            case INFO:
                setSnackbarBgColor(snackbar, BLUE);
                break;
            case CONFIRM:
                setSnackbarBgColor(snackbar, GREEN);
                break;
            case WARNING:
                setSnackbarBgColor(snackbar, ORANGE);
                break;
            case ALERT:
                setSnackbarColor(snackbar, Color.YELLOW, RED);
                break;
        }
    }
}

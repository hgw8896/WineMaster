package hoo.android.hooutil.utils;

import android.content.Context;
import android.text.TextPaint;
import android.text.style.ClickableSpan;
import android.view.View;


//多色TextView 可单独点击 多见回复评论
public class BaseClickableSpan extends ClickableSpan {

    private Context context;

    public BaseClickableSpan() {
    }

    public BaseClickableSpan(Context context) {
        super();
        this.context = context;
    }

    @Override
    public void updateDrawState(TextPaint ds) {
        //ds.setColor(context.getResources().getColor(R.color.black_gray));
    }

    @Override
    public void onClick(View widget) {

    }

}
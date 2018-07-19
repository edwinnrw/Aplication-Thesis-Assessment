package skripsi.edwin.filkomapps.util;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.text.Spannable;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import skripsi.edwin.filkomapps.R;

public class GuideMessageView extends LinearLayout {

    private Paint mPaint;
    private RectF mRect;

    private TextView mTitleTextView;
    private TextView mContentTextView;
    private TextView mOk;
    float density;


    GuideMessageView(Context context) {
        super(context);

        density = context.getResources().getDisplayMetrics().density;
        setWillNotDraw(false);

        mRect = new RectF();

        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
//        float radius = density * 3.0f;
//        float dy = density * 2f;
//        mPaint.setShadowLayer(radius, 0, dy, 0xFF3D3D3D);

        setLayerType(LAYER_TYPE_SOFTWARE, null);
        setOrientation(HORIZONTAL);
        setGravity(Gravity.CENTER);


        final int padding = (int) (10 * density);
        final int paddingBetween = (int) (3 * density);
        LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT);
        params.setMargins(0,7,0,0);
        params.weight=1;
        mTitleTextView = new TextView(context);
        mTitleTextView.setPadding(padding, padding, padding, paddingBetween);
        mTitleTextView.setGravity(Gravity.CENTER);
        mTitleTextView.setTextColor(Color.BLACK);
        addView(mTitleTextView, params);

        mContentTextView = new TextView(context);
        mContentTextView.setTextColor(Color.BLACK);
        mContentTextView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 10);
        mContentTextView.setPadding(padding, paddingBetween,0, padding);
        mContentTextView.setGravity(Gravity.CENTER_VERTICAL);

        addView(mContentTextView, params);
        LayoutParams params1 = new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT);
        params1.setMargins(10,0,0,0);
//
        mOk=new TextView(context);
        mOk.setTextColor(context.getResources().getColor(R.color.colorOrangeFilkomNewApp));
        mOk.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 16);
        mOk.setPadding(20,0,20,0);
        mOk.setBackgroundResource(R.drawable.round_oke);
//        mOk.setBackgroundColor(context.getResources().getColor(R.color.colorOrangeFilkomNewApp));
        mOk.setGravity(Gravity.CENTER);
        mOk.setText("OK");
        addView(mOk, params1);


    }


    public void setTitle(String title) {
        if (title == null) {
            removeView(mTitleTextView);
            return;
        }
        mTitleTextView.setText(title);
    }


    public void setContentText(String content) {
        mContentTextView.setText(content);
    }

    public void setContentSpan(Spannable content) {
        mContentTextView.setText(content);
    }

    public void setContentTypeFace(Typeface typeFace) {
        mContentTextView.setTypeface(typeFace);
    }

    public void setTitleTypeFace(Typeface typeFace) {
        mTitleTextView.setTypeface(typeFace);
    }

    public void setTitleTextSize(int size) {
        mTitleTextView.setTextSize(TypedValue.COMPLEX_UNIT_SP, size);
    }

    public void setContentTextSize(int size) {
        mContentTextView.setTextSize(TypedValue.COMPLEX_UNIT_SP, size);
    }

    public void setColor(int color) {

        mPaint.setAlpha(255);
        mPaint.setColor(color);

        invalidate();
    }

    int location[] = new int[2];

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);


        this.getLocationOnScreen(location);


        mRect.set(getPaddingLeft(),
                getPaddingTop(),
                canvas.getWidth() - getPaddingRight(),
                canvas.getHeight() - getPaddingBottom());


        canvas.drawRoundRect(mRect, 15, 15, mPaint);
    }
}

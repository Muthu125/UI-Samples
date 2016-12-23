package uiexamples.msf.com.uiandroidexamples.adapters;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.SweepGradient;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;

import uiexamples.msf.com.uiandroidexamples.R;

/**
 * Created by muthuv on 12/22/2016.
 */

public class AccountProgressCircle extends View {

    // ColorInt
    private int startColor;
    // ColorInt
    private int endColor;
    // ColorInt
    private int defaultColor;

    private int percentEndColor;

    private int strokeWidth;
    private float percent;

    // ç”¨äºŽæ¸å˜
    private Paint paint;


    public AccountProgressCircle(Context context) {
        super(context);
        init(context, null);
    }

    public AccountProgressCircle(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public AccountProgressCircle(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public AccountProgressCircle(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context, attrs);
    }


    private void init(final Context context, final AttributeSet attrs) {
        float defaultPercent = -1;
        if (isInEditMode()) {
            defaultPercent = 0.6f;
        }

        do {
            final int strokeWdithDefaultValue = (int) (18 * getResources().getDisplayMetrics().density + 0.5f);
            if (context == null || attrs == null) {
                strokeWidth = strokeWdithDefaultValue;
                percent = defaultPercent;
                startColor = getResources().getColor(R.color.my_account_circle_default_rim);
                endColor = getResources().getColor(R.color.my_account_circle_default_rim);
                defaultColor = getResources().getColor(R.color.my_account_circle_default_rim);
                break;
            }

            TypedArray typedArray = null;
            try {
                typedArray = context.obtainStyledAttributes(attrs, R.styleable.AccountProgressCircle);
                percent = typedArray.getFloat(R.styleable.AccountProgressCircle_mpc_percent, defaultPercent);
                strokeWidth = (int) typedArray.getDimension(R.styleable.AccountProgressCircle_mpc_stroke_width, strokeWdithDefaultValue);
                startColor = typedArray.getColor(R.styleable.AccountProgressCircle_mpc_start_color, getResources().getColor(R.color.my_account_circle_default_rim));
                endColor = typedArray.getColor(R.styleable.AccountProgressCircle_mpc_end_color, getResources().getColor(R.color.my_account_circle_default_rim));
                defaultColor = typedArray.getColor(R.styleable.AccountProgressCircle_mpc_default_color, getResources().getColor(R.color.my_account_circle_default_rim));
            } finally {
                if (typedArray != null) {
                    typedArray.recycle();
                }
            }


        } while (false);

        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStrokeWidth(strokeWidth);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeJoin(Paint.Join.ROUND);
        paint.setStrokeCap(Paint.Cap.ROUND);

        startPaint = new Paint();
        startPaint.setColor(startColor);
        startPaint.setAntiAlias(true);
        startPaint.setStyle(Paint.Style.FILL);


        endPaint = new Paint();
        endPaint.setAntiAlias(true);
        endPaint.setStyle(Paint.Style.FILL);

        refreshDelta();



        customColors = new int[]{startColor, percentEndColor, defaultColor, defaultColor};
        fullColors = new int[]{startColor, endColor};
        emptyColors = new int[]{defaultColor, defaultColor};

        customPositions = new float[4];
        customPositions[0] = 0;
        customPositions[3] = 1;

        extremePositions = new float[]{0, 1};
    }

    private void refreshDelta() {
        int endR = (endColor & 0xFF0000) >> 16;
        int endG = (endColor & 0xFF00) >> 8;
        int endB = (endColor & 0xFF);

        this.startR = (startColor & 0xFF0000) >> 16;
        this.startG = (startColor & 0xFF00) >> 8;
        this.startB = (startColor & 0xFF);

        deltaR = endR - startR;
        deltaG = endG - startG;
        deltaB = endB - startB;
    }

    /**
     * @param percent FloatRange(from = 0.0, to = 1.0)
     */
    public void setPercent(final float percent) {
        this.percent = percent;

        invalidate();
    }

    public float getPercent() {
        return this.percent;
    }

    /**
     * @param color ColorInt
     */
    public void setStartColor(final int color) {
        if (this.startColor != color) {
            this.startColor = color;
            // deltaå˜åŒ–
            refreshDelta();

            // æ¸å˜å‰éƒ¨åˆ†
            customColors[0] = color;
            // å‰åŠåœ†
            startPaint.setColor(color);
            // å…¨æ»¡æ—¶ æ¸å˜èµ·ç‚¹
            fullColors[0] = color;

            invalidate();
        }
    }

    public int getStartColor() {
        return this.startColor;
    }

    /**
     * @param color ColorInt
     */
    public void setEndColor(final int color) {
        if (this.endColor != color) {
            this.endColor = color;
            // deltaå˜åŒ–
            refreshDelta();

            // æ¸å˜åŽéƒ¨åˆ† åŠ¨æ€è®¡ç®—#draw
            // åŽåŠåœ† éœ€è¦åŠ¨æ€è®¡ç®—#drawï¼Œåœ¨æŸäº›æƒ…å†µä¸‹æ²¡æœ‰

            // å…¨æ»¡æ—¶ æ¸å˜ç»“æŸ
            fullColors[1] = color;


            invalidate();
        }
    }

    public int getEndColor() {
        return this.endColor;
    }

    /**
     * @param color ColorInt
     */
    public void setDefaultColor(final int color) {
        if (this.defaultColor != color) {
            this.defaultColor = color;

            // æ¸å˜åŽåŠéƒ¨åˆ†
            customColors[2] = color;
            customColors[3] = color;

            // percent = 0
            emptyColors[0] = color;
            emptyColors[1] = color;

            invalidate();
        }
    }

    public int getDefaultColor() {
        return this.defaultColor;
    }

    /**
     * @param width px
     */
    public void setStrokeWidth(final int width) {
        if (this.strokeWidth != width) {
            this.strokeWidth = width;
            // ç”»æè¾¹çš„æè¾¹å˜åŒ–
            paint.setStrokeWidth(width);

            // ä¼šå½±å“measure
            requestLayout();
        }
    }

    public int getStrokeWidth() {
        return this.strokeWidth;
    }

    private int deltaR, deltaB, deltaG;
    private int startR, startB, startG;

    private void calculatePercentEndColor(final float percent) {
        percentEndColor = ((int) (deltaR * percent + startR) << 16) +
                ((int) (deltaG * percent + startG) << 8) +
                ((int) (deltaB * percent + startB)) + 0xFF000000;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        this.rectF.left = getMeasuredWidth() / 2 - strokeWidth / 2;
        this.rectF.top = 0;
        this.rectF.right = getMeasuredWidth() / 2 + strokeWidth / 2;
        this.rectF.bottom = strokeWidth;
    }

    private Paint startPaint;
    private Paint endPaint;


    private final RectF rectF = new RectF();

    private int[] customColors;
    private int[] fullColors;
    private int[] emptyColors;
    private float[] customPositions;
    private float[] extremePositions;


    // ç›®å‰ç”±äºŽSweepGradientèµ‹å€¼åªåœ¨æž„é€ å‡½æ•°ï¼Œæ— æ³•pre allocate & reuse instead
    @SuppressLint("DrawAllocation")
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        final int restore = canvas.save();

        final int cx = getMeasuredWidth() / 2;
        final int cy = getMeasuredHeight() / 2;
        final int radius = getMeasuredWidth() / 2 - strokeWidth / 2;

        float drawPercent = percent;
        if (drawPercent > 0.97 && drawPercent < 1) {
            // è®¾è®¡å¸ˆè¯´è¿™æ ·æ¯”è¾ƒå¥½
            drawPercent = 0.97f;
        }

        // ç”»æ¸å˜åœ†
        canvas.save();
        canvas.rotate(-90, cx, cy);
        int[] colors;
        float[] positions;
        if (drawPercent < 1 && drawPercent > 0) {
            calculatePercentEndColor(drawPercent);
            customColors[1] = percentEndColor;
            colors = customColors;
            customPositions[1] = drawPercent;
            customPositions[2] = drawPercent;
            positions = customPositions;
        } else if (drawPercent == 1) {
            colors = fullColors;
            positions = extremePositions;
        } else {
            // <= 0 || > 1?
            colors = emptyColors;
            positions = extremePositions;
        }
        final SweepGradient sweepGradient = new SweepGradient(getMeasuredWidth() / 2, getMeasuredHeight() / 2, colors, positions);
        paint.setShader(sweepGradient);
        canvas.drawCircle(cx, cy, radius, paint);
        canvas.restore();

        if (drawPercent > 0) {

            // ç»˜åˆ¶ç»“æŸçš„åŠåœ†
            if (drawPercent < 1) {
                canvas.save();
                endPaint.setColor(percentEndColor);
                canvas.rotate((int) Math.floor(360.0f * drawPercent) - 1, cx, cy);
                canvas.drawArc(rectF, -90f, 180f, true, endPaint);
                canvas.restore();
            }


            canvas.save();
            // ç»˜åˆ¶å¼€å§‹çš„åŠåœ†
            canvas.drawArc(rectF, 90f, 180f, true, startPaint);
            canvas.restore();

        }


        canvas.restoreToCount(restore);

    }
}

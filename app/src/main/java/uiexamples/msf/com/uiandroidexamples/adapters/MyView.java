package uiexamples.msf.com.uiandroidexamples.adapters;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import java.util.Random;

import uiexamples.msf.com.uiandroidexamples.R;

/**
 * Created by sathishsr on 26/11/15.
 */
public class MyView extends View {
    Context c;
    private Bitmap mBitmap;
    private Canvas mCanvas;
    private Path mPath;
    private Paint mBitmapPaint;
    private Paint mpaint, paint2;
    private RectF rectF;
    private ImageView ivBackground;

    public MyView(Context context) {
        this(context, null);
//        c = context;
//        mpaint = new Paint();
//        mpaint.setColor(Color.RED);
//        mpaint.setStyle(Paint.Style.FILL);
//        paint2 = new Paint();
//        paint2.setColor(Color.GREEN);
//        paint2.setStrokeWidth(10);
//        mBitmapPaint = new Paint();
//        mBitmapPaint.setColor(Color.RED);
    }

    /**
     * @param context
     * @param attrs
     */
    public MyView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
//
//        c = context;
//        mpaint = new Paint();
//        mpaint.setColor(Color.RED);
//        mpaint.setStyle(Paint.Style.FILL);
//        paint2 = new Paint();
//        paint2.setColor(Color.GREEN);
//        paint2.setStrokeWidth(10);
//        mBitmapPaint = new Paint();
//        mBitmapPaint.setColor(Color.RED);
    }

    /**
     * @param context
     * @param attrs
     * @param defStyle
     */
    public MyView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
//        c = context;
//        mpaint = new Paint();
//        mpaint.setColor(Color.RED);
//        mpaint.setStyle(Paint.Style.FILL);
//        paint2 = new Paint();
//        paint2.setColor(Color.GREEN);
//        paint2.setStrokeWidth(10);
//        mBitmapPaint = new Paint();
//        mBitmapPaint.setColor(Color.RED);
    }

    public MyView(Activity context, ImageView ivBackgr) {
        super(context);
//        c = context;
//        ivBackground = ivBackgr;
//        mpaint = new Paint();
//        mpaint.setColor(Color.RED);
//        mpaint.setStyle(Paint.Style.FILL);
//        paint2 = new Paint();
//        paint2.setColor(Color.GREEN);
//        paint2.setStrokeWidth(10);
//        mBitmapPaint = new Paint();
//        mBitmapPaint.setColor(Color.RED);
        // TODO Auto-generated constructor stub
    }

    public MyView(Activity context) {
        super(context);
//        c = context;
//        mpaint = new Paint();
//        mpaint.setColor(Color.RED);
//        mpaint.setStyle(Paint.Style.FILL);
//        paint2 = new Paint();
//        paint2.setColor(Color.GREEN);
//        paint2.setStrokeWidth(10);
//        mBitmapPaint = new Paint();
//        mBitmapPaint.setColor(Color.RED);
        // TODO Auto-generated constructor stub
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        mCanvas = new Canvas(mBitmap);
    }


    @Override
    public void onDraw(Canvas canvas) {

//        canvas.drawColor(Color.CYAN);
//        Paint p = new Paint();
        // smooths
//        p.setAntiAlias(true);
//        p.setColor(Color.RED);
//        p.setStyle(Paint.Style.STROKE);
//        p.setStrokeWidth(4.5f);
//        // opacity
        //p.setAlpha(0x80); //
//        canvas.drawCircle(50, 50, 30, p);


        Paint paint = new Paint();
        float size = Math.min(getWidth(),getHeight());
        paint.setStrokeWidth(size/4);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeJoin(Paint.Join.ROUND);
        paint.setStrokeMiter(10);
        final RectF oval = new RectF(0, 0, getWidth(), getHeight());
        oval.inset(size/8,size/8);

        paint.setColor(getResources().getColor(R.color.grey));
        Path redPath = new Path();
        redPath.arcTo(oval, 0, 120, true);
        canvas.drawPath(redPath, paint);

//        paint.setColor(Color.GREEN);
        Path greenPath = new Path();
        greenPath.arcTo(oval, 120, 120, true);
        canvas.drawPath(greenPath, paint);

//        paint.setColor(Color.BLUE);
        Path bluePath = new Path();
        bluePath.arcTo(oval, 240, 120, true);
        canvas.drawPath(bluePath, paint);

        paint.setStrokeWidth(3);
        paint.setColor(Color.DKGRAY);
        canvas.save();
        for(int i=0;i<360;i+=32){
            canvas.rotate(32,size/2,size/2);
            canvas.drawLine(size/2,size/2,size,size/2,paint);
        }
        canvas.restore();

        final RectF ovalOuter = new RectF(0, 0, getWidth(), getHeight());
//        ovalOuter.inset(1,1);
        canvas.drawOval(ovalOuter,paint);




//        final RectF ovalInner = new RectF(size/4, size/4, size*3/4,size*3/4);
//        canvas.drawOval(ovalInner,paint);
    }
    public int getColor() {
        // generate the random integers for r, g and b value
        Random rand = new Random();
        int r = rand.nextInt(255);
        int g = rand.nextInt(255);
        int b = rand.nextInt(255);
        int randomColor = Color.rgb(r, g, b);
        return randomColor;
    }

    private void setUpDrawingArea() {

        // First get the screen dimensions
        Point size = new Point();

        DisplayMetrics metrics = new DisplayMetrics();
        WindowManager windowManager = (WindowManager) c
                .getSystemService(Context.WINDOW_SERVICE);
        windowManager.getDefaultDisplay().getMetrics(metrics);
//        display.getSize(size);
        int width = metrics.widthPixels/2;
        int height = metrics.heightPixels/2;


        // Set up the padding
        int paddingLeft = (int) c.getResources().getDimension(R.dimen.padding_large);
        int paddingTop = (int) c.getResources().getDimension(R.dimen.padding_large);
        int paddingRight = (int) c.getResources().getDimension(R.dimen.padding_large);
        int paddingBottom = (int) c.getResources().getDimension(R.dimen.padding_large);

        // Then get the left, top, right and bottom Xs and Ys for the rectangle we're going to draw in
        float left = 0 + paddingLeft;
        float top = 0 + paddingTop;
        float right = width - paddingRight;
        float bottom = width - paddingBottom;
//
//        getLeft()+(getRight()-getLeft())/3,
//                getTop()+(getBottom()-getTop())/3,
//                getRight()-(getRight()-getLeft())/3,
//                getBottom()-(getBottom()-getTop())/3
        rectF = new RectF(left, top, right, bottom);
    }
}
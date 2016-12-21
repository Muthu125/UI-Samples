package uiexamples.msf.com.uiandroidexamples.adapters;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import uiexamples.msf.com.uiandroidexamples.R;

public class SplashSurfaceView extends SurfaceView {

    private SurfaceHolder surfaceHolder;
    private SplashRendererThread splashRendererThread;

    private Activity activity;
    private int xconst, yconst;
    private boolean initialDrawingIsPerformed;
    private Bitmap cachedBitmap;
    private Context ctx;


    private ObjectCoordinates splashCordinates;
    private List<Poly> polyList;
    private int xmax;
    private LowPolyInflationListener lowPolyInflationListener;

    public int getXmax() {
        return xmax;
    }

    public int getYmax() {
        return ymax;
    }

    private int ymax;


    public SplashSurfaceView(Context context) {
        super(context);
        this.ctx=context;
        this.splashCordinates = getSplashCordinates();
        polyList = splashCordinates.getPoly();
        init();
    }

    public SplashSurfaceView(Context context,
                             AttributeSet attrs) {
        super(context, attrs);
        this.ctx=context;
        init();
    }

    public SplashSurfaceView(Context context,
                             AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init() {
        DisplayMetrics displaymetrics = new DisplayMetrics();
        ((Activity)ctx).getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        xmax = displaymetrics.widthPixels;
        ymax = displaymetrics.heightPixels;

        Log.d("xmax(width) ", xmax + "");
        Log.d("ymax(height) ", ymax + "");

        xconst = 1;
        yconst = 1;

        splashRendererThread = new SplashRendererThread(this);

        surfaceHolder = getHolder();


        surfaceHolder.addCallback(new SurfaceHolder.Callback() {

            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                splashRendererThread.setRunning(true);
                if(!splashRendererThread.isAlive()) {
                    splashRendererThread.start();
                }
            }

            @Override
            public void surfaceChanged(SurfaceHolder holder,
                                       int format, int width, int height) {
            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
                boolean retry = true;
                splashRendererThread.setRunning(false);
                while (retry) {
                    try {
                        splashRendererThread.join();
                        retry = false;
                    } catch (InterruptedException e) {
                    }
                }
            }
        });
    }

    protected void drawSplashPolygons(Canvas canvas, Paint paint, Path path, int colors) {

      /*  Rect retRect =canvas.getClipBounds();
        MSFLog.msg("LOOK>" + retRect.left + "," + retRect.top + "," + retRect.right + "," + retRect.bottom);*/

//

    }


    public void lowPolyGenerationComplete(){
        if(this.lowPolyInflationListener!= null) {
            this.lowPolyInflationListener.lowPolyInflationComplete();
        }else{
            //MSFLog.msg("SPLASHSCREEN"+" Your component has not implemented/set LowPolyInflationListener");
        }
    }

    public void drawPoly(Canvas canvas, Paint paint, Path path, int color, List<Integer> x, List<Integer> y) {
        //Log.i("TAG", "Drawing...");
        if(!initialDrawingIsPerformed){

            initialDrawingIsPerformed= true;
        }
        path.reset();
        paint.setColor(color);
        path.moveTo(x.get(0), y.get(0));
        for(int i=1;i<x.size();i++){
            path.lineTo(x.get(i), y.get(i));
            //MSFLog.msg("LOOK" +x.get(i)+","+ y.get(i));
        }
        canvas.drawPath(path, paint);

    }

    public void addBG(Canvas canvas) {
        /*canvas.drawColor(Color.WHITE, PorterDuff.Mode.SCREEN );*/
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.WHITE);
        canvas.drawRect(0, 0, this.getWidth(), this.getHeight(), paint);
    }

        public void addCreditsText(Canvas canvas, Paint paint){
        paint.reset();
        paint.setColor(Color.parseColor("#FFFFFF"));
        int scaledSize = getResources().getDimensionPixelSize(R.dimen.creditsFontSize);
        paint.setTextSize(scaledSize);
        Rect textBoundRect= new Rect();
        String text= "Powered by Market Simplified";
        paint.getTextBounds(text, 0, text.length(), textBoundRect);
        canvas.drawText(text, (canvas.getWidth()/2)-(textBoundRect.width()/2), (canvas.getHeight()-20)-(textBoundRect.height()), paint);
    }

    public void stop(){
        splashRendererThread.setRunning(false);
    }


     //  for gradient
    public interface LowPolyInflationListener {
        public void lowPolyInflationComplete();

    }

    public void setLowPolyInflationListener(LowPolyInflationListener lowPolyInflationListener){
        this.lowPolyInflationListener = lowPolyInflationListener;
    }

    public Bitmap getCacheBitmap(){
        return this.getDrawingCache();
    }



    public ObjectCoordinates getSplashCordinates() {
        ObjectCoordinates splashCordinates = new ObjectCoordinates();
        if(splashCordinates.getPoly()!=null && splashCordinates.getPoly().size()>0) {
            return splashCordinates;
        }
        try {
            JSONObject obj = new JSONObject(loadJSONFromAsset());
            JSONArray m_jArry = obj.getJSONArray("Poly");
            List<Poly> polyList = new ArrayList<>();


            for (int i = 0; i < m_jArry.length(); i++) {
                JSONObject polyObject = m_jArry.getJSONObject(i);

                JSONArray pointsArray = polyObject.getJSONArray("points");
                List<Point> pointList = new ArrayList<>();


                for (int ik = 0; ik < pointsArray.length(); ik++) {

                    Point point = new Point();
                    point.setX(pointsArray.getJSONObject(ik).getInt("X"));
                    point.setY(pointsArray.getJSONObject(ik).getInt("Y"));
                    pointList.add(point);

                }
                String colorObject = polyObject.getString("Colors");


                Poly poly = new Poly();
                poly.setPoints(pointList);
                poly.setColors(colorObject);

                polyList.add(poly);


            }
            splashCordinates.setPoly(polyList);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return splashCordinates;
    }

    private String loadJSONFromAsset() {
        String json = null;
        try {
            InputStream is = ctx.getAssets().open("splash/splash.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

}
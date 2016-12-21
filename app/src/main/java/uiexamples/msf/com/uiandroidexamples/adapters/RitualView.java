package uiexamples.msf.com.uiandroidexamples.adapters;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.os.Handler;
import android.view.View;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by MSF201Vijay on 20-09-2016.
 */
public class RitualView extends View implements Runnable {


    private Context ctx;
    private List<Poly> polyList;
    private ObjectCoordinates splashCordinates;
    private int polygonNum = 0;
    Paint paint;
    Path path;
    private int xmax;
    private int ymax;
    int color = 0;
     List<Integer> x = new ArrayList<Integer>();
     List<Integer> y = new ArrayList<Integer>();
    public int getXmax() {
        return xmax;
    }

    public int getYmax() {
        return ymax;
    }


    Handler handler = new Handler() {
        public void handleMessage(android.os.Message msg) {

            Rect dirtyRect=null;



   /*if (getPolygonNum()==-1){
    isAddBG = true;
   }
   else*/ if (getPolygonNum() < polyList.size()) {
                Poly poly = polyList.get(getPolygonNum());
                int left = poly.getPoints().get(0).getX(), top = poly.getPoints().get(0).getY(), right = poly.getPoints().get(0).getX(), bottom = poly.getPoints().get(0).getY();

                for (int i = 0; i < poly.getPoints().size(); i++) {
                    x.add(poly.getPoints().get(i).getX() == 0 ? 0 : (getXmax() * poly.getPoints().get(i).getX()) / 480);
                    y.add(poly.getPoints().get(i).getY() == 0 ? 0 : (getYmax() * poly.getPoints().get(i).getY()) / 800);

                    left = Math.min(poly.getPoints().get(i).getX(), left);
                    right = Math.max(poly.getPoints().get(i).getX(), right);
                    top = Math.min(poly.getPoints().get(i).getY(), top);
                    bottom = Math.max(poly.getPoints().get(i).getY(), bottom);

                }

                left = left == 0 ? 0 : (getXmax() * left) / 480;
                top = top == 0 ? 0 : (getYmax() * top) / 800;

                right = right == 0 ? 0 : (getXmax() * right) / 480;
                bottom = bottom == 0 ? 0 : (getYmax() * bottom) / 800;

                dirtyRect = new Rect(left, top, right, bottom);
                //MSFLog.msg("LOOK" + left + "," + top + "," + right + "," + bottom);
                color = Color.parseColor(poly.getColors());

            }









                invalidate();
            System.out.println("redraw");
        }


    };

    public RitualView(Context context) {
        super(context);
        ctx = context;
        this.splashCordinates = getSplashCordinates();
        polyList = this.splashCordinates.getPoly();
        paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        path = new Path();

        new Thread(this).start();
    }

    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawPoly(canvas, paint, path,color , x, y);
    }
    public void drawPoly(Canvas canvas, Paint paint, Path path, int color, List<Integer> x, List<Integer> y) {
        path.reset();
        paint.setColor(color);
        path.moveTo(x.get(0), y.get(0));
        for(int i=1;i<x.size();i++){
            path.lineTo(x.get(i), y.get(i));
            //MSFLog.msg("LOOK" +x.get(i)+","+ y.get(i));
        }
        canvas.drawPath(path, paint);

    }

    public void run() {
        while (true) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            handler.sendEmptyMessage(0);
        }
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
        public int getPolygonNum() {
            return polygonNum;
        }
}
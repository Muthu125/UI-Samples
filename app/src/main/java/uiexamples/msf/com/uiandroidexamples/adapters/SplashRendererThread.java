package uiexamples.msf.com.uiandroidexamples.adapters;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.os.SystemClock;

import java.util.ArrayList;
import java.util.List;

public class SplashRendererThread extends Thread {
 
 SplashSurfaceView myView;
 private boolean running = false;
 private final int PETALS_INTERVAL = 50;

 private int polygonNum = 0;
 private long prevTime = SystemClock.uptimeMillis();
 private List<Poly> polyList;

 Paint paint;
 Path path;
 private boolean isAddCreditsText, isAddBG;

 public SplashRendererThread(SplashSurfaceView view) {

  myView = view;
  polyList = myView.getSplashCordinates().getPoly();
  paint = new Paint();
  paint.setStyle(Paint.Style.FILL);
  path = new Path();
 }
 
 public void setRunning(boolean run) {
        running = run;    
 }

 @Override
 public void run() {
  while(running){
   long currentTime = SystemClock.uptimeMillis();

   Rect dirtyRect=null;

   int color = 0;

   final List<Integer> x = new ArrayList<Integer>();
   final List<Integer> y = new ArrayList<Integer>();
   /*if (getPolygonNum()==-1){
    isAddBG = true;
   }
   else*/ if (getPolygonNum() < polyList.size()) {
    Poly poly = polyList.get(getPolygonNum());
    int left = poly.getPoints().get(0).getX(), top = poly.getPoints().get(0).getY(), right = poly.getPoints().get(0).getX(), bottom = poly.getPoints().get(0).getY();

    for (int i = 0; i < poly.getPoints().size(); i++) {
     x.add(poly.getPoints().get(i).getX() == 0 ? 0 : (myView.getXmax() * poly.getPoints().get(i).getX()) / 480);
     y.add(poly.getPoints().get(i).getY() == 0 ? 0 : (myView.getYmax() * poly.getPoints().get(i).getY()) / 800);

     left = Math.min(poly.getPoints().get(i).getX(), left);
     right = Math.max(poly.getPoints().get(i).getX(), right);
     top = Math.min(poly.getPoints().get(i).getY(), top);
     bottom = Math.max(poly.getPoints().get(i).getY(), bottom);

    }

    left = left == 0 ? 0 : (myView.getXmax() * left) / 480;
    top = top == 0 ? 0 : (myView.getYmax() * top) / 800;

    right = right == 0 ? 0 : (myView.getXmax() * right) / 480;
    bottom = bottom == 0 ? 0 : (myView.getYmax() * bottom) / 800;

    dirtyRect = new Rect(left, top, right, bottom);
    //MSFLog.msg("LOOK" + left + "," + top + "," + right + "," + bottom);
    color = Color.parseColor(poly.getColors());
   } else if(getPolygonNum() == polyList.size()){
    isAddCreditsText= true;
   }
   else{
    setRunning(false);
    myView.lowPolyGenerationComplete();
    return;
   }

   Canvas canvas = myView.getHolder().lockCanvas(dirtyRect);


   if (canvas != null) {
    synchronized (myView.getHolder()) {
     /*if(isAddBG){
      myView.addBG(canvas);
     }else*/ if(isAddCreditsText){
      myView.addCreditsText(canvas,paint);
      isAddCreditsText = false;

     }else{
     // canvas.drawColor(Color.RED, PorterDuff.Mode.SCREEN );
      myView.drawPoly(canvas, paint, path,color , x, y);
     }
    }

    myView.getHolder().unlockCanvasAndPost(canvas);
   }

   if ((currentTime - prevTime) > (/*isAddBG?getPETALS_INTERVAL()*50:*/getPETALS_INTERVAL())) {
    prevTime = currentTime;
    polygonNum++;
    /*if(isAddBG){
     isAddBG = false;
    }*/
   }

   try {
    sleep(0,1);
   } catch (InterruptedException e) {
    e.printStackTrace();
   }

  }
 }

 private void updateLowPoly(Canvas canvas) {


/*  } else {
   setRunning(false);
   myView.lowPolyGenerationComplete();
  }*/
 }

 public int getPETALS_INTERVAL() {
  return PETALS_INTERVAL;
 }

 public int getPolygonNum() {
  return polygonNum;
 }



}
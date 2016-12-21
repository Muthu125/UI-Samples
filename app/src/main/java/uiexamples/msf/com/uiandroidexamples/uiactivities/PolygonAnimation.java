package uiexamples.msf.com.uiandroidexamples.uiactivities;

import android.app.Activity;
import android.graphics.PixelFormat;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;

import uiexamples.msf.com.uiandroidexamples.R;
import uiexamples.msf.com.uiandroidexamples.adapters.SplashSurfaceView;

/**
 * Created by muthuv on 12/21/2016.
 */

public class PolygonAnimation extends Activity implements SplashSurfaceView.LowPolyInflationListener{

    private SplashSurfaceView lowPolygraph;
    private boolean isisLowPolyAnimationCompleted;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.animation_slpash);
        final SplashSurfaceView lowPolygraph = generateLowPolygraph();

    }

    @NonNull
    private SplashSurfaceView generateLowPolygraph() {
        lowPolygraph = (SplashSurfaceView) findViewById(R.id.surfaceView);
        lowPolygraph.setLowPolyInflationListener(this);
        lowPolygraph.setZOrderOnTop(true);
        lowPolygraph.getHolder().setFormat(PixelFormat.TRANSPARENT);
        return lowPolygraph;
    }

    @Override
    public void lowPolyInflationComplete() {
        isisLowPolyAnimationCompleted = true;
    }
}

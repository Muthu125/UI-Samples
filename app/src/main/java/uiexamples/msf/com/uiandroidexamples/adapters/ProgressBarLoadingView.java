package uiexamples.msf.com.uiandroidexamples.adapters;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.drawable.AnimatedVectorDrawable;
import android.os.Build;
import android.os.Handler;
import android.support.graphics.drawable.VectorDrawableCompat;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;
import android.util.Log;

import uiexamples.msf.com.uiandroidexamples.R;

/**
 * Created by muthuv on 12/20/2016.
 */

public class ProgressBarLoadingView extends AppCompatImageView {

    private Context context;
    boolean isBull;
    private AnimatedVectorDrawable animaVectorDrawableBear;
    private AnimatedVectorDrawable animatVectorDrawableBull;
    private boolean isShowingBull;

    private Handler mHandler;
    private Runnable mStatusChecker;


    static{
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }

    public ProgressBarLoadingView(Context context) {
        super(context);
        this.context = context;
        init();
    }

    public ProgressBarLoadingView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        init();
    }

    public ProgressBarLoadingView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        init();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public void init() {
        try{
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                isShowingBull = false;
                animaVectorDrawableBear =
                        (AnimatedVectorDrawable) getContext().getDrawable(R.drawable.avd_loader_bear_to_bull);
                animatVectorDrawableBull =
                        (AnimatedVectorDrawable) getContext().getDrawable(R.drawable.avd_loader_bull_to_bear);
                setImageDrawable(animaVectorDrawableBear);

                mHandler = new Handler();
                morph();
            }else{
                morph();
            }
        }catch(Exception e){
            //Log.i("LoaderView "+e.toString());
        }

    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public void morph() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            startRepeatingTask();

        } else {
            final Handler handler = new Handler();
            final VectorDrawableCompat vectorBearComplete = (VectorDrawableCompat) getResources().getDrawable(R.drawable.vector_bear_complete);
            final VectorDrawableCompat vectorBullComplete = (VectorDrawableCompat) getResources().getDrawable(R.drawable.vector_bull_complete);

            Runnable runnable = new Runnable() {
                public void run() {
                    setImageDrawable(isBull ? vectorBullComplete : vectorBearComplete);
                    isBull = !isBull;
                    handler.postDelayed(this, 1000);  //for interval...
                }
            };
            handler.postDelayed(runnable, 500); //for initial delay..
        }
    }



    void startRepeatingTask() {
        mStatusChecker = new Runnable() {
            @TargetApi(Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void run() {

                final AnimatedVectorDrawable drawable
                        = isShowingBull ? animatVectorDrawableBull : animaVectorDrawableBear;
                setImageDrawable(drawable);
                drawable.start();
                isShowingBull = !isShowingBull;
                mHandler.postDelayed(mStatusChecker, 1000);

            }
        };
        mStatusChecker.run();
    }

    void stopRepeatingTask() {
        mHandler.removeCallbacks(mStatusChecker);
    }


}



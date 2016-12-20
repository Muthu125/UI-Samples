package uiexamples.msf.com.uiandroidexamples.uiactivities;

import android.app.Activity;
import android.os.Bundle;

import uiexamples.msf.com.uiandroidexamples.R;

/**
 * Created by muthuv on 12/20/2016.
 */

public class SlidingDrawer extends Activity implements android.widget.SlidingDrawer.OnDrawerOpenListener, android.widget.SlidingDrawer.OnDrawerCloseListener, android.widget.SlidingDrawer.OnDrawerScrollListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sliding_drawer_layout);
    }

    @Override
    public void onDrawerClosed() {

    }

    @Override
    public void onDrawerOpened() {

    }

    @Override
    public void onScrollStarted() {

    }

    @Override
    public void onScrollEnded() {

    }
}

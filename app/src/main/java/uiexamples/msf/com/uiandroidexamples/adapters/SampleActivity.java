package uiexamples.msf.com.uiandroidexamples.adapters;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;

import uiexamples.msf.com.uiandroidexamples.R;


public class SampleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);


        ViewGroup layout = (ViewGroup) findViewById(R.id.content_main);
        RitualView ritualView = new RitualView(SampleActivity.this);
        ritualView.setLayoutParams(new ActionBar.LayoutParams(ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT));
        layout.addView(ritualView);
    }
}

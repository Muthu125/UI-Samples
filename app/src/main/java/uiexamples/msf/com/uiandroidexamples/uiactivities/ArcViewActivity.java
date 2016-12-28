package uiexamples.msf.com.uiandroidexamples.uiactivities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import uiexamples.msf.com.uiandroidexamples.R;
import uiexamples.msf.com.uiandroidexamples.adapters.ArcLayoutController;

/**
 * Created by Thiyagesh S on 28-12-2016.
 */

public class ArcViewActivity extends AppCompatActivity  {

    ArcLayoutController arcLayoutController;
    Button button;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_arc_view);
        arcLayoutController = new ArcLayoutController(this);
        button = (Button) findViewById(R.id.arc_view_btn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                arcLayoutController.setUpViews(view, "Five", "QUOTE", "CHART", "", "SETALERT", "","Title", 3);
            }
        });

    }
}

package uiexamples.msf.com.uiandroidexamples.uiactivities;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.res.TypedArray;
import android.graphics.RectF;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.szugyi.circlemenu.view.ChartWheelController;
import com.szugyi.circlemenu.view.ChartWheelController.OnCenterClickListener;
import com.szugyi.circlemenu.view.ChartWheelController.OnItemClickListener;
import com.szugyi.circlemenu.view.ChartWheelController.OnItemSelectedListener;
import com.szugyi.circlemenu.view.ChartWheelController.OnRotationFinishedListener;
import com.szugyi.circlemenu.view.CircleImageView;

import java.text.DecimalFormat;

import ru.biovamp.widget.ChartCircle;
import uiexamples.msf.com.uiandroidexamples.R;
import uiexamples.msf.com.uiandroidexamples.adapters.PriceWheelController;

public class CircleSampleActivity extends Activity implements OnItemSelectedListener,
        OnItemClickListener, OnRotationFinishedListener, OnCenterClickListener, DialogInterface.OnDismissListener {
    public static final String ARG_LAYOUT = "layout";
    RectF rectF;
    private TextView selectedTextView;
    private ImageView ivBackground;
    private TextView tvPlus;
    private TextView tvMinus;
    private double progressValue;
    private double currentPrice;
    private int startProgress;
    private int stopProgress;
    private ImageView btnCenter;
    PriceWheelController dialog;
    boolean isClicked;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Set content view by passed extra
        Bundle extras = getIntent().getExtras();
        int layoutId = extras.getInt(ARG_LAYOUT);

        if (layoutId == R.layout.sample) {
            setContentView(R.layout.sample);


            final EditText edit = (EditText) findViewById(android.R.id.edit);

            edit.setText("112.250");


            Button btn2 = (Button) findViewById(android.R.id.button2);

            int[] attrs = new int[]{R.attr.selectableItemBackground};
            TypedArray typedArray = this.obtainStyledAttributes(attrs);
            int backgroundResource = typedArray.getResourceId(0, 0);
            btn2.setBackgroundResource(backgroundResource);

            btn2.setOnTouchListener(new View.OnTouchListener() {
                @TargetApi(Build.VERSION_CODES.HONEYCOMB)
                @Override
                public boolean onTouch(View v, MotionEvent event) {

                    dialog = new PriceWheelController();
                    dialog.setDefaultPrecision(3);

                    if (edit.getText().toString().length() > 0) {
                        dialog.setCurrentPrice((Double.parseDouble((edit.getText().toString()))));

                        dialog.show(getFragmentManager(), "");

                        dialog.setOnUpdateListener(new PriceWheelController.OnUpdateListener() {
                            @Override
                            public void onUpdateListener(String arg) {
                                edit.setText(arg);
                            }
                        });
                    }


                    return false;
                }
            });


        } else {
            setContentView(R.layout.sample_with_background);

            final ChartCircle circleLayout = (ru.biovamp.widget.ChartCircle) findViewById(R.id.bg_circle);
            final ChartWheelController circleMenu = (ChartWheelController) findViewById(R.id.main_circle_layout);
            btnCenter = (ImageView) findViewById(R.id.centerImage);

            View view = (View) findViewById(R.id.view);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    isClicked = false;
                    circleLayout.removeAllViews();
                    circleLayout.setVisibility(View.GONE);
                    circleMenu.setVisibility(View.GONE);
                }
            });


            btnCenter.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!isClicked) {

                        for (int i = 0; i < 11; i++) {
                            View view = new View(CircleSampleActivity.this);
                            if (i == 6)
                                view.setBackgroundColor(ContextCompat.getColor(CircleSampleActivity.this,android.R.color.holo_green_dark));
                            else
                                view.setBackgroundResource(R.drawable.back);
//                view.setBackgroundColor(getResources().getColor(((i == 5) ? android.R.color.holo_green_dark : R.color.grey)));


                            circleLayout.addView(view);
                        }

                        circleLayout.setVisibility(View.VISIBLE);
                        circleMenu.setVisibility(View.VISIBLE);
                        isClicked = true;
                    } else {
                        isClicked = false;
                        circleLayout.removeAllViews();
                        circleLayout.setVisibility(View.GONE);
                        circleMenu.setVisibility(View.GONE);
                    }
                }
            });


            // Set listeners

            circleMenu.setOnItemSelectedListener(this);
            circleMenu.setOnItemClickListener(this);
            circleMenu.setOnRotationFinishedListener(this);
            circleMenu.setOnCenterClickListener(this);

            selectedTextView = (TextView) findViewById(R.id.main_selected_textView);
            selectedTextView.setText(((CircleImageView) circleMenu
                    .getSelectedItem()).getName());
//        }
            // ATTENTION: This was auto-generated to implement the App Indexingld();
        }
    }

    private String decimalFormatter(double number) {
        DecimalFormat numberFormat = new DecimalFormat("#.00000");
        return numberFormat.format(number);
    }


    public static String decimalFormat(double price) {
        DecimalFormat decim = new DecimalFormat("0.00");
        return decim.format(price);
    }

    @Override
    public void onItemSelected(View view, String name) {
        selectedTextView.setText(name);

        switch (view.getId()) {
            case R.id.main_calendar_image:
                // Handle calendar selection
                break;
            case R.id.main_cloud_image:
                // Handle cloud selection
                break;
            case R.id.main_facebook_image:
                // Handle facebook selection
                break;
//			case R.id.main_key_image:
//				// Handle key selection
//				break;
//			case R.id.main_profile_image:
//				// Handle profile selection
//				break;
            case R.id.main_tap_image:
                // Handle tap selection
                break;
        }
    }

    @Override
    public void onCenterClick() {
        Toast.makeText(getApplicationContext(), R.string.center_click,
                Toast.LENGTH_SHORT).show();
    }


    public double getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(double currentPrice) {
        this.currentPrice = currentPrice;
    }


    @Override
    public void onDismiss(DialogInterface dialog) {

//        this.d
    }


    @Override
    public void onRotationFinished(View view) {

    }

    @Override
    public void onItemClick(View view, String name) {

    }
}

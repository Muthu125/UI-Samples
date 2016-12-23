package uiexamples.msf.com.uiandroidexamples.adapters;

import android.annotation.TargetApi;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import java.text.DecimalFormat;

import uiexamples.msf.com.uiandroidexamples.R;

/**
 * Created by SathishSr
 */
@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class PriceWheelController extends DialogFragment {

    // --Commented out by Inspection (19/2/16 2:30 PM):private EditText mEditText;
    //Will store the current price of the text field
    private double currentPrice;

    private float discrete = 0;
    private float start = 0;
    private float end = 100;
    //Stores the updated price
    private String newPrice = "";
    double updatedValue;
    public PriceWheelController() {
    }

    public double getCurrentPrice() {
        return currentPrice;
    }

    //Stores default precision for the symbol
    private int defaultPrecision;

    public void setCurrentPrice(double currentPrice) {
        this.currentPrice = currentPrice;
    }

    public OnUpdateListener onUpdateListener;

    public void setOnUpdateListener(OnUpdateListener onUpdateListener) {
        this.onUpdateListener = onUpdateListener;
    }

    public int getDefaultPrecision() {

        return defaultPrecision;
    }

    public void setDefaultPrecision(int defaultPrecision) {
        this.defaultPrecision = defaultPrecision;
        switch (defaultPrecision) {
            case 3:
                this.defaultPrecision = 100;
                break;
            case 4:
                this.defaultPrecision = 1000;
                break;
            case 5:
                this.defaultPrecision = 10000;
                break;
        }
    }


    public interface OnUpdateListener {
        void onUpdateListener(String arg);
    }


    private String decimalFormatter(double number) {
        DecimalFormat numberFormat = new DecimalFormat("#.0000");  //100 ->Should be based on Precision
        return numberFormat.format(number);
    }

    @Override
    public void show(FragmentManager manager, String tag) {
        if (manager.findFragmentByTag(tag) == null) {
            super.show(manager, tag);
        }
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
        getNewPrice();
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final Dialog dialog = new Dialog(getActivity());
        Window window = dialog.getWindow();
        window.requestFeature(Window.FEATURE_NO_TITLE);
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        dialog.setContentView(R.layout.seekbar_dailog);
        window.setBackgroundDrawable(
                new ColorDrawable(Color.TRANSPARENT));

        final CircularSeekBar circularSeekBar = (CircularSeekBar) dialog.findViewById(R.id.circularSeekBar1);
        final TextView progressUpdate = (TextView) dialog.findViewById(R.id.main_selected_textView);

        double initialValue = getCurrentPrice();

        circularSeekBar.setProgress(10);

        progressUpdate.setText(String.valueOf(decimalFormatter(initialValue)));

        start = -50;//you need to give starting value of SeekBar
        end = 50;//you need to give end value of SeekBar


        circularSeekBar.setOnSeekBarChangeListener(new CircularSeekBar.OnCircularSeekBarChangeListener() {
            @Override
            public void onStopTrackingTouch(CircularSeekBar seekBar) {
            }

            @Override
            public void onStartTrackingTouch(CircularSeekBar seekBar) {
            }

            @Override
            public void onProgressChanged(CircularSeekBar CircularSeekBar, int progress, boolean fromUser) {
                // To convert it as discrete value
//                if (progress != 10) {
                    float dis = end - start;
                    System.out.println("progess::" + progress);
                    discrete = (start + (((float) progress / 100) * dis)) + 40;
                    float precision = discrete / defaultPrecision;// 100 -> should be based on precision


                    updatedValue = getCurrentPrice() - precision;
                    System.out.println("discrete::"+discrete);
                System.out.println("precision::"+precision);
//                System.out.println("end  if(progress!=10)
                    progressUpdate.setText(String.valueOf(decimalFormatter(updatedValue)));

//}
                    //Pass the updated value to the listener.
                    if (onUpdateListener != null) {
                        onUpdateListener.onUpdateListener(progressUpdate.getText().toString());
                    }

//                }else{
//                    progressUpdate.setText(String.valueOf(decimalFormatter(getCurrentPrice())));
//                }
            }
        });


        setNewPrice(progressUpdate.getText().toString());

        progressUpdate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        return dialog;
    }

    public String getNewPrice() {
        return newPrice;
    }

    public void setNewPrice(String newPrice) {
        this.newPrice = newPrice;
    }

    public static String decimalFormat(double price) {
        DecimalFormat decim = new DecimalFormat("0.00");
        return decim.format(price);
    }
}

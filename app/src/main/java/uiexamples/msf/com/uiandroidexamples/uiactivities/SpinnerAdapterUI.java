package uiexamples.msf.com.uiandroidexamples.uiactivities;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;

import java.util.ArrayList;

import uiexamples.msf.com.uiandroidexamples.R;
import uiexamples.msf.com.uiandroidexamples.adapters.CustomSpinnerAdapter;

/**
 * Created by muthuv on 12/20/2016.
 */

public class SpinnerAdapterUI extends Activity {

    private CustomSpinnerAdapter spinnerAdapter;
    private Spinner spinner, spinner2;
    Typeface typeface;
    private String[] exchangeList = {"Android", "BlackBerry", "iPhone", "Windows"};
    private String[] exchangeList1 = {"NSE", "BSE"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.spinner_adapter_ui);

        typeface = Typeface.createFromAsset(this.getAssets(), "fonts/edel_icon_set_2.ttf");
        spinner = (Spinner) findViewById(R.id.spinner_example);
        spinner2 = (Spinner) findViewById(R.id.spinner_example2);
        spinnerAdapter = new CustomSpinnerAdapter(this, exchangeList, spinner, true, true, typeface);
        spinner.setAdapter(spinnerAdapter);
        //spinner.setOnItemSelectedListener(spinnerListener);
        //spinner.setEnabled(false);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spinnerAdapter = new CustomSpinnerAdapter(this, exchangeList1, spinner2, true, true, typeface);
        spinnerAdapter.setIsUpdateWithColor(true);
        spinner2.setAdapter(spinnerAdapter);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    }
}

package uiexamples.msf.com.uiandroidexamples.adapters;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import uiexamples.msf.com.uiandroidexamples.R;
/**
 * Created by muthuv on 12/20/2016.
 */

public class CustomSpinnerAdapter extends ArrayAdapter<String> {

    private Context ctx;
    private String[] contentArray;
    Spinner mySpinner;
    boolean isHideIcon;
    Typeface typeface;
    boolean isUpdateExchangeColor;//For Trade Exchange Color Code

    public boolean isUpdateWithColor() {
        return isUpdateExchangeColor;
    }

    public void setIsUpdateWithColor(boolean isUpdateWithColor) {
        this.isUpdateExchangeColor = isUpdateWithColor;
    }

    public CustomSpinnerAdapter(Context context, String[] objects, Spinner mySpinner,
                                boolean isHideIcon, boolean isUpdateExchangeColor, Typeface typeface) {
        super(context, R.layout.custom_spinner_item, R.id.spinnerItemText, objects);
        this.ctx = context;
        this.contentArray = objects;
        this.mySpinner = mySpinner;
        this.isHideIcon = isHideIcon;
        this.typeface = typeface;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.custom_spinner_item, null);
        }

        TextView textView = (TextView) convertView.findViewById(R.id.spinnerItemText);
        textView.setText(contentArray[position]);
        double density = ctx.getResources().getDisplayMetrics().density;
        if (density == 1.5) {
            textView.setTextSize(14);
        } else {
            textView.setTextSize(16);
        }

        TextView imageView = (TextView) convertView.findViewById(R.id.spinnerItemImage);
        if(isHideIcon){
            imageView.setVisibility(View.GONE);
            if(isUpdateExchangeColor){
                //Exchange
                //CalligraphyUtils.applyFontToTextView(textView, TypefaceUtils.load(ctx.getAssets(), "fonts/Roboto-Regular.ttf"));
                if (textView.getText().toString().equalsIgnoreCase("NSE")) {
                    textView.setTextColor(ctx.getResources().getColor(R.color.colorAccent));
                } else if (textView.getText().toString().equalsIgnoreCase("BSE")) {
                    textView.setTextColor(ctx.getResources().getColor(R.color.colorAccent));
                }/* else if (textView.getText().toString().equalsIgnoreCase(EMTLabels.NFO_EXCHANGE)) {
                    textView.setTextColor(ctx.getResources().getColor(R.color.nfo_color));
                }else if (textView.getText().toString().equalsIgnoreCase(EMTLabels.CDS_EXCHANGE)) {
                    textView.setTextColor(ctx.getResources().getColor(R.color.cds_color));
                }*/
            }
        }else {
            //Exchange
            if (textView.getText().toString().equalsIgnoreCase("NSE")) {
                textView.setTextColor(ctx.getResources().getColor(R.color.colorAccent));
            } else if (textView.getText().toString().equalsIgnoreCase("BSE")) {
                textView.setTextColor(ctx.getResources().getColor(R.color.colorAccent));
            } /*else if (textView.getText().toString().equalsIgnoreCase(EMTLabels.NFO_EXCHANGE)) {
                textView.setTextColor(ctx.getResources().getColor(R.color.nfo_color));
                imageView.setTextColor(ctx.getResources().getColor(R.color.nfo_color));
            }else if (textView.getText().toString().equalsIgnoreCase(EMTLabels.CDS_EXCHANGE)) {
                textView.setTextColor(ctx.getResources().getColor(R.color.cds_color));
                imageView.setTextColor(ctx.getResources().getColor(R.color.cds_color));
            }*/
            //CalligraphyUtils.applyFontToTextView(textView, TypefaceUtils.load(ctx.getAssets(), "fonts/Roboto-Light.ttf"));
            imageView.setTypeface(typeface);
            imageView.setVisibility(View.VISIBLE);
        }
        return convertView;

    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup prnt) {
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.custom_spinner_drop_down, null);

        }

        TextView textView = (TextView) convertView.findViewById(R.id.spinnerItemText);
        textView.setText(contentArray[position]);

        TextView imageView = (TextView) convertView.findViewById(R.id.spinnerItemImage);


        imageView.setTypeface(typeface);
        int selected = mySpinner.getSelectedItemPosition();
        /*if (position == selected) {
            imageView.setVisibility(View.VISIBLE);
            textView.setTextColor(ctx.getResources().getColor(R.color.trade_blue));
        } else {
            imageView.setVisibility(View.INVISIBLE);
            textView.setTextColor(Color.BLACK);
        }*/
        if (position == selected) {
            imageView.setVisibility(View.VISIBLE);
            if(isUpdateExchangeColor) {
                if (textView.getText().toString().trim().equalsIgnoreCase("NSE")) {
                    imageView.setTextColor(ctx.getResources().getColor(R.color.colorAccent));
                    textView.setTextColor(ctx.getResources().getColor(R.color.colorAccent));
                } else if (textView.getText().toString().trim().equalsIgnoreCase("BSE")) {
                    imageView.setTextColor(ctx.getResources().getColor(R.color.colorPrimary));
                    textView.setTextColor(ctx.getResources().getColor(R.color.colorPrimary));
                } /*else if (textView.getText().toString().trim().equalsIgnoreCase(EMTLabels.NFO_EXCHANGE)) {
                    imageView.setTextColor(ctx.getResources().getColor(R.color.nfo_color));
                    textView.setTextColor(ctx.getResources().getColor(R.color.nfo_color));
                } else if (textView.getText().toString().trim().equalsIgnoreCase(EMTLabels.CDS_EXCHANGE)) {
                    imageView.setTextColor(ctx.getResources().getColor(R.color.cds_color));
                    textView.setTextColor(ctx.getResources().getColor(R.color.cds_color));
                }*/
            }
        } else {
            imageView.setVisibility(View.INVISIBLE);
            textView.setTextColor(Color.BLACK);

            if(isUpdateExchangeColor) {
                if (textView.getText().toString().trim().equalsIgnoreCase("NSE")) {
                    textView.setTextColor(ctx.getResources().getColor(R.color.colorAccent));
                } else if (textView.getText().toString().trim().equalsIgnoreCase("BSE")) {
                    textView.setTextColor(ctx.getResources().getColor(R.color.colorPrimary));
                } /*else if (textView.getText().toString().trim().equalsIgnoreCase(EMTLabels.NFO_EXCHANGE)) {
                    textView.setTextColor(ctx.getResources().getColor(R.color.nfo_color));
                } else if (textView.getText().toString().trim().equalsIgnoreCase(EMTLabels.CDS_EXCHANGE)) {
                    textView.setTextColor(ctx.getResources().getColor(R.color.cds_color));
                }*/
            }
        }


        return convertView;
    }
}

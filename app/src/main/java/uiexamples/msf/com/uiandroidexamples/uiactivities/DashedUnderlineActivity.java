package uiexamples.msf.com.uiandroidexamples.uiactivities;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.view.View;
import android.widget.TextView;

import uiexamples.msf.com.uiandroidexamples.R;
import uiexamples.msf.com.uiandroidexamples.adapters.DashedUnderlineSpan;

/**
 * Created by Thiyagesh S on 27-12-2016.
 */

public class DashedUnderlineActivity extends AppCompatActivity {

    TextView dashed_txt;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashedunderline);
        dashed_txt = (TextView) findViewById(R.id.dashed_txt);
        String text = "hello how are you";
        applyDottedLine(getApplicationContext(),text,dashed_txt);
    }

    public static void applyDottedLine(Context mContext, String input, TextView textView) {
        if (!input.isEmpty()) {
            SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(input);
            int intSpannableStringBuilderLength = spannableStringBuilder.length();
            int colorCode = R.color.colorPrimary;
            spannableStringBuilder.setSpan(
                    new DashedUnderlineSpan(textView, ContextCompat.getColor(mContext, colorCode),
                            mContext.getResources().getDimension(R.dimen.dus_stroke_thickness),
                            mContext.getResources().getDimension(R.dimen.dus_dash_path),
                            mContext.getResources().getDimension(R.dimen.dus_offset_y),
                            mContext.getResources().getDimension(R.dimen.dus_spacing_extra)), 0,
                    intSpannableStringBuilderLength, Spanned.SPAN_INCLUSIVE_INCLUSIVE);


            textView.setLayerType(View.LAYER_TYPE_SOFTWARE, null);

            textView.setText(spannableStringBuilder);
        } else {
            textView.setText(input);
        }
    }
}

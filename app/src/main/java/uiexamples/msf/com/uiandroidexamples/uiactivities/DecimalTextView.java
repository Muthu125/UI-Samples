package uiexamples.msf.com.uiandroidexamples.uiactivities;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.text.SpannableString;
import android.text.style.RelativeSizeSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AccelerateInterpolator;
import android.widget.TextView;

import uiexamples.msf.com.uiandroidexamples.R;
import uiexamples.msf.com.uiandroidexamples.adapters.AccountProgressCircle;

import static uiexamples.msf.com.uiandroidexamples.adapters.DecimalFormatText.setUpSymbolRate;


/**
 * Created by muthuv on 12/20/2016.
 */

public class DecimalTextView extends Activity {

    private TextView ruppeeSymbol, withoutRuppeeSymbol, txtHeader1, txtHeader2;
    private AccountProgressCircle accountCircle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.decimal_textview);
        ruppeeSymbol = (TextView) findViewById(R.id.rupee_symbol);
        withoutRuppeeSymbol = (TextView) findViewById(R.id.no_rupee_symbol);
        accountCircle = (AccountProgressCircle) findViewById(R.id.account_circle);
        txtHeader1 = (TextView) findViewById(R.id.text1_header);
        txtHeader2 = (TextView) findViewById(R.id.text2_header);

        SpannableString ltpValue = new SpannableString(getString(R.string.AE_RUPEES_SYMBOL) + " " + "14,788.48");
        ltpValue.setSpan(new RelativeSizeSpan(0.8f), 0, 1, 0); // set size
        setUpSymbolRate(this, ruppeeSymbol, ltpValue.toString(), false);

        SpannableString ltpValue1 = new SpannableString(getString(R.string.AE_RUPEES_SYMBOL) + " " + "16,210.55");
        ltpValue1.setSpan(new RelativeSizeSpan(0.8f), 0, 1, 0); // set size
        setUpSymbolRate(this, withoutRuppeeSymbol, ltpValue1.toString(), true);

        if (getIntent().getExtras() != null){
            String received = getIntent().getStringExtra("RECEIVED");
            if (received.equalsIgnoreCase("PROGRESS_ANIMATION")){
                showProgress("", true);
                accountCircle.setVisibility(View.GONE);
            } else if (received.equalsIgnoreCase("PROGRESS_ACCOUNT")) {
                accountCircle.setVisibility(View.VISIBLE);
                anim();
            }
            ruppeeSymbol.setVisibility(View.GONE);
            withoutRuppeeSymbol.setVisibility(View.GONE);
            txtHeader1.setVisibility(View.GONE);
            txtHeader2.setVisibility(View.GONE);
        } else {
            accountCircle.setVisibility(View.GONE);
            ruppeeSymbol.setVisibility(View.VISIBLE);
            withoutRuppeeSymbol.setVisibility(View.VISIBLE);
            txtHeader1.setVisibility(View.VISIBLE);
            txtHeader2.setVisibility(View.VISIBLE);
        }
    }

    private void anim() {
//        AccountProgressCircle accountProgressCircle = new AccountProgressCircle(this);
        AnimatorSet set = new AnimatorSet();

        accountCircle.setStartColor(ContextCompat.getColor(this,R.color.my_account_circle_green_end));
        accountCircle.setEndColor(ContextCompat.getColor(this,R.color.my_account_circle_green_end));

        set.playTogether(ObjectAnimator.ofFloat(accountCircle, "percent", 0,1));
        set.setDuration(600);
        set.setInterpolator(new AccelerateInterpolator());
        set.start();
    }




    public static Dialog getProgressDialog(Context context, String progressMsg,
                                           boolean isCancelable) {
        Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.custom_progress);
        dialog.getWindow().setBackgroundDrawable(
                new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);

        dialog.setCancelable(isCancelable);
        dialog.setCanceledOnTouchOutside(true);
        return dialog;
    }

    private Dialog dialog;

    public void showProgress(String progressMsg, boolean isCancelable) {
        if (dialog == null) {
            dialog = getProgressDialog(this, "", isCancelable);
        }

        if (!dialog.isShowing()) {
            dialog.show();
        }
    }


    public void removeProgress() {
        if (dialog != null && dialog.isShowing()) {
            dialog.cancel();
        }
    }

}

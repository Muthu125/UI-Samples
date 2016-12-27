package uiexamples.msf.com.uiandroidexamples.uiactivities;

import android.app.AlertDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;


import uiexamples.msf.com.uiandroidexamples.R;
import uiexamples.msf.com.uiandroidexamples.adapters.MSFDialog;

/**
 * Created by Thiyagesh S on 27-12-2016.
 */

public class CustomDialog extends AppCompatActivity {
    Button btn1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialogfragment);
        btn1 = (Button) findViewById(R.id.button);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final AlertDialog alertDialog = MSFDialog.alertDialogWithView(CustomDialog.this, 0, "", "",
                        "CONFIRM", "No", true, R.layout.withdraw_dialogfragment, new MSFDialog.DialogListener() {
                            @Override
                            public void alertDialogAction(MSFDialog.Action var1, Object... var2) {
                            }
                        });

                String withDrawTitle = "Order placed";

                alertDialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
                alertDialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);

                ((TextView) alertDialog.findViewById(R.id.tv_withdraw_title)).setText("TITLE");
                ((TextView) alertDialog.findViewById(R.id.tv_withdraw_msg)).setText("MESSAGE");
                ((TextView) alertDialog.findViewById(R.id.bt_withdraw_cancel)).setText("CANCEL");
                TextView title = (TextView) alertDialog.findViewById(R.id.tv_withdraw_content);
                title.setText(withDrawTitle);
                final Button cancelUtOrderButton = (Button) alertDialog.findViewById(R.id.btn_withdraw_confirm);
                cancelUtOrderButton.setAlpha((float) 0.25);
                cancelUtOrderButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        alertDialog.dismiss();
                    }
                });
                Button cancelButton = (Button) alertDialog.findViewById(R.id.bt_withdraw_cancel);
                cancelButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        alertDialog.dismiss();
                    }
                });

            }
        });




    }
}





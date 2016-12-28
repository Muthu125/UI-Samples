package uiexamples.msf.com.uiandroidexamples.adapters;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Handler;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnticipateInterpolator;
import android.view.animation.OvershootInterpolator;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.msf.ui.BaseActivity;
import com.ogaclejapan.arclayout.ArcLayout;

import java.util.ArrayList;
import java.util.List;

import uiexamples.msf.com.uiandroidexamples.R;

/*
 * Created by VelrajP on 5/13/2016.*/


public class ArcLayoutController extends BaseActivity {


    private View fab, menuLayout;
    private PopupWindow popupWindow;
    com.ogaclejapan.arclayout.ArcLayout arcLayout;
    TextView detradeingtext, btntrade, btnAlert, btnchart, btnQuote, btnaddScrips, btndetailsTextView;

    Toast toast = null;


    public ArcLayoutController(Context mContext) {

        this.context = mContext;
    }





    public interface OnOptionSClickListener {

        void onTradeClick(int position);

        void onChartClick(int postiion);

        void onQuoteClick(int position);

        void onSetAlertClick(int position);

        void onSetAddScrips(int position);

        void onDetailsPage(int position);

    }

    public void setUpViews(View view, String trade, String quote, String chart, String details, String setalert, String addscrips, String title, final int position) {


        if (trade.equals("Six")) {
            LayoutInflater layoutInflater =
                    (LayoutInflater) context.getApplicationContext()
                            .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View popupView = layoutInflater.inflate(R.layout.arcview, null);


            popupWindow = new PopupWindow(
                    popupView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            fab = (View) popupView.findViewById(R.id.fab);


            detradeingtext = (TextView) popupView.findViewById(R.id.detradedsymbol);


           // setTypefaceForTextViewIcon((TextView) fab, FONT_ICON_SET_0);
            fab.setPressed(true);
            fab.invalidate();
            menuLayout = (View) popupView.findViewById(R.id.menu_layout);
            arcLayout = (ArcLayout) popupView.findViewById(R.id.arc_layout);
            arcLayout.setVisibility(View.VISIBLE);
            RelativeLayout root_layout = (RelativeLayout) popupView.findViewById(R.id.root_layout);

            //child buttons

            btntrade = (TextView) popupView.findViewById(R.id.trade_btn_Six);
            btnAlert = (TextView) popupView.findViewById(R.id.Alert_Btn_Six);
            btnchart = (TextView) popupView.findViewById(R.id.Charts_btn_Six);
            btnQuote = (TextView) popupView.findViewById(R.id.Quotes_btn_Six);
            btnaddScrips = (TextView) popupView.findViewById(R.id.Scrips_btn_Six);
            btndetailsTextView = (TextView) popupView.findViewById(R.id.Detail_btn_Six);

         /*   setTypefaceForTextViewIcon(btntrade, FONT_ICON_SET_0);
            setTypefaceForTextViewIcon(btnAlert, FONT_ICON_SET_1);
            setTypefaceForTextViewIcon(btnchart, FONT_ICON_SET_0);
            setTypefaceForTextViewIcon(btnQuote, FONT_ICON_SET_0);
            setTypefaceForTextViewIcon(btnaddScrips, FONT_ICON_SET_0);
            setTypefaceForTextViewIcon(btndetailsTextView, FONT_ICON_SET_0);*/


            root_layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    fab.performClick();
                }
            });



            btnAlert.setOnClickListener(new Button.OnClickListener() {
                @Override
                public void onClick(View v) {


                    fab.performClick();
                    final Handler mHandler = new Handler();

                    Runnable mUpdateTimeTask = new Runnable() {
                        public void run() {

                        }
                    };
                    mHandler.postDelayed(mUpdateTimeTask, 1300);
                }
            });



            btndetailsTextView.setOnClickListener(new Button.OnClickListener() {

                @Override
                public void onClick(View v) {


                    fab.performClick();
                    final Handler mHandler = new Handler();

                    Runnable mUpdateTimeTask = new Runnable() {
                        public void run() {
                        }
                    };
                    mHandler.postDelayed(mUpdateTimeTask, 1300);

                }
            });

            btnaddScrips.setOnClickListener(new Button.OnClickListener() {

                @Override
                public void onClick(View v) {
                    fab.performClick();
                    final Handler mHandler = new Handler();

                    Runnable mUpdateTimeTask = new Runnable() {
                        public void run() {
                        }
                    };
                    mHandler.postDelayed(mUpdateTimeTask, 1300);

                }
            });






        } else if (trade.equals("Five")) {


            LayoutInflater layoutInflater =
                    (LayoutInflater) context.getApplicationContext()
                            .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View popupView = layoutInflater.inflate(R.layout.arcview, null);


            popupWindow = new PopupWindow(
                    popupView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            fab = (View) popupView.findViewById(R.id.fab);


            detradeingtext = (TextView) popupView.findViewById(R.id.detradedsymbol);



            fab.setPressed(true);
            fab.invalidate();
            menuLayout = (View) popupView.findViewById(R.id.menu_layout);
            arcLayout = (ArcLayout) popupView.findViewById(R.id.arc_layout_Five);
            arcLayout.setVisibility(View.VISIBLE);
            RelativeLayout root_layout = (RelativeLayout) popupView.findViewById(R.id.root_layout);
            //child buttons
            btntrade = (TextView) popupView.findViewById(R.id.trade_btn_Five);
            btnAlert = (TextView) popupView.findViewById(R.id.Alert_btn_Five);
            btnchart = (TextView) popupView.findViewById(R.id.Charts_btn_Five);
            btnQuote = (TextView) popupView.findViewById(R.id.Quotes_btn_Five);
            btnaddScrips = (TextView) popupView.findViewById(R.id.Scrips_btn_Five);




            root_layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    fab.performClick();
                }
            });
            btnAlert.setOnClickListener(new Button.OnClickListener() {
                @Override
                public void onClick(View v) {

                    fab.performClick();
                    final Handler mHandler = new Handler();

                    Runnable mUpdateTimeTask = new Runnable() {
                        public void run() {

                        }
                    };
                    mHandler.postDelayed(mUpdateTimeTask, 1300);
                }
            });

            btnaddScrips.setOnClickListener(new Button.OnClickListener() {

                @Override
                public void onClick(View v) {


                    fab.performClick();
                    final Handler mHandler = new Handler();

                    Runnable mUpdateTimeTask = new Runnable() {
                        public void run() {
                        }
                    };
                    mHandler.postDelayed(mUpdateTimeTask, 1300);

                }
            });


        } else {
            LayoutInflater layoutInflater =
                    (LayoutInflater) context.getApplicationContext()
                            .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View popupView = layoutInflater.inflate(R.layout.arcview, null);


            popupWindow = new PopupWindow(
                    popupView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            fab = (View) popupView.findViewById(R.id.fab);


            detradeingtext = (TextView) popupView.findViewById(R.id.detradedsymbol);


            fab.setPressed(true);
            fab.invalidate();
            menuLayout = (View) popupView.findViewById(R.id.menu_layout);
            arcLayout = (ArcLayout) popupView.findViewById(R.id.arc_layout_four);
            arcLayout.setVisibility(View.VISIBLE);
            RelativeLayout root_layout = (RelativeLayout) popupView.findViewById(R.id.root_layout);
            TextView alert =
            //child buttons
            btntrade = (TextView) popupView.findViewById(R.id.trade_btn_Four);
            btnchart = (TextView) popupView.findViewById(R.id.Charts_btn_Four);
            btnQuote = (TextView) popupView.findViewById(R.id.Quotes_btn_Four);
            btnaddScrips = (TextView) popupView.findViewById(R.id.Scrips_btn_Four);
            btnaddScrips.setText("k");




            root_layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    fab.performClick();
                }
            });

            btnaddScrips.setOnClickListener(new Button.OnClickListener() {

                @Override
                public void onClick(View v) {


                    fab.performClick();
                    final Handler mHandler = new Handler();

                    Runnable mUpdateTimeTask = new Runnable() {
                        public void run() {
                        }
                    };
                    mHandler.postDelayed(mUpdateTimeTask, 1300);

                }
            });

        }




        detradeingtext.setText(title);
        btntrade.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {

                fab.performClick();
                final Handler mHandler = new Handler();
                Runnable mUpdateTimeTask = new Runnable() {
                    public void run() {

                    }
                };
                mHandler.postDelayed(mUpdateTimeTask, 1300);


            }
        });




        btnchart.setOnClickListener(new Button.OnClickListener() {

            @Override
            public void onClick(View v) {
                fab.performClick();


                final Handler mHandler = new Handler();

                Runnable mUpdateTimeTask = new Runnable() {
                    public void run() {

                    }
                };
                mHandler.postDelayed(mUpdateTimeTask, 1300);


            }
        });


        btnQuote.setOnClickListener(new Button.OnClickListener() {

            @Override
            public void onClick(View v) {


                fab.performClick();
                final Handler mHandler = new Handler();

                Runnable mUpdateTimeTask = new Runnable() {
                    public void run() {
                    }
                };
                mHandler.postDelayed(mUpdateTimeTask, 1300);

            }
        });






        fab.setOnClickListener(new Button.OnClickListener() {

                                   @Override
                                   public void onClick(View v) {
                                       if (v.getId() == R.id.fab) {
                                           onFabClick(v);
                                           return;
                                       }

                                       if (v instanceof Button) {
                                           showToast((Button) v);
                                       }
                                   }
                               }


        );


        //hear i give the position of the pop up x,y

        popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);

        fab.performClick();



    }


    // To exit the arc layout pop up
    private void exitMenu() {

        List<Animator> animList = new ArrayList<>();

        for (int i = arcLayout.getChildCount() - 1; i >= 0; i--) {
            animList.add(createHideItemAnimator(arcLayout.getChildAt(i)));
        }

        AnimatorSet animSet = new AnimatorSet();
        animSet.setDuration(700);
        animSet.setInterpolator(new AnticipateInterpolator());
        animSet.playTogether(animList);
        animSet.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                menuLayout.setVisibility(View.INVISIBLE);
            }
        });
        animSet.start();

        final Handler mHandler = new Handler();

        Runnable mUpdateTimeTask = new Runnable() {
            public void run() {

                popupWindow.dismiss();

            }
        };
        mHandler.postDelayed(mUpdateTimeTask, 650);
    }

    //this below method is toast method to Display some msg to check the button
    private void showToast(Button btn) {
        if (toast != null) {
            toast.cancel();
        }

        String text = "Clicked: " + btn.getText();
        toast = Toast.makeText(context.getApplicationContext(), text, Toast.LENGTH_SHORT);
        toast.show();
    }

    //fab the x button oc click
    private void onFabClick(final View v) {
        final Handler mHandler = new Handler();

        Runnable mUpdateTimeTask = new Runnable() {
            public void run() {

                if (v.isSelected()) {
                    exitMenu();
                } else {
                    showMenu();
                }
                v.setSelected(!v.isSelected());

            }
        };
        mHandler.postDelayed(mUpdateTimeTask, 500);

    }

    //To open the arc menu
    @SuppressWarnings("NewApi")
    private void showMenu() {
        menuLayout.setVisibility(View.VISIBLE);
        List<Animator> animList = new ArrayList<>();
        for (int i = 0, len = arcLayout.getChildCount(); i < len; i++) {
            animList.add(createShowItemAnimator(arcLayout.getChildAt(i)));
        }
        AnimatorSet animSet = new AnimatorSet();
        animSet.setDuration(700);
        animSet.setInterpolator(new OvershootInterpolator());
        animSet.playTogether(animList);
        animSet.start();
        final Handler mHandler = new Handler();
        Runnable mUpdateTimeTask = new Runnable() {
            public void run() {
                detradeingtext.setVisibility(View.VISIBLE);
            }
        };
        mHandler.postDelayed(mUpdateTimeTask, 400);
    }


    //open arc progress_bar_animator
    private Animator createShowItemAnimator(View item) {

        float dx = fab.getX() - item.getX();
        float dy = fab.getY() - item.getY();

        item.setRotation(0f);
        item.setTranslationX(dx);
        item.setTranslationY(dy);

        Animator anim = ObjectAnimator.ofPropertyValuesHolder(
                item,
                AnimatorUtils.rotation(0f, 0f),
                AnimatorUtils.translationX(dx, 0f),
                AnimatorUtils.translationY(dy, 0f)
        );

        return anim;
    }

    private Animator createHideItemAnimator(final View item) {
//close arc progress_bar_animator
        float dx = fab.getX() - item.getX();
        float dy = fab.getY() - item.getY();

        Animator anim = ObjectAnimator.ofPropertyValuesHolder(
                item,
                AnimatorUtils.rotation(0f, 0f),
                AnimatorUtils.translationX(0f, dx),
                AnimatorUtils.translationY(0f, dy)
        );

        anim.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                item.setTranslationX(0f);
                item.setTranslationY(0f);
            }
        });

        return anim;
    }


}

package uiexamples.msf.com.uiandroidexamples.uiactivities;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ListView;

import com.msf.ui.adapter.MSFCommonAdapter;
import com.msf.ui.adapter.MSFPopulationListener;
import com.msf.util.statistics.MSFStatistics;

import java.util.ArrayList;
import java.util.LinkedList;

import uiexamples.msf.com.uiandroidexamples.R;

/**
 * Created by muthuv on 12/21/2016.
 */

public class BlinkListView extends Activity  {

    private ListView doneTradesListView;
    private MSFCommonAdapter<String> doneTradesCommonAdapter;
    private int moveMaxXWidth, moveMinXWidth, moveAniXWidth;
    private LinkedList<Integer> movedPos=new LinkedList<Integer>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.currenex_blink);
    }

    private void setUpViews(View view) {

        doneTradesListView = (ListView) view
                .findViewById(R.id.doneTradeListView);

        /*setUpHeader();*/

    }

   /* @Override
    protected void proceed() {
        super.proceed();
        setUpController();
    }*/

  /*  private void setUpHeader() {
        row1Header1.setText(getString(DT_CURRENCY_PAIR_LBL));
        row1Header2.setText(getString(DT_AMOUNT_LBL));
        row1Header3.setText(getString(DT_AT_RATE_LBL));
        row2Header1.setText(getString(DT_TRADE_ID_LBL));
        row2Header2.setText(getString(DT_TRADE_TIME_LBL));
        row2Header3.setText(getString(DT_USER_LBL));
    }*/

    private void setUpController() {
        moveMinXWidth = MSFStatistics.getWidth(this) * 7 / 100;
        moveMaxXWidth = MSFStatistics.getWidth(this) * 15 / 100;
        moveAniXWidth = MSFStatistics.getWidth(this) * 3 / 100;
        ArrayList<String> sampleList = new ArrayList<>();
        sampleList.add("Android");
        sampleList.add("Baseball");
        sampleList.add("Cricket");
        sampleList.add("Friend");
        sampleList.add("Lion");
        sampleList.add("Leopard");
        sampleList.add("Marshmallow");
        sampleList.add("Manhattan");
        sampleList.add("Nigeria");
        sampleList.add("Nimbus");
        sampleList.add("Night life");
        sampleList.add("Ontario");
        sampleList.add("Peacock");
        sampleList.add("Soccer");
        sampleList.add("Slip fielder");
        sampleList.add("Quick view");
        sampleList.add("Readers list");
        sampleList.add("Tea time");
        sampleList.add("Umberalla");
        sampleList.add("Version update");
        sampleList.add("xmas");
        sampleList.add("Y not");
        sampleList.add("Zoho");

        setUpListAdapter(sampleList);
    }

    private String oldOrderId = "";


    private void setUpListAdapter(ArrayList<String> sampleList) {
        doneTradesCommonAdapter = new MSFCommonAdapter<String>(
                this, sampleList);
        int[] viewIDs = { R.id.filterView};

        doneTradesCommonAdapter.setLayoutTextViews(
                R.layout.filter_list_row, viewIDs);

        doneTradesCommonAdapter.setAlternativeRowColor(
                getResources().getColor(R.color.colorPrimary),
                getResources().getColor(R.color.colorAccent));

        doneTradesCommonAdapter
                .setPopulationListener(new MSFPopulationListener<String>() {

                    @Override
                    public void populateFrom(View v, int position,
                                             String row, View[] views) {

//                        System.out.println("done trade adapter onpop..");
                        //  if(movedViews.contains()

//                v.clearAnimation();

                       // hashMap.put(position, v);


                        if(movedPos.size() > 0) {
                            if (movedPos.contains((Integer) position)) {
                                selectListViewAnimation(v, position);
                            } else {
                                remainingListViewAnimation(v, position);
                            }
                        }
                    }

                    @Override
                    public void onRowCreate(View[] views) {
                    }

                });

        doneTradesListView.setAdapter(doneTradesCommonAdapter);
    }


    private void remainingListViewAnimation(View v,int pos) {

        v.clearAnimation();
        //movedViews.remove(v);

        //movedPos.remove((Integer)pos);

        TranslateAnimation animation = new TranslateAnimation(moveAniXWidth,
                moveMinXWidth, 0, 0);
        animation.setFillAfter(true);
        animation.setRepeatCount(-1);
        animation.setRepeatMode(Animation.REVERSE);
        animation.setDuration(500);
        v.startAnimation(animation);
    }

    private void selectListViewAnimation(final View v,int pos) {

        v.clearAnimation();
        TranslateAnimation animation = new TranslateAnimation(0, moveMaxXWidth,
                0, 0);
        //  movedViews.add(v);
//        movedPos.add(pos);

       /* if(!movedPos.contains((Integer)pos)) {
            movedPos.add(pos);
        }*/

        animation.setFillAfter(true);
        animation.setDuration(100);
        v.startAnimation(animation);
    }




}



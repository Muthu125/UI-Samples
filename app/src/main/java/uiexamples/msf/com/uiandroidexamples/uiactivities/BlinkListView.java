package uiexamples.msf.com.uiandroidexamples.uiactivities;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ListView;
import android.widget.TextView;

import com.msf.ui.adapter.MSFCommonAdapter;
import com.msf.ui.adapter.MSFPopulationListener;
import com.msf.util.statistics.MSFStatistics;

import java.util.ArrayList;
import java.util.LinkedHashMap;
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
    private int swipeListViewPos;
    private boolean checkSwipeAction = false;

    private LinkedHashMap<Integer, View> hashMap = new LinkedHashMap<Integer, View>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.currenex_blink);
        doneTradesListView = (ListView)findViewById(R.id.doneTradeListView);
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
        sampleList.add("Umbrella");
        sampleList.add("Version update");
        sampleList.add("xmas");
        sampleList.add("Y not");
        sampleList.add("Zoho");

        setUpListAdapter(sampleList);
    }


    private void setUpListAdapter(ArrayList<String> sampleList) {
        doneTradesCommonAdapter = new MSFCommonAdapter<String>(
                this, sampleList);
        int[] viewIDs = { R.id.filterView};

        doneTradesCommonAdapter.setLayoutTextViews(
                R.layout.filter_list_row, viewIDs);

        /*doneTradesCommonAdapter.setAlternativeRowColor(
                getResources().getColor(R.color.men),
                getResources().getColor(R.color.colorAccent));*/

        doneTradesCommonAdapter
                .setOnRowCreateListener(new MSFCommonAdapter.OnRowCreateListener<String>() {

                    @Override
                    public void onRowCreate(View v, int position,
                                            String row, View[] views) {
                        v.setOnTouchListener(gestureListener);

                    }
                });

        doneTradesCommonAdapter
                .setPopulationListener(new MSFPopulationListener<String>() {

                    @Override
                    public void populateFrom(View v, int position,
                                             String row, View[] views) {

//                        System.out.println("done trade adapter onpop..");
                        //  if(movedViews.contains()

//                v.clearAnimation();

                        hashMap.put(position, v);


                        if(movedPos.size() > 0) {
                            if (movedPos.contains((Integer) position)) {
                                selectListViewAnimation(v, position);
                            } else {
                                remainingListViewAnimation(v, position);
                            }
                        }

                        ((TextView)views[0]).setText(row);
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

        movedPos.remove((Integer)pos);

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
        movedPos.add(pos);

        if(!movedPos.contains((Integer)pos)) {
            movedPos.add(pos);
        }

        animation.setFillAfter(true);
        animation.setDuration(100);
        v.startAnimation(animation);
    }


    View.OnTouchListener gestureListener = new View.OnTouchListener() {
        private int padding = 0;
        private int initialx = 0;
        private int currentx = 0;

        public boolean onTouch(View v, MotionEvent event) {

            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                checkSwipeAction = false;
                padding = 0;
                initialx = (int) event.getX();
                currentx = (int) event.getX();
            }
            if (event.getAction() == MotionEvent.ACTION_MOVE) {
                currentx = (int) event.getX();
                padding = currentx - initialx;
            }

            if (event.getAction() == MotionEvent.ACTION_UP
                    || event.getAction() == MotionEvent.ACTION_CANCEL) {

                if (checkSwipeAction) {
                    swipeListViewPos = doneTradesListView.getPositionForView(v);
                    if (!checkFilterFlag)
                        setUpSwipeAnimationAdpter(swipeListViewPos);
                }

                padding = 0;
                initialx = 0;
                currentx = 0;
            }

            int curPosition = doneTradesListView.getPositionForView(v);

            if (initialx > currentx) {
                if (padding < -30 && padding < 0 && movedPos.contains(curPosition)) {
                    checkSwipeAction = true;
                    remainingListViewAnimation(v,curPosition);
                    //removeNetAmountDisplay(curPosition);

                }
            } else {

                if (padding > 20) {
                    if ((!movedPos.contains(curPosition))) {
                        selectListViewAnimation(v,curPosition);
                        //showNetAmountDisplay(curPosition);
                    }
                    if (checkFilterFlag && (!movedPos.contains(curPosition))) {
                        selectListViewAnimation(v,curPosition);
                    }
                    checkSwipeAction = true;
                }
            }
            return true;
        }
    };

    int swipeDoneTradeListPos;
    boolean checkFilterFlag = false;
    private void setUpSwipeAnimationAdpter(int swipePos) {

        String rowSymbol = "";
        double VWAP=0;
        String data = null;

        //doneTradesCommonAdapter.clear();
       /* swipeDoneTradeListPos = doneTradesCommonAdapter
                .getCount() - 1;*/
        //doneTradesCommonAdapter.notifyDataSetChanged();

        doneTradesListView.post(new Runnable() {

            @Override
            public void run() {
                if (checkSwipeAction) {

//             View v1 = doneTradesListView
//                   .getChildAt(swipeDoneTradeListPos);
                    View v1 = hashMap.get(swipeDoneTradeListPos);
                    if (v1 != null) {
                        selectListViewAnimation(v1,swipeDoneTradeListPos);
                    }

                    if(!movedPos.contains(swipeDoneTradeListPos)) {
                        movedPos.add(swipeDoneTradeListPos);
                    }

                    for (int i = 0; i < doneTradesListView.getChildCount(); i++) {
                        if (i != swipeDoneTradeListPos) {
                            View v = doneTradesListView.getChildAt(i);
                            remainingListViewAnimation(v,i);
                        }
                    }

                }
            }
        });
        doneTradesCommonAdapter.notifyDataSetChanged();
    }


}



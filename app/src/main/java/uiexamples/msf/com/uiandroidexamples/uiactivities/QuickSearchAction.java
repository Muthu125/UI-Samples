package uiexamples.msf.com.uiandroidexamples.uiactivities;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.Display;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.msf.ui.adapter.MSFCommonAdapter;
import com.msf.ui.adapter.MSFPopulationListener;
import com.msf.ui.listview.MSFListView;
import com.msf.ui.textview.MSFTextView;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import uiexamples.msf.com.uiandroidexamples.R;

/**
 * Created by muthuv on 12/21/2016.
 */

public class QuickSearchAction extends Activity {

    private MSFListView filterListView;
    private MSFCommonAdapter<String> quickSearchCommonAdapter;
    private RelativeLayout alphabetBaseOverLay;
    private String text = "a";
    private View selectedView = null;
    private String exchangeTokens[];
    private String assetTypes[];
    private String insType[];
    private String symbols[];

    List<String> fullList, exchangeTokenList, sampleList;
    private TextView textViewCenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quick_search_layout);
        setUpViews();
    }

   /* @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = setBaseView(R.layout.base_layout, inflater, container);
        addContent(R.layout.quick_search_layout, inflater, view);
        getIntentCheck();
        setUpViews(view);
        return view;
    }*/

   /* private void getIntentCheck() {
        Bundle bundle = this.getArguments();
        symbols = bundle.getStringArray("QUOTE_NAMES");
        exchangeTokens = bundle.getStringArray("QUOTE_IDS");
        assetTypes = bundle.getStringArray("QUOTE_ASSET_TYPES");
        insType = bundle.getStringArray("QUOTE_INS_TYPES");
        fullList = new ArrayList<String>(Arrays.asList(symbols));
        exchangeTokenList = new ArrayList<String>(Arrays.asList(exchangeTokens));
        assetTypesList = new ArrayList<String>(Arrays.asList(assetTypes));
        insTypeList = new ArrayList<>(Arrays.asList(insType));
    }*/

    private void setUpViews() {
        filterListView = (MSFListView) findViewById(R.id.filteredListView);
        alphabetBaseOverLay = (RelativeLayout) findViewById(R.id.quickscrollOverlay);
        textViewCenter = (MSFTextView) findViewById(R.id.textViewCenter);
        alphabetBaseOverLay.addView(createAlphabetTrack());
        sampleList = new ArrayList<>();
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

        setUpAdapter(sampleList);
        //((MainActivity) getActivity()).setOrientation();
    }

    private void setUpAdapter(final List<String> typedAnswerList) {

        int[] viewIDs = {R.id.filterView};
        //MSFLog.msg("SymbolList Respones data showing : " + typedAnswerList.size());
        quickSearchCommonAdapter = new MSFCommonAdapter<>(this, typedAnswerList);

        quickSearchCommonAdapter.setLayoutTextViews(R.layout.filter_list_row, viewIDs);

        quickSearchCommonAdapter.setPopulationListener(new MSFPopulationListener<String>() {
            @Override
            public void populateFrom(View view, final int i, final String symbolList, final View[] views) {
                ((TextView) views[0]).setText(symbolList);

            }

            @Override
            public void onRowCreate(View[] views) {


            }
        });

        filterListView.setAdapter(quickSearchCommonAdapter);
       /* filterListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(getActivity(), QuoteDetailActivity.class);
                i.putExtra(EXCHANGE, typedAnswerList.get(position));
                i.putExtra(EXCHANGE_TOKEN, exchangeTokensSorted.get(position));
                i.putExtra(ASSET_TYPE, assetTypesSorted.get(position));
                startActivity(i);
            }
        });*/
        quickSearchCommonAdapter.notifyDataSetChanged();
    }

    private ViewGroup createAlphabetTrack() {
        LinearLayout layout = new LinearLayout(this);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams((int) (250 * getResources().getDisplayMetrics().density), LinearLayout.LayoutParams.MATCH_PARENT);
        layoutParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        layout.setLayoutParams(layoutParams);

        layout.setGravity(Gravity.RIGHT);
        layout.setOrientation(LinearLayout.VERTICAL);

        RelativeLayout.LayoutParams textparams = new RelativeLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        textparams.addRule(RelativeLayout.CENTER_IN_PARENT);

        //       textparams.weight = 1;
        int height = getDeviceRealHeight() - (getActionBarHeight() * 2);
        float itemHeight = height / 26.5f;
        int iterate = 1;

        for (char character = 'a'; character <= 'z'; character += iterate) {
            RelativeLayout textViewItemBg = new RelativeLayout(this);
            textViewItemBg.setTag(character);
            RelativeLayout.LayoutParams textViewItemBgParams =
                    new RelativeLayout.LayoutParams((int) itemHeight, (int) itemHeight);
            textViewItemBgParams.addRule(Gravity.RIGHT);
            textViewItemBg.setLayoutParams(textViewItemBgParams);
            textViewItemBg.setBackgroundResource(R.drawable.quickscroll_floating_textview_bg);

            TextView textview = new TextView(this);
            textview.setTextSize(pixelsToSp(this, itemHeight - getTypeValue(6)));
            textview.setTypeface(Typeface.MONOSPACE, Typeface.NORMAL);
            textview.setLayoutParams(textparams);
            textview.setText(Character.toString(character));
            textViewItemBg.addView(textview);
            layout.addView(textViewItemBg);

        }

        layout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                alphabetBaseOverLay.setBackgroundColor(Color.parseColor("#BB383838"));

                LinearLayout layout = (LinearLayout) v;


                switch (event.getActionMasked()) {

                    case MotionEvent.ACTION_DOWN: {

                        View firstView = getSelectedView(event, layout);
                        if (selectedView != firstView) {
                            zoomInView(firstView);
                            selectedView = firstView;
                        }
                        break;

                    }
                    case MotionEvent.ACTION_MOVE: {

                        View moveView = getSelectedView(event, layout);
                        if (selectedView != moveView) {
                            if (selectedView != null) {
                                zoomOutView(selectedView);
                            }

                            zoomInView(moveView);
                            selectedView = moveView;
                        }
                        break;
                    }
                    case MotionEvent.ACTION_UP: {

                        if (selectedView != null) {
                            zoomOutView(selectedView);
                        }

                        break;
                    }

                }
                return true;
            }
        });

        return layout;
    }

    private View getSelectedView(MotionEvent event, LinearLayout layout) {
        View view;
        for (int i = 0; i < layout.getChildCount(); i++) {
            view = layout.getChildAt(i);
            if (view.getTag() != null) {
                Rect outRect = new Rect(view.getLeft(), view.getTop(), view.getRight(), view.getBottom());

                if (outRect.contains((int) event.getX(), (int) event.getY())) {
                    Log.d("", view.getId() + "");
                    text = (char) view.getTag() + "";
                    textViewCenter.setText(text);
                    List<String> sortedList = new ArrayList<String>();
                    List<String> sortedExchangeTokensList = new ArrayList<>();
                    List<String> sortedAssetTypesList = new ArrayList<>();
                    List<String> sortedInsTypeList = new ArrayList<>();
                    for (int k = 0; k < sampleList.size(); k++) {
                        if (sampleList.get(k).startsWith(text.toUpperCase())) {
                            sortedList.add(sampleList.get(k));
                           /* sortedExchangeTokensList.add(exchangeTokenList.get(k));
                            sortedAssetTypesList.add(assetTypesList.get(k));
                            sortedInsTypeList.add(insTypeList.get(k));*/
                        }
                    }
                    //setUpAdapter(sortedList, sortedExchangeTokensList, sortedAssetTypesList, sortedInsTypeList);
                    return view;
                }
            }
        }
        return null;

    }

    private void zoomInView(View view) {
        if (view != null) {
            final AnimatorSet as = new AnimatorSet();

            ObjectAnimator translationX = ObjectAnimator.ofFloat(view, "translationX", -200);
            translationX.setInterpolator(new AccelerateInterpolator());

            ObjectAnimator scaleX = ObjectAnimator.ofFloat(view, "scaleX", 2.0f);
            scaleX.setInterpolator(new AccelerateInterpolator());

            ObjectAnimator scaleY = ObjectAnimator.ofFloat(view, "scaleY", 2.0f);
            scaleY.setInterpolator(new AccelerateInterpolator());

            as.play(translationX);
            as.playTogether(scaleX, scaleY);

            as.setDuration(200);
            as.start();
        }
    }


    private void zoomOutView(View view) {
        if (view != null) {
            final AnimatorSet as = new AnimatorSet();

            ObjectAnimator translationX = ObjectAnimator.ofFloat(view, "translationX", -0);
            translationX.setInterpolator(new AccelerateInterpolator());

            ObjectAnimator scaleX = ObjectAnimator.ofFloat(view, "scaleX", 1);
            scaleX.setInterpolator(new AccelerateInterpolator());

            ObjectAnimator scaleY = ObjectAnimator.ofFloat(view, "scaleY", 1);
            scaleY.setInterpolator(new AccelerateInterpolator());

            as.play(translationX);
            as.playTogether(scaleX, scaleY);

            as.setDuration(200);
            as.start();
        }
    }

    public int getDeviceRealHeight() {
        Display display = getWindowManager().getDefaultDisplay();
        int realWidth;
        int realHeight;

        if (Build.VERSION.SDK_INT >= 17) {
            //new pleasant way to get real metrics
            DisplayMetrics realMetrics = new DisplayMetrics();
            display.getRealMetrics(realMetrics);
            realWidth = realMetrics.widthPixels;
            realHeight = realMetrics.heightPixels;

        } else if (Build.VERSION.SDK_INT >= 14) {
            //reflection for this weird in-between time
            try {
                Method mGetRawH = Display.class.getMethod("getRawHeight");
                Method mGetRawW = Display.class.getMethod("getRawWidth");
                realWidth = (Integer) mGetRawW.invoke(display);
                realHeight = (Integer) mGetRawH.invoke(display);
            } catch (Exception e) {
                //this may not be 100% accurate, but it's all we've got
                realWidth = display.getWidth();
                realHeight = display.getHeight();
            }

        } else {
            //This should be close, as lower API devices should not have window navigation bars
            realWidth = display.getWidth();
            realHeight = display.getHeight();
        }
        return realHeight;
    }

    public int getActionBarHeight() {
        //int actionBarHeight = this.getActionBar().getHeight();
        int actionBarHeight = 0;
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            actionBarHeight = getResources().getDimensionPixelSize(resourceId);
        }

        final TypedValue tv = new TypedValue();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            if (getTheme().resolveAttribute(android.R.attr.actionBarSize, tv, true))
                actionBarHeight = TypedValue.complexToDimensionPixelSize(tv.data, getResources().getDisplayMetrics());
        } else if (true == getTheme().resolveAttribute(android.support.v7.appcompat.R.attr.actionBarSize, tv, true))
            actionBarHeight = TypedValue.complexToDimensionPixelSize(tv.data, getResources().getDisplayMetrics());
        return actionBarHeight;
    }

    public float getTypeValue(int value) {
        Resources resources = getResources();
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, value, resources.getDisplayMetrics());
    }

    public static float pixelsToSp(Context context, float px) {
        float scaledDensity = context.getResources().getDisplayMetrics().scaledDensity;
        return px / scaledDensity;
    }
}

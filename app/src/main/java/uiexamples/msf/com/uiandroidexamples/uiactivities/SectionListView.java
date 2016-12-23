package uiexamples.msf.com.uiandroidexamples.uiactivities;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

import uiexamples.msf.com.uiandroidexamples.R;
import uiexamples.msf.com.uiandroidexamples.adapters.HeaderListViewAdapter;
import uiexamples.msf.com.uiandroidexamples.adapters.RollOverDataClass;

/**
 * Created by muthuv on 12/23/2016.
 */

public class SectionListView extends Activity {

    ListView sectionListView;
    HeaderListViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.section_listview);

        sectionListView = (ListView) findViewById(R.id.headerListView);
        ArrayList<String> sampleHeaderList = new ArrayList<>();
        sampleHeaderList.add("Header 1");
        sampleHeaderList.add("Header 2");
        sampleHeaderList.add("Header 3");
        sampleHeaderList.add("Header 4");
        sampleHeaderList.add("Header 5");

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

        showRollOverValues(sampleList, sampleHeaderList);
    }

    private void showRollOverValues(ArrayList<String> sampleList, ArrayList<String> sampleHeaderList) {

        adapter = new HeaderListViewAdapter(this);
        LayoutInflater inflater1 =(LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        ViewGroup header = (ViewGroup)inflater1.inflate(R.layout.header_layout, sectionListView, false);
        sectionListView.addHeaderView(header, null, false);


        RollOverDataClass dataSeperator = new RollOverDataClass();
        dataSeperator.setHeaderName("Highest Rollovers");
        dataSeperator.setSectionHeader(true);
        adapter.addSeparatorItem(sampleHeaderList);

        //dataSeperator = null;
        //adapter.addItem(sampleList);
        for (int i = 0; i < sampleList.size(); i++) {

            //if(i < 5) {
                adapter.addItem(sampleList.get(i).toString());
            //}

            //adapter.addItemForDetail(sampleList);
        }



    /*    RollOverDataClass datLower = new RollOverDataClass();
        datLower.setHeaderName("Lowest Rollovers");
        datLower.setIsSectionHeader(true);
        adapter.addSeparatorItem(datLower);

        datLower = null;

        for (int i = 0;i < marketRollOverResponse.getLR().size(); i++) {
            RollOverDataClass data = new RollOverDataClass();
            data.setLowerRollOver(marketRollOverResponse.getLR().get(i));
            data.setIsHR(false);

            if(i < 5) {
                adapter.addItem(data);
            }
            adapter.addItemForDetail(data);
        }*/

        sectionListView.setAdapter(adapter);

    }
}

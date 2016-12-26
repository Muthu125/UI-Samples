package uiexamples.msf.com.uiandroidexamples.uiactivities;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.inqbarna.tablefixheaders.TableFixHeaders;
import com.inqbarna.tablefixheaders.adapters.IMSFHorizontalListPopulationListener;
import com.inqbarna.tablefixheaders.adapters.MSFCommonHorizontalListAdapter;
import java.util.ArrayList;
import java.util.List;
import uiexamples.msf.com.uiandroidexamples.R;


public class FamilyTable extends Activity {
	private MSFCommonHorizontalListAdapter<String> adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.table);
		tableData();
}

	private void tableData(){
		String headers[] = {"Name", "Company", "Version", "API", "Storage", "Size", "RAM", "API", "Storage", "Size", "RAM"};
		handleIndicesResponse(headers);
	}





	private void handleIndicesResponse(String headers[]) {
		TableFixHeaders tableFixHeaders = (TableFixHeaders) findViewById(R.id.watchlist);

		int columnSize = headers.length;
		List<List<String>> adapterData = new ArrayList<>();
		//ZEROTH ROW
		{
			List<String> rowZero = new ArrayList<>();
			for (int i = 0; i < columnSize; i++) {

				//HEADER MAKE IT TRUE
				rowZero.add(headers[i]);

			}
			adapterData.add(rowZero);

		}
		//Group data
		String array[] = {"Nexus One", "HTC", "Gingerbread", "10", "512 MB", "3.7\"", "512 MB","Nexus One", "HTC", "Gingerbread", "10"};



		{
			List<String> data = new ArrayList<>();
			for (int i = 0; i < columnSize; i++) {

				data.add(array[i]);

			}
			adapterData.add(data);
			adapterData.add(data);
			adapterData.add(data);
			adapterData.add(data);
			adapterData.add(data);
			adapterData.add(data);
			adapterData.add(data);
			adapterData.add(data);
			adapterData.add(data);
			adapterData.add(data);
			adapterData.add(data);
			adapterData.add(data);
			adapterData.add(data);
			adapterData.add(data);
			adapterData.add(data);
		}
		int[] bodyIds = {R.id.tvFXInvestRowValue, R.id.rootLayout};
		int[] columnIds = {R.id.tvFXInvestColumnValue,R.id.productIcon};

		adapter = new MSFCommonHorizontalListAdapter<>
				(this, R.layout.fxinvest_body_cell, bodyIds, bodyListener,
						R.layout.fxinvest_row_cell, bodyIds, firstBoxListener,
						R.layout.prelogin_watchlist_first_column, columnIds, firstColumnListener,
						R.layout.fxinvest_first_row_cell, bodyIds, firstRowListener,
						adapterData);
		adapter.setFirstColumnWidth(90);
		adapter.setHeight(60);
		adapter.setHeaderHeight(25);
		tableFixHeaders.setAdapter(adapter);

	}


	IMSFHorizontalListPopulationListener<String> bodyListener = new IMSFHorizontalListPopulationListener<String>() {
		@Override
		public void populateFrom(int row, int column, View convertView, ViewGroup parent, String cellObject, View[] views) {
			((TextView) views[0]).setText("Honeycomb");

		}

		@Override
		public void onRowCreate(int row, int column, View convertView, ViewGroup parent, String cellObject, View[] views) {

		}
	};

	IMSFHorizontalListPopulationListener<String> firstBoxListener = new IMSFHorizontalListPopulationListener<String>() {
		@Override
		public void populateFrom(int row, int column, View convertView, ViewGroup parent, String cellObject, View[] views) {
			((TextView) views[0]).setText("Nexus Q");

		}

		@Override
		public void onRowCreate(int row, int column, View convertView, ViewGroup parent, String cellObject, View[] views) {

		}
	};

	IMSFHorizontalListPopulationListener<String> firstColumnListener = new IMSFHorizontalListPopulationListener<String>() {
		@Override
		public void populateFrom(final int row, int column, View convertView, ViewGroup parent, String cellObject, final View[] views) {

			((TextView) views[0]).setText("Nexus j");





		}


		@Override
		public void onRowCreate(int row, int column, View convertView, ViewGroup parent, String cellObject, View[] views) {

		}
	};

	IMSFHorizontalListPopulationListener<String> firstRowListener = new IMSFHorizontalListPopulationListener<String>() {
		@Override
		public void populateFrom(int row, int column, View convertView, ViewGroup parent, String cellObject, View[] views) {
			((TextView) views[0]).setText("Nexus Q");

		}

		@Override
		public void onRowCreate(int row, int column, View convertView, ViewGroup parent, String cellObject, View[] views) {

		}
	};










}

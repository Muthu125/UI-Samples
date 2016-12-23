package uiexamples.msf.com.uiandroidexamples.uiactivities;


import android.app.ListActivity;
import android.content.Intent;
import android.view.View;
import android.widget.ListView;

import uiexamples.msf.com.uiandroidexamples.R;

public class CircleMainActivity extends ListActivity {

	protected void onCreate(android.os.Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.circle_activity_main);
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);

		Intent intent = new Intent(this, CircleSampleActivity.class);
		switch (position) {
			case 0:
				intent.putExtra(CircleSampleActivity.ARG_LAYOUT, R.layout.sample);
				break;
			case 1:
				intent.putExtra(CircleSampleActivity.ARG_LAYOUT, R.layout.sample_fast);
				break;
		/*	case 2:
				intent.putExtra(CircleSampleActivity.ARG_LAYOUT,
						R.layout.sample_no_rotation);
				break;
			case 3:
				intent.putExtra(CircleSampleActivity.ARG_LAYOUT, R.layout.sample_west);
				break;
			case 4:
				intent.putExtra(CircleSampleActivity.ARG_LAYOUT,
						R.layout.sample_with_background);
				break;
			case 5:
				intent.putExtra(CircleSampleActivity.ARG_LAYOUT,
						R.layout.sample_7_items);
				break;
			case 6:
				intent.putExtra(CircleSampleActivity.ARG_LAYOUT,
						R.layout.sample_8_items);
				break;*/
		}

		startActivity(intent);
	}


}

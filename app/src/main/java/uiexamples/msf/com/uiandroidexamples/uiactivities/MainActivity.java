package uiexamples.msf.com.uiandroidexamples.uiactivities;

import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import uiexamples.msf.com.uiandroidexamples.R;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //commend removed branch
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        /*if (id == R.id.action_settings) {
            return true;
        }*/

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.sliding_drawer) {
            startActivity(new Intent(this, SlidingDrawer.class));
        } else if (id == R.id.spinner_adapter) {
            startActivity(new Intent(this, SpinnerAdapterUI.class));
        } else if (id == R.id.decimal_font) {
            startActivity(new Intent(this, DecimalTextView.class));
        } else if (id == R.id.progress_animation) {
            Intent intent = new Intent(this, DecimalTextView.class);
            intent.putExtra("RECEIVED", "PROGRESS_ANIMATION");
            startActivity(intent);
        } else if (id == R.id.account_progress) {
            Intent intent = new Intent(this, DecimalTextView.class);
            intent.putExtra("RECEIVED", "PROGRESS_ACCOUNT");
            startActivity(intent);
        } else if (id == R.id.polygon_animation) {
            startActivity(new Intent(this, PolygonAnimation.class));
        } else if (id == R.id.quick_search) {
            startActivity(new Intent(this, QuickSearchAction.class));
        } else if (id == R.id.chart_interval) {
            Intent intent = new Intent(this, CircleSampleActivity.class);
            intent.putExtra(CircleSampleActivity.ARG_LAYOUT, R.layout.sample_fast);
            startActivity(intent);
        } else if (id == R.id.price_wheel) {
            Intent intent = new Intent(this, CircleSampleActivity.class);
            intent.putExtra(CircleSampleActivity.ARG_LAYOUT, R.layout.sample);
            startActivity(intent);
        } else if (id == R.id.price_wheel) {
            Intent intent = new Intent(this, CircleSampleActivity.class);
            intent.putExtra(CircleSampleActivity.ARG_LAYOUT, R.layout.sample);
            startActivity(intent);
        } else if (id == R.id.animation_listview) {
            startActivity(new Intent(this, BlinkListView.class));
        } else if (id == R.id.table_fixed_headers) {
            startActivity(new Intent(this, FamilyTable.class));
        }

        else if (id == R.id.dashed_line) {
            startActivity(new Intent(this, DashedUnderlineActivity.class));
        } else if (id == R.id.custom_dialog) {
            startActivity(new Intent(this, CustomDialog.class));
        }  else if (id == R.id.arc_view) {
        startActivity(new Intent(this, ArcViewActivity.class));
    }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        drawer.closeDrawer(GravityCompat.START);

        return true;
    }

}

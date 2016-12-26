package com.inqbarna.tablefixheaders.adapters;

import android.view.View;
import android.view.ViewGroup;

/**
 * @author mallikarjuna
 * @version 1.0.0
 * @since 23/9/16
 **/
public interface IMSFHorizontalListPopulationListener<T> {

    void populateFrom(int row, int column, View convertView, ViewGroup parent, T cellObject, View[] views);

    void onRowCreate(int row, int column, View convertView, ViewGroup parent, T cellObject, View[] views);

}

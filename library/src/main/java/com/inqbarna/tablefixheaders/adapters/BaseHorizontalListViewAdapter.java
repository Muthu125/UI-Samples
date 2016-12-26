package com.inqbarna.tablefixheaders.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * Created by VelrajP on 6/21/2016.
 */
public abstract class BaseHorizontalListViewAdapter extends BaseTableAdapter {
    private final Context context;
    private final LayoutInflater inflater;
    private int lastPos;

    /**
     * Constructor
     *
     * @param context
     *            The current context.
     */
    public BaseHorizontalListViewAdapter(Context context) {
        this.context = context;
        inflater = LayoutInflater.from(context);
        lastPos = -1;
    }

    /**
     * Returns the context associated with this array adapter. The context is
     * used to create views from the resource passed to the constructor.
     *
     * @return The Context associated with this adapter.
     */
    public Context getContext() {
        return context;
    }

    /**
     * Quick access to the LayoutInflater instance that this Adapter retreived
     * from its Context.
     *
     * @return The shared LayoutInflater.
     */
    public LayoutInflater getInflater() {
        return inflater;
    }

    @Override
    public int getRowCount() {
        return 0;
    }

    @Override
    public int getColumnCount() {
        return 0;
    }

    @Override
    public View getView(int row, int column, View convertView, ViewGroup parent) {

        final View view;

        switch (getItemViewType(row, column)) {
            case 0:
                view = getFirstHeader(row, column, convertView, parent);
                break;
            case 1:
                view = getHeader(row, column, convertView, parent);
                break;
            case 2:
                setLastPos(row);
                view = getFirstBody(row, column, convertView, parent);
                setLastPos(row);
                break;
            case 3:
                view = getBody(row, column, convertView, parent);
                break;
            default:
                throw new RuntimeException("???");
        }
        // view = getBody(row, column, convertView, parent);
        return view;
    }

    @Override
    public int getWidth(int i) {
        return 0;
    }

    @Override
    public int getHeight(int i) {
        return 0;
    }

    @Override
    public int getItemViewType(int i, int i1) {
        return 0;
    }

    @Override
    public int getViewTypeCount() {
        return 0;
    }

    public int getLastPos() {
        return lastPos;
    }

    public void setLastPos(int lastPos) {
        this.lastPos = lastPos;
    }

    /**
     * Sets the text to the view.
     *
     * @param view
     * @param text
     */
    // private void setText(View view, String text) {
    // ((TextView) view.findViewById(android.R.id.text1)).setText(text);
    // }

    /**
     * @param row
     *            the title of the row of this header. If the column is -1
     *            returns the title of the row header.
     * @param column
     *            the title of the column of this header. If the column is -1
     *            returns the title of the column header.
     * @return the string for the cell [row, column]
     */
    public abstract String getCellString(int row, int column);

    public abstract View getBody(int row, int column, View convertView,
                                 ViewGroup parent);

    public abstract View getFirstBody(int row, int column, View convertView,
                                      ViewGroup parent);

    public abstract View getFirstHeader(int row, int column, View convertView,
                                        ViewGroup parent);

    public abstract View getHeader(int row, int column, View convertView,
                                   ViewGroup parent);

    public abstract int getLayoutResource(int row, int column);
}

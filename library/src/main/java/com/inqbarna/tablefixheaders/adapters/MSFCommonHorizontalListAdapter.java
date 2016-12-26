package com.inqbarna.tablefixheaders.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;


/**
 * @author mallikarjuna
 * @version 1.0.0
 * @since 23/9/16
 **/
public class MSFCommonHorizontalListAdapter<T> extends BaseHorizontalListViewAdapter {

    int width = 100;
    int firstColumnWidth = 100;
    int height = 50;
    int headerHeight = 20;

    private Context mContext;
    private float density;
    private List<List<T>> cellObject;

    private int lastSelectedCell = -1;


    /**
     * Moving Items Layout, Views and creation listener
     */
    private int mBodyLayout;
    private int[] mBodyViews;
    private IMSFHorizontalListPopulationListener<T> mBodyListener;

    /**
     * First cell[TopLeft] Layout, Views and creation listener
     */
    private int mFirstCellLayout;
    private int[] mFirstViews;
    private IMSFHorizontalListPopulationListener<T> mFirstCellListener;
    private IHeightChangeListener mHeightChangeListener;

    /**
     * First column [Non Moving] Layout, Views and creation listener
     */
    private int mFirstColumnLayout;
    private int[] mFirstColumnViews;
    private IMSFHorizontalListPopulationListener<T> mFirstColumnCellListener;

    /**
     * First row Layout, Views and creation listener
     */
    private int mFirstRowLayout;
    private int[] mFirstRowViews;
    private IMSFHorizontalListPopulationListener<T> mFirstRowCellListener;
    private int mColumnCount = 0;

    public MSFCommonHorizontalListAdapter(Context context,
                                          int mBodyLayout, int[] mBodyViews, IMSFHorizontalListPopulationListener<T> mBodyListener,
                                          int mFirstCellLayout, int[] mFirstViews, IMSFHorizontalListPopulationListener<T> mFirstCellListener,
                                          int mFirstColumnLayout, int[] mFirstColumnViews, IMSFHorizontalListPopulationListener<T> mFirstColumnCellListener,
                                          int mFirstRowLayout, int[] mFirstRowViews, IMSFHorizontalListPopulationListener<T> mFirstRowCellListener,
                                          List<List<T>> cellObject) {
        super(context);
        this.mContext = context;
        this.mBodyLayout = mBodyLayout;
        this.mBodyViews = mBodyViews;
        this.mBodyListener = mBodyListener;
        this.mFirstCellLayout = mFirstCellLayout;
        this.mFirstViews = mFirstViews;
        this.mFirstCellListener = mFirstCellListener;
        this.mFirstColumnLayout = mFirstColumnLayout;
        this.mFirstColumnViews = mFirstColumnViews;
        this.mFirstColumnCellListener = mFirstColumnCellListener;
        this.mFirstRowLayout = mFirstRowLayout;
        this.mFirstRowViews = mFirstRowViews;
        this.mFirstRowCellListener = mFirstRowCellListener;
        this.cellObject = cellObject;
        this.density = context.getResources().getDisplayMetrics().density;
        if(cellObject != null && cellObject.get(0) != null) {
            this.mColumnCount = cellObject.get(0).size() - 1;
        }
    }

    @Override
    public int getWidth(int column) {


            return Math.round(density * width);
    }

    @Override
    public int getHeight(int row) {

        if(mHeightChangeListener != null) {
            if(mHeightChangeListener.isHeightChangeRequired(row)) {
                return mHeightChangeListener.getHeight(row);
            }
        }


            return getCellHeightForBody();

    }

    public int getCellHeightForBody() {
        return Math.round(density * height);
    }

    @Deprecated
    @Override
    public String getCellString(int row, int column) {
        return null;
    }

    public T getCellObject(int row, int column) {
        column = column + 1;
        row = row + 1;
        return cellObject.get(row).get(column);
    }


    //Moving Cell
    @Override
    public View getBody(int row, int column, View convertView, ViewGroup parent) {

        RowHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(getLayoutResource(row, column), parent, false);
            holder = new RowHolder(convertView, this.mBodyViews);
            if (this.mBodyListener != null) {
                this.mBodyListener.onRowCreate(row, column, convertView, parent, getCellObject(row, column), holder.getViews());
            }
            convertView.setTag(holder);
        } else {
            holder = (RowHolder) convertView.getTag();
        }
        if (this.mBodyListener != null) {
            this.mBodyListener.populateFrom(row, column, convertView, parent, getCellObject(row, column), holder.getViews());
        }

        return convertView;
    }

    //First Column (0 , -1)
    @Override
    public View getFirstBody(int row, int column, View convertView, ViewGroup parent) {

        RowHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(this.mContext).inflate(getLayoutResource(row, column), parent, false);
            holder = new RowHolder(convertView, this.mFirstColumnViews);
            if (this.mFirstColumnCellListener != null) {
                this.mFirstColumnCellListener.onRowCreate(row, column, convertView, parent, getCellObject(row, column), holder.getViews());
            }
            convertView.setTag(holder);
        } else {
            holder = (RowHolder) convertView.getTag();
        }
        if (this.mFirstColumnCellListener != null) {
            this.mFirstColumnCellListener.populateFrom(row, column, convertView, parent, getCellObject(row, column), holder.getViews());
        }

        return convertView;
    }

    //First box
    @Override
    public View getFirstHeader(int row, int column, View convertView, ViewGroup parent) {
        RowHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(this.mContext).inflate(getLayoutResource(row, column), parent, false);
            holder = new RowHolder(convertView, this.mFirstViews);
            if (this.mFirstCellListener != null) {
                this.mFirstCellListener.onRowCreate(row, column, convertView, parent, getCellObject(row, column), holder.getViews());
            }
            convertView.setTag(holder);
        } else {
            holder = (RowHolder) convertView.getTag();
        }
        if (this.mFirstCellListener != null) {
            this.mFirstCellListener.populateFrom(row, column, convertView, parent, getCellObject(row, column), holder.getViews());
        }

        return convertView;
    }

    //  First Row (-1. 0...)
    @Override
    public View getHeader(int row, int column, View convertView, ViewGroup parent) {
        RowHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(this.mContext).inflate(getLayoutResource(row, column), parent, false);
            holder = new RowHolder(convertView, this.mFirstRowViews);
            if (this.mFirstRowCellListener != null) {
                this.mFirstRowCellListener.onRowCreate(row, column, convertView, parent, getCellObject(row, column), holder.getViews());
            }
            convertView.setTag(holder);
        } else {
            holder = (RowHolder) convertView.getTag();
        }
        if (this.mFirstRowCellListener != null) {
            this.mFirstRowCellListener.populateFrom(row, column, convertView, parent, getCellObject(row, column), holder.getViews());
        }

        return convertView;
    }

    @Override
    public int getViewTypeCount() {
        return 5;
    }

    @Override
    public int getLayoutResource(int row, int column) {
        final int layoutResource;
        switch (getItemViewType(row, column)) {
            case 0:
                layoutResource = mFirstCellLayout;
                break;
            case 1:
                layoutResource = mFirstRowLayout;
                break;
            case 2:
                layoutResource = mFirstColumnLayout;
                break;
            case 3:
                layoutResource = mBodyLayout;
                break;
            default:
                throw new RuntimeException("RuntimeException?");
        }
        return layoutResource;
    }


    @Override
    public int getRowCount() {
        return cellObject.size() - 1;
    }

    @Override
    public int getColumnCount() {
        if (cellObject.isEmpty()) {
            return 0;
        }
        if(mColumnCount == 0) {
            mColumnCount = cellObject.get(0).size() - 1;
        }

        return mColumnCount;
    }


    @Override
    public int getItemViewType(int row, int column) {
        final int itemViewType;
        if (row == -1 && column == -1) { // First Box
            itemViewType = 0;
        } else if (row == -1) { // First Header
            itemViewType = 1;
        } else if (column == -1) { // First Column
            itemViewType = 2;
        } else { // Body
            itemViewType = 3;
        }
        return itemViewType;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setHeaderHeight(int headerHeight) {
        this.headerHeight = headerHeight;
    }

    public void setFirstColumnWidth(int firstColumnWidth) {
        this.firstColumnWidth = firstColumnWidth;
    }

    public List<T> getRow(int rowPosition) {
        return cellObject.get(rowPosition);
    }

    public void removeRow(int rowPosition) {
        cellObject.remove(rowPosition);
    }

    public void removeSelectedRow(List<T> rowData) {
        cellObject.removeAll(rowData);
    }

    public void addRow(int rowPosition, List<T> rowData) {
        cellObject.add(rowPosition, rowData);
    }

    public void setColumnCount(int mColumnCount) {
        this.mColumnCount = mColumnCount;
    }

    public void clearAllRows() {
        cellObject.clear();

    }

    public interface IHeightChangeListener {
        boolean isHeightChangeRequired(int row);
        int getHeight(int row);
    }

    public IHeightChangeListener getHeightChangeListener() {
        return mHeightChangeListener;
    }

    public void setHeightChangeListener(IHeightChangeListener mHeightChangeListener) {
        this.mHeightChangeListener = mHeightChangeListener;
    }
}

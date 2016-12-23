package uiexamples.msf.com.uiandroidexamples.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

import uiexamples.msf.com.uiandroidexamples.R;


/**
 * Created by muthuv on 12/23/2016.
 */

public class HeaderListViewAdapter extends BaseAdapter  {

    private static final int TYPE_ITEM = 0;
    private static final int TYPE_SEPARATOR = 1;
    private static final int TYPE_MAX_COUNT = TYPE_SEPARATOR + 1;

    private List<String> mData = new ArrayList<>();
    private LayoutInflater mInflater;
    private List<String> mDataForDetail = new ArrayList<>();

    private TreeSet<Integer> mSeparatorsSet = new TreeSet<>();
    private Context mContext;

    public HeaderListViewAdapter(Context mcontext) {
        mInflater = (LayoutInflater) mcontext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.mContext = mcontext;
    }

    public void clearAllItems() {
        mData.clear();
        mSeparatorsSet.clear();
    }

    public void addItem(final String item) {
        mData.add(item);
        notifyDataSetChanged();
    }

    public void addItemForDetail(final ArrayList<String> item) {
        mDataForDetail.addAll(item);
    }


    public void addSeparatorItem(final List<String> item) {
        mData.addAll(item);
        // save separator position
        mSeparatorsSet.add(mData.size() - 1);
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        return mSeparatorsSet.contains(position) ? TYPE_SEPARATOR : TYPE_ITEM;
    }

    @Override
    public int getViewTypeCount() {
        return TYPE_MAX_COUNT;
    }

    public int getCount() {
        return mData.size();
    }


    @Override
    public List<String> getItem(int position) {
        return mData;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Integer positionvalue = new Integer(position);

        ViewHolder holder = null;
        int type = getItemViewType(position);
        if (convertView == null) {
            holder = new ViewHolder();
            switch (type) {
                case TYPE_SEPARATOR:
                    convertView = mInflater.inflate(R.layout.section_header, parent, false);
                    holder.textViewHeaderName = (TextView) convertView.findViewById(R.id.textViewHeaderName);

                    break;
                case TYPE_ITEM:
                    convertView = mInflater.inflate(R.layout.header_section, parent, false);
                    holder.textViewSymbolName = (TextView) convertView.findViewById(R.id.textViewSymbolName);


                    break;
            }
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        switch (type) {
            case TYPE_SEPARATOR:
                holder.textViewHeaderName.setText(mData.get(position));

                break;
            case TYPE_ITEM:
                holder.textViewSymbolName.setText(mData.get(position));
                break;
        }


        return convertView;
    }



    public static class ViewHolder {

        //Section Header
        TextView textViewHeaderName;

        //Item Vlaue
        TextView textViewSymbolName;

    }

}



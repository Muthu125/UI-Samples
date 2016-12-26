package com.inqbarna.tablefixheaders.adapters;

/**
 * Created by mallikarjuna on 14/6/16.
 */
import android.view.View;

public class RowHolder {
    private View[] views;

    public RowHolder(View row, int[] viewIds) {
        if(row != null && viewIds != null) {
            this.views = new View[viewIds.length];

            for(int i = 0; i < viewIds.length; ++i) {
                this.views[i] = row.findViewById(viewIds[i]);
            }
        }

    }

    public View[] getViews() {
        return this.views;
    }
}

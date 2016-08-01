package com.binvshe.binvshe.adapter.myselfadapter;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.View;

/**
 * Created by chenjy on 2015/11/24.
 */
public abstract class ChenRecyclerBaseAdapter<T extends ViewHolder> extends RecyclerView.Adapter<ViewHolder> {

    private OnRecyclerItemClickListener l = null;

    public static interface OnRecyclerItemClickListener {
        void onItemClick(ViewHolder viewHolder, int position);
    }

    public void setOnRecyclerItemClickListener (OnRecyclerItemClickListener l){
        this.l = l;
    }

    private OnRecyclerItemLongClickListener ll = null;

    public static interface OnRecyclerItemLongClickListener {
        void onItemLongClick(ViewHolder viewHolder, int position);
    }

    public void setOnRecyclerItemLongClickListener (OnRecyclerItemLongClickListener ll){
        this.ll = ll;
    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, final int position) {

        myBindViewHolder(viewHolder, position);
        if (l != null) {
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    l.onItemClick(viewHolder, position);
                }
            });
        }
        if (ll != null) {
            viewHolder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    ll.onItemLongClick(viewHolder, position);
                    return true;
                }
            });
        }
    }

    public abstract void myBindViewHolder(ViewHolder viewHolder, int position);

}

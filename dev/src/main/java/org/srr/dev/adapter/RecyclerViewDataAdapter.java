package org.srr.dev.adapter;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

import org.srr.dev.util.Loger;

import java.lang.reflect.Array;
import java.util.ArrayList;

public abstract class RecyclerViewDataAdapter<E,T extends ViewHolder> extends
        RecyclerView.Adapter<ViewHolder> {

    private ArrayList<E> mData;
    private OnItemClickLitener l;

    public void setOnItemClickLitener(OnItemClickLitener l) {
        this.l = l;
    }

    public interface OnItemClickLitener {
        void onItemClick(View views, int position);

        boolean onItemLongClick(View views, int position);
    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, final int i) {

        E e = mData.get(i);
        T t = (T) viewHolder;
        onBindHolder(t, i, e);
        if (l != null) {
            viewHolder.itemView.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    l.onItemClick(viewHolder.itemView, i);
                }
            });
            viewHolder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    boolean isclick = l.onItemLongClick(viewHolder.itemView, i);
                    return isclick;
                }
            });

        }
    }

    @Override
    public int getItemCount() {
        int size = mData == null ? 0 : mData.size();
        Loger.i("vicky", "size=" + size);
        return size;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v;
        T viewHolder;
        int layoutId = getLayoutId(viewType);
        v = LayoutInflater.from(parent.getContext()).inflate(
                layoutId, parent, false);
        viewHolder = getViewHolder(v);
        return viewHolder;
    }

    public abstract void onBindHolder(T viewHolder, int i, E e);

    public abstract T getViewHolder(View view);

    public abstract int getLayoutId(int viewType);

    public ArrayList<E> getData() {
        return mData;
    }

    public void setData(ArrayList<E> mData) {
        this.mData = mData;
    }
}

package com.example.wenkun.finalproject;

/**
 * Created by ASUS on 2018/1/8.
 */

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ASUS on 2017/12/14.
 */

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.SampleViewHolder> {
    private ArrayList<Essay> list;
    private NoteAdapter.OnItemClickListener onItemClickListener;
    private NoteAdapter.OnItemLongClickListener onItemLongClickListener;
    public static interface OnItemClickListener {
        void onItemClick(View view, int position);
    }
    public static interface OnItemLongClickListener {
        void onItemLongClick(View view, int position);
    }
    public void addData(List<Essay> result) {
        //Log.e("ReposAdapter","addData="+repos.toString());
        for (int i = 0; i < result.size(); i++) {
            list.add(result.get(i));
        }
        notifyDataSetChanged();
    }
    @Override
    public NoteAdapter.SampleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.note_view, parent, false);
        NoteAdapter.SampleViewHolder sampleViewHolder = new NoteAdapter.SampleViewHolder(view, onItemClickListener, onItemLongClickListener);
        return sampleViewHolder;
    }
    @Override
    public void onBindViewHolder(NoteAdapter.SampleViewHolder holder, int position) {
        Essay essay = list.get(position);
        holder.title.setText(essay.getTitle());
        holder.date.setText(essay.getTime());
        holder.content.setText(essay.getDetails());
    }
    @Override
    public int getItemCount() {
        return list.size();
    }

    class SampleViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
        TextView title;
        TextView date;
        TextView content;
        NoteAdapter.OnItemClickListener onItemClickListener;
        NoteAdapter.OnItemLongClickListener onItemLongClickListener;
        public SampleViewHolder(View view, NoteAdapter.OnItemClickListener onItemClickListener, NoteAdapter.OnItemLongClickListener onItemLongClickListener) {
            super(view);
            title = view.findViewById(R.id.title);
            date = view.findViewById(R.id.date);
            content = view.findViewById(R.id.content);
            this.onItemClickListener = onItemClickListener;
            this.onItemLongClickListener = onItemLongClickListener;
            view.setOnClickListener(this);
            view.setOnLongClickListener(this);
        }
        @Override
        public void onClick(View view) {
            if (onItemClickListener != null) {
                onItemClickListener.onItemClick(view, getPosition());
            }
        }
        @Override
        public boolean onLongClick(View view) {
            if (onItemLongClickListener != null) {
                onItemLongClickListener.onItemLongClick(view, getPosition());
                return true;
            }
            return false;
        }
    }
    public NoteAdapter(ArrayList<Essay> list) {
        this.list = list;
    }
    public void setOnItemClickListener(NoteAdapter.OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
    public void setOnItemLongClickListener(NoteAdapter.OnItemLongClickListener onItemLongClickListener) {
        this.onItemLongClickListener = onItemLongClickListener;
    }
}


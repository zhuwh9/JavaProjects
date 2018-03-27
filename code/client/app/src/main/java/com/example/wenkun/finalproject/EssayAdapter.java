package com.example.wenkun.finalproject;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Wenkun on 2017/12/27.
 */

public class EssayAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private OnItemClickListener mOnItemClickListener;
    private List<Essay> list;
    private Context context;
    private LayoutInflater inflater;

    public EssayAdapter(Context context, List<Essay> list) {
        this.context = context;
        this.list = list;
        inflater = LayoutInflater.from(context);
    }


    public interface OnItemClickListener{
        void onClick(int position);
        void onLongClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener=onItemClickListener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.essay_item,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        MyViewHolder holder1 = (MyViewHolder) holder;
        Essay itemEntity = list.get(position);

        holder1.title.setText(itemEntity.getTitle());
        holder1.time.setText(itemEntity.getTime());
        holder1.details.setText(itemEntity.getDetails());

        if(mOnItemClickListener!=null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnItemClickListener.onClick(holder.getAdapterPosition());
                }
            });
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener(){
                @Override
                public boolean onLongClick(View v) {
                    mOnItemClickListener.onLongClick(holder.getAdapterPosition());
                    return false;
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public Essay getItemData(int position) {
        return list.get(position);
    }

    public void setItemData(int position, Essay e) {
        list.set(position, e);
        notifyDataSetChanged();
    }

    public void addData(Essay data) {
        list.add(0, data);
        notifyItemInserted(0);
        notifyItemRangeChanged(0, list.size());
        notifyDataSetChanged();
    }
    public void removeData( int position) {
        list.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, list.size());
        notifyDataSetChanged();
    }
    private class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView title;
        private TextView time;
        private TextView details;

        public MyViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.title);

            time = (TextView) itemView.findViewById(R.id.time);
            details=(TextView) itemView.findViewById(R.id.details);
        }
    }
}

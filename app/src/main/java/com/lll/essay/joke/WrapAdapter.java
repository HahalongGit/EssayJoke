package com.lll.essay.joke;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;

/**
 * Created by longlong on 2018/1/17.
 *
 * @ClassName: WrapAdapter
 * @Description:
 * @Date 2018/1/17
 */

public class WrapAdapter extends RecyclerView.Adapter {

    private Context context;

    public WrapAdapter(Context context) {
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        WrapViewHolder viewHolder = new WrapViewHolder(LayoutInflater.from(context).inflate(R.layout.recycle_adapter_item_layout,parent,false));
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 5;
    }

    class WrapViewHolder extends RecyclerView.ViewHolder{

        public WrapViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }

}

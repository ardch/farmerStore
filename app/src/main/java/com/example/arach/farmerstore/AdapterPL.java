package com.example.arach.farmerstore;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class AdapterPL extends BaseAdapter {
    ArrayList<String> name;
    Context mContext;
    ArrayList<Integer> productSell;

    public AdapterPL(Context context, ArrayList<String> name, ArrayList<Integer> productSell) {
        this.name = name;
        this.productSell = productSell;
        this.mContext = context;
    }

    @Override
    public int getCount() {
        return name.size();
    }

    @Override
    public String getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        LayoutInflater mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (view != null) {
            holder = (ViewHolder) view.getTag();
        } else {
            view = mInflater.inflate(R.layout.listviewproduct, viewGroup, false);
            holder = new ViewHolder();
            view.setTag(holder);
        }
        holder.tv_info = (TextView) view.findViewById(R.id.productName);
        holder.tv_info.setText(name.get(i));
        holder.tv_info = (TextView) view.findViewById(R.id.productSell);
        holder.tv_info.setText(productSell.get(i).toString());
        return view;
    }
}

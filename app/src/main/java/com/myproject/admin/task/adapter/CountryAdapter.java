package com.myproject.admin.task.adapter;


import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.myproject.admin.task.R;
import com.myproject.admin.task.modal.CountryModal;

import java.util.List;

public class CountryAdapter extends RecyclerView.Adapter<CountryAdapter.MyViewHolder> {
    private List<CountryModal> countryModalList;

    private final OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(CountryModal item);
    }

    public CountryAdapter(List<CountryModal> countryModalList, OnItemClickListener listener) {
        this.countryModalList = countryModalList;
        this.listener = listener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.view_country, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.bind(countryModalList.get(position), listener);

    }

    @Override
    public int getItemCount() {
        return countryModalList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
         TextView countryName;
         LinearLayout linearLayout;


        public MyViewHolder(View view) {
            super(view);
            countryName = (TextView) view.findViewById(R.id.country_name);
            linearLayout = view.findViewById(R.id.main_layout);
        }

        public void bind(final CountryModal item, final OnItemClickListener listener) {
            countryName.setText(item.getName());
            linearLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(item);
                }
            });
        }
    }


}

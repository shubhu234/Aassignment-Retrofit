package com.example.retrofitimplementation.;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.example.countriesretrofit.R;

import java.util.ArrayList;

public class CountryAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private ArrayList<Country> countryArrayList;
    public CountryAdapter(final ArrayList<Country> countries){
        this.countryArrayList=countries;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_layout_adapter_country,viewGroup,false);
        StudentViewHolder studentViewHolder = new StudentViewHolder(view)
                ; return studentViewHolder;
    }



     @Override
     public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

     Country std=countryArrayList.get(i);
     String title=std.getmName();
     ((StudentViewHolder) viewHolder).mTextViewName.setText(title);

     }


     @Override
     public int getItemCount() {

     return countryArrayList.size();
     }

     /*
      * inner class for viewHolder
      * used set id to textview and also used set Onclicklistener
     */

    class StudentViewHolder extends RecyclerView.ViewHolder{
        private TextView mTextViewName;
        RelativeLayout relativeLayout;
        public StudentViewHolder(@NonNull View itemView) {
            super(itemView);


            mTextViewName=itemView.findViewById(R.id.textName);

        }
    }
}
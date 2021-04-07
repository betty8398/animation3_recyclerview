package com.example.recyclerview2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;


public class CardViewAdapter extends RecyclerView.Adapter<CardViewAdapter.viewHolder> {

    private final List<String> myListString;
    private final LayoutInflater myLayoutInflater;
    private final Context myContext;


    public CardViewAdapter(Context context, List<String> listString){
        myContext = context;
        myListString = listString;
        myLayoutInflater = LayoutInflater.from(context);
    };

    @NonNull
    @Override
    public com.example.recyclerview2.CardViewAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = myLayoutInflater.inflate(R.layout.recyclerview_item, parent, false);
        viewHolder holder = new viewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull com.example.recyclerview2.CardViewAdapter.viewHolder holder, int position) {
        holder.textView_dataID.setText(myListString.get(position));
        holder.imageView_dataID.setImageResource(R.drawable.cat_1);
    }

    @Override
    public int getItemCount() {
        return myListString.size();
    };


    public class viewHolder extends RecyclerView.ViewHolder{
        private final ImageView imageView_dataID;
        private final TextView textView_dataID;

        public viewHolder(@NonNull View itemView) {
            super(itemView);
            imageView_dataID = (ImageView) itemView.findViewById(R.id.imageView);
            textView_dataID = (TextView) itemView.findViewById(R.id.textView);
        }
    }
}

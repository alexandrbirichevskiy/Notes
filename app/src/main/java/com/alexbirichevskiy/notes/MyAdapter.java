package com.alexbirichevskiy.notes;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private Notes[] notes;

    MyAdapter(Notes[] notes) {
        this.notes = notes;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter.MyViewHolder holder, int position) {
        holder.bind(notes[position].getName());
    }

    @Override
    public int getItemCount() {
        return notes.length;
    }

    static class MyViewHolder extends RecyclerView.ViewHolder{

        private TextView textView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.text_view_items);
        }

        void bind(String text) {
            textView.setText(text);
        }
    }
}

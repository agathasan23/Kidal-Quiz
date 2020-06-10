package com.example.kidal.rangking;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.kidal.R;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class adapter extends RecyclerView.Adapter<adapter.ViewHolder> {

    List<model> data;
    Context context;

    public adapter(List<model> data, Context applicationContext) {
        this.data = data;
        this.context = applicationContext;
    }

    @NonNull
    @Override
    public adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull adapter.ViewHolder holder, int position) {
    holder.nama.setText(data.get(position).getNama());
    holder.score.setText(String.valueOf(data.get(position).getScore()));
    holder.nomer.setText(data.get(position).getId());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView nomer;
        TextView nama;
        TextView score;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nomer = (TextView)itemView.findViewById(R.id.nomer);
            nama = (TextView)itemView.findViewById(R.id.nama);
            score = (TextView)itemView.findViewById(R.id.score);


        }
    }
}

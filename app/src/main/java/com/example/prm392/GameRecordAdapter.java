package com.example.prm392;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class GameRecordAdapter extends RecyclerView.Adapter<GameRecordViewHolder> {
    private List<GameRecord> gameRecordList;

    public GameRecordAdapter(List<GameRecord> gameRecordList) {
        super();
        this.gameRecordList=gameRecordList;
    }

    @NonNull
    @Override
    public GameRecordViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.record_layout, parent, false);
        return new GameRecordViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull GameRecordViewHolder holder, int position) {
        GameRecord gameRecord =gameRecordList.get(position);
        holder.bind(gameRecord);
    }

    @Override
    public int getItemCount() {
        return gameRecordList.size();
    }
}

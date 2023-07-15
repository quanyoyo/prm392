package com.example.prm392;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class GameRecordViewHolder extends RecyclerView.ViewHolder {
    private TextView tv_record_id;
    private TextView tv_record_des;
    public GameRecordViewHolder(@NonNull View itemView) {
        super(itemView);
        tv_record_id =itemView.findViewById(R.id.tv_record_id);
        tv_record_des =itemView.findViewById(R.id.tv_record_des);
    }
    public void bind(GameRecord gameRecord){
        tv_record_id.setText(String.valueOf(gameRecord.getId()));
        tv_record_des.setText(gameRecord.getDes());
    }
}

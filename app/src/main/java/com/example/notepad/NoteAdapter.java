package com.example.notepad;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteViewHolder> {
    Context context;
    List<Notepad> list;

    public NoteAdapter(Context context, ArrayList<Notepad> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.notepad_cell, parent, false);
        return new NoteViewHolder(view);


    }

    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder holder, int position) {
        final Notepad notepad = list.get(position);
        holder.title.setText(notepad.getTitle());
        holder.text.setText(notepad.getText());

        holder.mbtn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               listener.update(notepad);

            }

       });


        holder.mbtn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.delete(notepad);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class NoteViewHolder extends RecyclerView.ViewHolder {
        TextView title, text;
        Button mbtn_update, mbtn_delete;

        public NoteViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.tv_title);
            text = itemView.findViewById(R.id.tv_text);
            mbtn_update = itemView.findViewById(R.id.btn_update);
            mbtn_delete = itemView.findViewById(R.id.btn_delete);

        }
    }

    OnItemClickInterface listener;

    public void setListener(OnItemClickInterface listener) {
        this.listener = listener;

    }

    public interface OnItemClickInterface {
        void update(Notepad notepad);

        void delete(Notepad notepad);

    }
}


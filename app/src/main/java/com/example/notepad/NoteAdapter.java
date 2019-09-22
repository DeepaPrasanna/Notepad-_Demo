package com.example.notepad;

import android.content.Context;
import android.util.Log;
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
    private static final String TAG = NoteAdapter.class.getSimpleName();

    private Context context;
    List<Notepad> list;
    OnItemClickInterface listener;

    public NoteAdapter(Context context, ArrayList<Notepad> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.notepad_cell, parent, false);
        return (new NoteViewHolder(view));


    }

    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder holder, int position) {
        final Notepad notepad = list.get(position);

        Log.i(TAG, "onBindViewHolder: Title ::: " + notepad.title);
        Log.i(TAG, "onBindViewHolder: Desc ::: " + notepad.text);

        holder.title.setText(notepad.title);
        holder.desc.setText(notepad.text);

        holder.mbtn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.OnUpdate(notepad.id, notepad.title, notepad.text);
            }

        });


        holder.mbtn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.OnDelete(notepad.id);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class NoteViewHolder extends RecyclerView.ViewHolder {
        TextView title, desc;
        Button mbtn_update, mbtn_delete;

        public NoteViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.tv_title);
            desc = itemView.findViewById(R.id.tv_desc);
            mbtn_update = itemView.findViewById(R.id.btn_update);
            mbtn_delete = itemView.findViewById(R.id.btn_delete);

        }
    }


    public void setListener(OnItemClickInterface listener) {
        this.listener = listener;

    }

    public interface OnItemClickInterface {
        void OnUpdate(String id, String title, String text);

        void OnDelete(String id);

    }
}


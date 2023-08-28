package com.example.noterecorder;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.noterecorder.databinding.RecyclerRowBinding;

import java.util.ArrayList;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.NotesHolder> {

    ArrayList<NotesInfo> notesInfoArrayList;

    public NotesAdapter(ArrayList<NotesInfo> notesInfoArrayList) {
        this.notesInfoArrayList = notesInfoArrayList;
    }

    @NonNull
    @Override
    public NotesHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerRowBinding recyclerRowBinding = RecyclerRowBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new NotesHolder(recyclerRowBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull NotesHolder holder, @SuppressLint("RecyclerView") int position) {

        holder.binding.recyclerViewTextView.setText(notesInfoArrayList.get(position).name);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(holder.itemView.getContext(), AddPage.class);
                intent.putExtra("info", "old");
                intent.putExtra("noteId", notesInfoArrayList.get(position).id);
                holder.itemView.getContext().startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return  notesInfoArrayList.size();
    }

    public class NotesHolder extends RecyclerView.ViewHolder {

        private RecyclerRowBinding binding;

        public NotesHolder(RecyclerRowBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

}

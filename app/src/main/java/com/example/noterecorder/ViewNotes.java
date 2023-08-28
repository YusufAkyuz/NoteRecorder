package com.example.noterecorder;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;

import com.example.noterecorder.databinding.ActivityMainBinding;
import com.example.noterecorder.databinding.ActivityViewNotesBinding;

import java.util.ArrayList;

public class ViewNotes extends AppCompatActivity {

    private ActivityViewNotesBinding binding;

    ArrayList<NotesInfo> notesInfoArrayList ;

    NotesAdapter notesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityViewNotesBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        notesInfoArrayList = new ArrayList<>();

        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        notesAdapter = new NotesAdapter(notesInfoArrayList);
        binding.recyclerView.setAdapter(notesAdapter);

        getData();

        Intent intent = getIntent();
    }

    private void getData() {
        try {

            SQLiteDatabase database = this.openOrCreateDatabase("Notes", MODE_PRIVATE, null);
            Cursor cursor = database.rawQuery("SELECT * FROM notes", null);

            int nameIndex = cursor.getColumnIndex("name");
            int idIndex = cursor.getColumnIndex("id");
            int turkceIndex = cursor.getColumnIndex("turkceNet");
            int matIndex = cursor.getColumnIndex("matNet");
            int fenIndex = cursor.getColumnIndex("fenNet");
            int sosIndex = cursor.getColumnIndex("sosNet");

            while (cursor.moveToNext()) {
                String name = cursor.getString(nameIndex);
                int id = cursor.getInt(idIndex);
                double turkceNet = cursor.getDouble(turkceIndex);
                double matNet = cursor.getDouble(matIndex);
                double fenNet = cursor.getDouble(fenIndex);
                double sosNet = cursor.getDouble(sosIndex);

                NotesInfo note = new NotesInfo(name, id, turkceNet, matNet, fenNet, sosNet);
                notesInfoArrayList.add(note);

            }

            notesAdapter.notifyDataSetChanged(); //recyclerView her veri eklendiğinde verilerin değiştiğini bildirmemiz gerekiyor bunu da bu şekilde yapabiliriz

            cursor.close();

        }catch (Exception e) {
            e.printStackTrace();
        }

    }
}
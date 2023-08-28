package com.example.noterecorder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.noterecorder.databinding.ActivityAddPageBinding;

import java.util.ArrayList;

public class AddPage extends AppCompatActivity {

    ArrayList<NotesInfo> infos;

    SQLiteDatabase database;

    private ActivityAddPageBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddPageBinding.inflate(getLayoutInflater());
        View view =binding.getRoot();
        setContentView(view);

        database =this.openOrCreateDatabase("Notes", MODE_PRIVATE, null);

        Intent intent = getIntent();
        String info =intent.getStringExtra("info");

        if (info.equals("new")) {
            binding.nameText.setText("");
            binding.matemetikNet.setText("");
            binding.fenNet.setText("");
            binding.sosyalNet.setText("");
        }else {
            int noteId =intent.getIntExtra("noteId", 1);
            binding.save.setVisibility(View.INVISIBLE);

            try {

                Cursor cursor = database.rawQuery("SELECT * FROM notes WHERE id = ?", new String[]{String.valueOf(noteId)});

                int noteNameIndex = cursor.getColumnIndex("name");
                int turkceIndex = cursor.getColumnIndex("turkceNet");
                int matNetIndex = cursor.getColumnIndex("matNet");
                int fenNetIndex = cursor.getColumnIndex("fenNet");
                int sosNetIndex = cursor.getColumnIndex("sosNet");

                while (cursor.moveToNext()) {
                    binding.nameText.setText(cursor.getString(noteNameIndex));
                    binding.turkceNet.setText(cursor.getString(turkceIndex));
                    binding.matemetikNet.setText(cursor.getString(matNetIndex));
                    binding.fenNet.setText(cursor.getString(fenNetIndex));
                    binding.sosyalNet.setText(cursor.getString(sosNetIndex));


                }

                cursor.close();


            }catch (Exception e) {
                e.printStackTrace();
            }
            }
        }


    public void save(View view) {
        String name = binding.nameText.getText().toString();
        double turkceNet = Double.parseDouble(binding.turkceNet.getText().toString());
        double matNet = Double.parseDouble(binding.matemetikNet.getText().toString());
        double fenNet = Double.parseDouble(binding.fenNet.getText().toString());
        double sosNet = Double.parseDouble(binding.sosyalNet.getText().toString());

        try {
            database.execSQL("CREATE TABLE IF NOT EXISTS notes(id INTEGER PRIMARY KEY, name VARCHAR, turkceNet VARCHAR, matNet VARCHAR, fenNet VARCHAR, sosNet VARCHAR)");

            String sqlString = "INSERT INTO notes (name, turkceNet, matNet, fenNet, sosNet) VALUES (?,?,?,?,?)";
            SQLiteStatement sqLiteStatement =database.compileStatement(sqlString);

            sqLiteStatement.bindString(1, name);
            sqLiteStatement.bindString(2, String.valueOf(turkceNet));
            sqLiteStatement.bindString(3, String.valueOf(matNet));
            sqLiteStatement.bindString(4, String.valueOf(fenNet));
            sqLiteStatement.bindString(5, String.valueOf(sosNet));

            sqLiteStatement.execute();

            Toast.makeText(this, "Sonuç Başarıyla Kaydedildi", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);



        }catch (Exception e) {
            e.printStackTrace();
        }
    }

}
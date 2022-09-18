package net.ariflaksito.mystudents;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import net.ariflaksito.mystudents.adapter.StudentAdapter;
import net.ariflaksito.mystudents.db.DbHelper;
import net.ariflaksito.mystudents.model.Chasflow;


import java.util.ArrayList;

public class ListStudentsActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private StudentAdapter adapter;
    private ArrayList<Chasflow> studentsArrayList;
    private DbHelper dbHelper;

    private Button keluar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_students);

        recyclerView = (RecyclerView) findViewById(R.id.rview);
        adapter = new StudentAdapter(this);

        dbHelper = new DbHelper(this);
        studentsArrayList = dbHelper.getAllChasflow();
        adapter.setListStudents(studentsArrayList);

        keluar = findViewById(R.id.kembalibtn);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(ListStudentsActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        keluar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ListStudentsActivity.this, BerandaActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        studentsArrayList = dbHelper.getAllChasflow();
        adapter.setListStudents(studentsArrayList);
        adapter.notifyDataSetChanged();
    }
}
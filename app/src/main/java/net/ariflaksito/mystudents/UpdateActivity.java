package net.ariflaksito.mystudents;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import net.ariflaksito.mystudents.db.DbHelper;
import net.ariflaksito.mystudents.model.Chasflow;


public class UpdateActivity extends AppCompatActivity {

    private DbHelper dbHelper;
    private EditText etKeterangan, etNominal , etTanggal;
    private Button btnSave, keluar;
    private Chasflow student;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        dbHelper = new DbHelper(this);

        etKeterangan = findViewById(R.id.edt_name);
        etNominal = findViewById(R.id.edt_nim);
        etTanggal = findViewById(R.id.edt_tanggal);
        btnSave = findViewById(R.id.btn_submit);
        keluar = findViewById(R.id.kembalibtn);

        Intent intent = getIntent();
        student = (Chasflow) intent.getSerializableExtra("user");

        etKeterangan.setText(student.getKeterangan());
        etNominal.setText(student.getNominal());
        etTanggal.setText(student.getTanggal());


        btnSave.setOnClickListener((View v) -> {
            dbHelper.updateChasflow(student.getId(), etNominal.getText().toString(), etKeterangan.getText().toString(), etTanggal.getText().toString());
            Toast.makeText(UpdateActivity.this, "Updated berhasil!", Toast.LENGTH_SHORT).show();
            finish();
        });

        keluar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UpdateActivity.this, BerandaActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
package com.vsga.validasiloginp2;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Objects;

public class RegisterActivity extends AppCompatActivity {
    EditText editUsername, editPassword, editEmail, editNamaLengkap,
            editAsalSekolah, editAlamat;
    Button btnSimpan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        getSupportActionBar().setTitle("Register");
        editUsername = findViewById(R.id.editUsername);
        editPassword = findViewById(R.id.editPassword);
        editEmail = findViewById(R.id.input_email);
        editAsalSekolah = findViewById(R.id.input_school);
        editAlamat = findViewById(R.id.input_address);
        editNamaLengkap = findViewById(R.id.input_full_name);
        btnSimpan = findViewById(R.id.button_save);
        btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isValidation()) {
                    simpanFileData();
                } else {
                    Toast.makeText(RegisterActivity.this, "Lengkapi seluruh data.",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    boolean isValidation() {
        if (editUsername.getText().toString().equals("") ||
                editPassword.getText().toString().equals("") ||
                editEmail.getText().toString().equals("") ||
                editNamaLengkap.getText().toString().equals("") ||
                editAsalSekolah.getText().toString().equals("") ||
                editAlamat.getText().toString().equals("")) {
            return false;
        } else {
            return true;
        }
    }

    void simpanFileData() {
        String isiFile = editUsername.getText().toString() + ";" +
                editPassword.getText().toString() + ";" +
                editEmail.getText().toString() + ";" +
                editNamaLengkap.getText().toString() + ";" +
                editAsalSekolah.getText().toString() + ";" +
                editAlamat.getText().toString();
        File file = new File(getFilesDir(),
                editUsername.getText().toString());

        FileOutputStream outputStream = null;
        try {
            file.createNewFile();
            outputStream = new FileOutputStream(file, false);
            outputStream.write(isiFile.getBytes());
            outputStream.flush();
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Toast.makeText(this, "Register Berhasil",
                Toast.LENGTH_SHORT).show();
        onBackPressed();
    }
}


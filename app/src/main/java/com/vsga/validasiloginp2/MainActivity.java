package com.vsga.validasiloginp2;


import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    EditText editUserName, editPassword, editEmail, editNamaLengkap,
    editAsalSekolah, editAlamat;
    Button btnSimpan;
    TextView textViewPassword;

    public static final String FILENAME = "login";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        Objects.requireNonNull(getSupportActionBar()).setTitle("Halaman Depan");

        editUserName = findViewById(R.id.editUsername);
        textViewPassword = findViewById(R.id.label_password);
        editPassword = findViewById(R.id.input_password);
        editEmail = findViewById(R.id.input_email);
        editNamaLengkap = findViewById(R.id.input_full_name);
        editAsalSekolah = findViewById(R.id.input_school);
        editAlamat = findViewById(R.id.input_address);
        btnSimpan = findViewById(R.id.button_save);

        btnSimpan.setVisibility(View.GONE);
        editUserName.setEnabled(false);
        editPassword.setVisibility(View.GONE);
        textViewPassword.setVisibility(View.GONE);
        editEmail.setEnabled(false);
        editNamaLengkap.setEnabled(false);
        editAsalSekolah.setEnabled(false);
        editAlamat.setEnabled(false);

        bacaFileLogin();
    }

    private void setSupportActionBar(Toolbar toolbar) {
    }

    void bacaFileLogin() {
        File sdCard = getFilesDir();
        File file = new File (sdCard, FILENAME);
        if (file.exists()) {
            StringBuilder text = new StringBuilder();
            try {
                BufferedReader br =
                        new BufferedReader(new FileReader(file));
                String line = br.readLine();
                while (line != null) {
                    text.append(line);
                    line= br.readLine();
                }
                br.close();
            } catch (IOException e) {
                System.out.println("Error" + e.getMessage());
            }
            String data = text.toString();
            String[] dataUser = data.split(";");
            bacaDataUser (dataUser[0]);

        }
    }
    void bacaDataUser(String fileName){
        File sdcard = getFilesDir();
        File file = new File(sdcard, fileName);
        if (file.exists()) {
            StringBuilder text = new StringBuilder();
            try {
                BufferedReader br =
                        new BufferedReader(new FileReader(file));
                String line = br.readLine();
                while (line != null) {
                    text.append(line);
                    line = br.readLine();
                }
                br.close();
            } catch (IOException e){
                System.out.println("Error" + e.getMessage());
            }
            String data = text.toString();
            String [] dataUser = data.split(";");

            editUserName.setText(dataUser[0]);
            editEmail.setText(dataUser[2]);
            editNamaLengkap.setText(dataUser[3]);
            editAsalSekolah.setText(dataUser[4]);
            editAlamat.setText(dataUser[5]);

        }else{
            Toast.makeText(this, "Username salah",
                    Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_logout:
                tampilkanDialogKonfirmasiLogout();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    void hapusFile() {
        File file = new  File(getFilesDir(), FILENAME);
        if (file.exists()) {
            file.delete();
        }
    }
    void tampilkanDialogKonfirmasiLogout() {
        new AlertDialog.Builder(this)
                .setTitle("Logout")
                .setMessage("Mau keluar?")
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setPositiveButton(android.R.string.yes, new
                        DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogInterface, int
                            whichButton){
                        hapusFile();
                        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                        startActivity(intent);
                        finish();
                    }
                        })
                .setNegativeButton(android.R.string.no, null).show();
    }
}

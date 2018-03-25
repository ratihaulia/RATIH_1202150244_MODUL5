package com.example.brahmantyo.ratih_1202150244_modul5;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.TextView;

public class Settings extends AppCompatActivity {
    //mendeklarasikan variabel yang akan digunakan
    private TextView warna;
    int colorid;
    AlertDialog.Builder alert;
    SharedPreferences.Editor sharedpref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        //membuat alert dialog
        alert = new AlertDialog.Builder(this);

        //inisialisasi shared preference
        SharedPreferences sharedP = getApplicationContext().getSharedPreferences("Preferences", 0);
        sharedpref = sharedP.edit();
        colorid = sharedP.getInt("Colourground", R.color.white);
        //mengakses text view pada layout
        warna = findViewById(R.id.shapecolor);
        //set shape color dengan color id yang terpilih
        warna.setText(getShapeColor(colorid));
    }

    @Override
    public void onBackPressed() {
        //intent dari setting ke main activity
        Intent intent = new Intent(Settings.this, MainActivity.class);
        //memulai intent
        startActivity(intent);
        finish();
    }

    //method yang dijalankan ketika pilihan pada menu dipilih
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==android.R.id.home){
            //menjalankan method onbackpressed
            this.onBackPressed();
        }
        return true;
    }

    //string warna yang akan digunakan untuk mengubah warna
    public String getShapeColor(int i){
        if (i==R.color.red){
            return "Red";
        }else if (i==R.color.green){
            return "Green";
        }else if (i==R.color.blue){
            return "Blue";
        }else if (i==R.color.MerahMuda){
            return "MerahMuda";
        }else if (i==R.color.Orange){
            return "Orange";
        }else{
            return "Default";
        }
    }

    //mendapatkan id dari warna yang akan digunakan
    public int getColorid(int i){
        if (i==R.color.red){
            return R.id.red;
        }else if (i==R.color.green){
            return R.id.green;
        }else if (i==R.color.blue){
            return R.id.blue;
        }else if (i==R.color.MerahMuda){
            return R.id.MerahMuda;
        }else if (i==R.color.Orange){
            return R.id.Orange;
        }else{
            return R.id.white;
        }
    }

    public void pilihWarna(View view) {
        //set title dari alert dialog menjadi Shape Color
        alert.setTitle("Shape Color");
        //membuat view baru
        View view1 = getLayoutInflater().inflate(R.layout.setting_color, null);
        //menampilkan view yang telah dibuat sebelumnya
        alert.setView(view1);

        //mengakses radio group pada layout
        final RadioGroup radG = view1.findViewById(R.id.radio_color);
        radG.check(getColorid(colorid));

        //membuat alert dialog
        alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                //mendapatkan id radio button yang di pilih
                int a = radG.getCheckedRadioButtonId();
                switch (a){
                    case R.id.red:
                        colorid = R.color.red;
                        break;
                    case R.id.green:
                        colorid = R.color.green;
                        break;
                    case R.id.blue:
                        colorid = R.color.blue;
                        break;
                    case R.id.MerahMuda:
                        colorid = R.color.MerahMuda;
                        break;
                    case R.id.Orange:
                        colorid = R.color.Orange;
                        break;
                    case R.id.white:
                        colorid = R.color.white;
                        break;
                }
                //set shape color menjadi color id yang dipilih
                warna.setText(getShapeColor(colorid));
                //menaruh shared preference
                sharedpref.putInt("Colourground", colorid);
                //commit shared preference
                sharedpref.commit();
            }
        });

        //ketika menekan Cancel pada alert dialog
        alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        //membuat dan menampilkan alert dialog
        alert.create().show();
    }


}
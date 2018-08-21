package com.willicab.conversor;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.DecimalFormat;

/*
 * Copyright 2018 WilLiam Cabrera
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and
 * associated documentation files (the "Software"), to deal in the Software without restriction,
 * including without limitation the rights to use, copy, modify, merge, publish, distribute,
 * sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or
 * substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED,
 * INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE
 * AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM,
 * DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

public class MainActivity extends AppCompatActivity {

    EditText etBolivar, etBolivarFuerte, etBolivarSoberano;
    Button btnAcerca, btnClose;
    LinearLayout lyAcerca;
    TextView tvGithub, tvBlog, tvPaypal;
    double bolivar, bolivarFuerte, bolivarSoberano;
    DecimalFormat decimalFormatter;
    boolean editando = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etBolivar = findViewById(R.id.etBolivar);
        etBolivarFuerte = findViewById(R.id.etBolivarFuerte);
        etBolivarSoberano = findViewById(R.id.etBolivarSoberano);
        btnAcerca = findViewById(R.id.btnAcerca);
        btnClose = findViewById(R.id.btnClose);
        lyAcerca = findViewById(R.id.lyAcerca);
        tvGithub = findViewById(R.id.tvGithub);
        tvBlog = findViewById(R.id.tvBlog);
        tvPaypal = findViewById(R.id.tvPaypal);
        decimalFormatter = new DecimalFormat("#.##");
        decimalFormatter.setMinimumFractionDigits(2);
        decimalFormatter.setMaximumFractionDigits(2);

        btnAcerca.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lyAcerca.setVisibility(View.VISIBLE);
            }
        });

        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lyAcerca.setVisibility(View.GONE);
            }
        });

        tvBlog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse("https://blog.willicab.com.ve");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });

        tvGithub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse("https://github.com/willicab/Conversor");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });

        tvPaypal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse("https://www.paypal.me/willicab");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });

        etBolivar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (editando) return;
                editando = true;
                try {
                    bolivar = Double.parseDouble(String.valueOf(charSequence));
                    bolivarFuerte = (bolivar / 1000);
                    bolivarSoberano = (bolivarFuerte / 100000);
                    Log.d("etBolivar: ", decimalFormatter.format(bolivar) + ", " +
                            decimalFormatter.format(bolivarFuerte) + ", " +
                            decimalFormatter.format(bolivarSoberano));
                    etBolivarFuerte.setText(decimalFormatter.format(bolivarFuerte));
                    etBolivarSoberano.setText(decimalFormatter.format(bolivarSoberano));
                } catch (NumberFormatException e) {
                    Log.w("onTextChanged: ", e.getMessage());
                    etBolivar.setText("");
                    etBolivarFuerte.setText("");
                    etBolivarSoberano.setText("");
                }
                editando = false;
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        etBolivarFuerte.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (editando) return;
                editando = true;
                try {
                    bolivarFuerte = Double.parseDouble(String.valueOf(charSequence));
                    bolivar = (bolivarFuerte * 1000);
                    bolivarSoberano = (bolivarFuerte / 100000);
                    Log.d("etBolivarFuerte: ", bolivar + ", " + bolivarFuerte + ", " + bolivarSoberano);
                    etBolivar.setText(decimalFormatter.format(bolivar));
                    etBolivarSoberano.setText(decimalFormatter.format(bolivarSoberano));
                } catch (NumberFormatException e) {
                    Log.w("onTextChangedF: ", e.getMessage());
                    etBolivar.setText("");
                    etBolivarFuerte.setText("");
                    etBolivarSoberano.setText("");
                }
                editando = false;
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        etBolivarSoberano.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (editando) return;
                editando = true;
                try {
                    bolivarSoberano = Double.parseDouble(String.valueOf(charSequence));
                    bolivarFuerte = (bolivarSoberano * 100000);
                    bolivar = (bolivarFuerte * 1000);
                    Log.d("etBolivarSoberano: ", bolivar + ", " + bolivarFuerte + ", " + bolivarSoberano);
                    etBolivarFuerte.setText(decimalFormatter.format(bolivarFuerte));
                    etBolivar.setText(decimalFormatter.format(bolivar));
                } catch (NumberFormatException e) {
                    Log.w("onTextChangedS: ", e.getLocalizedMessage());
                    etBolivar.setText("");
                    etBolivarFuerte.setText("");
                    etBolivarSoberano.setText("");
                }
                editando = false;
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

    }
}

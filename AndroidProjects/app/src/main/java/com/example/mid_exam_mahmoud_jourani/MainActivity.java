package com.example.mid_exam_mahmoud_jourani;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Patterns;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    private EditText etEmail, etPassword;
    private Button btnLogin;
    private boolean backPressedOnce = false;
    private final Handler handler = new Handler(Looper.getMainLooper());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);
        btnLogin.setEnabled(false);

        TextWatcher watcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                validate();
            }

            @Override
            public void afterTextChanged(Editable s) { }
        };
        etEmail.addTextChangedListener(watcher);
        etPassword.addTextChangedListener(watcher);

        btnLogin.setOnClickListener(v ->
                startActivity(new Intent(MainActivity.this, ModeActivity.class)));

        getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                if (backPressedOnce) {
                    finish();
                    return;
                }
                backPressedOnce = true;
                Snackbar.make(findViewById(R.id.main), "Press back again to exit",
                        Snackbar.LENGTH_SHORT).show();
                handler.postDelayed(() -> backPressedOnce = false, 2000);
            }
        });
    }

    private void validate() {
        String email = etEmail.getText().toString().trim();
        String password = etPassword.getText().toString();
        btnLogin.setEnabled(Patterns.EMAIL_ADDRESS.matcher(email).matches()
                && password.length() >= 6);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacksAndMessages(null);
    }
}
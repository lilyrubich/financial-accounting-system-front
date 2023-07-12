package com.example.finaccsystem.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.finaccsystem.R;
import com.example.finaccsystem.data.ClientDataHolder;

import java.util.Base64;

public class LoginActivity extends AppCompatActivity {

    EditText userName;
    EditText password;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        userName = findViewById(R.id.userName);
        password = findViewById(R.id.password);
    }

    private static String getBasicAuthenticationHeader(String userName, String password) {
        String valueToEncode = userName + ":" + password;
        String authorizationHeader = "Basic " + Base64.getEncoder().encodeToString(valueToEncode.getBytes());
        ClientDataHolder.getInstance().setAuth(authorizationHeader);
        return authorizationHeader;
    }

    public void startMainActivity(View view) {
        getBasicAuthenticationHeader(userName.getText().toString(), password.getText().toString());
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

}

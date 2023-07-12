package com.example.finaccsystem.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.finaccsystem.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void startListOfNodesActivity(View view) {
        Intent intent = new Intent(this, ListOfNodesActivity.class);
        startActivity(intent);
    }

    public void startListOfTransactionsActivity(View view) {
        Intent intent = new Intent(this, ListOfTransactionsActivity.class);
        startActivity(intent);
    }
}
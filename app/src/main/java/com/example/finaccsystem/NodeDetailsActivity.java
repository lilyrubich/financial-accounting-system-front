package com.example.finaccsystem;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class NodeDetailsActivity extends AppCompatActivity {

    TextView txt_node_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.node_details);

    }

    public void initData(){

        txt_node_name=findViewById(R.id.txt_node_name);
    }

    private void getData(){
        Intent intent = new Intent();
    }
}

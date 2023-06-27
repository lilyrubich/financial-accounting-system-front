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
    Node node;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.node_details);
        txt_node_name = findViewById(R.id.txt_node_name);

        intent = getIntent();
        if (intent != null) {
            node = (Node) intent.getSerializableExtra("data");
            String nodeName = node.getName();
            txt_node_name.setText(nodeName);
        }
    }

    public void initData() {


    }

    private void getData() {
        Intent intent = new Intent();
    }
}

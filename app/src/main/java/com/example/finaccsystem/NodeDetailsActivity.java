package com.example.finaccsystem;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.finaccsystem.model.Node;

public class NodeDetailsActivity extends AppCompatActivity {

    private TextView node_name, amount, description;
    private Node node;
    private Intent intent;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.node_details);
        node_name = findViewById(R.id.txt_node_name);
        amount = findViewById(R.id.txt_amount);
        description = findViewById(R.id.txt_description);

        intent = getIntent();
        if (intent != null) {
            node = (Node) intent.getSerializableExtra("data");
            node_name.setText(node.getName());
            amount.setText(node.getAmount()+" "+node.getCurrency());
            description.setText(node.getDescription());

        }
    }

    public void initData() {
    }

    private void getData() {
        Intent intent = new Intent();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        final ImageView buttonImage;
        final TextView buttonView;

        public ViewHolder(View view) {
            super(view);
            buttonImage = view.findViewById(R.id.buttonImage);
            buttonView = view.findViewById(R.id.buttonName);
        }
    }

    public void startNewTransactionActivity(View view) {
        Intent intent = new Intent(this, TransactionActivity.class);
        startActivity(intent);
    }


}

package com.example.finaccsystem.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;

import androidx.appcompat.app.AppCompatActivity;

import com.example.finaccsystem.R;
import com.example.finaccsystem.enums.CurrencyEnum;
import com.example.finaccsystem.model.Node;
import com.example.finaccsystem.task.PostNodeTask;
import com.example.finaccsystem.transportObject.NodeTransportObject;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class NewNodeActivity extends AppCompatActivity {

    private EditText nodeName;
    private EditText description;
    private EditText amount;
    private Spinner currency;
    private Node newNode = new Node();
    private ArrayList<String> listOfCurrencies = CurrencyEnum.getAllCurrencySymbols();
    private Switch isExternal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_node);
        nodeName = findViewById(R.id.nodeName);
        description = findViewById(R.id.nodeDescription);
        amount = findViewById(R.id.nodeAmount);
        isExternal = findViewById(R.id.switchIsExternal);
        currency = findViewById(R.id.spinnerNodeCurrency);
        ArrayAdapter<String> adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, listOfCurrencies);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        currency.setAdapter(adapter);
        AdapterView.OnItemSelectedListener itemSelectedListener = new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long l) {
                String selectedCurency = (String) parent.getItemAtPosition(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                String selectedCurrency = CurrencyEnum.DOLLAR_USA.getSymbol();
                currency.setSelection(0);
            }
        };
        currency.setOnItemSelectedListener(itemSelectedListener);
    }

    private NodeTransportObject sendPostNode() throws IOException, ExecutionException, InterruptedException {
        // on below line creating a url to post the data.
        //URL url = new URL("http://192.168.1.7:8081");
        PostNodeTask task = new PostNodeTask();
        NodeTransportObject nto = task.execute(newNode).get();
        return nto;
    }

    public void executeAddNode(View view) throws IOException, ExecutionException, InterruptedException {
        newNode.setName(nodeName.getText().toString());
        newNode.setDescription(description.getText().toString());
        newNode.setAmount(new BigDecimal(amount.getText().toString()));
        newNode.setExternal(isExternal.isChecked());
        newNode.setCurrency(currency.getSelectedItem().toString());

        NodeTransportObject executePostRequest = sendPostNode();
        if (executePostRequest != null) {
            Intent intent = new Intent(this, StatusOfTransactionActivity.class);
            startActivity(intent);
        }
    }
}

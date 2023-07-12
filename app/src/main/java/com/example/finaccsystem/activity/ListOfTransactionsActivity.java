package com.example.finaccsystem.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.finaccsystem.R;
import com.example.finaccsystem.adapter.TransactionAdapter;
import com.example.finaccsystem.model.Node;
import com.example.finaccsystem.model.Transaction;
import com.example.finaccsystem.task.GetNodesTask;
import com.example.finaccsystem.task.GetTransactionsTask;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class ListOfTransactionsActivity extends AppCompatActivity {
    private RecyclerView rvTransactions;
    private TransactionAdapter transactionAdapter;
    private List<Transaction> transactions = new ArrayList<>();


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_of_transactions);
        rvTransactions = findViewById(R.id.rvTransactions);
        rvTransactions.setAdapter(transactionAdapter);
        rvTransactions.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        try {
            initData();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void initData() throws IOException, ExecutionException, InterruptedException {
        rvTransactions = findViewById(R.id.rvTransactions);
        initRecyclerView();
    }

    public void initRecyclerView() throws IOException, ExecutionException, InterruptedException {
        rvTransactions.setLayoutManager(new LinearLayoutManager(this));
        transactionAdapter = new TransactionAdapter(this, transactions);
        rvTransactions.setAdapter(transactionAdapter);
        showData();
    }

    private void showData() throws IOException, ExecutionException, InterruptedException {
        transactionAdapter.setData(populateTransactions());
    }


    private List populateTransactions() throws IOException, ExecutionException, InterruptedException {
        transactions = sendGetTransactions();
        return transactions;
    }

    private List<Transaction> sendGetTransactions() throws IOException, ExecutionException, InterruptedException {
        // on below line creating a url to post the data.
        URL url = new URL("http://94.142.141.198:8081");
        GetTransactionsTask task = new GetTransactionsTask();
        return task.execute(url).get();
    }
}

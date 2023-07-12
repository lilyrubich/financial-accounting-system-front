package com.example.finaccsystem.task;

import android.os.AsyncTask;

import com.example.finaccsystem.model.Transaction;
import com.example.finaccsystem.transportObject.TransactionTransportObject;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Header;

public class GetTransactionsTask extends AsyncTask<URL, Void, List<Transaction>> {

    @Override
    protected List<Transaction> doInBackground(URL... urls) {
        try {
            return getTransactions(urls[0]);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Transaction> getTransactions(URL url) throws IOException {
        //Retrofit turns your HTTP API into a Java interface
        //The Retrofit class generates an implementation of the RequestNode interface
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://94.142.141.198:8081")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        GetTransactionsTask.RequestTransaction requestTransaction = retrofit.create(GetTransactionsTask.RequestTransaction.class);
        Response<List<TransactionTransportObject>> execute = requestTransaction.getListOfTransactions("Basic a29sOjEyMw==").execute();
        return ListOfTransactionsConverter(execute.body());
    }

    public interface RequestTransaction {
        @GET("/transaction/getAll")
        Call<List<TransactionTransportObject>> getListOfTransactions(@Header("Authorization") String authorization);
    }

    public List<Transaction> ListOfTransactionsConverter(List<TransactionTransportObject> transactionTransportObjects) {
        ArrayList<Transaction> transactions = new ArrayList<>();
        for (TransactionTransportObject value : transactionTransportObjects) {
            transactions.add(new Transaction(value.getId(), value.getDescription(), value.getSenderNodeId(), value.getSenderNodeName(), value.getSenderAmount(), value.getReceiverNodeId(), value.getReceiverNodeName(), value.getReceiverAmount(), LocalDate.parse(value.getTime(), DateTimeFormatter.ISO_LOCAL_DATE)));
        }
        return transactions;
    }
}

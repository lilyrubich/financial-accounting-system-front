package com.example.finaccsystem.task;

import android.os.AsyncTask;

import com.example.finaccsystem.model.Transaction;
import com.example.finaccsystem.transportObject.TransactionTransportObject;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;

public class PostTransactionTask extends AsyncTask<Transaction, Void, TransactionTransportObject> {
    @Override
    protected TransactionTransportObject doInBackground(Transaction... transaction) {
        Response<TransactionTransportObject> execute = null;
        try {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("http://192.168.1.3:8081")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            RequestTransaction requestTransaction = retrofit.create(RequestTransaction.class);
            TransactionTransportObject transactionTransportObject = TransactionToTORequestConverter(transaction[0]);
            execute = requestTransaction.postNewTransaction("Basic bGlseToxMjM=", transactionTransportObject).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return execute.body();
    }


    public interface RequestTransaction {
        @POST("/transaction/add")
        Call<TransactionTransportObject> postNewTransaction(@Header("Authorization") String authorization, @Body TransactionTransportObject transactionTransportObject);
    }

    public TransactionTransportObject TransactionToTORequestConverter(Transaction transaction) {
        TransactionTransportObject tto = new TransactionTransportObject();
        tto.setSenderNodeId(transaction.getSenderNodeId());
        tto.setReceiverNodeId(transaction.getReceiverNodeId());
        tto.setSenderAmount(transaction.getSenderAmount());
        tto.setReceiverAmount(transaction.getReceiverAmount());
        tto.setDateTime(transaction.getDate().format(DateTimeFormatter.ISO_LOCAL_DATE));
        tto.setDescription(transaction.getDescription());
        return tto;
    }
}

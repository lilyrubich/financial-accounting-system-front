package com.example.finaccsystem.task;

import android.content.Intent;
import android.os.AsyncTask;

import com.example.finaccsystem.activity.StatusOfTransactionActivity;
import com.example.finaccsystem.data.ClientDataHolder;
import com.example.finaccsystem.model.Node;
import com.example.finaccsystem.model.Transaction;
import com.example.finaccsystem.transportObject.TransactionTransportObject;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

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
                    .baseUrl("http://94.142.141.198:8081")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            RequestTransaction requestTransaction = retrofit.create(RequestTransaction.class);
            TransactionTransportObject transactionTransportObject = transactionToTORequestConverter(transaction[0]);
            execute = requestTransaction.postNewTransaction(ClientDataHolder.getInstance().getAuth(), transactionTransportObject).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return execute.body();
    }


    public interface RequestTransaction {
        @POST("/transaction/add")
        Call<TransactionTransportObject> postNewTransaction(@Header("Authorization") String authorization, @Body TransactionTransportObject transactionTransportObject);
    }

    public TransactionTransportObject transactionToTORequestConverter(Transaction transaction) {
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

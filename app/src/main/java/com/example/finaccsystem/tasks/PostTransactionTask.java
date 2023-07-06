package com.example.finaccsystem.tasks;

import android.os.AsyncTask;

import com.example.finaccsystem.model.Transaction;
import com.example.finaccsystem.transportObject.TransactionTransportObject;

import java.io.IOException;
import java.net.URL;

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
        try {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("http://192.168.1.3:8081")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            PostTransactionTask.RequestTransaction requestTransaction = retrofit.create(PostTransactionTask.RequestTransaction.class);
            TransactionTransportObject transactionTransportObject = TransactionToTORequestConverter(transaction[0]);
            Response<TransactionTransportObject> execute = requestTransaction.postNewTransaction("Basic bGlseToxMjM=", transactionTransportObject).execute();
            return execute.body();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    public interface RequestTransaction {
        @POST("/transaction/add")
        Call<TransactionTransportObject> postNewTransaction(@Header("Authorization") String authorization, @Body TransactionTransportObject transactionTransportObject);
    }

    public TransactionTransportObject TransactionToTORequestConverter(Transaction transaction) {
        TransactionTransportObject tto = new TransactionTransportObject();
        //tto.setId(transaction.getId());
        tto.setSenderNodeId(transaction.getSenderNodeId());
        tto.setReceiverNodeId(transaction.getReceiverNodeId());
        tto.setSenderAmount(transaction.getSenderAmount());
        tto.setReceiverAmount(transaction.getReceiverAmount());
        tto.setDateTime(transaction.getDate());
        tto.setDescription(transaction.getDescription());
        return tto;
    }
}

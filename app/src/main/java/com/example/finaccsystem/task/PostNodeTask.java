package com.example.finaccsystem.task;

import android.os.AsyncTask;

import com.example.finaccsystem.data.ClientDataHolder;
import com.example.finaccsystem.enums.CurrencyEnum;
import com.example.finaccsystem.model.Node;
import com.example.finaccsystem.transportObject.NodeTransportObject;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;

public class PostNodeTask extends AsyncTask<Node, Void, NodeTransportObject> {

    @Override
    protected NodeTransportObject doInBackground(Node... node) {
        Response<NodeTransportObject> execute = null;
        try {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("http://94.142.141.198:8081")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            PostNodeTask.RequestNode requestNode = retrofit.create(PostNodeTask.RequestNode.class);
            NodeTransportObject nodeTransportObject = nodeToTORequestConverter(node[0]);
            execute = requestNode.postNewNode(ClientDataHolder.getInstance().getAuth(), nodeTransportObject).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return execute.body();
    }


    public interface RequestNode {
        @POST("/node/add")
        Call<NodeTransportObject> postNewNode(@Header("Authorization") String authorization, @Body NodeTransportObject nodeTransportObject);
    }

    public NodeTransportObject nodeToTORequestConverter(Node node) {
        NodeTransportObject nto = new NodeTransportObject();
        nto.setName(node.getName());
        nto.setAmount(node.getAmount());
        nto.setDescription(node.getDescription());
        nto.setCurrencyId(CurrencyEnum.getCurrencyIdBySymbol(node.getCurrency()));
        nto.setExternal(node.isExternal());
        //1`пока user_id=null, возможно на бэке уберем
        nto.setUserId(null);
        return nto;
    }
}


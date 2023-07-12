package com.example.finaccsystem.task;

import android.os.AsyncTask;

import com.example.finaccsystem.data.ClientDataHolder;
import com.example.finaccsystem.enums.CurrencyEnum;
import com.example.finaccsystem.model.Node;
import com.example.finaccsystem.transportObject.NodeTransportObject;
import com.example.finaccsystem.R;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Header;

public class GetNodesTask extends AsyncTask<URL, Void, List<Node>> {
    @Override
    protected List<Node> doInBackground(URL...urls) {
        try {
            return getNodes(urls[0]);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Node> getNodes(URL url) throws IOException {
        //Retrofit turns your HTTP API into a Java interface
        //The Retrofit class generates an implementation of the RequestNode interface
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://94.142.141.198:8081")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RequestNode requestNode = retrofit.create(RequestNode.class);
        Response<List<NodeTransportObject>> execute = requestNode.getListOfNodes(ClientDataHolder.getInstance().getAuth()).execute();
        return ListOfNodesConverter(execute.body());
    }

    public interface RequestNode {
        @GET("/node/getAll")
        Call<List<NodeTransportObject>> getListOfNodes(@Header("Authorization") String authorization);
    }

    public List<Node> ListOfNodesConverter(List<NodeTransportObject> nodeTransportObjects) {
        ArrayList<Node> nodes = new ArrayList<>();
        for (NodeTransportObject value : nodeTransportObjects) {
            nodes.add(new Node(value.getName(), value.getAmount().setScale(2), CurrencyEnum.getCurrencySymbolById(value.getCurrencyId()), R.drawable.circle_1, value.getId(), value.getDescription(), value.getUserId(), value.isExternal()));
        }
        return nodes;
    }

}

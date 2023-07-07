package com.example.finaccsystem.activity;

import com.example.finaccsystem.R;
import com.example.finaccsystem.activity.NodeDetailsActivity;
import com.example.finaccsystem.adapter.NodeAdapter;
import com.example.finaccsystem.model.Node;
import com.example.finaccsystem.task.GetNodesTask;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;


import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;


public class ListOfNodesActivity extends AppCompatActivity implements NodeAdapter.NodeClickListener {

    private RecyclerView rvNodes;
    private NodeAdapter nodeAdapter;
    private ArrayList<Node> nodes;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_of_nodes);
        RecyclerView recyclerView = findViewById(R.id.rvNodes);
        recyclerView.setAdapter(nodeAdapter);
        //toDo: летающая кнопка
        //FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        initData();
    }


    private void showData() {
        nodeAdapter.setData(populateNodes());
    }

    private void initData() {
        rvNodes = findViewById(R.id.rvNodes);
        toolbar = findViewById(R.id.tbToolbar);
        this.setSupportActionBar(toolbar);
        this.setTitle("");
        initRecyclerView();
    }


    private void initRecyclerView() {
        rvNodes.setLayoutManager(new LinearLayoutManager(this));
        nodeAdapter = new NodeAdapter(this, nodes, this::selectedNode);
        //nodeAdapter = new NodeAdapter( getApplicationContext(), nodes);
        rvNodes.setAdapter(nodeAdapter);
        showData();
    }


    private List populateNodes() {
        ArrayList<Node> nodes = new ArrayList<Node>();
        /*nodes.add(new Node("Карта Совком", "120000", "₽", R.drawable.credit_card));
        nodes.add(new Node("Наличка у мамы", "344000", "$", R.drawable.cash));
        nodes.add(new Node("Долг Лили", "200", "$", R.drawable.pay));
        nodes.add(new Node("Биткоины", "0.001", "₿", R.drawable.bitcoin));
        nodes.add(new Node("Вклад УБРиР", "177990", "₽", R.drawable.time_is_money));
        nodes.add(new Node("Наличка в евро", "100", "€", R.drawable.euro));
        nodes.add(new Node("Вклад Альфа", "105000", "₽", R.drawable.time_is_money));
        nodes.add(new Node("На карте Шри-Ланки", "300", "€", R.drawable.euro));
        nodes.add(new Node("Карта Сбер", "54000", "₽", R.drawable.credit_card));*/

        try {
            nodes = (ArrayList<Node>) sendGetNodes();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return nodes;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_menu, menu);
        MenuItem menuItem = menu.findItem(R.id.search_view);
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setMaxWidth(Integer.MAX_VALUE);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String query) {
                nodeAdapter.getFilter().filter(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                nodeAdapter.getFilter().filter(newText);
                return true;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void selectedNode(Node node) {
        Intent intent = new Intent(this, NodeDetailsActivity.class).putExtra("data", node);
        startActivity(intent);
    }


    // on below line creating a class to post the data.
    private List<Node> sendGetNodes() throws IOException, ExecutionException, InterruptedException {
        // on below line creating a url to post the data.
        URL url = new URL("http://192.168.1.3:8081");
        GetNodesTask task = new GetNodesTask();
        return task.execute(url).get();
    }
}
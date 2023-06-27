package com.example.finaccsystem;

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
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ListOfNodesActivity extends AppCompatActivity implements NodeAdapter.NodeClickListener {

    RecyclerView rvNodes;
    NodeAdapter nodeAdapter;
    ArrayList<Node> nodes = new ArrayList<Node>();
    Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_of_nodes);
        RecyclerView recyclerView = findViewById(R.id.rvNodes);
        recyclerView.setAdapter(nodeAdapter);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
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
//ПРОВЕРКА
        //Toast.makeText(ListOfNodesActivity.this, "user ", Toast.LENGTH_SHORT).show();

        rvNodes.setLayoutManager(new LinearLayoutManager(this));
        nodeAdapter = new NodeAdapter(this, nodes, this::selectedNode);
        //nodeAdapter = new NodeAdapter( getApplicationContext(), nodes);
        rvNodes.setAdapter(nodeAdapter);
        showData();
    }


    private List populateNodes() {
        ArrayList<Node> nodes = new ArrayList<Node>();
        nodes.add(new Node("Карта Совком", "120000", "₽", R.drawable.credit_card));
        nodes.add(new Node("Наличка у мамы", "344000", "$", R.drawable.cash));
        nodes.add(new Node("Долг Лили", "200", "$", R.drawable.pay));
        nodes.add(new Node("Биткоины", "0.001", "₿", R.drawable.bitcoin));
        nodes.add(new Node("Вклад УБРиР", "177990", "₽", R.drawable.time_is_money));
        nodes.add(new Node("Наличка в евро", "100", "€", R.drawable.euro));
        nodes.add(new Node("Вклад Альфа", "105000", "₽", R.drawable.time_is_money));
        nodes.add(new Node("На карте Шри-Ланки", "300", "€", R.drawable.euro));
        nodes.add(new Node("Карта Сбер", "54000", "₽", R.drawable.credit_card));
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
}
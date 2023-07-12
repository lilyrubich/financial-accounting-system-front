package com.example.finaccsystem.activity;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.example.finaccsystem.R;
import com.example.finaccsystem.model.Node;
import com.example.finaccsystem.model.Transaction;
import com.example.finaccsystem.task.GetNodesTask;
import com.example.finaccsystem.task.PostTransactionTask;
import com.example.finaccsystem.transportObject.TransactionTransportObject;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class NewTransactionActivity extends AppCompatActivity {


    private Intent intent;
    private List<Node> nodes;
    private ArrayList<String> nodesNameList;
    private Calendar dateAndTime = Calendar.getInstance();
    private EditText editTextDate;
    private EditText description;
    private EditText senderAmount;
    private EditText receiverAmount;
    private TextView senderCurrency, receiverCurrency;
    private Spinner spinnerSender, spinnerReceiver;
    private Transaction transaction = new Transaction();

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_transaction);
        editTextDate = findViewById(R.id.date);
        description = findViewById(R.id.description);
        senderAmount = findViewById(R.id.senderAmount);
        receiverAmount = findViewById(R.id.receiverAmount);
        senderCurrency = findViewById(R.id.senderCurrency);
        receiverCurrency = findViewById(R.id.receiverCurrency);
        intent = getIntent();
        setInitialDateTime();
        spinnerSender = findViewById(R.id.spinnerSenderNode);
        spinnerReceiver = findViewById(R.id.spinnerReceiverNode);

        try {
            nodes = sendGetNodes();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        nodesNameList = getNodesNameList(nodes);
        // Создаем адаптер ArrayAdapter с помощью массива строк и стандартной разметки элемета spinner
        ArrayAdapter<String> adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, nodes);
        // Определяем разметку для использования при выборе элемента
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Применяем адаптер к элементу spinner
        spinnerSender.setAdapter(adapter);
        spinnerReceiver.setAdapter(adapter);

        AdapterView.OnItemSelectedListener senderNodeSelectedListener = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                // Получаем выбранный объект
                Node selectedNode = (Node) parent.getItemAtPosition(position);
                senderCurrency.setText(selectedNode.getCurrency());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        };

        AdapterView.OnItemSelectedListener receiverNodeSelectedListener = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                // Получаем выбранный объект
                Node selectedNode = (Node) parent.getItemAtPosition(position);
                receiverCurrency.setText(selectedNode.getCurrency());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        };
        spinnerSender.setOnItemSelectedListener(senderNodeSelectedListener);
        spinnerReceiver.setOnItemSelectedListener(receiverNodeSelectedListener);
    }

    // установка начальной даты
    public void setInitialDateTime() {

        editTextDate.setText(DateUtils.formatDateTime(this,
                dateAndTime.getTimeInMillis(),
                DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_SHOW_YEAR));
    }

    // отображаем диалоговое окно для выбора времени
    public void setDate(View v) {
        new DatePickerDialog(this, d,
                dateAndTime.get(Calendar.YEAR),
                dateAndTime.get(Calendar.MONTH),
                dateAndTime.get(Calendar.DAY_OF_MONTH))
                .show();
    }

    // установка обработчика выбора даты
    DatePickerDialog.OnDateSetListener d = new DatePickerDialog.OnDateSetListener() {
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            dateAndTime.set(Calendar.YEAR, year);
            dateAndTime.set(Calendar.MONTH, monthOfYear);
            dateAndTime.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            setInitialDateTime();
        }
    };


    private List<Node> sendGetNodes() throws IOException, ExecutionException, InterruptedException {
        // on below line creating a url to post the data.
        //URL url = new URL("http://192.168.1.7:8081");
        URL url = new URL("http://192.168.1.3:8081");
        GetNodesTask task = new GetNodesTask();
        return task.execute(url).get();
    }


    private ArrayList<String> getNodesNameList(List<Node> nodes) {
        ArrayList<String> nodesNameList = new ArrayList<>();
        for (Node value : nodes) {
            nodesNameList.add(value.getName());
        }
        return nodesNameList;
    }

    private TransactionTransportObject sendPostTransaction() throws IOException, ExecutionException, InterruptedException {
        // on below line creating a url to post the data.
        //URL url = new URL("http://192.168.1.7:8081");
        PostTransactionTask task = new PostTransactionTask();
        TransactionTransportObject tto = task.execute(transaction).get();
        return tto;
    }


    public void executeAddTransaction(View view) throws IOException, ExecutionException, InterruptedException {
        Node senderNode = (Node) spinnerSender.getSelectedItem();
        Node receiverNode = (Node) spinnerReceiver.getSelectedItem();
        transaction.setDescription(description.getText().toString());
        transaction.setSenderAmount(new BigDecimal(senderAmount.getText().toString()));
        transaction.setReceiverAmount(new BigDecimal(receiverAmount.getText().toString()));
        transaction.setSenderNodeName(senderNode.getName());
        transaction.setReceiverNodeName(receiverNode.getName());
        transaction.setSenderNodeId(senderNode.getId());
        transaction.setReceiverNodeId(receiverNode.getId());
        transaction.setDate(dateAndTime.getTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());

        TransactionTransportObject executePostRequest = sendPostTransaction();
        if (executePostRequest != null) {
            Intent intent = new Intent(this, StatusOfTransactionActivity.class);
            startActivity(intent);
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            Intent intent = new Intent(this, ListOfNodesActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}

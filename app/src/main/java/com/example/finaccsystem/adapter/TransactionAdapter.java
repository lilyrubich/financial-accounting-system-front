package com.example.finaccsystem.adapter;

import android.content.Context;
import android.graphics.drawable.ShapeDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.finaccsystem.R;
import com.example.finaccsystem.model.Transaction;

import java.util.List;

public class TransactionAdapter extends RecyclerView.Adapter<TransactionAdapter.ViewHolder> {

    private LayoutInflater inflater;
    private Transaction transaction;
    private View view;
    private List<Transaction> transactions;
    private Context context;

    public TransactionAdapter(Context context, List<Transaction> transactions){
        this.context = context;
        this.transactions = transactions;
        this.inflater = LayoutInflater.from(context);
    }

    //создаем список карточек view
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        view = inflater.inflate(R.layout.each_transaction_from_history, parent, false);
        return new ViewHolder(view);
    }


    //задаем параметры заполнения карточки значениями класса транзакции
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        transaction = transactions.get(position);
        holder.description.setText(transaction.getDescription());
        holder.senderNode.setText(transaction.getSenderNodeName());
        holder.receiverNode.setText(transaction.getReceiverNodeName());
        holder.senderAmount.setText(transaction.getSenderAmount().toString() + " " + transaction.getSenderCurrency());
        holder.receiverAmount.setText(transaction.getReceiverAmount().toString() + " " + transaction.getReceiverCurrency());

    }


    @Override
    public int getItemCount() {
        return transactions.size();
    }

    public void setData(List<Transaction> transactions){
        this.transactions = transactions;
    }

    //создаем класс содержимого карточки
    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView description;
        TextView senderNode;
        TextView receiverNode;
        TextView senderAmount;
        TextView receiverAmount;

        public ViewHolder(View view) {
            super(view);
            description = view.findViewById(R.id.description);
            senderNode = view.findViewById(R.id.senderNode);
            receiverNode = view.findViewById(R.id.receiverNode);
            senderAmount = view.findViewById(R.id.senderAmount);
            receiverAmount = view.findViewById(R.id.receiverAmount);

        }
    }
}

package com.example.finaccsystem;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public class NodeAdapter extends RecyclerView.Adapter<NodeAdapter.ViewHolder> implements Filterable {

    private LayoutInflater inflater;
    private static List<Node> nodes;
    private List<Node> nodesFiltered;
    private Context context;

    NodeAdapter(Context context, List<Node> nodes) {
        this.nodes = nodes;
        this.inflater = LayoutInflater.from(context);
    }

/*
    public NodeAdapter(Context context, List<Node> nodes){
        this.context=context;
        this.nodes=nodes;
    }

     */


    @Override
    public NodeAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.each_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(NodeAdapter.ViewHolder holder, int position) {
        Node node = nodes.get(position);
        holder.iconView.setImageResource(node.getIconResource());
        holder.nameView.setText(node.getName());
        holder.amountView.setText(node.getAmount());
        holder.currencyView.setText(node.getCurrency());
    }

    @Override
    public int getItemCount() {
        return nodes.size();
    }

    public void setData(List<Node> newNodes) {
        this.nodes = newNodes;
        this.nodesFiltered = newNodes;

    }

    @Override
    public Filter getFilter() {
        Filter filter = new Filter() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults filterResults = new FilterResults();
                if (constraint == null || constraint.length() == 0) {
                    filterResults.values = nodesFiltered;
                    filterResults.count = nodesFiltered.size();
                } else {
                    String searchChar = constraint.toString().toLowerCase();
                    filterResults.values
                            = nodesFiltered.stream().filter(n -> n.getName().toLowerCase().contains(searchChar.toLowerCase())).collect(Collectors.toList());
                }
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                nodes = (List<Node>) results.values;
                notifyDataSetChanged();
            }
        };
        return filter;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        final ImageView iconView;
        final TextView nameView, amountView, currencyView;
        //OnNodeClickListener onNodeClickListener;

        public ViewHolder(View view) {
            super(view);
            iconView = view.findViewById(R.id.icon);
            nameView = view.findViewById(R.id.name);
            amountView = view.findViewById(R.id.amount);
            currencyView = view.findViewById(R.id.currency);
            /*


            this.onNodeClickListener = onNodeClickListener;
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Node node = nodes.get(getLayoutPosition());
                    onNodeClickListener.onNodeClick(node);
                }
            });

            */
        }

    }

             /*
//
    public interface OnNodeClickListener {
        void onNodeClick(Node node);
    }
    //

             */
}

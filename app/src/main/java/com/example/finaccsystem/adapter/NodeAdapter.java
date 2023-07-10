package com.example.finaccsystem.adapter;

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


import com.example.finaccsystem.R;
import com.example.finaccsystem.model.Node;

import java.util.List;
import java.util.stream.Collectors;

public class NodeAdapter extends RecyclerView.Adapter<NodeAdapter.ViewHolder> implements Filterable {

    private LayoutInflater inflater;
    private static List<Node> nodes;
    private List<Node> nodesFiltered;
    private Context context;
    private NodeClickListener nodeClickListener;


    public NodeAdapter(Context context, List<Node> nodes, NodeClickListener nodeClickListener) {
        this.context = context;
        this.nodes = nodes;
        this.nodeClickListener = nodeClickListener;
        this.inflater = LayoutInflater.from(context);
    }


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
        holder.amountView.setText(node.getAmount().toString());
        holder.currencyView.setText(node.getCurrency());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nodeClickListener.selectedNode(node);
            }
        });
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

        public ViewHolder(View view) {
            super(view);
            iconView = view.findViewById(R.id.icon);
            nameView = view.findViewById(R.id.name);
            amountView = view.findViewById(R.id.amount);
            currencyView = view.findViewById(R.id.currency);
        }
    }

    public interface NodeClickListener {
        void selectedNode(Node node);
    }
}




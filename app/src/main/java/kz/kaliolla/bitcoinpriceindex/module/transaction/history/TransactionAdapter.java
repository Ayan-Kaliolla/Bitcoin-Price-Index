package kz.kaliolla.bitcoinpriceindex.module.transaction.history;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import kz.kaliolla.bitcoinpriceindex.R;
import kz.kaliolla.bitcoinpriceindex.repository.model.Transaction;

public class TransactionAdapter extends RecyclerView.Adapter<TransactionAdapter.TransactionViewHolder> {
    private List<Transaction> transactions;
    private LayoutInflater inflater;
    private SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss.SSSSSS");

    public TransactionAdapter(Context context, List<Transaction> transactions) {
        this.transactions = transactions;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @NonNull
    @Override
    public TransactionViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = inflater.inflate(R.layout.item_transaction, viewGroup, false);
        TransactionViewHolder holder = new TransactionViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull TransactionViewHolder transactionViewHolder, int position) {
        Transaction transaction = transactions.get(position);
        transactionViewHolder.id.setText(String.valueOf(transaction.getId()));
        transactionViewHolder.date.setText(format.format(new Date(transaction.getDate())));
        transactionViewHolder.price.setText(String.valueOf(transaction.getPrice()));
    }

    @Override
    public int getItemCount() {
        return transactions.size();
    }

    public void setTransactions(@NonNull List<Transaction> transactions) {
        this.transactions = transactions;
    }

    static class TransactionViewHolder extends RecyclerView.ViewHolder {
        TextView id;
        TextView date;
        TextView price;

        public TransactionViewHolder(@NonNull View itemView) {
            super(itemView);
            id = itemView.findViewById(R.id.id);
            date = itemView.findViewById(R.id.date);
            price = itemView.findViewById(R.id.price);
        }
    }
}

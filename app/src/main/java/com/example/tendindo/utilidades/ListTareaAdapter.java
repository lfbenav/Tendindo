package com.example.tendindo.utilidades;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.tendindo.R;

import java.util.List;

public class ListTareaAdapter extends RecyclerView.Adapter<ListTareaAdapter.ViewHolder> {

    private List<ListTareaElement> mData;
    private LayoutInflater mInflater;
    private Context context;
    final ListTareaAdapter.OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(ListTareaElement item);
    }

    public ListTareaAdapter(List<ListTareaElement> mData, Context context, OnItemClickListener listener) {
        this.mData = mData;
        this.mInflater = LayoutInflater.from(context);
        this.context = context;
        this.listener = listener;
    }

    @Override
    public int getItemCount() { return this.mData.size(); }

    @Override
    public ListTareaAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.list_element_menu_tareas,null);
        return new ListTareaAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ListTareaAdapter.ViewHolder holder, final int position) {
        holder.bindData(mData.get(position));
    }

    public void setItems(List<ListTareaElement> items) { mData = items; }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView text_view_nombre;

        ViewHolder(View itemView) {
            super(itemView);
            text_view_nombre = itemView.findViewById(R.id.text_view_nombre_tarea);
        }

        void bindData(final ListTareaElement item) {
            text_view_nombre.setText(item.getNombre());
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(item);
                }
            });
        }

    }
}

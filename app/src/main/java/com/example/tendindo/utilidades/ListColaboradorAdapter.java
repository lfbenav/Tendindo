package com.example.tendindo.utilidades;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.tendindo.R;

import java.util.List;

public class ListColaboradorAdapter  extends RecyclerView.Adapter<ListColaboradorAdapter.ViewHolder> {

    private List<ListColaboradorElement> mData;
    private LayoutInflater mInflater;
    private Context context;
    final ListColaboradorAdapter.OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(ListColaboradorElement item);
    }

    public ListColaboradorAdapter(List<ListColaboradorElement> mData, Context context, ListColaboradorAdapter.OnItemClickListener listener) {
        this.mData = mData;
        this.mInflater = LayoutInflater.from(context);
        this.context = context;
        this.listener = listener;
    }

    @Override
    public int getItemCount() { return this.mData.size(); }

    @Override
    public ListColaboradorAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.list_element_menu_colaboradores,null);
        return new ListColaboradorAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ListColaboradorAdapter.ViewHolder holder, final int position) {
        holder.bindData(mData.get(position));
    }

    public void setItems(List<ListColaboradorElement> items) { mData = items; }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView text_view_nombre, text_view_estado;

        ViewHolder(View itemView) {
            super(itemView);
            text_view_nombre = itemView.findViewById(R.id.text_view_nombre_colaborador);
            text_view_estado = itemView.findViewById(R.id.text_view_estado_colaborador);
        }

        void bindData(final ListColaboradorElement item) {
            text_view_nombre.setText(item.getNombre());
            text_view_estado.setText(item.getEstado());
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(item);
                }
            });
        }

    }

}

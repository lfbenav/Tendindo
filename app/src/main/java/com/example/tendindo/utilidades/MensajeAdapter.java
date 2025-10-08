package com.example.tendindo.utilidades;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.tendindo.R;

import java.util.List;

public class MensajeAdapter extends RecyclerView.Adapter<MensajeAdapter.ViewHolder> {

    private List<Mensaje> mData;
    private LayoutInflater mInflater;
    private Context context;
    final MensajeAdapter.OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(Mensaje item);
    }

    public MensajeAdapter(List<Mensaje> mData, Context context, MensajeAdapter.OnItemClickListener listener) {
        this.mData = mData;
        this.mInflater = LayoutInflater.from(context);
        this.context = context;
        this.listener = listener;
    }

    @Override
    public int getItemCount() { return this.mData.size(); }

    @Override
    public MensajeAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.list_element_mensaje,null);
        return new MensajeAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MensajeAdapter.ViewHolder holder, final int position) {
        holder.bindData(mData.get(position));
    }

    public void setItems(List<Mensaje> items) { mData = items; }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView text_view_nombre, text_view_mensaje;

        ViewHolder(View itemView) {
            super(itemView);
            text_view_nombre = itemView.findViewById(R.id.text_view_emisor);
            text_view_mensaje = itemView.findViewById(R.id.text_view_mensaje);
        }

        void bindData(final Mensaje item) {
            text_view_nombre.setText(item.nombreColaborador);
            text_view_mensaje.setText(item.mensaje);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(item);
                }
            });
        }

    }
}

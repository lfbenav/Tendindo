package com.example.tendindo.utilidades;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.tendindo.R;

import java.util.List;

public class ListProyectoAdapter extends RecyclerView.Adapter<ListProyectoAdapter.ViewHolder> {
    private List<ListProyectoElement> mData;
    private LayoutInflater mInflater;
    private Context context;
    final ListProyectoAdapter.OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(ListProyectoElement item);
    }

    public ListProyectoAdapter(List<ListProyectoElement> mData, Context context, ListProyectoAdapter.OnItemClickListener listener) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = mData;
        this.context = context;
        this.listener = listener;
    }

    @Override
    public int getItemCount() { return this.mData.size(); }

    @Override
    public ListProyectoAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.list_element_menu_principal,null);
        return new ListProyectoAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ListProyectoAdapter.ViewHolder holder, final int position) {
        holder.bindData(mData.get(position));
    }

    public void setItems(List<ListProyectoElement> items) { mData = items; }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView image_view_icono_proyecto;
        TextView text_view_titulo_proyecto, text_view_subtitulo_proyecto, text_view_estado_proyecto;

        ViewHolder(View itemView) {
            super(itemView);
            image_view_icono_proyecto = itemView.findViewById(R.id.image_view_icono_proyecto);
            text_view_titulo_proyecto = itemView.findViewById(R.id.text_view_titulo_proyecto);
            text_view_subtitulo_proyecto = itemView.findViewById(R.id.text_view_subtitulo_proyecto);
            text_view_estado_proyecto = itemView.findViewById(R.id.text_view_estado_proyecto);
        }

        void bindData(final ListProyectoElement item) {
            text_view_titulo_proyecto.setText(item.getNombre());
            text_view_subtitulo_proyecto.setText(item.getSubtitulo());
            text_view_estado_proyecto.setText(item.getEstado());
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(item);
                }
            });
        }

    }
}

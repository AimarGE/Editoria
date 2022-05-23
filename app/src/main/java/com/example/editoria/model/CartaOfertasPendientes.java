package com.example.editoria.model;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.editoria.R;

import java.util.List;

public class CartaOfertasPendientes extends RecyclerView.Adapter<CartaOfertasPendientes.ViewHolder> {

    private List<ListElement> mData;
    private LayoutInflater mInflater;
    private Context context;
    final CartaOfertasPendientes.OnItemClickListener listener;

    public interface OnItemClickListener{
        void onItemClick(ListElement item);
    }

    public CartaOfertasPendientes(List<ListElement> itemList, Context context, CartaOfertasPendientes.OnItemClickListener listener){
        this.mInflater = LayoutInflater.from(context);
        this.context = context;
        this.mData = itemList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public CartaOfertasPendientes.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.carta_proyecto_pendiente, null);
        return new CartaOfertasPendientes.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartaOfertasPendientes.ViewHolder holder, int position) {
        holder.bindData(mData.get(position));
    }

    @Override
    public int getItemCount( ) {
        return mData.size();
    }

    public void setItems(List<ListElement> items){
        mData = items;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView icono;//PONER EL ICONO DE LA BASE DE DATOS DEL EDITOR
        TextView name, titulo, precio;

        ViewHolder(View itemView){
            super(itemView);
                name = itemView.findViewById(R.id.cvNombreCliente);
                icono = itemView.findViewById(R.id.cvIconoEditor);
                titulo = itemView.findViewById(R.id.tituloProyecto);
                precio = itemView.findViewById(R.id.cvPrecioProyecto);
        }

        void bindData(final ListElement item){
            icono.setImageResource(R.drawable.ejemplo);
            name.setText(item.getName());
            titulo.setText(item.getTitulo());
            precio.setText(item.getPrecio());

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(item);
                }
            });
        }
    }
}

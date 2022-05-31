package com.example.editoria.model;


import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.editoria.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CartaProyectoInformacion extends RecyclerView.Adapter<CartaProyectoInformacion.ViewHolder> {

    private List<ListElement> mData;
    private LayoutInflater mInflater;
    private Context context;
    final CartaProyectoInformacion.OnItemClickListener listener;

    public interface OnItemClickListener{
        void onItemClick(ListElement item);
    }

    public CartaProyectoInformacion(List<ListElement> itemList, Context context, CartaProyectoInformacion.OnItemClickListener listener){
        this.mInflater = LayoutInflater.from(context);
        this.context = context;
        this.mData = itemList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public CartaProyectoInformacion.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.carta_proyectos_informacion, null);
        return new CartaProyectoInformacion.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartaProyectoInformacion.ViewHolder holder, int position) {
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
        ImageView icono, foto;//PONER EL ICONO DE LA BASE DE DATOS DEL EDITOR
        TextView name, titulo;

        ViewHolder(View itemView){
            super(itemView);
                name = itemView.findViewById(R.id.cvNombreEditor);
                icono = itemView.findViewById(R.id.cvIconoEditor);
                titulo = itemView.findViewById(R.id.tituloProyecto);
                foto = itemView.findViewById(R.id.imagenProyecto);
        }

        void bindData(final ListElement item){
            icono.setImageResource(R.drawable.ejemplo);
            name.setText(item.getName());
            titulo.setText(item.getTitulo());
            Picasso.get().load(item.getIcon()).into(icono);
            Picasso.get().load("https://c.tenor.com/P0H97FaEQhoAAAAM/mario-edit-edit.gif").into(foto);

            name.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(item);
                }
            });

            icono.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(item);
                }
            });
        }
    }
}

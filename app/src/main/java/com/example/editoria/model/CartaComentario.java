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

import java.util.List;

public class CartaComentario extends RecyclerView.Adapter<CartaComentario.ViewHolder> {

    private List<ListElementComentario> mData;
    private LayoutInflater mInflater;
    private Context context;

    public CartaComentario(List<ListElementComentario> itemList, Context context){
        this.mInflater = LayoutInflater.from(context);
        this.context = context;
        this.mData = itemList;
    }

    @NonNull
    @Override
    public CartaComentario.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.carta_comentario, null);
        return new CartaComentario.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartaComentario.ViewHolder holder, int position) {
        holder.bindData(mData.get(position));
    }

    @Override
    public int getItemCount( ) {
        return mData.size();
    }

    public void setItems(List<ListElementComentario> items){
        mData = items;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView icono;//PONER EL ICONO DE LA BASE DE DATOS DEL EDITOR
        TextView name, comentario, valoracion;

        ViewHolder(View itemView){
            super(itemView);
                name = itemView.findViewById(R.id.cvNombreEditor);
                icono = itemView.findViewById(R.id.cvIconoEditor);
                comentario = itemView.findViewById(R.id.cvComentarioText);
                valoracion = itemView.findViewById(R.id.valoracionTV);
        }

        void bindData(final ListElementComentario item){
            icono.setImageResource(R.drawable.ejemplo);
            name.setText(item.getName());
            Log.i("EJEMPLO", "TEXTO "+item.getComentario());
            comentario.setText(item.getComentario());
            valoracion.setText(item.getValoracion());
        }
    }
}

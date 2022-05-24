package com.example.editoria.model;


import android.content.Context;
import android.media.Image;
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

public class CartaRecursosClientes extends RecyclerView.Adapter<CartaRecursosClientes.ViewHolder> {

    private List<ListElement> mData;
    private LayoutInflater mInflater;
    private Context context;
    private ImageView foto;
    final CartaRecursosClientes.OnItemClickListener listener;

    public interface OnItemClickListener{
        void onItemClick(ListElement item);
    }

    public CartaRecursosClientes(List<ListElement> itemList, Context context, CartaRecursosClientes.OnItemClickListener listener){
        this.mInflater = LayoutInflater.from(context);
        this.context = context;
        this.mData = itemList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public CartaRecursosClientes.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.carta_proyecto_pendiente_expandido, null);
        return new CartaRecursosClientes.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartaRecursosClientes.ViewHolder holder, int position) {
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
                foto = itemView.findViewById(R.id.imagenProyecto);
        }

        void bindData(final ListElement item){
            icono.setImageResource(R.drawable.ejemplo);
            name.setText(item.getName());
            titulo.setText(item.getTitulo());
            precio.setText("Precio: "+item.getPrecio()+"€");
            Picasso.get().load("https://static.wikia.nocookie.net/doraenciclopedia/images/7/79/Nobita_Nobi_-_2005_anime.png/revision/latest?cb=20170723201924&path-prefix=es").into(foto);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(item);
                }
            });
        }
    }

    public ImageView getImageView() {
        return foto;
    }
}

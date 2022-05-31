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
import com.squareup.picasso.Picasso;

import java.util.List;

public class CartaRanking extends RecyclerView.Adapter<CartaRanking.ViewHolder> {

    private List<ListElementRanking> mData;
    private LayoutInflater mInflater;
    private Context context;
    final CartaRanking.OnItemClickListener listener;

    public interface OnItemClickListener{
        void onItemClick(ListElementRanking item);
    }

    public CartaRanking(List<ListElementRanking> itemList, Context context, CartaRanking.OnItemClickListener listener){
        this.mInflater = LayoutInflater.from(context);
        this.context = context;
        this.mData = itemList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public CartaRanking.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.carta_ranking, null);
        return new CartaRanking.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartaRanking.ViewHolder holder, int position) {
        holder.bindData(mData.get(position));
    }

    @Override
    public int getItemCount( ) {
        return mData.size();
    }

    public void setItems(List<ListElementRanking> items){
        mData = items;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView icono, foto;//PONER EL ICONO DE LA BASE DE DATOS DEL EDITOR
        TextView name, likes, ranking;

        ViewHolder(View itemView){
            super(itemView);
                name = itemView.findViewById(R.id.cvNombreEditor);
                icono = itemView.findViewById(R.id.cvIconoEditor);
                ranking = itemView.findViewById(R.id.cvRanking);
                likes = itemView.findViewById(R.id.likes);
                foto = itemView.findViewById(R.id.imagenProyecto);
        }

        void bindData(final ListElementRanking item){
            icono.setImageResource(R.drawable.ejemplo);
            name.setText(item.getName());
            likes.setText(item.getLikes());
            ranking.setText(item.getRanking()+" -");
            Picasso.get().load(item.getFoto()).into(foto);
            Picasso.get().load(item.getIcon()).into(icono);
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

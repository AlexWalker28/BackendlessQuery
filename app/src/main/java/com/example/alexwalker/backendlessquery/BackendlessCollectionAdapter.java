package com.example.alexwalker.backendlessquery;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.backendless.BackendlessCollection;

/**
 * Created by AlexWalker on 02.10.2016.
 */
public class BackendlessCollectionAdapter implements ListAdapter {
    private BackendlessCollection <Sorting> backendlessCollection;
    private LayoutInflater layoutInflater;

    public BackendlessCollectionAdapter(Context context, BackendlessCollection <Sorting> backendlessCollection){
        this.backendlessCollection = backendlessCollection ;
        layoutInflater = LayoutInflater.from(context);
    }


    public int getCount(){
        return backendlessCollection.getData().size();
    }

    public Object getItem(int position){
        return backendlessCollection.getData().get(position);
    }
    public long getItemId(int position){
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent){
        ViewHolder holder;
        if (convertView == null){
            convertView = layoutInflater.inflate(R.layout.custom_adapter,null);
            holder = new ViewHolder();
            holder.streetView = (TextView)convertView.findViewById(R.id.street);
            holder.apartmentTypeView = (TextView)convertView.findViewById(R.id.apartmentType);
            holder.priceView = (TextView)convertView.findViewById(R.id.price);
            holder.floorCountView = (TextView)convertView.findViewById(R.id.floorCount);
            holder.roomsCountView = (TextView)convertView.findViewById(R.id.roomsCount);

        }else {
            holder = (ViewHolder)convertView.getTag();
        }
        Sorting content = null;

        holder.streetView.setText(content.getStreet());
        holder.apartmentTypeView.setText(content.getApartmentType());
        holder.priceView.setText(content.getPrice());
        holder.floorCountView.setText(content.getFloorCount());
        holder.roomsCountView.setText(content.getRoomsCount());
        return convertView;
    }

    static class ViewHolder{
        TextView streetView;
        TextView apartmentTypeView;
        TextView priceView;
        TextView floorCountView;
        TextView roomsCountView;
    }



}

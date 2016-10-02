package com.example.alexwalker.backendlessquery;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.backendless.BackendlessCollection;

/**
 * Created by AlexWalker on 02.10.2016.
 */
public class BackendlessCollectionAdapter extends BaseAdapter {
    private BackendlessCollection <Sorting> backendlessCollection;
    private LayoutInflater layoutInflater;

    public BackendlessCollectionAdapter(Context context, BackendlessCollection <Sorting> backendlessCollection){
        this.backendlessCollection = backendlessCollection ;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount(){
        return backendlessCollection.getData().size();
    }
    @Override
    public Object getItem(int position){
        return backendlessCollection.getData().get(position);
    }
    @Override
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
            convertView.setTag(holder);

        }else {
            holder = (ViewHolder)convertView.getTag();
        }


        holder.streetView.setText("address: " + backendlessCollection.getData().get(position).getStreet());
        holder.apartmentTypeView.setText("type: " + backendlessCollection.getData().get(position).getApartmentType());
        holder.priceView.setText("price: " + backendlessCollection.getData().get(position).getPrice());
        holder.floorCountView.setText("floor: " + backendlessCollection.getData().get(position).getFloorCount());
        holder.roomsCountView.setText("rooms: " + backendlessCollection.getData().get(position).getRoomsCount());
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

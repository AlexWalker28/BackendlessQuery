package com.example.alexwalker.backendlessquery;

/**
 * Created by AlexWalker on 01.10.2016.
 */
public class Sorting {

   String street;
   String apartmentType;
   String price;
   String floorCount;
   String roomsCount;

    Sorting()
    {}

    public Sorting (String street, String apartmentType, String price, String floorCount, String roomsCount){
        this.street = street;
        this.apartmentType = apartmentType;
        this.price = price;
        this.floorCount = floorCount;
        this.roomsCount = roomsCount;
    }

    public String getStreet(){
        return street;
    }
    public void setStreet(String street){
        this.street = street;
    }

    public String getApartmentType(){
        return apartmentType;
    }
    public void setApartmentType(String apartmentType){
        this.apartmentType = apartmentType;
    }
    public String getPrice(){
        return price;
    }
    public void setPrice(String price){
        this.price = price;
    }
    public String getFloorCount(){
        return floorCount;
    }
    public void setFloorCount(String floorCount){
        this.floorCount = floorCount;
    }
    public String getRoomsCount(){
        return roomsCount;
    }
    public void setRoomsCount(String roomsCount){
        this.roomsCount = roomsCount;
    }

}

package com.thekeval.tourismapp;

public class PlaceModel {
    String name;
    String image;
    double price;

    public PlaceModel(String name, String image, double price) {
        this.name = name;
        this.image = image;
        this.price = price;
    }

    // region getters & setters

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    // endregion


    @Override
    public String toString() {
        return "PlaceModel{" +
                "name='" + name + '\'' +
                ", image='" + image + '\'' +
                ", price=" + price +
                '}';
    }
}

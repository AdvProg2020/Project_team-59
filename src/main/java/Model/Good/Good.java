package Model.Good;

import Model.Account.Seller;

import java.util.ArrayList;


public class Good {
    private String productId;
    private ProductState productState;
    private boolean isAvailable;
    private ArrayList<Seller> sellers;
    private double price;
    private String productName;
    private Category category;
    private Category subCategory;
    private String goodDescription;
    private ArrayList<Characteristic> characteristics;
    private ArrayList<Rating> ratings;
    private ArrayList<Comment> comments;
    private int timesVisited;

    public Good(String productId, String productName, String goodDescription , Category category, Category subCategory, ArrayList<Characteristic> characteristics) {
        this.productId = productId;
        this.productName = productName;
        this.goodDescription = goodDescription;
        this.category = category;
        this.subCategory = subCategory;
        this.characteristics = characteristics;
        this.timesVisited = 0;
    }

    public double getAverageRating(){
        double scoreSum = 0;
        for (Rating rating : this.ratings) {
            scoreSum += rating.getRating();
        }
        return  Math.floor((scoreSum/ratings.size()) * 100) / 100;
    }

    public boolean hasOtherSellersAvailable(){
        return sellers.size() >= 2;
    }

    public boolean sellerSellsThisGood(Seller seller){
        return this.sellers.contains(seller);
    }

    public String getGoodDescription() {
        return goodDescription;
    }

    public int getTimesVisited() {
        return timesVisited;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void addSeller(Seller seller){
        this.sellers.add(seller);
    }

    public void addRating(Rating rating){
        this.ratings.add(rating);
    }

    public void addComment(Comment comment){
        this.comments.add(comment);
    }

    public void addCharacteristic(Characteristic characteristic){
        this.characteristics.add(characteristic);
    }

    public String getProductId() {
        return productId;
    }

    public ProductState getProductState() {
        return productState;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public ArrayList<Seller> getSellers() {
        return sellers;
    }

    public double getPrice() {
        return price;
    }

    public String getProductName() {
        return productName;
    }

    public Category getCategory() {
        return category;
    }

    public Category getSubCategory() {
        return subCategory;
    }

    public ArrayList<Characteristic> getCharacteristics() {
        return characteristics;
    }

    public ArrayList<Rating> getRatings() {
        return ratings;
    }

    public ArrayList<Comment> getComments() {
        return comments;
    }
}

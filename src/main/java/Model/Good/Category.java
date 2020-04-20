package Model.Good;

import java.util.ArrayList;

public class Category {
    private String categoryName;
    private ArrayList<Characteristic> characteristics;
    private Category parentCategory;
    private ArrayList<Good> goodsInCategory;

    public Category(String categoryName, ArrayList<Characteristic> characteristics, Category parentCategory, ArrayList<Good> goodsInCategory) {
        this.categoryName = categoryName;
        this.characteristics = characteristics;
        this.parentCategory = parentCategory;
        this.goodsInCategory = goodsInCategory;
    }

    public void addCharacteristics(Characteristic characteristic) {
        this.characteristics.add(characteristic);
    }

    public void removeCharacteristics(Characteristic characteristic) {
        this.characteristics.remove(characteristic);
    }

    public void addGood(Good good) {
        this.goodsInCategory.add(good);
    }

    public void removeGood(Good good) {
        this.goodsInCategory.remove(good);
    }

    public String getCategoryName() {
        return categoryName;
    }

    public ArrayList<Characteristic> getCharacteristics() {
        return characteristics;
    }

    public Category getParentCategory() {
        return parentCategory;
    }

    public ArrayList<Good> getGoodsInCategory() {
        return goodsInCategory;
    }
}

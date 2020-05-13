package Model.Good;

import java.util.ArrayList;

public class Category {
    private String categoryName;
    private ArrayList<Characteristic> characteristics;
    private ArrayList<Category> subCategories;
    private ArrayList<Good> goodsInCategory;

    public Category(String categoryName, ArrayList<Characteristic> characteristics , ArrayList<Good> goodsInCategory) {
        this.categoryName = categoryName;
        this.characteristics = characteristics;
        this.goodsInCategory = goodsInCategory;
    }

    public void addSubCategory(Category category){
        this.subCategories.add(category);
    }

    public ArrayList<Category> getSubCategories() {
        return subCategories;
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

    public ArrayList<Good> getGoodsInCategory() {
        return goodsInCategory;
    }
}

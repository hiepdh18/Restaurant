package com.example.restaurant.DTO;

public class DishDTO {
    private int id, category;
    private String name, imageUrl;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public DishDTO(int id, int category, String name, String imageUrl) {
        this.id = id;
        this.category = category;
        this.name = name;
        this.imageUrl = imageUrl;
    }

    public DishDTO() {
    }
}

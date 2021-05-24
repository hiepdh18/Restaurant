package com.example.restaurant.DTO;

public class RoleDTO {
    int id;
    String name;

    public RoleDTO() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public RoleDTO(int id, String name) {
        this.id = id;
        this.name = name;
    }
}

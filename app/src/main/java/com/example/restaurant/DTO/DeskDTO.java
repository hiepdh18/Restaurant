package com.example.restaurant.DTO;

public class DeskDTO {
    int id;
    String  name,status;

    public DeskDTO() {
    }

    public DeskDTO(String name) {
        this.name = name;
        this.status = "false";
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

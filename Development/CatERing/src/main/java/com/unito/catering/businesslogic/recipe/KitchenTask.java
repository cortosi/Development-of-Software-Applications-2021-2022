package com.unito.catering.businesslogic.recipe;

import com.unito.catering.businesslogic.user.User;

public class KitchenTask {
    private int id;

    private String name;
    private boolean published;
    private User author;
    private User owner;
    private String description;

    public int getId() {
        return id;
    }
}

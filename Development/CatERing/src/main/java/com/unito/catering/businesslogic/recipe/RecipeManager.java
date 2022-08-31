package com.unito.catering.businesslogic.recipe;

import com.unito.catering.businesslogic.CatERing;
import com.unito.catering.businesslogic.general.UseCaseLogicException;
import com.unito.catering.businesslogic.user.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class RecipeManager {

    private CookBook cb = new CookBook();
    private KitchenTask currentKt;

    // INSTANCES METHODS
    public RecipeManager() {
        Recipe.loadAllRecipes();
    }

    public ObservableList<Recipe> getRecipes() {
        return FXCollections.unmodifiableObservableList(Recipe.getAllRecipes());
    }

}

package com.unito.catering.businesslogic;

import com.unito.catering.businesslogic.event.EventManager;
import com.unito.catering.businesslogic.menu.MenuManager;
import com.unito.catering.businesslogic.recipe.RecipeManager;
import com.unito.catering.businesslogic.task.TaskManager;
import com.unito.catering.businesslogic.turn.TurnManager;
import com.unito.catering.businesslogic.user.UserManager;
import com.unito.catering.persistence.MenuPersistence;
import com.unito.catering.persistence.TaskPersistence;

public class CatERing {
    private static CatERing singleInstance;

    public static CatERing getInstance() {
        if (singleInstance == null) {
            singleInstance = new CatERing();
        }
        return singleInstance;
    }

    private MenuManager menuMgr;
    private RecipeManager recipeMgr;
    private UserManager userMgr;
    private EventManager eventMgr;
    private MenuPersistence menuPersistence;

    private TaskManager taskMgr;
    private TurnManager turnMgr;
    private TaskPersistence taskPersistence;


    private CatERing() {
        menuMgr = new MenuManager();
        recipeMgr = new RecipeManager();
        userMgr = new UserManager();
        eventMgr = new EventManager();
        menuPersistence = new MenuPersistence();
        taskMgr = new TaskManager();
        turnMgr = new TurnManager();
        taskPersistence = new TaskPersistence();

        menuMgr.addEventReceiver(menuPersistence);
        taskMgr.addEventReceiver(taskPersistence);
    }


    public MenuManager getMenuManager() {
        return menuMgr;
    }

    public RecipeManager getRecipeManager() {
        return recipeMgr;
    }

    public UserManager getUserManager() {
        return userMgr;
    }

    public TurnManager getTurnManager() {
        return turnMgr;
    }

    public TaskManager getTaskManager() {
        return taskMgr;
    }


    public EventManager getEventManager() {
        return eventMgr;
    }

}

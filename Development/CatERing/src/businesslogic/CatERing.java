package businesslogic;

import businesslogic.event.EventManager;
import businesslogic.menu.Menu;
import businesslogic.menu.MenuManager;
import businesslogic.recipe.RecipeManager;
import businesslogic.task.Task;
import businesslogic.task.TaskManager;
import businesslogic.turn.TurnManager;
import businesslogic.user.UserManager;
import persistence.MenuPersistence;
import persistence.PersistenceManager;
import persistence.TaskPersistence;

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

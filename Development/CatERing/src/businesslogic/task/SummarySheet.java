package businesslogic.task;

import businesslogic.event.Service;
import businesslogic.menu.Menu;
import businesslogic.recipe.KitchenTask;
import businesslogic.recipe.Recipe;
import businesslogic.user.User;

import java.util.ArrayList;

public class SummarySheet {
    private User creator;
    private ArrayList<Task> tasks;
    private Service serviceAssigned;

    // CONSTRUCTORS
    public SummarySheet(Service ser, User usr) {
        this.creator = usr;
        this.serviceAssigned = ser;
        tasks = new ArrayList<>();
    }

    // Instances Methods

    public void initSheet() {
        Menu menuAassigned = this.serviceAssigned.getMenuAssigned();
        ArrayList<Recipe> allRecipes = menuAassigned.getAllRecipes();

        for (Recipe recipe : allRecipes) {
            tasks.add(new Task(recipe));
        }
    }

    public void resetSheet() {
        this.tasks = new ArrayList<>();
        initSheet();
    }

    public Task addTask(KitchenTask kt) {
        Task n_task = new Task(kt);
        tasks.add(n_task);

        return n_task;
    }

    public void addTask(Task task, int position) {
        this.tasks.add(position, task);
    }

    public void removeTask(Task task) {
        this.tasks.remove(task);
    }

    public boolean containsTask(Task task) {
        return this.tasks.contains(task);
    }

    public int tasksSize() {
        return this.tasks.size();
    }

    public User getCreator() {
        return creator;
    }

    public boolean isCreator(User user) {
        return (user.getId() == creator.getId());
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public Service getServiceAssigned() {
        return serviceAssigned;
    }


}

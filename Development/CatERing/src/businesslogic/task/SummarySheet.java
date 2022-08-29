package businesslogic.task;

import businesslogic.event.Service;
import businesslogic.menu.Menu;
import businesslogic.recipe.KitchenTask;
import businesslogic.recipe.Recipe;
import businesslogic.user.User;
import persistence.BatchUpdateHandler;
import persistence.PersistenceManager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SummarySheet {
    private int id;
    private User creator;
    private ArrayList<Task> tasks;
    private Service serviceAssigned;

    // CONSTRUCTORS
    public SummarySheet(Service ser, User usr) {
        this.creator = usr;
        this.serviceAssigned = ser;
        tasks = new ArrayList<>();
    }

    // PERSISTENCE METHODS
    public static void saveSheet(SummarySheet sheet, Menu m) {
        String sheetInsert = "INSERT INTO catering.summarysheets (id_chef, id_service) VALUES (?, ?);";
        int[] result = PersistenceManager.executeBatchUpdate(sheetInsert, 1, new BatchUpdateHandler() {
            @Override
            public void handleBatchItem(PreparedStatement ps, int batchCount) throws SQLException {
                ps.setInt(1, sheet.creator.getId());
                ps.setInt(2, sheet.serviceAssigned.getId());
            }

            @Override
            public void handleGeneratedIds(ResultSet rs, int count) throws SQLException {
                if (count == 0) {
                    sheet.id = rs.getInt(1);
                }
            }
        });
        if (result[0] > 0) {
            // SAVING ALL SHEET'S TASKS TOO
            Task.saveAllNewTasks(sheet, m);
        }
    }

    public static void reorderTasks(SummarySheet sheet) {
        String upd = "UPDATE catering.tasks SET position = ? WHERE id = ?";
        PersistenceManager.executeBatchUpdate(upd, sheet.getTasks().size(), new BatchUpdateHandler() {
            @Override
            public void handleBatchItem(PreparedStatement ps, int batchCount) throws SQLException {
                ps.setInt(1, batchCount);
                ps.setInt(2, sheet.getTasks().get(batchCount).getId());
            }

            @Override
            public void handleGeneratedIds(ResultSet rs, int count) throws SQLException {
            }
        });
    }

    // INSTANCES METHODS
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

    public int getTaskIndex(Task task) {
        return tasks.indexOf(task);
    }

    @Override
    public String toString() {
        return "-SUMMARY SHEET-" +
                "\n| Creator: " + creator +
                "\n| Tasks: " + tasks +
                "\n| serviceAssigned: " + serviceAssigned +
                "\n--------------";
    }

    public int getId() {
        return id;
    }
}

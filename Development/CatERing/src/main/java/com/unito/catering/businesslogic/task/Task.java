package com.unito.catering.businesslogic.task;

import com.unito.catering.businesslogic.menu.Menu;
import com.unito.catering.businesslogic.recipe.KitchenTask;
import com.unito.catering.businesslogic.turn.Turn;
import com.unito.catering.businesslogic.user.User;
import com.unito.catering.pertistence.BatchUpdateHandler;
import com.unito.catering.pertistence.PersistenceManager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static java.sql.Types.NULL;

public class Task {
    private int id;

    private KitchenTask ktAssigned;
    private Turn turnAssigned;
    private User cookAssigned;
    private int timeEstimate;
    private boolean completed;
    private int quantity;

    public Task(KitchenTask tkAssigned) {
        this.ktAssigned = tkAssigned;
    }

    // PERSISTENCE METHODS
    public static void saveAllNewTasks(SummarySheet sheet, Menu m) {
        String sheetInsert = "INSERT INTO catering.tasks (id_sheet, id_recipe, position) VALUES (?, ?, ?);";
        int[] result = PersistenceManager.executeBatchUpdate(sheetInsert, m.getAllRecipes().size(), new BatchUpdateHandler() {
            @Override
            public void handleBatchItem(PreparedStatement ps, int batchCount) throws SQLException {
                ps.setInt(1, sheet.getId());
                ps.setInt(2, m.getAllRecipes().get(batchCount).getId());
                ps.setInt(3, batchCount);
            }

            @Override
            public void handleGeneratedIds(ResultSet rs, int count) throws SQLException {
                sheet.getTasks().get(count).id = rs.getInt(1);
            }
        });
    }

    public static void saveNewTask(int sheet_id, Task task, int p_index) {
        String taskInsert = "INSERT INTO catering.tasks (id_sheet, position, id_recipe) " +
                "VALUES (" + sheet_id + ", " + p_index + ", " + task.getKtAssigned().getId() + ");";
        PersistenceManager.executeUpdate(taskInsert);
        task.id = PersistenceManager.getLastId();
    }

    public static void resetTasks(SummarySheet sheet) {
        // DELETING FIRST
        deleteAllTasks(sheet);

        // INSERTING
        saveAllNewTasks(sheet, sheet.getServiceAssigned().getMenuAssigned());
    }

    public static void deleteTask(Task task) {
        String taskDelete = "DELETE FROM tasks WHERE id = " + task.getId();
        PersistenceManager.executeUpdate(taskDelete);
    }

    public static void deleteAllTasks(SummarySheet sheet) {
        String deleteAll = "DELETE FROM tasks WHERE id_sheet = " + sheet.getId();
        PersistenceManager.executeUpdate(deleteAll);
    }

    public static void setTaskCompleted(Task task) {
        String deleteAll = "UPDATE tasks SET completed = 1 WHERE id = " + task.getId();
        PersistenceManager.executeUpdate(deleteAll);
    }

    public static void setCookChange(Task task) {
        String deleteAll = "UPDATE tasks SET id_cook = " + task.getCookAssigned().getId() +
                " WHERE id = " + task.getId();
        PersistenceManager.executeUpdate(deleteAll);
    }

    public static void setTurnChanged(Task task) {
        String deleteAll = "UPDATE tasks SET id_turn = " + task.getTurnAssigned().getId() +
                " WHERE id = " + task.getId();
        PersistenceManager.executeUpdate(deleteAll);
    }

    public static void taskTimeChanged(Task task) {
        String deleteAll = "UPDATE tasks SET timeEstimate = " + task.getTimeEstimate() +
                " WHERE id = " + task.getId();
        PersistenceManager.executeUpdate(deleteAll);
    }

    public static void taskQuantityChanged(Task task) {
        String deleteAll = "UPDATE tasks SET quantity = " + task.getQuantity() +
                " WHERE id = " + task.getId();
        PersistenceManager.executeUpdate(deleteAll);
    }

    public void assignTask(Turn turn, User cook) {
        this.turnAssigned = turn;
        this.cookAssigned = cook;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public void setDetails(int timeEstimate, int quantity) {
        this.setQuantity(quantity);
        this.setTimeEstimate(timeEstimate);
    }

    public int getId() {
        return id;
    }

    public Turn getTurnAssigned() {
        return turnAssigned;
    }

    public void setTurnAssigned(Turn turnAssigned) {
        this.turnAssigned = turnAssigned;
    }

    public User getCookAssigned() {
        return cookAssigned;
    }

    public void setCookAssigned(User cookAssigned) {
        this.cookAssigned = cookAssigned;
    }

    public KitchenTask getKtAssigned() {
        return ktAssigned;
    }

    public int getTimeEstimate() {
        return timeEstimate;
    }

    public void setTimeEstimate(int timeEstimate) {
        this.timeEstimate = timeEstimate;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "\n\tTask" +
                "id: " + id +
                ", ktAssigned: " + ktAssigned +
                ", turnAssigned: " + turnAssigned +
                ", cookAssigned: " + cookAssigned +
                ", timeEstimate: " + timeEstimate +
                ", completed: " + completed +
                ", quantity: " + quantity;
    }
}

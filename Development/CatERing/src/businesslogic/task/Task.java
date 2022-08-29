package businesslogic.task;

import businesslogic.recipe.KitchenTask;
import businesslogic.turn.Turn;
import businesslogic.user.User;
import persistence.BatchUpdateHandler;
import persistence.PersistenceManager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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

    public void assignTask(Turn turn, User cook) {
        this.turnAssigned = turn;
        this.cookAssigned = cook;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setDetails(int timeEstimate, int quantity) {
        this.setQuantity(quantity);
        this.setTimeEstimate(timeEstimate);
    }

    public void setCookAssigned(User cookAssigned) {
        this.cookAssigned = cookAssigned;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public void setTurnAssigned(Turn turnAssigned) {
        this.turnAssigned = turnAssigned;
    }

    public void setTimeEstimate(int timeEstimate) {
        this.timeEstimate = timeEstimate;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    // PERSISTENCE METHODS

    public static void saveAllNewTasks(SummarySheet sheet, ArrayList<Task> tasks) {
        String sheetInsert = "INSERT INTO catering.taskssheets (id_sheet, id_kitchentask, p_index) VALUES (?, ?, ?);";
        int[] result = PersistenceManager.executeBatchUpdate(sheetInsert, 1, new BatchUpdateHandler() {
            @Override
            public void handleBatchItem(PreparedStatement ps, int batchCount) throws SQLException {
                ps.setInt(1, sheet.getId());
                ps.setInt(2, tasks.get(batchCount).ktAssigned.getId());
                ps.setInt(3, batchCount);
            }

            @Override
            public void handleGeneratedIds(ResultSet rs, int count) throws SQLException {
                if (count == 0) {
                    tasks.get(count).id = rs.getInt(1);
                }
            }
        });
    }

    public static void saveNewTask(int sheet_id, Task task, int p_index) {
        String taskInsert = "INSERT INTO catering.SheetsTasks (sheetId, procedureId, position) " +
                "VALUES (" + sheet_id + ", " + task.ktAssigned.getId() + ", " + p_index + ");";
        PersistenceManager.executeUpdate(taskInsert);
//        task.id = PersistenceManager.getLastId();
    }
}

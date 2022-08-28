package businesslogic.task;

public interface TaskEventReceiver {
    void updateSheetCreated(SummarySheet sheet);

    void updateSheetReset(SummarySheet sheet);

    void updateTaskAdded(Task n_task);

    void updateTaskDeleted(Task task);

    void updateTasksSorted(SummarySheet sheet);

    void updateTaskAssigned(Task task);

    void updateTaskCompleted(Task task);

    void updateCookChanged(Task task);

    void updateCookRemoved(Task task);

    void updateTurnChanged(Task task);

    void updateTaskDetailed(Task task);

    void updateTaskTimeChanged(Task task);

    void updateTaskQuantityChanged(Task task);
}

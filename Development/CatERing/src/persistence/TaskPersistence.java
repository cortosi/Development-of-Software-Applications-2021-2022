package persistence;

import businesslogic.menu.Menu;
import businesslogic.task.SummarySheet;
import businesslogic.task.Task;
import businesslogic.task.TaskEventReceiver;

public class TaskPersistence implements TaskEventReceiver {
    @Override
    public void updateSheetCreated(SummarySheet sheet, Menu m) {
        SummarySheet.saveSheet(sheet, m);
    }

    @Override
    public void updateSheetReset(SummarySheet sheet) {
    }

    @Override
    public void updateTaskAdded(SummarySheet sheet, Task n_task) {
        Task.saveNewTask(sheet.getId(), n_task, sheet.getTaskIndex(n_task));
    }

    @Override
    public void updateTaskDeleted(Task task) {

    }

    @Override
    public void updateTasksSorted(SummarySheet sheet) {
        SummarySheet.reorderTasks(sheet);
    }

    @Override
    public void updateTaskAssigned(Task task) {

    }

    @Override
    public void updateTaskCompleted(Task task) {

    }

    @Override
    public void updateCookChanged(Task task) {

    }

    @Override
    public void updateCookRemoved(Task task) {

    }

    @Override
    public void updateTurnChanged(Task task) {

    }

    @Override
    public void updateTaskDetailed(Task task) {

    }

    @Override
    public void updateTaskTimeChanged(Task task) {

    }

    @Override
    public void updateTaskQuantityChanged(Task task) {

    }
}

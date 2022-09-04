package com.unito.catering.persistence;

import com.unito.catering.businesslogic.menu.Menu;
import com.unito.catering.businesslogic.task.SummarySheet;
import com.unito.catering.businesslogic.task.Task;
import com.unito.catering.businesslogic.task.TaskEventReceiver;

public class TaskPersistence implements TaskEventReceiver {

    @Override
    public void updateSheetCreated(SummarySheet sheet, Menu m) {
        SummarySheet.saveSheet(sheet, m);
    }

    @Override
    public void updateSheetReset(SummarySheet sheet) {
        Task.resetTasks(sheet);
    }

    @Override
    public void updateTaskAdded(SummarySheet sheet, Task n_task) {
        Task.saveNewTask(sheet.getId(), n_task, sheet.getTaskIndex(n_task));
    }

    @Override
    public void updateTaskDeleted(Task task) {
        Task.deleteTask(task);
    }

    @Override
    public void updateTasksSorted(SummarySheet sheet) {
        SummarySheet.reorderTasks(sheet);
    }

    @Override
    public void updateTaskAssigned(Task task) {
        SummarySheet.setAssignment(task);
    }

    @Override
    public void updateTaskCompleted(Task task) {
        Task.setTaskCompleted(task);
    }

    @Override
    public void updateCookChanged(Task task) {
        Task.setCookChanged(task);
    }

    @Override
    public void updateCookRemoved(Task task) {
        Task.setCookChanged(task);
    }

    @Override
    public void updateTurnChanged(Task task) {
        Task.setTurnChanged(task);
    }

    @Override
    public void updateTaskDetailed(Task task) {
        SummarySheet.setDetails(task);
    }

    @Override
    public void updateTaskTimeChanged(Task task) {
        Task.taskTimeChanged(task);
    }

    @Override
    public void updateTaskQuantityChanged(Task task) {
        Task.taskQuantityChanged(task);
    }
}

package businesslogic.task;

import businesslogic.CatERing;
import businesslogic.general.TaskException;
import businesslogic.general.UseCaseLogicException;
import businesslogic.event.Service;
import businesslogic.recipe.KitchenTask;
import businesslogic.turn.Turn;
import businesslogic.turn.WorkshiftBoard;
import businesslogic.user.User;
import persistence.MenuPersistence;
import persistence.TaskPersistence;

import java.util.ArrayList;

public class TaskManager {

    private SummarySheet currentSheet;
    private ArrayList<TaskEventReceiver> eventReceivers = new ArrayList<>();

    public SummarySheet createSheet(Service ser) throws UseCaseLogicException {
        User currentUser = CatERing.getInstance().getUserManager().getCurrentUser();
        if (!currentUser.isChef()
                || ser.getMenuAssigned() == null)
            throw new UseCaseLogicException();

        SummarySheet new_sheet = new SummarySheet(ser, currentUser);
        this.setCurrentSheet(new_sheet);
        notifySheetCreated(this.currentSheet);

        return new_sheet;
    }

    public void resetSheet(SummarySheet sheet) throws UseCaseLogicException, TaskException {
        User currentUser = CatERing.getInstance().getUserManager().getCurrentUser();
        if (!(currentUser.isChef()))
            throw new UseCaseLogicException();

        if (!(sheet.isCreator(currentUser)))
            throw new TaskException();

        setCurrentSheet(sheet);
        sheet.resetSheet();

        notifySheetReset(sheet);
    }

    public void addTask(KitchenTask kt) throws TaskException {
        if (currentSheet == null)
            throw new TaskException();

        notifyTaskAdded(currentSheet.addTask(kt));
    }

    public void deleteTask(Task task) throws TaskException {
        if (currentSheet == null)
            throw new TaskException();

        if (!(currentSheet.containsTask(task)))
            throw new TaskException();

        currentSheet.removeTask(task);

        notifyTaskDeleted(task);
    }

    public void sortTask(Task task, int position) throws TaskException {
        if (currentSheet == null ||
                position < 0 || position > currentSheet.tasksSize())
            throw new TaskException();

        currentSheet.removeTask(task);
        currentSheet.addTask(task, position);

        notifyTasksSorted(currentSheet);
    }

    public WorkshiftBoard checkWorkshiftBoard() {
        return CatERing.getInstance().getTurnManager().getWb();
    }

    public void assignTask(Task toAssign, Turn turn, User cook) throws UseCaseLogicException, TaskException {
        if (currentSheet == null || !(currentSheet.containsTask(toAssign)))
            throw new UseCaseLogicException();

        if (cook != null && cook.isBusy())
            throw new TaskException();

        toAssign.assignTask(turn, cook);

        this.notifyTaskAssigned(toAssign);
    }

    public void assignTask(Task toAssign, Turn turn) throws TaskException, UseCaseLogicException {
        this.assignTask(toAssign, turn, null);
    }

    public void setTaskCompleted(Task task) throws UseCaseLogicException {
        if (currentSheet == null || !(currentSheet.containsTask(task)))
            throw new UseCaseLogicException();

        task.setCompleted(true);

        this.notifyTaskCompleted(task);
    }

    public void editCookAssigned(Task toChange, User toAssign) throws UseCaseLogicException, TaskException {
        if (currentSheet == null || !(currentSheet.containsTask(toChange)))
            throw new UseCaseLogicException();

        if (toAssign != null && toAssign.isBusy())
            throw new TaskException();

        toChange.setCookAssigned(toAssign);

        this.notifyCookChanged(toChange);
    }

    public void removeCookAssigned(Task toChange) throws UseCaseLogicException {
        if (currentSheet == null || !(currentSheet.containsTask(toChange)))
            throw new UseCaseLogicException();

        toChange.setCookAssigned(null);

        this.notifyCookRemoved(toChange);
    }

    public void changeTurnAssigned(Task toChange, Turn toAssign) throws UseCaseLogicException {
        if (currentSheet == null || !(currentSheet.containsTask(toChange)))
            throw new UseCaseLogicException();

        toChange.setTurnAssigned(toAssign);

        this.notifyTurnChanged(toChange);
    }

    public void setTaskDetails(Task toDetail, int timeEstimate, int quantity) throws UseCaseLogicException {
        if (currentSheet == null || !(currentSheet.containsTask(toDetail)))
            throw new UseCaseLogicException();

        toDetail.setDetails(timeEstimate, quantity);

        this.notifyTaskDetailed(toDetail);
    }

    public void changeTaskTimeEstimate(Task toChange, int timeEstimate) throws UseCaseLogicException {
        if (currentSheet == null || !(currentSheet.containsTask(toChange)))
            throw new UseCaseLogicException();

        toChange.setTimeEstimate(timeEstimate);

        this.notifyTaskTimeChanged(toChange);
    }

    public void changeTaskQuantity(Task toChange, int quantity) throws UseCaseLogicException {
        if (currentSheet == null || !(currentSheet.containsTask(toChange)))
            throw new UseCaseLogicException();

        toChange.setQuantity(quantity);

        this.notifyTaskQuantityChanged(toChange);
    }

    public void setCurrentSheet(SummarySheet sheet) {
        this.currentSheet = sheet;
    }

    public void openSheet(SummarySheet sheet) throws UseCaseLogicException {
        User currentUser = CatERing.getInstance().getUserManager().getCurrentUser();
        if (!currentUser.isChef())
            throw new UseCaseLogicException();

        this.setCurrentSheet(sheet);
    }

    // EVENT SENDER METHODS
    private void notifySheetCreated(SummarySheet sheet) {
        for (TaskEventReceiver er : eventReceivers) {
            er.updateSheetCreated(sheet);
        }
    }

    private void notifySheetReset(SummarySheet sheet) {
        for (TaskEventReceiver er : eventReceivers) {
            er.updateSheetReset(sheet);
        }
    }

    private void notifyTaskAdded(Task task) {
        for (TaskEventReceiver er : eventReceivers) {
            er.updateTaskAdded(task);
        }
    }

    private void notifyTaskDeleted(Task task) {
        for (TaskEventReceiver er : eventReceivers) {
            er.updateTaskDeleted(task);
        }
    }

    private void notifyTasksSorted(SummarySheet sheet) {
        for (TaskEventReceiver er : eventReceivers) {
            er.updateTasksSorted(sheet);
        }
    }

    private void notifyTaskAssigned(Task task) {
        for (TaskEventReceiver er : eventReceivers) {
            er.updateTaskAssigned(task);
        }
    }

    private void notifyTaskCompleted(Task task) {
        for (TaskEventReceiver er : eventReceivers) {
            er.updateTaskCompleted(task);
        }

    }

    private void notifyCookChanged(Task task) {
        for (TaskEventReceiver er : eventReceivers) {
            er.updateCookChanged(task);
        }
    }

    private void notifyCookRemoved(Task task) {
        for (TaskEventReceiver er : eventReceivers) {
            er.updateCookRemoved(task);
        }

    }

    private void notifyTurnChanged(Task task) {
        for (TaskEventReceiver er : eventReceivers) {
            er.updateTurnChanged(task);
        }
    }

    private void notifyTaskDetailed(Task task) {
        for (TaskEventReceiver er : eventReceivers) {
            er.updateTaskDetailed(task);
        }

    }

    private void notifyTaskTimeChanged(Task task) {
        for (TaskEventReceiver er : eventReceivers) {
            er.updateTaskTimeChanged(task);
        }

    }

    private void notifyTaskQuantityChanged(Task task) {
        for (TaskEventReceiver er : eventReceivers) {
            er.updateTaskQuantityChanged(task);
        }

    }


    public void addEventReceiver(TaskEventReceiver er) {
        this.eventReceivers.add(er);
    }
}

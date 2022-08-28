package businesslogic.task;

import businesslogic.recipe.KitchenTask;
import businesslogic.turn.Turn;
import businesslogic.user.User;

public class Task {
    private KitchenTask tkAssigned;
    private Turn turnAssigned;
    private User cookAssigned;
    private int timeEstimate;
    private boolean completed;
    private int quantity;

    public Task(KitchenTask tkAssigned) {
        this.tkAssigned = tkAssigned;
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
}

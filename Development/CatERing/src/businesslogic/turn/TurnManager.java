package businesslogic.turn;

import javafx.collections.ObservableList;

import java.util.List;

public class TurnManager {
    private WorkshiftBoard wb;

    public TurnManager() {
        this.wb = new WorkshiftBoard();
    }

    public WorkshiftBoard getWb() {
        return wb;
    }

    public ObservableList<Turn> getTurns() {
        return wb.getTurns();
    }
}

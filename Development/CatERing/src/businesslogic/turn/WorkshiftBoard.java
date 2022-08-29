package businesslogic.turn;

import businesslogic.menu.Section;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class WorkshiftBoard {

    private Map<Integer, Turn> turns;

    public void loadWorkshiftBoard() {
        turns = Turn.loadAllTurns();
    }

    public ObservableList<Turn> getTurns() {
        loadWorkshiftBoard();
        return FXCollections.observableArrayList(turns.values());
    }
}

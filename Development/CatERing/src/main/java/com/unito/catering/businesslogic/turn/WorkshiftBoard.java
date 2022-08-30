package com.unito.catering.businesslogic.turn;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

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

package com.unito.catering.businesslogic.turn;

import javafx.collections.ObservableList;

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

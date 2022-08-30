package com.unito.catering.businesslogic.turn;

import com.unito.catering.pertistence.PersistenceManager;
import com.unito.catering.pertistence.ResultHandler;
import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.Date;

public class Turn {
    private int id;
    private Date date;
    private String place;
    private Time start;
    private Time end;

    public static ObservableMap<Integer, Turn> loadAllTurns() {
        ObservableMap<Integer, Turn> turns = FXCollections.observableHashMap();

        String query = "SELECT * FROM turns";
        PersistenceManager.executeQuery(query, new ResultHandler() {
            @Override
            public void handle(ResultSet rs) throws SQLException {
                Turn t = new Turn();
                t.id = rs.getInt("id");
                t.date = rs.getDate("date");
                t.start = rs.getTime("h_start");
                t.end = rs.getTime("h_end");
                t.place = rs.getString("place");
                turns.put(t.id, t);
            }
        });
        return turns;
    }

    @Override
    public String toString() {
        return "id: " + id +
                ", date: " + date +
                ", place: " + place + '\'' +
                ", start: " + start +
                ", end: " + end;
    }

    public int getId() {
        return id;
    }
}

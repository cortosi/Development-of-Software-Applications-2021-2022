package com.unito.catering.businesslogic.event;

import com.unito.catering.businesslogic.menu.Menu;
import com.unito.catering.pertistence.PersistenceManager;
import com.unito.catering.pertistence.ResultHandler;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;

public class Service implements EventItemInfo {
    private int id;
    private String name;
    private Date date;
    private Time timeStart;
    private Time timeEnd;
    private int participants;

    private Menu menuAssigned;

    public Service(String name) {
        this.name = name;
    }

    public Service() {
    }

    public static Service loadServiceById(int id) {
        Service service = new Service();
        String query = "SELECT * FROM services WHERE id=" + id;
        PersistenceManager.executeQuery(query, new ResultHandler() {
            @Override
            public void handle(ResultSet rs) throws SQLException {
                service.name = rs.getString("name");
                service.id = rs.getInt("id");
                service.date = rs.getDate("service_date");
                service.timeStart = rs.getTime("time_start");
                service.timeEnd = rs.getTime("time_end");
                service.menuAssigned = Menu.getMenuById(rs.getInt("id_menu"));
            }
        });
        return service;
    }

    public static ObservableList<Service> loadServiceInfoForEvent(int event_id) {
        ObservableList<Service> result = FXCollections.observableArrayList();
        String query = "SELECT id, name, service_date, time_start, time_end, expected_participants, id_menu " +
                "FROM Services WHERE event_id = " + event_id;
        PersistenceManager.executeQuery(query, new ResultHandler() {
            @Override
            public void handle(ResultSet rs) throws SQLException {
                String s = rs.getString("name");
                Service serv = new Service(s);
                serv.id = rs.getInt("id");
                serv.date = rs.getDate("service_date");
                serv.timeStart = rs.getTime("time_start");
                serv.timeEnd = rs.getTime("time_end");
                serv.participants = rs.getInt("expected_participants");

                // Loading Menu and assigning it
                int menu_id = rs.getInt("id_menu");
                serv.menuAssigned = Menu.getMenuById(menu_id);
                result.add(serv);
            }
        });
        return result;
    }

    // STATIC METHODS FOR PERSISTENCE

    public String toString() {
        return name + ": " + date + " (" + timeStart + "-" + timeEnd + "), " + participants + " pp." + "menu assigned: " + menuAssigned;
    }

    public int getId() {
        return id;
    }

    public Menu getMenuAssigned() {
        return menuAssigned;
    }
}

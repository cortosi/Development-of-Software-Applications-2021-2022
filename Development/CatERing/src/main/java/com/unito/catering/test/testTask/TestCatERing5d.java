package com.unito.catering.test.testTask;

import com.unito.catering.businesslogic.CatERing;
import com.unito.catering.businesslogic.event.Service;
import com.unito.catering.businesslogic.general.TaskException;
import com.unito.catering.businesslogic.general.UseCaseLogicException;
import com.unito.catering.businesslogic.menu.Menu;
import com.unito.catering.businesslogic.task.SummarySheet;
import com.unito.catering.businesslogic.turn.Turn;
import com.unito.catering.businesslogic.user.User;

import java.util.List;

public class TestCatERing5d {
    public static void main(String[] args) throws UseCaseLogicException, TaskException {
        System.out.println("[TEST]: FAKE LOGIN");
        CatERing.getInstance().getUserManager().fakeLogin("Lidia");
        System.out.println(CatERing.getInstance().getUserManager().getCurrentUser());

        // LOADING DUMMY DATAS
        Menu.loadAllMenus();
        Service service1 = Service.loadServiceById(1);
        List<Turn> turns = CatERing.getInstance().getTurnManager().getTurns();

        // TEST: start
        System.out.println("\n[TEST]: GENERATING SUMMARY SHEET");
        SummarySheet sheet1 = CatERing.getInstance().getTaskManager().createSheet(service1);

        System.out.println("\n[TEST]: TASK ASSIGNMENT (WITH COOK)");
        CatERing.getInstance().getTaskManager().assignTask(sheet1.getTasks().get(0), turns.get(0), User.loadUserById(5));

        System.out.println("\n[TEST]: TASK INFO DEFINITION");
        CatERing.getInstance().getTaskManager().setTaskDetails(sheet1.getTasks().get(0), 90, 2);

        System.out.println("before edit: " + sheet1);
        System.out.println("\n[TEST]: EDIT TURN ASSIGNED");
        CatERing.getInstance().getTaskManager().changeTurnAssigned(sheet1.getTasks().get(0), turns.get(1));
        System.out.println("after edit: " + sheet1);
        // TEST: end
    }
}

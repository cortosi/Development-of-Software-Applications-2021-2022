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

public class TestCatERing {
    public static void main(String[] args) throws UseCaseLogicException, TaskException {
        System.out.println("[TEST]: FAKE LOGIN");
        CatERing.getInstance().getUserManager().fakeLogin("Lidia");
        System.out.println(CatERing.getInstance().getUserManager().getCurrentUser());

        // LOADING DUMMY DATAS
        Menu.loadAllMenus();
        Service service = Service.loadServiceById(1);

        // TEST: Starting
        System.out.println("\n[TEST]: GENERATING SUMMARY SHEET");
        SummarySheet sheet = CatERing.getInstance().getTaskManager().createSheet(service);

        System.out.println(sheet);

        System.out.println("\n[TEST]: REORDERING SUMMARY SHEET's TASKS");
        CatERing.getInstance().getTaskManager().sortTask(sheet.getTasks().get(0), 3);

        System.out.println(sheet);

        System.out.println("\n[TEST]: GET WORKSHIFTBOARD");
        List<Turn> turns = CatERing.getInstance().getTurnManager().getTurns();

        System.out.println(turns);

        System.out.println("\n[TEST]: TASK ASSIGNMENT (WITH COOK)");
        CatERing.getInstance().getTaskManager().assignTask(sheet.getTasks().get(0), turns.get(0), User.loadUserById(5));

        System.out.println(sheet);

        System.out.println("\n[TEST]: TASK INFO DEFINITION");
        CatERing.getInstance().getTaskManager().setTaskDetails(sheet.getTasks().get(0), 90, 2);

        System.out.println(sheet);
        // TEST: End
    }
}

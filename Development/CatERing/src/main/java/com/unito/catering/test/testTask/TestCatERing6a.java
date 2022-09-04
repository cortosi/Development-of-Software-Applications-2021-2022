package com.unito.catering.test.TestTask;

import com.unito.catering.businesslogic.CatERing;
import com.unito.catering.businesslogic.event.Service;
import com.unito.catering.businesslogic.general.TaskException;
import com.unito.catering.businesslogic.general.UseCaseLogicException;
import com.unito.catering.businesslogic.menu.Menu;
import com.unito.catering.businesslogic.task.SummarySheet;
import com.unito.catering.businesslogic.turn.Turn;
import com.unito.catering.businesslogic.user.User;

import java.util.List;

public class TestCatERing6a {
    public static void main(String[] args) throws UseCaseLogicException, TaskException {
        try {
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

            System.out.println(sheet1);

            System.out.println("\n[TEST]: TASK ASSIGNMENT (WITH COOK)");
            CatERing.getInstance().getTaskManager().assignTask(sheet1.getTasks().get(0), turns.get(0), User.loadUserById(5));

            System.out.println(sheet1);

            System.out.println("\n[TEST]: TASK INFO DEFINITION");
            CatERing.getInstance().getTaskManager().setTaskDetails(sheet1.getTasks().get(0), 90, 2);

            System.out.println(sheet1);

            System.out.println("\n[TEST]: EDIT TASK TIME");
            CatERing.getInstance().getTaskManager().changeTaskTimeEstimate(sheet1.getTasks().get(0), 120);

            System.out.println(sheet1);
            // TEST: end
        } catch (UseCaseLogicException | TaskException e) {
            System.out.println("Errore su estensione 6a");
            e.printStackTrace();
        }
    }
}

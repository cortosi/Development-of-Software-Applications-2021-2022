package com.unito.catering.test.testTask;

import com.unito.catering.businesslogic.CatERing;
import com.unito.catering.businesslogic.event.Service;
import com.unito.catering.businesslogic.general.UseCaseLogicException;
import com.unito.catering.businesslogic.menu.Menu;
import com.unito.catering.businesslogic.task.SummarySheet;
import com.unito.catering.businesslogic.turn.Turn;

import java.util.List;

public class TestCatERing5a {
    public static void main(String[] args) throws UseCaseLogicException {
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

        System.out.println("\n[TEST]: SET TASK COMPLETED");
        CatERing.getInstance().getTaskManager().setTaskCompleted(sheet1.getTasks().get(0));

        System.out.println(sheet1);
        // TEST: end

    }
}
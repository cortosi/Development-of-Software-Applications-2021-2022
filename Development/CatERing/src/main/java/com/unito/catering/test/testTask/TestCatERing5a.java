package com.unito.catering.test.TestTask;

import com.unito.catering.businesslogic.CatERing;
import com.unito.catering.businesslogic.event.Service;
import com.unito.catering.businesslogic.general.UseCaseLogicException;
import com.unito.catering.businesslogic.menu.Menu;
import com.unito.catering.businesslogic.task.SummarySheet;

public class TestCatERing5a {
    public static void main(String[] args) throws UseCaseLogicException {
        try {
            System.out.println("[TEST]: FAKE LOGIN");
            CatERing.getInstance().getUserManager().fakeLogin("Lidia");
            System.out.println(CatERing.getInstance().getUserManager().getCurrentUser());

            // LOADING DUMMY DATAS
            Menu.loadAllMenus();
            Service service1 = Service.loadServiceById(1);

            // TEST: start
            System.out.println("\n[TEST]: GENERATING SUMMARY SHEET");
            SummarySheet sheet1 = CatERing.getInstance().getTaskManager().createSheet(service1);

            System.out.println(sheet1);

            System.out.println("\n[TEST]: SET TASK COMPLETED");
            CatERing.getInstance().getTaskManager().setTaskCompleted(sheet1.getTasks().get(0));

            System.out.println(sheet1);
            // TEST: end
        } catch (Exception e) {
            System.out.println("Errore su estensione 5a");
            e.printStackTrace();
        }
    }
}

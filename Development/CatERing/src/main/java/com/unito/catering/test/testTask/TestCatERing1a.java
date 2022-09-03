package com.unito.catering.test.testTask;

import com.unito.catering.businesslogic.CatERing;
import com.unito.catering.businesslogic.event.Service;
import com.unito.catering.businesslogic.general.TaskException;
import com.unito.catering.businesslogic.general.UseCaseLogicException;
import com.unito.catering.businesslogic.menu.Menu;
import com.unito.catering.businesslogic.task.SummarySheet;
import com.unito.catering.businesslogic.task.Task;

import java.sql.DriverManager;

public class TestCatERing1a {
    public static void main(String[] args) throws UseCaseLogicException {
        try {
            System.out.println("[TEST]: FAKE LOGIN");
            CatERing.getInstance().getUserManager().fakeLogin("Lidia");
            System.out.println(CatERing.getInstance().getUserManager().getCurrentUser());

            // LOADING DUMMY DATAS
            Menu.loadAllMenus();
            Service service1 = Service.loadServiceById(1);
            Service service2 = Service.loadServiceById(2);

            // TEST: start
            System.out.println("\n[TEST]: GENERATING SUMMARY SHEET");
            SummarySheet sheet1 = CatERing.getInstance().getTaskManager().createSheet(service1);
            System.out.println("First sheet created: \n" + sheet1);

            System.out.println("\n[TEST]: GENERATING SUMMARY SHEET");
            SummarySheet sheet2 = CatERing.getInstance().getTaskManager().createSheet(service2);
            System.out.println("Second sheet created: \n" + sheet2);

            System.out.println("\n[TEST]: OPEN SUMMARY SHEET");
            System.out.println("Open first sheet: \n" + CatERing.getInstance().getTaskManager().openSheet(sheet1));
            // TEST: end
        } catch (TaskException e) {
            System.out.println("Errore su estensione 1a");
            e.printStackTrace();
        }
    }
}

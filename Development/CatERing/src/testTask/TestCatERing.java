package testTask;

import businesslogic.CatERing;
import businesslogic.event.Service;
import businesslogic.general.TaskException;
import businesslogic.general.UseCaseLogicException;
import businesslogic.menu.Menu;
import businesslogic.task.SummarySheet;
import businesslogic.turn.Turn;
import businesslogic.turn.WorkshiftBoard;

import java.util.List;

public class TestCatERing {
    public static void main(String[] args) throws UseCaseLogicException, TaskException {
        System.out.println("[TEST]: FAKE LOGIN");
        CatERing.getInstance().getUserManager().fakeLogin("Lidia");
        System.out.println(CatERing.getInstance().getUserManager().getCurrentUser());

        // LOADING DUMMY DATAS
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
    }
}

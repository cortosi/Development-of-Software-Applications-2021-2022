package testTask;

import businesslogic.CatERing;
import businesslogic.event.Event;
import businesslogic.event.Service;
import businesslogic.general.UseCaseLogicException;
import businesslogic.recipe.Recipe;
import businesslogic.task.SummarySheet;

public class TestCatERing {
    public static void main(String[] args) throws UseCaseLogicException {
//        System.out.println("TEST DATABASE CONNECTION");
//        PersistenceManager.testSQLConnection();

        System.out.println("[TEST]: FAKE LOGIN");
        CatERing.getInstance().getUserManager().fakeLogin("Lidia");
        System.out.println(CatERing.getInstance().getUserManager().getCurrentUser());

        CatERing.getInstance().getMenuManager().getAllMenus();
        Recipe.loadAllRecipes();

        Event event = CatERing.getInstance().getEventManager().getEventInfo().get(1);
        Service service = event.getServices().get(0);

        System.out.println("\n[TEST]: GENERATING SUMMARY SHEET");
        SummarySheet sheet = CatERing.getInstance().getTaskManager().createSheet(service);
        System.out.println(sheet);
    }
}

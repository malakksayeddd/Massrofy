import controller.BudgetController;
import view.SetupScreen;

public class Main {
    public static void main(String[] args) {
        BudgetController controller = new BudgetController();
        new SetupScreen(controller);
        
    }
}
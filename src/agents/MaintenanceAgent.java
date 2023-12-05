package agents;

import behaviors.MaintenanceBehaviour;
import jade.core.Agent;
import ui.JavaFXApplication;

public class MaintenanceAgent extends Agent {
    private String mainControllerName;

    protected void setup() {
        mainControllerName = "MainController";
        String initialMessage = "My name is " + this.getLocalName() +" Agent"+ " and I am responisble for the maintenance tasks.";
        System.out.println(initialMessage);
        JavaFXApplication.appendMessage(initialMessage);

        addBehaviour(new MaintenanceBehaviour(this));
    }

    public String getMainControllerName() {
        return mainControllerName;
    }
}

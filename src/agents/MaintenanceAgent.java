package agents;

import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import ui.JavaFXApplication;

public class MaintenanceAgent extends Agent {

    private String mainControllerName;

    protected void setup() {
        mainControllerName = "MainController";
        String initialMessage = "Hello. My name is " + this.getLocalName() + " and I am ready for maintenance tasks.";
        System.out.println(initialMessage);
        JavaFXApplication.appendMessage(initialMessage);

        addBehaviour(new MaintenanceBehaviour(this));
    }

    private class MaintenanceBehaviour extends CyclicBehaviour {
        private static final int MAINTENANCE_INTERVAL = 20000; 
        private long lastMaintenanceTime = System.currentTimeMillis();

        public MaintenanceBehaviour(MaintenanceAgent maintenanceAgent) {
            super(maintenanceAgent);
        }

        public void action() {
            long currentTime = System.currentTimeMillis();
            if (currentTime - lastMaintenanceTime >= MAINTENANCE_INTERVAL) {
                String maintenanceMessage = myAgent.getLocalName() + ": Performing maintenance task.";
                System.out.println(maintenanceMessage);
                JavaFXApplication.appendMessage(maintenanceMessage);

                ACLMessage reportMessage = new ACLMessage(ACLMessage.INFORM);
                reportMessage.addReceiver(getAID(mainControllerName));
                reportMessage.setContent("Maintenance: Report Status");
                myAgent.send(reportMessage);
                ACLMessage reply = blockingReceive();
                if (reply != null && reply.getContent().matches("Done")) {
                    String maintenanceDoneMessage = myAgent.getLocalName() + ": Maintenance Done!";
                    System.out.println(maintenanceDoneMessage);
                    JavaFXApplication.appendMessage(maintenanceDoneMessage);
                }

                lastMaintenanceTime = currentTime;
            } else {
                block(MAINTENANCE_INTERVAL - (currentTime - lastMaintenanceTime));
            }
        }
    }
}

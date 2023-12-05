package behaviors;

import agents.MaintenanceAgent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import ui.JavaFXApplication;

public class MaintenanceBehaviour extends CyclicBehaviour {
    private static final int MAINTENANCE_INTERVAL = 20000; 
    private long lastMaintenanceTime = System.currentTimeMillis();
    private final MaintenanceAgent maintenanceAgent;

    public MaintenanceBehaviour(MaintenanceAgent agent) {
        super(agent);
        this.maintenanceAgent = agent;
    }

    public void action() {
        long currentTime = System.currentTimeMillis();
        if (currentTime - lastMaintenanceTime >= MAINTENANCE_INTERVAL) {
            String maintenanceMessage = maintenanceAgent.getLocalName() + ": Performing maintenance task.";
            System.out.println(maintenanceMessage);
            JavaFXApplication.appendMessage(maintenanceMessage);

            ACLMessage reportMessage = new ACLMessage(ACLMessage.INFORM);
            reportMessage.addReceiver(maintenanceAgent.getAID(maintenanceAgent.getMainControllerName()));
            reportMessage.setContent("Maintenance: Report Status");
            maintenanceAgent.send(reportMessage);

            ACLMessage reply = maintenanceAgent.blockingReceive();
            if (reply != null && reply.getContent().matches("Done")) {
                String maintenanceDoneMessage = maintenanceAgent.getLocalName() + ": Maintenance Done!";
                System.out.println(maintenanceDoneMessage);
                JavaFXApplication.appendMessage(maintenanceDoneMessage);
            }

            lastMaintenanceTime = currentTime;
        } else {
            maintenanceAgent.doWait(MAINTENANCE_INTERVAL - (currentTime - lastMaintenanceTime));
        }
    }
}

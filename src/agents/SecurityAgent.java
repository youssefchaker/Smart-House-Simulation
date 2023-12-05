package agents;

import jade.core.Agent;
import jade.lang.acl.ACLMessage;
import ui.JavaFXApplication;
import behaviors.SecurityBehaviour;

public class SecurityAgent extends Agent {
    private String mainControllerName;
    public static final int ALERT_PROBABILITY = 20;

    protected void setup() {
        mainControllerName = "MainController";
        String initialMessage = "My name is " + this.getLocalName() +" Agent"+ " and I manage security in the smart house.";
        System.out.println(initialMessage);
        JavaFXApplication.appendMessage(initialMessage);
        addBehaviour(new SecurityBehaviour(this, 10000));
    }
    
    public void handleIntruderDetected() {
        ACLMessage alertMsg = new ACLMessage(ACLMessage.INFORM);
        alertMsg.addReceiver(getAID(mainControllerName));
        alertMsg.setContent("Security Alert: Intruder detected!");
        send(alertMsg);

        String alertMessage = getLocalName()+ " Agent " + ": Security Alert - Intruder detected!";
        System.out.println(alertMessage);
        JavaFXApplication.appendMessage(alertMessage);
    }
}   

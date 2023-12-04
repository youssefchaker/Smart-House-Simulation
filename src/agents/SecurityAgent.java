package agents;

import jade.core.Agent;
import jade.core.behaviours.TickerBehaviour;
import jade.lang.acl.ACLMessage;
import ui.JavaFXApplication;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;

public class SecurityAgent extends Agent {
    private String mainControllerName;
    private static final int ALERT_PROBABILITY = 20;

    protected void setup() {
        mainControllerName = "MainController";
        String initialMessage = "Hello. My name is " + this.getLocalName() + " and I manage security in the smart house.";
        System.out.println(initialMessage);
        JavaFXApplication.appendMessage(initialMessage);
        addBehaviour(new SecurityBehaviour(this, 10000)); 
    }

    private class SecurityBehaviour extends TickerBehaviour {
        public SecurityBehaviour(Agent agent, long period) {
            super(agent, period);
        }

        protected void onTick() {
            if (isNightTime()) {
                Random rand = new Random();
                if (rand.nextInt(100) < ALERT_PROBABILITY && isNightTime()) {
                    ACLMessage alertMsg = new ACLMessage(ACLMessage.INFORM);
                    alertMsg.addReceiver(getAID(mainControllerName));
                    alertMsg.setContent("Security Alert: Intruder detected!");
                    send(alertMsg);

                    String alertMessage = myAgent.getLocalName() + ": Security Alert - Intruder detected!";
                    System.out.println(alertMessage);
                    JavaFXApplication.appendMessage(alertMessage);
                }
            }
        }

        private boolean isNightTime() {
            Calendar calendar = Calendar.getInstance();
            int hour = calendar.get(Calendar.HOUR_OF_DAY);
            return (hour >= 23 || hour < 6);
        }
    }
}

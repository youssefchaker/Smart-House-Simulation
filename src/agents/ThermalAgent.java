package agents;

import jade.core.Agent;
import jade.core.behaviours.TickerBehaviour;
import jade.lang.acl.ACLMessage;
import ui.JavaFXApplication;

import java.util.Random;

public class ThermalAgent extends Agent {
    private static final int HEAT_INCREASE_INTERVAL = 10000; 
    private static final int ALERT_THRESHOLD = 35; 
    private String mainControllerName;
    private int globalHeatLevel = 25;

    protected void setup() {
        mainControllerName = "MainController";
        String initialMessage = "My name is " + this.getLocalName() +" Agent"+ " and I manage thermal conditions in the smart house.";
        System.out.println(initialMessage);
        JavaFXApplication.appendMessage(initialMessage);
        addBehaviour(new ThermalBehaviour(this, HEAT_INCREASE_INTERVAL));
    }

    private class ThermalBehaviour extends TickerBehaviour {
        public ThermalBehaviour(Agent agent, long period) {
            super(agent, period);
        }

        protected void onTick() {
            Random rand = new Random();
            int heatIncrease = rand.nextInt(3);
            globalHeatLevel += heatIncrease;

            String heatLevelMessage =myAgent.getLocalName()+ "Global Heat Level: " + globalHeatLevel + "°C";
            System.out.println(heatLevelMessage);
            JavaFXApplication.appendMessage(heatLevelMessage);

            if (globalHeatLevel >= ALERT_THRESHOLD) {
                ACLMessage alertMsg = new ACLMessage(ACLMessage.INFORM);
                alertMsg.addReceiver(getAID(mainControllerName));
                alertMsg.setContent(myAgent.getLocalName()+": High temperature detected!");
                send(alertMsg);

                String alertMessage = myAgent.getLocalName()+ " Agent " + ": Thermal Alert - High temperature detected!";
                System.out.println(alertMessage);
                JavaFXApplication.appendMessage(alertMessage);

                ACLMessage reply = blockingReceive();
                if (reply != null && reply.getContent().equals("DecreaseTemperature")) {
                    globalHeatLevel = rand.nextInt(6) + 25;
                    String decreasedMessage =myAgent.getLocalName()+ " Agent " +  ":Global Heat Level decreased to: " + globalHeatLevel + "°C";
                    System.out.println(decreasedMessage);
                    JavaFXApplication.appendMessage(decreasedMessage);
                }
            }
        }
    }
}

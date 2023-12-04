package agents;

import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;
import ui.JavaFXApplication;

import java.util.Random;

public class EnergyManagementAgent extends Agent {
    private static final double CHANGE_STATE_PROBABILITY = 0.6;

    private String mainControllerName;
    private boolean fridgeState;
    private boolean tvState;
    private boolean ovenState;
    private int threshold;

    protected void setup() {
        mainControllerName = "MainController";
        fridgeState = true;
        tvState = true;
        ovenState = true;

        String initialMessage = "Hello. My name is " + this.getLocalName() + " and I manage electric energy in the smart house.";
        System.out.println(initialMessage);
        JavaFXApplication.appendMessage(initialMessage);

        addBehaviour(new EnergyManagementBehaviour(this));
        addBehaviour(new PeriodicStateChangeBehaviour(this));
        addBehaviour(new PeriodicThresholdUpdateBehaviour(this));
    }

    private void turnOffDevice(int deviceToTurnOff) {
        String deviceName = "";
        switch (deviceToTurnOff) {
            case 0:
                if (fridgeState) {
                    fridgeState = false;
                    deviceName = "Fridge";
                }
                break;
            case 1:
                if (tvState) {
                    tvState = false;
                    deviceName = "TV";
                }
                break;
            case 2:
                if (ovenState) {
                    ovenState = false;
                    deviceName = "Oven";
                }
                break;
            default:
                deviceName = "Invalid device number";
        }

        String message = "Energy Management Agent: Turning off " + deviceName + ".";
        System.out.println(message);
        JavaFXApplication.appendMessage(message);
    }

    private class EnergyManagementBehaviour extends CyclicBehaviour {
        public EnergyManagementBehaviour(Agent agent) {
            super(agent);
        }

        public void action() {
            if (fridgeState && tvState && ovenState && (getTotalElectricity() > threshold)) {
                ACLMessage shutdownMsg = new ACLMessage(ACLMessage.REQUEST);
                shutdownMsg.addReceiver(getAID(mainControllerName));
                shutdownMsg.setContent("Energy Management: Powerhouse Status");
                send(shutdownMsg);

                ACLMessage reply = blockingReceive();
                if (reply != null && reply.getContent().matches("\\d")) {
                    int deviceToTurnOff = Integer.parseInt(reply.getContent());
                    turnOffDevice(deviceToTurnOff);
                }
            } else {
                this.block();
            }
        }
    }

    private class PeriodicStateChangeBehaviour extends OneShotBehaviour {
        public PeriodicStateChangeBehaviour(Agent agent) {
            super(agent);
        }

        public void action() {
            Random rand = new Random();
            if (rand.nextDouble() < CHANGE_STATE_PROBABILITY) {
                fridgeState = !fridgeState;
            }
            if (rand.nextDouble() < CHANGE_STATE_PROBABILITY) {
                tvState = !tvState;
            }
            if (rand.nextDouble() < CHANGE_STATE_PROBABILITY) {
                ovenState = !ovenState;
            }
        }
    }

    private class PeriodicThresholdUpdateBehaviour extends OneShotBehaviour {
        public PeriodicThresholdUpdateBehaviour(Agent agent) {
            super(agent);
        }

        public void action() {
            Random rand = new Random();
            threshold = rand.nextInt(501);
        }
    }

    private int getTotalElectricity() {
        int fridgeElectricity = fridgeState ? 100 : 0;
        int tvElectricity = tvState ? 150 : 0;
        int ovenElectricity = ovenState ? 200 : 0;
        return fridgeElectricity + tvElectricity + ovenElectricity;
    }
}

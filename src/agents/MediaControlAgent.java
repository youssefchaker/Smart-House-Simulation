package agents;

import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;
import ui.JavaFXApplication;

public class MediaControlAgent extends Agent {
    private static final int PAUSE_THRESHOLD = 20; 

    private String mainControllerName;
    private String currentState;
    private int pauseCounter;

    protected void setup() {
        mainControllerName = "MainController"; 
        currentState = "playing"; 
        pauseCounter = 0;

        String initialMessage = "Hello. My name is " + this.getLocalName() + " and I control the smart speakers.";
        System.out.println(initialMessage);
        JavaFXApplication.appendMessage(initialMessage);

        addBehaviour(new MediaControlBehaviour(this));
        addBehaviour(new PeriodicPauseBehaviour(this));
    }

    private class MediaControlBehaviour extends CyclicBehaviour {
        public MediaControlBehaviour(Agent agent) {
            super(agent);
        }

        public void action() {
            ACLMessage msg = myAgent.receive();
            if (msg != null) {
                String content = msg.getContent();
                if ("Media Pause".equals(content)) {
                    currentState = "paused";
                    String pauseMessage = myAgent.getLocalName() + ": Smart speakers paused.";
                    System.out.println(pauseMessage);
                    JavaFXApplication.appendMessage(pauseMessage);
                    pauseCounter = 0; 
                } else if ("Media Stop".equals(content)) {
                    currentState = "stopped";
                    String stopMessage = myAgent.getLocalName() + ": Smart speakers stopped.";
                    System.out.println(stopMessage);
                    JavaFXApplication.appendMessage(stopMessage);
                } else if ("Media Play".equals(content)) {
                    currentState = "playing";
                    String playMessage = myAgent.getLocalName() + ": Smart speakers playing.";
                    System.out.println(playMessage);
                    JavaFXApplication.appendMessage(playMessage);
                } else {
                    String unknownMessage = myAgent.getLocalName() + ": Unknown message received.";
                    System.out.println(unknownMessage);
                    JavaFXApplication.appendMessage(unknownMessage);
                }
            } else {
                this.block();
            }
        }
    }

    private class PeriodicPauseBehaviour extends OneShotBehaviour {
        public PeriodicPauseBehaviour(Agent agent) {
            super(agent);
        }

        public void action() {
            if ("paused".equals(currentState)) {
                pauseCounter += 5; 

                if (pauseCounter > PAUSE_THRESHOLD) {
                    String stopMessage = myAgent.getLocalName() + ": Smart speakers stopped due to prolonged pause.";
                    System.out.println(stopMessage);
                    JavaFXApplication.appendMessage(stopMessage);
                }
            }
        }
    }
}

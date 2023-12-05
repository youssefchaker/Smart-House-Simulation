package agents;

import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import ui.JavaFXApplication;

import java.util.Random;

public class MainControllerAgent extends Agent {

    protected void setup() {
        String initialMessage = "My name is " + this.getLocalName() + " and I am the main controller agent managing the other agents";
        System.out.println(initialMessage);
        JavaFXApplication.appendMessage(initialMessage);

        addBehaviour(new ControllerBehaviour(this));
        addBehaviour(new PeriodicMediaControlBehaviour(this));
    }

    private class ControllerBehaviour extends CyclicBehaviour {
        public ControllerBehaviour(Agent agent) {
            super(agent);
        }

        public void action() {
            ACLMessage msg = myAgent.receive();
            if (msg != null) {
                String content = msg.getContent();
                String message;

                switch (content) {
                    case "Thermal Alert: High temperature detected!":
                        message = "Main Controller Agent: Received Thermal Alert. Taking necessary action.";
                        System.out.println(message);
                        JavaFXApplication.appendMessage(message);

                        ACLMessage replyThermal = new ACLMessage(ACLMessage.REQUEST);
                        replyThermal.addReceiver(msg.getSender());
                        replyThermal.setContent("DecreaseTemperature");
                        send(replyThermal);
                        break;

                    case "Security Alert: Intruder detected!":
                        message = "Main Controller Agent: Received Security Alert. Sounding the alarm.";
                        System.out.println(message);
                        JavaFXApplication.appendMessage(message);

                        ACLMessage replySecurity = new ACLMessage(ACLMessage.REQUEST);
                        replySecurity.addReceiver(msg.getSender());
                        replySecurity.setContent("SoundAlarm");
                        send(replySecurity);
                        break;

                    case "Maintenance: Report Status":
                        message = "Main Controller Agent: Received Maintenance Report. Checking status.";
                        System.out.println(message);
                        JavaFXApplication.appendMessage(message);

                        ACLMessage replyMaintenance = new ACLMessage(ACLMessage.REQUEST);
                        replyMaintenance.addReceiver(msg.getSender());
                        replyMaintenance.setContent("Done");
                        send(replyMaintenance);
                        break;

                    case "Energy Management: Powerhouse Status":
                        message = "Main Controller Agent: Received Energy Management Update. Checking powerhouses.";
                        System.out.println(message);
                        JavaFXApplication.appendMessage(message);

                        ACLMessage replyEnergyManagement = new ACLMessage(ACLMessage.REQUEST);
                        replyEnergyManagement.addReceiver(msg.getSender());
                        int randomDeviceToTurnOff = new Random().nextInt(3);
                        replyEnergyManagement.setContent(String.valueOf(randomDeviceToTurnOff));
                        send(replyEnergyManagement);
                        break;

                    default:
                        message = "Main Controller Agent: Received unknown message. Ignoring.";
                        System.out.println(message);
                        JavaFXApplication.appendMessage(message);
                }
            } else {
                block();
            }
        }
    }

    private class PeriodicMediaControlBehaviour extends CyclicBehaviour {
        public PeriodicMediaControlBehaviour(Agent agent) {
            super(agent);
        }

        public void action() {
            Random rand = new Random();

            double playProbability = 0.5;
            double pauseProbability = 0.7;
            double stopProbability = 0.4;

            if (rand.nextDouble() < playProbability) {
                ACLMessage playMsg = new ACLMessage(ACLMessage.REQUEST);
                playMsg.addReceiver(getAID("MediaControlAgent"));
                playMsg.setContent("Media Play");
                send(playMsg);
            } else if (rand.nextDouble() < pauseProbability) {
                ACLMessage pauseToPlayMsg = new ACLMessage(ACLMessage.REQUEST);
                pauseToPlayMsg.addReceiver(getAID("MediaControlAgent"));
                pauseToPlayMsg.setContent("Media Pause");
                send(pauseToPlayMsg);
            } else if (rand.nextDouble() < stopProbability) {
                ACLMessage stopToPlayMsg = new ACLMessage(ACLMessage.REQUEST);
                stopToPlayMsg.addReceiver(getAID("MediaControlAgent"));
                stopToPlayMsg.setContent("Media Stop");
                send(stopToPlayMsg);
            }

            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

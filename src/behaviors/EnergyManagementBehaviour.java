package behaviors;

import agents.EnergyManagementAgent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import ui.JavaFXApplication;

public class EnergyManagementBehaviour extends CyclicBehaviour {
    private final EnergyManagementAgent energyManagementAgent;

    public EnergyManagementBehaviour(EnergyManagementAgent agent) {
        super(agent);
        this.energyManagementAgent = agent;
    }

    public void action() {
        if (energyManagementAgent.isFridgeState() && energyManagementAgent.isTvState() &&
            energyManagementAgent.isOvenState() && (energyManagementAgent.getTotalElectricity() > energyManagementAgent.getThreshold())) {
            
            ACLMessage shutdownMsg = new ACLMessage(ACLMessage.REQUEST);
            shutdownMsg.addReceiver(energyManagementAgent.getAID(energyManagementAgent.getMainControllerName()));
            shutdownMsg.setContent("Energy Management: Powerhouse Status");
            energyManagementAgent.send(shutdownMsg);

            ACLMessage reply = energyManagementAgent.blockingReceive();
            if (reply != null && reply.getContent().matches("\\d")) {
                int deviceToTurnOff = Integer.parseInt(reply.getContent());
                energyManagementAgent.turnOffDevice(deviceToTurnOff);
            }
        } else {
            this.block();
        }
    }
}

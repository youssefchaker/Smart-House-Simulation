// PeriodicEnergyManagementBehaviour.java
package behaviors;

import agents.EnergyManagementAgent;
import jade.core.behaviours.TickerBehaviour;
import jade.lang.acl.ACLMessage;
import ui.JavaFXApplication;

public class PeriodicEnergyManagementBehaviour extends TickerBehaviour {
    private final EnergyManagementAgent energyManagementAgent;

    public PeriodicEnergyManagementBehaviour(EnergyManagementAgent agent, long period) {
        super(agent, period);
        this.energyManagementAgent = agent;
    }

    protected void onTick() {
        if (energyManagementAgent.isFridgeState() && energyManagementAgent.isTvState() &&
            energyManagementAgent.isOvenState() && (energyManagementAgent.getTotalElectricity() > energyManagementAgent.getThreshold())) {
            
            ACLMessage shutdownMsg = new ACLMessage(ACLMessage.REQUEST);
            shutdownMsg.addReceiver(energyManagementAgent.getAID(energyManagementAgent.getMainControllerName()));
            shutdownMsg.setContent("Energy OverLoad: Powerhouse Status");
            energyManagementAgent.send(shutdownMsg);

            ACLMessage reply = energyManagementAgent.blockingReceive();
            if (reply != null && reply.getContent().matches("\\d")) {
                int deviceToTurnOff = Integer.parseInt(reply.getContent());
                energyManagementAgent.turnOffDevice(deviceToTurnOff);
            }
        }
    }
}

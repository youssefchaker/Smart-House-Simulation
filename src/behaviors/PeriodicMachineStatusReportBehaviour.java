package behaviors;

import agents.EnergyManagementAgent;
import jade.core.behaviours.TickerBehaviour;
import ui.JavaFXApplication;

public class PeriodicMachineStatusReportBehaviour extends TickerBehaviour {
    private final EnergyManagementAgent energyManagementAgent;

    public PeriodicMachineStatusReportBehaviour(EnergyManagementAgent agent, long period) {
        super(agent, period);
        this.energyManagementAgent = agent;
    }

    protected void onTick() {
        String machineStatusMessage = energyManagementAgent.getLocalName() +
                ": Fridge state - " + (energyManagementAgent.isFridgeState() ? "ON" : "OFF") +
                ", TV state - " + (energyManagementAgent.isTvState() ? "ON" : "OFF") +
                ", Oven state - " + (energyManagementAgent.isOvenState() ? "ON" : "OFF");
        System.out.println(machineStatusMessage);
        JavaFXApplication.appendMessage(machineStatusMessage);
    }
}
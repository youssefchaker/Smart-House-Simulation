package behaviors;

import agents.EnergyManagementAgent;
import jade.core.behaviours.OneShotBehaviour;

import java.util.Random;

public class PeriodicStateChangeBehaviour extends OneShotBehaviour {
    private final EnergyManagementAgent energyManagementAgent;

    public PeriodicStateChangeBehaviour(EnergyManagementAgent agent) {
        super(agent);
        this.energyManagementAgent = agent;
    }

    public void action() {
        Random rand = new Random();
        if (rand.nextDouble() < EnergyManagementAgent.CHANGE_STATE_PROBABILITY) {
            energyManagementAgent.setFridgeState(!energyManagementAgent.isFridgeState());
        }
        if (rand.nextDouble() < EnergyManagementAgent.CHANGE_STATE_PROBABILITY) {
            energyManagementAgent.setTvState(!energyManagementAgent.isTvState());
        }
        if (rand.nextDouble() < EnergyManagementAgent.CHANGE_STATE_PROBABILITY) {
            energyManagementAgent.setOvenState(!energyManagementAgent.isOvenState());
        }
    }
}

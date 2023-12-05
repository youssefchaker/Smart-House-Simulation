package behaviors;

import agents.EnergyManagementAgent;
import jade.core.behaviours.TickerBehaviour;

import java.util.Random;

public class PeriodicStateChangeBehaviour extends TickerBehaviour {
    private final EnergyManagementAgent energyManagementAgent;

    public PeriodicStateChangeBehaviour(EnergyManagementAgent agent, long period) {
        super(agent, period);
        this.energyManagementAgent = agent;
    }

    protected void onTick() {
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

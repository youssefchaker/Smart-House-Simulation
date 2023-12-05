package behaviors;

import agents.EnergyManagementAgent;
import jade.core.behaviours.TickerBehaviour;

import java.util.Random;

public class PeriodicThresholdUpdateBehaviour extends TickerBehaviour {
    private final EnergyManagementAgent energyManagementAgent;

    public PeriodicThresholdUpdateBehaviour(EnergyManagementAgent agent, long period) {
        super(agent, period);
        this.energyManagementAgent = agent;
    }

    protected void onTick() {
        Random rand = new Random();
        energyManagementAgent.setThreshold(rand.nextInt(1));
    }
}

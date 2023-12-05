package behaviors;

import agents.EnergyManagementAgent;
import jade.core.behaviours.OneShotBehaviour;

import java.util.Random;

public class PeriodicThresholdUpdateBehaviour extends OneShotBehaviour {
    private final EnergyManagementAgent energyManagementAgent;

    public PeriodicThresholdUpdateBehaviour(EnergyManagementAgent agent) {
        super(agent);
        this.energyManagementAgent = agent;
    }

    public void action() {
        Random rand = new Random();
        energyManagementAgent.setThreshold(rand.nextInt(501));
    }
}

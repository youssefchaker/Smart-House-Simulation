package behaviors;

import agents.SecurityAgent;
import jade.core.behaviours.TickerBehaviour;

import java.util.Calendar;
import java.util.Random;

public class SecurityBehaviour extends TickerBehaviour {
    public SecurityBehaviour(SecurityAgent agent, long period) {
        super(agent, period);
    }

    protected void onTick() {
        if (isNightTime()) {
            Random rand = new Random();
            if (rand.nextInt(100) < SecurityAgent.ALERT_PROBABILITY && isNightTime()) {
                ((SecurityAgent) myAgent).handleIntruderDetected();
            }
        }
    }

    private boolean isNightTime() {
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        return (hour >= 23 || hour < 6);
    }
}

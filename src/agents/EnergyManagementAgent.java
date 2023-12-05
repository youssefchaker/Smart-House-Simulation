package agents;

import behaviors.EnergyManagementBehaviour;
import behaviors.PeriodicStateChangeBehaviour;
import behaviors.PeriodicThresholdUpdateBehaviour;
import jade.core.Agent;
import ui.JavaFXApplication;

public class EnergyManagementAgent extends Agent {
    public static final double CHANGE_STATE_PROBABILITY = 0.6;

    private String mainControllerName;
    private boolean fridgeState;
    private boolean tvState;
    private boolean ovenState;
    private int threshold;

    protected void setup() {
        mainControllerName = "MainController";
        fridgeState = true;
        tvState = true;
        ovenState = true;

        String initialMessage = "My name is " + this.getLocalName() + " and I manage electric energy in the smart house.";
        System.out.println(initialMessage);
        JavaFXApplication.appendMessage(initialMessage);

        addBehaviour(new EnergyManagementBehaviour(this));
        addBehaviour(new PeriodicStateChangeBehaviour(this));
        addBehaviour(new PeriodicThresholdUpdateBehaviour(this));
    }

    public String getMainControllerName() {
        return mainControllerName;
    }

    public boolean isFridgeState() {
        return fridgeState;
    }

    public boolean isTvState() {
        return tvState;
    }

    public boolean isOvenState() {
        return ovenState;
    }

    public int getThreshold() {
        return threshold;
    }

    public void turnOffDevice(int deviceToTurnOff) {
        String deviceName = "";
        switch (deviceToTurnOff) {
            case 0:
                if (fridgeState) {
                    fridgeState = false;
                    deviceName = "Fridge";
                }
                break;
            case 1:
                if (tvState) {
                    tvState = false;
                    deviceName = "TV";
                }
                break;
            case 2:
                if (ovenState) {
                    ovenState = false;
                    deviceName = "Oven";
                }
                break;
            default:
                deviceName = "Invalid device number";
        }

        String message = "Energy Management Agent: Turning off " + deviceName + ".";
        System.out.println(message);
        JavaFXApplication.appendMessage(message);
    }

    public int getTotalElectricity() {
        int fridgeElectricity = fridgeState ? 100 : 0;
        int tvElectricity = tvState ? 150 : 0;
        int ovenElectricity = ovenState ? 200 : 0;
        return fridgeElectricity + tvElectricity + ovenElectricity;
    }

	public void setFridgeState(boolean b) {
		this.fridgeState=b;
		
	}
	
	public void setTvState(boolean b) {
		this.tvState=b;
		
	}
	
	public void setOvenState(boolean b) {
		this.ovenState=b;
		
	}

	public void setThreshold(int nextInt) {
		threshold=nextInt;
		
	}
}

package main;

import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.wrapper.AgentController;
import jade.wrapper.ContainerController;
import javafx.application.Application;
import ui.JavaFXApplication;

public class Main {

    public static void main(String[] args) {
        Thread javafxThread = new Thread(() -> Application.launch(JavaFXApplication.class));
        javafxThread.start();

        Runtime rt = Runtime.instance();

        Profile profile = new ProfileImpl();

        ContainerController mainContainer = rt.createMainContainer(profile);

        try {
            AgentController mainControllerAgent = mainContainer.createNewAgent("MainController", agents.MainControllerAgent.class.getName(), null);
            AgentController securityAgent = mainContainer.createNewAgent("SecurityAgent", agents.SecurityAgent.class.getName(), null);
            AgentController thermalAgent = mainContainer.createNewAgent("ThermalAgent", agents.ThermalAgent.class.getName(), null);
            AgentController mediaControlAgent = mainContainer.createNewAgent("MediaControlAgent", agents.MediaControlAgent.class.getName(), null);
            AgentController maintenanceAgent = mainContainer.createNewAgent("MaintenanceAgent", agents.MaintenanceAgent.class.getName(), null);
            AgentController energyManagementAgent = mainContainer.createNewAgent("EnergyManagementAgent", agents.EnergyManagementAgent.class.getName(), null);

            mainControllerAgent.start();
            securityAgent.start();
            thermalAgent.start();
            mediaControlAgent.start();
            maintenanceAgent.start();
            energyManagementAgent.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

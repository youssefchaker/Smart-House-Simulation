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
        // Launch JavaFX application in a separate thread
        Thread javafxThread = new Thread(() -> Application.launch(JavaFXApplication.class));
        javafxThread.start();

        // Get a hold on JADE runtime
        Runtime rt = Runtime.instance();

        // Create a default profile
        Profile profile = new ProfileImpl();

        // Create the main container
        ContainerController mainContainer = rt.createMainContainer(profile);

        try {
            // Create agents
            AgentController mainControllerAgent = mainContainer.createNewAgent("MainController", agents.MainControllerAgent.class.getName(), null);
            AgentController securityAgent = mainContainer.createNewAgent("SecurityAgent", agents.SecurityAgent.class.getName(), null);
            AgentController thermalAgent = mainContainer.createNewAgent("ThermalAgent", agents.ThermalAgent.class.getName(), null);
            AgentController mediaControlAgent = mainContainer.createNewAgent("MediaControlAgent", agents.MediaControlAgent.class.getName(), null);
            AgentController maintenanceAgent = mainContainer.createNewAgent("MaintenanceAgent", agents.MaintenanceAgent.class.getName(), null);
            AgentController energyManagementAgent = mainContainer.createNewAgent("EnergyManagementAgent", agents.EnergyManagementAgent.class.getName(), null);

            // Start the agents
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

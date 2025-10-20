# Smart House Simulation

## Description

This project is a simulation of a smart house environment using a multi-agent system. The simulation is built in Java and utilizes the JADE (Java Agent DEvelopment Framework) for creating and managing agents. The user interface is a simple JavaFX application that displays logs from the different agents.

## Features

The smart house simulation includes the following features:

- **Thermal Control:** A `ThermalAgent` monitors the temperature of the house and sends alerts when it gets too high.
- **Security System:** A `SecurityAgent` periodically checks for intruders and sends alerts if an intruder is detected.
- **Energy Management:** An `EnergyManagementAgent` monitors the power consumption of different devices (fridge, TV, oven) and can turn them off to prevent overload.
- **Media Control:** A `MediaControlAgent` simulates smart speakers that can play, pause, or stop media.
- **Maintenance:** A `MaintenanceAgent` is responsible for maintenance tasks and reporting status.
- **Central Control:** A `MainControllerAgent` acts as the central brain of the house, receiving alerts from other agents and sending them commands.

## Architecture

The application is based on a multi-agent system architecture using the JADE framework. Each component of the smart house is represented by an agent. The agents communicate with each other using ACL messages.

The main components of the architecture are:

- **Agents:** The core components of the system, each responsible for a specific task.
- **Behaviors:** The actions and logic of the agents are defined in behaviors.
- **Main Container:** The JADE container that hosts all the agents.
- **User Interface:** A JavaFX application that displays the logs from the agents.

## Agents

The simulation consists of the following agents:

- **`MainControllerAgent`:** The central controller that manages the other agents. It receives alerts and sends commands to the appropriate agents.
- **`ThermalAgent`:** Manages the thermal conditions of the house. It periodically increases the temperature and sends an alert to the `MainControllerAgent` if the temperature exceeds a certain threshold.
- **`SecurityAgent`:** Manages the security of the house. It periodically checks for intruders and sends an alert to the `MainControllerAgent` if an intruder is detected.
- **`EnergyManagementAgent`:** Manages the energy consumption of the house. It monitors the power usage of different devices and can turn them off if the total consumption exceeds a threshold.
- **`MediaControlAgent`:** Controls the smart speakers. It can be in one of three states: `playing`, `paused`, or `stopped`.
- **`MaintenanceAgent`:** Responsible for maintenance tasks. It sends status reports to the `MainControllerAgent`.

## User Interface

The user interface is a simple JavaFX window with a text area. The text area displays the logs and messages from all the agents, providing a real-time view of the simulation.

## How to Run

To run the application, you need to have a Java IDE (like Eclipse or IntelliJ IDEA) with JavaFX and JADE libraries configured.

1.  Clone the repository to your local machine.
2.  Open the project in your IDE.
3.  Make sure the JADE and JavaFX libraries are included in the project's build path.
4.  Run the `main.Main` class to start the simulation.

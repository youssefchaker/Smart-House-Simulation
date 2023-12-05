/**
 * 
 */
/**
 * 
 */
module prj {
	requires javafx.controls;
    requires javafx.fxml;
	exports main;
	exports agents;
	exports ui;
	requires jade;
	
    opens main to javafx.fxml;
}
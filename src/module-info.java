/**
 * 
 */
/**
 * 
 */
module prj {
	requires jade;
	requires javafx.controls;
    requires javafx.fxml;
	exports main;
	exports agents;
	exports ui;
	
	
    opens main to javafx.fxml;
}
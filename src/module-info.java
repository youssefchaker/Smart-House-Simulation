/**
 * 
 */
/**
 * 
 */
module prj {
	exports main;
	exports agents;
	exports ui;
	requires jade;
	requires javafx.controls;
    requires javafx.fxml;
    opens main to javafx.fxml;
}
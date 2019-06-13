module adoPet {
	requires transitive java.sql;
	requires transitive javafx.graphics;
	requires javafx.controls;
	requires javafx.fxml;
	requires transitive java.desktop;
	requires javafx.swing;
	opens frontend;
	exports frontend;
	exports backend;
	exports bd;
}

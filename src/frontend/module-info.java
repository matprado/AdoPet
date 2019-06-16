module adoPet {
	requires transitive java.sql;
	requires transitive javafx.graphics;
	requires transitive javafx.controls;
	requires transitive javafx.fxml;
	requires transitive java.desktop;
	requires transitive javafx.swing;
	opens frontend;
	exports frontend;
	exports backend;
	exports bd;
}

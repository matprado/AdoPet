module adoPet {
	requires transitive java.sql;
	requires transitive javafx.graphics;
	requires javafx.controls;
	requires javafx.fxml;
	opens frontend;
	exports frontend;
	exports backend;
	exports bd;
}

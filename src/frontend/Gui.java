package frontend;
import java.io.File;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
 
public class Gui extends Application {
	
    @Override
    public void start(@SuppressWarnings("exports") Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(new File("fxml/inicial.fxml").toURI().toURL());
        Parent root = loader.load();
        stage.setScene(new Scene(root));
        stage.show();
    }
    
    public static void main(String[] args) {
    	Gui.launch(args); //Requisitando inicializacao da Gui
    }
}
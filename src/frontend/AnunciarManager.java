package frontend;

import backend.Pet;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class AnunciarManager {

	@FXML protected void voltarDisponiveis(ActionEvent event) {
		Gui.telaDisponiveis();
	}
	
	@FXML protected void selecionarImagem(ActionEvent event) {
		Gui.escolheFoto();
	}
	
	@FXML protected void confirmarAnuncio(ActionEvent event) {
		Pet pet = new Pet();
		pet.setNome(((TextField)Gui.getComp("nome")).getText());
		pet.setEspecie(((TextField)Gui.getComp("especie")).getText());
		pet.setSexo(((TextField)Gui.getComp("sexo")).getText());
		pet.setDetalhes(((TextArea)Gui.getComp("descricao")).getText());
		pet.setAnunciante(Gui.User);
		pet.setAnuncianteID(Gui.User.getId());
		pet.setIcone(Gui.fotopet);
		Gui.confirmarAnuncio(pet);
	}
	
}

package frontend;

import java.sql.SQLException;

import bd.BDConexaoClass;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.control.TextArea;

public class ChatManager {
	
	@FXML protected void abaDisponiveis(ActionEvent event) {
		Gui.telaDisponiveis();
	}
	
	@FXML protected void abaPorqueAdotar(ActionEvent event) {
		Gui.telaPorqueAdotar();
	}
	
	@FXML public void apertou(ActionEvent event) {
        int id = Integer.parseInt(((Control)event.getSource()).getId());
		Gui.contato = Gui.contatos[id];
        try {
			Gui.iniciarChat(Gui.contato);
		} catch (SQLException e) {
			System.out.println("Erro ao iniciar chat");
		}
    }
	
	@FXML public void enviouMensagem(ActionEvent event) {
		if(((TextArea)Gui.getComp("texto")).toString().length() != 0) {
			//criarMensagem(Gui.User, Gui.contato, ((TextArea)Gui.getComp("texto")).toString());
			Gui.mostrarNovaMensagem(((TextArea)Gui.getComp("texto")).toString());
			try {
				BDConexaoClass.criarMensagem(Gui.User, Gui.contato, ((TextArea)Gui.getComp("texto")).toString());
			} catch (SQLException e) {
				System.out.println("Erro ao criar nova mensagem!");
			}
		}
	}
	
	@FXML public void usuarioAceitou(ActionEvent event) {
		if(((Button)Gui.getComp(((Control)event.getSource()).getId())).getText() == "Finalizar") {
			((Button)Gui.getComp(((Control)event.getSource()).getId())).setText("Esperando");
			definir_no_bd_usuario_aceitou...
		}
		if(contato_tbm_aceitou) {
			Gui.finalizaAdocao();
		}
		
	}
	
	@FXML public void voltar(ActionEvent event) {
		Gui.telaChat();
	}
	
}
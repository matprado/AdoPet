package frontend;

import bd.BDConexaoClass;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.control.TextArea;

/**
 * Classe de manager para as telas envolvendo chat;
 * @authors Mateus Prado, Mateus Tomieiro, Victor Reis, Matheus Rigato;
 *
 */
public class ChatManager {
	
	/**
	 * Metodo para ir ate a tela de disponiveis;
	 * @param event - evento realizado;
	 */
	@FXML protected void abaDisponiveis(ActionEvent event) {
		Gui.telaDisponiveis();
	}
	
	/**
	 * Metodo para ir ate a tela de porque adotar;
	 * @param event - evento realizado;
	 */
	@FXML protected void abaPorqueAdotar(ActionEvent event) {
		Gui.telaPorqueAdotar();
	}
	
	/**
	 * Metodo para quando se escolhe um contato;
	 * @param event - evento realizado;
	 */
	public static void apertou(ActionEvent event) {
        String aux = ((Control)event.getSource()).getId();
		aux = aux.replaceFirst("b", "");
		int id = Integer.parseInt(aux);		
		Gui.contato = Gui.contatos[id];
		//EXIBE TELA COM PETS CONVERSADOS//
		Gui.mostraPetsChat();
    }
	
	public static void apertouPet(ActionEvent event) {
		String aux = ((Control)event.getSource()).getId();
		aux = aux.replaceFirst("bp", "");
		int id = Integer.parseInt(aux);		
		Gui.selecionadoChat = Gui.vpets.elementAt(id);
		Gui.petCorrente = Gui.selecionadoChat;
		Gui.iniciarChat();
	}
	
	@FXML public void enviouMensagem(ActionEvent event) {
		if(((TextArea)Gui.getComp("texto")).getText().length() != 0) {
			String mensagem =((TextArea)Gui.getComp("texto")).getText();
			Gui.mostrarNovaMensagem(mensagem);
			((TextArea)Gui.getComp("texto")).setText("");
			BDConexaoClass.criarMensagem(Gui.User, Gui.contato, mensagem);
		}
	}
	
	/**
	 * Metodo para quando o usuario aceita prosseguir com a adocao;
	 * @param event - evento realizado;
	 */
	@FXML public void usuarioAceitou(ActionEvent event) {
		((Button)Gui.getComp(((Control)event.getSource()).getId())).setText("Esperando");
		((Button)Gui.getComp(((Control)event.getSource()).getId())).setDisable(true);
		BDConexaoClass.finaliza(Gui.User, Gui.contato, Gui.petCorrente);
	}
	
	/**
	 * Metodo para quando o usuario volta numa tela anterior;
	 * @param event - evento realizado;
	 */
	@FXML public void voltar(ActionEvent event) {
		Gui.telaChat();
	}
	
	
	/**
	 * Metodo para quando o usuario cancela um chat;
	 * @param event - evento realizado;
	 */
	@FXML public void cancelar(ActionEvent event) {
		//BDConexaoClass.excluirChat(Gui.User, Gui.contato);
		Gui.telaDisponiveis();
	}
}
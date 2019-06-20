package frontend;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Control;

/**
 * Classe manager da tela de disponiveis;
 * @authors Mateus Prado, Mateus Tomieiro, Victor Reis, Matheus Rigato;
 *
 */
public class DisponiveisManager {
	
	/**
	 * Metodo para quando se quer ir para a tela de porque adotar;
	 * @param event - evento realizado;
	 */
	@FXML protected void abaPorqueAdotar(ActionEvent event) {
		Gui.telaPorqueAdotar();
	}
	
	/**
	 * Metodo para quando se quer ir para a tela de chat;
	 * @param event - evento realizado;
	 */
	@FXML protected void abaChat(ActionEvent event) {
		Gui.telaChat();
	}
	
	/**
	 * Metodo para quando se quer avancar uma pagina nos anuncios de pets;
	 * @param event - evento realizado;
	 */
	@FXML protected void avancaPag(ActionEvent event) {
		Gui.avancaPag();
	}
	
	/**
	 * Metodo para quando se quer voltar uma pagina nos anuncios de pets;
	 * @param event - evento realizado;
	 */
	@FXML protected void voltaPag(ActionEvent event) {
		Gui.voltaPag();
	}
	
	/**
	 * Metodo para quando se quer escolher um pet nos anuncios para visualizar;
	 * @param event - evento realizado;
	 */
	@FXML protected void clicaPet(ActionEvent event) {
		Gui.index = ((int)(((Control)event.getSource()).getId().charAt(8))-48) - 1;
		Gui.petCorrente = Gui.pet[Gui.index];
		Gui.telaInfoPet();
	}
	
	/**
	 * Metodo para quando se quer anunciar um pet;
	 * @param event - evento realizado;
	 */
	@FXML protected void clicaAnunciar(ActionEvent event) {
		Gui.fotopet = null;
		Gui.telaAnunciar();
	}
	
	//Dentro da tela de informacoes do animal
	
	/**
	 * Metodo para quando se quer voltar para a tela de disponiveis;
	 * @param event - evento realizado;
	 */
	@FXML protected void voltarDisponiveis(ActionEvent event) {
		Gui.telaDisponiveis();
	}
	
	/**
	 * Metodo para quando se quer iniciar um chat com quem anunciou o pet;
	 * @param event - evento realizado;
	 */
	@FXML protected void mensagemDono(ActionEvent event) {
		Gui.contato = Gui.pet[Gui.index].getAnunciante();
		Gui.iniciarChat();
	}
	
	/**
	 * Metodo para quando se sair do programa e voltar para a tela de login/cadastro;
	 * @param event - evento realizado;
	 */
	@FXML protected void sair(ActionEvent event) {
		try {
			Gui.telaInicial();
		} catch (IOException e) {
			return;
		}
	}
	
}

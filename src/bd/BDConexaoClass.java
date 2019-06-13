package bd;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import backend.Usuario;
import backend.Pet;

/*TODO ANGRA, ao inves de salvar a imagem do pet lá... Pega na funcao que retorna
 *		o pet, e instacia uma Image retornando ela la...
 */

public class BDConexaoClass{
    
    
    DriverManager driver;
    
    public static Connection BDConexao() throws SQLException{
        
       String con="jdbc:mysql://localhost:3306/poo_db";
       String server_user = "root";
       String server_pass = "rootpass";
       
       Connection connect = null;
       connect = DriverManager.getConnection(con,server_user,server_pass);
       
       return connect;
        
    }
    
    public void cadastroPet(Pet p) throws SQLException{
        Connection con = BDConexao();
        String insert = "INSERT INTO pets values(?,?,?,?,?,?)";
        PreparedStatement ps = con.prepareStatement(insert);
        ps.setInt(1, p.getPetID());
        ps.setString(2, p.getEspecie());
        ps.setString(3, p.getNome());
        ps.setString(4, p.getSexo());
        ps.setString(5, p.getDetalhes());
        ps.setInt(6,p.getAnuncianteID());
        ResultSet rs = ps.executeQuery();
        
    }
    
    private static byte[] readFile(String file) throws IOException{
    	
    	ByteArrayOutputStream bos = null;
    	try{
    		File f = new File(file);
    		FileInputStream fis = new FileInputStream(f);
    		byte[] buffer = new byte[1024];
    		bos = new ByteArrayOutputStream();
    		for(int len; (len = fis.read(buffer)) != -1; ){
    			bos.write(buffer,0,len);
    			fis.close();
    		}
    		
    	}
    	catch(FileNotFoundException e){
    		System.err.println(e.getMessage());
    	}catch(IOException e2){
    		System.err.println(e2.getMessage());
    	}
    	return bos != null  ? bos.toByteArray() : null;
    	
    }
    
    
    // Fun��o que cadastra o usu�rio no Banco de Dados
    public static void cadastroUser(Usuario user) throws SQLException{
        Connection con = BDConexao();
        String insert = "INSERT INTO clientes values(?,?,?,?,?,?,?,?,?)";
        PreparedStatement ps = con.prepareStatement(insert);
        ps.setInt(1, user.getId());
        ps.setString(2, user.getUserName());
        ps.setString(3, user.getSenha());
        ps.setString(4, user.getNome());
        ps.setString(6, user.getCpf());
        ps.setString(7, user.getCidade());
        ps.setString(8, user.getEndereco());
        ps.setString(9, user.getCep());
        
        ResultSet rs = ps.executeQuery();
    }
    
    public static boolean loginUser(Usuario user) throws SQLException{
        
        Connection con = BDConexao();
        String select = "SELECT * FROM clientes where nomelogin=?";
        PreparedStatement ps = con.prepareStatement(select);
        ps.setString(1, user.getUserName());
        
        ResultSet rs = ps.executeQuery();
        
        if(rs.wasNull()){
            //Nega login, pois n�o existe ningu�m com o nome colocado no campo
            return false;
        }
        
        while(rs.next()){
            String  getpass = rs.getString(3);
            if(user.getSenha().equals(getpass)){
            	//Autoriza login, pois o usuario esta no BD;
                return true;
            }
        }
        //Se nao encontrar nenhuma senha igual, nega login!
        return false;
    }


    public static void adotarPet(Pet p) throws SQLException{

        Connection con = BDConexao();
        String del = "Delete FROM pets WHERE nome = ?";
        PreparedStatement ps = con.prepareStatement(del);
        ps.setString(1,p.getNome());
        ResultSet rs = ps.executeQuery();

    }

    public static boolean existeChat(Usuario user1,Usuario user2){

        Connection con = BDConexao();
        String select = "SELECT * FROM chat where user1_id=? AND user2_id=?";
        PreparedStatement ps = con.prepareStatement(select);
        ps.setInt(1, user1.getId());
        ps.setInt(2, user2.getId());

        ResultSet rs = ps.executeQuery();

        if(rs.wasNull()){
            return false;
        }

        return true;
    }

    public static void comecarChat(Usuario user1,Usuario User2){
        
        Connection con = BDConexao();
        String select = "SELECT * FROM chat where user1_id=? AND user2_id=?";
        PreparedStatement ps = con.prepareStatement(select);
        ps.setInt(1, user1.getId());
        ps.setInt(2, user2.getId());

        ResultSet rs = ps.executeQuery();

        
        String insert = "INSERT INTO chat Values(?,?)";
        PreparedStatement psi =  con.prepareStatement(insert);
        psi.setInt(2, user1.getId());
        psi.setInt(3, user2.getId());

        ResultSet rsi = psi.executeQuery();
        
    }

    public static HashMap<int,String> getMensagensAntigas(Usuario user1, Usuario user2) throws SQLException{

        Connection con = BDConexao();
        String select = "SELECT * FROM chat where user1_id=? AND user2_id=?";
        PreparedStatement ps = con.prepareStatement(select);
        ps.setInt(1, user1.getId());
        ps.setInt(2, user2.getId());

        ResultSet rs = ps.executeQuery();

        int id = rs.getInt(1);
        
        String puxarMensagens = "Select mensagem,id_remetente FROM mensagens INNER JOIN chat ON chat.chat_id = mensagens.id_chat WHERE chat.chat_id = ?";
        PreparedStatement psp = con.prepareStatement(puxarMensagens);
        psp.setInt(1,id);

        ResultSet rsp = psp.executeQuery();

        HashMap<int,String> messages = new HashMap<int,String>();
        while(rsp.next()){
            String msg = rsp.getString(4);
            int sent = rsp.getInt(3);

            messages.put(sent,msg);

        }

        return messages;
    }

    public static void criarMensagem(Usuario user1, Usuario user2,String mensagem) throws SQLException{
    	Connection con = BDConexao();
        String pegarID = "Select chat_id FROM chat WHERE user1_id = ? AND user2_id = ?";
        PreparedStatement psc = con.prepareStatement(pegarID);
        psc.setInt(2,user1.getId());
        psc.setInt(3,user2.getId());

        ResultSet rsc = psc.executeQuery();


        int chat = rsc.getInt(1);


        String inserirMensagem = "Insert INTO mensagens(id_chat,id_remetente,mensagem) VALUES(?,?,?)";
        PreparedStatement psi = con.prepareStatement(inserirMensagem);
        psi.setInt(1,chat);
        psi.setInt(2,user2.getId());
        psi.setString(3,mensagem);

        ResultSet rsi = psi.executeQuery();
      
    }
    
    public static int getSizeUser(Usuario user1) throws SQLException{
    	
    	Connection con = BDConexao();
    	String cont = "SELECT COUNT(chat_id) FROM chat WHERE user1_id = ?";
    	PreparedStatement ps = con.prepareStatement(cont);
    	ps.setInt(1,user1.getId());
    	
    	ResultSet rs = ps.executeQuery();
    	
    	return rs.getInt(1);
    	
    }


       
    public static int getSizePets() throws SQLException{
    	
    	Connection con = BDConexao();
    	String cont = "SELECT COUNT(pet_id) FROM pets";
    	PreparedStatement ps = con.prepareStatement(cont);
    	
    	ResultSet rs = ps.executeQuery();
    	
    	return rs.getInt(1);
    	
    }



    public static Usuario[] listaContatos(Usuario user1) throws SQLException{
    	
    	Connection con = BDConexao();
    	String select = "SELECT * FROM chat WHERE user1_id = ?";
    	PreparedStatement ps = con.prepareStatement(select);
    	ps.setInt(1,user1.getId());

    	ResultSet rs = ps.executeQuery();
    	
    	int len = getSizeUser(user1);
    	
    	Usuario[] pessoa = new Usuario[len];
    	int i = 0;
    	
    	while(rs.next()){
    		pessoa[i].setId(rs.getInt(1));
    		pessoa[i].setUserName(rs.getString(2));
    		pessoa[i].setSenha(rs.getString(3));
    		pessoa[i].setNome(rs.getString(4));
    		pessoa[i].setCpf(rs.getString(5));
    		pessoa[i].setCidade(rs.getString(6));
    		pessoa[i].setEndereco(rs.getString(7));
    		pessoa[i].setCep(rs.getString(8));
    		
    		i++;
    	}
    	
    	return pessoa;
    }

    
    
    public static void inserirImagem(String filename) throws SQLException, IOException{
    	
    	String update= "UPDATE pets SET caminho_imagem_pet= ?";
    	
    	Connection con = BDConexao();
    	PreparedStatement ps = con.prepareStatement(update);
    	ps.setBytes(1,readFile(filename));
    	ps.executeUpdate();
    	
    }
    
    public static void selecionarImagem(Pet p) throws SQLException, IOException{
    	
    	Connection con = BDConexao();
    	String select = "SELECT caminho_imagem_pet FROM pets WHERE pet_id = ?";
    	PreparedStatement ps = con.prepareStatement(select);
    	ps.setInt(1,p.getPetID());
    	
    	ResultSet rs = ps.executeQuery();
    	
    	File arq = new File(BDConexaoClass.class.getResource("../../resources/iconepet.jpg").getPath());
    	FileOutputStream fos = new FileOutputStream(arq);
    	fos.close();
    	while(rs.next()){
    		InputStream input = rs.getBinaryStream("caminho_imagem_pet");
    		byte[] buffer = new byte[1024];
    		while(input.read(buffer) > 0){
    			fos.write(buffer);
    		}
    	}
    			
    	
    }
    
    public static Usuario retornaUsuario(int id){

        Usuario user = new Usuario();
        Connection con = BDConexao();
        String select = "SELECT * FROM clientes WHERE cliente_id = ?";
        PreparedStatement ps = con.prepareStatement(select);
        ps.setInt(1,id);
        ResultSet rs = ps.executeQuery();

        while(rs.next()){
            user.setId(rs.getInt(1));
            user.setUserName(rs.getString(2));
            user.setSenha(rs.getString(3));
            user.setNome(rs.getString(4));
            user.setCpf(rs.getString(5));
            user.setCidade(rs.getString(6));
            user.setEndereco(rs.getString(7));
            user.setCep(rs.getString(8));
        }

        return user;

    }

    public static Pet retornaPet(int index) throws NumberFormatException, SQLException{

        int indextotal = getSizePets();
        int aux = indextotal - index;

        Connection con = BDConexao();
        String select = "SELECT * FROM pets WHERE pet_id= ?";
        PreparedStatement ps = con.prepareStatement(select);
        ps.setInt(1,aux);

        ResultSet rs = ps.executeQuery();

        Pet p = new Pet();

        while(rs.next()){
            p.setPetID(rs.getInt(1));
            p.setEspecie(rs.getString(2));
            p.setNome(rs.getString(3));
            p.setSexo(rs.getString(4));
            p.setDetalhes(rs.getString(5));
            p.setAnuncianteID(rs.getInt(6));
            Usuario anunciante = new Usuario();
            anunciante = retornaUsuario(p.getAnuncianteID());
            p.setAnunciante(anunciante);
        }

        return p;

    }

}
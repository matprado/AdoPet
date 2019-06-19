package bd;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import javax.imageio.ImageIO;
import backend.Usuario;
import javafx.embed.swing.SwingFXUtils;
import backend.Pet;


public class BDConexaoClass{
    
    DriverManager driver;
    
    
    public static Connection BDConexao(){
        
       String con="jdbc:mysql://127.0.0.1:3306/adopet";
       String server_user = "adopet";
       String server_pass = "@adopet33";
       
       Connection connect = null;
       try {
		connect = DriverManager.getConnection(con,server_user,server_pass);
	} catch (SQLException e) {
		System.out.println("Erro ao conectar com o BD");
	}
       return connect;
        
    }
    
    public static void cadastroPet(Pet p){
        Connection con = BDConexao();
        String insert = "INSERT INTO pets(species,nome,sexo,detalhes,id_doador,imagem) values(?,?,?,?,?,?)";
        PreparedStatement ps = null;
		try {
			ps = con.prepareStatement(insert);
		} catch (SQLException e) {
			System.out.println("Erro na preparacao da Query");
		}
        try {
			ps.setString(1, p.getEspecie());
			ps.setString(2, p.getNome());
	        ps.setString(3, p.getSexo());
	        ps.setString(4, p.getDetalhes());
	        ps.setInt(5,(int)p.getAnuncianteID());
	        BufferedImage image = SwingFXUtils.fromFXImage(p.getIcone(), null);
	        ByteArrayOutputStream A = new ByteArrayOutputStream();
	        try {
				ImageIO.write(image, "jpg", A);
			} catch (IOException e) {
				System.out.println("Erro ao escrever a imagem!");
			}
	        InputStream is = new ByteArrayInputStream(A.toByteArray());
	        ps.setBlob(6, is);
		} catch (SQLException e) {
			System.out.println("Erro ao setar o statament");
		}
        try {
			ps.execute();
		} catch (SQLException e) {
			System.out.println("Erro ao executar a Query Cadastro");
		}
    }
    
    
    // Fun��o que cadastra o usu�rio no Banco de Dados
    public static void cadastroUser(Usuario user){
        Connection con = BDConexao();
        String insert = "INSERT INTO clientes(username,senha,nome,cpf,cidade,endereco,cep) values(?,?,?,?,?,?,?)";
        System.out.println(insert);
        PreparedStatement ps = null;
		try {
			ps = con.prepareStatement(insert);
		} catch (SQLException e) {
			System.out.println("Erro na preparacao da Query");
		}
        try {
			ps.setString(1, user.getUserName());
	        ps.setString(2, user.getSenha());
	        ps.setString(3, user.getNome());
	        ps.setString(4, user.getCpf());
	        ps.setString(5, user.getCidade());
	        ps.setString(6, user.getEndereco());
	        ps.setString(7, user.getCep());
		} catch (SQLException e) {
			System.out.println("Erro ao setar valores");
		}
        
        try {
			ps.execute();
		} catch (SQLException e) {
			System.out.println("Erro na execucao da Query");
		}
    }
    
    public static boolean loginUser(Usuario user){
        Connection con = BDConexao();
        String select = "SELECT * FROM clientes where username=?";
        PreparedStatement ps = null;
		try {
			ps = con.prepareStatement(select);
		} catch (SQLException e) {
			System.out.println("Erro ao preparar statament");
		}
        try {
			ps.setString(1, user.getUserName());
		} catch (SQLException e) {
			System.out.println("Erro ao setar nome");
		}
        
        ResultSet rs = null;
		try {
			rs = ps.executeQuery();
		} catch (SQLException e) {
			System.out.println("Erro ao exucutar a query antes do while");
		}
		
        if(rs == null){
		    //Nega login, pois n�o existe ningu�m com o nome colocado no campo
		    return false;
		}
        
        try {
			while(rs.next()){
			    String  getpass = rs.getString(3);
			    if(user.getSenha().equals(getpass)){
			    	//Autoriza login, pois o usuario esta no BD;
			        return true;
			    }
			}
		} catch (SQLException e) {
			System.out.println("Erro ao acessar o bd para verificar se \"next\"");
		}
        //Se nao encontrar nenhuma senha igual, nega login!
        return false;
    }


    public static void adotarPet(Pet p){

        Connection con = BDConexao();
        String del = "Delete FROM pets WHERE nome = ?";
        PreparedStatement ps = null;
		try {
			ps = con.prepareStatement(del);
		} catch (SQLException e) {
			System.out.println("Erro ao preparar statament");
		}
        try {
			ps.setString(1,p.getNome());
		} catch (SQLException e) {
			System.out.println("Erro ao receber nome");
		}
        try {
			ps.execute();
		} catch (SQLException e) {
			System.out.println("Erro ao executar statament");
		}

		String up = "ALTER TABLE pets AUTO_INCREMENT=?";
		String pegarId = "Select pet_id FROM pets";

		PreparedStatement pss = null;
		try{
			pss = con.prepareStatement(pegarId);
		}catch(SQLException e){
			System.out.println("Erro ao preparar statement");
		}

		ResultSet rss = null;
		try{
			rss = pss.executeQuery();
		}catch(SQLException e){
			System.out.println("Erro ao executar query");
		}

		rs.last();
		int id = rs.getInt(1);

		PreparedStatement psu = null;
		try{
			psu = con.prepareStatement(up);
		}catch(SQLException e){
			System.out.println("Erro ao preparar statement");
		}

		psu.setInt(1,id-1);

		ResultSet rsu = null;
		try{
			rsu = psu.executeQuery();
		}catch(SQLException e){
			System.out.println("Erro ao executar query");
		}

		

    }

    public static boolean existeChat(Usuario user1,Usuario user2) throws SQLException{

        Connection con = BDConexao();
        String select = "SELECT * FROM chat where user1_id=? AND user2_id=?";
        PreparedStatement ps = con.prepareStatement(select);
        ps.setInt(1, (int)user1.getId());
        ps.setInt(2, (int)user2.getId());

        ResultSet rs = ps.executeQuery();

        if(!rs)){
            return false;
        }

        return true;
    }

    public static void comecarChat(Usuario user1,Usuario user2) throws SQLException{
        
        Connection con = BDConexao();
        
        String insert = "INSERT INTO chat(user1_id,user2_id,confirma_user1,confirma_user2) Values(?,?,?,?)";
        PreparedStatement psi =  con.prepareStatement(insert);
        psi.setInt(1, (int)user1.getId());
		psi.setInt(2, (int)user2.getId());
		psi.setBoolean(3, false);
		psi.setBoolean(4, false);

        @SuppressWarnings("unused")
		psi.execute();
        
    }

    public static HashMap<Integer,String> getMensagensAntigas(Usuario user1, Usuario user2) throws SQLException{

        Connection con = BDConexao();
        String select = "SELECT * FROM chat where user1_id=? AND user2_id=?";
        PreparedStatement ps = con.prepareStatement(select);
        ps.setInt(1, (int)user1.getId());
        ps.setInt(2, (int)user2.getId());

        ResultSet rs = ps.executeQuery();

        int id = rs.getInt(1);
        
        String puxarMensagens = "Select mensagem,id_remetente FROM mensagens INNER JOIN chat ON chat.chat_id = mensagens.id_chat WHERE chat.chat_id = ?";
        PreparedStatement psp = con.prepareStatement(puxarMensagens);
        psp.setInt(1,id);

        ResultSet rsp = psp.executeQuery();

        HashMap<Integer,String> messages = new HashMap<Integer,String>();
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
        psc.setInt(2,(int)user1.getId());
        psc.setInt(3,(int)user2.getId());

        ResultSet rsc = psc.executeQuery();


        int chat = rsc.getInt(1);


        String inserirMensagem = "Insert INTO mensagens(id_chat,id_remetente,mensagem) VALUES(?,?,?)";
        PreparedStatement psi = con.prepareStatement(inserirMensagem);
        psi.setInt(1,chat);
        psi.setInt(2,(int)user2.getId());
        psi.setString(3,mensagem);

        @SuppressWarnings("unused")
		psi.execute();
      
    }
    
    public static int getSizeUser(Usuario user1) throws SQLException{
    	
    	Connection con = BDConexao();
    	String cont = "SELECT COUNT(chat_id) FROM chat WHERE user1_id = ?";
    	PreparedStatement ps = con.prepareStatement(cont);
    	ps.setInt(1,(int)user1.getId());
    	
    	ResultSet rs = ps.executeQuery();
    	
    	return rs.getInt(1);
    	
    }


       
    public static int getSizePets(){
    	
    	Connection con = BDConexao();
    	String cont = "SELECT COUNT(pet_id) FROM pets";
    	PreparedStatement ps = null;
		try {
			ps = con.prepareStatement(cont);
		} catch (SQLException e) {
			System.out.println("Erro ao preparar a Query");
		}
    	
    	ResultSet rs = null;
		try {
			rs = ps.executeQuery();
		} catch (SQLException e) {
			System.out.println("Erro ao executar o Query RS");
		}
    	
    	try {
			return rs.getInt(1);
		} catch (SQLException e) {
			return 0;
		}
    }



    public static Usuario[] listaContatos(Usuario user1) throws SQLException{
    	
    	Connection con = BDConexao();
    	String select = "SELECT * FROM chat WHERE user1_id = ?";
    	PreparedStatement ps = con.prepareStatement(select);
    	ps.setInt(1,(int)user1.getId());

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

    
    public static Usuario retornaUsuario(int id) throws SQLException{

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

    public static Pet retornaPet(int index){

        int indextotal = getSizePets();
        int aux = indextotal - index+1;

        Connection con = BDConexao();
        String select = "SELECT * FROM pets WHERE pet_id= ?";
        PreparedStatement ps = null;
		try {
			ps = con.prepareStatement(select);
		} catch (SQLException e) {
			System.out.println("Erro ao preparar a statament retornaPet");
		}
        try {
			ps.setInt(1,aux);
		} catch (SQLException e) {
			System.out.println("Erro ou setar Int RetornaPet");
		}

        ResultSet rs = null;
		try {
			rs = ps.executeQuery();
		} catch (SQLException e) {
			System.out.println("Erro ao executar a Query retornaPet");
		}

        Pet p = new Pet();

        try {
			while(rs.next()){
			    p.setPetID(rs.getInt(1));
			    p.setEspecie(rs.getString(2));
			    p.setNome(rs.getString(3));
			    p.setSexo(rs.getString(4));
			    p.setDetalhes(rs.getString(5));
			    p.setAnuncianteID(rs.getInt(6));
			    Usuario anunciante = new Usuario();
			    anunciante = retornaUsuario((int)p.getAnuncianteID());
			    p.setAnunciante(anunciante);
			}
		} catch (SQLException e) {
			System.out.println("Erro ao setar return retornaPet");
		}
        
        try {
        	rs.last();
            InputStream in = rs.getBlob(7).getBinaryStream();
            BufferedImage image = ImageIO.read(in);
			p.setIcone(SwingFXUtils.toFXImage(image, null));
		} catch (SQLException e) {
			System.out.println("Exception SQL ao setar o icone");
		} catch (IOException e) {
			System.out.println("Exception IO ao setar o icone");
		}
        
        
        return p;

    }

}
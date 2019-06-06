package bd;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import backend.Usuario;
import backend.Pet;

class BDConexao{
    
    
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
        ps.setLong(1, p.getPetID());
        ps.setString(2, p.getEspecie());
        ps.setString(3, p.getNome());
        ps.setString(4, p.getSexo());
        ps.setString(5, p.getDetalhes());
        ps.setString(6, p.getAnunciante().getNome());
        
        
        ResultSet rs = ps.executeQuery();
        
    }
    // Função que cadastra o usuï¿½rio no Banco de Dados
    public void cadastroUser(Usuario user) throws SQLException{
        
        Connection con = BDConexao();
        String insert = "INSERT INTO clientes values(?,?,?,?,?,?,?,?,?)";
        PreparedStatement ps = con.prepareStatement(insert);
        ps.setLong(1, user.getId());
        ps.setString(2, user.getUserName());
        ps.setString(3, user.getSenha());
        ps.setString(4, user.getNome());
        ps.setInt(5, user.getIdade());
        ps.setString(6, user.getCpf());
        ps.setString(7, user.getCidade());
        ps.setString(8, user.getEndereco());
        ps.setString(9, user.getCep());
        
        ResultSet rs = ps.executeQuery();
    }
    
    public void loginUser(Usuario user) throws SQLException{
        
        Connection con = BDConexao();
        String select = "SELECT * FROM clientes where nomelogin=?";
        PreparedStatement ps = con.prepareStatement(select);
        ps.setString(1, user.getUserName());
        
        ResultSet rs = ps.executeQuery();
        
        if(rs.wasNull()){
            //Mensagem de erro, pois nï¿½o existe ninguï¿½m com o nome colocado no campo
            return;
        }
        
        while(rs.next()){
            String  getpass = rs.getString(3);
            if(!user.getSenha().equals(getpass)){
                //Mensagem de erro, pois as  senhas sï¿½o diferentes
                return;
            }
            
            else{
                //Abre a JFrame com as informaï¿½ï¿½es do usuario
            }
            
        }
        
        
        
    }


    public void adotarPet(Pet p){

        Connection con = BDConexao();
        String del = "Delete FROM pets WHERE nome = ?";
        PreparedStatement ps = con.prepareStatement(del);
        ps.setString(1,p.getNome());


        ResultSet rs = ps.executeQuery();



    }


    public void comecarChat(Usuario user1, Usuario user2){

        Connection con = BDConexao();
        String select = "SELECT * FROM chat where user1_id=? AND user2_id=?";
        PreparedStatement ps = con.prepareStatement(select);
        ps.setLong(1, user1.getId());
        ps.setLong(2, user2.getId());

        ResultSet rs = ps.executeQuery();

        if(rs.wasNull()){
            
            String insert = "INSERT INTO chat Values(?,?)";
            PreparedStatement psi =  con.prepareStatement(insert);
            psi.setLong(2, user1.getId());
            psi.setLong(3, user2.getId());

            ResultSet rsi = psi.executeQuery();
            
        }else{

            Long id = rs.getLong(1);
            
            String puxarMensagens = "Select mensagem,id_remetente FROM mensagens INNER JOIN chat ON chat.chat_id = mensagens.id_chat WHERE chat.chat_id = ?";
            PreparedStatement psp = con.prepareStatement(puxarMensagens);
            psp.setLong(1,id);

            ResultSet rsp = psp.executeQuery();


            while(rsp.next()){
                String msg = rsp.getString(1);
                Long sent = rsp.getLong(2);

                //mensagem serÃ¡ setada no novo componente que vai ser criado na GUI
                /*

                if(sent.equals(user1.getId())) <- Coloca mensagem na direita (user1 = o cara logado nesta conta)

                else   <- Coloca mensagem na esquerda (user2 = o cara que ele estÃ¡ conversando)

                */
            }

           

        }

    }

    public void criarMensagem(Usuario user1, Usuario user2){

        String pegarID = "Select chat_id FROM chat WHERE user1_id = ? AND user2_id = ?";
        PreparedStatement psc = con.prepareStatement(pegarID);
        psc.setLong(2,user1.getId());
        psc.setLong(3,user2.getId());

        ResultSet rsc = psc.executeQuery();


        Long chat = rsc.getLong(1);


        String inserirMensagem = "Insert INTO mensagens VALUES(?) WHERE id_chat = ?";
        PreparedStatement psi = con.prepareStatement(inserirMensagem);
        //Pegar mensagem que foi escrita na GUI e colocar em psi.setString(4,);

        ResultSet rsi = psi.executeQuery();
        

    }




    
}
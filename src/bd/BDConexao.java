package bd;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import backend.Usuario;



class BDconexao{
    
    
    DriverManager driver;
    
    public static Connection Bd_conexao() throws SQLException{
        
       String con="jdbc:mysql://localhost:3306/poo_db";
       String server_user = "root";
       String server_pass = "rootpass";
       
       Connection connect = null;
       connect = DriverManager.getConnection(con,server_user,server_pass);
       
       return connect;
        
    }
    
    public void cadastroPet(int id,String nome, String tipo) throws SQLException{
        
        Connection con = BDconexao();
        String insert = "INSERT INTO pets values(?,?,?)";
        PreparedStatement ps = con.prepareStatement(insert);
        ps.setInt(1, id);
        ps.setString(2, nome);
        ps.setString(3, tipo);
        
        ResultSet rs = ps.executeQuery();
        
    }
    
    public void cadastroUser(Usuario user) throws SQLException{
        
        Connection con = BDconexao();
        String insert = "INSERT INTO clientes values(?,?,?)";
        PreparedStatement ps = con.prepareStatement(insert);
        ps.setInt(1, user.id);
        ps.setString(2, user.nome);
        ps.setString(3, user.pass);
        ps.setString(4, user.cpf);
        ps.setString(5, user.cidade);
        ps.setString(6, user.endereco);
        ps.setString(7, user.cep);
        
        ResultSet rs = ps.executeQuery();
    }
    
    public void loginUser(Usuario user) throws SQLException{
        
        Connection con = BDconexao();
        String select = "SELECT * FROM clientes where nome=?";
        PreparedStatement ps = con.prepareCall(select);
        ps.setString(1, user.nome);
        
        ResultSet rs = ps.executeQuery();
        
        if(rs.wasNull()){
            //Mensagem de erro, pois nao existe ninguem com o nome colocado no campo
            return;
        }
        
        while(rs.next()){
            String  getpass = rs.getString(3);
            if(!user.pass.equals(getpass)){
                //Mensagem de erro, pois as  senhas sao diferentes
                return;
            }
            
            else{
                //Abre a JFrame com as informacoes do usuario
            }
            
        }
        
        
        
    }
    
    
}

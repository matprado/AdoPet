
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;



class BDconexao{
    
    
    DriverManager driver;
    
    public static Connection BDconexao() throws SQLException{
        
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
    // Função que cadastra o usuário no Banco de Dados
    public void cadastroUser(Usuario user) throws SQLException{
        
        Connection con = BDconexao();
        String insert = "INSERT INTO clientes values(?,?,?,?,?,?,?,?,?)";
        PreparedStatement ps = con.prepareStatement(insert);
        ps.setInt(1, user.getId());
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
        
        Connection con = BDconexao();
        String select = "SELECT * FROM clientes where nomelogin=?";
        PreparedStatement ps = con.prepareStatement(select);
        ps.setString(1, user.getUserName());
        
        ResultSet rs = ps.executeQuery();
        
        if(rs.wasNull()){
            //Mensagem de erro, pois não existe ninguém com o nome colocado no campo
            return;
        }
        
        while(rs.next()){
            String  getpass = rs.getString(3);
            if(!user.getSenha().equals(getpass)){
                //Mensagem de erro, pois as  senhas são diferentes
                return;
            }
            
            else{
                //Abre a JFrame com as informações do usuario
            }
            
        }
        
        
        
    }
    
    
}
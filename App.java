import java.sql.*;

public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Consulta de clientes!");
        
        // URL de conexão do Supabase
        String url = "jdbc:postgresql://aws-1-sa-east-1.pooler.supabase.com:6543/postgres";
        String user = "postgres.wdkkakpbcxfzqhjalxse"; // Usuário do Supabase
        String password = "nX1D07utAev1mVx3"; // Senha do banco de dados

        // Estabelecer a conexão com o banco de dados
        Connection c = DriverManager.getConnection(url, user, password);
        System.out.println("Conexão ok!");

        // Consulta SQL para obter os dados da tabela
        String sql = "SELECT * FROM clientes";
        PreparedStatement stm = c.prepareStatement(sql);
        ResultSet resultado = stm.executeQuery();

        // Exibindo os dados dos clientes
        while (resultado.next()) {
            long id = resultado.getLong("id");
            String nome = resultado.getString("nome");
            String email = resultado.getString("email");
            System.out.println("ID: " + id + " - Nome: " + nome + " - Email: " + email);
        }

        // Fechar a conexão
        c.close();
    }
}

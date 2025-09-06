import java.sql.*;

public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Consulta e inserção de clientes!");

        // URL de conexão do Supabase
        String url = "jdbc:postgresql://aws-1-sa-east-1.pooler.supabase.com:6543/postgres";
        String user = "postgres.wdkkakpbcxfzqhjalxse"; // Usuário do Supabase
        String password = "nX1D07utAev1mVx3"; // Senha do banco de dados

        // Tenta estabelecer a conexão dentro de um bloco try-with-resources
        try (Connection c = DriverManager.getConnection(url, user, password)) {
            System.out.println("Conexão ok!");
            System.out.println("------------------------------------");

            // --- ETAPA 1: INSERÇÃO DE UM NOVO CLIENTE ---
            System.out.println("Inserindo um novo cliente...");
            // O '?' evita SQL Injection e permite definir os valores depois
            String sqlInsert = "INSERT INTO clientes (nome, email) VALUES (?, ?)";

            try (PreparedStatement pstmt = c.prepareStatement(sqlInsert)) {
                // Define os valores para o novo cliente
                pstmt.setString(1, "Maria Souza");
                pstmt.setString(2, "maria.souza@example.com");

                int linhasAfetadas = pstmt.executeUpdate(); // Executa o comando de inserção

                if (linhasAfetadas > 0) {
                    System.out.println("--> Novo cliente inserido com sucesso!");
                }
            }
            System.out.println("------------------------------------");


            // --- ETAPA 2: CONSULTA E EXIBIÇÃO DE TODOS OS CLIENTES ---
            System.out.println("Exibindo todos os clientes (incluindo o novo):");
            String sqlSelect = "SELECT * FROM clientes ORDER BY id";

            try (PreparedStatement stm = c.prepareStatement(sqlSelect)) {
                ResultSet resultado = stm.executeQuery();

                // Exibindo os dados dos clientes
                while (resultado.next()) {
                    long id = resultado.getLong("id");
                    String nome = resultado.getString("nome");
                    String email = resultado.getString("email");
                    System.out.println("ID: " + id + " - Nome: " + nome + " - Email: " + email);
                }
            }

        } catch (SQLException e) {
            System.err.println("Ocorreu um erro de SQL: " + e.getMessage());
            e.printStackTrace();
        }
        System.out.println("\nProcesso finalizado.");
    }
}
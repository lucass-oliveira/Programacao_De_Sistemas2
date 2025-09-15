import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String url = "jdbc:postgresql://aws-1-sa-east-1.pooler.supabase.com:6543/postgres";
        String user = "postgres.wdkkakpbcxfzqhjalxse"; // Usuário do Supabase
        String password = "nX1D07utAev1mVx3"; // Senha do banco de dados.

        try {
            // Garante registro do driver JDBC (útil se o SPI não carregar automaticamente)
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Driver JDBC do PostgreSQL não encontrado no classpath.");
            return;
        }

        try (Connection conn = ConnectionFactory.getConnection(url, user, password)) {
            if (conn == null) {
                System.out.println("Não foi possível conectar ao banco de dados.");
                return;
            }

            // Garante que a tabela necessária existe
            try {
                ensureTabelaContas(conn);
            } catch (SQLException e) {
                System.out.println("Falha ao preparar o schema: " + e.getMessage());
                return;
            }

            ContaDao dao = new ContaDao(conn);
            Scanner sc = new Scanner(System.in);
            while (true) {
                System.out.println();
                System.out.println("(1) Listar todas as contas");
                System.out.println("(2) Buscar conta pelo número");
                System.out.println("(3) Criar conta");
                System.out.println("(4) Alterar saldo");
                System.out.println("(5) Apagar conta");
                System.out.println("(0) Sair");
                System.out.print("Escolha: ");
                String opcao = sc.nextLine().trim();

                try {
                    switch (opcao) {
                        case "1":
                            listar(dao);
                            break;
                        case "2":
                            buscar(sc, dao);
                            break;
                        case "3":
                            criar(sc, dao);
                            break;
                        case "4":
                            alterar(sc, dao);
                            break;
                        case "5":
                            apagar(sc, dao);
                            break;
                        case "0":
                            System.out.println("Saindo...");
                            sc.close();
                            return;
                        default:
                            System.out.println("Opção inválida.");
                    }
                } catch (SQLException e) {
                    System.out.println("Erro na operação: " + e.getMessage());
                }
            }
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private static void ensureTabelaContas(Connection conn) throws SQLException {
        String ddl = "CREATE TABLE IF NOT EXISTS contas (" +
                     "nro_conta INTEGER PRIMARY KEY, " +
                     "saldo NUMERIC(15,2) NOT NULL" +
                     ")";
        try (Statement st = conn.createStatement()) {
            st.executeUpdate(ddl);
        }
    }

    private static void listar(ContaDao dao) throws SQLException {
        List<Conta> contas = dao.listarContas();
        if (contas.isEmpty()) {
            System.out.println("Nenhuma conta encontrada.");
        } else {
            contas.forEach(System.out::println);
        }
    }

    private static void buscar(Scanner sc, ContaDao dao) throws SQLException {
        System.out.print("Número da conta: ");
        int numero = Integer.parseInt(sc.nextLine().trim());
        Conta c = dao.buscarContaPorNumero(numero);
        if (c == null) System.out.println("Conta não encontrada.");
        else System.out.println(c);
    }

    private static void criar(Scanner sc, ContaDao dao) throws SQLException {
        System.out.print("Número da conta: ");
        int numero = Integer.parseInt(sc.nextLine().trim());
        System.out.print("Saldo inicial: ");
        double saldo = Double.parseDouble(sc.nextLine().trim());
        Conta c = new Conta(numero, BigDecimal.valueOf(saldo));
        boolean ok = dao.criarConta(c);
        System.out.println(ok ? "Conta criada com sucesso." : "Falha ao criar conta.");
    }

    private static void alterar(Scanner sc, ContaDao dao) throws SQLException {
        System.out.print("Número da conta: ");
        int numero = Integer.parseInt(sc.nextLine().trim());
        System.out.print("Novo saldo: ");
        double novoSaldo = Double.parseDouble(sc.nextLine().trim());
        boolean ok = dao.alterarSaldo(numero, novoSaldo);
        System.out.println(ok ? "Saldo alterado com sucesso." : "Falha ao alterar saldo.");
    }

    private static void apagar(Scanner sc, ContaDao dao) throws SQLException {
        System.out.print("Número da conta: ");
        int numero = Integer.parseInt(sc.nextLine().trim());
        boolean ok = dao.apagarConta(numero);
        System.out.println(ok ? "Conta apagada com sucesso." : "Falha ao apagar conta.");
    }
}

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ContaDao {
    private final PreparedStatement pstmCreate;
    private final PreparedStatement pstmReadAll;
    private final PreparedStatement pstmReadByNumber;
    private final PreparedStatement pstmUpdateSaldo;
    private final PreparedStatement pstmDelete;

    public ContaDao(Connection c) throws SQLException {
        this.pstmCreate = c.prepareStatement("INSERT INTO CONTAS (NRO_CONTA, SALDO) VALUES (?, ?)");
        this.pstmReadAll = c.prepareStatement("SELECT NRO_CONTA, SALDO FROM CONTAS");
        this.pstmReadByNumber = c.prepareStatement("SELECT NRO_CONTA, SALDO FROM CONTAS WHERE NRO_CONTA = ?");
        this.pstmUpdateSaldo = c.prepareStatement("UPDATE CONTAS SET SALDO = ? WHERE NRO_CONTA = ?");
        this.pstmDelete = c.prepareStatement("DELETE FROM CONTAS WHERE NRO_CONTA = ?");
    }

    public List<Conta> listarContas() throws SQLException {
        List<Conta> contas = new ArrayList<>();
        try (ResultSet rs = pstmReadAll.executeQuery()) {
            while (rs.next()) {
                int numero = rs.getInt("NRO_CONTA");
                BigDecimal saldo = rs.getBigDecimal("SALDO");
                contas.add(new Conta(numero, saldo));
            }
        }
        return contas;
    }

    public Conta buscarContaPorNumero(int numero) throws SQLException {
        pstmReadByNumber.setInt(1, numero);
        try (ResultSet rs = pstmReadByNumber.executeQuery()) {
            if (rs.next()) {
                BigDecimal saldo = rs.getBigDecimal("SALDO");
                return new Conta(numero, saldo);
            }
            return null;
        }
    }

    public boolean criarConta(Conta conta) throws SQLException {
        pstmCreate.setInt(1, conta.getNumero());
        pstmCreate.setBigDecimal(2, conta.getSaldo());
        return pstmCreate.executeUpdate() == 1;
    }

    public boolean alterarSaldo(int numero, double novoSaldo) throws SQLException {
        pstmUpdateSaldo.setBigDecimal(1, BigDecimal.valueOf(novoSaldo));
        pstmUpdateSaldo.setInt(2, numero);
        return pstmUpdateSaldo.executeUpdate() == 1;
    }

    public boolean apagarConta(int numero) throws SQLException {
        pstmDelete.setInt(1, numero);
        return pstmDelete.executeUpdate() == 1;
    }
}


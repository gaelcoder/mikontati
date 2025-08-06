import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import classes.Contato;

public class ContatoDAO {
    public void criarContato(Contato contato){

        String sql = "INSERT INTO contatos (nome, sobrenome, telefone, email) VALUES (?, ?, ?, ?);";
        try (Connection conexao = MySQLConnection.getConexao();
             PreparedStatement statement = conexao.prepareStatement(sql)) {

            statement.setString(1, contato.getNome());
            statement.setString(2, contato.getSobrenome());
            statement.setString(3, contato.getTelefone());
            statement.setString(4, contato.getEmail());

            statement.executeUpdate();
            System.out.println("Contato inserido com sucesso!");

        } catch (SQLException e) {
            System.err.println("Erro ao inserir contato no banco de dados.");
            e.printStackTrace();
        }
    }

    public void atualizarContato(Contato contato) {

        String sql = "UPDATE contatos SET nome = ?, sobrenome = ?, email = ?, telefone = ? WHERE mktiID = ?";

        try (Connection conexao = MySQLConnection.getConexao();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setString(1, contato.getNome());
            stmt.setString(2, contato.getSobrenome());
            stmt.setString(3, contato.getEmail());
            stmt.setString(4, contato.getTelefone());
            stmt.setInt(5, contato.getId());

            int linhasAfetadas = stmt.executeUpdate();

            if (linhasAfetadas > 0) {
                System.out.println("Contato atualizado com sucesso!");
            } else {
                System.out.println("Nenhum contato encontrado com o ID " + contato.getId() + ". Nenhuma atualização foi feita.");
            }

        } catch (SQLException e) {
            System.err.println("Erro ao atualizar contato no banco de dados.");
            e.printStackTrace();
        }
    }
}


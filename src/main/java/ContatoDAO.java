import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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

    public void excluirContato(int id) {

        String sql = "DELETE FROM contatos WHERE mktiID = ?";

        try (Connection conexao = MySQLConnection.getConexao();
             PreparedStatement stmt = conexao.prepareStatement(sql)){

            stmt.setInt(1, id);
            int linhasAfetadas = stmt.executeUpdate();

            if (linhasAfetadas > 0) {
                System.out.println("Contato excluído com sucesso!");
            } else {
                System.out.println("Nenhum contato encontrado com o ID " + id + ". Nenhuma exclusão foi feita.");
            }

        } catch (SQLException e) {
            System.err.println("Erro ao excluir contato no banco de dados.");
            e.printStackTrace();
        }
    }

    public void pesquisarContato(String pesquisa){

        String sql = "SELECT * FROM contatos WHERE nome LIKE ? OR sobrenome LIKE ? OR email LIKE ? OR telefone LIKE ?";

        try(Connection conexao = MySQLConnection.getConexao();
            PreparedStatement stmt = conexao.prepareStatement(sql)){

            String parametroPesquisa = "%" + pesquisa + "%";
            stmt.setString(1, parametroPesquisa);
            stmt.setString(2, parametroPesquisa);
            stmt.setString(3, parametroPesquisa);
            stmt.setString(4, parametroPesquisa);

            System.out.println("Pesquisando contatos...");
            ResultSet rs = stmt.executeQuery();

            if (!rs.next()) {
                System.out.println("Nenhum contato encontrado com o termo '" + pesquisa + "'.");
            } else {
                System.out.println("Contatos encontrados!");
                System.out.println("---------------------------------------------");
                System.out.println("ID\tNome\tSobrenome\tTelefone\tEmail");
                do {
                    System.out.print(rs.getInt("mktiID") + "\t");
                    System.out.print(rs.getString("nome") + "\t");
                    System.out.print(rs.getString("sobrenome") + "\t\t");
                    System.out.print(rs.getString("telefone") + "\t");
                    System.out.println(rs.getString("email"));
                } while (rs.next());
                System.out.println("---------------------------------------------");
            }

        } catch (SQLException e) {
            System.err.println("Erro ao pesquisar contato no banco de dados.");
            e.printStackTrace();
        }
    }

    public void exibirTodosOsContatos(){
        String sql = "SELECT * FROM contatos";

        try(Connection conexao = MySQLConnection.getConexao();
        PreparedStatement stmt = conexao.prepareStatement(sql)){

            System.out.println("Pesquisando contatos...");
            ResultSet rs = stmt.executeQuery();
            if (!rs.next()){
                System.out.println("Nenhum contato encontrado");
            } else {
                System.out.println("---------------------------------------------");
                System.out.println("ID\tNome\tSobrenome\tTelefone\tEmail");
                do {
                    System.out.print(rs.getInt("mktiID") + "\t");
                    System.out.print(rs.getString("nome") + "\t");
                    System.out.print(rs.getString("sobrenome") + "\t\t");
                    System.out.print(rs.getString("telefone") + "\t");
                    System.out.println(rs.getString("email"));
                } while (rs.next());
                System.out.println("---------------------------------------------");
            }

        } catch (SQLException e){
            System.err.println("Erro ao exibir todos os contatos no banco de dados.");
            e.printStackTrace();
        }

    }

    public Contato buscarContatoPorId(int id) {
        String sql = "SELECT * FROM contatos WHERE mktiID = ?";
        try (Connection conexao = MySQLConnection.getConexao();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Contato(
                        rs.getInt("mktiID"),
                        rs.getString("nome"),
                        rs.getString("sobrenome"),
                        rs.getString("telefone"),
                        rs.getString("email")
                );
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar contato no banco de dados.");
            e.printStackTrace();
        }
        return null;
    }

    public void atualizarContato(Contato contatoComNovosDados) {
        Contato contatoAtual = buscarContatoPorId(contatoComNovosDados.getId());

        if (contatoAtual == null) {
            System.out.println("Nenhum contato encontrado com o ID " + contatoComNovosDados.getId() + ". Nenhuma atualização foi feita.");
            return;
        }

        // Se o novo dado for vazio, usa o dado antigo. Senão, usa o novo.
        String nomeFinal = contatoComNovosDados.getNome().isEmpty() ? contatoAtual.getNome() : contatoComNovosDados.getNome();
        String sobrenomeFinal = contatoComNovosDados.getSobrenome().isEmpty() ? contatoAtual.getSobrenome() : contatoComNovosDados.getSobrenome();
        String emailFinal = contatoComNovosDados.getEmail().isEmpty() ? contatoAtual.getEmail() : contatoComNovosDados.getEmail();
        String telefoneFinal = contatoComNovosDados.getTelefone().isEmpty() ? contatoAtual.getTelefone() : contatoComNovosDados.getTelefone();

        String sql = "UPDATE contatos SET nome = ?, sobrenome = ?, email = ?, telefone = ? WHERE mktiID = ?";
        try (Connection conexao = MySQLConnection.getConexao();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setString(1, nomeFinal);
            stmt.setString(2, sobrenomeFinal);
            stmt.setString(3, emailFinal);
            stmt.setString(4, telefoneFinal);
            stmt.setInt(5, contatoComNovosDados.getId());

            stmt.executeUpdate();
            System.out.println("Contato atualizado com sucesso!");

        } catch (SQLException e) {
            System.err.println("Erro ao atualizar contato no banco de dados.");
            e.printStackTrace();
        }
    }
}
import classes.Contato;

import java.io.IOException;
import java.util.Scanner;

public class MiKontati {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean executando = true;

        System.out.println("MiKontati - Sua agenda de contatos Java + MySQL!");
        System.out.println("---------------------------------------------");
        pressioneEnterParaContinuar(scanner);

        while (executando) {
            agenda();
            System.out.print("Sua escolha: ");
            int escolha = scanner.nextInt();
            scanner.nextLine();

            switch (escolha) {
                case 1:
                    criarKontato();
                    break;
                case 2:
                    verTodosContatos();
                    break;
                case 3:
                    pesquisarKontato();
                    break;
                case 4:
                    atualizarKontato();
                    break;
                case 5:
                    deletarKontato();
                    break;
                case 6:
                    executando = false;
                    System.out.println("Saindo da agenda. Até logo!");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }

            if (executando) {
                pressioneEnterParaContinuar(scanner);
            }
        }
        scanner.close();
    }


    public static void verTodosContatos() {
        System.out.println("Lista de Todos os Contatos");
        System.out.println("--------------------------\n");
        ContatoDAO contatoDAO = new ContatoDAO();
        contatoDAO.exibirTodosOsContatos();
    }


    private static void pressioneEnterParaContinuar(Scanner scanner) {
        System.out.println("\nPressione Enter para continuar...");
        scanner.nextLine();
    }


    public static void agenda(){
        System.out.println("Escolha uma função:");
        System.out.println("[1] - Criar contato");
        System.out.println("[2] - Ver contatos salvos");
        System.out.println("[3] - Pesquisar contato");
        System.out.println("[4] - Editar contato");
        System.out.println("[5] - Excluir contato");
        System.out.println("[6] - Sair");
    }

    public static void pesquisarKontato(){
        String pesquisa;
        System.out.println("Pesquisar contato: ");
        Scanner scanner = new Scanner(System.in);
        pesquisa = scanner.nextLine();

        ContatoDAO contatoDAO = new ContatoDAO();
        contatoDAO.pesquisarContato(pesquisa);

    }

    public static void criarKontato(){

        String nome;
        String sobrenome;
        String telefone;
        String email;

        System.out.println("Criando novo contato");
        System.out.println("--------------------\n");

        System.out.println("Nome: ");
        Scanner scanner = new Scanner(System.in);
        nome = scanner.nextLine();
        System.out.println("Sobrenome: ");
        sobrenome = scanner.nextLine();
        System.out.println("Telefone: ");
        telefone = scanner.nextLine();
        System.out.println("Email: ");
        email = scanner.nextLine();

        Contato novoContato = new Contato(nome, sobrenome, telefone, email);
        ContatoDAO contatoDAO = new ContatoDAO();
        contatoDAO.criarContato(novoContato);

    }

    public static void deletarKontato(){
        int id;
        System.out.println("Digite o ID do contato: ");
        Scanner scanner = new Scanner(System.in);
        id = scanner.nextInt();

        ContatoDAO contatoDAO = new ContatoDAO();
        contatoDAO.excluirContato(id);

    }

    public static void atualizarKontato(){
        int id;
        Scanner scanner = new Scanner(System.in);
        ContatoDAO contatoDAO = new ContatoDAO();

        System.out.println("Atualizar contato");
        System.out.println("--------------------");
        System.out.print("Digite o ID do contato que deseja atualizar: ");
        id = scanner.nextInt();
        scanner.nextLine();

        Contato contatoAtual = contatoDAO.buscarContatoPorId(id);

        if (contatoAtual == null) {
            System.out.println("Contato com ID " + id + " não encontrado.");
            return;
        }

        System.out.println("\nDados atuais do contato:");
        System.out.println("Nome: " + contatoAtual.getNome());
        System.out.println("Sobrenome: " + contatoAtual.getSobrenome());
        System.out.println("Telefone: " + contatoAtual.getTelefone());
        System.out.println("Email: " + contatoAtual.getEmail());
        System.out.println("\nDigite os novos dados. Pressione Enter para manter o valor atual.");

        System.out.print("Novo Nome: ");
        String nome = scanner.nextLine();
        System.out.print("Novo Sobrenome: ");
        String sobrenome = scanner.nextLine();
        System.out.print("Novo Telefone: ");
        String telefone = scanner.nextLine();
        System.out.print("Novo Email: ");
        String email = scanner.nextLine();

        Contato contatoComNovosDados = new Contato(id, nome, sobrenome, telefone, email);
        contatoDAO.atualizarContato(contatoComNovosDados);
    }


}

import classes.Contato;

import java.util.Scanner;

public class MiKontati {

    public static void main(String[] args) {

        System.out.println("MiKontati - Sua agenda de contatos Java + MySQL!");
        System.out.println("---------------------------------------------");
        agenda();
        atualizarKontato();


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

    public static void atualizarKontato(){
        int id;
        String nome;
        String sobrenome;
        String telefone;
        String email;

        System.out.println("Atualizar contato");
        System.out.println("--------------------");

        System.out.println("ID do contato: ");
        Scanner scanner = new Scanner(System.in);
        id = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Nome: ");
        nome = scanner.nextLine();
        System.out.println("Sobrenome: ");
        sobrenome = scanner.nextLine();
        System.out.println("Telefone: ");
        telefone = scanner.nextLine();
        System.out.println("Email: ");
        email = scanner.nextLine();

        Contato atualizandoKontato = new Contato(id, nome, sobrenome, telefone, email);
        ContatoDAO contatoDAO = new ContatoDAO();
        contatoDAO.atualizarContato(atualizandoKontato);
    }

}

package main.Java;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.Scanner;

import main.Java.lib.Usuario;
import main.Java.lib.Movimentacao;
import main.Java.lib.Livro;

public class Biblioteca {
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        Scanner scan = new Scanner(System.in);
        List<Usuario> listaUsuarios = new ArrayList<>();
        List<Livro> listaLivro = new ArrayList<>();
        List<Movimentacao> listaMovimentacao = new ArrayList<>();
        int opcao;

        do {
            Menu();
            System.out.println();
            System.out.print("Digite uma opção: ");
            opcao = scan.nextInt();

            switch (opcao) {
                case 0:
                    System.out.println("Encerrando aplicação.");
                    break;
                case 1:
                    inserirNovoLivro(scan, listaLivro);
                    break;
                case 2:
                    exibirTodosLivros(listaLivro);
                    break;
                case 3:
                    inserirNovoUsuario(scan, listaUsuarios);
                    break;
                case 4:
                    exibirTodosUsuarios(listaUsuarios);
                    break;
                case 5:
                    efetuarEmprestimo(scan, listaLivro, listaUsuarios, listaMovimentacao);
                    break;
                case 6:
                    devolverLivro(scan, listaLivro, listaMovimentacao);
                    break;
                case 7:
                    exibirMovimentacoesUsuarioEspecifico(scan, listaMovimentacao, listaUsuarios);
                    break;
            } 
        } while (opcao != 0);
        scan.close();
    }

    public static void Menu() {
        System.out.println();
        System.out.println("MENU:");
        System.out.println("0 - Sair (Encerrar Aplicação)");
        System.out.println("1 - Inserir novo Livro");
        System.out.println("2 - Exibir todos os Livros");
        System.out.println("3 - Inserir novo Usuario");
        System.out.println("4 - Exibir todos os Usuários");
        System.out.println("5 - Efetuar Empréstimo de Livro");
        System.out.println("6 - Devolver Livro");
        System.out.println("7 - Exibir Movimentacoes de Usuario Especifico");
    }

    public static void inserirNovoLivro(Scanner scan, List<Livro> listaLivro) {
        char resposta;
        do {
            System.out.print("Digite o nome do livro: ");
            scan.nextLine(); // Consumir a quebra de linha pendente
            String nome = scan.nextLine();
            System.out.print("Digite o número do livro: ");
            int numero = scan.nextInt();
            Livro livro = new Livro(nome, numero);
            listaLivro.add(livro);
            System.out.print("Deseja adicionar mais livros (S/N): ");
            resposta = scan.next().toUpperCase().charAt(0);
        } while (resposta == 'S');
    }

    public static void exibirTodosLivros(List<Livro> listaLivro) {
        System.out.println();
        System.out.println("Lista de livros: ");
        for (Livro livro : listaLivro) {  
            System.out.println("Titulo: " + livro.getNome() + ", Número: " + livro.getNumero());              
        }
    }

    public static void inserirNovoUsuario(Scanner scan, List<Usuario> listaUsuarios) {
        char resposta;
        do {
            System.out.print("Nome: ");
            scan.nextLine(); // Limpar o buffer.
            String nomeUsuario = scan.nextLine();
            System.out.print("Matricula: ");
            int matricula = scan.nextInt();
            System.out.print("Gênero (M/F): ");
            char genero = scan.next().toUpperCase().charAt(0);
            Usuario usuario = new Usuario(nomeUsuario, matricula, genero);
            listaUsuarios.add(usuario);
            System.out.print("Deseja adicionar mais usuários (S/N): ");
            resposta = scan.next().toUpperCase().charAt(0);
        } while (resposta == 'S');
    }

    public static void exibirTodosUsuarios(List<Usuario> listaUsuarios) {
        System.out.println();
        System.out.println("Lista de usuários: ");
        for (Usuario usuario : listaUsuarios) {
            System.out.println("Nome: " + usuario.getNome() + ", Matricula: " + usuario.getMatricula() + ", Gênero: " + usuario.getGenero());              
        }
    }

    public static void efetuarEmprestimo(Scanner scan, List<Livro> listaLivro, List<Usuario> listaUsuarios, List<Movimentacao> listaMovimentacao) {
        // Exibir todos os Livros disponíveis
        exibirTodosLivros(listaLivro);

        // Solicitar as informações para o empréstimo.
        System.out.println();
        System.out.print("Digite o nome do livro que deseja alugar: ");
        scan.nextLine();
        String nomeLivroEmprestimo = scan.nextLine();

        Optional<Livro> livroOptional = listaLivro.stream().filter(livro -> livro.getNome().equals(nomeLivroEmprestimo)).findFirst();

        if (livroOptional.isPresent()) {
            Livro livroEmprestimo = livroOptional.get();

            listaLivro.remove(livroEmprestimo);

            // Solicitar informações do usuário.
            System.out.print("Digite o nome do usuário: ");
            String nomeUsuarioEmprestimo = scan.nextLine();
            System.out.print("Digite o número de matrícula do usuário: ");
            int matriculaEmprestimo = scan.nextInt();
            System.out.print("Digite o gênero do usuário (M/F): ");
            char generoEmprestimo = scan.next().toUpperCase().charAt(0);

            // Calcular a data de devolução (7 dias).
            LocalDate dataPrevistaDevolucao = LocalDate.now().plusDays(7);

            // Cria uma nova instancia e adiciona a lista de movimentações.
            Movimentacao movimentacaoEmprestimo = new Movimentacao(new Usuario(nomeUsuarioEmprestimo, matriculaEmprestimo, generoEmprestimo), livroEmprestimo, dataPrevistaDevolucao, null);
            listaMovimentacao.add(movimentacaoEmprestimo);

            System.out.println("Empréstimo realizado com sucesso!");
        } else {
            System.out.println("Livro não encontrado na lista.");
        }
    }

    public static void devolverLivro(Scanner scan, List<Livro> listaLivro, List<Movimentacao> listaMovimentacao) {
        // Solicita informações para devolução.
        System.out.print("Digite o nome do livro que deseja devolver: ");
        scan.nextLine();
        String nomeLivroDevolucao = scan.nextLine();

        // Verifica se o livro foi emprestado.
        boolean livroDevolvido = false;
        for (Movimentacao movimentacao : listaMovimentacao) {
            if (movimentacao.getLivro().getNome().equals(nomeLivroDevolucao) && movimentacao.getDataEfetivaDevolucao() == null) {
                movimentacao.setDataEfetivaDevolucao(LocalDate.now());
                livroDevolvido = true;

                // Adicionando o livro novamente a lista de livros disponiveis.
                listaLivro.add(movimentacao.getLivro());
                System.out.println("Livro devolvido com sucesso!");
                break;
            }
        }
        if (!livroDevolvido) {
            System.out.println("Livro não encontrado na lista de empréstimos ou já foi devolvido anteriormente.");
        }
    }

    public static void exibirMovimentacoesUsuarioEspecifico(Scanner scan, List<Movimentacao> listaMovimentacao, List<Usuario> listaUsuarios) {
        exibirTodosUsuarios(listaUsuarios);

        System.out.print("Digite a matricula que deseja exibir as movimentações: ");
        int matriculaUsuarioMovimentacao = scan.nextInt();

        boolean movimentacaoEncontrada = false;
        for (Movimentacao movimentacao : listaMovimentacao) {
            if (movimentacao.getUsuario().getMatricula() == matriculaUsuarioMovimentacao) {
                System.out.println("Usuário: " + movimentacao.getUsuario().getNome() + ", Livro: " + movimentacao.getLivro().getNome() +
                        ", Data Prevista de Devolução: " + movimentacao.getDataPrevistaDevolucao() +
                        ", Data Efetiva de Devolução: " + movimentacao.getDataEfetivaDevolucao());
                movimentacaoEncontrada = true;
            }
        }
        if (!movimentacaoEncontrada) {
            System.out.println("Movimentações não encontradas para a matricula especificada.");
        }
    }
}
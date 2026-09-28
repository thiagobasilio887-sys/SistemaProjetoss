import java.util.List;
import java.util.Scanner;

import model.Projeto;
import service.ProjetoService;

public class Main {

    public static void main(String[] args) throws Exception {

        Scanner scanner = new Scanner(System.in);
        ProjetoService service = new ProjetoService();

        service.carregar();

        int opcao = -1;

        while (opcao != 0) {

            System.out.println();
            System.out.println("========================================");
            System.out.println("       SISTEMA DE PROJETOS");
            System.out.println("========================================");
            System.out.println("1 - Listar projetos");
            System.out.println("2 - Buscar projeto por ID");
            System.out.println("3 - Cadastrar projeto");
            System.out.println("4 - Alterar projeto");
            System.out.println("5 - Excluir projeto");
            System.out.println("6 - Buscar por categoria");
            System.out.println("7 - Buscar por status");
            System.out.println("0 - Sair");
            System.out.print("Escolha: ");

            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {

                case 1:

                    List<Projeto> projetos = service.listar();

                    if (projetos.isEmpty()) {
                        System.out.println("Nenhum projeto cadastrado.");
                    } else {

                        for (Projeto projeto : projetos) {
                            System.out.println("-------------------------");
                            projeto.exibirDados();
                        }
                    }

                    break;

                case 2:

                    System.out.print("Digite o ID: ");
                    int idBusca = scanner.nextInt();
                    scanner.nextLine();

                    Projeto encontrado = service.buscarPorId(idBusca);

                    if (encontrado != null) {
                        encontrado.exibirDados();
                    } else {
                        System.out.println("Projeto não encontrado.");
                    }

                    break;

                case 3:

                    System.out.print("ID: ");
                    int id = scanner.nextInt();
                    scanner.nextLine();

                    System.out.print("Nome: ");
                    String nome = scanner.nextLine();

                    System.out.print("Descrição: ");
                    String descricao = scanner.nextLine();

                    System.out.print("Categoria: ");
                    String categoria = scanner.nextLine();

                    System.out.print("Status: ");
                    String status = scanner.nextLine();

                    Projeto novoProjeto = new Projeto(
                            id,
                            nome,
                            descricao,
                            categoria,
                            status
                    );

                    service.adicionar(novoProjeto);
                    service.salvar();

                    System.out.println("Projeto cadastrado com sucesso.");

                    break;

                case 4:

                    System.out.print("ID do projeto que deseja alterar: ");
                    int idAlterar = scanner.nextInt();
                    scanner.nextLine();

                    Projeto projetoExistente =
                            service.buscarPorId(idAlterar);

                    if (projetoExistente == null) {

                        System.out.println("Projeto não encontrado.");

                    } else {

                        System.out.print("Novo nome: ");
                        String novoNome = scanner.nextLine();

                        System.out.print("Nova descrição: ");
                        String novaDescricao = scanner.nextLine();

                        System.out.print("Nova categoria: ");
                        String novaCategoria = scanner.nextLine();

                        System.out.print("Novo status: ");
                        String novoStatus = scanner.nextLine();

                        Projeto projetoAtualizado = new Projeto(
                                idAlterar,
                                novoNome,
                                novaDescricao,
                                novaCategoria,
                                novoStatus
                        );

                        boolean alterado =
                                service.alterar(projetoAtualizado);

                        if (alterado) {

                            service.salvar();

                            System.out.println(
                                    "Projeto alterado com sucesso."
                            );

                        } else {

                            System.out.println(
                                    "Não foi possível alterar."
                            );
                        }
                    }

                    break;

                case 5:

                    System.out.print(
                            "ID do projeto que deseja excluir: "
                    );

                    int idExcluir = scanner.nextInt();
                    scanner.nextLine();

                    boolean removido =
                            service.removerPorId(idExcluir);

                    if (removido) {

                        service.salvar();

                        System.out.println(
                                "Projeto excluído com sucesso."
                        );

                    } else {

                        System.out.println(
                                "Projeto não encontrado."
                        );
                    }

                    break;

                case 6:

                    System.out.print("Digite a categoria: ");
                    String categoriaBusca = scanner.nextLine();

                    List<Projeto> porCategoria =
                            service.buscarPorCategoria(categoriaBusca);

                    for (Projeto projeto : porCategoria) {
                        projeto.exibirDados();
                        System.out.println("-------------------------");
                    }

                    break;

                case 7:

                    System.out.print("Digite o status: ");
                    String statusBusca = scanner.nextLine();

                    List<Projeto> porStatus =
                            service.buscarPorStatus(statusBusca);

                    for (Projeto projeto : porStatus) {
                        projeto.exibirDados();
                        System.out.println("-------------------------");
                    }

                    break;

                case 0:

                    System.out.println("Encerrando o sistema...");
                    break;

                default:

                    System.out.println("Opção inválida.");
            }
        }

        scanner.close();
    }
}

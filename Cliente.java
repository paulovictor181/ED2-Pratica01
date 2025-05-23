import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

import enteties.RegistroClimatico;
import enteties.No;
import utilities.MicroControladores;

public class Cliente {

    private static Scanner sc = new Scanner(System.in);
    private static Servidor servidor = new Servidor();

    public static void main(String[] args) throws Exception {
        

        servidor.inicializarServidor();

        MicroControladores microControlador1 = new MicroControladores("1");
        MicroControladores microControlador2 = new MicroControladores("2");
        MicroControladores microControlador3 = new MicroControladores("3");
        MicroControladores microControlador4 = new MicroControladores("4");


        int opcao;

        do {

            RegistroClimatico temp1 = microControlador1.coletarDados(servidor.valorAutoincremento());
            servidor.inserir(temp1);
            RegistroClimatico temp2 = microControlador2.coletarDados(servidor.valorAutoincremento());
            servidor.inserir(temp2);
            RegistroClimatico temp3 = microControlador3.coletarDados(servidor.valorAutoincremento());
            servidor.inserir(temp3);
            RegistroClimatico temp4 = microControlador4.coletarDados(servidor.valorAutoincremento());
            servidor.inserir(temp4);

            exibirMenu();
            
            System.out.println("Selecione uma opção:");
            opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao) {
                case 1:
                    cadastrarOS();
                    break;
                case 2:
                    listarOS();
                    break;
                case 3:
                    alterarOS();
                    break;
                case 4:
                    removerOS();
                    break;
                case 5:
                    buscarOS();
                    break;
                case 6:
                    quantidadeOS();
                    break;
                case 0:
                    System.out.println("Encerrando...");
                    break;
            
                default:
                    System.out.println("Opção Inválida");
                    break;
            }

        }while (opcao != 0);

       

    }

    private static void exibirMenu() {
        System.out.println("\n==============================");
        System.out.println("       - MENU PRINCIPAL       ");
        System.out.println("==============================");
        System.out.println("1  - Cadastrar OS");
        System.out.println("2  - Listar todas as OS");
        System.out.println("3  - Alterar OS");
        System.out.println("4  - Remover OS");
        System.out.println("5  - Buscar OS");
        System.out.println("6  - Acessar a quantidade de registros");
        System.out.println("0  - Sair");
        System.out.println("==============================");
    }

    private static void cadastrarOS() {
        System.out.println("\n- Cadastrar Nova Ordem de Serviço:");
        System.out.print("* Código: ");
        int codigo = sc.nextInt();
        sc.nextLine();

        System.out.print("* Nome: ");
        String nome = sc.nextLine();

        System.out.print("* Descrição: ");
        String descricao = sc.nextLine();

        // RegistroClimatico reg = new RegistroClimatico();
        // servidor.inserir(reg);
    }

    private static void listarOS() {
        System.out.println("\n Listagem de Todas as Ordens de Serviço:");
        servidor.listar();
    }

    private static void alterarOS() {
        System.out.print("\n Informe o código da OS que deseja alterar: ");
        int codigo = sc.nextInt();
        sc.nextLine();

        // No<OrdemDeServico> ordemAlterar = servidor.alterarBanco(codigo);

        // if (ordemAlterar != null) {
            System.out.println(" OS encontrada. Informe os novos dados:");
            System.out.print("* Novo Nome: ");
            String nome = sc.nextLine();

            System.out.print("* Nova Descrição: ");
            String descricao = sc.nextLine();

            LocalDateTime dataHoraSolicitacao = null;
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

            while (dataHoraSolicitacao == null) {
                try {
                    System.out.print("* Nova Hora da Solicitação (formato: dd/MM/yyyy HH:mm:ss): ");
                    String dataHora = sc.nextLine();
                    dataHoraSolicitacao = LocalDateTime.parse(dataHora, formatter);
                } catch (DateTimeParseException e) {
                    System.out.println("Formato inválido. Tente novamente.");
                }
            }

            System.out.println("v Ordem de Serviço alterada com sucesso!");

            // OrdemDeServico ordemTemp = new OrdemDeServico(codigo,nome,descricao,dataHoraSolicitacao);

            // ordemAlterar.setDado(ordemTemp);
            
            // if(servidor.existeCache(codigo)){
            //    servidor.alterarCache(ordemTemp);
            // }
        //} else {
        //    System.out.println("X Ordem de Serviço não encontrada.");
        //}
    }

    private static void removerOS() {
        System.out.print("\n Informe o código da OS que deseja remover: ");
        int codigo = sc.nextInt();
        sc.nextLine(); // Limpar o buffer

        if (servidor.remover(codigo)) {
            System.out.println("V Ordem de Serviço removida com sucesso!");
        } else {
            System.out.println("X Ordem de Serviço não encontrada.");
        }
    }

    private static void buscarOS(){
        System.out.print("\n Informe o código da OS que deseja buscar: ");
        int codigo = sc.nextInt();
        sc.nextLine(); // Limpar o buffer

        // OrdemDeServico os = servidor.buscar(codigo);

        // if(os != null){
        //    System.out.println("OS encontrada:");
        //    System.out.println(os);
        // } else {
        //    System.out.println("OS não encontrada");
        // }
    }

    public static void quantidadeOS(){
        System.out.println("Quantidade de Registros: " + servidor.quantidadeRegistros());
    }
}

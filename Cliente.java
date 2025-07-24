import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Scanner;

import enteties.*;
import utilities.ArvoreHuffman;
import utilities.MicroControladores;
import utilities.TabelaHashDispositivos;

public class Cliente {

    private static Scanner sc = new Scanner(System.in);
    private static Servidor servidor = new Servidor();

    private static MicroControladores microControlador1 = new MicroControladores("1");
    private static MicroControladores microControlador2 = new MicroControladores("2");
    private static MicroControladores microControlador3 = new MicroControladores("3");
    private static MicroControladores microControlador4 = new MicroControladores("4");
    private static MicroControladores microControlador5 = new MicroControladores("5");


    public static void main(String[] args) throws Exception {
        

        servidor.inicializarServidor();


        int opcao;

        do {


            exibirMenu();
            
            System.out.println("Selecione uma opção:");
            opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao) {
                case 1:
                    cadastrarRegistro();
                    break;
                case 2:
                    listarRegistros();
                    break;
                case 3:
                    alterarRegistro();
                    break;
                case 4:
                    removerRegistro();
                    break;
                case 5:
                    buscarRegistro();
                    break;
                case 6:
                    quantidadeRegistros();
                    break;
                case 7:
                    listarRegistrosLista();
                    break;
                case 8:
                    listarRegistrosTabela();
                    break;
                case 9:
                    cadastroMicro();
                    break;
                case 10:
                    cadastroMicro10();
                    break;
                case 11:
                    alteraMicro();
                    break;
                case 12:
                    remover50();
                    break;
                case 0:
                    servidor.finalizarServidor();
                    System.out.println("Encerrando...");
                    break;
            
                default:
                    System.out.println("Opção Inválida");
                    break;
            }



        }while (opcao != 0);

       

    }

    private static void alteraMicro() {
        servidor.alterar10();
    }

    private static void cadastroMicro() {
        servidor.inserir10erro();
    }

    private static void cadastroMicro10() {
        servidor.inserir10();
    }

    private static void exibirMenu() {
        System.out.println("\n==============================");
        System.out.println("       - MENU PRINCIPAL       ");
        System.out.println("==============================");
        System.out.println("1  - Cadastrar Registro");
        System.out.println("2  - Listar todos os Registros");
        System.out.println("3  - Alterar Registro");
        System.out.println("4  - Remover Registro");
        System.out.println("5  - Buscar Registro");
        System.out.println("6  - Acessar a quantidade de registros");
        System.out.println("7  - Listar todos os Registros na lista");
        System.out.println("8  - Listar todos os Registros na tabela");
        System.out.println("0  - Sair");
        System.out.println("\n==============================");
        System.out.println("       - FUNÇÕES MICROCONTROLADORES       ");
        System.out.println("==============================");
        System.out.println("9  - Cadastra 10 Registros 2 com erro");
        System.out.println("10  - Cadastra 10 Registros");
        System.out.println("11  - Alterar 10 Registros");
        System.out.println("12  - Remover 50 Registros");
        System.out.println("==============================");
    }

    private static void cadastrarRegistro() {
        System.out.println("\n Cadastrar Novo Registro Climático:");

        System.out.print("* ID do Dispositivo: ");
        String idDispositivo = sc.nextLine();
        sc.nextLine();

        LocalDate dataHora = null;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        while (dataHora == null) {
            try {
                System.out.print("* Data da Medição (formato: dd/MM/yyyy): ");
                String data = sc.nextLine();
                dataHora = LocalDate.parse(data, formatter);
            } catch (DateTimeParseException e) {
                System.out.println("Formato de data inválido. Tente novamente.");
            }
        }

        System.out.print("* Temperatura (ºC): ");
        double temperatura = sc.nextDouble();

        System.out.print("* Umidade (%): ");
        double umidade = sc.nextDouble();

        System.out.print("* Pressão (hPa): ");
        double pressao = sc.nextDouble();
        sc.nextLine(); // consumir quebra de linha final

        RegistroClimatico reg = new RegistroClimatico(
                servidor.valorAutoincremento(),
                pressao,
                umidade,
                temperatura,
                dataHora,
                idDispositivo
        );

        String mensagem = "cadastrar<#DIV#>" + reg.registroMensagem();

        NoHuffman raiz = ArvoreHuffman.construirArvoreHuffman(mensagem);
        Map<Character, String> codigos = new HashMap<>();
        ArvoreHuffman.gerarCodigos(raiz, "", codigos);

        String mensagemCodificada = ArvoreHuffman.codificar(mensagem,codigos);

        // Envio da mensagem
        Mensagem resposta = servidor.mensagemServidor(mensagemCodificada, raiz);

        // Recebimento e processamento
        System.out.println(resposta.getMensagem());
        String mensagemDecodificada = ArvoreHuffman.decodificar(resposta.getMensagem(),resposta.getRaiz());
        System.out.println(mensagemDecodificada);

        // servidor.inserir(reg);
    }

    private static void listarRegistros() {
        String mensagem = "listartodos";

        NoHuffman raiz = ArvoreHuffman.construirArvoreHuffman(mensagem);
        Map<Character, String> codigos = new HashMap<>();
        ArvoreHuffman.gerarCodigos(raiz, "", codigos);

        String mensagemCodificada = ArvoreHuffman.codificar(mensagem,codigos);

        Mensagem resposta = servidor.mensagemServidor(mensagemCodificada, raiz);

        // Mostrar na apresentação
        // System.out.println(resposta.getMensagem());
        String mensagemDecodificada = ArvoreHuffman.decodificar(resposta.getMensagem(),resposta.getRaiz());
        System.out.println(mensagemDecodificada);
        // servidor.listar();
    }

    private static void listarRegistrosLista() {

        String mensagem = "listarlista";

        NoHuffman raiz = ArvoreHuffman.construirArvoreHuffman(mensagem);
        Map<Character, String> codigos = new HashMap<>();
        ArvoreHuffman.gerarCodigos(raiz, "", codigos);

        String mensagemCodificada = ArvoreHuffman.codificar(mensagem,codigos);

        Mensagem resposta = servidor.mensagemServidor(mensagemCodificada, raiz);

        String mensagemDecodificada = ArvoreHuffman.decodificar(resposta.getMensagem(),resposta.getRaiz());
        System.out.println(mensagemDecodificada);

        // System.out.println(servidor.mensagemServidor("listarlista"));
        // servidor.listarLista();
    }

    private static void listarRegistrosTabela() {
        String mensagem = "listartabela";

        NoHuffman raiz = ArvoreHuffman.construirArvoreHuffman(mensagem);
        Map<Character, String> codigos = new HashMap<>();
        ArvoreHuffman.gerarCodigos(raiz, "", codigos);

        String mensagemCodificada = ArvoreHuffman.codificar(mensagem,codigos);

        Mensagem resposta = servidor.mensagemServidor(mensagemCodificada, raiz);

        String mensagemDecodificada = ArvoreHuffman.decodificar(resposta.getMensagem(),resposta.getRaiz());
        System.out.println(mensagemDecodificada);

        //System.out.println(servidor.mensagemServidor("listartabela"));

        // servidor.listarTabela();
    }

    private static void alterarRegistro() {
        RegistroClimatico registroClimatico = new RegistroClimatico();

        System.out.print("\n Informe o Id do Registro Climatico que deseja alterar: ");
        int codigo = sc.nextInt();
        registroClimatico.setIdRegistro(codigo);

        sc.nextLine();

        System.out.println(" Registro encontrado. Informe os novos dados:");
        System.out.print("* Novo ID do Dispositivo: ");
        String idDispositivo = sc.nextLine();

        registroClimatico.setIdDispositivo(idDispositivo);

        LocalDate dataHora = null;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        while (dataHora == null) {
            try {
                System.out.print("* Nova Data da Medição (formato: dd/MM/yyyy): ");
                String data = sc.nextLine();
                dataHora = LocalDate.parse(data, formatter);
                sc.nextLine();
            } catch (DateTimeParseException e) {
                System.out.println("Formato de data inválido. Tente novamente.");
            }
        }

        registroClimatico.setDataHora(dataHora);

        System.out.print("* Nova Temperatura (ºC): ");
        double temperatura = sc.nextDouble();
        registroClimatico.setTemperatura(temperatura);

        System.out.print("* Nova Umidade (%): ");
        double umidade = sc.nextDouble();
        registroClimatico.setUmidade(umidade);

        System.out.print("* Nova Pressão (hPa): ");
        double pressao = sc.nextDouble();
        registroClimatico.setPressao(pressao);

        String mensagem = "alterar<#DIV#>" + registroClimatico.registroMensagem();

        NoHuffman raiz = ArvoreHuffman.construirArvoreHuffman(mensagem);
        Map<Character, String> codigos = new HashMap<>();
        ArvoreHuffman.gerarCodigos(raiz, "", codigos);

        String mensagemCodificada = ArvoreHuffman.codificar(mensagem,codigos);

        Mensagem resposta = servidor.mensagemServidor(mensagemCodificada, raiz);

        String mensagemDecodificada = ArvoreHuffman.decodificar(resposta.getMensagem(),resposta.getRaiz());
        System.out.println(mensagemDecodificada);

        // String resposta = servidor.mensagemServidor(mensagem);

        // System.out.println(resposta);


    }

    private static void removerRegistro() {
        System.out.print("\n Informe o Id do Registro que deseja remover: ");
        int id = sc.nextInt();
        sc.nextLine(); // Limpar o buffer

        String mensagem = "remover<#DIV#>" + id;

        NoHuffman raiz = ArvoreHuffman.construirArvoreHuffman(mensagem);
        Map<Character, String> codigos = new HashMap<>();
        ArvoreHuffman.gerarCodigos(raiz, "", codigos);

        String mensagemCodificada = ArvoreHuffman.codificar(mensagem,codigos);

        Mensagem resposta = servidor.mensagemServidor(mensagemCodificada, raiz);

        String mensagemDecodificada = ArvoreHuffman.decodificar(resposta.getMensagem(),resposta.getRaiz());
        System.out.println(mensagemDecodificada);


        // System.out.println(servidor.mensagemServidor(mensagem));
    }

    private static void remover50() {
        servidor.remover50();
    }

    private static void buscarRegistro(){
        System.out.print("\n Informe o Id do Registro que deseja buscar: ");
        int id = sc.nextInt();
        sc.nextLine(); // Limpar o buffer

        String mensagem = "buscar<#DIV#>" + id;

        NoHuffman raiz = ArvoreHuffman.construirArvoreHuffman(mensagem);
        Map<Character, String> codigos = new HashMap<>();
        ArvoreHuffman.gerarCodigos(raiz, "", codigos);

        String mensagemCodificada = ArvoreHuffman.codificar(mensagem,codigos);

        Mensagem resposta = servidor.mensagemServidor(mensagemCodificada, raiz);

        String mensagemDecodificada = ArvoreHuffman.decodificar(resposta.getMensagem(),resposta.getRaiz());
        System.out.println(mensagemDecodificada);


        // String resposta = servidor.mensagemServidor(mensagem);
        String[] partes = mensagemDecodificada.split("<#DIV#>");

        int idRegistro = Integer.parseInt(partes[0]);
        double pressao = Double.parseDouble(partes[1]);
        double umidade = Double.parseDouble(partes[2]);
        double temperatura = Double.parseDouble(partes[3]);
        LocalDate dataHora = LocalDate.parse(partes[4]);
        String idDispositivo = partes[5];

        RegistroClimatico registro = new RegistroClimatico(
                idRegistro,
                pressao,
                umidade,
                temperatura,
                dataHora,
                idDispositivo
        );

        if(registro != null){
            System.out.println("Registro encontrado:");
            System.out.println(registro);
        } else {
            System.out.println("Registro não encontrado");
        }


    }

    public static void quantidadeRegistros(){
        // System.out.println(servidor.mensagemServidor("quantidade"));
        String mensagem = "quantidade";

        NoHuffman raiz = ArvoreHuffman.construirArvoreHuffman(mensagem);
        Map<Character, String> codigos = new HashMap<>();
        ArvoreHuffman.gerarCodigos(raiz, "", codigos);

        String mensagemCodificada = ArvoreHuffman.codificar(mensagem,codigos);

        Mensagem resposta = servidor.mensagemServidor(mensagemCodificada, raiz);

        String mensagemDecodificada = ArvoreHuffman.decodificar(resposta.getMensagem(),resposta.getRaiz());
        System.out.println(mensagemDecodificada);
    }
}

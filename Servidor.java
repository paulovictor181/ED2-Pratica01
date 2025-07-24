import enteties.RegistroAuxiliar;
import enteties.RegistroClimatico;
import utilities.*;
import enteties.No;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Random;

public class Servidor {

    // public static ArvoreAVL<RegistroClimatico> bancoDeDados = new ArvoreAVL<RegistroClimatico>();
    public static TabelaHash bancoDeDados = new TabelaHash(3);

    private static TabelaHashDispositivos controladores = new TabelaHashDispositivos(101);

    public static ListaLigadaRegistros lista = new ListaLigadaRegistros();
    private static int autoIncremento = 0;


    public void inicializarServidor(){

        System.out.println("Iniciando o sistema...");

        for (int i = 1; i <= 100; i++) {
            MicroControladores microControlador = new MicroControladores(Integer.toString(i));
            controladores.inserir(i, microControlador);

            for (int j = 0; j < 100; j++) {
                RegistroClimatico temp = microControlador.coletarDados(valorAutoincremento());
                inserir(temp);
            }

        }


        listar();
    }

    public String mensagemServidor(String mensagem) {

        String[] partes = mensagem.split("<#DIV#>");

        switch (partes[0]) {
            case "cadastrar":

                System.out.println(Arrays.toString(partes));

                int id = Integer.parseInt(partes[1]);
                double pressao = Double.parseDouble(partes[2]);
                double umidade = Double.parseDouble(partes[3]);
                double temperatura = Double.parseDouble(partes[4]);
                LocalDate dataHora = LocalDate.parse(partes[5]);
                String idDispositivo = partes[6];

                RegistroClimatico reg = new RegistroClimatico(
                    id,
                    pressao,
                    umidade,
                    temperatura,
                    dataHora,
                    idDispositivo
                );

                inserir(reg);
                return "Registro cadastrado com sucesso!";

            case "listartodos":
                return "Listando Lista de Registros... \n" + lista.listarMensagem() + "Listando Banco de Dados Tabela Hash...\n" + bancoDeDados.imprimirMensagem();
            case "alterar":
                int idAlterar = Integer.parseInt(partes[1]);
                RegistroClimatico registroAlterar = buscar(idAlterar);

                if (registroAlterar != null) {
                    registroAlterar.setPressao(Double.parseDouble(partes[2]));
                    registroAlterar.setUmidade(Double.parseDouble(partes[3]));
                    registroAlterar.setTemperatura(Double.parseDouble(partes[4]));
                    registroAlterar.setDataHora(LocalDate.parse(partes[5]));
                    registroAlterar.setIdDispositivo(partes[6]);
                    return "Registro alterado com sucesso! \n" + registroAlterar;
                } else {
                    return "Registro não encontrado!";
                }

            case "remover":
                int idRemocao = Integer.parseInt(partes[1]);
                boolean resultado = remover(idRemocao);
                if (resultado) {
                    return "Registro removido com sucesso!";
                } else {
                    return "Falha ao remover registro!";
                }
            case "buscar":
                int idBusca = Integer.parseInt(partes[1]);
                RegistroClimatico registro = buscar(idBusca);
                return registro.registroMensagem();
            case "quantidade":
                Integer quantidade = bancoDeDados.getQuantidadeNo();
                return "Quantidade de Registros: " + (quantidade != null ? quantidade : 0);
            case "listarlista":
                return "Listando Lista de Registros... \n" + lista.listarMensagem();
            case "listartabela":
                return "Listando Banco de Dados Tabela Hash...\n" + bancoDeDados.imprimirMensagem();
        }

        return "Falha na operação";
    }

    public void inserir(RegistroClimatico registro) {
        try {
            int idDispositivo = Integer.parseInt(registro.getIdDispositivo());

            if (controladores.buscar(idDispositivo) != null) {
                bancoDeDados.inserir(registro.getIdRegistro(), lista.inserir(registro));
                System.out.println("✔ Registro climático cadastrado com sucesso!");
            } else {
                System.out.println("Registro invalido!");
            }

        } catch (NumberFormatException e) {
            System.err.println("Erro ao inserir: O ID do dispositivo '" + registro.getIdDispositivo() + "' não é um número válido.");
        }
    }

    public void inserir10() {
        try {
            Random random = new Random();

            for (int i = 0; i < 10; i++) {

                int numeroAleatorio = random.nextInt(100) + 1;
                MicroControladores controlador = controladores.buscar(numeroAleatorio);

                RegistroClimatico temp;

                if (controlador != null) {
                    temp = controlador.coletarDados(valorAutoincremento());

                    int idDispositivo = Integer.parseInt(temp.getIdDispositivo());

                    if (controladores.buscar(idDispositivo) != null) {
                        bancoDeDados.inserir(temp.getIdRegistro(), lista.inserir(temp));
                        System.out.println("✔ Registro climático cadastrado com sucesso!");
                    } else {
                        System.out.println("Registro invalido!");
                    }
                }
            }

        } catch (NumberFormatException e) {
            System.err.println("Erro ao inserir: O ID do dispositivo não é um número válido.");
        }
    }

    public void inserir10erro() {
        try {
            Random random = new Random();

            for (int i = 0; i < 8; i++) {

                int numeroAleatorio = random.nextInt(100) + 1;
                MicroControladores controlador = controladores.buscar(numeroAleatorio);

                RegistroClimatico temp;

                if (controlador != null) {
                    temp = controlador.coletarDados(valorAutoincremento());

                    int idDispositivo = Integer.parseInt(temp.getIdDispositivo());

                    if (controladores.buscar(idDispositivo) != null) {
                        bancoDeDados.inserir(temp.getIdRegistro(), lista.inserir(temp));
                        System.out.println("✔ Registro climático cadastrado com sucesso!");
                    } else {
                        System.out.println("Registro invalido!");
                    }
                }
            }

            MicroControladores controladorTemp1 = new MicroControladores("-1");
            MicroControladores controladorTemp2 = new MicroControladores("-2");

            RegistroClimatico regTemp1 = controladorTemp1.coletarDados(valorAutoincremento());
            RegistroClimatico regTemp2 = controladorTemp2.coletarDados(valorAutoincremento());

            inserir(regTemp1);
            inserir(regTemp2);

        } catch (NumberFormatException e) {
            System.err.println("Erro ao inserir: O ID do dispositivo não é um número válido.");
        }
    }

    public void listar() {
        System.out.println("Listando Lista de Registros...");
        lista.listar();
        System.out.println("Listando Banco de Dados Tabela Hash...");
        bancoDeDados.imprimir();
    }

    public void listarLista() {
        System.out.println("Listando Lista de Registros...");
        lista.listar();
    }

    public void listarTabela() {
        System.out.println("Listando Banco de Dados Tabela Hash...");
        bancoDeDados.imprimir();
    }


    public RegistroClimatico alterarBanco(int Id) {
        return bancoDeDados.buscar(Id);
    }

    public void alterar10(){
        try {
            Random random = new Random();

            for (int i = 0; i < 10; i++) {

                int numeroAleatorio = random.nextInt(100) + 1;
                MicroControladores controlador = controladores.buscar(numeroAleatorio);

                RegistroClimatico temp;

                if (controlador != null) {

                    int RegistroDisp1 =  controlador.removerDados();
                    boolean registro1Encontrado = false;
                    while(!registro1Encontrado){
                        RegistroClimatico registroAlterar = alterarBanco(RegistroDisp1);
                        if(registroAlterar != null){
                            RegistroClimatico registroClimatico = registroAlterar;
                            System.out.println("Registro Alterado do microcontrolador!");
                            System.out.println("Registro Antes: " + registroClimatico.toString());
                            controlador.atualizarDadosClimaticos(registroClimatico);
                            System.out.println("Registro Atualizado:" + registroClimatico.toString());
                            registro1Encontrado = true;
                        } else {
                            RegistroDisp1 =  controlador.removerDados();
                        }
                    }

                }
            }

        } catch (NumberFormatException e) {
            System.err.println("Erro ao inserir: O ID do dispositivo não é um número válido.");
        }
    }

    public boolean remover(int Id) {

        boolean removacao = lista.remover(bancoDeDados.remover(Id));

        return removacao;

    }

    public void remover50(){
        try {
            Random random = new Random();

            for (int i = 0; i < 10; i++) {

                int numeroAleatorio = random.nextInt(100) + 1;
                MicroControladores controlador = controladores.buscar(numeroAleatorio);
                System.out.println("Microcontrolador " + numeroAleatorio + " selecionado para remoção!");
                RegistroClimatico temp;

                if (controlador != null) {

                    int RegistroDisp1 =  controlador.removerDados();
                    boolean registro1Encontrado = false;
                    while(!registro1Encontrado){
                        RegistroClimatico registroRemover = alterarBanco(RegistroDisp1);
                        if(registroRemover != null){
                            System.out.println("Registro Removido!: " + registroRemover.toString());
                            remover(registroRemover.getIdRegistro());
                            registro1Encontrado = true;
                        } else {
                            RegistroDisp1 =  controlador.removerDados();
                        }
                    }

                }
            }

        } catch (NumberFormatException e) {
            System.err.println("Erro ao inserir: O ID do dispositivo não é um número válido.");
        }
    }

    public RegistroClimatico buscar(int codigo){
        RegistroClimatico registro = bancoDeDados.buscar(codigo);

        if(registro != null){
            System.out.println("Registro encontrado");
            return registro;
        } else {
            return null;
        }


    }

    public int quantidadeRegistros() {
        return bancoDeDados.getQuantidadeNo();
    }

    public int valorAutoincremento() {
        return ++autoIncremento;
    }
}

import enteties.RegistroAuxiliar;
import enteties.RegistroClimatico;
import utilities.*;
import enteties.No;

import java.util.LinkedList;
import java.util.Random;

public class Servidor {

    // public static ArvoreAVL<RegistroClimatico> bancoDeDados = new ArvoreAVL<RegistroClimatico>();
    public static TabelaHash bancoDeDados = new TabelaHash(10007);

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

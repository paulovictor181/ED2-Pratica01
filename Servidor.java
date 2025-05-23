import enteties.RegistroClimatico;
import utilities.ArvoreAVL;
import utilities.LeitorArquivoRegistros;
import utilities.ListaLigadaRegistros;
import enteties.No;

import java.util.LinkedList;

public class Servidor {

    public static ArvoreAVL<RegistroClimatico> bancoDeDados = new ArvoreAVL<RegistroClimatico>();
    public static ListaLigadaRegistros lista = new ListaLigadaRegistros();
    private static int autoIncremento = 0;


    public void inicializarServidor(){

        System.out.println("Iniciando o sistema...");

        LeitorArquivoRegistros leitor = new LeitorArquivoRegistros();

        LinkedList<RegistroClimatico> listaRegistrosIniciais = new LinkedList<RegistroClimatico>(leitor.lerArquivoOS("registrosClimaticos.txt"));

        for (RegistroClimatico registro : listaRegistrosIniciais) {
            lista.inserir(registro);
            bancoDeDados.inserir(registro.getIdRegistro(), registro);
            autoIncremento++;
        }

        listar();

    }

    public void inserir(RegistroClimatico registro) {
        bancoDeDados.inserir(registro.getIdRegistro(),lista.inserir(registro));
    }

    public void listar() {
        //cacheEviction.mostrarCache();
        bancoDeDados.ordem();
    }

    public boolean existeCache(int codigo) {
        // if(cacheEviction.existe(codigo)){
        //    return true;
        // } else {
        //    return false;
        //}
        return false;
    }

    public No<RegistroClimatico> alterarBanco(int codigo) {
        return bancoDeDados.buscar(codigo);
    }

    public void alterarCache(RegistroClimatico os) {

    }

    public boolean remover(int codigo) {
        
        // if(!cacheEviction.existe(codigo) && bancoDeDados.buscar(codigo) == null) {
        //     return false;
        // }

        // cacheEviction.remove(codigo);
        // bancoDeDados.remover(codigo, null);

        // cacheEviction.mostrarCache();

        return true;

    }

    public RegistroClimatico buscar(int codigo){
        RegistroClimatico os = lista.buscar(codigo);

        if(os != null){
            // cacheEviction.mostrarCache();
            return os;
        } else {
            os = bancoDeDados.buscarDado(codigo);

            if(os != null){
            //    cacheEviction.inserir(os);
            //     cacheEviction.mostrarCache();
                return os;
            } else {
            //    cacheEviction.mostrarCache();
                return null;
            }
        }

    }

    public int quantidadeRegistros() {
        return bancoDeDados.getQuantidadeNo();
    }

    public int valorAutoincremento() {
        return ++autoIncremento;
    }
}

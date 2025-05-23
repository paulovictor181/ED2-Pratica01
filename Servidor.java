import enteties.RegistroClimatico;
import utilities.ArvoreAVL;
import utilities.LeitorArquivoRegistros;
import utilities.ListaLigadaRegistros;
import enteties.No;

import java.util.LinkedList;

public class Servidor {

    public static ArvoreAVL<RegistroClimatico> bancoDeDados = new ArvoreAVL<RegistroClimatico>();
    public static ListaLigadaRegistros lista = new ListaLigadaRegistros();


    public void inicializarServidor(){

        System.out.println("Iniciando o sistema...");

        LeitorArquivoRegistros leitor = new LeitorArquivoRegistros();

        LinkedList<RegistroClimatico> listaRegistrosIniciais = new LinkedList<RegistroClimatico>(leitor.lerArquivoOS("registrosClimaticos.txt"));

        for (RegistroClimatico registro : listaRegistrosIniciais) {
            lista.inserir(registro);
            bancoDeDados.inserir(registro.getIdRegistro(), registro);
        }

        listar();

    }

    public void inserir(RegistroClimatico os) {
        //if(!cacheEviction.existe(os.getCodigo())  || !bancoDeDados.existe(os.getCodigo())){
        //     cacheEviction.inserir(os);
        //    bancoDeDados.inserir(os.getCodigo(), os);
        //} else {
        //    System.out.println("Não foi possível cadastrar");
        //}
        // cacheEviction.mostrarCache();

    }

    public void listar() {
        //cacheEviction.mostrarCache();
        //bancoDeDados.ordem();
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


}

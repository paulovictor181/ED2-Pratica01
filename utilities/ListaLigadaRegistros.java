package utilities;

import enteties.RegistroClimatico;

public class ListaLigadaRegistros {
    private RegistroClimatico primeiro;
    private RegistroClimatico ultimo;
    private long tamanho;

    public ListaLigadaRegistros() {
        this.primeiro = null;
        this.ultimo = null;
        this.tamanho = 0;
    }

    public void listar(){
        RegistroClimatico registro = primeiro;

        if(registro == null){
            System.out.println("Banco Vazio!!!");
        } else {
            while(registro != null){
                System.out.println("Lista Ligada: " + registro);
                registro = registro.getProximo();
            }
        }

    }

    public String listarMensagem() {
        RegistroClimatico registro = primeiro;

        if (registro == null) {
            return "Banco Vazio!!!";
        }

        StringBuilder resultado = new StringBuilder();

        while (registro != null) {
            resultado.append("Lista Ligada: ").append(registro).append("\n");
            registro = registro.getProximo();
        }

        return resultado.toString();
    }

    public RegistroClimatico inserir(RegistroClimatico registro){
        RegistroClimatico registroInserido = registro;
        if(primeiro == null){
            primeiro = registroInserido;
            ultimo = registroInserido;
        } else {
            ultimo.setProximo(registroInserido);
            registroInserido.setAnterior(ultimo);
            ultimo = registroInserido;
        }
        tamanho++;

        return  registroInserido;
    }

    public RegistroClimatico buscar(int IdRegistro){
        RegistroClimatico registro = primeiro;

        while(registro != null){
            if (registro.getIdRegistro() == IdRegistro){
                return registro;
            }
            registro = registro.getProximo();
        }
        return null;
    }

    /*
    public RegistroClimatico buscarAnterior(int IdRegistro){
        RegistroClimatico registro = primeiro;
        RegistroClimatico registroAnterior = null;

        while(registro != null){
            registroAnterior = registro;
            registro = registro.getProximo();


            if (registro.getIdRegistro() == IdRegistro){
                return registroAnterior;
            }
        }

        return null;
    }
     */
    public RegistroClimatico buscarAnterior(int IdRegistro) {
        RegistroClimatico atual = primeiro;

        // Se a lista estiver vazia ou o ID for o primeiro, não há anterior.
        if (primeiro == null || primeiro.getIdRegistro() == IdRegistro) {
            return null;
        }

        // Percorre a lista olhando para o próximo nó
        while (atual.getProximo() != null) {
            if (atual.getProximo().getIdRegistro() == IdRegistro) {
                return atual; // Encontrou o nó anterior ao que será removido
            }
            atual = atual.getProximo();
        }

        return null; // Não encontrou o registro
    }

    public RegistroClimatico removerPrimeiro(){
        RegistroClimatico registro = primeiro;
        RegistroClimatico registroRemovido = null;

        if(primeiro == null){
            System.out.println("Banco Vazio!!!");
        } else {

            registroRemovido = primeiro;

            if(primeiro == ultimo){
                primeiro = null;
                ultimo = null;
            } else {
                primeiro = primeiro.getProximo();
            }
            registro.setProximo(null);
            tamanho--;
        }

        return registroRemovido;
    }

    public RegistroClimatico removerUltimo(){
        RegistroClimatico registroRemovido = null;

        if(ultimo == null){
            System.out.println("Banco Vazio!!!");
        } else {

            registroRemovido = ultimo;

            if(primeiro == ultimo){
                primeiro = null;
                ultimo = null;
            } else {
                ultimo = ultimo.getAnterior(); // Acesso direto ao penúltimo
                ultimo.setProximo(null); // O penúltimo agora é o último
            }
            tamanho--;
        }

        return registroRemovido;
    }

    public RegistroClimatico remover(int IdRegistro){
        RegistroClimatico anterior = null;
        RegistroClimatico removido = null;

        if(primeiro == null){
            return null;
        }

        RegistroClimatico registroAnterior = buscarAnterior(IdRegistro);

        if(registroAnterior == null){
            if(primeiro.getIdRegistro() == IdRegistro){
                return removerPrimeiro();
            } else {
                return null;
            }
        } else {
            removido = registroAnterior.getProximo();

            if(removido == ultimo){
                return removerUltimo();
            } else {
                registroAnterior.setProximo(removido.getProximo());
                removido.setProximo(null);
                tamanho--;
                return removido;
            }
        }
    }
/*
    public boolean remover(RegistroClimatico registro) {
        if (registro != null){
            if (registro == primeiro) {
                removerPrimeiro();
                return true;
            }

            if (registro == ultimo) {
                removerUltimo();
                return true;
            }

            RegistroClimatico proximo = registro.getProximo();

            registro.setIdRegistro(proximo.getIdRegistro());
            registro.setIdDispositivo(proximo.getIdDispositivo());
            registro.setDataHora(proximo.getDataHora());
            registro.setTemperatura(proximo.getTemperatura());
            registro.setUmidade(proximo.getUmidade());
            registro.setPressao(proximo.getPressao());
            registro.setProximo(proximo.getProximo());

            if (proximo == ultimo) {
                ultimo = registro;
            }

            proximo.setProximo(null);
            tamanho--;
            return true;
        }
        return false;
    }
 */

    public boolean remover(RegistroClimatico registroParaRemover) {
        if (registroParaRemover == null) {
            return false; // Não se pode remover um objeto nulo
        }

        if (registroParaRemover == primeiro) {
            removerPrimeiro();
            return true;
        }

        if (registroParaRemover == ultimo) {
            removerUltimo();
            return true;
        }

        // Lógica para remover um nó do meio
        RegistroClimatico anterior = registroParaRemover.getAnterior();
        RegistroClimatico proximo = registroParaRemover.getProximo();

        // Se o nó não estiver corretamente ligado na lista, 'anterior' pode ser nulo.
        if (anterior == null) {
            return false;
        }

        anterior.setProximo(proximo);
        proximo.setAnterior(anterior);

        registroParaRemover.setProximo(null);
        registroParaRemover.setAnterior(null);

        tamanho--;
        return true; // Sucesso na remoção
    }
}

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
                System.out.println(registro);
                registro = registro.getProximo();
            }
        }

    }

    public RegistroClimatico inserir(RegistroClimatico registro){
        RegistroClimatico registroInserido = registro;
        if(primeiro == null){
            primeiro = registroInserido;
            ultimo = registroInserido;
        } else {
            ultimo.setProximo(registroInserido);
            ultimo = registroInserido;
        }
        tamanho++;

        return  ultimo;
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
                RegistroClimatico registro = primeiro;

                while(registro.getProximo() != ultimo){
                    registro = registro.getProximo();
                }

                ultimo = registro;
                ultimo.setProximo(null);
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
}

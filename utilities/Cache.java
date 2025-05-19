package utilities;

import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Queue;

public class Cache<T> {

    private Queue<T> cache;
    private int tamanho;

    public Cache(int tamanho) {

        if (tamanho <= 0) {
            throw new IllegalArgumentException("A capacidade deve ser maior que zero.");
        }

        this.tamanho = tamanho;
        this.cache = new LinkedList<>();
    }

    public void inserir(T objeto) {
        if (cache.size() == tamanho) {
            cache.remove();
        }
        cache.add(objeto);
    }

    public T buscar(int codigo){
        for (T objeto : cache) {
            if(objeto.hashCode() == codigo){
                return objeto;
            }
        }

        return null;
    }

    public void mostrarCache(){
        for (T t : cache) {
            System.out.println(t);
        }
    }

    public boolean existe(int codigo){
        for (T objeto : cache) {
            if(objeto.hashCode() == codigo){
                return true;
            }
        }

        return false;
    }

    public T remove(int codigo){
        for (T objeto : cache) {
            if(objeto.hashCode() == codigo){
                cache.remove(objeto);
                return objeto;
            }
        }

        return null;

    }

    public void alterar(T novoValor){

        ListIterator<T> iterator = ((LinkedList<T>) cache).listIterator();

        while (iterator.hasNext()) {
            T objeto = iterator.next();
            if (objeto.hashCode() == novoValor.hashCode()) {
                iterator.set(novoValor);
                break;
            }
        }
    }



    @Override
    public String toString() {
        return super.toString();
    }

    
}

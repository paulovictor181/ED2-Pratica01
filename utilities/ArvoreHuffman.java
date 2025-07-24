package utilities;

import enteties.NoHuffman;

import java.util.HashMap;
import java.util.Map;

public class ArvoreHuffman {

    public static Map<Character, Integer> calcularFrequencias(String texto) {
        Map<Character, Integer> freq = new HashMap<>();

        char[] caracteres = texto.toCharArray();

        for (int i = 0; i < caracteres.length; i++) {
            char c = caracteres[i];

            if (freq.containsKey(c)) {
                int valorAtual = freq.get(c);
                freq.put(c, valorAtual + 1);
            } else {
                freq.put(c, 1);
            }
        }
        return freq;
    }


    public static NoHuffman construirArvoreHuffman(String texto) {
        Map<Character, Integer> frequencias = calcularFrequencias(texto);

        MinHeap heap = new MinHeap(frequencias.size());


        for (Map.Entry<Character, Integer> entry : frequencias.entrySet()) {
            heap.inserir(new NoHuffman(entry.getKey(), entry.getValue()));
        }

        heap.construir();

        while (heap.tamanho() > 1) {
            NoHuffman x = heap.removerMin();
            NoHuffman y = heap.removerMin();

            NoHuffman z =
                    new NoHuffman(x.frequencia + y.frequencia, x, y);
            heap.inserir(z);
        }

        return heap.removerMin();
    }

    public static void gerarCodigos(NoHuffman no,
                                    String codigo,
                                    Map<Character, String> tabela) {
        if (no == null)
            return;

        if (no.isFolha()) {
            tabela.put(no.caractere, codigo);
        }

        gerarCodigos(no.esq, codigo + "0", tabela);

        gerarCodigos(no.dir, codigo + "1", tabela);
    }

    public static String codificar(String texto, Map<Character, String> tabela) {
        StringBuilder codificada = new StringBuilder();

        for (char c : texto.toCharArray()) {

            codificada.append(tabela.get(c));

        }
        return codificada.toString();

    }

    public static String decodificar(String codificado, NoHuffman raiz) {
        StringBuilder decodificada = new StringBuilder();

        NoHuffman atual = raiz;

        for (char bit : codificado.toCharArray()) {
            atual = (bit == '0') ? atual.esq : atual.dir;

            if (atual.isFolha()) {

                decodificada.append(atual.caractere);
                atual = raiz;
            }
        }
        return decodificada.toString();

    }

}


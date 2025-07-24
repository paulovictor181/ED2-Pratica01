package utilities;

import enteties.NoHuffman;

public class MinHeap {
    NoHuffman[] heap;
    int tamanho;

    public MinHeap(int capacidade) {
        heap = new NoHuffman[capacidade];
        tamanho = 0;
    }

    public void inserir(NoHuffman no) {
        heap[tamanho] = no;
        subir(tamanho);
        tamanho++;
    }

    private void subir(int i) {
        while (i > 0) {
            int pai = (i - 1) / 2;
            if (heap[i].frequencia < heap[pai].frequencia) {
                trocar(i, pai);
                i = pai;
            } else {
                break;
            }
        }
    }

    public NoHuffman removerMin() {
        if (tamanho == 0)
            return null;
        NoHuffman min = heap[0];
        heap[0] = heap[--tamanho];
        descerV1(0);
        return min;
    }

    private void descerV1(int i) {
        int j = 2 * i + 1; // filho esquerdo
        int temp;

        if (j < tamanho) { // existe filho esquerdo

            if (j < tamanho - 1) {
                if (heap[j + 1].frequencia < heap[j].frequencia) {
                    j++;
                }
            }

            if (heap[j].frequencia < heap[i].frequencia) {
                trocar(i, j);
                descerV1(j); // chamada recursiva
            }
        }
    }

    private void descerV2(int i) {
        while (true) {
            int menor = i;
            int esq = 2 * i + 1;
            int dir = 2 * i + 2;

            if (esq < tamanho && heap[esq].frequencia < heap[menor].frequencia)
                menor = esq;

            if (dir < tamanho && heap[dir].frequencia < heap[menor].frequencia)
                menor = dir;

            if (menor != i) {
                trocar(i, menor);
                i = menor;
            } else {
                break;
            }
        }
    }

    private void trocar(int i, int j) {
        NoHuffman tmp = heap[i];
        heap[i] = heap[j];
        heap[j] = tmp;
    }

    public void construir() {
        for (int i = (tamanho / 2) - 1; i >= 0; i--) {
            descerV1(i);
        }
    }

    public int tamanho() {
        return tamanho;
    }
}

package enteties;

public class NoHuffman {
    public char caractere;
    public int frequencia;
    public NoHuffman esq;
    public NoHuffman dir;

    public NoHuffman(char caractere, int frequencia) {
        this.caractere = caractere;
        this.frequencia = frequencia;
    }

    public NoHuffman(int frequencia, NoHuffman esquerdo, NoHuffman direito) {
        this.caractere = '\0'; // nó interno
        this.frequencia = frequencia;
        this.esq = esquerdo;
        this.dir = direito;
    }

    public boolean isFolha() {
        return esq == null && dir == null;
    }
}


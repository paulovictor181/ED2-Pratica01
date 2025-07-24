package enteties;

public class Mensagem {
    private String mensagem;
    private NoHuffman raiz;

    public Mensagem() {
    }

    public Mensagem(String mensagem, NoHuffman raiz) {
        this.mensagem = mensagem;
        this.raiz = raiz;
    }

    public NoHuffman getRaiz() {
        return raiz;
    }

    public void setRaiz(NoHuffman raiz) {
        this.raiz = raiz;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }
}

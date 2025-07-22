package enteties;

public class NoHash {
    private int chave;
    private NoHash proximo;
    private RegistroClimatico registro;

    public NoHash(int chave, NoHash proximo, RegistroClimatico registro) {
        this.chave = chave;
        this.proximo = proximo;
        this.registro = registro;
    }

    public int getChave() {
        return chave;
    }

    public void setChave(int chave) {
        this.chave = chave;
    }

    public NoHash getProximo() {
        return proximo;
    }

    public void setProximo(NoHash proximo) {
        this.proximo = proximo;
    }

    public RegistroClimatico getMicrocontrolador() {
        return registro;
    }

    public void setRegistro(RegistroClimatico registro) {
        this.registro = registro;
    }
}

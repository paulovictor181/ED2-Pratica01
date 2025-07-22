package enteties;

import utilities.MicroControladores;

public class NoHashDisp {
    private int chave;
    private NoHashDisp proximo;
    private MicroControladores micro;

    public NoHashDisp(int chave, NoHashDisp proximo, MicroControladores micro) {
        this.chave = chave;
        this.proximo = proximo;
        this.micro = micro;
    }

    public int getChave() {
        return chave;
    }

    public void setChave(int chave) {
        this.chave = chave;
    }

    public NoHashDisp getProximo() {
        return proximo;
    }

    public void setProximo(NoHashDisp proximo) {
        this.proximo = proximo;
    }

    public MicroControladores getMicrocontrolador() {
        return micro;
    }

    public void setMicrocontrolador(MicroControladores micro) {
        this.micro = micro;
    }
}

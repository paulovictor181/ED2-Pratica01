package enteties;

public class RegistroAuxiliar {
    int idRegistro;
    String idDispositivo;

    public RegistroAuxiliar(int idRegistro, String idDispositivo) {
        this.idRegistro = idRegistro;
        this.idDispositivo = idDispositivo;
    }

    public int getIdRegistro() {
        return idRegistro;
    }

    public void setIdRegistro(int idRegistro) {
        this.idRegistro = idRegistro;
    }

    public String getIdDispositivo() {
        return idDispositivo;
    }

    public void setIdDispositivo(String idDispositivo) {
        this.idDispositivo = idDispositivo;
    }
}

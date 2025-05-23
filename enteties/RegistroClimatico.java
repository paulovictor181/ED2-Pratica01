package enteties;

import java.time.LocalDate;

public class RegistroClimatico {
    private int idRegistro;
    private String idDispositivo;
    private LocalDate dataHora;
    private double temperatura;
    private double umidade;
    private double pressao;
    RegistroClimatico proximo;

    public RegistroClimatico(int idRegistro, double pressao, double umidade, double temperatura, LocalDate dataHora, String idDispositivo) {
        this.idRegistro = idRegistro;
        this.pressao = pressao;
        this.umidade = umidade;
        this.temperatura = temperatura;
        this.dataHora = dataHora;
        this.idDispositivo = idDispositivo;
        this.proximo = null;
    }

    public RegistroClimatico(int idRegistro, RegistroClimatico proximo, double pressao, double umidade, double temperatura, LocalDate dataHora, String idDispositivo) {
        this.idRegistro = idRegistro;
        this.proximo = proximo;
        this.pressao = pressao;
        this.umidade = umidade;
        this.temperatura = temperatura;
        this.dataHora = dataHora;
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

    public LocalDate getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDate dataHora) {
        this.dataHora = dataHora;
    }

    public double getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(double temperatura) {
        this.temperatura = temperatura;
    }

    public double getUmidade() {
        return umidade;
    }

    public void setUmidade(double umidade) {
        this.umidade = umidade;
    }

    public double getPressao() {
        return pressao;
    }

    public void setPressao(double pressao) {
        this.pressao = pressao;
    }

    public RegistroClimatico getProximo() {
        return proximo;
    }

    public void setProximo(RegistroClimatico proximo) {
        this.proximo = proximo;
    }

    @Override
    public String toString() {
        return "RegistroClimatico { idRegistro=" + idRegistro +
                ", idDispositivo='" + idDispositivo + '\'' +
                ", dataHora=" + dataHora +
                ", temperatura=" + temperatura +
                ", umidade=" + umidade +
                ", pressao=" + pressao + " }\n";
    }
}

package enteties;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class RegistroClimatico {
    private int idRegistro;
    private String idDispositivo;
    private LocalDate dataHora;
    private double temperatura;
    private double umidade;
    private double pressao;
    RegistroClimatico proximo;
    RegistroClimatico anterior;

    public RegistroClimatico(int idRegistro, double pressao, double umidade, double temperatura, LocalDate dataHora, String idDispositivo) {
        this.idRegistro = idRegistro;
        this.pressao = pressao;
        this.umidade = umidade;
        this.temperatura = temperatura;
        this.dataHora = dataHora;
        this.idDispositivo = idDispositivo;
        this.proximo = null;
        this.anterior = null;
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

    public RegistroClimatico getAnterior() {
        return anterior;
    }

    public void setAnterior(RegistroClimatico anterior) {
        this.anterior = anterior;
    }

    @Override
    public String toString() {
        // Formatador para números com vírgula como separador decimal
        DecimalFormat df = new DecimalFormat("#0.0", DecimalFormatSymbols.getInstance(new Locale("pt", "BR")));

        // Formatador para data no padrão brasileiro
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        return "RegistroClimatico { " +
                "idRegistro=" + idRegistro +
                ", idDispositivo='" + idDispositivo + '\'' +
                ", dataHora=" + dataHora.format(formatter) +
                ", temperatura=" + df.format(temperatura) + " ºC" +
                ", umidade=" + df.format(umidade) + " %" +
                ", pressao=" + df.format(pressao) + " hPa }\n";
    }
}

package utilities;

import enteties.RegistroClimatico;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class MicroControladores {

    private String idDispositivo;
    private Random randomGenerator;
    private LinkedList<Integer> listaRegistrosCadastrados = new LinkedList<>();

    public MicroControladores(String idDispositivo) {
        this.idDispositivo = idDispositivo;
        this.randomGenerator = new Random();
    }

    public RegistroClimatico coletarDados(int idRegistro) {
        double temperatura = 10.0 + (randomGenerator.nextDouble() * 30.0);
        double umidade = 30.0 + (randomGenerator.nextDouble() * 60.0);
        double pressao = 950.0 + (randomGenerator.nextDouble() * 100.0);
        LocalDate dataHora = LocalDate.now();

        temperatura = Math.round(temperatura * 10.0) / 10.0;
        umidade = Math.round(umidade * 10.0) / 10.0;
        pressao = Math.round(pressao * 10.0) / 10.0;

        listaRegistrosCadastrados.add(idRegistro);

        return new RegistroClimatico(idRegistro, pressao, umidade, temperatura, dataHora, this.idDispositivo);
    }

    public void atualizarDadosClimaticos(RegistroClimatico registro) {

        double temperatura = 10.0 + (randomGenerator.nextDouble() * 30.0);
        double umidade = 30.0 + (randomGenerator.nextDouble() * 60.0);
        double pressao = 950.0 + (randomGenerator.nextDouble() * 100.0);
        LocalDate dataHora = LocalDate.now();

        temperatura = Math.round(temperatura * 10.0) / 10.0;
        umidade = Math.round(umidade * 10.0) / 10.0;
        pressao = Math.round(pressao * 10.0) / 10.0;

        registro.setTemperatura(temperatura);
        registro.setUmidade(umidade);
        registro.setPressao(pressao);
        registro.setDataHora(dataHora);
    }

    public Integer removerDados() {
        return listaRegistrosCadastrados.poll();
    }

    public void adicionarDados(int idRegistro) {
        listaRegistrosCadastrados.add(idRegistro);
    }

    public String getIdDispositivo() {
        return idDispositivo;
    }
}
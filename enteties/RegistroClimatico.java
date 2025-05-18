package enteties;

import java.time.LocalDate;

public class RegistroClimatico {
    int idRegistro;
    String idDispositivo;
    LocalDate dataHora;
    double temperatura;
    double umidade;
    double pressao;
    RegistroClimatico proximo;
}

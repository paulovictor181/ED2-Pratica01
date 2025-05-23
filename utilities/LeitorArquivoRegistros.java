package utilities;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
// import java.time.LocalDateTime; // Removido se não estiver usando
import java.time.format.DateTimeParseException;
import java.util.LinkedList;
import java.util.List;

import enteties.RegistroClimatico; // Certifique-se que este import está correto

public class LeitorArquivoRegistros {

    public List<RegistroClimatico> lerArquivoOS(String caminho) {
        List<RegistroClimatico> listaRegistros = new LinkedList<>();

        // Try-with-resources para garantir que o BufferedReader seja fechado
        try (BufferedReader leitor = new BufferedReader(new FileReader(caminho))) {
            String linha;
            while ((linha = leitor.readLine()) != null) {
                if (linha.trim().isEmpty()) { // Pula linhas em branco
                    continue;
                }

                String[] campos = linha.split(";"); // Assume que o separador é vírgula (,)

                System.out.println(linha.length());
                if (campos.length == 6) {
                    try {
                        int idRegistro = Integer.parseInt(campos[0].trim());
                        double pressao = Double.parseDouble(campos[1].trim());
                        double umidade = Double.parseDouble(campos[2].trim());
                        double temperatura = Double.parseDouble(campos[3].trim());
                        LocalDate dataHora = LocalDate.parse(campos[4].trim()); // Formato AAAA-MM-DD
                        String idDispositivo = campos[5].trim();

                        RegistroClimatico novoRegistro = new RegistroClimatico(
                                idRegistro, pressao, umidade, temperatura, dataHora, idDispositivo
                        );

                        listaRegistros.add(novoRegistro);

                    } catch (NumberFormatException e) {
                        System.err.println("Erro de formatação numérica na linha: \"" + linha + "\" - " + e.getMessage());
                    } catch (DateTimeParseException e) {
                        System.err.println("Erro de formatação de data na linha: \"" + linha + "\" - " + e.getMessage());
                    }
                } else {
                    System.err.println("Linha ignorada (formato inválido - esperado 6 campos, encontrados " + campos.length + "): \"" + linha + "\"");
                }
            }
        } catch (IOException e) {
            System.err.println("Erro de I/O ao ler o arquivo: " + caminho + " - " + e.getMessage());
        }

        return listaRegistros; // Retorna a lista após tentar ler todas as linhas
    }
}
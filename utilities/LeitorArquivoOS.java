package utilities;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

import model.entities.OrdemDeServico;

public class LeitorArquivoOS {

    public List<OrdemDeServico> lerArquivoOS(String caminho) {

        List<OrdemDeServico> listaOS = new LinkedList<>();

        try (BufferedReader leitor = new BufferedReader(new FileReader(caminho))) {
            String linha;
            while ((linha = leitor.readLine()) != null) {
                String[] campos = linha.split(",");

                OrdemDeServico OS = new OrdemDeServico(Integer.parseInt(campos[0]), campos[1], campos[2], LocalDateTime.parse(campos[3]));
            
                listaOS.add(OS);
            }
        } catch (IOException e) {
            System.out.println("Ocorreu um erro ao ler o arquivo: " + e.getMessage());
        }

        return listaOS;
    }
}



package utilities;

import enteties.NoHash;
import enteties.RegistroClimatico;

public class TabelaHash {
    int tamanho, quantidade;
    final int TAM_BASE = 3;
    NoHash[] tabela;

    private int redimensionamentos = 0;
    private int colisoes = 0;

    private EscritorLog logger;

    public TabelaHash(int tamanho){
        this.tamanho = tamanho;
        tabela = new NoHash[tamanho];
        quantidade = 0;
        this.logger = new EscritorLog("Log.txt");

        if (this.logger != null) {
            this.logger.escreverLog("INFO: Tabela Hash iniciada com tamanho " + tamanho);
        }
    }

    private int hash(int chave) {
        return Math.abs(chave) % tamanho;
    }

    public void inserir(int chave, RegistroClimatico registro) {
        int h = hash(chave);
        NoHash atual = tabela[h];

        while (atual != null) {
            if (atual.getChave() == chave) {
                return;
            }
            atual = atual.getProximo();
        }

        tabela[h] = new NoHash(chave,tabela[h],registro);
        quantidade++;

        if (logger != null) {
            logger.escreverLog("INFO: Chave " + chave + " inserida com sucesso.");
        }

        if (tabela[h].getProximo() != null) {
            colisoes++;
        }

        examinarCarga();
    }

    private void examinarCarga() {
        double fatorCarga = (double) quantidade / tamanho;

        if (fatorCarga >= 2.0) {
            reorganizar(tamanho * 2);
        } else if (fatorCarga < 0.25 && tamanho > TAM_BASE) {
            reorganizar(Math.max(tamanho / 2, TAM_BASE));
        }
    }

    private void reorganizar(int novoTamanho) {
        if (logger != null) {
            logger.escreverLog("WARN: Redimensionamento da tabela. Tamanho antigo: " + tamanho + ", Novo tamanho: " + novoTamanho);
        }
        redimensionamentos++;
        NoHash[] antiga = tabela;

        tamanho = novoTamanho;
        tabela = new NoHash[tamanho];
        quantidade = 0;

        for (NoHash noHash : antiga) {
            while (noHash != null) {
                reinserir(noHash.getChave(), noHash.getMicrocontrolador());
                noHash = noHash.getProximo();
            }
        }
    }

    private void reinserir(int chave,RegistroClimatico registro) {
        int h = hash(chave);
        NoHash atual = tabela[h];

        while (atual != null) {
            if (atual.getChave() == chave) return;
            atual = atual.getProximo();
        }

        tabela[h] = new NoHash(chave, tabela[h],registro);
        quantidade++;

        if (tabela[h].getProximo() != null) {
            colisoes++;
        }
    }

    public void removerSemRetorno(int chave) {
        int h = hash(chave);
        NoHash atual = tabela[h];
        NoHash anterior = null;

        while (atual != null) {
            if (atual.getChave() == chave) {
                if (anterior == null) {
                    tabela[h] = atual.getProximo();
                    // remove primeiro nó
                } else {
                    anterior.setProximo(atual.getProximo());
                }
                quantidade--;
                examinarCarga();
                return;
            }
            anterior = atual;
            atual = atual.getProximo();
        }
    }

    // Remover com Retorno
    public RegistroClimatico remover(int chave) {
        int h = hash(chave);
        NoHash atual = tabela[h];
        NoHash anterior = null;

        while (atual != null) {
            if (atual.getChave() == chave) {
                if (anterior == null) {
                    tabela[h] = atual.getProximo();
                    // remove primeiro nó
                } else {
                    anterior.setProximo(atual.getProximo());
                }
                quantidade--;

                if (logger != null) {
                    logger.escreverLog("INFO: Chave " + chave + " removida.");
                }
                examinarCarga();
                return atual.getMicrocontrolador();
            }
            anterior = atual;
            atual = atual.getProximo();
        }
        return null;
    }

    public RegistroClimatico buscar(int chave) {
        int h = hash(chave);
        NoHash no = tabela[h];

        while (no != null) {
            if (no.getChave() == chave) {
                return no.getMicrocontrolador();
            }
            no = no.getProximo();
        }

        return null;
    }

    public void imprimir() {
        int total = 0;

        for (int i = 0; i < tamanho; i++) {
            NoHash no = tabela[i];
            System.out.println("Chave --> " + i + " -------------- ");

            while (no != null) {
                System.out.print("Tabela Hash:" + no.getMicrocontrolador().toString());
                total++;
                no = no.getProximo();
            }
        }

        System.out.println("Total de elementos: " + total);
        logger.escreverLog("INFO: Total de elementos: " + total);

        System.out.println("Tamanho da tabela (m): " + tabela.length);
        logger.escreverLog("INFO: Tamanho da tabela (m): " + tabela.length);

        System.out.printf("Fator de carga: %.2f\n", (double) total / tabela.length);

        System.out.println("Redimensionamentos: " + redimensionamentos);
        logger.escreverLog("INFO: Redimensionamentos: " + redimensionamentos);

        System.out.println("Colisões detectadas: " + colisoes);
        logger.escreverLog("INFO: Colisões detectadas: " + colisoes);

    }

    public int getQuantidadeNo() {
        return quantidade;
    }
}

package utilities;

import enteties.NoHashDisp;

public class TabelaHashDispositivos {
    int tamanho, quantidade;
    final int TAM_BASE = 3;
    NoHashDisp[] tabela;

    private int redimensionamentos = 0;
    private int colisoes = 0;

    public TabelaHashDispositivos(int tamanho){
        this.tamanho = tamanho;
        tabela = new NoHashDisp[tamanho];
        quantidade = 0;
    }

    private int hash(int chave) {
        return Math.abs(chave) % tamanho;
    }

    public void inserir(int chave, MicroControladores micro) {
        int h = hash(chave);
        NoHashDisp atual = tabela[h];

        while (atual != null) {
            if (atual.getChave() == chave) {
                return;
            }
            atual = atual.getProximo();
        }

        tabela[h] = new NoHashDisp(chave,tabela[h],micro);
        quantidade++;

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
        redimensionamentos++;
        NoHashDisp[] antiga = tabela;

        tamanho = novoTamanho;
        tabela = new NoHashDisp[tamanho];
        quantidade = 0;

        for (NoHashDisp noHashDisp : antiga) {
            while (noHashDisp != null) {
                reinserir(noHashDisp.getChave(), noHashDisp.getMicrocontrolador());
                noHashDisp = noHashDisp.getProximo();
            }
        }
    }

    private void reinserir(int chave,MicroControladores micro) {
        int h = hash(chave);
        NoHashDisp atual = tabela[h];

        while (atual != null) {
            if (atual.getChave() == chave) return;
            atual = atual.getProximo();
        }

        tabela[h] = new NoHashDisp(chave, tabela[h],micro);
        quantidade++;

        if (tabela[h].getProximo() != null) {
            colisoes++;
        }
    }

    public void removerSemRetorno(int chave) {
        int h = hash(chave);
        NoHashDisp atual = tabela[h];
        NoHashDisp anterior = null;

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
    public MicroControladores remover(int chave) {
        int h = hash(chave);
        NoHashDisp atual = tabela[h];
        NoHashDisp anterior = null;

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
                return atual.getMicrocontrolador();
            }
            anterior = atual;
            atual = atual.getProximo();
        }
        return null;
    }

    public MicroControladores buscar(int chave) {
        int h = hash(chave);
        NoHashDisp no = tabela[h];

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
            NoHashDisp no = tabela[i];
            System.out.println("Chave --> " + i + " -------------- ");

            while (no != null) {
                System.out.print("Tabela Hash:" + no.getMicrocontrolador().toString());
                total++;
                no = no.getProximo();
            }

            System.out.println();
        }

        System.out.println("Total de elementos: " + total);
        System.out.println("Tamanho da tabela (m): " + tabela.length);
        System.out.printf("Fator de carga: %.2f\n", (double) total / tabela.length);
        System.out.println("Redimensionamentos: " + redimensionamentos);
        System.out.println("Colisões detectadas: " + colisoes);
    }

    public int getQuantidadeNo() {
        return quantidade;
    }
}

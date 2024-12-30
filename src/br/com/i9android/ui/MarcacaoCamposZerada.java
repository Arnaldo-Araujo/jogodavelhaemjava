package br.com.i9android.ui;

import java.util.Map;
/**
 * Classe utilizada para inicializar o mapeamento de marcações do tabuleiro.
 * Cada posição do tabuleiro é representada por um número de 1 a 9 e começa zerada.
 */
public class MarcacaoCamposZerada {
     /**
     * Inicializa o mapa de marcações com valores zerados.
     * 
     * @param marcacao Mapa representando as posições do tabuleiro.
     */
    public static void inicializar(Map<Integer, Integer> marcacao) {
        for (int i = 1; i <= 9; i++) {
            marcacao.put(i, 0);
        }
    }
    public static void iniciarJogadas(Map<Integer , Boolean> marcarQuadradoMap){
        for (int i = 1; i<=18; i++) {
            marcarQuadradoMap.put(i, Boolean.FALSE);
        }
    }
}

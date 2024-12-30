/**
 * Classe principal que implementa um jogo da velha em Java.
 * A interface do jogo é desenhada usando a biblioteca Swing, e a lógica
 * do jogo permite que dois jogadores se alternem para marcar "X" ou "O".
 * O jogo verifica automaticamente o vencedor ou um empate após cada jogada.
 */
package br.com.i9android.ui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashMap;
import java.util.Random;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class JogoDaVelha extends JFrame {
    /**
     * Coordenadas do clique do mouse, usadas para determinar a jogada.
     */
    protected static int coordenadaX, coordenadaY = 0;
     /**
     * Indica se é a vez do jogador 1.
     */
    private boolean jogador1 = true;
    /**
     * Indica se é a vez do jogador 2.
     */
    private boolean jogador2 = false;
    /**
     * Variáveis de controle para verificar quais campos estão marcados por "O" ou "X".
     */

    HashMap<Integer, Boolean> liberacaoMap = new HashMap<>();
    /**
    * Mapeamento das marcações feitas pelos jogadores.
    * O índice da célula é associado ao jogador (1 para jogador 1, 2 para jogador 2).
    */
    HashMap<Integer, Integer> marcacaoMap = new HashMap<>();
    /**
    * Contador usado para identificar a célula que foi marcada na jogada atual.
    */
    private int contagem = 1;
    /**
    * Contador de jogadas realizadas no jogo.
    */
    private int contador = 0;
    /**
    * Construtor que inicializa o jogo, determina o jogador inicial de forma aleatória
    * e configura a interface gráfica e os eventos de clique do mouse.
    */
    public JogoDaVelha() {

        setTitle("Jogo da Velha");
        
        determinarJogadorInicial();
        
        MarcacaoCamposZerada.inicializar(marcacaoMap);
        
        MarcacaoCamposZerada.iniciarJogadas(liberacaoMap);

        JPanel painel = new JPanel() {
            private Graphics2D graphics2d;

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                graphics2d = (Graphics2D) g;
                desenharTabuleiro(graphics2d);
                // Marcar O ou X
                marcaCampo(graphics2d, coordenadaX, coordenadaY);
                desenharJogadores(graphics2d);
                deixarPermanente(graphics2d);

            }
        };

        add(painel);
        setSize(628, 650);
        setVisible(true);
        setResizable(false);
        addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {
                confereGanhador();
                coordenadaX = e.getX();
                coordenadaY = e.getY();
                repaint();
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }

        });
    }
    /**
     * Desenha o tabuleiro do jogo da velha.
     * 
     * @param graphics2d Objeto usado para desenhar os elementos gráficos.
     */
    protected void desenharTabuleiro(Graphics2D graphics2d) {
        BasicStroke stroke = new BasicStroke(3.0f);
        graphics2d.setStroke(stroke);
        graphics2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        graphics2d.setColor(new Color(128, 43, 160));
        graphics2d.drawRect(5, 5, 600, 600);
        // Desenha as linhas internas
        for (int i = 1; i <= 2; i++) {
            graphics2d.drawLine(200 * i, 5, 200 * i, 605); // Linhas verticais
            graphics2d.drawLine(5, 200 * i, 605, 200 * i); // Linhas horizontais
        }
    }
    /**
     * Exibe os nomes dos jogadores na interface do jogo.
     * 
     * @param graphics2d Objeto usado para desenhar os textos na tela.
     */
    protected void desenharJogadores(Graphics2D graphics2d) {
        graphics2d.drawString("Jogadores", 30.0f, 20.0f);
        graphics2d.setColor(new Color(254, 10, 10));
        graphics2d.drawString("Jogador 1 (O)", 20.0f, 40.0f);
        graphics2d.setColor(new Color(10, 10, 254));
        graphics2d.drawString("Jogador 2 (X)", 100.0f, 40.0f);
    }
    /**
     * Mantém as marcações de "X" ou "O" no tabuleiro após a jogada.
     * 
     * @param graphics2d Objeto usado para desenhar as marcações permanentes.
     */
    protected void deixarPermanente(Graphics2D graphics2d) {
        // Código desenha "X" ou "O" de acordo com as marcações armazenadas.
        for(int i = 0; i<3; i++){
            if(liberacaoMap.get(i+1)==true&&marcacaoMap.get(i+1)==1){
                graphics2d.setColor(new Color(254, 10, 10));
                graphics2d.drawOval(i*200 + 50, 50, 100, 100);
            }
            if (liberacaoMap.get(i+4)==true && marcacaoMap.get(i+4) == 1) {
                graphics2d.setColor(new Color(254, 10, 10));
                graphics2d.drawOval(i*200+50, 250, 100, 100);
            }
            if (liberacaoMap.get(i+7)==true && marcacaoMap.get(i+7) == 1) {
                graphics2d.setColor(new Color(254, 10, 10));
                graphics2d.drawOval(i*200+50, 450, 100, 100);
            }
            if(liberacaoMap.get(i+10)==true&&marcacaoMap.get(i+1)==2){
                graphics2d.setColor(new Color(10, 10, 254));
                graphics2d.drawLine(i*200+50, 50, i*200+150, 150);
                graphics2d.drawLine(i*200+150, 50, i*200+50, 150);
            }
            if (liberacaoMap.get(i+13)==true && marcacaoMap.get(i+4) == 2) {
                graphics2d.setColor(new Color(10, 10, 254));
                graphics2d.drawLine(i*200+50, 250, i*200+150, 350);
                graphics2d.drawLine(i*200+150, 250, i*200+50, 350);
            }
            if (liberacaoMap.get(i+16)==true && marcacaoMap.get(i+7) == 2) {
                graphics2d.setColor(new Color(10, 10, 254));
                graphics2d.drawLine(i*200+50, 450, i*200+150, 550);
                graphics2d.drawLine(i*200+150, 450, i*200+50, 550);
            }
        }
    }
     /**
     * Alterna entre os jogadores após cada jogada.
     */
    protected void mudarJogador() {
        jogador1 = !jogador1;
        jogador2 = !jogador2;
    }
    /**
     * Marca a jogada feita no tabuleiro com base nas coordenadas do clique do mouse.
     * 
     * @param graphics2d Objeto usado para desenhar as marcações na tela.
     * @param coordenadaX Coordenada X do clique.
     * @param coordenadaY Coordenada Y do clique.
     */
    protected void marcaCampo(Graphics2D graphics2d, int coordenadaX, int coordenadaY) {
        // Código para marcar a célula correspondente no tabuleiro.
        if (coordenadaX > 5 && coordenadaX < 200 && coordenadaY > 5 && coordenadaY < 200) {
            // marcacao |1 1|
            contagem = 1;
            if (jogador1 == true) {
                liberacaoMap.put(contagem, true);
            } else if (jogador2 == true) {
                liberacaoMap.put(contagem+9, true);
            }
            marcarContagem(contagem);
            mudarJogador();
        }
        if (coordenadaX > 205 && coordenadaX < 400 && coordenadaY > 5 && coordenadaY < 200) {
            // marcacao |1 2|
            contagem = 2;
            if (jogador1 == true) {
                liberacaoMap.put(contagem, true);
            } else if (jogador2 == true) {
                liberacaoMap.put(contagem+9, true);
            }
            marcarContagem(contagem);
            mudarJogador();
        }
        if (coordenadaX > 405 && coordenadaX < 600 && coordenadaY > 5 && coordenadaY < 200) {
            // marcacao |1 3|
            contagem = 3;
            if (jogador1 == true) {
                liberacaoMap.put(contagem, true);
            } else if (jogador2 == true) {
                liberacaoMap.put(contagem+9, true);
            }
            marcarContagem(contagem);
            mudarJogador();
        }
        // marcacao |2 1|
        if (coordenadaX > 5 && coordenadaX < 200 && coordenadaY > 205 && coordenadaY < 400) {
            contagem = 4;
            if (jogador1 == true) {
                liberacaoMap.put(contagem, true);
            } else if (jogador2 == true) {
                liberacaoMap.put(contagem+9, true);
            }
            marcarContagem(contagem);
            mudarJogador();
        }
        if (coordenadaX > 205 && coordenadaX < 400 && coordenadaY > 205 && coordenadaY < 400) {
            // marcacao |2 2|
            contagem = 5;
            if (jogador1 == true) {
                liberacaoMap.put(contagem, true);
            } else if (jogador2 == true) {
                liberacaoMap.put(contagem+9, true);
            }
            marcarContagem(contagem);
            mudarJogador();
        }
        if (coordenadaX > 405 && coordenadaX < 600 && coordenadaY > 205 && coordenadaY < 400) {
            // marcacao |2 3|
            contagem = 6;
            if (jogador1 == true) {
                liberacaoMap.put(contagem, true);
            } else if (jogador2 == true) {
                liberacaoMap.put(contagem+9, true);
            }
            marcarContagem(contagem);
            mudarJogador();
        }
        
        if (coordenadaX > 5 && coordenadaX < 200 && coordenadaY > 405 && coordenadaY < 600) {
            // marcacao |3 1|
            contagem = 7;
            if (jogador1 == true) {
                liberacaoMap.put(contagem, true);
            } else if (jogador2 == true) {
                liberacaoMap.put(contagem+9, true);
            }
            marcarContagem(contagem);
            mudarJogador();
        }
        if (coordenadaX > 205 && coordenadaX < 400 && coordenadaY > 405 && coordenadaY < 600) {
            // marcacao |3 2|
            contagem = 8;
            if (jogador1 == true) {
                liberacaoMap.put(contagem, true);
            } else if (jogador2 == true) {
                liberacaoMap.put(contagem+9, true);
            }
            marcarContagem(contagem);
            mudarJogador();
        }
        if (coordenadaX > 405 && coordenadaX < 600 && coordenadaY > 405 && coordenadaY < 600) {
            // marcacao |3 3|
            contagem = 9;
            if (jogador1 == true) {
                liberacaoMap.put(contagem, true);
            } else if (jogador2 == true) {
                liberacaoMap.put(contagem+9, true);
            }
            marcarContagem(contagem);
            mudarJogador();
        }
    }
    /**
     * Atualiza o mapa de marcações com base na jogada atual.
     * 
     * @param contagem2 Identificador da célula marcada.
     */
    private void marcarContagem(int contagem2) {
        if (jogador1 == true) {
            marcacaoMap.put(contagem2, 1);
        } else if (jogador2 == true) {
            marcacaoMap.put(contagem2, 2);
        }
        contador++;
    }
    /**
     * Verifica se há um vencedor ou um empate após cada jogada.
     * Exibe uma mensagem e encerra o jogo caso haja um resultado.
     */
    private void confereGanhador() {
        // Código para verificar combinações vencedoras e empates.
        String jogador = "";
        if (marcacaoMap.get(1) == marcacaoMap.get(2) && marcacaoMap.get(2) == marcacaoMap.get(3)
                && marcacaoMap.get(1) != 0 && marcacaoMap.get(2) != 0 && marcacaoMap.get(3) != 0) {
            if (marcacaoMap.get(1) == 1) {
                jogador = "Jogador 1 Ganhou";
            } else if (marcacaoMap.get(1) == 2) {
                jogador = "Jogador 2 Ganhou";
            }
            JOptionPane.showMessageDialog(null, "Parabens! " + jogador);
            System.exit(0);
        } else if (marcacaoMap.get(1) == marcacaoMap.get(4) && marcacaoMap.get(4) == marcacaoMap.get(7)
                && marcacaoMap.get(1) != 0 && marcacaoMap.get(4) != 0 && marcacaoMap.get(7) != 0) {
            if (marcacaoMap.get(1) == 1) {
                jogador = "Jogador 1 Ganhou";
            } else if (marcacaoMap.get(1) == 2) {
                jogador = "Jogador 2 Ganhou";
            }
            JOptionPane.showMessageDialog(null, "Parabens! " + jogador);
            System.exit(0);
        } else if (marcacaoMap.get(1) == marcacaoMap.get(5) && marcacaoMap.get(5) == marcacaoMap.get(9)
                && marcacaoMap.get(1) != 0 && marcacaoMap.get(5) != 0 && marcacaoMap.get(9) != 0) {
            if (marcacaoMap.get(1) == 1) {
                jogador = "Jogador 1 Ganhou";
            } else if (marcacaoMap.get(1) == 2) {
                jogador = "Jogador 2 Ganhou";
            }
            JOptionPane.showMessageDialog(null, "Parabens! " + jogador);
            System.exit(0);
        } else if (marcacaoMap.get(2) == marcacaoMap.get(5) && marcacaoMap.get(5) == marcacaoMap.get(8)
                && marcacaoMap.get(2) != 0 && marcacaoMap.get(5) != 0 && marcacaoMap.get(8) != 0) {
            if (marcacaoMap.get(2) == 1) {
                jogador = "Jogador 1 Ganhou";
            } else if (marcacaoMap.get(2) == 2) {
                jogador = "Jogador 2 Ganhou";
            }
            JOptionPane.showMessageDialog(null, "Parabens! " + jogador);
            System.exit(0);
        } else if (marcacaoMap.get(3) == marcacaoMap.get(5) && marcacaoMap.get(5) == marcacaoMap.get(7)
                && marcacaoMap.get(3) != 0 && marcacaoMap.get(5) != 0 && marcacaoMap.get(7) != 0) {
            if (marcacaoMap.get(3) == 1) {
                jogador = "Jogador 1 Ganhou";
            } else if (marcacaoMap.get(3) == 2) {
                jogador = "Jogador 2 Ganhou";
            }
            JOptionPane.showMessageDialog(null, "Parabens! " + jogador);
            System.exit(0);
        } else if (marcacaoMap.get(3) == marcacaoMap.get(6) && marcacaoMap.get(6) == marcacaoMap.get(9)
                && marcacaoMap.get(3) != 0 && marcacaoMap.get(6) != 0 && marcacaoMap.get(9) != 0) {
            if (marcacaoMap.get(3) == 1) {
                jogador = "Jogador 1 Ganhou";
            } else if (marcacaoMap.get(3) == 2) {
                jogador = "Jogador 2 Ganhou";
            }
            JOptionPane.showMessageDialog(null, "Parabens! " + jogador);
            System.exit(0);
        } else if (marcacaoMap.get(4) == marcacaoMap.get(5) && marcacaoMap.get(5) == marcacaoMap.get(6)
                && marcacaoMap.get(4) != 0 && marcacaoMap.get(5) != 0 && marcacaoMap.get(6) != 0) {
            if (marcacaoMap.get(4) == 1) {
                jogador = "Jogador 1 Ganhou";
            } else if (marcacaoMap.get(4) == 2) {
                jogador = "Jogador 2 Ganhou";
            }
            JOptionPane.showMessageDialog(null, "Parabens! " + jogador);
            System.exit(0);
        } else if (marcacaoMap.get(7) == marcacaoMap.get(8) && marcacaoMap.get(8) == marcacaoMap.get(9)
                && marcacaoMap.get(7) != 0 && marcacaoMap.get(8) != 0 && marcacaoMap.get(9) != 0) {
            if (marcacaoMap.get(7) == 1) {
                jogador = "Jogador 1 Ganhou";
            } else if (marcacaoMap.get(7) == 2) {
                jogador = "Jogador 2 Ganhou";
            }
            JOptionPane.showMessageDialog(null, "Parabens! " + jogador);
            System.exit(0);
        } else if (contador == 9) {
            JOptionPane.showMessageDialog(null, "Infelizmente nao houve ganhador");
            System.exit(0);
        }
    }

    private void determinarJogadorInicial() {
        Random numero = new Random();
        int nextInt = numero.nextInt(2);
        if (nextInt == 1) {
            jogador1 = true;
            jogador2 = false;
            JOptionPane.showMessageDialog(null, "Jogador 1 comeca");
        } else {
            jogador2 = true;
            jogador1 = false;
            JOptionPane.showMessageDialog(null, "Jogador 2 comeca");
        }
    }
}
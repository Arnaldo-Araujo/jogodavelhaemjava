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

    protected static int coordenadaX, coordenadaY = 0;
    private boolean jogador1 = true;
    private boolean jogador2 = false;
    protected boolean oliberar11, oliberar12, oliberar13, oliberar21, oliberar22, oliberar23,
            oliberar31, oliberar32, oliberar33, xliberar11, xliberar12, xliberar13, xliberar21, xliberar22,
            xliberar23, xliberar31, xliberar32, xliberar33 = false;
    HashMap<Integer, Integer> marcacaoMap = new HashMap<>();
    private int contagem = 1;
    private int contador = 0;

    public JogoDaVelha() {
        setTitle("Jogo da Velha");
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
        new MarcacaoCamposZerada(marcacaoMap);

        JPanel painel = new JPanel() {
            private Graphics2D graphics2d;

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                graphics2d = (Graphics2D) g;
                desenharTela(graphics2d);
                // Marcar O ou X
                marcaCampo(graphics2d, coordenadaX, coordenadaY);
                marcarJogadorNaTela(graphics2d);
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

    protected void desenharTela(Graphics2D graphics2d) {
        BasicStroke stroke = new BasicStroke(3.0f);
        graphics2d.setStroke(stroke);
        graphics2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        graphics2d.setColor(new Color(128, 43, 160));
        graphics2d.drawRect(5, 5, 600, 600);
        graphics2d.drawLine(200, 5, 200, 602);
        graphics2d.drawLine(400, 5, 400, 602);
        graphics2d.drawLine(4, 200, 602, 200);
        graphics2d.drawLine(4, 400, 602, 400);
    }

    protected void marcarJogadorNaTela(Graphics2D graphics2d) {
        graphics2d.drawString("Jogadores", 30.0f, 20.0f);
        graphics2d.setColor(new Color(254, 10, 10));
        graphics2d.drawString("Jogador 1", 20.0f, 40.0f);
        graphics2d.setColor(new Color(10, 10, 254));
        graphics2d.drawString("Jogador 2", 80.0f, 40.0f);
    }

    protected void deixarPermanente(Graphics2D graphics2d) {
        if (oliberar11 & marcacaoMap.get(1) == 1) {
            graphics2d.setColor(new Color(254, 10, 10));
            graphics2d.drawOval(50, 50, 100, 100);
        }
        if (oliberar12 & marcacaoMap.get(2) == 1) {
            graphics2d.setColor(new Color(254, 10, 10));
            graphics2d.drawOval(250, 50, 100, 100);
        }
        if (oliberar13 & marcacaoMap.get(3) == 1) {
            graphics2d.setColor(new Color(254, 10, 10));
            graphics2d.drawOval(450, 50, 100, 100);
        }
        if (oliberar21 & marcacaoMap.get(4) == 1) {
            graphics2d.setColor(new Color(254, 10, 10));
            graphics2d.drawOval(50, 250, 100, 100);
        }
        if (oliberar22 & marcacaoMap.get(5) == 1) {
            graphics2d.setColor(new Color(254, 10, 10));
            graphics2d.drawOval(250, 250, 100, 100);
        }
        if (oliberar23 & marcacaoMap.get(6) == 1) {
            graphics2d.setColor(new Color(254, 10, 10));
            graphics2d.drawOval(450, 250, 100, 100);
        }
        if (oliberar31 & marcacaoMap.get(7) == 1) {
            graphics2d.setColor(new Color(254, 10, 10));
            graphics2d.drawOval(50, 450, 100, 100);
        }
        if (oliberar32 & marcacaoMap.get(8) == 1) {
            graphics2d.setColor(new Color(254, 10, 10));
            graphics2d.drawOval(250, 450, 100, 100);
        }
        if (oliberar33 & marcacaoMap.get(9) == 1) {
            graphics2d.setColor(new Color(254, 10, 10));
            graphics2d.drawOval(450, 450, 100, 100);
        }

        if (xliberar11 & marcacaoMap.get(1) == 2) {
            graphics2d.setColor(new Color(10, 10, 254));
            graphics2d.drawLine(50, 50, 150, 150);
            graphics2d.drawLine(150, 50, 50, 150);
        }
        if (xliberar12 & marcacaoMap.get(2) == 2) {
            graphics2d.setColor(new Color(10, 10, 254));
            graphics2d.drawLine(250, 50, 350, 150);
            graphics2d.drawLine(350, 50, 250, 150);
        }
        if (xliberar13 & marcacaoMap.get(3) == 2) {
            graphics2d.setColor(new Color(10, 10, 254));
            graphics2d.drawLine(450, 50, 550, 150);
            graphics2d.drawLine(550, 50, 450, 150);
        }
        if (xliberar21 & marcacaoMap.get(4) == 2) {
            graphics2d.setColor(new Color(10, 10, 254));
            graphics2d.drawLine(50, 250, 150, 350);
            graphics2d.drawLine(150, 250, 50, 350);
        }
        if (xliberar22 & marcacaoMap.get(5) == 2) {
            graphics2d.setColor(new Color(10, 10, 254));
            graphics2d.drawLine(250, 250, 350, 350);
            graphics2d.drawLine(350, 250, 250, 350);
        }
        if (xliberar23 & marcacaoMap.get(6) == 2) {
            graphics2d.setColor(new Color(10, 10, 254));
            graphics2d.drawLine(450, 250, 550, 350);
            graphics2d.drawLine(550, 250, 450, 350);
        }
        if (xliberar31 & marcacaoMap.get(7) == 2) {
            graphics2d.setColor(new Color(10, 10, 254));
            graphics2d.drawLine(50, 450, 150, 550);
            graphics2d.drawLine(150, 450, 50, 550);
        }
        if (xliberar32 & marcacaoMap.get(8) == 2) {
            graphics2d.setColor(new Color(10, 10, 254));
            graphics2d.drawLine(250, 450, 350, 550);
            graphics2d.drawLine(350, 450, 250, 550);
        }
        if (xliberar33 & marcacaoMap.get(9) == 2) {
            graphics2d.setColor(new Color(10, 10, 254));
            graphics2d.drawLine(450, 450, 550, 550);
            graphics2d.drawLine(550, 450, 450, 550);
        }
    }

    protected void mudarJogador() {
        jogador1 = !jogador1;
        jogador2 = !jogador2;
    }

    protected void marcaCampo(Graphics2D graphics2d, int coordenadaX, int coordenadaY) {
        if (coordenadaX > 5 && coordenadaX < 200 && coordenadaY > 5 && coordenadaY < 200) {
            // marcacao |1 1|
            contagem = 1;
            if (jogador1 == true) {
                oliberar11 = true;
            } else if (jogador2 == true) {
                xliberar11 = true;
            }
            marcarContagem(contagem);
            mudarJogador();
        }
        if (coordenadaX > 205 && coordenadaX < 400 && coordenadaY > 5 && coordenadaY < 200) {
            // marcacao |1 2|
            contagem = 2;
            if (jogador1 == true) {
                oliberar12 = true;
            } else if (jogador2 == true) {
                xliberar12 = true;
            }
            marcarContagem(contagem);
            mudarJogador();
        }
        if (coordenadaX > 405 && coordenadaX < 600 && coordenadaY > 5 && coordenadaY < 200) {
            // marcacao |1 3|
            contagem = 3;
            if (jogador1 == true) {
                oliberar13 = true;
            } else if (jogador2 == true) {
                xliberar13 = true;
            }
            marcarContagem(contagem);
            mudarJogador();
        }
        // marcacao |2 1|
        if (coordenadaX > 5 && coordenadaX < 200 && coordenadaY > 205 && coordenadaY < 400) {
            contagem = 4;
            if (jogador1 == true) {
                oliberar21 = true;
            } else if (jogador2 == true) {
                xliberar21 = true;
            }
            marcarContagem(contagem);
            mudarJogador();
        }
        if (coordenadaX > 205 && coordenadaX < 400 && coordenadaY > 205 && coordenadaY < 400) {
            // marcacao |2 2|
            contagem = 5;
            if (jogador1 == true) {
                oliberar22 = true;
            } else if (jogador2 == true) {
                xliberar22 = true;
            }
            marcarContagem(contagem);
            mudarJogador();
        }
        if (coordenadaX > 405 && coordenadaX < 600 && coordenadaY > 205 && coordenadaY < 400) {
            // marcacao |2 3|
            contagem = 6;
            if (jogador1 == true) {
                oliberar23 = true;
            } else if (jogador2 == true) {
                xliberar23 = true;
            }
            marcarContagem(contagem);
            mudarJogador();
        }

        if (coordenadaX > 5 && coordenadaX < 200 && coordenadaY > 405 && coordenadaY < 600) {
            // marcacao |3 1|
            contagem = 7;
            if (jogador1 == true) {
                oliberar31 = true;
            } else if (jogador2 == true) {
                xliberar31 = true;
            }
            marcarContagem(contagem);
            mudarJogador();
        }
        if (coordenadaX > 205 && coordenadaX < 400 && coordenadaY > 405 && coordenadaY < 600) {
            // marcacao |3 2|
            contagem = 8;
            if (jogador1 == true) {
                oliberar32 = true;
            } else if (jogador2 == true) {
                xliberar32 = true;
            }
            marcarContagem(contagem);
            mudarJogador();
        }
        if (coordenadaX > 405 && coordenadaX < 600 && coordenadaY > 405 && coordenadaY < 600) {
            // marcacao |3 3|
            contagem = 9;
            if (jogador1 == true) {
                oliberar33 = true;
            } else if (jogador2 == true) {
                xliberar33 = true;
            }
            marcarContagem(contagem);
            mudarJogador();
        }
    }

    private void marcarContagem(int contagem2) {
        if (jogador1 == true) {
            marcacaoMap.put(contagem2, 1);
        } else if (jogador2 == true) {
            marcacaoMap.put(contagem2, 2);
        }
        contador++;
    }

    private void confereGanhador() {
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
}

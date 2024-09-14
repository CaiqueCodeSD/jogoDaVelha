package ticTacToe;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TicTacToe extends JFrame {

    private JButton[][] botoes = new JButton[3][3];
    private boolean jogadorX = true;

    public TicTacToe() {

        setTitle("Jogo da Velha - TIC TAC TOE");
        setSize(700, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(3, 3));

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                botoes[i][j] = new JButton("");
                botoes[i][j].setFont(new Font("Arial", Font.PLAIN, 60));
                botoes[i][j].setFocusPainted(false);
                botoes[i][j].setBackground(Color.DARK_GRAY); 
                botoes[i][j].addActionListener(new ButtonClickListener(i, j));
                add(botoes[i][j]);
            }
        }

        setVisible(true); 
    }

    private class ButtonClickListener implements ActionListener {

        private int linha;
        private int coluna;

        public ButtonClickListener(int linha, int coluna) {
            this.linha = linha;
            this.coluna = coluna;
        }

        @Override
        public void actionPerformed(ActionEvent e) {

            if (botoes[linha][coluna].getText().equals("")) {
                if (jogadorX) {
                    botoes[linha][coluna].setText("X");
                    botoes[linha][coluna].setForeground(Color.GREEN); 
                } else {
                    botoes[linha][coluna].setText("O");
                    botoes[linha][coluna].setForeground(Color.RED); 
                }
                jogadorX = !jogadorX; 

                if (verificarVitoria()) {
                    JOptionPane.showMessageDialog(null, (jogadorX ? "O" : "X") + " venceu!");
                    reiniciarJogo(); 
                } else if (verificarEmpate()) {
                    JOptionPane.showMessageDialog(null, "Empate!");
                    reiniciarJogo(); 
                }
            }
        }
    }

    
    private boolean verificarVitoria() {
        
        for (int i = 0; i < 3; i++) {
            if (botoes[i][0].getText().equals(botoes[i][1].getText()) && botoes[i][0].getText().equals(botoes[i][2].getText()) && !botoes[i][0].getText().equals("")) {
                return true; 
            }
            if (botoes[0][i].getText().equals(botoes[1][i].getText()) && botoes[0][i].getText().equals(botoes[2][i].getText()) && !botoes[0][i].getText().equals("")) {
                return true; 
            }
        }

        if (botoes[0][0].getText().equals(botoes[1][1].getText()) && botoes[0][0].getText().equals(botoes[2][2].getText()) && !botoes[0][0].getText().equals("")) {
            return true;
        }
        if (botoes[0][2].getText().equals(botoes[1][1].getText()) && botoes[0][2].getText().equals(botoes[2][0].getText()) && !botoes[0][2].getText().equals("")) {
            return true;
        }
        return false;
    }

    private boolean verificarEmpate() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (botoes[i][j].getText().equals("")) {
                    return false; 
                }
            }
        }
        return true; 
    }

    private void reiniciarJogo() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                botoes[i][j].setText(""); 
                botoes[i][j].setForeground(Color.BLACK); 
            }
        }
        jogadorX = true;
    }

    public static void main(String[] args) {
        new TicTacToe();
    }
}

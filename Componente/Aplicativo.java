//O objetivo é implementar a classe main, criando telas/janelas de acordo com nossa necessidade

import javax.swing.*;
// Janela principal
public class Aplicativo extends JFrame {

    public Aplicativo() {
        setTitle("Aplicativo");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // centraliza na tela

        // Define a tela inicial
        setContentPane(new Janela());
        setVisible(true);
    }

    public static void main(String[] args) {
        new Aplicativo();
    }
}

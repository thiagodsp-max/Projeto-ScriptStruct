//O objetivo é implementar a classe main, criando telas/janelas de acordo com nossa necessidade

import javax.swing.*;
// Janela principal que implementará as subclasses de Àrea/Janela
public class App extends JFrame {

    public App() {
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // centraliza na tela

        // Aqui definimos qual das nossas telas vai ser a inicial
        setContentPane(new Collections());
        setVisible(true);
    }

    public static void main(String[] args) {
        new App();
    }
}

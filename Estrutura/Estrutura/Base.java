package Estrutura;
import Componente.Projeto;
import Controle.Tema;
import javax.swing.*;
import java.awt.*;

public abstract class Base extends JPanel {
    protected JPanel cabecalho;
    protected JPanel conteudo;
    protected JPanel rodape;
    JLabel titulo;
    //Classe Abstrata que serve de Base para as Subclasses na Package
    protected Base() {
        setLayout(new BorderLayout());
        //Simula a tela de um aplicativo de telefone, pela divisão em 3 paineis
        cabecalho = new JPanel();
        conteudo = new JPanel();
        rodape = new JPanel();
        add(cabecalho, BorderLayout.NORTH);
        add(conteudo, BorderLayout.CENTER);
        add(rodape, BorderLayout.SOUTH);
        Controle.Tema.loading();
    }

    //Os 3 Componentes Estruturais: Conteudo, Cabeçalho e Rodape

    protected abstract void montarConteudo();

    protected abstract void montarRodape();

    protected void montarCabecalho(String botao, String title) {
        //Botão para ir prar outra Tela
        JButton back = new JButton(botao);
        back.addActionListener(e_ -> changetela());
        cabecalho.add(back);
        //Nome da Tela Atual
        titulo = new JLabel(title);
        titulo.setForeground(Tema.fgSecondary);
        //titulo.setEditable(false);
        cabecalho.add(titulo);

        //Configuração de Formatação
        JPopupMenu menu = new JPopupMenu();
        JMenuItem light = new JMenuItem("Modo Claro");
        JMenuItem dark = new JMenuItem("Modo Escuro");
        JButton plus = new JButton("Config");
        plus.addActionListener(e -> {
            menu.show(plus, 0, plus.getHeight());
        });
        light.addActionListener(e->{
            Tema.bgPrimary = Color.WHITE;
            Tema.bgSecondary = Color.BLACK;
            Tema.bgButton = new Color(220,220,220);
            Tema.fgPrimary = Color.BLACK;
            Tema.fgSecondary = Color.WHITE;
            Tema.fgButton = Color.BLACK;

            Tema.saving();
            aplicarTema();
        });
        dark.addActionListener(e->{
            Tema.bgPrimary = new Color(30,30,30);
            Tema.bgSecondary = Color.WHITE;
            Tema.bgButton = new Color(70,70,70);
            Tema.fgPrimary = Color.WHITE;
            Tema.fgSecondary = Color.BLACK;
            Tema.fgButton = Color.WHITE;

            Tema.saving();
            aplicarTema();
        });
        menu.add(light);
        menu.add(dark);
        cabecalho.add(plus);
    }

    //Métodos auxiliares de mudança
    public void trocada(JPanel novaTela) {
        if(novaTela instanceof Base){
            ((Base) novaTela).aplicarTema();
        }
        JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
        frame.setContentPane(novaTela);
        frame.revalidate();
        frame.repaint();
    }

    protected void changetela() {
        trocada(new Collections()); // padrão, pode sobrescrever
    }

    protected void aplicarTema(){
        TemaEstrutura(this);
    }

    protected void TemaEstrutura(Component c) {
        if (c instanceof JButton) {
            c.setBackground(Tema.bgButton);
            c.setForeground(Tema.fgButton);
        }
        else if (c instanceof JLabel && SwingUtilities.isDescendingFrom(c, cabecalho)) {
            c.setBackground(Tema.bgSecondary);
            c.setForeground(Tema.fgSecondary);
        }
        else if (c == cabecalho || c == rodape) {
            c.setBackground(Tema.bgSecondary);
            c.setForeground(Tema.fgSecondary);
        }
        else {
            c.setBackground(Tema.bgPrimary);
            c.setForeground(Tema.fgPrimary);
        }

        if (c instanceof Container) {
            for (Component child : ((Container) c).getComponents()) {
                TemaEstrutura(child);
            }
        }
    }
}

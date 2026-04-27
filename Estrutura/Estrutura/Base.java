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

    //protected abstract void montarCabecalho();
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
        //JMenuItem fundo = new JMenuItem("Cor do Fundo");
        //JMenuItem letra = new JMenuItem("Cor da Letra");
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

        /*
        fundo.addActionListener(e -> {
            Color ground = JColorChooser.showDialog(this, "Escolha o cor do fundo", getBackground());
            if (ground != null) {
                //aplicarfundo(conteudo,ground);
                Tema.back=ground;
                Tema.saving();
                aplicarTema();
                repaint();
            }
        });
        letra.addActionListener(e -> {
            Color shine = JColorChooser.showDialog(this, "Escolha a cor dos elementos", getForeground());
            if (shine != null) {
                //aplicarcor(conteudo,shine);
                //cabecalho.setBackground(shine);
                //rodape.setBackground(shine);
                Tema.fore=shine;
                Tema.saving();
                aplicarTema();
                repaint();
            }
        });
        menu.add(fundo);
        menu.add(letra);
         */
        menu.add(light);
        menu.add(dark);
        cabecalho.add(plus);

    }

    protected abstract void montarConteudo();

    protected abstract void montarRodape();

    //Métodos auxiliares de mudança
    protected void aplicarfundo(Component piece,Color cor){
        if(piece instanceof JComponent){
            piece.setBackground(cor);
        }
        if(piece instanceof Container){
            //Recursivamente aplicar cor em todos os elementos
            for(Component child :((Container)piece).getComponents()){
                aplicarfundo(child,cor);
            }
        }
    }

    protected void aplicarcor(Component piece,Color cor){
        if(piece instanceof JComponent){
            piece.setForeground(cor);
        }
        if(piece instanceof Container){
            //Recursivamente aplicar cor em todos os elementos
            for(Component child :((Container)piece).getComponents()){
                aplicarcor(child,cor);
            }
        }
    }
    //Método de Trocar Telas
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

package Estrutura;
import Componente.Projeto;

import javax.swing.*;
import java.awt.*;

public abstract class Base extends JPanel {
    protected JPanel cabecalho;
    protected JPanel conteudo;
    protected JPanel rodape;
    JTextField titulo;

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

    }

    //protected abstract void montarCabecalho();
    protected void montarCabecalho(String botao, String title) {
        //Botão para ir prar outra Tela
        JButton back = new JButton(botao);
        back.addActionListener(e_ -> changetela());
        cabecalho.add(back);
        //Nome da Tela Atual
        titulo = new JTextField(title);
        cabecalho.add(titulo);
        //Configuração de Formatação
        JPopupMenu menu = new JPopupMenu();
        JMenuItem fundo = new JMenuItem("Cor do Fundo");
        JMenuItem letra = new JMenuItem("Cor da Letra");
        JButton plus = new JButton("Config");
        plus.addActionListener(e -> {
            menu.show(plus, 0, plus.getHeight());
        });
        fundo.addActionListener(e -> {
            Color ground = JColorChooser.showDialog(this, "Escolha o cor do fundo", getBackground());
            if (ground != null) {
                aplicarfundo(conteudo,ground);
                repaint();
            }
        });
        letra.addActionListener(e -> {
            Color shine = JColorChooser.showDialog(this, "Escolha a cor dos elementos", getForeground());
            if (shine != null) {
                aplicarcor(conteudo,shine);
                cabecalho.setBackground(shine);
                rodape.setBackground(shine);
                repaint();
            }
        });
        menu.add(fundo);
        menu.add(letra);
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
        JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
        frame.setContentPane(novaTela);
        frame.revalidate();
        frame.repaint();
    }

    protected void changetela() {
        trocada(new Collections()); // padrão, pode sobrescrever
    }

}

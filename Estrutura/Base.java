package Struct;
import Beta.Livro;
import javax.swing.*;
import java.awt.*;

public abstract class Base extends JPanel {
    protected JPanel cabecalho;
    protected JPanel conteudo;
    protected JPanel rodape;
    //Essa é a classe que definirá a estrutura base para todas as telas
  //Por isso é uma classe abstrata, que serve de plataforma para as subclasses de tela
    protected Base(){
        setLayout(new BorderLayout());
      //Divisão em 3 painéis, simulando um aplicativo de telefone real
        cabecalho = new JPanel();
        conteudo = new JPanel();
        rodape = new JPanel();
        add(cabecalho, BorderLayout.NORTH);
        add(conteudo, BorderLayout.CENTER);
        add(rodape, BorderLayout.SOUTH);

        montarCabecalho();
        montarConteudo();
        montarRodape();
    }

    protected abstract void montarCabecalho(); //Alterações futuras para facilitar padronização
    protected abstract void montarConteudo();
    protected abstract void montarRodape();

    //Método de Trocar Telas
    public void trocada(JPanel novaTela) {
        JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
        frame.setContentPane(novaTela);
        frame.revalidate();
        frame.repaint();
    }
}

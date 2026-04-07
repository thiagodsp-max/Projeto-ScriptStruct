import javax.swing.*;
//Baseado no conceito de Tela->Janela
import java.awt.*;

//Aqui é onde juntamos as diferentes áreas, afim de compôr a
//nossa tela, de acordo com o conteúdo que se deve mostrar
class Janela extends Area {
    @Override
    protected void montarCabecalho() {
        JButton back = new JButton("Voltar");
        cabecalho.add(back);
        cabecalho.add(new JLabel("Meu Aplicativo"));
        JButton plus = new JButton("Config");
        cabecalho.add(plus);
    }

    @Override
    protected void montarConteudo() {
        //Conseguir compôr diferentes tipos de conteúdos
        //a depender da classe/tela a ser implementada
        conteudo.setLayout(new BorderLayout());
        Editor paper=new Editor();
        conteudo.add(paper, BorderLayout.CENTER);
    }

    @Override
    protected void montarRodape() {
        rodape.add(new JLabel("Versão 1.0"));
    }
}

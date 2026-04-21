package Struct;

import Beta.Livro;

import javax.swing.*;
import java.awt.*;

public class Collections extends Base{

    JLabel titulo;
    JPanel libros;
    //Janela feita para armazenar Livros/Lista de Notas
    @Override
    protected void montarCabecalho() {
        JLabel titulo = new JLabel("Coleção de Livros");
        cabecalho.add(titulo);
        JButton plus = new JButton("Config");
        cabecalho.add(plus);
    }

    @Override
    protected void montarConteudo() {
        conteudo.setLayout(new BorderLayout());
        libros=new JPanel();
        JScrollPane scroll= new JScrollPane(libros);
        conteudo.add(scroll,BorderLayout.CENTER);
        //
    }

    @Override
    protected void montarRodape() {
        JButton livro = new JButton("Novo Livro");
        rodape.add(livro);

        livro.addActionListener(e ->{
            String name=JOptionPane.showInputDialog("Nome do Livro");
            if(name!=null && !name.isEmpty()){
                //Livro view=new Livro(name);
                //addBook(view);
            }
        });
    }

    /*
    public void addBook(Livro Conto){
        JPanel bookpanel = new JPanel();
        bookpanel.setLayout(new BorderLayout());
        bookpanel.setBorder(BorderFactory.createTitledBorder(Conto.getName()));
    }
     */
}

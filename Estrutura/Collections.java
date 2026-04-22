package Struct;

import Beta.Projeto;

import javax.swing.*;
import java.awt.*;

public class Collections extends Base{
    JPanel libros;
    //Janela feita para armazenar Livros/Lista de Notas
    @Override
    protected void montarCabecalho() {
        JButton back = new JButton("Lista de Anotações");
        back.addActionListener(e_->{
            trocada(new ToDoList());
        });
        cabecalho.add(back);
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
                Projeto view=new Projeto(name);
                addBook(view);
            }
        });
    }


    public void addBook(Projeto Conto){
        JPanel bookpanel = new JPanel();
        JButton caderno = new JButton(Conto.getName());
        caderno.addActionListener(e -> {
            BookOrder sumario = new BookOrder();
            trocada(sumario);
        });
        bookpanel.add(caderno);
        libros.add(bookpanel);
        libros.revalidate();
    }
}

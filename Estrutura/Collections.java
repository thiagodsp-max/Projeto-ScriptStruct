package Struct;

import Beta.Projeto;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Collections extends Base{
    JPanel libros;

    List<Projeto> biblio=new ArrayList<>();

    public Collections(){
        montarCabecalho("Lista de Anotações","Coleção de Livros");
        montarConteudo();
        montarRodape();
    }
    protected void changetela() {
        trocada(new ToDoList()); // padrão, pode sobrescrever
    }
    //Janela feita para armazenar Livros/Lista de Notas
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
                biblio.add(view);
                addBook(view);
            }
        });
    }


    public void addBook(Projeto Conto){
        JPanel bookpanel = new JPanel();
        JButton caderno = new JButton(Conto.getName());
        caderno.addActionListener(e -> {
            BookOrder sumario = new BookOrder(Conto);
            trocada(sumario);
        });
        bookpanel.add(caderno);
        libros.add(bookpanel);
        libros.revalidate();
    }
}

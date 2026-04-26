package Estrutura;

import Componente.Projeto;
import Controle.Files;
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
        loadlivro();
    }
    protected void changetela() {
        trocada(new ToDoList()); // padrão, pode sobrescrever
    }
    //Janela feita para armazenar Livros/Lista de Notas
    @Override
    protected void montarConteudo() {
        conteudo.setLayout(new BorderLayout());
        libros=new JPanel();
        libros.setLayout(new BoxLayout(libros,BoxLayout.X_AXIS));
        JScrollPane scroll= new JScrollPane(libros);
        conteudo.add(scroll,BorderLayout.CENTER);
    }

    private void loadlivro(){
        List<String> nomes = Files.listaPasta("");
        for(String name:nomes){
            Projeto pr=new Projeto(name);
            addBook(pr);
        }
    }

    @Override
    protected void montarRodape() {
        JButton livro = new JButton("Novo Livro");
        rodape.add(livro);

        livro.addActionListener(e ->{
            String name=JOptionPane.showInputDialog("Nome do Livro");
            if(name!=null && !name.isEmpty()){
                Projeto view=new Projeto(name);
                Files.criarPasta(name);
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
        JButton rename = new JButton("F12");
        /*
        rename.addActionListener(e->{
            String capa=JOptionPane.showInputDialog("Novo Título do Livro:");
            if(){
                String old=archive.get
            }
        });

         */
        bookpanel.add(caderno);
        bookpanel.add(rename);
        libros.add(bookpanel);
        libros.revalidate();
    }
}

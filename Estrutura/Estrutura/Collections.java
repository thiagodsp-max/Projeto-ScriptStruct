package Estrutura;

import Componente.Projeto;
import Controle.Files;
import javax.swing.*;
import java.awt.*;
import java.awt.print.Book;
import java.io.File;
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
        libros.setLayout(new BoxLayout(libros,BoxLayout.Y_AXIS));
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
        JPanel linha=new JPanel(new BorderLayout());
        linha.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        linha.setPreferredSize(new Dimension(0, 40));
        linha.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        JButton caderno= new JButton(Conto.getName());
        caderno.setFocusPainted(false);
        caderno.setMargin(new Insets(5,10,5,10));
        caderno.addActionListener(e->{
            BookOrder sumario = new BookOrder(Conto);
            trocada(sumario);
        });
        JPanel act=new JPanel(new FlowLayout(FlowLayout.RIGHT,5,0));
        //
        JButton rename=new JButton("F12");
        rename.addActionListener(e->{
            String nova=JOptionPane.showInputDialog("Novo Título:",Conto.getName());
            if(nova!=null && !nova.isEmpty()){
                String old=Conto.getName();
                String novo=nova;
                Files.renomear(old,novo);
                Conto.setName(nova);
                caderno.setText(nova);
            }
        });
        rename.setMargin(new Insets(2,5,2,5));
        JButton delete=new JButton("Deletar");
        delete.addActionListener(e->{
            int confirma = JOptionPane.showConfirmDialog(null,"Excluir livro??");
            if(confirma == JOptionPane.YES_OPTION){
                File pst=new File("repositorio/"+Conto.getName());
                deletaPasta(pst);
                libros.remove(linha);
                libros.revalidate();
                libros.repaint();
            }
        });
        delete.setMargin(new Insets(2,5,2,5));
        act.add(rename);
        act.add(delete);
        linha.add(caderno,BorderLayout.CENTER);
        linha.add(act,BorderLayout.EAST);
        libros.add(linha);
        libros.revalidate();
    }
    private void deletaPasta(File dir){
        if(dir.isDirectory()){
            for(File f:dir.listFiles()){
                deletaPasta(f);
            }
        }
        dir.delete();
    }
/*
    public void addBook(Projeto Conto){
        JPanel bookpanel = new JPanel();
        JButton caderno = new JButton(Conto.getName());
        caderno.addActionListener(e -> {
            BookOrder sumario = new BookOrder(Conto);
            trocada(sumario);
        });
        JButton rename = new JButton("F12");
        bookpanel.add(caderno);
        bookpanel.add(rename);
        libros.add(bookpanel);
        libros.revalidate();
    }
 */
}

package Estrutura;

import Componente.Pasta;
import Componente.Projeto;
import Componente.Arquivo;

import javax.swing.*;
import java.awt.*;

public class BookOrder extends Base{
    JTextField titulo;
    JPanel listaPasta;
    //Janela que mostra as Categorias e Arquivos de um Livro
    public BookOrder(Projeto book){
        montarCabecalho("Voltar",book.getName());
        montarConteudo();
        montarRodape();
    }
    protected void changetela() {
        trocada(new Collections()); // padrão, pode sobrescrever
    }
    @Override
    protected void montarConteudo() {
        conteudo.setLayout(new BorderLayout());
        listaPasta=new JPanel();
        listaPasta.setLayout(new BoxLayout(listaPasta,BoxLayout.Y_AXIS));
        JScrollPane scroll= new JScrollPane(listaPasta);
        conteudo.add(scroll,BorderLayout.CENTER);

        addPasta(new Pasta("Categoria Padrão"));
        /*for(Pasta y : libro.getSecao()){
            addPasta(y);
        }*/

    }

    @Override
    protected void montarRodape() {
        //Botão para criar nova categoria ou novo arquivo
        JButton pasta = new JButton("Nova Pasta");
        //JButton arquivo= new JButton("Novo Arquivo");
        rodape.add(pasta);
        //rodape.add(arquivo);

        pasta.addActionListener(e->{
            String nome=JOptionPane.showInputDialog("Nome da Categoria");
            if(nome != null && !nome.isEmpty()){
                Pasta pst=new Pasta(nome);
                addPasta(pst);
                //book.addSecao(pst);
            }
        });
    }

    public void addPasta(Pasta Pasta){
        JPanel pastapainel = new JPanel();
        pastapainel.setLayout(new BorderLayout());
        pastapainel.setBorder(BorderFactory.createTitledBorder(Pasta.getNome()));
        JPanel capitulos= new JPanel();
        capitulos.setLayout(new BoxLayout(capitulos, BoxLayout.Y_AXIS));
        JButton addTexto= new JButton("+ Capitulo");
        addTexto.addActionListener(e -> {
            String nome=JOptionPane.showInputDialog("Nome do Texto:");

            if(nome != null && !nome.isEmpty()){
                Arquivo novo=new Arquivo(nome,"");
                Pasta.addTexto(novo);
                JButton botao=criarBotao(novo);
                capitulos.add(botao);
                capitulos.revalidate();
                capitulos.repaint();
            }
        });
        capitulos.add(addTexto);
        for (Arquivo x: Pasta.getTextos()){
            capitulos.add(criarBotao(x));
        }
        pastapainel.add(capitulos, BorderLayout.CENTER);
        listaPasta.add(pastapainel);
        listaPasta.revalidate();
    }

    private JButton criarBotao(Arquivo archive){
        JButton bt= new JButton(archive.getTitle());
        bt.addActionListener(e -> {
            System.out.println("O seguinte arquivo foi aberto:"+archive.getTitle());
            TextEditor chapter = new TextEditor(archive,this);
            //chapter.setDoc
            trocada(chapter);
        });
        return bt;
    }
}

package Struct;

import Beta.Categorias;
import Beta.Livro;
import Beta.Texto;

import javax.swing.*;
import java.awt.*;

public class BookOrder extends Base{
    JTextField titulo;
    JPanel listaPasta;
    //Janela que mostra as Categorias e Arquivos de um Livro

    @Override
    protected void montarCabecalho() {
        JButton back = new JButton("Voltar");
        cabecalho.add(back);
        titulo = new JTextField("Livro 01",15);
        cabecalho.add(titulo);
        JButton plus = new JButton("Config");
        cabecalho.add(plus);
    }

    @Override
    protected void montarConteudo() {
        conteudo.setLayout(new BorderLayout());
        listaPasta=new JPanel();
        listaPasta.setLayout(new BoxLayout(listaPasta,BoxLayout.Y_AXIS));
        JScrollPane scroll= new JScrollPane(listaPasta);
        conteudo.add(scroll,BorderLayout.CENTER);

        addPasta(new Categorias("Categoria Padrão"));
        /*for(Categorias y : libro.getSecao()){
            addPasta(y);
        }*/

    }

    @Override
    protected void montarRodape() {
        //Botão para criar nova categoria ou novo arquivo
        JButton pasta = new JButton("Nova Pasta");
        JButton arquivo= new JButton("Novo Texto");
        rodape.add(pasta);
        rodape.add(arquivo);

        pasta.addActionListener(e->{
            String nome=JOptionPane.showInputDialog("Nome da Categoria");
            if(nome != null && !nome.isEmpty()){
                Categorias pst=new Categorias(nome);
                addPasta(pst);
                //libro.addSecao(pst);
            }

        });

        arquivo.addActionListener(e -> {
            String name=JOptionPane.showInputDialog("Nome do Arquivo:");
            if(name!=null && !name.isEmpty()){
                Texto z=new Texto(name,"");
            }
        });
    }

    public void addPasta(Categorias Pasta){
        JPanel pastapainel = new JPanel();
        pastapainel.setLayout(new BorderLayout());
        pastapainel.setBorder(BorderFactory.createTitledBorder(Pasta.getNome()));
        JPanel capitulos= new JPanel();
        capitulos.setLayout(new BoxLayout(capitulos, BoxLayout.Y_AXIS));
        JButton addTexto= new JButton("+ Capitulo");
        addTexto.addActionListener(e -> {
            String nome=JOptionPane.showInputDialog("Nome do Texto:");

            if(nome != null && !nome.isEmpty()){
                Texto novo=new Texto(nome,"");
                Pasta.addTexto(novo);
                JButton botao=criarBotao(novo);
                capitulos.add(botao);
                capitulos.revalidate();
                capitulos.repaint();
            }
        });
        capitulos.add(addTexto);
        for (Texto x: Pasta.getTextos()){
            capitulos.add(criarBotao(x));
        }
        pastapainel.add(capitulos, BorderLayout.CENTER);
        listaPasta.add(pastapainel);
        listaPasta.revalidate();
    }

    private JButton criarBotao(Texto archive){
        JButton bt= new JButton(archive.getTitle());
        bt.addActionListener(e -> {
            System.out.println("O seguinte arquivo foi aberto:"+archive.getTitle());
            TextEditor chapter = new TextEditor(archive);
            //chapter.setDoc
            trocada(chapter);
        });
        return bt;
    }
}

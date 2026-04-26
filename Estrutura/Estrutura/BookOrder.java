package Estrutura;

import Componente.Pasta;
import Componente.Projeto;
import Componente.Arquivo;
import Controle.Files;
import javax.swing.*;
import java.awt.*;
import java.util.List;

public class BookOrder extends Base{
    Projeto biblios;
    JPanel listaPasta;
    //Janela que mostra as Categorias e Arquivos de um Livro
    public BookOrder(Projeto book){
        this.biblios=book;
        montarCabecalho("Voltar",book.getName());
        montarConteudo();
        montarRodape();
        loadpasta();
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
    }

    private void loadpasta(){
        java.util.List<String> nomes= Files.listaPasta(biblios.getName());
        for(String name: nomes){
            Pasta pst = new Pasta(name);
            addPasta(pst);
        }
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
                String caminho=biblios.getName()+"/"+nome;
                Files.criarPasta(caminho);
                Pasta pst=new Pasta(nome);
                addPasta(pst);
            }
        });
    }

    public void addPasta(Pasta Pasta){
        JPanel pastapainel = new JPanel();
        pastapainel.setLayout(new BorderLayout());
        pastapainel.setBorder(BorderFactory.createTitledBorder(Pasta.getNome()));
        JPanel capitulos= new JPanel();
        capitulos.setLayout(new BoxLayout(capitulos, BoxLayout.X_AXIS));
        JButton addTexto= new JButton("+ Capitulo");
        addTexto.addActionListener(e -> {
            String nome=JOptionPane.showInputDialog("Nome do Texto:");

            if(nome != null && !nome.isEmpty()){
                String caminho=biblios.getName()+"/"+Pasta.getNome()+"/"+nome+".txt";
                Files.salvarArquivo(caminho,"");

                Arquivo novo=new Arquivo(nome,"");
                novo.setCaminho(caminho);
                Pasta.addTexto(novo);
                JButton botao=criarBotao(novo);
                capitulos.add(botao);
                capitulos.revalidate();
                capitulos.repaint();
            }
        });
        capitulos.add(addTexto);
        List<String> arquivos = Files.listaArquivo(biblios.getName()+"/"+Pasta.getNome());
        for(String name : arquivos){
            String clean=name.replace(".txt","");
            Arquivo ark = new Arquivo(clean,"");
            ark.setCaminho(biblios.getName()+"/"+Pasta.getNome()+"/"+name);
            capitulos.add(criarBotao(ark));
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

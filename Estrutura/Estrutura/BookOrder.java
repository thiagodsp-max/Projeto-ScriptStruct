package Estrutura;

import Componente.Pasta;
import Componente.Projeto;
import Componente.Arquivo;
import Controle.Files;
import javax.swing.*;
import java.awt.*;
import java.io.File;
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
        trocada(new Collections());
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
        //Botão para criar nova categoria
        JButton pasta = new JButton("Nova Pasta");
        rodape.add(pasta);
        pasta.addActionListener(e->{
            String nome=JOptionPane.showInputDialog("Nome da Categoria");
            if(nome != null && !nome.isEmpty()){
                String caminho = biblios.getName() + "/" + nome;
                //String fullPath = Files.BASE_PATH + caminho;
                //JOptionPane.showMessageDialog(this,"Criando pasta em:\n" + fullPath);
                Files.criarPasta(caminho);
                Pasta pst=new Pasta(nome);
                addPasta(pst);
            }
        });
    }
    public void addPasta(Pasta pasta){
        JPanel bloco = new JPanel();
        bloco.setLayout(new BorderLayout());
        JPanel header = new JPanel(new BorderLayout());
        JPanel action = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JLabel nome = new JLabel(pasta.getNome());
        JButton rename = new JButton("F12");

        final String nomeOriginal=pasta.getNome();
        final String nomePastaAtual=pasta.getNome();

        rename.addActionListener(e -> {
            String nova = JOptionPane.showInputDialog("Novo Nome:", nomeOriginal);
            if(nova != null && !nova.isEmpty()){
                String oldPath = biblios.getName() + "/" + nomeOriginal;
                String newPath = biblios.getName() + "/" + nova;
                //JOptionPane.showMessageDialog(this,"Renomeando pasta:\n" +Files.BASE_PATH + oldPath +"\nPARA:\n" +Files.BASE_PATH + newPat);
                Files.renomear(oldPath, newPath);
                pasta.setNome(nova);
                nome.setText(nova);
            }
        });
        rename.setMargin(new Insets(2,5,2,5));

        JButton remove = new JButton("Deletar");
        remove.addActionListener(e -> {
            int confirm = JOptionPane.showConfirmDialog(null, "Excluir a pasta ?");
            if(confirm == JOptionPane.YES_OPTION){
                File pst = new File(Files.BASE_PATH + biblios.getName()+"/"+nomePastaAtual);
                //JOptionPane.showMessageDialog(this,"Deletando pasta:\n" + pst.getAbsolutePath());
                deletaPasta(pst);
                listaPasta.remove(bloco);
                listaPasta.revalidate();
                listaPasta.repaint();
            }
        });
        remove.setMargin(new Insets(2,5,2,5));
        JPanel capitulos = new JPanel();
        JButton addTexto = new JButton("+ Capítulo");

        addTexto.addActionListener(e -> {
            String nomeCap = JOptionPane.showInputDialog("Nome do Texto:");
            if(nomeCap != null && !nomeCap.isEmpty()){
                String caminho = biblios.getName()+"/"+nomePastaAtual+"/"+nomeCap+".txt";
                String[] opcoes = {"Livre", "Personagem", "Ambiente"};

                String tipo = (String) JOptionPane.showInputDialog(
                        this,
                        "Escolha o tipo de arquivo:",
                        "Tipo",
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        opcoes,
                        opcoes[0]
                );
                String content;
                if ("Personagem".equals(tipo)) {
                    content = modelopersonagem();
                }
                else if ("Ambiente".equals(tipo)) {
                    content = modeloambiente();
                }
                else {
                    content = "";
                }
                //String fullPath = Files.BASE_PATH + caminho;
               // JOptionPane.showMessageDialog(this,"Criando arquivo em:\n" + fullPath);
                Files.salvarArquivo(caminho, content);

                Arquivo novo = new Arquivo(nomeCap, content);
                novo.setCaminho(caminho);

                capitulos.add(criarBotao(novo));
                capitulos.revalidate();
            }
        });
        bloco.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        header.setBorder(BorderFactory.createEmptyBorder(2, 5, 2, 5));
        action.add(addTexto);
        action.add(rename);
        action.add(remove);
        header.add(nome,BorderLayout.WEST);
        header.add(action, BorderLayout.EAST);
        // 🔹 Lista de capítulos
        capitulos.setLayout(new BoxLayout(capitulos, BoxLayout.Y_AXIS));
        List<String> arquivos = Files.listaArquivo(biblios.getName()+"/"+pasta.getNome());
        for(String name : arquivos){
            String clean = name.replace(".txt","");
            Arquivo ark = new Arquivo(clean,"");
            ark.setCaminho(biblios.getName()+"/"+pasta.getNome()+"/"+name);
            capitulos.add(criarBotao(ark));
        }

        bloco.add(header, BorderLayout.NORTH);
        bloco.add(capitulos, BorderLayout.CENTER);
        listaPasta.add(bloco);
        listaPasta.revalidate();
    }
    private JPanel criarBotao(Arquivo archive){
        JPanel linha = new JPanel(new BorderLayout());
        linha.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        linha.setPreferredSize(new Dimension(0, 40));
        linha.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));

        final String CaminhoOriginal=archive.getCaminho();
        JButton abrir = new JButton(archive.getTitle());
        abrir.addActionListener(e -> {
            TextEditor chapter = new TextEditor(archive,this);
            trocada(chapter);
        });

        JPanel act = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton rename = new JButton("F12");
        rename.addActionListener(e->{
            String nova = JOptionPane.showInputDialog("Novo titulo", archive.getTitle());
            if(nova != null && !nova.isEmpty()){
                //String old = archive.getCaminho();
                String pst = CaminhoOriginal.substring(0, CaminhoOriginal.lastIndexOf("/"));
                String path = pst + "/" + nova + ".txt";
                //JOptionPane.showMessageDialog(this,"Renomeando arquivo:\n" + Files.BASE_PATH + old + "\nPARA:\n" + Files.BASE_PATH + path);
                Files.renomear(CaminhoOriginal, path);

                archive.setCaminho(path);
                archive.setTitle(nova);
                abrir.setText(nova);
            }
        });

        JButton delete = new JButton("Deletar");
        delete.addActionListener(e -> {
            int confirm = JOptionPane.showConfirmDialog(null, "Excluir arquivo?");
            if(confirm == JOptionPane.YES_OPTION){
                File f = new File(Files.BASE_PATH + archive.getCaminho());
                //JOptionPane.showMessageDialog(this,"Deletando arquivo:\n" + f.getAbsolutePath());
                f.delete();
                linha.getParent().remove(linha);
                linha.getParent().revalidate();
                linha.getParent().repaint();
            }
        });

        act.add(rename);
        act.add(delete);
        linha.add(abrir, BorderLayout.CENTER);
        linha.add(act, BorderLayout.EAST);
        return linha;
    }
    private void deletaPasta(File dir){
        if(dir.isDirectory()){
            for(File f:dir.listFiles()){
                deletaPasta(f);
            }
        }
        dir.delete();
    }
    private String modelopersonagem(){
        return """
    Nome:
    Idade:
    Aparência:

    Personalidade:

    História:

    Motivação:

    Relacionamentos:
    """;
    }
    private String modeloambiente(){
        return """
    Nome do Local:

    Descrição:

    Clima:

    História:

    Importância:

    Detalhes:
    """;
    }
}

package Estrutura;

import Componente.Arquivo;
import Controle.Files;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.Document;
import java.awt.*;

public class TextEditor extends Base{
    //Atributos importantes
    JTextArea textArea;
    JScrollPane scrollPane;
    JSpinner fontsize;
    JComboBox fontype;
    Arquivo txt;
    BookOrder sumario;
    boolean alterado = false;
    JLabel status = new JLabel("Palavras: 0");

    //Janela orientada para permitir a Escrita do usuário

    public TextEditor(Arquivo txt, BookOrder antes){
        this.txt=txt;
        this.sumario=antes;
        montarCabecalho("Voltar",txt.getTitle());
        montarConteudo();
        montarRodape();
    }

    @Override
    protected void changetela() {
        trocada(sumario);
    }

    @Override
    protected void montarConteudo() {
        conteudo.setLayout(new BorderLayout());
        textArea = new JTextArea();
        textArea.setOpaque(true);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);

        Document doc = textArea.getDocument();

        doc.addDocumentListener(new DocumentListener() {

            private void atualizarTudo() {
                alterado = true;

                String texto = textArea.getText().trim();
                int palavras = texto.isEmpty() ? 0 : texto.split("\\s+").length;

                status.setText("Palavras: " + palavras);
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                atualizarTudo();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                atualizarTudo();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                atualizarTudo();
            }
        });

        if(txt != null){
            //textArea.setText(txt.getStory());
            if(txt.getCaminho() != null){
                String content=Files.leitura(txt.getCaminho());
                textArea.setText(content);
            }else{
                textArea.setText(txt.getStory());
            }
        }
        textArea.setFont(new Font("Arial",Font.PLAIN,20));
        scrollPane = new JScrollPane(textArea);
        conteudo.add(scrollPane,BorderLayout.CENTER);
    }

    @Override
    protected void montarRodape() {
        fontsize= new JSpinner();
        fontsize.setPreferredSize(new Dimension(50,25));
        fontsize.setValue(20);
        fontsize.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                textArea.setFont(new Font(textArea.getFont().getFamily(),Font.PLAIN,(int) fontsize.getValue()));
            }
        });
        String[] fontes= GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();

        fontype= new JComboBox(fontes);
        fontype.addActionListener(e-> {
            String selected= (String)fontype.getSelectedItem();
            Font atual=textArea.getFont();
            textArea.setFont(new Font(selected,atual.getStyle(),atual.getSize()));
        });
        fontype.setSelectedItem("Arial");
        JPopupMenu menu = new JPopupMenu();
        JMenuItem salve=new JMenuItem("Salvar");
        JMenuItem abre=new JMenuItem("Abrir");
        menu.add(salve);
        menu.add(abre);
        JButton opt=new JButton("Arquivo");

        opt.addActionListener(e -> {
            menu.show(opt,0,opt.getHeight());
        });

        salve.addActionListener(e-> {
            //Arquivo doc=getDoc();
            txt.setStory(textArea.getText());
            txt.setTitle(titulo.getText());

            if(txt.getCaminho() != null){
                //Files.salvarArquivo(txt.getCaminho(),txt.getStory());
                Files.salvarArquivo(txt.getCaminho(),textArea.getText());
            }
            //Files.salvarArquivo(txt.getCaminho(),txt.getStory());
            System.out.println("Sua narrativa foi salva em: "+txt.getCaminho());
            //System.out.println("Título:" + txt.getTitle());
            //System.out.println("História:" + txt.getStory());
        });
        abre.addActionListener(e->{
            JFileChooser seletor=new JFileChooser();
            int result=seletor.showOpenDialog(this);

            if(result == JFileChooser.APPROVE_OPTION){
                java.io.File file = seletor.getSelectedFile();
                String content=Files.leitura(file.getPath());
                textArea.setText(content);
                txt.setCaminho(file.getPath());
                txt.setTitle(file.getName());
                titulo.setText(file.getName());
                System.out.println("O arquivo "+file.getPath()+" foi carregado!!");
            }
        });
        rodape.add(fontype);
        rodape.add(fontsize);
        rodape.add(opt);
        status = new JLabel("Palavras: 0");
        rodape.add(status);
    }

    public Arquivo getDoc(){
        String big=titulo.getText();
        String sma=textArea.getText();
        return new Arquivo(big,sma);
    }

}

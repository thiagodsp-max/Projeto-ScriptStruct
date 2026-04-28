package Estrutura;

import Componente.Arquivo;
import Controle.Files;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.*;
import javax.swing.text.rtf.RTFEditorKit;
import java.awt.*;
import java.io.*;

public class TextEditor extends Base{
    //Atributos importantes
    JTextPane textArea;
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
        textArea = new JTextPane();
        textArea.setOpaque(true);

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

        if (txt != null && txt.getCaminho() != null) {
            try {
                File file = new File(Files.BASE_PATH + txt.getCaminho());

                BufferedReader reader = new BufferedReader(new FileReader(file));
                String primeiraLinha = reader.readLine();
                reader.close();

                FileInputStream input = new FileInputStream(file);

                if (primeiraLinha != null && primeiraLinha.startsWith("{\\rtf")) {
                    // 📄 É RTF
                    RTFEditorKit kit = new RTFEditorKit();
                    kit.read(input, textArea.getDocument(), 0);
                } else {
                    // 📝 É texto simples
                    String content = Files.leitura(txt.getCaminho());
                    textArea.setText(content);
                }
                input.close();

            } catch (Exception e) {
                e.printStackTrace();
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

        JButton opt=new JButton("Salvar");
        opt.addActionListener(e-> {
            try{
                txt.setTitle(titulo.getText());
                if(txt.getCaminho() != null){
                    FileOutputStream output= new FileOutputStream(Files.BASE_PATH+txt.getCaminho());
                    RTFEditorKit kit=new RTFEditorKit();
                    kit.write(output,textArea.getDocument(),0,textArea.getDocument().getLength());
                    output.close();
                }
                System.out.println("Sua narrativa foi salva em:"+txt.getCaminho());
            }catch (Exception ex){
                ex.printStackTrace();
            }
        });

        JButton bold = new JButton("B");
        JButton italic = new JButton("I");
        JButton under = new JButton("U");
        bold.addActionListener(e -> {
            StyledDocument doc = textArea.getStyledDocument();
            int start = textArea.getSelectionStart();
            int end = textArea.getSelectionEnd();

            if (start != end) {
                Style style = textArea.addStyle("Bold", null);
                StyleConstants.setBold(style, true);
                doc.setCharacterAttributes(start, end - start, style, false);
            }
        });

        italic.addActionListener(e -> {
            StyledDocument doc = textArea.getStyledDocument();
            int start = textArea.getSelectionStart();
            int end = textArea.getSelectionEnd();

            if (start != end) {
                Style style = textArea.addStyle("Italic", null);
                StyleConstants.setItalic(style, true);
                doc.setCharacterAttributes(start, end - start, style, false);
            }
        });
        under.addActionListener(e -> {
            StyledDocument doc = textArea.getStyledDocument();
            int start = textArea.getSelectionStart();
            int end = textArea.getSelectionEnd();

            if (start != end) {
                Style style = textArea.addStyle("Underline", null);
                StyleConstants.setUnderline(style, true);
                doc.setCharacterAttributes(start, end - start, style, false);
            }
        });
        //Alinhamento
        JPopupMenu linha = new JPopupMenu();
        JMenuItem center=new JMenuItem("Centro");
        JMenuItem left=new JMenuItem("Esquerda");
        JMenuItem right=new JMenuItem("Direita");
        linha.add(right);
        linha.add(center);
        linha.add(left);
        JButton lin=new JButton("Alinhar");
        lin.addActionListener(e -> {
            linha.show(lin,0,lin.getHeight());
        });
        left.addActionListener(e -> alinhar(StyleConstants.ALIGN_LEFT));
        center.addActionListener(e -> alinhar(StyleConstants.ALIGN_CENTER));
        right.addActionListener(e -> alinhar(StyleConstants.ALIGN_RIGHT));
        rodape.add(fontype);
        rodape.add(fontsize);
        rodape.add(opt);
        rodape.add(bold);
        rodape.add(italic);
        rodape.add(under);
        rodape.add(lin);
        status = new JLabel("Palavras: 0");
        rodape.add(status);
    }

    private void alinhar(int alinhamento) {
        StyledDocument doc = textArea.getStyledDocument();
        SimpleAttributeSet attr = new SimpleAttributeSet();
        StyleConstants.setAlignment(attr, alinhamento);

        int start = textArea.getSelectionStart();
        int end = textArea.getSelectionEnd();

        doc.setParagraphAttributes(start, end - start, attr, false);
    }
}

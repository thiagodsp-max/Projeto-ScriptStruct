package Estrutura;

import Componente.Arquivo;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;

public class TextEditor extends Base{
    //Atributos importantes
    JTextArea textArea;
    //JTextField titulo;
    JScrollPane scrollPane;
    JSpinner fontsize;
    JComboBox fontype;
    Arquivo txt;

    private Base previous;
    BookOrder sumario;
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
        if(txt != null){
            textArea.setText(txt.getStory());
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
        JMenuItem sai=new JMenuItem("Sair");
        menu.add(salve);
        menu.add(abre);
        menu.add(sai);
        JButton opt=new JButton("Arquivo");

        opt.addActionListener(e -> {
            menu.show(opt,0,opt.getHeight());
        });

        salve.addActionListener(e-> {
            Arquivo doc=getDoc();
            txt.setStory(textArea.getText());
            txt.setTitle(titulo.getText());
            System.out.println("Sua narrativa foi salva!!");
            System.out.println("Título:" + txt.getTitle());
            System.out.println("História:" + txt.getStory());
        });

        rodape.add(fontype);
        rodape.add(fontsize);
        rodape.add(opt);
    }

    public Arquivo getDoc(){
        String big=titulo.getText();
        String sma=textArea.getText();
        return new Arquivo(big,sma);
    }

}

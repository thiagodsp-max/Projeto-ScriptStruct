//Classe para facilitar o armazenamento dos textos escritos, em outras classes
import javax.swing.*;
import java.awt.*;

public class Editor extends JPanel{
    JTextArea textArea;
    JScrollPane scrollPane;

    public Editor(){
        this.setLayout(new BorderLayout());
        textArea = new JTextArea();
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setFont(new Font("Arial",Font.PLAIN,20));
        //
        scrollPane = new JScrollPane(textArea);
        //scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        this.add(scrollPane,BorderLayout.CENTER);
        this.setVisible(true);
    }

    public String getTexto(){
        return textArea.getText();
    }

}

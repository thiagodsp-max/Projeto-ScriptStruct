//Baseado no conceito Tela
import javax.swing.*;
import java.awt.*;

//Aqui é para representar os blocos que compõem a janela, a menor
//unidade na composição das imagens presentes na Tela
public abstract class Area extends JPanel {
    protected JPanel cabecalho;
    protected JPanel conteudo;
    protected JPanel rodape;

    public Area() {
        setLayout(new BorderLayout());

        cabecalho = new JPanel();
        conteudo = new JPanel();
        rodape = new JPanel();

        add(cabecalho, BorderLayout.NORTH);
        add(conteudo, BorderLayout.CENTER);
        add(rodape, BorderLayout.SOUTH);

        montarCabecalho();
        montarConteudo();
        montarRodape();
    }
    //Parâmetros para definir opções e títulos
    protected abstract void montarCabecalho();
    //Parâmetros para definir o tipo de objeto presente
    protected abstract void montarConteudo();
    //Parâmetros para definir opções
    protected abstract void montarRodape();
}

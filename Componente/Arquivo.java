//Baseado no Conceito de Arquivos de Texto

public class Arquivo {
    //Atributos
    String titulo;
    String texto;

    //Construtor
    public void Arquivo(String nome){
        this.titulo=nome;
    }

    //Outros Métodos
    public void editar(Editor lore){
        //Agora o usuario armazena o que ele escreveu no JTextArea (em desenvolvimento)
        this.texto=lore.getTexto();
    }
  
    public void exibir(){
        System.out.printf(titulo);
        System.out.printf(texto);
    }
}

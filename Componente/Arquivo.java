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
    public void editar(String lore){
        this.texto=lore;
        //Posterior substituição por um Método que permite armazenar o que o usuário escrever no JTextArea
    }
  
    public void exibir(){
        System.out.printf(titulo);
        System.out.printf(texto);
    }
}

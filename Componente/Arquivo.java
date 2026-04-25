package Componente;

public class Arquivo {
    //São os arquivos de Texto, aqui iremos definir que eles recebem
    String title;
    String story;

    //Criando um Novo Arquivo
    public Arquivo(String nome, String letra){
        this.title=nome;
        this.story=letra;
    }

    public String getTitle(){
        return title;
    }

    public String getStory(){
        return story;
    }

    public void setTitle(String titulo){
        this.title=titulo;
    }

    public void setStory(String letra){
        this.story=letra;
    }
}

package Beta;

//Baseado no Conceito de Anotações

public class Notas {
    //Atributos
    //int id;
    String nome;
    String descricao;
    boolean feito;

    //Construtor Inicial
    public Notas(String title){
        //this.id=uni;
        this.nome=title;
        this.descricao="";
        this.feito=false;
    }

    //Métodos Padrão (Gets e is)
    public String getName(){
        return nome;
    }
    public String getDetails(){
        return descricao;
    }
    public void setDetails(String texto){
        descricao=texto;
    }
    //Métodos para permitir Interação

    public Boolean getstatus(){
        return feito;
    }
    public void setstatus(){
        if(feito==true){
            feito=false;
        }
        else {
            feito = true;
        }
    }
}

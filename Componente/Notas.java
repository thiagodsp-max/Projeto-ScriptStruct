package Componente;

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
    public void setName(String name){
        nome=name;
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

    public String formato(){
        return nome+"|"+descricao+"|"+feito;
    }
    public static Notas linhas(String linha){
        String[] parte=linha.split("\\|");
        Notas no=new Notas(parte[0]);
        if(parte.length>1)no.setDetails(parte[1]);
        if(parte.length>2 && parte[2].equals("true"))no.setstatus();
        return no;
    }
}

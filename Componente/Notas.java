//Baseado no Conceito de Anotações

public class Notas {
    //Atributos
    int id;
    String nome;
    String descricao;
    boolean box;//Para indicar os status da anotação
    
    //Construtor
    public Notas(int uni,String title){
        this.id=uni;
        this.nome=title;
        this.box=false;//Inicializa a tarefa como não concluída
    }

    //Outros métodos
    public void editarnota(Editor change){
        //Para melhor detalhar aquela anotação
        this.descricao=change.getTexto();
    }
  
    //Método a ser alterado para compreender fenômenos on click()
    public void concluir(){
        box=true;//Troca os status da tarefa
    }
}

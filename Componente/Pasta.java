//Baseado no Conceito de Categorias de Texto ou Pasta de Arquivos

public class Pasta {
    //Atributo
    String nome;
    Arquivo[] chapters;//Precisa inicializar vazio, para ser preenchido com o tempo
    int count;//Permite delimitar o tamanho dos Capítulos dentro da Categoria
    
    //Construtor()
    public void Pasta(String name){
       this.nome=name;
       this.count=0;//A pasta está vazia
    }
    
    //Outros métodos
    public void adicionar(Arquivo texto){
        //A ideia é colocar o Capítulo mais recente na última posição livre, para evitar que os Arquivos se sobreponham
        chapters[count]=texto;
        this.count++;
    }
}

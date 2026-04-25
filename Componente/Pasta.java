package Componente;

import java.util.ArrayList;
import java.util.List;

public class Pasta {
    //Depende que já se tenha criado a classe de Texto
    //Equivalente à Pasta
    String nome;
    List<Arquivo> arquivos;

    public Pasta(String name){
        this.nome=name;
        this.arquivos=new ArrayList<>();
    }

    public String getNome(){
        return nome;
    }

    public List<Arquivo> getTextos(){
        return arquivos;
    }

    public void addTexto(Arquivo x){
        arquivos.add(x);
    }
}

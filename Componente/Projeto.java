package Componente;

import java.util.ArrayList;
import java.util.List;

public class Projeto {
    //O Equivalente de Projeto
    //Depende que já tenham sido criadas:
    //- Categorias
    //- Texto
    String name;
    List<Pasta> secao;
    //= new ArrayList<>();

    public Projeto(String name){
        this.name = name;
        this.secao = new ArrayList<>();
    }

    public String getName(){
        return name;
    }

    public List<Pasta> getSecao(){
        return secao;
    }

    public void addSecao(Pasta v){
        secao.add(v);
    }
}

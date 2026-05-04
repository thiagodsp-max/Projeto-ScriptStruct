package Componente;

import java.util.ArrayList;
import java.util.List;

public class Projeto {
    //Depende que já tenham sido criadas: Arquivo e Pasta
    String name;
    List<Pasta> secao;

    public Projeto(String name){
        this.name = name;
        this.secao = new ArrayList<>();
    }

    public String getName(){
        return name;
    }

    public void setName(String nova) {
        name=nova;
    }
}

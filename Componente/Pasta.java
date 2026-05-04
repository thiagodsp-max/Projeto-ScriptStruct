package Componente;

import java.util.ArrayList;
import java.util.List;

public class Pasta {
    //Depende que já se tenha criado a classe de Arquivo
    static String nome;
    static List<Arquivo> arquivos;

    public Pasta(String name){
        this.nome=name;
        this.arquivos=new ArrayList<>();
    }

    public static String getNome(){
        return nome;
    }

    public static void setNome(String title){
        nome=title;
    }

}

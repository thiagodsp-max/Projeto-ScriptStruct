package Componente;

import java.util.ArrayList;
import java.util.List;

public class Pasta {
    //Depende que já se tenha criado a classe de Texto
    //Equivalente à Pasta
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

    public static List<Arquivo> getTextos(){
        return arquivos;
    }

    public void addTexto(Arquivo x){
        arquivos.add(x);
    }
}

//Baseado no conceito Janela/Tela

import javax.swing.*;
//Uso de Bibliotecas que permitam desenvolver o App

public class Area extends JFrame{
    //Atributo
    int id;
    //As janelas precisam ter a capacidade de conter diferentes Objetos dentro de si
    //Implementar Subclasses para cada tipo de elemento, se assim for necessário
  
    //Construtor Geral
    public Area(String titulo){
        //Criando Janela:
        setTitle(titulo);
        setSize(600,500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //O Conteúdo abaixo do Título dependerá de qual Janela
        //Aplicar uma Função que possa sofrer Override pelas Sub-classes
    }
    //Outros Métodos
    //Função para transitar entre as telas
}

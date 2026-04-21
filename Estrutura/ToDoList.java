package Struct;

import Beta.Notas;

import javax.swing.*;
import java.awt.*;

public class ToDoList extends Base{

    //Essa classe nos permite implementar um espaço reservado só para as Anotações
    JPanel notes;
    @Override
    protected void montarCabecalho() {

        JLabel titulo = new JLabel("Lista de Anotações");
        cabecalho.add(titulo);
        JButton plus = new JButton("Config");
        cabecalho.add(plus);
    }

    @Override
    protected void montarConteudo() {
        //
        conteudo.setLayout(new BorderLayout());
        notes=new JPanel();
        notes.setLayout(new BoxLayout(notes,BoxLayout.Y_AXIS));
        JScrollPane scroll= new JScrollPane(notes);
        conteudo.add(scroll,BorderLayout.CENTER);
    }

    @Override
    protected void montarRodape() {
        JButton anotar= new JButton("Nova Anotação");
        rodape.add(anotar);

        anotar.addActionListener(e->{
            String name=JOptionPane.showInputDialog("Insira o título de sua anotação:");
            if(name != null && !name.isEmpty()){
                Notas tsk= new Notas(name);
                addNote(tsk);
            }
        });
    }

    public void addNote(Notas ideia){
        JPanel noteline = new JPanel();
        JCheckBox task= new JCheckBox(ideia.getName());
        task.setFocusable(ideia.getstatus());
        JButton edit= new JButton("Editar");
        edit.addActionListener(e->{
            String detail=JOptionPane.showInputDialog("Descreva a tarefa em questão");
            ideia.setDetails(detail);
        });
        JButton see= new JButton("Ver");
        see.addActionListener(e->{
            System.out.println(ideia.getName());
            System.out.println(ideia.getDetails());
            noteline.revalidate();
            noteline.repaint();
        });
        noteline.add(task);
        noteline.add(edit);
        noteline.add(see);
        notes.add(noteline);
        notes.revalidate();
    }
}

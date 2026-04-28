package Estrutura;

import Componente.Notas;
import Controle.Files;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ToDoList extends Base{

    JPanel notes;
    List<Notas> listas=new ArrayList<>();
    public ToDoList(){
        montarCabecalho("Coleção de Livros","Lista de Anotações");
        montarConteudo();
        montarRodape();
        listas=Files.loadNota();
        for(Notas n:listas){
            addNote(n);
        }
    }
    protected void changetela() {
        trocada(new Collections()); // padrão, pode sobrescrever
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
                listas.add(tsk);
                addNote(tsk);
                Files.saveNota(listas);
            }
        });
    }

    public void addNote(Notas ideia){
        JPanel noteline = new JPanel();
        JCheckBox task= new JCheckBox(ideia.getName());
        task.setSelected(ideia.getstatus());
        task.addActionListener(e->{
            ideia.setstatus();
            Files.saveNota(listas);
        });

        JButton edit= new JButton("Editar");
        edit.addActionListener(e->{
            String detail=JOptionPane.showInputDialog("Descreva a tarefa em questão",ideia.getDetails());
            if(detail != null){
                ideia.setDetails(detail);
                Files.saveNota(listas);
            }
        });
        JButton see= new JButton("Ver");
        see.addActionListener(e->{
            System.out.println(ideia.getName());
            System.out.println(ideia.getDetails());
            noteline.revalidate();
            noteline.repaint();
        });
        JButton rename = new JButton("F12");
        rename.addActionListener(e->{
            String nova=JOptionPane.showInputDialog("Novo Nome:",ideia.getName());
            if(nova!=null&&!nova.isEmpty()){
                ideia.setName(nova);
                task.setText(nova);
                Files.saveNota(listas);
            }
        });
        JButton delete = new JButton("Deletar");
        delete.addActionListener(e -> {
            int confirm = JOptionPane.showConfirmDialog(
                    null,
                    "Deseja excluir esta anotação?",
                    "Confirmar",
                    JOptionPane.YES_NO_OPTION
            );
            if (confirm == JOptionPane.YES_OPTION) {
                listas.remove(ideia);          // remove da lista
                notes.remove(noteline);        // remove da UI
                notes.revalidate();
                notes.repaint();
                Files.saveNota(listas);        // atualiza arquivo
            }
        });
        noteline.add(task);
        noteline.add(edit);
        noteline.add(see);
        noteline.add(rename);
        noteline.add(delete);
        notes.add(noteline);
        notes.revalidate();
    }
}

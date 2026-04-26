package Controle;

import Componente.Notas;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Files {
    static final String BASE_PATH="repositorio/";
    public static void criarPasta(String destino){
        File pasta = new File(BASE_PATH+destino);
        if(!pasta.exists()){
            pasta.mkdirs();
        }
    }

    public static void salvarArquivo(String destino, String texto){
        try(FileWriter writer = new FileWriter(BASE_PATH+destino)){
            writer.write(texto);
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public static String leitura(String destino){
        StringBuilder content= new StringBuilder();
        try(BufferedReader reader = new BufferedReader(new FileReader(BASE_PATH+destino))){
            String linha;
            while((linha = reader.readLine()) != null){
                content.append(linha).append("\n");
            }
        } catch (IOException e){
            e.printStackTrace();
        }
        return content.toString();
    }

    public static List<String> listaArquivo(String destino){
        List<String> arquivos = new ArrayList<>();
        File pasta = new File(BASE_PATH+destino);
        if(pasta.exists() && pasta.isDirectory()){
            for(File f: pasta.listFiles()){
                if(f.isFile()){
                    arquivos.add(f.getName());
                }
            }
        }
        return arquivos;
    }

    public static List<String> listaPasta(String destino){
        List<String> pastas=new ArrayList<>();
        File direc = new File(BASE_PATH+destino);
        if(!direc.exists()){
            direc.mkdirs();
        }
        if(direc.exists() && direc.isDirectory()){
            for(File f: direc.listFiles()){
                if(f.isDirectory()){
                    pastas.add(f.getName());
                }
            }
        }
        return pastas;
    }

    public static void saveNota(List<Notas> tarefas){
        criarPasta("");
        try(PrintWriter writer=new PrintWriter(new FileWriter(BASE_PATH+"notas.txt"))){
            for(Notas no: tarefas){
                writer.println(no.formato());
            }
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public static List<Notas> loadNota(){
        List<Notas> tasks= new ArrayList<>();
        File ark = new File(BASE_PATH+"notas.txt");
        if(!ark.exists()) return tasks;
        try(BufferedReader reader= new BufferedReader(new FileReader(ark))){
            String line;
            while((line = reader.readLine())!= null){
                tasks.add(Notas.linhas(line));
            }
        } catch (IOException e){
            e.printStackTrace();
        }
        return tasks;
    }
}

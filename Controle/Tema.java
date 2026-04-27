package Controle;

import java.awt.*;
import java.io.*;

public class Tema {
    public static Color bgPrimary = Color.WHITE;
    public static Color bgSecondary = new Color(230,230,230);
    public static Color bgButton = new Color(200,200,200);

    public static Color fgPrimary = Color.BLACK;
    public static Color fgSecondary = Color.DARK_GRAY;
    public static Color fgButton = Color.BLACK;

    private static final String PATH = "config.dat";

    public static void saving() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(PATH))) {
            oos.writeObject(bgPrimary);
            oos.writeObject(bgSecondary);
            oos.writeObject(bgButton);
            oos.writeObject(fgPrimary);
            oos.writeObject(fgSecondary);
            oos.writeObject(fgButton);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void loading() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(PATH))) {
            bgPrimary = (Color) ois.readObject();
            bgSecondary = (Color) ois.readObject();
            bgButton = (Color) ois.readObject();
            fgPrimary = (Color) ois.readObject();
            fgSecondary = (Color) ois.readObject();
            fgButton = (Color) ois.readObject();
        } catch (Exception e) {
            // primeira execução → ignora
        }
    }
}
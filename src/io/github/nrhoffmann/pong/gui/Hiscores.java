package io.github.nrhoffmann.pong.gui;

import java.io.*;
import java.util.ArrayList;

public class Hiscores {
    private final String PATH = System.getenv("HOME") + ".config/pong/scores.dat";

    private ArrayList<Element> hiscores = new ArrayList<>();

    public Hiscores(){
        System.out.println(PATH);
        load();
    }

    private void load(){
        FileInputStream fin = null;
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(PATH));
            try {
                hiscores = (ArrayList<Element>) ois.readObject();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            ois.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean add(Element element){
        //return true if score is successfully added to hiscores (and saved) and false otherwise.

        return true;
    }

    private void save(){
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(PATH));
            oos.writeObject(hiscores);
            oos.close();
        } catch (IOException e) {
            e.printStackTrace(); // todo do something better?
        }
    }

    class Element {
        private String name;
        private int score;
        Element(String name, int score){
            this.name = name;
            this.score = score;
        }
    }

}

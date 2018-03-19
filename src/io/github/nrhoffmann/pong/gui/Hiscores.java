package io.github.nrhoffmann.pong.gui;

import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class Hiscores {
    private final String SAVE_DIR = System.getenv("HOME") + "/.config/pong/";
    private final String FILE_NAME = "scores.dat";
    private final String SAVE_PATH = SAVE_DIR + FILE_NAME;

    private Element[] hiscores = new Element[10];

    private static Hiscores instance;

    public static Hiscores instance() {
        if (instance == null) {
            instance = new Hiscores();
        }

        return instance;
    }

    public int lowestExistingHiscore() {
        return hiscores[0].score;
    }

    private Hiscores() {
        try {
            Arrays.fill(hiscores,new Element());

            File p = new File(SAVE_PATH);
            if (!p.exists()) {
                if (new File(SAVE_DIR).mkdirs()) {
                    if (!p.createNewFile()) {
                        throw new RuntimeException("¯\\_(ツ)_/¯");
                    }
                }
            } else {
                load();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


        System.out.println(SAVE_DIR);
    }

    private void load() {
        FileInputStream fin = null;
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(SAVE_PATH));
            try {
                hiscores = (Element[]) ois.readObject();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            ois.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        ArrayList<Element> elts = new ArrayList<>(Arrays.asList(hiscores));
        for (Element elt:
             hiscores) {
            System.out.println(elt + String.format("[%d]", elts.indexOf(elt)));
        }
    }

    public boolean add(String initials, int score) {
        hiscores[0] = new Element(initials, score);

        Arrays.sort(hiscores, Comparator.comparingInt(score2 -> score2.score));

        save();

        //return true if score is successfully added to hiscores (and saved) and false otherwise.

        return true;
    }

    private void save() {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(SAVE_PATH));
            oos.writeObject(hiscores);
            oos.close();
        } catch (IOException e) {
            e.printStackTrace(); // todo do something better?
        }
    }

    public Element[] getElements() {
        return hiscores;
    }

    public static class Element implements Serializable {

        private static final long serialVersionUID = -6849794470754667719L;

        private String name;
        private int score;

        Element() {
            name = "Nobody";
            score = 0;
        }

        Element(String name, int score) {
            this.name = name;
            this.score = score;
        }

        public String getName() {
            return name;
        }

        public int getScore() {
            return score;
        }

        @Override
        public String toString() {
            return String.format("Element: { name: %s, score: %d }", name, score);
        }
    }

}

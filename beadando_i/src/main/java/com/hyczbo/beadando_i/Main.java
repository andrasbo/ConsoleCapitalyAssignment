package com.hyczbo.beadando_i;
import com.hyczbo.beadando_i.field.*;
import com.hyczbo.beadando_i.player.*;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author Andras Boromissza [hyczbo]
 */
public class Main {
    
    /**
     * Loads, plays and displays games from input files provided through args.
     * @param args
     */
    public static void main(String[] args) {
        System.out.println("""
                           Welcome to Capitaly!
                           Please provide game parameters through args!""");
        
        for (int i = 0; i < args.length; i++) {
            System.out.println("""
                               
                               
                               ====================================
                               Loading game no.""" + i);
            
            try {
                Player winner = loadGame(args[i]).play();
                
                System.out.println("\n\nwinner of game["+i+"]: "
                    + winner.getName()
                    + "["+winner.toString()+"]\n"
                    + "\twinner cash: "
                    + winner.getCash()+"\n"
                    + "\twinner estates: ");   

                ArrayList<Estate> estates = winner.getEstates();

                for(Estate h : estates) {
                    System.out.println("\t- "
                            +h.getName()
                            +"["+h.getLevel().toString()+"]");
                }                
            }
            catch(FileNotFoundException e) {
                System.out.println("ERROR: no such file!");
            }
            catch(InvalidGameParametersException e) {
                System.out.println("ERROR: Need atleast 2 players and 2 fields!");
            }        
            catch(Exception e) {
                System.out.println("ERROR: invalid input file!");
            }
        }
    }
    
    /**
     * Using scanner, tries to load either a random or a simulation game
     * from an input file.
     * @param filename
     * @return game object loaded from given file
     * @throws FileNotFoundException
     * @throws InvalidGameParametersException
     * @throws Exception
     */
    private static Game loadGame(String filename)
        throws FileNotFoundException, 
            InvalidGameParametersException,
            Exception {
        final ArrayList<Player> players = new ArrayList<>();
        final ArrayList<Field> board = new ArrayList<>();
        final ArrayList<Integer> diceThrows = new ArrayList<>();
        
        System.out.println("load begins");
        
        Scanner sc = new Scanner(
                        new BufferedReader(
                        new FileReader(filename)));

        System.out.println("scanner initiated");

        int n = sc.nextInt();
        for (int i = 0; i < n; i++) {
            Field field;
            String type = sc.next();
            switch(type) {
                case "E" -> {
                    field = new Estate(sc.next());
                    board.add(field);
                }
                case "T" -> {
                    field = new Tax(sc.nextInt());
                    board.add(field);
                }
                case "J" -> {
                    field = new Jackpot(sc.nextInt());
                    board.add(field);
                }
            }

        }
        n = sc.nextInt();
        for (int i = 0; i < n; i++) {
            Player player;
            String strategy = sc.next();
            switch(strategy) {
                case "G" -> {
                    player = new Greedy(sc.next());
                    players.add(player);
                }
                case "C" -> {
                    player = new Careful(sc.next());
                    players.add(player);
                }
                case "T" -> {
                    player = new Tactical(sc.next());
                    players.add(player);
                }
            }
        }
        while (sc.hasNextInt()) {
            diceThrows.add(sc.nextInt());
        }

        System.out.println("scanning successul");

        if (!diceThrows.isEmpty()) {
            return new GameSimulation(players, board, diceThrows);
        }
        else {return new GameRandom(players, board);}
    }
}

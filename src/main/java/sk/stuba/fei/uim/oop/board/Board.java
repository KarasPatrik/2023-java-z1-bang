package sk.stuba.fei.uim.oop.board;

import sk.stuba.fei.uim.oop.cards.*;
import sk.stuba.fei.uim.oop.player.Player;
import sk.stuba.fei.uim.oop.utility.ZKlavesnice;

import java.util.ArrayList;
import java.util.Collections;

public class Board {
    private ArrayList<Player> players;
    private int currentPlayer;
    private ArrayList<Card> mainDeck;
    private ArrayList<Card> discardDeck;

    public Board() {
        this.players = new ArrayList<Player>();
        this.addPlayers();
        this.currentPlayer = 0;

        this.mainDeck = new ArrayList<Card>();
        this.discardDeck = new ArrayList<Card>();
        this.createCards();
        Collections.shuffle(this.mainDeck);
    }

    public ArrayList<Card> getMainDeck() {
        return mainDeck;
    }

    public ArrayList<Card> getDiscardDeck() {
        return discardDeck;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public int getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(int currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    private void createCards() {
        for (int i = 0; i < 30; i++) {
            this.mainDeck.add(new Bang());
        }
        for (int i = 0; i < 15; i++) {
            this.mainDeck.add(new Missed());
        }
        for (int i = 0; i < 8; i++) {
            this.mainDeck.add(new Beer());
        }
        for (int i = 0; i < 6; i++) {
            this.mainDeck.add(new CatBalou());
        }
        for (int i = 0; i < 4; i++) {
            this.mainDeck.add(new Stagecoach());
        }
        this.mainDeck.add(new Indians());
        this.mainDeck.add(new Indians());
        this.mainDeck.add(new Barrel());
        this.mainDeck.add(new Barrel());
        this.mainDeck.add(new Dynamite());
        this.mainDeck.add(new Prison());
        this.mainDeck.add(new Prison());
        this.mainDeck.add(new Prison());
    }

    public void moveCards() {
        this.mainDeck.addAll(this.discardDeck);
        Collections.shuffle(this.mainDeck);
        this.discardDeck.clear();
        System.out.println("MainDeck is empty, adding " + this.mainDeck.size() + " cards from discardDeck");
        System.out.println("Shuffling cards");
    }

    private void addPlayers() {
        int numberOfPlayers = 0;
        while (numberOfPlayers < 2 || numberOfPlayers > 4) {
            numberOfPlayers = ZKlavesnice.readInt("Select number of players (2-4) : ");
            if (numberOfPlayers < 2 || numberOfPlayers > 4) {
                System.out.println("Selected number must be from 2 to 4, try again");
            }
        }
        for (int i = 0; i < numberOfPlayers; i++) {
            this.players.add(new Player(ZKlavesnice.readString("Name for player " + (i + 1) + " : "), this));
        }
    }

    public void goesNext() {
        this.currentPlayer++;
        this.currentPlayer %= this.getPlayers().size();
    }
}

package sk.stuba.fei.uim.oop.player;

import sk.stuba.fei.uim.oop.board.Board;
import sk.stuba.fei.uim.oop.cards.*;

import java.util.ArrayList;

public class Player {

    private final String name;
    private int lives;
    private Board board;
    private ArrayList<Card> cardsOnHand;
    private ArrayList<BlueCard> cardsOnTable;

    public Player(String name, Board board) {
        this.name = name;
        this.board = board;
        this.lives = 4;
        this.cardsOnHand = new ArrayList<Card>();
        this.cardsOnTable = new ArrayList<BlueCard>();
    }

    public int getLives() {
        return lives;
    }


    public String getName() {
        return name;
    }

    public ArrayList<Card> getCardsOnHand() {
        return cardsOnHand;
    }

    public ArrayList<BlueCard> getCardsOnTable() {
        return cardsOnTable;
    }

    public void takeCard(Board board) {
        if (!board.getMainDeck().isEmpty()) {
            this.cardsOnHand.add(board.getMainDeck().get(0));
            System.out.println(board.getMainDeck().get(0).getName() + " card added to your hand");
            board.getMainDeck().remove(0);
        } else {
            if (!board.getDiscardDeck().isEmpty()) {
                board.moveCards();
                this.cardsOnHand.add(board.getMainDeck().get(0));
                System.out.println(board.getMainDeck().get(0).getName() + " card added to your hand");
                board.getMainDeck().remove(0);
            } else {
                System.out.println("There are no more card to take");
            }

        }
    }

    public void addCards(Board board) {
        for (int i = 0; i < 4; i++) {
            if (!board.getMainDeck().isEmpty()) {
                this.cardsOnHand.add(board.getMainDeck().get(0));
                board.getMainDeck().remove(0);
            }
        }
    }

    public void addLives(int lives) {
        this.lives = this.lives + lives;
    }

    public void takeDamage(int lives) {
        this.lives = this.lives - lives;
        System.out.println("Player " + this.getName() + " took " + lives + " damage and now has " + this.getLives() + " lives");
        if (this.getLives() < 1) {
            System.out.println("Player " + this.getName() + " took fatal damage and dies");
            if (this.getIndex() <= board.getCurrentPlayer()) {
                board.setCurrentPlayer(board.getCurrentPlayer() - 1);
            }
            this.killPlayer();
        }
    }

    public void killPlayer() {
        this.discardCards();
        System.out.println("Player " + this.getName() + " leaves the game");
        this.board.getPlayers().remove(this);
    }

    public void discardCards() {
        System.out.println(this.getName() + "'s cards from hand and table are put to discardDeck");
        this.board.getDiscardDeck().addAll(this.cardsOnHand);
        this.cardsOnHand.clear();

        this.board.getDiscardDeck().addAll(this.cardsOnTable);
        this.cardsOnTable.clear();
    }

    public boolean haveBarrel() {
        if (this.cardsOnTable.size() == 0) {
            return false;
        }
        for (Card card : this.cardsOnTable) {
            if (card instanceof Barrel) {
                return true;
            }
        }
        return false;
    }

    public boolean havePrison() {
        if (this.cardsOnTable.size() == 0) {
            return false;
        }
        for (Card card : this.cardsOnTable) {
            if (card instanceof Prison) {
                return true;
            }
        }
        return false;
    }

    public boolean haveBang() {
        if (this.cardsOnHand.size() == 0) {
            return false;
        }
        for (Card card : this.cardsOnHand) {
            if (card instanceof Bang) {
                return true;
            }
        }
        return false;
    }

    public void playBangToDefense() {
        for (Card card : this.cardsOnHand) {
            if (card instanceof Bang) {
                ((Bang) card).autoplay(this, board);
                break;
            }
        }
    }

    public boolean staysInPrison() {
        if (this.cardsOnTable.size() == 0) {
            return false;
        }
        for (BlueCard card : this.cardsOnTable) {
            if (card instanceof Prison) {
                return card.checkEffect(this, board);
            }
        }
        return false;
    }

    public void checkDynamite() {
        if (this.cardsOnTable.size() == 0) {
            return;
        }
        for (BlueCard card : this.cardsOnTable) {
            if (card instanceof Dynamite) {
                card.checkEffect(this, this.board);
                return;
            }
        }
    }

    public boolean haveMissed() {
        if (this.cardsOnHand.size() == 0) {
            return false;
        }
        for (Card card : this.cardsOnHand) {
            if (card instanceof Missed) {
                return true;
            }
        }
        return false;
    }

    public int getIndex() {
        for (int i = 0; i < board.getPlayers().size(); i++) {
            if (board.getPlayers().get(i) == this) {
                return i;
            }
        }
        return 0;
    }

    public void playMissed() {
        for (Card card : this.cardsOnHand) {
            if (card instanceof Missed) {
                ((Missed) card).autoplay(this, board);
                break;
            }
        }
    }

    public String discardRandomCardFromHand() {
        String nameOfCard;
        int cardToDiscard = chooseRandomNumber(this.cardsOnHand.size() - 1);
        board.getMainDeck().add(this.cardsOnHand.get(cardToDiscard));
        nameOfCard = this.cardsOnHand.get(cardToDiscard).getName();
        this.cardsOnHand.remove(cardToDiscard);
        return nameOfCard;
    }

    public String discardRandomCardFromTable() {
        String nameOfCard;
        int cardToDiscard = chooseRandomNumber(this.cardsOnTable.size() - 1);
        board.getMainDeck().add(this.cardsOnTable.get(cardToDiscard));
        nameOfCard = this.cardsOnTable.get(cardToDiscard).getName();
        this.cardsOnTable.remove(cardToDiscard);
        return nameOfCard;
    }

    public void printBlueCards() {
        System.out.print("Player has these blue cards in front of him :  ");
        for (int i = 0; i < this.getCardsOnTable().size(); i++) {
            System.out.print(this.getCardsOnTable().get(i).getName() + "  ");
        }
        System.out.println();
    }

    private int chooseRandomNumber(int max) {
        return (int) (Math.random() * (max + 1) + 0);
    }
}


package sk.stuba.fei.uim.oop.cards;

import sk.stuba.fei.uim.oop.board.Board;
import sk.stuba.fei.uim.oop.player.Player;
import sk.stuba.fei.uim.oop.utility.ZKlavesnice;

import java.util.ArrayList;

public abstract class Card {

    protected String name;

    public Card(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void play(Player player, Board board) {
    }

    public void discard(Player player, Board board) {
        board.getDiscardDeck().add(this);
        player.getCardsOnHand().remove(this);
    }

    protected Player chooseTarget(ArrayList<Player> players) {
        int target = -1;
        System.out.println("Choose number of player you want play the " + this.getName() + " card on (or 0 to cancel)");

        for (int i = 0; i < players.size(); i++) {
            System.out.println((i + 1) + ". --- " + players.get(i).getName());
        }
        while (target < 0 || target > players.size()) {
            target = ZKlavesnice.readInt("choose :");
            if (target < 0 || target > players.size()) {
                System.out.println("Select number from 1 to " + players.size() + " or 0 to cancel, try again");
            }
        }
        if (target == 0) {
            return null;
        } else {
            return players.get(target - 1);
        }
    }
}

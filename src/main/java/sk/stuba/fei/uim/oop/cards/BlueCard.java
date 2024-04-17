package sk.stuba.fei.uim.oop.cards;

import sk.stuba.fei.uim.oop.board.Board;
import sk.stuba.fei.uim.oop.player.Player;

import java.util.Random;

public abstract class BlueCard extends Card {
    protected int chance;
    protected Random rand;

    public BlueCard(String name) {
        super(name);
    }

    protected void play(Player player, Player target) {
        target.getCardsOnTable().add(this);
        player.getCardsOnHand().remove(this);
    }

    public boolean checkEffect(Player player, Board board) {
        return true;
    }
}

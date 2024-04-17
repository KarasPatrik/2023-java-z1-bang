package sk.stuba.fei.uim.oop.cards;

import sk.stuba.fei.uim.oop.board.Board;
import sk.stuba.fei.uim.oop.player.Player;

import java.util.Random;

public class Dynamite extends BlueCard {
    private static final String CARD_NAME = "Dynamite";

    public Dynamite() {
        super(CARD_NAME);
        chance = 8;
        rand = new Random();
    }

    public void play(Player player, Board board) {
        System.out.println("Dynamite added in front of you ");
        super.play(player, player);
    }

    public boolean checkEffect(Player player, Board board) {
        if (rand.nextInt(chance) == 0) {
            System.out.println("** DYNAMITE EXPLODED **");
            board.getDiscardDeck().add(this);
            player.getCardsOnTable().remove(this);
            player.takeDamage(3);
            return true;
        } else {
            System.out.println("tik-tok ... dynamite didn't explode, moving dynamite to previous player");
            int previousPlayer = ((board.getCurrentPlayer() + board.getPlayers().size() - 1) % board.getPlayers().size());
            board.getPlayers().get(previousPlayer).getCardsOnTable().add(this);
            player.getCardsOnTable().remove(this);
            return false;
        }

    }
}

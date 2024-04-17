package sk.stuba.fei.uim.oop.cards;

import sk.stuba.fei.uim.oop.board.Board;
import sk.stuba.fei.uim.oop.player.Player;

public class Beer extends BrownCard {
    private static final String CARD_NAME = "Beer";

    public Beer() {
        super(CARD_NAME);
    }

    public void play(Player player, Board board) {
        player.addLives(1);
        System.out.println("One life added, now you have " + player.getLives() + " lives.");
        super.play(player, board);
    }

}

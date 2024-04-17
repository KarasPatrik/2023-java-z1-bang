package sk.stuba.fei.uim.oop.cards;

import sk.stuba.fei.uim.oop.board.Board;
import sk.stuba.fei.uim.oop.player.Player;

public class Missed extends BrownCard {
    private static final String CARD_NAME = "Missed";

    public Missed() {
        super(CARD_NAME);
    }

    public void autoplay(Player player, Board board) {
        System.out.println("Player " + player.getName() + " played Missed against your Bang! card");
        super.play(player, board);
    }

    public void play(Player player, Board board) {
        System.out.println("You can play card Missed just as respond to someone's Bang!, not playable now.");
    }
}

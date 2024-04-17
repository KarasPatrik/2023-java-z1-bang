package sk.stuba.fei.uim.oop.cards;

import sk.stuba.fei.uim.oop.board.Board;
import sk.stuba.fei.uim.oop.player.Player;

public class Stagecoach extends BrownCard {
    private static final String CARD_NAME = "Stagecoach";

    public Stagecoach() {
        super(CARD_NAME);
    }

    public void play(Player player, Board board) {
        System.out.println("Stagecoach card played, you take two cards : ");
        player.takeCard(board);
        player.takeCard(board);
        super.play(player, board);
    }
}

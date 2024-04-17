package sk.stuba.fei.uim.oop.cards;

import sk.stuba.fei.uim.oop.board.Board;
import sk.stuba.fei.uim.oop.player.Player;

public class Barrel extends BlueCard {
    private static final String CARD_NAME = "Barrel";

    public Barrel() {
        super(CARD_NAME);
    }

    public void play(Player player, Board board) {

        if (!player.haveBarrel()) {
            System.out.println("Barrel added in front of you ");
            super.play(player, player);
        } else {
            System.out.println("You already have one Barrel in front of you, can't have more");
        }
    }
}

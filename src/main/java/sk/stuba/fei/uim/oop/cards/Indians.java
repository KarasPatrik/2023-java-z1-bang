package sk.stuba.fei.uim.oop.cards;

import sk.stuba.fei.uim.oop.board.Board;
import sk.stuba.fei.uim.oop.player.Player;

public class Indians extends BrownCard {
    private static final String CARD_NAME = "Indians";

    public Indians() {
        super(CARD_NAME);
    }

    public void play(Player player, Board board) {
        Player target;
        System.out.println("Player " + player.getName() + " played Indians, all other players need to play Bang! or take 1 damage");
        for (int i = board.getPlayers().size(); i > 0; i--) {
            target = board.getPlayers().get(i - 1);
            if (target != player) {
                if (target.haveBang()) {
                    target.playBangToDefense();
                } else {
                    target.takeDamage(1);
                }
            }
        }
        super.play(player, board);
    }
}

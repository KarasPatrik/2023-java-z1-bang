package sk.stuba.fei.uim.oop.cards;

import sk.stuba.fei.uim.oop.board.Board;
import sk.stuba.fei.uim.oop.player.Player;

import java.util.Random;

public class Bang extends BrownCard {
    private static final String CARD_NAME = "Bang!";
    private Random rand;

    public Bang() {
        super(CARD_NAME);
        rand = new Random();
    }

    public void play(Player player, Board board) {
        Player target = chooseTarget(board.getPlayers());
        while (target == player) {
            System.out.println("You can't play Bang! against yourself");
            target = chooseTarget(board.getPlayers());
        }
        if (target != null) {
            System.out.println("Player " + player.getName() + " played Bang! on player " + target.getName());
            if (target.haveBarrel()) {
                boolean miss = rand.nextInt(4) == 0;
                if (miss) {
                    System.out.println(target.getName() + " was saved by his Barrel");
                    super.play(player, board);
                    return;
                }
            }
            if (target.haveMissed()) {
                target.playMissed();
            } else {
                target.takeDamage(1);
            }
            super.play(player, board);
        }
    }

    public void autoplay(Player player, Board board) {
        System.out.println("Player " + player.getName() + " played Bang! against your Indians");
        super.play(player, board);
    }
}

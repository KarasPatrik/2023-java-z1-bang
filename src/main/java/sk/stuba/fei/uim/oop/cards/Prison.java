package sk.stuba.fei.uim.oop.cards;

import sk.stuba.fei.uim.oop.board.Board;
import sk.stuba.fei.uim.oop.player.Player;

import java.util.Random;

public class Prison extends BlueCard {
    private static final String CARD_NAME = "Prison";

    public Prison() {
        super(CARD_NAME);
        chance = 4;
        rand = new Random();
    }

    public void play(Player player, Board board) {
        Player target;
        target = chooseTarget(board.getPlayers());
        while (target == player) {
            System.out.println("You can't play Prison on yourself");
            target = chooseTarget(board.getPlayers());
        }
        while (target != null) {
            if (!target.havePrison()) {
                System.out.println("Prison added in front of player " + target.getName());
                super.play(player, target);
                break;
            } else {
                System.out.println("This player already have one Prison in front of him, can't add more");
                target = chooseTarget(board.getPlayers());
            }
        }
    }

    public boolean checkEffect(Player player, Board board) {
        if (rand.nextInt(chance) == 0) {
            System.out.println("** You escaped the prison, you can continue in your turn **");
            board.getDiscardDeck().add(this);
            player.getCardsOnTable().remove(this);
            return false;
        } else {
            System.out.println("** You didn't manage to escape the prison **");
            board.getDiscardDeck().add(this);
            player.getCardsOnTable().remove(this);
            return true;
        }
    }
}
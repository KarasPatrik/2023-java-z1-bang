package sk.stuba.fei.uim.oop.cards;

import sk.stuba.fei.uim.oop.board.Board;
import sk.stuba.fei.uim.oop.player.Player;
import sk.stuba.fei.uim.oop.utility.ZKlavesnice;


public class CatBalou extends BrownCard {
    private static final String CARD_NAME = "CatBalou";

    public CatBalou() {
        super(CARD_NAME);
    }

    public void play(Player player, Board board) {
        int want;
        String discardedCard;
        Player target = chooseTarget(board.getPlayers());
        while (target == player) {
            System.out.println("You can't play CatBalou on yourself, choose again");
            target = chooseTarget(board.getPlayers());
        }
        while (target != null) {
            if (target.getCardsOnHand().isEmpty() && target.getCardsOnTable().isEmpty()) {
                System.out.println("This player does not have any cards, choose another player");
                target = chooseTarget(board.getPlayers());
            } else if (!target.getCardsOnHand().isEmpty() && target.getCardsOnTable().isEmpty()) {
                System.out.println("This player has " + target.getCardsOnHand().size() + " cards in hand, and no active BlueCards");
                want = ZKlavesnice.readInt("do you want to discard 1 random card from his Hand ?    0 = NO     1 = YES");
                while (want > 1 || want < 0) {
                    want = ZKlavesnice.readInt("try again, 0 = NO, 1 = YES");
                }
                if (want == 1) {
                    discardedCard = target.discardRandomCardFromHand();
                    System.out.println(discardedCard + " card from " + target.getName() + "'s hand has been put to discardDeck");
                    super.play(player, board);
                    return;
                } else {
                    target = chooseTarget(board.getPlayers());
                }
            } else if (target.getCardsOnHand().isEmpty() && !target.getCardsOnTable().isEmpty()) {
                System.out.println("This player has " + target.getCardsOnTable().size() + " BlueCards, and no cards in hand");
                target.printBlueCards();
                want = ZKlavesnice.readInt("do you want to discard 1 random card from them ?    0 = NO     1 = YES");
                while (want > 1 || want < 0) {
                    want = ZKlavesnice.readInt("try again, 0 = NO, 1 = YES");
                }
                if (want == 1) {
                    discardedCard = target.discardRandomCardFromTable();
                    System.out.println(discardedCard + " card from " + target.getName() + "'s Table has been put to discardDeck");
                    super.play(player, board);
                    return;
                } else {
                    target = chooseTarget(board.getPlayers());
                }
            } else {
                System.out.println("This player has " + target.getCardsOnHand().size() + " cards in hand, and " + target.getCardsOnTable().size() + " BlueCards");
                target.printBlueCards();
                want = ZKlavesnice.readInt("do you want to discard 1 random card from Hand or Table ?   1 = Hand   2 = Table   (0 to cancel)");
                while (want > 2 || want < 0) {
                    want = ZKlavesnice.readInt("try again,   1 = Hand   2 = Table   (0 to cancel)");
                }
                if (want == 1) {
                    discardedCard = target.discardRandomCardFromHand();
                    System.out.println(discardedCard + " card from " + target.getName() + "'s hand has been put to discardDeck");
                    super.play(player, board);
                    return;
                } else if (want == 2) {
                    discardedCard = target.discardRandomCardFromTable();
                    System.out.println(discardedCard + " card from " + target.getName() + "'s Table has been put to discardDeck");
                    super.play(player, board);
                    return;
                } else {
                    target = chooseTarget(board.getPlayers());
                }
            }
        }
    }
}

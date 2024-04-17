package sk.stuba.fei.uim.oop.cards;

import sk.stuba.fei.uim.oop.board.Board;
import sk.stuba.fei.uim.oop.player.Player;

public abstract class BrownCard extends Card {
    public BrownCard(String name) {
        super(name);
    }

    public void play(Player player, Board board) {
        board.getDiscardDeck().add(this);
        player.getCardsOnHand().remove(this);
    }
}

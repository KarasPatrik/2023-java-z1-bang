package sk.stuba.fei.uim.oop.game;

import sk.stuba.fei.uim.oop.board.Board;
import sk.stuba.fei.uim.oop.player.Player;
import sk.stuba.fei.uim.oop.utility.ZKlavesnice;

public class BangGame {
    private Board board;

    public BangGame() {
        this.board = new Board();
        for (Player player : this.board.getPlayers()) {
            player.addCards(board);
        }
        this.startGame();
    }

    private void startGame() {
        while (this.board.getPlayers().size() > 1) {
            this.makeTurn(this.board.getPlayers().get(board.getCurrentPlayer()));
            board.goesNext();
        }
        System.out.println(" xxxxxxxxx ---- GAME ENDED ---- xxxxxxxxx ");
        System.out.println("Winner is player : " + board.getPlayers().get(0).getName());
    }

    private void makeTurn(Player player) {
        System.out.println("------   It's " + player.getName() + "'s turn   ------");
        boolean toContinue = this.checkBlueCards(player);
        if (toContinue) {
            player.takeCard(board);
            player.takeCard(board);
            System.out.println("You have " + player.getLives() + " lives and your cards are :");
            this.printCardsOnHand(player);
            this.playCards(player);
            if (board.getPlayers().size() == 1) {
                return;
            }
            System.out.println("You have " + player.getLives() + " lives and " + player.getCardsOnHand().size() + " cards on hand");
            if (player.getCardsOnHand().size() <= player.getLives()) {
                System.out.println("You don't need to discard any cards");
            } else {
                this.discardExtraCards(player);
            }
        }
    }

    private boolean checkBlueCards(Player player) {
        if (player.getCardsOnTable().isEmpty()) {
            System.out.println("Player " + player.getName() + " don't have any blue cards in front of him");
            return true;
        } else {
            player.printBlueCards();
            player.checkDynamite();
            if (!this.board.getPlayers().contains(player)) {
                return false;
            }
            if (player.havePrison()) {
                return !player.staysInPrison();
            }
        }
        return true;
    }

    private void discardExtraCards(Player player) {
        int cardIndex;
        do {
            System.out.println("You need to discard " + (player.getCardsOnHand().size() - player.getLives()) + " more cards");
            System.out.println("Now your cards are :");
            this.printCardsOnHand(player);
            cardIndex = ZKlavesnice.readInt("Select number of card you want to discard ");
            if (cardIndex < 1 || cardIndex > player.getCardsOnHand().size()) {
                System.out.println("Invalid card number, choose again");
            } else {
                player.getCardsOnHand().get(cardIndex - 1).discard(player, this.board);
            }


        } while (player.getCardsOnHand().size() > player.getLives());
    }

    private void playCards(Player player) {
        int cardIndex = ZKlavesnice.readInt("Select number of card you want to play (O for none) :");
        do {
            if (cardIndex < 0) {
                cardIndex = ZKlavesnice.readInt("You dont have negative numbers of cards, choose again :");
            } else if (cardIndex == 0) {
                break;
            } else if (cardIndex > player.getCardsOnHand().size()) {
                cardIndex = ZKlavesnice.readInt("You have just " + player.getCardsOnHand().size() + " cards on hand, choose again :");
            } else {
                player.getCardsOnHand().get(cardIndex - 1).play(player, board);
                if (board.getPlayers().size() == 1) {
                    return;
                }
                System.out.println("Now your cards are :");
                this.printCardsOnHand(player);
                cardIndex = ZKlavesnice.readInt("Select number of next card you want to play (O for none) :");
            }
        } while (true);
    }

    private void printCardsOnHand(Player player) {
        for (int i = 0; i < player.getCardsOnHand().size(); i++) {
            System.out.println(" " + (i + 1) + " --- " + player.getCardsOnHand().get(i).getName());
        }
    }
}

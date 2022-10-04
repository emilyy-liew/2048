import java.util.Scanner;

public class two048 {

    public static void main(String[] args) {
        // TODO add user interface
        String again;
        do {
            Board board = new Board();
            String move, game = "";
            Scanner in = new Scanner(System.in);
            board.printBoard();

            while (!(board.getIsWon().equals("won")|| board.getIsWon().equals("lost") || board.getIsWon().equals("again"))){
                move = in.nextLine();
                switch(move) {
                    case "w":
                        board.moveW();
                        break;
                    case "a":
                        board.moveA();
                        break;
                    case "s":
                        board.moveS();
                        break;
                    case "d":
                        board.moveD();
                        break;
                    default:
                        System.out.println("Please enter a valid move key.");
                        break;
                }

                board.setIsWon(board.checkBoard());
                if (board.getIsWon().equals("won")) {
                    System.out.println("Great job you 2048 whiz!");
                    System.out.println("Would you like to continue your board? (Y/N)");
                    board.setIsWon(in.nextLine());
                    validateYorN(board, in);
                    if(board.getIsWon().equals("Y") || board.getIsWon().equals("y")) {
                        board.setSameBoard(true);
                        board.printBoard();
                    } else {
                        board.setIsWon("");
                        System.out.println("Would like to play a new board? (Y/N)");
                        board.setIsWon(in.nextLine());
                        validateYorN(board, in);
                        if(board.getIsWon().equals("Y") || board.getIsWon().equals("y")) {
                            board.setIsWon("again");
                        } else {
                            board.setIsWon("won");
                        }
                    }
                } else if (board.getIsWon().equals("lost") && board.calculateMax() != 2048) {
                   System.out.println("Better luck next time! Would you like to play again? (Y/N)");
                   board.setIsWon(in.nextLine());
                   validateYorN(board, in);
                   board.setIsWon((board.getIsWon().equals("Y") || board.getIsWon().equals("y")) ? "again" : "lost");
               } else if (board.getIsWon().equals("lost")) {
                   System.out.println("WOW great job! Would like to play again? (Y/N)");
                   board.setIsWon(in.nextLine());
                   validateYorN(board, in);
                   board.setIsWon((game.equals("Y") || game.equals("y")) ? "again" : "lost");
               }
            }
            again = board.getIsWon();
        } while (again.equals("again"));
    }

    public static void validateYorN(Board board, Scanner in) {
        while(!(board.getIsWon().equals("Y") || board.getIsWon().equals("y") || board.getIsWon().equals("N") || board.getIsWon().equals("n"))) {
            System.out.print("Please enter a valid selection: ");
            board.setIsWon(in.nextLine());
        }
    }
}

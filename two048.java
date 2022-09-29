import java.util.Scanner;

public class two048 {

    public static void main(String[] args) {
            // TODO fix add new numbers
            // TODO method everything
            // TODO add user interface

            int[][] numbers = new int[4][4];
            String[] maxCol = new String[4];
            int row = 0, col = 0, score = 0;
            String move, game = "";
            Scanner in = new Scanner(System.in);

            for (int x = 0; x < 2; x++) {
                addNewNumbers(row, col, numbers);
            }

            printBoard(numbers, score, maxCol);

            while (!(game.equals("won")|| game.equals("lost"))){
                move = in.nextLine();
                switch(move) {
                    case "w":
                        for(int c = 0; c < 4; c++) {
                            int counter = 0;
                            for(int r = 0; r < 4; r++) {
                                if(!(numbers[r][c] == 0)){
                                    numbers[counter][c] = numbers[r][c];
                                    if(!(r == counter))
                                        numbers[r][c] = 0;
                                    counter++;
                                }
                            }
                        }
                        for(int c = 0; c < 4; c++) {
                            for (int r = 0; r < 3; r++) {
                                if (numbers[r][c] == numbers[r + 1][c] && !(numbers[r][c] == 0)) {
                                    numbers[r][c] += numbers[r + 1][c];
                                    score = calculateScore(numbers[r][c], score);
                                    numbers[r + 1][c] = 0;
                                }
                            }
                        }
                        for(int c = 0; c < 4; c++) {
                            int counter = 0;
                            for(int r = 0; r < 4; r++) {
                                if(!(numbers[r][c] == 0)){
                                    numbers[counter][c] = numbers[r][c];
                                    if(!(r == counter))
                                        numbers[r][c] = 0;
                                    counter++;
                                }
                            }
                        }
                        addNewNumbers(row, col, numbers);
                        printBoard(numbers, score, maxCol);
                        break;
                    case "a":
                        for(int r = 0; r < 4; r++) {
                            int counter = 0;
                            for(int c = 0; c < 4; c++) {
                                if(!(numbers[r][c] == 0)) {
                                    numbers[r][counter] = numbers[r][c];
                                    if(!(c == counter))
                                        numbers[r][c] = 0;
                                    counter++;
                                }
                            }
                        }
                        for(int r = 0; r < 4; r++) {
                            for(int c = 0; c < 3; c++) {
                                if(numbers[r][c] == numbers[r][c+1] && !(numbers[r][c] == 0)) {
                                    numbers[r][c] += numbers[r][c+1];
                                    score = calculateScore(numbers[r][c], score);
                                    numbers[r][c+1] = 0;
                                }
                            }
                        }
                        for(int r = 0; r < 4; r++) {
                            int counter = 0;
                            for(int c = 0; c < 4; c++) {
                                if(!(numbers[r][c] == 0)) {
                                    numbers[r][counter] = numbers[r][c];
                                    if(!(c == counter))
                                        numbers[r][c] = 0;
                                    counter++;
                                }
                            }
                        }
                        addNewNumbers(row, col, numbers);
                        printBoard(numbers, score, maxCol);
                        break;
                    case "s":
                        for(int c = 0; c < 4; c++) {
                            int counter = 3;
                            for(int r = 3; r >= 0; r--) {
                                if(!(numbers[r][c] == 0)) {
                                    numbers[counter][c] = numbers[r][c];
                                    if(!(r == counter))
                                        numbers[r][c] = 0;
                                    counter--;
                                }
                            }
                        }
                        for(int c = 0; c < 4; c++) {
                            for(int r = 3; r > 0; r--) {
                                if(numbers[r][c] == numbers[r-1][c] && !(numbers[r][c] == 0)) {
                                    numbers[r][c] += numbers[r-1][c];
                                    score = calculateScore(numbers[r][c], score);
                                    numbers[r-1][c] = 0;
                                }
                            }
                        }
                        for(int c = 0; c < 4; c++) {
                            int counter = 3;
                            for(int r = 3; r >= 0; r--) {
                                if(!(numbers[r][c] == 0)) {
                                    numbers[counter][c] = numbers[r][c];
                                    if(!(r == counter))
                                        numbers[r][c] = 0;
                                    counter--;
                                }
                            }
                        }
                        addNewNumbers(row, col, numbers);
                        printBoard(numbers, score, maxCol);
                        break;
                    case "d":
                        for(int r = 0; r < 4; r++) {
                            int counter = 3;
                            for(int c = 3; c >= 0; c--) {
                                if(!(numbers[r][c] == 0)) {
                                    numbers[r][counter] = numbers[r][c];
                                    if(!(c == counter))
                                        numbers[r][c] = 0;
                                    counter--;
                                }
                            }
                        }
                        for(int r = 0; r < 4; r++) {
                            for(int c = 3; c > 0; c--) {
                                if(numbers[r][c] == numbers[r][c-1] && !(numbers[r][c] == 0)) {
                                    numbers[r][c] += numbers[r][c-1];
                                    score = calculateScore(numbers[r][c], score);

                                    numbers[r][c-1] = 0;
                                }
                            }
                        }
                        for(int r = 0; r < 4; r++) {
                            int counter = 3;
                            for(int c = 3; c >= 0; c--) {
                                if(!(numbers[r][c] == 0)) {
                                    numbers[r][counter] = numbers[r][c];
                                    if(!(c == counter))
                                        numbers[r][c] = 0;
                                    counter--;
                                }
                            }
                        }
                        addNewNumbers(row, col, numbers);
                        printBoard(numbers, score, maxCol);
                        break;
                    default:
                        System.out.println("You suck, Anay!!!");
                        break;
                }

                game = checkBoard(numbers);
            }
            if (game.equals("won")) {
                System.out.println("Congrats you 2048 whiz!");
            } else {
                System.out.println("Better luck next time! Would you like to play again?");
            }
        }



        public static void printBoard(int[][] arr, int score, String[] cols) {
            for(int c = 0; c < 4; c++) {
                int max = 0;
                for (int r = 0; r < 4; r++) {
                    if(arr[r][c] > max) {
                        max = arr[r][c];
                    }
                }
                cols[c] = "" + max;
            }
            String m = "" + calculateMax(arr);
            System.out.println("Score: " + score);
            for(int r = 0; r < 4; r++) {
                for (int c = 0; c < 4; c++) {
                    if(arr[r][c] == 0) {
                        System.out.print("_");
                    } else {
                        System.out.print(arr[r][c]);
                    }
                    String temp = "" + arr[r][c];
                    for(int x = 0; x < cols[c].length() - temp.length() + 1; x++) {
                        System.out.print(" ");
                    }
                }
                System.out.println("");
            }
        }

        public static void addNewNumbers(int row, int col, int[][] arr) {
            //
            if (isBoardFull(arr) || !canMerge(arr) && !(calculateMax(arr) <= 2)) {
                // do nothing
            } else {
                do {
                    row = (int) (Math.random() * 4);
                    col = (int) (Math.random() * 4);
                } while (!(arr[row][col] == 0));

                arr[row][col] = 2;
            }
        }

        public static int calculateScore(int mergedSquare, int totalScore) {
            if(mergedSquare == 2) {
                return 0;
            }
            return totalScore + (2 * calculateScore((int) (mergedSquare * Math.pow(2, -1)), 0) + mergedSquare);
        }

        public static String checkBoard(int[][] arr) {
            if(calculateMax(arr) == 2048) {
                return "won";
            } else if (isBoardFull(arr) && !canMerge(arr)) {
                return "lost";
            }
            return "";
        }

        public static boolean isBoardFull(int[][] arr) {
            for (int[] y: arr)
                for(int x: y)
                    if(x == 0)
                        return false;
            return true;
        }

        public static boolean canMergeHorizontally(int[][] arr) {
            for(int r = 0; r < 4; r++) {
                for (int c = 0; c < 3; c++) {
                    if (arr[r][c] == arr[r][c + 1] && !(arr[r][c] == 0)) {
                        return true;
                    }
                }
            }
            for(int r = 0; r < 4; r++) {
                for (int c = 0; c < 3; c++) {
                    if(!(arr[r][c] == arr[r][c + 1]) && arr[r][c] == 0 || arr[r][c+1] == 0) {
                        return true;
                    }
                }
            }
            return false;
        }

        public static boolean canMergeVertically(int[][] arr) {
            for(int c = 0; c < 4; c++) {
                for (int r = 0; r < 3; r++) {
                    if (arr[r][c] == arr[r + 1][c] && !(arr[r][c] == 0)) {
                        return true;
                    }
                }
            }
            for(int c = 0; c < 4; c++) {
                for (int r = 0; r < 3; r++) {
                    if(!(arr[r][c] == arr[r+1][c]) && arr[r][c] == 0 || arr[r+1][c] == 0) {
                        return true;
                    }
                }
            }
            return false;
        }

        public static boolean canMerge(int[][] arr) {
            return canMergeHorizontally(arr) || canMergeVertically(arr);
        }

        public static int calculateMax(int[][] arr) {
            int max = 0;
            for(int r = 0; r < 4; r++) {
                for (int c = 0; c < 4; c++) {
                    if(arr[r][c] > max)
                        max = arr[r][c];
                }
            }
            return max;
        }
    }

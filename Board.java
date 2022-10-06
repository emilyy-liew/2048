public class Board {
    private int[][] numbers;
    private String[] maxCol;
    private static int score;
    private static String isWon;
    private boolean sameBoard;

    Board() {
        numbers = new int[4][4];
        maxCol = new String[4];
        score = 0;
        setIsWon("");
        setSameBoard(false);
        for (int x = 0; x < 2; x++) {
            addNewNumbers();
        }
    }

    public void printBoard() {
        for(int c = 0; c < 4; c++) {
            int max = 0;
            for (int r = 0; r < 4; r++) {
                if(numbers[r][c] > max) {
                    max = numbers[r][c];
                }
            }
            maxCol[c] = "" + max;
        }
        String m = "" + calculateMax();
        System.out.println("Score: " + score);
        for(int r = 0; r < 4; r++) {
            for (int c = 0; c < 4; c++) {
                if(numbers[r][c] == 0) {
                    System.out.print("_");
                } else {
                    System.out.print(numbers[r][c]);
                }
                String temp = "" + numbers[r][c];
                for(int x = 0; x < maxCol[c].length() - temp.length() + 1; x++) {
                    System.out.print(" ");
                }
            }
            System.out.println("");
        }
    }

    public void setSameBoard(boolean sameBoard) {
        this.sameBoard = sameBoard;
    }

    private void addNewNumbers() {
        int row, col;
        if (isBoardFull()) {
            // do nothing
        } else {
            do {
                row = (int) (Math.random() * 4);
                col = (int) (Math.random() * 4);
            } while (!(numbers[row][col] == 0));

            numbers[row][col] = (((int)(Math.random() * 10) % 2 == 0) ? 2 : 4);
        }
    }

    public String checkBoard() {
        if (!sameBoard) {
            if(calculateMax() == 2048) {
                return "won";
            }
        }

        if (isBoardFull() && !canMerge()) {
            return "lost";
        }
        return "";
    }

    private boolean isBoardFull() {
        for (int[] y: numbers)
            for(int x: y)
                if (x == 0)
                    return false;
        return true;
    }

    private boolean canMergeHorizontally() {
        return canMoveLeft() || canMoveRight();
    }

    private boolean canMoveLeft() {
        for(int r = 0; r < 4; r++) {
            for (int c = 0; c < 3; c++) {
                if (numbers[r][c] == 0 && numbers[r][c + 1] != 0) {
                    return true;
                }
            }
        }
        for(int r = 0; r < 4; r++) {
            for (int c = 0; c < 3; c++) {
                if (numbers[r][c] == numbers[r][c + 1] && !(numbers[r][c] == 0)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean canMoveRight() {
        for(int r = 0; r < 4; r++) {
            for (int c = 0; c < 3; c++) {
                if (numbers[r][c] != 0 && numbers[r][c + 1] == 0) {
                    return true;
                }
            }
        }
        for(int r = 0; r < 4; r++) {
            for (int c = 0; c < 3; c++) {
                if (numbers[r][c] == numbers[r][c + 1] && !(numbers[r][c] == 0)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean canMergeVertically() {
        return canMoveUp() || canMoveDown();
    }

    private boolean canMoveUp() {
        for(int c = 0; c < 4; c++) {
            for (int r = 0; r < 3; r++) {
                if (numbers[r][c] == 0 && numbers[r + 1][c] != 0) {
                    return true;
                }
            }
        }
        for(int c = 0; c < 4; c++) {
            for (int r = 0; r < 3; r++) {
                if (numbers[r][c] == numbers[r + 1][c] && !(numbers[r][c] == 0)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean canMoveDown() {
        for(int c = 0; c < 4; c++) {
            for (int r = 0; r < 3; r++) {
                if (numbers[r+1][c] == 0 && numbers[r][c] != 0) {
                    return true;
                }
            }
        }
        for(int c = 0; c < 4; c++) {
            for (int r = 0; r < 3; r++) {
                if (numbers[r][c] == numbers[r + 1][c] && !(numbers[r][c] == 0)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean canMerge() {
        return canMergeHorizontally() || canMergeVertically();
    }

    public int calculateMax() {
        int max = 0;
        for(int r = 0; r < 4; r++) {
            for (int c = 0; c < 4; c++) {
                if(numbers[r][c] > max)
                    max = numbers[r][c];
            }
        }
        return max;
    }

    public void moveW() {
        if (canMoveUp()) {
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
                        score += numbers[r][c];
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
            addNewNumbers();
        }
        printBoard();
    }

    public void moveA() {
        if (canMoveLeft()) {
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
                        score += numbers[r][c];
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
            addNewNumbers();
        }
        printBoard();
    }

    public void moveS() {
        if (canMoveDown()) {
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
                        score += numbers[r][c];
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
            addNewNumbers();



        }
        printBoard();
    }

    public void moveD() {
        if (canMoveRight()) {
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
                        score += numbers[r][c];
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
            addNewNumbers();
        }
        printBoard();
    }

    public String getIsWon() {
        return isWon;
    }

    public void setIsWon(String isWon) {
        this.isWon = isWon;
    }
}

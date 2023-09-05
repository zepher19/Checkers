package com.myapps.checkersv2;


import android.view.View;

public class BoardModel {
    //the imaginary board has 2 extra rows and columns on each side of the board.
    // The reason for this is to allow the movement logic to look into memory beyond the bounds of the
    //board without crashing the program, thus greatly simplifying the logic.

    //0 = a space nothing can occupy
    //r = red piece
    //k = red king
    //w = white piece
    //q = white king

    public static String[] imaginaryBoard =
                    {"000000000000",
                    "000000000000",
                    "000w0w0w0w00",
                    "00w0w0w0w000",
                    "000w0w0w0w00",
                    "00b0b0b0b000",
                    "000b0b0b0b00",
                    "00r0r0r0r000",
                    "000r0r0r0r00",
                    "00r0r0r0r000",
                    "000000000000",
                    "000000000000"};
    private static final int[] INT_ROW_0 = {R.id.r0c0, R.id.r0c1, R.id.r0c2, R.id.r0c3, R.id.r0c4, R.id.r0c5, R.id.r0c6, R.id.r0c7};
    private static final int[] INT_ROW_1 = {R.id.r1c0, R.id.r1c1, R.id.r1c2, R.id.r1c3, R.id.r1c4, R.id.r1c5, R.id.r1c6, R.id.r1c7};
    private static final int[] INT_ROW_2 = {R.id.r2c0, R.id.r2c1, R.id.r2c2, R.id.r2c3, R.id.r2c4, R.id.r2c5, R.id.r2c6, R.id.r2c7};
    private static final int[] INT_ROW_3 = {R.id.r3c0, R.id.r3c1, R.id.r3c2, R.id.r3c3, R.id.r3c4, R.id.r3c5, R.id.r3c6, R.id.r3c7};
    private static final int[] INT_ROW_4 = {R.id.r4c0, R.id.r4c1, R.id.r4c2, R.id.r4c3, R.id.r4c4, R.id.r4c5, R.id.r4c6, R.id.r4c7};
    private static final int[] INT_ROW_5 = {R.id.r5c0, R.id.r5c1, R.id.r5c2, R.id.r5c3, R.id.r5c4, R.id.r5c5, R.id.r5c6, R.id.r5c7};
    private static final int[] INT_ROW_6 = {R.id.r6c0, R.id.r6c1, R.id.r6c2, R.id.r6c3, R.id.r6c4, R.id.r6c5, R.id.r6c6, R.id.r6c7};
    private static final int[] INT_ROW7 = {R.id.r7c0, R.id.r7c1, R.id.r7c2, R.id.r7c3, R.id.r7c4, R.id.r7c5, R.id.r7c6, R.id.r7c7};
    public static int[][] viewPositions = {INT_ROW_0, INT_ROW_1, INT_ROW_2, INT_ROW_3, INT_ROW_4, INT_ROW_5, INT_ROW_6, INT_ROW7};
    public static String selectedPiece, previousSelectedPiece, additionalMovePieceType;
    public static int rowAdditionalMovePiece = 0, indexAdditionalMovePiece = 0;
    public static boolean redTurn = true, additionalMove = false, pieceIsHighlighted = false;
    private static final int NORMAL_MOVE_CODE = 10, ADDITIONAL_MOVE_CODE = 20,
            RED_CANT_MOVE = 13, WHITE_CANT_MOVE = 15, GAME_CONTINUE = 0 ;
    ;
    private static final String RED_PIECE = "r", RED_KING = "k", WHITE_PIECE = "w",
            WHITE_KING = "q", HIGHLIGHT = "h", BLANK = "b";
    private static final char BLANK_CHAR = 'b', RED_PIECE_CHAR = 'r', WHITE_PIECE_CHAR = 'w',
            RED_KING_CHAR = 'k', WHITE_KING_CHAR = 'q', HIGHLIGHT_CHAR = 'h';


    public static int identifyMoveFlow(View v, View previousView) {
        int gameCode = GAME_CONTINUE;

        //if this is a normal move
        if (!additionalMove) {
            //white highlight the selected view
            v.setBackgroundResource(R.drawable.black_square);

            //if either player cannot move, the other player wins
            //handleMoveFlow returns false if the player cannot move
            if (!normalMoveFlow(v, previousView)) {
                //based on whose turn it is, we figure out who has lost
                if (redTurn) {
                    gameCode = RED_CANT_MOVE;
                }
                else {
                    gameCode = WHITE_CANT_MOVE;
                }
            }
        }

        //if this is an additional move
        if (additionalMove) {
            additionalMoveFlow(v, previousView);
        }
        return gameCode;
    }

    public static boolean normalMoveFlow(View v, View previousView) {
        //check to see whether player can move, otherwise game ends

        if (canPlayerMove()) {

            //remove previous white highlight so that only one view is white highlighted at a time
            if (previousView != null) {
                previousView.setBackgroundResource(R.drawable.black_square);
                pieceIsHighlighted = false;
            }

            //store the previously selected piece to allow movement later
            //important once this method has been used several times
            previousSelectedPiece = selectedPiece;

            //set piece details from clicked view
            setSelectedPiece(v);

            //gather details from selected piece
            int row = getPieceRow(selectedPiece);
            int index = getPieceIndex(selectedPiece);
            String pieceType = getPieceType(row, index);

            //first unhighlight anything already highlighted
            unhighlight();

            //if spot was highlighted, move the piece to that spot
            //pieceType is calculated before the call to unhighlight
            //hence why this logic works
            if (pieceType.equals(HIGHLIGHT)) {
                movePiece();
            }

            //the following if statements prevent spots from being highlighted
            // or pieces moved when it is not that players turn

            //king logic for highlighting spots
            if (pieceType.equals(RED_KING) && redTurn || pieceType.equals(WHITE_KING) && !redTurn) {
                highlightMovesCommon(row, index, pieceType, NORMAL_MOVE_CODE);
                //highlight selected view
                v.setBackgroundResource(R.drawable.black_square_white_highlight);
                pieceIsHighlighted = true;
            }

            //normal white piece logic
            if (pieceType.equals(WHITE_PIECE) && !redTurn) {
                highlightMovesCommon(row, index, pieceType, NORMAL_MOVE_CODE);
                //highlight selected view
                v.setBackgroundResource(R.drawable.black_square_white_highlight);
                pieceIsHighlighted = true;
            }

            //normal red piece logic
            if(pieceType.equals(RED_PIECE) && redTurn) {
                highlightMovesCommon(row, index, pieceType, NORMAL_MOVE_CODE);
                //highlight selected view
                v.setBackgroundResource(R.drawable.black_square_white_highlight);
                pieceIsHighlighted = true;
            }
            return true;
        }
        return false;
    }

    private static void highlightMovesCommon(int row, int index, String pieceType, int moveCode) {
        String enemy;
        String enemyKing;

        //row advance and retreat vary by color. For white, advancing means going to a higher index row,
        //for red, the reverse is true.
        int rowAdvance1 = 0;
        int rowAdvance2 = 0;
        int rowRetreat1 = 0;
        int rowRetreat2 = 0;

        final int INDEX_LEFT_1 = index - 1;
        final int INDEX_LEFT_2 = index - 2;
        final int INDEX_RIGHT_1 = index + 1;
        final int INDEX_RIGHT_2 = index + 2;

        if (pieceType.equals(RED_PIECE) || pieceType.equals(RED_KING)) {
            enemy = WHITE_PIECE;
            enemyKing = WHITE_KING;
            rowAdvance1 = row - 1;
            rowAdvance2 = row - 2;
            rowRetreat1 = row + 1;
            rowRetreat2 = row + 2;
        }
        else {
            enemy = RED_PIECE;
            enemyKing = RED_KING;
            rowAdvance1 = row + 1;
            rowAdvance2 = row + 2;
            rowRetreat1 = row - 1;
            rowRetreat2 = row - 2;
        }

        //value representing whether there is a nearby enemy piece that could be captured
        boolean nearEnemy = false;

        // if there is an enemy piece in any of the spots around
        if (Character.toString(imaginaryBoard[rowAdvance1].charAt(INDEX_LEFT_1)).equals(enemy) ||
                (Character.toString(imaginaryBoard[rowAdvance1].charAt(INDEX_LEFT_1)).equals(enemyKing))) {
            if (Character.toString(imaginaryBoard[rowAdvance2].charAt(INDEX_LEFT_2)).equals(BLANK)) {
                changeRowString(rowAdvance2, INDEX_LEFT_2,  HIGHLIGHT_CHAR);
                nearEnemy = true;
            }
        }

        if (Character.toString(imaginaryBoard[rowAdvance1].charAt(INDEX_RIGHT_1)).equals(enemy) ||
                (Character.toString(imaginaryBoard[rowAdvance1].charAt(INDEX_RIGHT_1)).equals(enemyKing))) {
            if (Character.toString(imaginaryBoard[rowAdvance2].charAt(INDEX_RIGHT_2)).equals(BLANK)) {
                changeRowString(rowAdvance2, INDEX_RIGHT_2,  HIGHLIGHT_CHAR);
                nearEnemy = true;
            }
        }

        //look for extra highlight spots for king pieces
        if (pieceType.equals(RED_KING) || pieceType.equals(WHITE_KING)) {
            if (Character.toString(imaginaryBoard[rowRetreat1].charAt(INDEX_LEFT_1)).equals(enemy) ||
                    (Character.toString(imaginaryBoard[rowRetreat1].charAt(INDEX_LEFT_1)).equals(enemyKing))) {
                if (Character.toString(imaginaryBoard[rowRetreat2].charAt(INDEX_LEFT_2)).equals(BLANK)) {
                    changeRowString(rowRetreat2, INDEX_LEFT_2,  HIGHLIGHT_CHAR);
                    nearEnemy = true;
                }
            }

            if (Character.toString(imaginaryBoard[rowRetreat1].charAt(INDEX_RIGHT_1)).equals(enemy) ||
                    (Character.toString(imaginaryBoard[rowRetreat1].charAt(INDEX_RIGHT_1)).equals(enemyKing))) {
                if (Character.toString(imaginaryBoard[rowRetreat2].charAt(INDEX_RIGHT_2)).equals(BLANK)) {
                    changeRowString(rowRetreat2, INDEX_RIGHT_2,  HIGHLIGHT_CHAR);
                    nearEnemy = true;
                }
            }
        }

        //if a piece was deleted we check for an additional move
        //here the method exits with whether there is an additional move open or not
        if (moveCode == ADDITIONAL_MOVE_CODE) {
            //if there are no near enemies, there cannot be an additional move
            additionalMove = nearEnemy;
            return;
        }

        //if there is no near enemy, check for available blank spots
        if (!nearEnemy && !additionalMove) {
            if (Character.toString(imaginaryBoard[rowAdvance1].charAt(INDEX_LEFT_1)).equals(BLANK)) {
                changeRowString(rowAdvance1, INDEX_LEFT_1,  HIGHLIGHT_CHAR);
            }

            if (Character.toString(imaginaryBoard[rowAdvance1].charAt(INDEX_RIGHT_1)).equals(BLANK)) {
                changeRowString(rowAdvance1, INDEX_RIGHT_1,  HIGHLIGHT_CHAR);
            }

            //look for extra highlight spots for king pieces
            if (pieceType.equals(RED_KING) || pieceType.equals(WHITE_KING)) {
                if (Character.toString(imaginaryBoard[rowRetreat1].charAt(INDEX_LEFT_1)).equals(BLANK)) {
                    changeRowString(rowRetreat1, INDEX_LEFT_1, HIGHLIGHT_CHAR);
                }

                if (Character.toString(imaginaryBoard[rowRetreat1].charAt(INDEX_RIGHT_1)).equals(BLANK)) {
                    changeRowString(rowRetreat1, INDEX_RIGHT_1, HIGHLIGHT_CHAR);
                }
            }
        }
    }

    public static void movePiece() {

        //keeps track if a piece is deleted.
        //if a piece is deleted, the game needs to check for
        //additional jumps
        boolean deletedPiece = false;

        int rowInitialPiece;
        int indexInitialPiece;
        String pieceTypeInitialPiece;

        //if this is an additional move different logic is required
        if (additionalMove) {
            rowInitialPiece = rowAdditionalMovePiece;
            indexInitialPiece = indexAdditionalMovePiece;
            pieceTypeInitialPiece = additionalMovePieceType;
        }

        //if this is a standard move, standard logic applies
        else {
            //get info of originally selected spot
            rowInitialPiece = getPieceRow(previousSelectedPiece);
            indexInitialPiece = getPieceIndex(previousSelectedPiece);
            pieceTypeInitialPiece = getPieceType(rowInitialPiece, indexInitialPiece);
        }

        //remove the piece from the initial spot
        changeRowString(rowInitialPiece, indexInitialPiece, BLANK_CHAR);

        //get info of the most recently clicked spot
        int rowToLand = getPieceRow(selectedPiece);
        int indexToLand = getPieceIndex(selectedPiece);

        //replace the blank spot with the piece, thereby moving it
        changeRowString(rowToLand, indexToLand, pieceTypeInitialPiece.charAt(0));

        //if piece moves 2 rows, it must have captured an enemy piece, therefore we delete the piece in between
        //found by averaging
        if (rowInitialPiece - rowToLand == 2 || rowInitialPiece - rowToLand == -2) {
            int rowToDelete = (rowInitialPiece + rowToLand) / 2;
            int indexToDelete = (indexInitialPiece + indexToLand) /2;

            //store the boolean if a piece was deleted
            deletedPiece = deletePiece(rowToDelete, indexToDelete);
        }

        //if piece is at the enemy end of the board, king it
        //imaginary board has 2 extra rows on each side, so array rows are transformed by 2
        if (rowToLand == 2 && pieceTypeInitialPiece.charAt(0) == RED_PIECE_CHAR) {
            changeRowString(rowToLand, indexToLand, RED_KING_CHAR);
        }

        if (rowToLand == 9 && pieceTypeInitialPiece.charAt(0) == WHITE_PIECE_CHAR) {
            changeRowString(rowToLand, indexToLand, WHITE_KING_CHAR);
        }

        //store info of current piece in case it is needed for additional jumps
        additionalMovePieceType = getPieceType(rowToLand, indexToLand);
        rowAdditionalMovePiece = rowToLand;
        indexAdditionalMovePiece = indexToLand;

        if (deletedPiece) {
            highlightMovesCommon(rowToLand, indexToLand, getPieceType(rowToLand, indexToLand), ADDITIONAL_MOVE_CODE);
        }

        if (!additionalMove) {
            //if there are no additional moves required, turn changes
            redTurn = !redTurn;
        }
    }
    public static void additionalMoveFlow(View v, View previousView) {

        //handles highlighting the moving piece
        if (!pieceIsHighlighted) {
            v.setBackgroundResource(R.drawable.black_square_white_highlight);
            pieceIsHighlighted = true;
        }
        //set piece details from clicked view
        setSelectedPiece(v);
        //gather current piece details
        int row = getPieceRow(selectedPiece);
        int index = getPieceIndex(selectedPiece);
        String pieceType = getPieceType(row, index);

        if (pieceType.equals(HIGHLIGHT)) {
            //need to unhighlight for additional move logic
            unhighlight();
            movePiece();
            if (additionalMove) {
                v.setBackgroundResource(R.drawable.black_square_white_highlight);
                pieceIsHighlighted = true;
            } else {
                v.setBackgroundResource(R.drawable.black_square);
                pieceIsHighlighted = false;
            }
        }
    }

    private static void changeRowString(int rowToBuild, int index, char character) {
        StringBuilder stringBuilder = new StringBuilder(imaginaryBoard[rowToBuild]);
        stringBuilder.setCharAt(index, character);
        imaginaryBoard[rowToBuild] = stringBuilder.toString();
    }

    private static boolean deletePiece(int rowToDelete, int indexToDelete) {
        changeRowString(rowToDelete, indexToDelete, BLANK_CHAR);
        return true;
    }

    public static void setSelectedPiece(View v) {
        selectedPiece = v.getResources().getResourceEntryName(v.getId());
    }

    public static int getPieceRow(String piece) {
        return Integer.parseInt(piece.substring(1,2)) + 2;
    }

    public static int getPieceIndex(String piece) {
        return Integer.parseInt(piece.substring(3)) + 2;
    }

    public static String getPieceType(int row, int index) {
        return Character.toString(imaginaryBoard[row].charAt(index));
    }


    private static void unhighlight() {
        for (int i = 2; i < imaginaryBoard.length - 2; i++) {
            for (int j = 2; j < imaginaryBoard[i].length() - 2; j++) {
                if (imaginaryBoard[i].charAt(j) == HIGHLIGHT_CHAR) {
                    changeRowString(i, j, BLANK_CHAR);
                }
            }
        }
    }

    //checks whether the current player has any legal moves by scanning through the array board.
    //At each piece of the current player, the program determines whether there are any moves.
    public static boolean canPlayerMove() {
        char currentPlayerPiece;
        char currentPlayerKing;
        String enemy;
        String enemyKing;

        //row advance and retreat vary by color. For white, advancing means going to a higher index row,
        //for red, the reverse is true.
        int rowAdvance1 = 0;
        int rowAdvance2 = 0;
        int rowRetreat1 = 0;
        int rowRetreat2 = 0;

        int index = 0;
        int indexLeft1 = 0;
        int indexLeft2 = 0;
        int indexRight1 = 0;
        int indexRight2 = 0;


        if (redTurn){
            currentPlayerPiece = RED_PIECE_CHAR;
            currentPlayerKing = RED_KING_CHAR;
            enemy = WHITE_PIECE;
            enemyKing = WHITE_KING;
        }
        else {
            currentPlayerPiece = WHITE_PIECE_CHAR;
            currentPlayerKing = WHITE_KING_CHAR;
            enemy = RED_PIECE;
            enemyKing = RED_KING;
        }

        //iterate through the imaginary board and check if the current player's pieces have
        //any possible moves
        for (int i = 2; i < imaginaryBoard.length - 2; i++) {
            for (int j = 2; j < imaginaryBoard[i].length() - 2; j++) {
                index = j;
                indexLeft1 = index - 1;
                indexLeft2 = index - 2;
                indexRight1 = index + 1;
                indexRight2 = index + 2;

                if (imaginaryBoard[i].charAt(j) == currentPlayerPiece || imaginaryBoard[i].charAt(j) == currentPlayerKing) {
                    if (redTurn) {
                        rowAdvance1 = i - 1;
                        rowAdvance2 = i - 2;
                        rowRetreat1 = i + 1;
                        rowRetreat2 = i + 2;
                    }
                    else {
                        rowAdvance1 = i + 1;
                        rowAdvance2 = i + 2;
                        rowRetreat1 = i - 1;
                        rowRetreat2 = i - 2;
                    }
                    // if there is an enemy piece in any of the spots around
                    if (Character.toString(imaginaryBoard[rowAdvance1].charAt(indexLeft1)).equals(enemy) ||
                            (Character.toString(imaginaryBoard[rowAdvance1].charAt(indexLeft1)).equals(enemyKing)) ||
                            Character.toString(imaginaryBoard[rowAdvance1].charAt(indexLeft1)).equals(HIGHLIGHT)) {
                        if ((Character.toString(imaginaryBoard[rowAdvance2].charAt(indexLeft2)).equals(BLANK)) ||
                                (Character.toString(imaginaryBoard[rowAdvance2].charAt(indexLeft2)).equals(HIGHLIGHT))) {
                            return true;
                        }
                    }

                    if (Character.toString(imaginaryBoard[rowAdvance1].charAt(indexRight1)).equals(enemy) ||
                            (Character.toString(imaginaryBoard[rowAdvance1].charAt(indexRight1)).equals(enemyKing)) ||
                            (Character.toString(imaginaryBoard[rowAdvance1].charAt(indexRight1)).equals(HIGHLIGHT))) {
                        if ((Character.toString(imaginaryBoard[rowAdvance2].charAt(indexRight2)).equals(BLANK)) ||
                                (Character.toString(imaginaryBoard[rowAdvance2].charAt(indexRight2)).equals(HIGHLIGHT))) {
                            return true;
                        }
                    }

                    if (imaginaryBoard[i].charAt(j) == currentPlayerKing) {
                        //look for extra highlight spots for king pieces
                        if (Character.toString(imaginaryBoard[rowRetreat1].charAt(indexLeft1)).equals(enemy) ||
                                (Character.toString(imaginaryBoard[rowRetreat1].charAt(indexLeft1)).equals(enemyKing)) ||
                                Character.toString(imaginaryBoard[rowRetreat1].charAt(indexLeft1)).equals(HIGHLIGHT)) {
                            if (Character.toString(imaginaryBoard[rowRetreat2].charAt(indexLeft2)).equals(BLANK) ||
                                    Character.toString(imaginaryBoard[rowRetreat2].charAt(indexLeft2)).equals(HIGHLIGHT)) {
                                return true;
                            }
                        }

                        if ((Character.toString(imaginaryBoard[rowRetreat1].charAt(indexRight1)).equals(enemy)) ||
                                (Character.toString(imaginaryBoard[rowRetreat1].charAt(indexRight1)).equals(enemyKing)) ||
                                (Character.toString(imaginaryBoard[rowRetreat1].charAt(indexRight1)).equals(HIGHLIGHT))) {
                            if (Character.toString(imaginaryBoard[rowRetreat2].charAt(indexRight2)).equals(BLANK) ||
                                    (Character.toString(imaginaryBoard[rowRetreat2].charAt(indexRight2)).equals(HIGHLIGHT))) {
                                return true;
                            }
                        }
                    }

                    if (Character.toString(imaginaryBoard[rowAdvance1].charAt(indexLeft1)).equals(BLANK) ||
                            Character.toString(imaginaryBoard[rowAdvance1].charAt(indexLeft1)).equals(HIGHLIGHT)) {
                        return true;
                    }
                    if (Character.toString(imaginaryBoard[rowAdvance1].charAt(indexRight1)).equals(BLANK) ||
                            Character.toString(imaginaryBoard[rowAdvance1].charAt(indexRight1)).equals(HIGHLIGHT) ) {
                        return true;
                    }
                    //look for extra highlight spots for king pieces
                    if (imaginaryBoard[i].charAt(j) == currentPlayerKing) {
                        if (Character.toString(imaginaryBoard[rowRetreat1].charAt(indexLeft1)).equals(BLANK) ||
                                Character.toString(imaginaryBoard[rowRetreat1].charAt(indexLeft1)).equals(HIGHLIGHT)) {
                            return true;
                        }
                        if (Character.toString(imaginaryBoard[rowRetreat1].charAt(indexRight1)).equals(BLANK) ||
                                Character.toString(imaginaryBoard[rowRetreat1].charAt(indexRight1)).equals(HIGHLIGHT)) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    public static void newGame() {
        imaginaryBoard = new String[]{
                "000000000000",
                "000000000000",
                "000w0w0w0w00",
                "00w0w0w0w000",
                "000w0w0w0w00",
                "00b0b0b0b000",
                "000b0b0b0b00",
                "00r0r0r0r000",
                "000r0r0r0r00",
                "00r0r0r0r000",
                "000000000000",
                "000000000000"};
        redTurn = true;
        additionalMove = false;
        pieceIsHighlighted = false;
        selectedPiece = null;
        previousSelectedPiece = null;
        additionalMovePieceType = null;
        rowAdditionalMovePiece = 0;
        indexAdditionalMovePiece = 0;
    }


}
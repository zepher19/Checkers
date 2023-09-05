package com.myapps.checkersv2;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.core.splashscreen.SplashScreen;
import androidx.fragment.app.Fragment;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button newGameButton;
    Button newGameButton2;
    ImageView currentPlayerImageView;
    ImageView currentPlayerImageView2;
    ImageButton boardView;
    //the image buttons representing every square on the board. Blank red squares are included
    // to simplify calculations and movement logic. They could be removed in a future iteration
    ImageButton r0c0, r0c1, r0c2, r0c3, r0c4, r0c5, r0c6, r0c7,
                r1c0, r1c1, r1c2, r1c3, r1c4, r1c5, r1c6, r1c7,
                r2c0, r2c1, r2c2, r2c3, r2c4, r2c5, r2c6, r2c7,
                r3c0, r3c1, r3c2, r3c3, r3c4, r3c5, r3c6, r3c7,
                r4c0, r4c1, r4c2, r4c3, r4c4, r4c5, r4c6, r4c7,
                r5c0, r5c1, r5c2, r5c3, r5c4, r5c5, r5c6, r5c7,
                r6c0, r6c1, r6c2, r6c3, r6c4, r6c5, r6c6, r6c7,
                r7c0, r7c1, r7c2, r7c3, r7c4, r7c5, r7c6, r7c7;


    final int RED_CANT_MOVE = 13, RED_OUT_OF_PIECES_LOSS = 14, WHITE_CANT_MOVE = 15,
            WHITE_OUT_OF_PIECES_LOSS = 16, GAME_CONTINUE = 0 ;

    View previousView = null;

    TextView redPieceCount, whitePieceCount, redKingCount, whiteKingCount, upsideRedPieceCount,
            upsideWhitePieceCount, upsideRedKingCount, upsideWhiteKingCount;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        SplashScreen splashScreen = SplashScreen.installSplashScreen(this);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        redPieceCount = findViewById(R.id.red_piece_count_text_view);
        redKingCount = findViewById(R.id.red_king_count_text_view);
        whitePieceCount = findViewById(R.id.white_piece_count_text_view);
        whiteKingCount = findViewById(R.id.white_king_count_text_view);

        upsideRedPieceCount = findViewById(R.id.upside_red_piece_count_text_view);
        upsideRedKingCount = findViewById(R.id.upside_red_king_count_text_view);
        upsideWhitePieceCount = findViewById(R.id.upside_white_piece_count_text_view);
        upsideWhiteKingCount = findViewById(R.id.upside_white_king_count_text_view);


        currentPlayerImageView = findViewById(R.id.current_player_image_view);
        currentPlayerImageView2 = findViewById(R.id.current_player_image_view2);

        r0c0 = findViewById(R.id.r0c0);

        r0c1 = findViewById(R.id.r0c1);
        r0c1.setOnClickListener(this);

        r0c2 = findViewById(R.id.r0c2);

        r0c3 = findViewById(R.id.r0c3);
        r0c3.setOnClickListener(this);

        r0c4 = findViewById(R.id.r0c4);

        r0c5 = findViewById(R.id.r0c5);
        r0c5.setOnClickListener(this);

        r0c6 = findViewById(R.id.r0c6);

        r0c7 = findViewById(R.id.r0c7);
        r0c7.setOnClickListener(this);

        r1c0 = findViewById(R.id.r1c0);
        r1c0.setOnClickListener(this);

        r1c1 = findViewById(R.id.r1c1);

        r1c2 = findViewById(R.id.r1c2);
        r1c2.setOnClickListener(this);

        r1c3 = findViewById(R.id.r1c3);

        r1c4 = findViewById(R.id.r1c4);
        r1c4.setOnClickListener(this);

        r1c5 = findViewById(R.id.r1c5);

        r1c6 = findViewById(R.id.r1c6);
        r1c6.setOnClickListener(this);

        r1c7 = findViewById(R.id.r1c7);

        r2c0 = findViewById(R.id.r2c0);

        r2c1 = findViewById(R.id.r2c1);
        r2c1.setOnClickListener(this);

        r2c2 = findViewById(R.id.r2c2);

        r2c3 = findViewById(R.id.r2c3);
        r2c3.setOnClickListener(this);

        r2c4 = findViewById(R.id.r2c4);

        r2c5 = findViewById(R.id.r2c5);
        r2c5.setOnClickListener(this);

        r2c6 = findViewById(R.id.r2c6);

        r2c7 = findViewById(R.id.r2c7);
        r2c7.setOnClickListener(this);

        r3c0 = findViewById(R.id.r3c0);
        r3c0.setOnClickListener(this);

        r3c1 = findViewById(R.id.r3c1);

        r3c2 = findViewById(R.id.r3c2);
        r3c2.setOnClickListener(this);

        r3c3 = findViewById(R.id.r3c3);

        r3c4 = findViewById(R.id.r3c4);
        r3c4.setOnClickListener(this);

        r3c5 = findViewById(R.id.r3c5);

        r3c6 = findViewById(R.id.r3c6);
        r3c6.setOnClickListener(this);

        r3c7 = findViewById(R.id.r3c7);

        r4c0 = findViewById(R.id.r4c0);

        r4c1 = findViewById(R.id.r4c1);
        r4c1.setOnClickListener(this);

        r4c2 = findViewById(R.id.r4c2);

        r4c3 = findViewById(R.id.r4c3);
        r4c3.setOnClickListener(this);

        r4c4 = findViewById(R.id.r4c4);

        r4c5 = findViewById(R.id.r4c5);
        r4c5.setOnClickListener(this);

        r4c6 = findViewById(R.id.r4c6);

        r4c7 = findViewById(R.id.r4c7);
        r4c7.setOnClickListener(this);

        r5c0 = findViewById(R.id.r5c0);
        r5c0.setOnClickListener(this);

        r5c1 = findViewById(R.id.r5c1);

        r5c2 = findViewById(R.id.r5c2);
        r5c2.setOnClickListener(this);

        r5c3 = findViewById(R.id.r5c3);

        r5c4 = findViewById(R.id.r5c4);
        r5c4.setOnClickListener(this);

        r5c5 = findViewById(R.id.r5c5);

        r5c6 = findViewById(R.id.r5c6);
        r5c6.setOnClickListener(this);

        r5c7 = findViewById(R.id.r5c7);

        r6c0 = findViewById(R.id.r6c0);

        r6c1 = findViewById(R.id.r6c1);
        r6c1.setOnClickListener(this);

        r6c2 = findViewById(R.id.r6c2);

        r6c3 = findViewById(R.id.r6c3);
        r6c3.setOnClickListener(this);

        r6c4 = findViewById(R.id.r6c4);

        r6c5 = findViewById(R.id.r6c5);
        r6c5.setOnClickListener(this);

        r6c6 = findViewById(R.id.r6c6);

        r6c7 = findViewById(R.id.r6c7);
        r6c7.setOnClickListener(this);

        r7c0 = findViewById(R.id.r7c0);
        r7c0.setOnClickListener(this);

        r7c1 = findViewById(R.id.r7c1);

        r7c2 = findViewById(R.id.r7c2);
        r7c2.setOnClickListener(this);

        r7c3 = findViewById(R.id.r7c3);

        r7c4 = findViewById(R.id.r7c4);
        r7c4.setOnClickListener(this);

        r7c5 = findViewById(R.id.r7c5);

        r7c6 = findViewById(R.id.r7c6);
        r7c6.setOnClickListener(this);

        r7c7 = findViewById(R.id.r7c7);

        newGameButton = findViewById(R.id.new_game_button);
        View.OnClickListener newGameButtonListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                askBeginNewGame();
            }
        };
        newGameButton.setOnClickListener(newGameButtonListener);

        newGameButton2 = findViewById(R.id.new_game_button2);
        newGameButton2.setOnClickListener(newGameButtonListener);


        updateBoard();

    }

    private void askBeginNewGame() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Start New Game?");
        // Set Cancelable false for when the user clicks on the outside the Dialog Box then it will remain show
        builder.setCancelable(false);
        builder.setMessage("The current game will be deleted.\nAre you sure you want to begin a new game?");
        // Set the positive button with yes name Lambda OnClickListener method is use of DialogInterface interface.
        builder.setPositiveButton("Yes", (DialogInterface.OnClickListener) (dialog, which) -> {
            dialog.cancel();
            beginNewGame();
        });

        // Set the Negative button with No name Lambda OnClickListener method is use of DialogInterface interface.
        builder.setNegativeButton("No", (DialogInterface.OnClickListener) (dialog, which) -> {
            dialog.cancel();
        });

        // Create the Alert dialog
        AlertDialog alertDialog = builder.create();
        // Show the Alert Dialog box
        alertDialog.show();
    }

    @Override
    public void onClick(View v) {
        //identifyMoveFlow performs several checks, to see if the game is over,
        // to see if the move is an additional move or a normal move, etc.
        //the int code returned is passed to showGameOver and determines whether the game continues
        //identifyMoveFlow will run code for a normal move or additional move,
        //moving and deleting pieces as needed, and changing the BoardModel
        showGameOver(BoardModel.identifyMoveFlow(v, previousView));
        setCurrentPlayer();
        updateBoard();
        previousView = v;
    }

    //changes the UI to show which player's turn it is
    private void setCurrentPlayer() {
        if (BoardModel.redTurn) {
            currentPlayerImageView.setImageResource(R.drawable.red_piece);
            currentPlayerImageView2.setImageResource(R.drawable.red_piece);

        }
        else {
            currentPlayerImageView.setImageResource(R.drawable.white_piece);
            currentPlayerImageView2.setImageResource(R.drawable.white_piece);
        }
    }

    void beginNewGame() {
        if (previousView != null) {
            previousView.setBackgroundResource(R.drawable.black_square);
        }
        BoardModel.newGame();
        setCurrentPlayer();
        updateBoard();
    }

    //scans through the board and sets the views depending on what letter is stored in the
    // imaginaryBoard string array. Also counts the number of each type of piece for display
    //and determining if the game is over
    public void updateBoard() {
        int redCounter = 0;
        int whiteCounter = 0;
        int redKingCounter = 0;
        int whiteKingCounter = 0;
        int redNormalPieceCounter = 0;
        int whiteNormalPieceCounter = 0;

        //the imaginary board has 2 extra rows and columns on each side of the board.
        // The reason for this is to allow the movement logic to look into memory beyond the bounds of the
        //board without crashing the program, thus greatly simplifying the logic. The number two is used
        //in various array logic to account for the extra columns and rows.
        for (int i = 2; i < BoardModel.imaginaryBoard.length - 2; i++) {
            for(int j = 2; j < BoardModel.imaginaryBoard[i].length() - 2; j++) {

                char boardSpot = BoardModel.imaginaryBoard[i].charAt(j);
                boardView = findViewById(BoardModel.viewPositions[i - 2][j - 2]);

                if (boardSpot == 'b') {
                    boardView.setImageResource(R.drawable.black_square);
                }
                if (boardSpot == 'w') {
                    boardView.setImageResource(R.drawable.white_piece);
                    whiteCounter++;
                    whiteNormalPieceCounter++;
                }
                if (boardSpot == 'r') {
                    boardView.setImageResource(R.drawable.red_piece);
                    redCounter++;
                    redNormalPieceCounter++;
                }
                if (boardSpot == 'q') {
                    boardView.setImageResource(R.drawable.white_piece_king);
                    whiteCounter++;
                    whiteKingCounter++;
                }
                if (boardSpot == 'k') {
                    boardView.setImageResource(R.drawable.red_piece_king);
                    redCounter++;
                    redKingCounter++;
                }
                if (boardSpot == 'h') {
                    boardView.setImageResource(R.drawable.black_square_highlight);
                }
            }
        }
        //set values for piece counters
        redPieceCount.setText(String.valueOf(redNormalPieceCounter));
        redKingCount.setText(String.valueOf(redKingCounter));
        whitePieceCount.setText(String.valueOf(whiteNormalPieceCounter));
        whiteKingCount.setText(String.valueOf(whiteKingCounter));

        upsideRedPieceCount.setText(String.valueOf(redNormalPieceCounter));
        upsideRedKingCount.setText(String.valueOf(redKingCounter));
        upsideWhitePieceCount.setText(String.valueOf(whiteNormalPieceCounter));
        upsideWhiteKingCount.setText(String.valueOf(whiteKingCounter));

        //if there are no white pieces, red wins
        //if there are no red pieces, white wins
        if (whiteCounter == 0) {
            showGameOver(WHITE_OUT_OF_PIECES_LOSS);
        }
        if (redCounter == 0) {
            showGameOver(RED_OUT_OF_PIECES_LOSS);
        }
    }

    //ends game and produces an alert dialog depending on what caused the game to end
    void showGameOver(int lossCode) {

        if (lossCode == GAME_CONTINUE) {
            return;
        }

        previousView.setBackgroundResource(R.drawable.black_square);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Game Over");
        // Set Cancelable false for when the user clicks on the outside the Dialog Box then it will remain show
        builder.setCancelable(false);

        if (lossCode == WHITE_OUT_OF_PIECES_LOSS) {
            builder.setMessage("White is out of Pieces! Red Wins!\nWould you like to play again?");
        }
        if (lossCode == WHITE_CANT_MOVE) {
            builder.setMessage("White can't move! Red Wins!\nWould you like to play again?");
        }
        if (lossCode == RED_CANT_MOVE) {
            builder.setMessage("Red can't move! White Wins!\nWould you like to play again?");
        }
        if (lossCode == RED_OUT_OF_PIECES_LOSS) {
            builder.setMessage("Red is out of Pieces! White Wins!\nWould you like to play again?");
        }

        // Set the positive button with yes name Lambda OnClickListener method is use of DialogInterface interface.
        builder.setPositiveButton("Yes", (DialogInterface.OnClickListener) (dialog, which) -> {
            dialog.cancel();
            beginNewGame();
        });

        // Set the Negative button with No name Lambda OnClickListener method is use of DialogInterface interface.
        builder.setNegativeButton("No", (DialogInterface.OnClickListener) (dialog, which) -> {
            finish();
            System.exit(0);
        });

        // Create the Alert dialog
        AlertDialog alertDialog = builder.create();
        // Show the Alert Dialog box
        alertDialog.show();
    }
}
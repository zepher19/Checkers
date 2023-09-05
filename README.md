# Checkers
Hello there!

I'm currently looking for a coding job, so I decided to create a Checkers app to showcase my Java language competency.

Here's a quick demo video if you'd like to see the app in action:

https://www.youtube.com/watch?v=v00vlP1eLOI&ab_channel=Zepher319

The app is relatively simple. There are only two classes, Main Activity and BoardModel. MainActivity handles app functionality and UI changes. BoardModel handles all of the game's logic. 

So how does the game logic work?

BoardModel contains a String array called the "imaginaryBoard." Each String of the imaginaryBoard represents one row of the gameboard. So to find a particular space on the board, we find the row, i.e., the String at the row index, and then the column, i.e., the char located at the index of the string. Different characters were used to represent the different possible pieces. "0" represents a board space that cannot contain a piece, e.g., a red square or a spot beyond the edge of the board. "b" represents a blank space, a black square that may contain a piece but does not currently. "r" is for red piece. "w" is for white piece. "k" is for red king. "q" is for white king. "h" is for a highlighted square. 

A checkers board is 8x8, so why is the imaginary board 12x12?

To simplify movement logic, the game board, as well as each string, contains a bit of padding. The padding represents spaces beyond the bounds of the board, there simply to prevent game logic from attempting to access a string or index that is out of bounds. By doing this, I was able to have one tiny bit of code provide all the of the movement and highlighting rules. 

So what does normal flow look like?
When a player taps on a square, the program grabs the id of that particular ImageButton, and uses it to check the imaginaryBoard to figure out what kind of piece is currently there. If it is a player piece, say a red piece, and it is red's turn, the program first iterates through the imaginaryBoard to check whether red has any available legal moves. If red does not have any open moves, the game is over and white wins. If there is a legal move available somewhere on the board, the program will highlight nearby spaces that correspond to possible moves, which are determined by checking the imaginaryBoard to see what pieces are nearby. 





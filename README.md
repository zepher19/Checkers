# Checkers
Hello there!

I'm currently looking for a coding job, so I decided to create a Checkers app to showcase my Java language competency.

Here's a quick demo video if you'd like to see the app in action:

https://www.youtube.com/watch?v=v00vlP1eLOI&ab_channel=Zepher319

The app is relatively simple. There are only two classes, Main Activity and BoardModel. MainActivity handles app functionality and UI changes. BoardModel handles all of the game's logic. 

So how does the game logic work?

BoardModel contains a String array called the "imaginaryBoard." Each String of the imaginaryBoard represents one row of the gameboard. So to find a particular space on the board, we find the row, i.e., the String at the row index, and then the column, i.e., the char located at the index of the string.

A checkers board is 8x8, so why is the imaginary board 12x12?



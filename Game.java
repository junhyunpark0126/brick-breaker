/*  Name: Jun Hyun Park
*  PennKey: andrewpp
*  Recitation: Section 219
*
*  Execution: java Game [filename]
*
*  Represents the overall encompassing Brick Breaker game. Takes a level 
*  description text file and initializes a game arena with that data, then runs 
*  the game until the player wins or loses. When the player wins or loses, the
*  player has the option to restart the game without having to 
*  re-run the entire code.
*
*/

public class Game {

    public static void main(String[] args) {

        PennDraw.setCanvasSize(1000, 500);
        PennDraw.enableAnimation(30);

        Map map = new Map(args[0]);

        while (true) {
            while (!map.gameOver()) {
                map.update(0.2);
                map.draw();
            }

            while (map.gameOver()) {
                map.drawEndScreen();
                // Checks if the user clicks on the restart button
                if (PennDraw.mousePressed() && 
                map.checkClick(PennDraw.mouseX(), PennDraw.mouseY())) {
                    map = new Map(args[0]);
                }
            }
        }

    }
}
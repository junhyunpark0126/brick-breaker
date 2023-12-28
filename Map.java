/*  Name: Jun Hyun Park
*  PennKey: andrewpp
*  Recitation: Section 219
*
*  A class representing the map in which the Brick Breaker game takes place. Keeps 
*  track of the bricks, ball, and platform, and allows user input to control 
*  the position of the platform. Also handles miscellaneous aspects of the game
*  such as drawing the end screen and determining if a player has lost or won.
*
*/

public class Map {

    private int width, height;
    private Brick[] bricks;
    private Platform gamePlatform;
    private Ball gameBall;

    /*
    * Constructor for the Map class. Reads data from text file, sets the x and y 
    * scales for the game, and creates the bricks, platform, and ball objects
    */
    public Map(String filename) {

        In in = new In(filename);

        int numBricks = in.readInt();
        bricks = new Brick[numBricks];

        this.width = in.readInt();
        this.height = in.readInt();

        PennDraw.setXscale(0, this.width);
        PennDraw.setYscale(0, this.height);

        for (int i = 0; i < numBricks; i++) {
            bricks[i] = new Brick(in.readDouble(), in.readDouble(), in.readInt()); 
        }

        in.close();

        gamePlatform = new Platform(this.width, this.height, 1.5, 0.15, 
        this.width / 2, 0.15);
        gameBall = new Ball(this.width, this.height, 0.15, this.width / 2, 0.375);

    }

    /**
     * Inputs: none
     * Outputs: void
     * Description: Draws the bricks, platform, and ball
    */
    public void draw() {

        PennDraw.clear();

        for (int i = 0; i < bricks.length; i++) {
            bricks[i].draw();
        }

        gamePlatform.draw();
        gameBall.draw();

        PennDraw.advance();

    }

    /**
     * Inputs: a double time variable called timeStep
     * Outputs: void
     * Description: Updates the ball and platform with the parameter of timeStep. 
     * Also moves platform based on mouse location, tests collisions between
     * the ball and bricks / platform, changes ball direction based on
     * collisions, and resets the platform and ball positions if ball goes
     * out of bounds.
     * 
    */
    public void update(double timeStep) {

        gameBall.update(timeStep);
        
        // move platform to mouse x position
        if (PennDraw.mousePressed()) {
            // makes sure that platform can not go out of bounds
            if (PennDraw.mouseX() > this.width - 1.5 / 2) {
                gamePlatform.setXPos(this.width - 1.5 / 2);
            } else if (PennDraw.mouseX() < 1.5 / 2) {
                gamePlatform.setXPos(1.5 / 2);
            } else {
                gamePlatform.setXPos(PennDraw.mouseX());
            }
            gameBall.startBall();
        }
        
        gamePlatform.update(timeStep);

        for (int i = 0; i < bricks.length; i++) {
            gameBall.testCollisionAndSetBooleanBrick(bricks[i]);
        }

        // updateVelRight/Left/Top/Bottom responsible for ball acceleration
        for (int i = 0; i < bricks.length; i++) {
            if (bricks[i].getIsRightHit()) {
                gameBall.negateXVel();
                gameBall.updateVelRight(0.05);
                bricks[i].loseLife();
                bricks[i].setIsRightHit(false);
            }
            else if (bricks[i].getIsLeftHit()) {
                gameBall.negateXVel();
                gameBall.updateVelLeft(0.05);
                bricks[i].loseLife();
                bricks[i].setIsLeftHit(false);
            }
            else if (bricks[i].getIsTopHit()) {
                gameBall.negateYVel();
                gameBall.updateVelTop(0.05);
                bricks[i].loseLife();
                bricks[i].setIsTopHit(false);
            }
            else if (bricks[i].getIsBottomHit()) {
                gameBall.negateYVel();
                gameBall.updateVelBottom(0.05);
                bricks[i].loseLife();
                bricks[i].setIsBottomHit(false);
            }
            else {
                // filler code
                this.width += 0;
            }
        }

        /*
        * Ball goes right if right side of platform hit, goes left if left side of 
        * platform is hit
        */ 
        gameBall.testCollisionAndSetBooleanPlatform(gamePlatform);
        if (gamePlatform.isRightSideHit()) {
            gameBall.negateYVel();
            gameBall.bounceRight();
            gamePlatform.setIsRightSideHit(false);
        }
        if (gamePlatform.isLeftSideHit()) {
            gameBall.negateYVel();
            gameBall.bounceLeft();
            gamePlatform.setIsLeftSideHit(false);
        }

        if (isSideBoundaryHit()) {
            gameBall.negateXVel();
        }
        if (isTopBoundaryHit()) {
            gameBall.negateYVel();
        }
        if (isOutOfBounds()) {
            gameBall.loseLife();
            gameBall.reset();
            gamePlatform.reset();
        }

        // keeps track of score
        for (int i = 0; i < bricks.length; i++) {
            if (bricks[i].getHealth() == 0 && !bricks[i].getCounted()) {
                gameBall.gainScore();
                bricks[i].setCounted(true);
            }
        }

    }

    /**
     * Inputs: none
     * Outputs: boolean of if the side boundary is hit
     * Description: returns true or false depending on if the ball has collided with 
     * the side boundary of the map
     * 
    */
    public boolean isSideBoundaryHit() {
        return gameBall.getXPos() > this.width - gameBall.getRadius() || 
        gameBall.getXPos() < gameBall.getRadius();
    }

    /**
     * Inputs: none
     * Outputs: boolean of if the top boundary is hit
     * Description: returns true or false depending on if the ball has collided with
     * the top boundary of the map
     * 
    */
    public boolean isTopBoundaryHit() {
        return gameBall.getYPos() > this.height - gameBall.getRadius();
    }

    /**
     * Inputs: none
     * Outputs: boolean of if the ball goes out of bounds
     * Description: returns true or false depending on if the ball has went out of 
     * bounds through the bottom of the map
     * 
    */
    public boolean isOutOfBounds() {
        return gameBall.getYPos() < -gameBall.getRadius();
    }

    /**
     * Inputs: none
     * Outputs: boolean of if the player won
     * Description: returns true or false depending on if the player has destroyed
     * all the bricks or not
     * 
    */
    private boolean didPlayerWin() {
        int sum = 0;
        for (int i = 0; i < bricks.length; i++) {
            sum += bricks[i].getHealth();
        }
        return sum == 0;
    }

    /**
     * Inputs: none
     * Outputs: boolean of if the player lost
     * Description: returns true or false depending on if the player has lost all
     * lives
     * 
    */
    private boolean didPlayerLose() {
        return gameBall.getLivesRemaining() == 0;
    }

    /**
     * Inputs: none
     * Outputs: boolean of if the game is over
     * Description: returns true if the player has either won or lost
     * 
    */
    public boolean gameOver() {
        return didPlayerWin() || didPlayerLose();
    }

    /**
     * Inputs: double of mouse x position mouseX, double of mouse y position mouseY
     * Outputs: boolean of whether a certain location has been clicked
     * Description: returns true if the x and y position of the mouse are within
     * the bounds specified in the function
     * 
    */
    public boolean checkClick(double mouseX, double mouseY) {
        double top = this.height / 2 + 1.5 + 0.35;
        double bottom = this.height / 2 + 1.5 - 0.35;
        double left = this.width / 2 - 0.35;
        double right = this.width / 2 + 0.35;

        return mouseY < top &&
                mouseY > bottom &&
                mouseX > left &&
                mouseX < right;
    }

    /**
     * Inputs: none
     * Outputs: void
     * Description: gives victory or loss screen depending on if the player won or
     * lost ; also draws out the restart button
     * 
    */
    public void drawEndScreen() {

        PennDraw.clear();

        if (didPlayerWin()) {
            PennDraw.text(this.width / 2, this.height / 2, 
            "You Win! " + "Play Again?");
        }
        if (didPlayerLose()) {
            PennDraw.text(this.width / 2, this.height / 2, 
            "You Lose! " + "Play Again?");
        }

        PennDraw.setPenColor(PennDraw.YELLOW);
        PennDraw.filledSquare(this.width / 2, this.height / 2 + 1.5, 0.35);
        PennDraw.setPenColor(PennDraw.BLACK);
        PennDraw.square(this.width / 2, this.height / 2 + 1.5, 0.355);

        PennDraw.text(this.width / 2, this.height / 2 + 1.5, "Restart");

        PennDraw.advance();
    }


}
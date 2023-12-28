/*  Name: Jun Hyun Park
*
*  A class that represents the ball in Brick Breaker. The ball will be able to move
* and handle all of the collisions between the blocks and the boundaries of the map
*/

public class Ball {

    private double width, height, radius;
    private double xPos, yPos;
    private double xVel, yVel;
    private int livesRemaining;
    private int score;

    /*
    * Constructor for Ball class. Sets initial x and y velocities to 0, as well as 
    * lives remaining to 3 and score to 0
    */
    public Ball(double width, double height, double radius, double xPos, 
    double yPos) {
        this.width = width;
        this.height = height;
        this.radius = radius;
        this.xPos = xPos;
        this.yPos = yPos;
        xVel = 0;
        yVel = 0;
        livesRemaining = 3;
        score = 0;
    }

    /**
     * Inputs: none
     * Outputs: void
     * Description: Draws the ball and lives remaining and score onto the screen
    */
    public void draw() {
        PennDraw.setPenColor(PennDraw.BLUE);
        PennDraw.filledCircle(xPos, yPos, radius);
        PennDraw.setPenColor(PennDraw.BLACK);
        PennDraw.text(0.85, 4.5, "Lives Remaining: " + this.livesRemaining);
        PennDraw.text(9.5, 4.5, "Score: " + this.score);
    }

    /**
     * Inputs: double time variable called timeStep
     * Outputs: void
     * Description: updates x and y positions of ball based on velocities and
     * timeStep
    */
    public void update(double timeStep) {
        xPos += xVel * timeStep;
        yPos += yVel * timeStep;
    }

    /**
     * Inputs: double of velocity value vel
     * Outputs: void
     * Description: accelerates the ball by vel if right side of brick hit
    */
    public void updateVelRight(double vel) {
        xVel += vel;
        if (yVel > 0) {
            yVel += vel;
        }
        if (yVel < 0) {
            yVel -= vel;
        }
    }

    /**
     * Inputs: double of velocity value vel
     * Outputs: void
     * Description: accelerates the ball by vel if left side of brick hit
    */
    public void updateVelLeft(double vel) {
        xVel -= vel;
        if (yVel > 0) {
            yVel += vel;
        }
        if (yVel < 0) {
            yVel -= vel;
        }
    }

    /**
     * Inputs: double of velocity value vel
     * Outputs: void
     * Description: accelerates the ball by vel if top side of brick hit
    */
    public void updateVelTop(double vel) {
        yVel += vel;
        if (xVel > 0) {
            xVel += vel;
        }
        if (xVel < 0) {
            xVel -= vel;
        }
    }

    /**
     * Inputs: double of velocity value vel
     * Outputs: void
     * Description: accelerates the ball by vel if bottom side of brick hit
    */
    public void updateVelBottom(double vel) {
        yVel -= vel;
        if (xVel > 0) {
            xVel += vel;
        }
        if (xVel < 0) {
            xVel -= vel;
        }
    }

    /**
     * Inputs: none
     * Outputs: void
     * Description: gives ball initial velocity only if it was previously stationary
     * (called upon when the user first clicks to move the platform and start game)
    */
    public void startBall() {
        if (xVel == 0 && yVel == 0) {
            xVel = 0.2;
            yVel = 0.2;
        }
    }

    /**
     * Inputs: none
     * Outputs: double of radius
     * Description: getter function for radius
    */
    public double getRadius() {
        return this.radius;
    }

    /**
     * Inputs: none
     * Outputs: double of ball x position
     * Description: getter function for xPos
    */
    public double getXPos() {
        return this.xPos;
    }

    /**
     * Inputs: none
     * Outputs: double of ball y position
     * Description: getter function for yPos
    */
    public double getYPos() {
        return this.yPos;
    }

    /**
     * Inputs: none
     * Outputs: void
     * Description: switches x direction of ball movement
    */
    public void negateXVel() {
        this.xVel *= -1;
    }

    /**
     * Inputs: none
     * Outputs: void
     * Description: switches y direction of ball movement
    */
    public void negateYVel() {
        this.yVel *= -1;
    }

    /**
     * Inputs: none
     * Outputs: void
     * Description: makes ball go left (called upon when ball hits left side of 
     * platform)
    */
    public void bounceLeft() {
        this.xVel = Math.abs(this.xVel) * -1;
    }

    /**
     * Inputs: none
     * Outputs: void
     * Description: makes ball go right (called upon when ball hits right side of 
     * platform)
    */
    public void bounceRight() {
        this.xVel = Math.abs(this.xVel);
    }

    /**
     * Inputs: none
     * Outputs: void
     * Description: decrements livesRemaining
    */
    public void loseLife() {
        livesRemaining--;
    }

    /**
     * Inputs: none
     * Outputs: integer of lives remaining
     * Description: getter function for livesRemaining
    */
    public int getLivesRemaining() {
        return this.livesRemaining;
    }

    /**
     * Inputs: none
     * Outputs: void
     * Description: resets ball position to its original location and stop ball
     * movement
    */
    public void reset() {
        xVel = 0;
        yVel = 0;
        xPos = this.width / 2;
        yPos = 0.375;
    }

    /**
     * Inputs: object of Brick class called thisBrick
     * Outputs: void
     * Description: tests to see if ball has collided with any of the sides of 
     * thisBrick ; if the ball has collided, sets a certain boolean value of the
     * brick to true or false
    */
    public void testCollisionAndSetBooleanBrick(Brick thisBrick) {
        if (thisBrick.getHealth() > 0) {
            if ((this.xPos < thisBrick.getXPos() + thisBrick.getHalfWidth() + 
            this.radius) && (this.xPos > thisBrick.getXPos()) && 
            (this.yPos < thisBrick.getYPos() + thisBrick.getHalfHeight()) && 
            (this.yPos > thisBrick.getYPos() - thisBrick.getHalfHeight()) && 
            (this.xVel < 0)) {
                thisBrick.setIsRightHit(true);
            }
            if ((this.xPos > thisBrick.getXPos() - thisBrick.getHalfWidth() - 
            this.radius) && (this.xPos < thisBrick.getXPos()) && 
            (this.yPos < thisBrick.getYPos() + thisBrick.getHalfHeight()) && 
            (this.yPos > thisBrick.getYPos() - thisBrick.getHalfHeight()) && 
            (this.xVel > 0)) {
                thisBrick.setIsLeftHit(true);
            }
            if ((this.yPos < thisBrick.getYPos() + thisBrick.getHalfHeight() + 
            this.radius) && (this.yPos > thisBrick.getYPos()) && 
            (this.xPos < thisBrick.getXPos() + thisBrick.getHalfWidth()) && 
            (this.xPos > thisBrick.getXPos() - thisBrick.getHalfWidth()) && 
            (this.yVel < 0)) {
                thisBrick.setIsTopHit(true);
            }
            if ((this.yPos > thisBrick.getYPos() - thisBrick.getHalfHeight() - 
            this.radius) && (this.yPos < thisBrick.getYPos()) && 
            (this.xPos < thisBrick.getXPos() + thisBrick.getHalfWidth()) && 
            (this.xPos > thisBrick.getXPos() - thisBrick.getHalfWidth()) && 
            (this.yVel > 0)) {
                thisBrick.setIsBottomHit(true);
            }
        }
    }

    /**
     * Inputs: object of Platform class called thisPlatform
     * Outputs: void
     * Description: tests to see if the ball has collided with either the 
     * left or right side of the platform ; if the ball has collided, sets a
     * certain boolean value of the platform to true or false
    */
    public void testCollisionAndSetBooleanPlatform(Platform thisPlatform) {
        if ((this.yPos < thisPlatform.getYPos() + thisPlatform.getHalfHeight() + 
        this.radius) && (this.yPos > thisPlatform.getYPos()) && 
        (this.xPos < thisPlatform.getXPos() + thisPlatform.getHalfWidth()) && 
        (this.xPos > thisPlatform.getXPos()) && (this.yVel < 0)) {
                thisPlatform.setIsRightSideHit(true);
            }
        if ((this.yPos < thisPlatform.getYPos() + thisPlatform.getHalfHeight() + 
        this.radius) && (this.yPos > thisPlatform.getYPos()) && 
        (this.xPos > thisPlatform.getXPos() - thisPlatform.getHalfWidth()) && 
        (this.xPos < thisPlatform.getXPos()) && (this.yVel < 0)) {
                thisPlatform.setIsLeftSideHit(true);
            }
    }

    /**
     * Inputs: none
     * Outputs: void
     * Description: increments score
    */
    public void gainScore() {
        this.score++;
    }

}

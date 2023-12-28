/*  Name: Jun Hyun Park
*  PennKey: andrewpp
*  Recitation: Section 219
*
*  A class that represents the brick in Brick Breaker. Bricks cannot move
* but have aspects such as health and can change color
*/

public class Brick {

    private double xPos, yPos;
    private double halfWidth, halfHeight;
    private int health;
    private boolean isRightHit, isLeftHit, isTopHit, isBottomHit;
    private boolean counted;

    /*
    * Constructor for the Brick class. Sets isRight/Left/Top/BottomHit to false
    * and counted to false.
    */
    public Brick(double xPos, double yPos, int health) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.health = health;
        this.halfWidth = 0.8;
        this.halfHeight = 0.15;
        isRightHit = false;
        isLeftHit = false;
        isTopHit = false;
        isBottomHit = false;
        counted = false;
    }

    /**
     * Inputs: none
     * Outputs: void
     * Description: draws brick ; shade of brick depends on brick's health
    */
    public void draw() {
        if (this.health == 3) {
            PennDraw.setPenColor(255, 0, 0);
            PennDraw.filledRectangle(this.xPos, this.yPos, this.halfWidth, 
            this.halfHeight);
        }
        if (this.health == 2) {
            PennDraw.setPenColor(255, 140, 140);
            PennDraw.filledRectangle(this.xPos, this.yPos, this.halfWidth, 
            this.halfHeight);
        }
        if (this.health == 1) {
            PennDraw.setPenColor(255, 220, 220);
            PennDraw.filledRectangle(this.xPos, this.yPos, this.halfWidth, 
            this.halfHeight);
        }
    }

    /**
     * Inputs: none
     * Outputs: double of x position of brick
     * Description: getter function for x position of brick
    */
    public double getXPos() {
        return this.xPos;
    }

    /**
     * Inputs: none
     * Outputs: double of y position of brick
     * Description: getter function for y position of brick
    */
    public double getYPos() {
        return this.yPos;
    }

    /**
     * Inputs: none
     * Outputs: double of halfWidth
     * Description: getter function for brick halfWidth
    */
    public double getHalfWidth() {
        return this.halfWidth;
    }

    /**
     * Inputs: none
     * Outputs: double of halfHeight
     * Description: getter function for brick halfHeight
    */
    public double getHalfHeight() {
        return this.halfHeight;
    }

    /**
     * Inputs: boolean of hit
     * Outputs: void
     * Description: setter function for isRightHit
    */
    public void setIsRightHit(boolean hit) {
        this.isRightHit = hit;
    }

    /**
     * Inputs: boolean of hit
     * Outputs: void
     * Description: setter function for isLeftHit
    */
    public void setIsLeftHit(boolean hit) {
        this.isLeftHit = hit;
    }

    /**
     * Inputs: boolean of hit
     * Outputs: void
     * Description: setter function for isTopHit
    */
    public void setIsTopHit(boolean hit) {
        this.isTopHit = hit;
    }

    /**
     * Inputs: boolean of hit
     * Outputs: void
     * Description: setter function for isBottomHit
    */
    public void setIsBottomHit(boolean hit) {
        this.isBottomHit = hit;
    }

    /**
     * Inputs: none
     * Outputs: boolean of isRightHit
     * Description: getter function for isRightHit
    */
    public boolean getIsRightHit() {
        return isRightHit;
    }

    /**
     * Inputs: none
     * Outputs: boolean of isLeftHit
     * Description: getter function for isLeftHit
    */
    public boolean getIsLeftHit() {
        return isLeftHit;
    }

    /**
     * Inputs: none
     * Outputs: boolean of isTopHit
     * Description: getter function for isTopHit
    */
    public boolean getIsTopHit() {
        return isTopHit;
    }

    /**
     * Inputs: none
     * Outputs: boolean of isBottomHit
     * Description: getter function for isBottomHit
    */
    public boolean getIsBottomHit() {
        return isBottomHit;
    }

    /**
     * Inputs: none
     * Outputs: void
     * Description: decrements health
    */
    public void loseLife() {
        if (health > 0) {
            health--;
        }
    }

    /**
     * Inputs: none
     * Outputs: integer of health
     * Description: getter function for health
    */
    public int getHealth() {
        return this.health;
    }

    /**
     * Inputs: none
     * Outputs: boolean of counted
     * Description: getter function of counted
    */
    public boolean getCounted() {
        return this.counted;
    }

    /**
     * Inputs: boolean set
     * Outputs: void
     * Description: setter function for counted
    */
    public void setCounted(boolean set) {
        this.counted = set;
    }

}
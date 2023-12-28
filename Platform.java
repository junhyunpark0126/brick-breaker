/*  Name: Jun Hyun Park
*
*  A class that represents the platform in Brick Breaker. Platform is free to move
*  only in the x direction
*/

public class Platform {

    private double gameWidth, gameHeight;
    private double platWidth, platHeight;
    private double xPos, yPos;
    private double vel;
    private boolean isRightSideHit, isLeftSideHit;

    /*
    * Constructor for Platform class. Makes initial velocity 0.
    */
    public Platform(double gameWidth, double gameHeight, double platWidth, 
    double platHeight, double xPos, double yPos) {
        this.gameWidth = gameWidth;
        this.gameHeight = gameHeight;
        this.platWidth = platWidth;
        this.platHeight = platHeight;
        this.xPos = xPos;
        this.yPos = yPos;
        vel = 0;
    }

    /**
     * Inputs: none
     * Outputs: void
     * Description: draws the platform in black
    */
    public void draw() {
        PennDraw.setPenColor(0, 0, 0);
        PennDraw.filledRectangle(xPos, yPos, platWidth / 2, platHeight / 2);
    }

    /**
     * Inputs: double time variable called timeStep
     * Outputs: void
     * Description: updates position of platform based on velocity and timeStep
    */
    public void update(double timeStep) {
        xPos += vel * timeStep;
    }

    /**
     * Inputs: double of x position called input
     * Outputs: void
     * Description: setter function for x position of platform
    */
    public void setXPos(double input) {
        xPos = input;
    }

    /**
     * Inputs: none
     * Outputs: double of y position of platform
     * Description: getter function for y position of platform
    */
    public double getYPos() {
        return this.yPos;
    }

    /**
     * Inputs: none
     * Outputs: double of x position of platform
     * Description: getter function for x position of platform
    */
    public double getXPos() {
        return this.xPos;
    }

    /**
     * Inputs: none
     * Outputs: double of halfHeight of platform
     * Description: getter function for halfHeight of platform
    */
    public double getHalfHeight() {
        return platHeight / 2;
    }
    
    /**
     * Inputs: none
     * Outputs: double of halfWidth of platform
     * Description: getter function for halfWidth of platform
    */
    public double getHalfWidth() {
        return platWidth / 2;
    }

    /**
     * Inputs: none
     * Outputs: boolean of if right side of platform is hit
     * Description: getter function for isRightSideHit
    */
    public boolean isRightSideHit() {
        return isRightSideHit;
    }

    /**
     * Inputs: none
     * Outputs: boolean of if left side of platform is hit
     * Description: getter function for isLeftSideHit
    */
    public boolean isLeftSideHit() {
        return isLeftSideHit;
    }

    /**
     * Inputs: none
     * Outputs: void
     * Description: puts the platform back to its original position with 0 velocity
    */
    public void reset() {
        vel = 0;
        xPos = this.gameWidth / 2;
    }

    /**
     * Inputs: boolean called hit
     * Outputs: void
     * Description: setter function for isRightSideHit
    */
    public void setIsRightSideHit(boolean hit) {
        this.isRightSideHit = hit;
    }

    /**
     * Inputs: boolean called hit
     * Outputs: void
     * Description: setter function for isLeftSideHit
    */
    public void setIsLeftSideHit(boolean hit) {
        this.isLeftSideHit = hit;
    }

}

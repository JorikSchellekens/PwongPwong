import java.awt.Color;
import java.util.Random;

/**
 * Created by Jorik on 25/01/2017.
 */
class	Ball extends PhysicsObject{
  private PongPingPong parent;
  private Color ballColor = Constants.BALL_COLOR;
  private Random random = new Random();

  private int diameter;
  private int radius;

  Ball(PongPingPong p, int size) {
    parent = p;
    reset();
    diameter = size;
    radius = diameter / 2;
  }

  public void move(){
    position.add(velocity);
  }

  public void draw(){
    parent.fill(ballColor.getRGB());
    parent.ellipse((int) getX(),	(int) getY(), diameter, diameter);
  }

  public void collide(Paddle p){
    float y = getY();
    float x = getX();
    if (y + radius >= p.getY() &&
        y - radius < p.getY() + p.paddleHeight &&
        x >= p.getX() &&
        x <= p.getX() + p.paddleWidth) {
      System.out.println("Paddle collide!");

      bounceY(p);

      setVelocityX((float) (getVelX() + 0.1 * p.getVelX()));
      switch(p.side) {
        case Constants.TOP:
          setPositionY(p.getY() + p.paddleHeight + radius);
          break;
        case Constants.BOTTOM:
          setPositionY(p.getY() - radius);
          break;
        case Constants.LEFT:
          break;
        case Constants.RIGHT:
          break;
      }
    }
  }

  public void wallCollide() {
    float x = getX();
    if(x - radius <= 0) {
      setPositionX(radius);
      bounceX();
    }
    else if (x + radius >= parent.width) {
      setPositionX(parent.width - radius);
      bounceX();
    }
  }

  public void reset() {
    float x = (float) ((random.nextDouble() * 0.5 + 0.25) * parent.width);
    float y = (float) ((random.nextDouble() * 0.33 + 0.33) * parent.height);
    setPosition(x, y);
    float dx = (float) ((random.nextDouble() + 1) * (random.nextInt(3) - 1));
    float dy = (float) (random.nextDouble() + 1);
    setVelocity(dx, dy);
    float ddxy = (float) 1.001;
    setAcceleration(ddxy);
  }

  public void checkScore(Paddle p, double scoreLine) {
    boolean scored = false;

    switch (p.side) {
      case Constants.TOP:
        if (getY() < scoreLine) scored = true;
        break;
      case Constants.BOTTOM:
        if (getY() > scoreLine) scored = true;
        break;
      case Constants.LEFT:
        if (getX() < scoreLine) scored = true;
        break;
      case Constants.RIGHT:
        if (getX() > scoreLine) scored = true;
        break;
    }

    if (scored) {
      p.loseLife();
      reset();
      parent.gamePaused = true;
      parent.clearTrail();
    }
  }

  void bounceY(Paddle p) {

    inflectY();
    if (parent.millis() - parent.timeSinceSpacePressed < Constants.TIME_WINDOW)
      setVelocityY(getVelY() * Constants.BOOST);
    else setVelocityY(getVelY() * Constants.RESTITUTION);
  }

  void bounceX() {
    inflectX();
    setVelocityX(getVelX() * Constants.RESTITUTION);
  }
}
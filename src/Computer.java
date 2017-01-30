import com.sun.tools.internal.jxc.ap.Const;

/**
 * Created by Jorik on 25/01/2017.
 */
public class Computer extends Paddle {
  Ball ball;
  int difficulty;
  int callCount = 0;
  int increase = Constants.FRAME_RATE * Constants.SECONDS_PER_DIFFICULTY_INCREMENT;

  Computer(int	screen_y, PongPingPong p)
  {
    setParent(p);
    setPosition(parent.width/2, screen_y);
    setDifficulty(Constants.DIFFICULTY_1);

    side = Constants.TOP;
    gameOverMessage = Constants.GAME_WON.toUpperCase();
    paddleColor = Constants.COMPUTER_COLOR;
  }

  public void setTarget(Ball b) {ball = b;}
  public void setDifficulty(int d) {
    difficulty = d;
    System.out.println("CHANGING MODE: " + d);
  }

  public void move() {
    switch(difficulty) {
      case Constants.DIFFICULTY_1:
        moveTowardBall();
        break;
      case Constants.DIFFICULTY_2:
        moveProportionally();
        break;
      case Constants.DIFFICULTY_3:
        //alwaysCorrect();
        break;
    }
  }

  private void moveTowardBall() {
    if (ballIsAproaching())
    moveToX(position.x + ((ball.getX() > position.x + 0.5 * (paddleWidth))?2:-2));
  }

  private void moveProportionally() {
    //if (ballIsAproaching())
    moveToX((float) (position.x + (ball.getX() - (position.x + 0.5 * paddleWidth)) * 0.1));
  }

  private boolean ballIsAproaching() {
    switch(side) {
      case Constants.TOP:
        return ball.getVelY() < 0;
      case Constants.BOTTOM:
        return ball.getVelY() > 0;
      case Constants.LEFT:
        return ball.getVelX() < 0;
      case Constants.RIGHT:
        return ball.getVelX() > 0;
    }
    return false;
  }


  void alwaysCorrect() {
    if (true);
  }

  public void increaseDifficulty() {
    callCount ++;
    if (callCount % increase == 0 && difficulty < 2) {
      difficulty++;
      setDifficulty(difficulty);
      callCount = 0;
    }
  }

  @Override
  public void reset() {
    super.reset();
    setDifficulty(Constants.DIFFICULTY_1);
  }
}

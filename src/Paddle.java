import java.awt.Color;

public class Paddle extends PhysicsObject{
  PongPingPong parent;

  int paddleWidth = Constants.PADDLE_WIDTH;
  int paddleHeight = Constants.PADDLE_HEIGHT;
  short lives = Constants.LIVES;

  String side;
  String gameOverMessage;
  Color paddleColor;

  Paddle() {
    setVelocity(0, 0);
  }

  //setters
  void setParent(PongPingPong p){parent = p;}
  void setPaddleWidth(int width){paddleWidth = width;}
  void setPaddleHeight(int height){paddleHeight = height;}
  void setSide(String direction){
    side = direction;}

  void moveToX(float	x){
    float previousX = getX();
    if (x < 0) position.x = 0;
    else if(x > parent.width - paddleWidth)	setPositionX(parent.width - paddleWidth);
    else	setPositionX(x);
    setVelocityX(getX() - previousX);
  }

  void draw()
  {
    parent.fill(paddleColor.getRGB());
    parent.rect(position.x, position.y,	paddleWidth, paddleHeight);
  }

  void loseLife() {
    lives--;
    if (lives == 0) parent.gameOver(gameOverMessage);
  }

  void reset() {
    lives = Constants.LIVES;
  }

}

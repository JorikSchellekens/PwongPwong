import java.awt.Color;

/**
 * Created by Jorik on 25/01/2017.
 */
public class Player extends Paddle {
  private static double amt = 0.05;
  Player(int	screen_y, PongPingPong p)
  {
    setParent(p);
    setPosition(parent.width/2, screen_y);

    side = Constants.BOTTOM;
    gameOverMessage = Constants.GAME_LOST.toUpperCase();
    paddleColor = Constants.PLAYER_COLOR;
  }

  public void move() {moveToX(parent.mouseX - paddleWidth / 2);}

  @Override
  public void draw() {

    paddleColor = new Color(parent.lerpColor(paddleColor.getRGB(), Constants.PLAYER_COLOR.getRGB(), (float) amt));
    super.draw();
  }

  public void pulse() {
    paddleColor = Constants.PULSE_COLOR;
  }
}

import processing.core.PApplet;

/**
 * Created by Jorik on 25/01/2017.
 */
public class PongPingPong extends PApplet {

  private Player player;
  private Computer computer;
  private Ball ball;
  private Force gravity;
  private boolean gameFinished = false;
  public boolean gamePaused = true;
  public int timeSinceSpacePressed;
  private String gameOverMessage;

  public static void main (String[] args) {
    PApplet.main("PongPingPong");
  }

  public void settings() {
    size(Constants.SCREEN_X, Constants.SCREEN_Y);
    //fullScreen(0);
  }

  public void setup() {
    frameRate(Constants.FRAME_RATE);
    ellipseMode(CENTER);
    textAlign(CENTER);
    noStroke();
    textFont(createFont("Impact", 80));

    player = new Player(width - Constants.MARGIN - Constants.PADDLE_HEIGHT, this);
    computer = new Computer(Constants.MARGIN, this);
    ball = new Ball(this, 20);
    computer.setTarget(ball);
    gravity = new Force(0, Constants.GRAVITY);
  }

  public void draw() {
    if (gameFinished) showGameOver();
    else {
      blurMotion();
      player.move();
      computer.move();
      if (!gamePaused) {
        //ball.accelerate();
        gravity.apply(ball);
        ball.move();
        ball.collide(player);
        ball.collide(computer);
        ball.wallCollide();
        ball.checkScore(player, height);
        ball.checkScore(computer, 0);
      }

      player.draw();
      computer.draw();
      ball.draw();
      computer.increaseDifficulty();
    }
  }

  public void mousePressed() {
    if (gameFinished || gamePaused) {

    }
    gameFinished = false;
    gamePaused = false;
  }

  public void gameOver(String message) {
    gameOverMessage = message;
    gameFinished = true;
    player.reset();
    computer.reset();
  }

  public void showGameOver() {
    background(Constants.GAME_OVER_BG_COLOR.getRGB());
    text(gameOverMessage, width / 2, height / 2);
  }

  void blurMotion() {
    fill(Constants.BACKGROUND_COLOR.getRGB());
    rect(0, 0, width, height);
  }

  public void keyPressed() {
    timeSinceSpacePressed = millis();
    player.pulse();
  }

  public void clearTrail() {
    int r = Constants.BACKGROUND_COLOR.getRed();
    int g = Constants.BACKGROUND_COLOR.getGreen();
    int b = Constants.BACKGROUND_COLOR.getBlue();
    background(r, g, b);
  }
}

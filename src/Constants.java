import java.awt.Color;

/**
 * Created by Jorik on 25/01/2017.
 */
public final class Constants {
  final	static int SCREEN_X	=	500;
  final	static int SCREEN_Y	=	500;
  final static int FRAME_RATE = 100;
  final	static int MARGIN	=	20;
  final static int PADDLE_HEIGHT = 10;
  final static int PADDLE_WIDTH = 100;
  final static int LIVES = 3;
  final static int SECONDS_PER_DIFFICULTY_INCREMENT = 30;
  final static int DIFFICULTY_1 = 1;
  final static int DIFFICULTY_2 = 2;
  final static int DIFFICULTY_3 = 3;
  final static int TIME_WINDOW = 60;

  final static float RESTITUTION = (float) 0.95;
  final static float BOOST = (float) 1.3;
  final static float GRAVITY = (float) 0.05;

  final static double BALL_ACCELERATION = 1.0001;

  final static String TOP = "u";
  final static String LEFT = "l";
  final static String RIGHT = "r";
  final static String BOTTOM = "d";
  final static String GAME_LOST = "You lose bro.";
  final static String GAME_WON = "You win, go \nget yourself \nnachos.";

  final static Color BACKGROUND_COLOR = new Color(50,0,0,50);
  final static Color GAME_OVER_BG_COLOR = new Color(0);
  final static Color PLAYER_COLOR = new Color(50, 255, 50);
  final static Color COMPUTER_COLOR = new Color(50, 255, 50);
  final static Color PULSE_COLOR = new Color(50, 50, 255);
  final static Color BALL_COLOR = new Color(200, 100, 50);
}

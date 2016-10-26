import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Ball {
	private static final int DIAMETER = 10;
	
	int x;//x coordinate
	int y;//y coordinate
	int xa = 1;//x speed
	int ya = 1;//y speed
	boolean passMid;
	private Game game;

	public Ball(Game game) {
		this.game = game;
		x = 10;
		y = 0;
	}

	void move(boolean pause) {
		if(pause){
			boolean changeDirection = true;
			if (x + xa < 0){//Check for left corner of the window
				game.score_player2++;
				game.gameOver();
			}
			else if (x + xa > game.getWidth() - DIAMETER){//Check for right corner of the window
				game.score_player1++;
				game.gameOver();
			}
			else if (y + ya < 0)//Check for upper corner of the window
				ya = game.speed;
			else if (y + ya > game.getHeight() - DIAMETER)//Check for lower corner of the window
				ya = -game.speed;
			else if (collision_1()){
				xa = game.speed;//Sets the speed of the ball to the speed of the game 	
				if((game.ctr+1) % 6 == 0){//every five turns, the speed of the ball increases
					game.speed++;
				}
				game.ctr++;
				if(!game.getpowerUP1()){
					if(game.time1 == 2){
						game.racquetTwo.setHeight(true);
					}
					game.time1++;
				}

			}
			else if (collision_2()){
				xa = -game.speed;				
				if((game.ctr+1) % 6 == 0){//every five turns, the speed of the ball increases
					game.speed++;
				}
				game.ctr++;
				if(!game.getpowerUP2()){
					if(game.time2 == 2){
						game.racquetOne.setHeight(true);
					}
					game.time2++;
				}
			}

			else{
				changeDirection = false;
			}

			if (changeDirection){
				Sound.BALL.play();
			}
				x = x + xa;
				y = y + ya;
		}
	}

	private boolean collision_1() {//Checks if the position of the ball is in the area of the racquet
		return game.racquetOne.getBounds().intersects(getBounds());// return a boolean value
	}
	private boolean collision_2() {//Checks if the position of the ball is in the area of the racquet
		return game.racquetTwo.getBounds().intersects(getBounds());// return a boolean value
	}

	public void paint(Graphics2D g) {
		g.fillOval(x, y, DIAMETER, DIAMETER);//Paints the circle given its x and y coordinates
	}

	public Rectangle getBounds() {
		return new Rectangle(x, y, DIAMETER, DIAMETER);//Position of the rectangle
	}
}
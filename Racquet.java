import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;

public class Racquet {
	private int X;
	private int WIDTH = 10;
	private int HEIGHT = 60;
	private Game game;
	int ya; 
	int y;

	public Racquet(Game game, int pos, int y, int ya) {
		this.game = game;
		this.y = y;
		this.ya = ya;
		this.X = pos;
	}

	public void setHeight(boolean min){
		if(min){
			HEIGHT *= 2;
		}
		else{
			HEIGHT /= 2;
		}
	}	
	public void move() {
		if (y + ya > 0 && y + ya < game.getHeight() - HEIGHT)
			y = y + ya;
	}

	public void paint(Graphics2D g) {
		g.fillRect(X, y, WIDTH, HEIGHT);
	}

	public void keyReleased(KeyEvent e) {
	}

	public void keyPressed(KeyEvent e) {
	}

	public Rectangle getBounds() {
		return new Rectangle(X, y, WIDTH, HEIGHT);
	}

	public int getTop() {
		return X + WIDTH;// y coordinate of the racquet plus its width
	}
}
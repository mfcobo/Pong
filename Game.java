import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.util.*;	
import java.awt.*;
import java.awt.event.*;
@SuppressWarnings("serial")
public class Game extends JPanel {
	Ball ball = new Ball(this);
	Racquet racquetOne = new Racquet(this,5,110,0);
	Racquet racquetTwo = new Racquet(this,370,110,0);
	boolean pause = true;
	boolean powerUP1 = true;
	boolean powerUP2 = true;
	String nameP1, nameP2;
	int LENGTH = 400;
	int WIDTH = 300;
	int score_player1 = 0;
	int score_player2 = 0;
	int time1=0;
	int time2=0;
	int speed = 1;
	int ctr = 0;

	public boolean getpowerUP1(){
		return powerUP1;
	}
	public void setpowerUP1(){
		powerUP1 = !powerUP1;
	}
	public boolean getpowerUP2(){
		return powerUP2;
	}
	public void setpowerUP2(){
		powerUP2 = !powerUP2;
	}

	public Game() {		
		addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
			}

			@Override
			public void keyReleased(KeyEvent e) {
				racquetTwo.ya = 0;
				racquetOne.ya = 0;
			}

			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_W) 
					racquetOne.ya = -2;
				else if(e.getKeyCode() == KeyEvent.VK_UP)
					racquetTwo.ya = -2;
				else if (e.getKeyCode() == KeyEvent.VK_S) 
					racquetOne.ya = 2;
				else if(e.getKeyCode() == KeyEvent.VK_DOWN)
					racquetTwo.ya = 2;
				else if(e.getKeyCode() == KeyEvent.VK_SPACE){
					pause();
				}
				else if(e.getKeyCode() == KeyEvent.VK_E && getpowerUP1()){
					powerUP(true);
					setpowerUP1();
				}
				else if(e.getKeyCode() == KeyEvent.VK_D && ball.x >= 19 && ball.x < 25){
					speed += 3;
				}
				else if(e.getKeyCode() == KeyEvent.VK_L && getpowerUP2()){
					powerUP(false);
					setpowerUP2();
				}
				else if(e.getKeyCode() == KeyEvent.VK_LEFT && ball.x <= 361 && ball.x > 355){
					speed += 3;
				}
			}
		});
		setFocusable(true);
		
	}
	public void powerUP(boolean power){
		if(power){
			racquetTwo.setHeight(false);
		}
		else{
			racquetOne.setHeight(false);
		}
	}

	private void move() {
		ball.move(pause);//function that sets the new position of the ball
		racquetOne.move();//function that sets the new position of the racquet
		racquetTwo.move();//function that sets the new position of the racquet		
	}


	@Override
	public void paint(Graphics g) {
		//Paints the rectangle
		super.paint(g);
		g.setColor(Color.BLACK);
		g.fillRect(190,0,400,300);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);//define edges of component
		//Changing color of the ball		
		if(ball.x >= LENGTH/2 - 20){
			g2d.setColor(Color.WHITE);
		}else{
			g2d.setColor(Color.BLACK);
		}
		ball.paint(g2d);//Paints the ball
		
		g2d.setColor(Color.BLACK);
		racquetOne.paint(g2d);//Paints the racquet
		g2d.setColor(Color.WHITE);
		racquetTwo.paint(g2d);//Paints the racquet

		//This is the code for displaying the score
		g2d.setColor(Color.BLACK);
		g2d.setFont(new Font("CALIBRI", Font.BOLD, 30));
		g2d.drawString(score_player1+ ":", 170, 20);//String.valueOf(getScore())
		g2d.setColor(Color.WHITE);
		g2d.drawString(score_player2+"", 195, 20);//String.valueOf(getScore())
	}

	public void pause(){
		pause = false;
		JOptionPane.showMessageDialog(this,"Continue?" , "Game pause!", JOptionPane.YES_NO_OPTION);
		pause = true;
	}

	public void gameOver() {
		Sound.BACK.stop();	
		ball = new Ball(this);
		racquetOne = new Racquet(this,10,90,0);
		racquetTwo = new Racquet(this,370,90,0);
		speed = 1;
		ctr = 0;
		
		if(score_player2 == 3 || score_player1 == 3){
			promptWinner();
		}
	}

	public void promptWinner(){
		if(score_player1 == 3){
			if(nameP1 != null){
				String win = "Congratulations " + nameP1; 
				JOptionPane.showMessageDialog(this,win, "Winner!", JOptionPane.YES_NO_OPTION);
			}else
				JOptionPane.showMessageDialog(this,"Congratulations Player 1 ", "Winner!", JOptionPane.YES_OPTION);
		}
		else{
			if(nameP2 != null){
				String win = "Congratulations " + nameP2;
				JOptionPane.showMessageDialog(this,win, "Winner!", JOptionPane.YES_OPTION);
			}else
				JOptionPane.showMessageDialog(this,"Congratulations Player 2 ", "Winner!", JOptionPane.YES_OPTION);
		}

		if(JOptionPane.showConfirmDialog(this,"Do you want to play again?","Game Over",JOptionPane.YES_NO_OPTION) != JOptionPane.YES_OPTION){
			Sound.GAMEOVER.play();
			System.exit(ABORT);	
		}

		else{
			score_player1 = 0;
			score_player2 = 0;
			gameOver();
		}
		
	}

	public static void main(String[] args) throws InterruptedException {
		JFrame frame = new JFrame("Ping Pong");
		frame.setResizable(false);
		Game game = new Game();
		frame.add(game);
		frame.setSize(game.LENGTH, game.WIDTH);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JOptionPane.showMessageDialog(game,"Welcome to Pong v1.0\n=======================\nPlayer 1 :  W and S\nShrink : E     Smash : D\n=======================\nPlayer 2 :  UP and DOWN arrow\nShrink : L     Smash : LEFT Arrow\n=======================\nPause : Space bar\nNOTE:\nSpeed of the ball increseas every 5 turns.\nPlayer can only smash if the ball is VERY near the racquet.\n" , "Introduction", JOptionPane.YES_NO_OPTION);
		game.nameP1 = JOptionPane.showInputDialog(game,"Welcome to the Light Side!\nPlayer 1: Input name");
		game.nameP2 = JOptionPane.showInputDialog(game,"Welcome to the Dark Side!\nPlayer 2: Input name");
		while (true) {
			game.move();
			game.repaint();
			Thread.sleep(10);
		}
	}
} 
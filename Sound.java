import java.applet.Applet;
import java.applet.AudioClip;

public class Sound {
	public static final AudioClip BALL = Applet.newAudioClip(Sound.class.getResource("ball.wav"));				//Audioclip for ball
	public static final AudioClip GAMEOVER = Applet.newAudioClip(Sound.class.getResource("gameover.wav"));		//Audioclip for "Game Over"
	public static final AudioClip BACK = Applet.newAudioClip(Sound.class.getResource("back.wav"));				//Audioclip for racquet	
}
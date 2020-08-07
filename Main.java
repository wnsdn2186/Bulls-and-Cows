package model;
import javax.swing.JFrame;

public class Main 
{
	public static void main(String[] args)
	{
		BGM1 bgm1 = new BGM1();
		TTS TTS = new TTS();
		
		TTS.TTS("Welcome to BaseBallGame");
        baseball frame = new baseball();
		
		frame.setSize(1280, 720);
		frame.setTitle("Bulls And Cows");
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		while(true) 
		{
			try 
			{
				bgm1.bgm1();
				Thread.sleep(139000);
			} 
			
			catch(Exception e)
	     	{
				e.printStackTrace();
		    } 
		}	
	}
 }



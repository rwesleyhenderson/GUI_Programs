import javax.swing.*;
import java.awt.*;

public class JFrame1
{
	public static void main(String[] args)
	{
		final int FRAME_WIDTH = 250;
		final int FRAME_HEIGHT = 100;
		Font headlineFont = new Font("Ariel", Font.BOLD, 36);
		JFrame.setDefaultLookAndFeelDecorated(true);
		JFrame aFrame = new JFrame("First Name");
		aFrame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
		aFrame.setVisible(true);
		aFrame.setResizable(false);
		aFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JLabel greeting = new JLabel("Good Day! ");
		JLabel greeting2 = new JLabel("  Who are you?");
		JLabel greeting3 = new JLabel("  My name is Mud.");
		//greeting.setFont(headlineFont);	//sets the font on greeting
		
		
		//aFrame.setLayout(new FlowLayout());
		aFrame.setLayout(new BorderLayout());
		aFrame.add(greeting, BorderLayout.NORTH);
		aFrame.add(greeting2, BorderLayout.CENTER);
		aFrame.add(greeting3, BorderLayout.SOUTH);
		//greeting.setText("Howdy");	//changes the text of greeting
	}
}

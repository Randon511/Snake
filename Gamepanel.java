import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.Graphics;

public class Gamepanel extends JPanel
{
	private static final long serialVersionUID = 1L;
	public static final int WIDTH = 500, HEIGHT = 500;

	public Gamepanel()
	{
		setPreferredSize(new Dimension(WIDTH,HEIGHT));
	}

	public void paint(Graphics g)
	{
		//Horizontal Lines
		for(int i = 0; i < WIDTH/25; i++)
		{
			g.drawLine(i*25,0,i*25,HEIGHT);
		}

		//Vertical Lines
		for(int i = 0; i < HEIGHT/25; i++)
		{
			g.drawLine(0,i*25,WIDTH,i*25);
		}
	}


	public void start()
	{
	}

	public void stop()
	{
	}

	public void tick()
	{
	}

	public void run()
	{
	}
}



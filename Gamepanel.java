import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.BasicStroke;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;

public class Gamepanel extends JPanel implements Runnable
{
	private static final long serialVersionUID = 1L;
	public static final int WIDTH = 500, HEIGHT = 500;

	private int sx = 250 , sy = 250;

	private Random r;
	private int size = 3;

	private SnakeBodyPart b;
	private ArrayList<SnakeBodyPart> snake;

	private Apple a;
	private ArrayList<Apple> apples;
	
	private Boolean running = false;
	private Thread thread;

	private int ticks = 0;

	public Gamepanel()
	{
		setFocusable(true);
		setPreferredSize(new Dimension(WIDTH,HEIGHT));
		snake = new ArrayList<SnakeBodyPart>(); 
		apples = new ArrayList<Apple>();
		r = new Random();

		start();
	}

	public void paint(Graphics gr)
	{
		super.paint(gr);
		Graphics2D g = (Graphics2D) gr;

		g.clearRect(0, 0, WIDTH, HEIGHT);
		g.setColor(Color.green);
		g.fillRect(0, 0, WIDTH, HEIGHT);

		g.setColor(Color.black);
		g.setStroke(new BasicStroke(1));

		//Draw horizontal lines
		for(int i = 0; i < WIDTH/25; i++)
		{
			g.drawLine(i*25,0,i*25,HEIGHT);
		}

		//Draw vertical lines
		for(int i = 0; i < HEIGHT/25; i++)
		{
			g.drawLine(0,i*25,WIDTH,i*25);
		}

		//Draw snake
		for(int i = 0; i < snake.size(); i++)
		{
			snake.get(i).draw(g);
		}

		//Draw apple
		for(int i = 0; i < apples.size(); i++)
		{
			apples.get(i).draw(g);
		}
		
	}


	public void start()
	{
		running = true;
		thread = new Thread(this);
		thread.start();
	}

	public void stop()
	{
		running = false;
		try
		{
			thread.join();
		}
		catch (InterruptedException e)
		{
			e.printStackTrace();
		}
	}

	public void tick()
	{
		if(snake.size() == 0)
		{
			b = new SnakeBodyPart(sx,sy);
			snake.add(b);
		}

		if(apples.size() == 0)
		{
			a = new Apple(r.nextInt(20),r.nextInt(20));
			apples.add(a);
		}
		ticks++;
	}

	@Override
	public void run()
	{
		while(running)
		{
			tick();
			repaint();
		}
	}
}



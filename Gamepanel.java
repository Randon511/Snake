import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.BasicStroke;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Gamepanel extends JPanel implements Runnable, KeyListener
{
	private static final long serialVersionUID = 1L;
	public static final int WIDTH = 800, HEIGHT = 800;

	private int sx = 250 , sy = 250;

	private Random r;
	private int size = 3;

	private SnakeBodyPart b;
	private ArrayList<SnakeBodyPart> snake;

	private Apple a;
	private ArrayList<Apple> apples;
	
	private Boolean running = false;
	private Thread thread;

	private boolean right = true, left = false, up = false, down = false; 
	private int ticks = 0;

	public Gamepanel()
	{
		setFocusable(true);
		addKeyListener(this);
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
		//Generate the snake body if it doesnt exist
		if(snake.size() == 0)
		{
			b = new SnakeBodyPart(sx,sy);
			snake.add(b);
		}

		//Make new apple if there isnt one
		if(apples.size() == 0)
		{
			int newAx = r.nextInt((WIDTH/25)-1);
			int newAy = r.nextInt((HEIGHT/25)-1);

			for(int i = 0; i < snake.size(); i++)
			{
				if(snake.get(i).getX() == newAx)
				{
					newAx = r.nextInt((WIDTH/25)-1);
					i = -1;
				}

				if(snake.get(i).getY() == newAy)
				{
					newAy = r.nextInt((HEIGHT/25)-1);
					i = -1;
				}
			}

			a = new Apple(newAx,newAy);
			apples.add(a);
		}

		//Stop the game if the snake hits a boundary
		if(sx >= WIDTH || sx < 0 || sy >= HEIGHT || sx < 0)
		{
			stop();
		}

		//Stop the game if the snake hits itself
		if(snake.size() > 1)
		{
			for(int i = 0; i < snake.size(); i++)
			{

				if(sx == snake.get(i).getX() && sy == snake.get(i).getY() && i != snake.size() - 1)
				{
					stop();
				}
			}
		}

		//Increase snake length if snake hits the apple
		for(int i = 0; i < apples.size(); i++)
		{
			if(sx == apples.get(i).getX() && sy == apples.get(i).getY())
			{
				size++;
				apples.remove(i);
			}
		}

		if(ticks > 50000)
		{
			//Snake movement    
			//Change if condition to change game speed
			
			if(right)
			{
				sx += 25;
			}

			if(left)
			{
				sx -= 25;
			}

			if(up)
			{
				sy -= 25;
			}

			if(down)
			{
				sy += 25;
			}

			ticks = 0; 

			b = new SnakeBodyPart(sx,sy);
			snake.add(b);

			//Makes that snake start with the length "size"
			if(snake.size() > size)
			{
				snake.remove(0);
			}
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

	@Override
	public void keyPressed(KeyEvent e)
	{
		int key = e.getKeyCode();

		if(key == KeyEvent.VK_RIGHT && !left)
		{
			up = false;
			down = false;
			right = true; 
		}

		if(key == KeyEvent.VK_LEFT && !right)
		{
			up = false;
			down = false;
			left = true; 
		}

		if(key == KeyEvent.VK_UP && !down)
		{
			left = false;
			right = false;
			up = true; 
		}

		if(key == KeyEvent.VK_DOWN && !up)
		{
			right = false;
			left = false;
			down = true; 
		}
	}

	@Override
	public void keyReleased(KeyEvent a)
	{

	}

	@Override
	public void keyTyped(KeyEvent a)
	{

	}
}



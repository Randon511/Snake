import java.awt.Graphics;
import java.awt.Color;
import java.awt.Graphics2D;

public class SnakeBodyPart
{
	private int x,y,width,height;
	public SnakeBodyPart(int xc, int yc)
	{
		this.x = xc;
		this.y = yc;
	}

	public void tick()
	{

	}

	public void draw(Graphics2D g)
	{
		g.setColor(Color.BLACK);
		g.fillRect(x, y, 25, 25);
	}

	public int getX()
	{
		return this.x;
	}

	public void setX(int newXc)
	{
		this.x = newXc;
	}

	public int getY()
	{
		return this.y;
	}

	public void setY(int newYc)
	{
		this.y = newYc;
	}
}
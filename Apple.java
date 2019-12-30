import java.awt.Graphics;
import java.awt.Color;
import java.awt.Graphics2D;

public class Apple
{
	private int x, y;

	public Apple(int xc, int yc)
	{
		this.x = xc*25;
		this.y = yc*25;
	}

	public void draw(Graphics2D g)
	{
		g.setColor(Color.RED);
		g.fillRect(x+5,y+5,15,15);
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
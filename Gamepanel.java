import javax.swing.JPanel;
import java.awt.Dimension;

public class Gamepanel extends JPanel
{
	private static final long serialVersionUID = 1L;
	public static final int WIDTH = 500, HEIGHT = 500;

	public Gamepanel()
	{
		setPreferredSize(new Dimension(WIDTH,HEIGHT));
	}
}
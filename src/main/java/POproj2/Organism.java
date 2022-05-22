package POproj2;
import java.util.*;

public abstract class Organism {
	
	private int strength;
	private int initiative;
	private Point position;
	private boolean alive;
	protected World world;
	abstract void Action();
	abstract void Collision(Organism other);
	abstract String Draw();
	abstract String GetName();
	abstract Organism Birth(Point p);
	
	public Organism(World _w, Point _position, int _strength, int _initiative, boolean _alive)
	{
		world=_w;
		position=_position;
		strength=_strength;
		initiative=_initiative;
		alive=_alive;
	}
	
	public int GetStrength()
	{
		return strength;
	}
	
	public void SetStrength(int s)
	{
		strength = s;
	}
	
	public int GetInitiative()
	{
		return initiative;
	}
	
	public Point GetPosition()
	{
		return position;
	}

	public void SetPosition(Point p)
	{
		position = p;
	}

	public World GetWorld()
	{
		return world;
	}

	public boolean GetAlive()
	{
		return alive;
	}

	public void SetAlive(boolean _alive)
	{
		alive = _alive;
	}
	
	public Point GetRandomPoint(Point p, boolean top, boolean bot, boolean left, boolean right)
	{
		Point np = new Point(p);
		boolean found = false;
		if (p.y == 0)
			top = false;
		if (p.y == world.GetHeight() - 1)
			bot = false;
		if (p.x == 0)
			left = false;
		if (p.x == world.GetWidth() - 1)
			right = false;
		while (found == false && (top == true || bot == true || left == true || right == true))
		{
			int direction = new Random().nextInt(4);
			if (direction == 0 && top == true)
			{
				np.y = p.y - 1;
				np.x = p.x;
				found = true;
			}
			else if (direction == 1 && bot == true)
			{
				np.y = p.y + 1;
				np.x = p.x;
				found = true;
			}
			else if (direction == 2 && left == true)
			{
				np.y = p.y;
				np.x = p.x - 1;
				found = true;
			}
			else if (direction == 3 && right == true)
			{
				np.y = p.y;
				np.x = p.x + 1;
				found = true;
			}
		}
		return np;
	}
	
	public Point GetRandomSafePoint(Point p)
	{
		Point np = new Point(p);
		boolean top = true, bot = true, left = true, right = true;
		if (p.y == 0)
			top = false;
		if (p.y == world.GetHeight() - 1)
			bot = false;
		if (p.x == 0)
			left = false;
		if (p.x == world.GetWidth() - 1)
			right = false;
		if (top == true)
		{
			np.y--;
			if (world.GetOrganism(np) != null)
				top = false;
			np.y=p.y;
		}
		if(bot ==true)
		{
			np.y++;
			if (world.GetOrganism(np) != null)
				bot = false;
			np.y=p.y;
		}
		if (left == true)
		{
			np.x--;
			if (world.GetOrganism(np) != null)
				left = false;
			np.x = p.x;
		}
		if (right == true)
		{
			np.x++;
			if (world.GetOrganism(np) != null)
				right = false;
			np.x = p.x;
		}
		if (top == true || bot == true || left == true || right == true)
			np = GetRandomPoint(p, top, bot, left, right);
		return np;
	}
}
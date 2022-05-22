package POproj2;

import java.awt.event.KeyEvent;

public class Human extends Animal {
	private static final int HUMAN_STRENGTH = 5;
	private static final int HUMAN_INITIATIVE = 4;
	private static final int DEFAULT_SPECIAL = 0;
	

	private int specialtime;
	private int specialcooldown;
	
	public Human(World world, Point position)
	{
		super(world, position, HUMAN_STRENGTH, HUMAN_INITIATIVE);
		specialtime = DEFAULT_SPECIAL;
		specialcooldown = DEFAULT_SPECIAL;
	}
	
	@Override
	public boolean SameType(Organism o)
	{
		return o instanceof Human;
	}
	
	@Override
	public Organism Birth(Point np)
	{
		Organism newborn = new Human(world, np);
		world.AddEvent("Human has been born (" + np.x +" " + np.y + ")");
		return newborn;
	}
	
	@Override
	public String GetName()
	{
		return "Human";
	}
	
	@Override
	public String Draw()
	{
		return "src/img/human.jpg";
	}
	
	Point CatchMove()
	{
		boolean top = true, bot = true, left = true, right = true;
		Point np = new Point(GetPosition());
		if (np.y == 0)
			top = false;
		if (np.y == world.GetHeight() - 1)
			bot = false;
		if (np.x == 0)
			left = false;
		if (np.x == world.GetWidth() - 1)
			right = false;
		if (top == false && bot == false && left == false && right == false)
			return np;
		if (world.GetHumanDirection() == 38 && top == true)
		{
			np.y--;
			return np;
		}
		else if (world.GetHumanDirection() == 40 && bot == true)
		{
			np.y++;
			return np;
		}
		else if (world.GetHumanDirection() == 37 && left == true)
		{
			np.x--;
			return np;
		}
		else if (world.GetHumanDirection() == 39 && right == true)
		{
			np.x++;
			return np;
		}
		else if (world.GetHumanDirection() == 69)
		{
			if (specialtime == 0 && specialcooldown == 0)
			{
				specialtime = 5;
				specialcooldown = 5;
				world.AddEvent("Burning activated for " + specialtime + " turns!");
			}
			else if (specialcooldown > 0 && specialtime==0)
			{
				world.AddEvent("Can't use special skill for " + specialcooldown + " turns!");
			}
		}
		return np;
	}
	
	public void SpecialSkill()
	{
		boolean top = true, bot = true, left = true, right = true;
		Point np = new Point(GetPosition());
		if (np.y == 0)
			top = false;
		if (np.y == world.GetHeight() - 1)
			bot = false;
		if (np.x == 0)
			left = false;
		if (np.x == world.GetWidth() - 1)
			right = false;
		Organism other = null;
		if (top == true)
		{
			np.y--;
			other = world.GetOrganism(np);
			if (other != null)
			{
				other.SetAlive(false);
				world.AddEvent(this.GetName() + " (" + this.GetPosition().x + " " + this.GetPosition().y + ") burned(around) " + other.GetName() + " (" + other.GetPosition().x + " " + other.GetPosition().y + ")");
				world.DelOrganism(other);
			}
			np.y = GetPosition().y;
		}
		if (bot == true)
		{
			np.y++;
			other = world.GetOrganism(np);
			if (other != null)
			{
				other.SetAlive(false);
				world.AddEvent(this.GetName() + " (" + this.GetPosition().x + " " + this.GetPosition().y + ") burned(around) " + other.GetName() + " (" + other.GetPosition().x + " " + other.GetPosition().y + ")");
				world.DelOrganism(other);
			}
			np.y = GetPosition().y;
		}
		if (left == true)
		{
			np.x--;
			other = world.GetOrganism(np);
			if (other != null)
			{
				other.SetAlive(false);
				world.AddEvent(this.GetName() + " (" + this.GetPosition().x + " " + this.GetPosition().y + ") burned(around) " + other.GetName() + " (" + other.GetPosition().x + " " + other.GetPosition().y + ")");
				world.DelOrganism(other);
			}
			np.x = GetPosition().x;
		}
		if (right == true)
		{
			np.x++;
			other = world.GetOrganism(np);
			if (other != null)
			{
				other.SetAlive(false);
				world.AddEvent(this.GetName() + " (" + this.GetPosition().x + " " + this.GetPosition().y + ") burned(around) " + other.GetName() + " (" + other.GetPosition().x + " " + other.GetPosition().y + ")");
				world.DelOrganism(other);
			}
			np.x = GetPosition().x;
		}
	}
	
	@Override
	public void Action()
	{
		if (GetAlive() == true)
		{
			world.AddEvent("\nHUMAN");
			Point np = CatchMove();
			if (specialtime > 0)
			{
				SpecialSkill();
				specialtime--;
			}
			else if (specialcooldown > 0)
			{
				specialcooldown--;
			}
			if (np.x != GetPosition().x || np.y != GetPosition().y)
				GetWorld().MoveOrganism(GetPosition(), np);
			world.AddEvent("HUMAN\n");
		}
	}
}

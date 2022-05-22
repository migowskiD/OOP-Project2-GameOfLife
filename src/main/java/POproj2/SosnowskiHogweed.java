package POproj2;

public class SosnowskiHogweed extends Plant {
	private static final int SOSNOWSKI_STRENGTH = 10;
	
	public SosnowskiHogweed(World world, Point position)
	{
		super(world, position, SOSNOWSKI_STRENGTH);
	}
	
	@Override
	public Organism Birth(Point p)
	{
		Organism newborn = new SosnowskiHogweed(world, p);
		world.AddEvent("SosnowskiHogweed has spread (" + p.x +" " + p.y + ")");
		return newborn;
	}
	
	@Override
	public String GetName()
	{
		return "SosnowskiHogweed";
	}
	
	@Override
	public String Draw()
	{
		return "src/img/sosnowskihogweed.png";
	}
	
	@Override
	public void Collision(Organism other)
	{
		if(other instanceof CyberSheep)
		{
			SetAlive(false);
			world.AddEvent(this.GetName() + " (" + this.GetPosition().x + " " + this.GetPosition().y + ") was eaten by " + other.GetName() + " (" + other.GetPosition().x + " " + other.GetPosition().y + ")");
		}
		else
		{
			other.SetAlive(false);
			world.AddEvent(this.GetName() + " (" + this.GetPosition().x + " " + this.GetPosition().y + ") won with " + other.GetName() + " (" + other.GetPosition().x + " " + other.GetPosition().y + ")");
		}
	}

	@Override
	public void Action()
	{
		if (GetAlive() == true)
		{
			boolean top = true, bot = true, left = true, right = true;
			Point p = GetPosition();
			Point np = new Point (p);
			if (p.y == 0)
				top = false;
			if (p.y == world.GetHeight() - 1)
				bot = false;
			if (p.x == 0)
				left = false;
			if (p.x == world.GetWidth() - 1)
				right = false;
			
			Organism other = null;
			if (top == true)
			{
				np.y--;
				other = world.GetOrganism(np);
				if (other != null && !(other instanceof Plant) && !(other instanceof CyberSheep))
				{
					other.SetAlive(false);
					world.AddEvent(this.GetName() + " (" + this.GetPosition().x + " " + this.GetPosition().y + ") killed(around) " + other.GetName() + " (" + other.GetPosition().x + " " + other.GetPosition().y + ")");
					world.DelOrganism(other);
				}
				np.y = p.y;
			}
			if (bot == true)
			{
				np.y++;
				other = world.GetOrganism(np);
				if (other != null && !(other instanceof Plant) && !(other instanceof CyberSheep))
				{
					other.SetAlive(false);
					world.AddEvent(this.GetName() + " (" + this.GetPosition().x + " " + this.GetPosition().y + ") killed(around) " + other.GetName() + " (" + other.GetPosition().x + " " + other.GetPosition().y + ")");
					world.DelOrganism(other);
				}
				np.y = p.y;
			}
			if (left == true)
			{
				np.x--;
				other = world.GetOrganism(np);
				if (other != null && !(other instanceof Plant) && !(other instanceof CyberSheep))
				{
					other.SetAlive(false);
					world.AddEvent(this.GetName() + " (" + this.GetPosition().x + " " + this.GetPosition().y + ") killed(around) " + other.GetName() + " (" + other.GetPosition().x + " " + other.GetPosition().y + ")");
					world.DelOrganism(other);
				}
				np.x = p.x;
			}
			if (right == true)
			{
				np.x++;
				other = world.GetOrganism(np);
				if (other != null && !(other instanceof Plant) && !(other instanceof CyberSheep))
				{
					other.SetAlive(false);
					world.AddEvent(this.GetName() + " (" + this.GetPosition().x + " " + this.GetPosition().y + ") killed(around) " + other.GetName() + " (" + other.GetPosition().x + " " + other.GetPosition().y + ")");
					world.DelOrganism(other);
				}
				np.x = p.x;
			}
		}
		if (GetAlive() == true && DidSpread() == true)
		{
			Point np = GetRandomSafePoint(GetPosition());
			Organism o = null;
			if (np.x != GetPosition().x || np.y != GetPosition().y)
			{
				o = Birth(np);
			}
			if (o != null)
			{
				world.AddOrganism(o);
			}
		}
	}
}

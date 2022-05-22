package POproj2;

public class Fox extends Animal{
	private static final int FOX_STRENGTH = 3;
	private static final int FOX_INITIATIVE = 7;
	
	public Fox(World world, Point position)
	{
		super(world, position, FOX_STRENGTH, FOX_INITIATIVE);
	}
	
	@Override
	public boolean SameType(Organism o)
	{
		return o instanceof Fox;
	}
	
	@Override
	public Organism Birth(Point p)
	{
		Organism newborn = new Fox(world, p);
		world.AddEvent("Fox has been born (" + p.x +" " + p.y + ")");
		return newborn;
	}
	
	@Override
	public String GetName()
	{
		return "Fox";
	}
	
	@Override
	public String Draw()
	{
		return "src/img/fox.png";
	}
	
	@Override
	public void Action()
	{
		if (GetAlive() == true)
		{
			Point p = GetPosition();
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
				if (world.GetOrganism(np)!=null && world.GetOrganism(np).GetStrength() > GetStrength())
					top = false;
				np.y = p.y;
			}
			if (bot == true)
			{
				np.y++;
				if (world.GetOrganism(np) != null &&  world.GetOrganism(np).GetStrength() > GetStrength())
					bot = false;
				np.y = p.y;
			}
			if (left == true)
			{
				np.x--;
				if (world.GetOrganism(np) != null &&  world.GetOrganism(np).GetStrength() > GetStrength())
					left = false;
				np.x = p.x;
			}
			if (right == true)
			{
				np.x++;
				if (world.GetOrganism(np) != null &&  world.GetOrganism(np).GetStrength() > GetStrength())
					right = false;
				np.x = p.x;
			}
			if (top == true || bot == true || left == true || right == true)
				np = GetRandomPoint(p, top, bot, left, right);
			if (np.x != p.x || np.y != p.y)
				world.MoveOrganism(p, np);
		}
	}
}

package POproj2;

public class CyberSheep extends Animal {
	private static final int CSHEEP_STRENGTH = 11;
	private static final int CSHEEP_INITIATIVE = 4;
	private static final int BIG_NUMBER = 1000;
	
	
	public CyberSheep(World world, Point position)
	{
		super(world, position, CSHEEP_STRENGTH, CSHEEP_INITIATIVE);
	}
	
	public Point SeekForHogweed()
	{
		Point np = new Point();
		Point rem = new Point();
		double closest = BIG_NUMBER;
		for(int i = 0; i < world.GetHeight(); i++)
		{
			for(int j = 0; j < world.GetWidth(); j++)
			{
				np.y=i;
				np.x=j;
				if(world.GetOrganism(np) instanceof SosnowskiHogweed)
				{
					double distance=Math.abs(np.y-GetPosition().y)+Math.abs(np.x-GetPosition().x);
					if(distance < closest)
					{
						closest=distance;
						rem.x=np.x;
						rem.y=np.y;
					}
				}
			}
		}
		if(closest == BIG_NUMBER)
		{
			return null;
		}
		else if(GetPosition().x < rem.x)
		{
			rem.y=GetPosition().y;
			rem.x=GetPosition().x + 1;
		}
		else if(GetPosition().x > rem.x)
		{
			rem.y=GetPosition().y;
			rem.x=GetPosition().x - 1;
		}
		else if(GetPosition().y < rem.y)
		{
			rem.y=GetPosition().y + 1;
			rem.x=GetPosition().x;
		}
		else if(GetPosition().y > rem.y)
		{
			rem.y=GetPosition().y - 1;
			rem.x=GetPosition().x;
		}
		return rem;
	}
	
	@Override
	public boolean SameType(Organism o)
	{
		return o instanceof CyberSheep;
	}
	
	@Override
	public Organism Birth(Point p)
	{
		Organism newborn = new CyberSheep(world, p);
		world.AddEvent("CyberSheep has been born (" + p.x +" " + p.y + ")");
		return newborn;
	}
	
	@Override
	public String GetName()
	{
		return "CyberSheep";
	}
	
	@Override
	public String Draw()
	{
		return "src/img/cybersheep.png";
	}
	
	@Override
	public void Action()
	{
		if(GetAlive() == true && DidMove() == true)
		{
			Point np = SeekForHogweed();
			if(np == null)
			{
				np = GetRandomPoint(GetPosition(), true, true, true ,true);
			}
			if(np.x != GetPosition().x || np.y != GetPosition().y)
			{
				world.MoveOrganism(GetPosition(), np);
			}
		}
	}
}

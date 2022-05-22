package POproj2;

public class Wolf extends Animal{
	private static final int WOLF_STRENGTH = 9;
	private static final int WOLF_INITIATIVE = 5;
	
	public Wolf(World world, Point position)
	{
		super(world, position, WOLF_STRENGTH, WOLF_INITIATIVE);
	}
	
	@Override
	public boolean SameType(Organism o)
	{
		return o instanceof Wolf;
	}
	
	@Override
	public Organism Birth(Point p)
	{
		Organism newborn = new Wolf(world, p);
		world.AddEvent("Wolf has been born (" + p.x +" " + p.y + ")");
		return newborn;
	}
	
	@Override
	public String GetName()
	{
		return "Wolf";
	}
	
	@Override
	public String Draw()
	{
		return "src/img/wolf.png";
	}
	
}

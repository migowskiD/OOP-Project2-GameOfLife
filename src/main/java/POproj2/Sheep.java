package POproj2;

public class Sheep extends Animal {
	private static final int SHEEP_STRENGTH = 4;
	private static final int SHEEP_INITIATIVE = 4;
	
	public Sheep(World world, Point position)
	{
		super(world, position, SHEEP_STRENGTH, SHEEP_INITIATIVE);
	}
	
	@Override
	public boolean SameType(Organism o)
	{
		return o instanceof Sheep;
	}
	
	@Override
	public Organism Birth(Point p)
	{
		Organism newborn = new Sheep(world, p);
		world.AddEvent("Sheep has been born (" + p.x +" " + p.y + ")");
		return newborn;
	}
	
	@Override
	public String GetName()
	{
		return "Sheep";
	}
	
	@Override
	public String Draw()
	{
		return "src/img/sheep.png";
	}
}

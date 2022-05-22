package POproj2;

public class Grass extends Plant{
	private static final int GRASS_STRENGTH = 0;
	
	public Grass(World world, Point position)
	{
		super(world, position, GRASS_STRENGTH);
	}
	
	@Override
	public Organism Birth(Point p)
	{
		Organism newborn = new Grass(world, p);
		world.AddEvent("Grass has spread (" + p.x +" " + p.y + ")");
		return newborn;
	}
	
	@Override
	public String GetName()
	{
		return "Grass";
	}
	
	@Override
	public String Draw()
	{
		return "src/img/grass.png";
	}
}

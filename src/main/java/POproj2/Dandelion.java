package POproj2;

public class Dandelion extends Plant {
	private static final int DANDELION_STRENGTH = 0;
	private static final int DANDELION_SPREADTIMES = 3;
	
	public Dandelion(World world, Point position)
	{
		super(world, position, DANDELION_STRENGTH, DANDELION_SPREADTIMES);
	}
	
	@Override
	public Organism Birth(Point p)
	{
		Organism newborn = new Dandelion(world, p);
		world.AddEvent("Dandelion has spread (" + p.x +" " + p.y + ")");
		return newborn;
	}
	
	@Override
	public String GetName()
	{
		return "Dandelion";
	}
	
	@Override
	public String Draw()
	{
		return "src/img/dandelion.png";
	}
}

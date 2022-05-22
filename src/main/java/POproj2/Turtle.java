package POproj2;

public class Turtle extends Animal {
	private static final int TURTLE_STRENGTH = 2;
	private static final int TURTLE_INITIATIVE = 1;
	private static final int TURTLE_RANGE = 1;
	private static final double TURTLE_MOVE_CHANCE = 0.25;
	
	public Turtle(World world, Point position)
	{
		super(world, position, TURTLE_STRENGTH, TURTLE_INITIATIVE, true, TURTLE_RANGE, TURTLE_MOVE_CHANCE );
	}
	
	@Override
	public boolean SameType(Organism o)
	{
		return o instanceof Turtle;
	}
	
	@Override
	public Organism Birth(Point p)
	{
		Organism newborn = new Turtle(world, p);
		world.AddEvent("Turtle has been born (" + p.x +" " + p.y + ")");
		return newborn;
	}
	
	@Override
	public String GetName()
	{
		return "Turtle";
	}
	
	@Override
	public String Draw()
	{
		return "src/img/turtle.png";
	}
	
	@Override
	boolean DidBlock(Organism other)
	{
		if (other.GetStrength() < 5)
		{
			return true;
		}
		return false;
	}
}

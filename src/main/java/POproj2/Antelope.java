package POproj2;

import java.util.Random;

public class Antelope extends Animal {
	private static final int ANTELOPE_STRENGTH = 4;
	private static final int ANTELOPE_INITIATIVE = 4;
	private static final int ANTELOPE_RANGE = 2;
	private static final double ANTELOPE_MOVE_CHANCE = 1;
	
	public Antelope(World world, Point position)
	{
		super(world, position, ANTELOPE_STRENGTH, ANTELOPE_INITIATIVE, true, ANTELOPE_RANGE, ANTELOPE_MOVE_CHANCE);
	}
	
	@Override
	public boolean SameType(Organism o)
	{
		return o instanceof Antelope;
	}
	
	@Override
	public Organism Birth(Point p)
	{
		Organism newborn = new Antelope(world, p);
		world.AddEvent("Antelope has been born (" + p.x +" " + p.y + ")");
		return newborn;
	}
	
	@Override
	public String GetName()
	{
		return "Antelope";
	}
	
	@Override
	public String Draw()
	{
		return "src/img/antelope.png";
	}
	
	@Override
	boolean DidAvoid()
	{
		if (new Random().nextInt(2) == 0)
			return true;
		else return false;
	}
}

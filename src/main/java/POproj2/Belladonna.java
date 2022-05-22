package POproj2;

public class Belladonna extends Plant {
	private static final int BELLADONNA_STRENGTH = 99;
	
	public Belladonna(World world, Point position)
	{
		super(world, position, BELLADONNA_STRENGTH);
	}
	
	@Override
	public Organism Birth(Point p)
	{
		Organism newborn = new Belladonna(world, p);
		world.AddEvent("Belladonna has spread (" + p.x +" " + p.y + ")");
		return newborn;
	}
	
	@Override
	public String GetName()
	{
		return "Belladonna";
	}
	
	@Override
	public String Draw()
	{
		return "src/img/belladonna.png";
	}
	
	@Override
	public void Collision(Organism other)
	{
		other.SetAlive(false);
		world.AddEvent(this.GetName() + " (" + this.GetPosition().x + " " + this.GetPosition().y + ") won with " + other.GetName() + " (" + other.GetPosition().x + " " + other.GetPosition().y + ")");
	}
}

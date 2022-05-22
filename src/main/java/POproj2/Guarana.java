package POproj2;

public class Guarana extends Plant {
	private static final int GUARANA_STRENGTH = 0;
	
	public Guarana(World world, Point position)
	{
		super(world, position, GUARANA_STRENGTH);
	}
	
	@Override
	public Organism Birth(Point p)
	{
		Organism newborn = new Guarana(world, p);
		world.AddEvent("Guarana has spread (" + p.x +" " + p.y + ")");
		return newborn;
	}
	
	@Override
	public String GetName()
	{
		return "Guarana";
	}
	
	@Override
	public String Draw()
	{
		return "src/img/guarana.png";
	}
	
	@Override
	public void Collision(Organism other)
	{
		SetAlive(false);
		other.SetStrength(other.GetStrength() + 3);
		world.AddEvent(this.GetName() + " (" + this.GetPosition().x + " " + this.GetPosition().y + ") was eaten by " + other.GetName() + " (" + other.GetPosition().x + " " + other.GetPosition().y + ") Strength: " + other.GetStrength());
	}
}

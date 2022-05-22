package POproj2;

import java.util.Random;

public abstract class Plant extends Organism{
	private static final int DEFAULT = 1;
	private static final double DEFAULT_CHANCE = 0.1;
	private static final int DEFAULT_INITIATIVE = 0;
	private int spreadtimes;
	private double spreadchance;
	
	public Plant(World _w, Point _position, int _strength)
	{
		super(_w, _position, _strength, DEFAULT_INITIATIVE, true);
		spreadtimes = DEFAULT;
		spreadchance = DEFAULT_CHANCE;
	}
	
	public Plant(World _w, Point _position, int _strength, int _spreadtimes)
	{
		super(_w, _position, _strength, DEFAULT_INITIATIVE, true);
		spreadtimes = _spreadtimes;
		spreadchance = DEFAULT_CHANCE;
	}
	
	public boolean DidSpread()
	{
		double probability = new Random().nextInt(100);
		if (probability < (spreadchance * 100))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	@Override
	public void Action()
	{
		for (int i = 0; i < spreadtimes; i++)
		{
			if (GetAlive() == true && DidSpread() == true)
			{
				Point np = GetRandomSafePoint(GetPosition());
				Organism o = null;
				if (np.x != GetPosition().x || np.y != GetPosition().y)
				{
					o = Birth(np);
				}
				if (o != null)
				{
					world.AddOrganism(o);
				}
			}
		}
	}
	
	@Override
	public void Collision(Organism other)
	{
		if (this.GetStrength() > other.GetStrength())
		{
			other.SetAlive(false);
			world.AddEvent( this.GetName() + " (" + this.GetPosition().x + " " + this.GetPosition().y + ") won with " + other.GetName() + " (" + other.GetPosition().x + " " + other.GetPosition().y + ")");
		}
		else
		{
			SetAlive(false);
			world.AddEvent( this.GetName() + " (" + this.GetPosition().x + " " + this.GetPosition().y + ") lost with " + other.GetName() + " (" + other.GetPosition().x + " " + other.GetPosition().y + ")");
		}
	}
	
}

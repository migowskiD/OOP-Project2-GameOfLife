package POproj2;
import java.util.Random;

public abstract class Animal extends Organism {
	private static final int DEFAULT = 1;
	
	private int range;
	private double movechance;
	
	public Animal(World _w, Point _position, int _strength, int _initiative)
	{
		super(_w, _position, _strength, _initiative, true);
		range = DEFAULT;
		movechance = DEFAULT;
	}
	
	public Animal(World _w, Point _position, int _strength, int _initiative, boolean _alive, int _range, double _movechance)
	{
		super(_w, _position, _strength, _initiative, _alive);
		range = _range;
		movechance = _movechance;
	}
	
	public abstract boolean SameType(Organism o);
	
	public boolean DidMove()
	{
		double probability = new Random().nextInt(100);
		if (probability < (movechance * 100))
		{
			return true;
		}
		else
		{
			return false;
		}

	}

	boolean DidBlock(Organism other)
	{
		return false;
	}

	boolean DidAvoid()
	{
		return false;
	}
	
	@Override
	public void Action()
	{
		for(int i = 0; i < range; i++)
		{
			if(GetAlive() == true && DidMove() == true)
			{
				Point np = GetRandomPoint(GetPosition(), true, true, true ,true);
				if(np.x != GetPosition().x || np.y != GetPosition().y)
				{
					//System.out.println(GetPosition().x + " " + GetPosition().y +" na "+np.x+" "+ np.y);
					if(world.MoveOrganism(GetPosition(), np) == 1)
						break;
				}
			}
		}
	}
	
	@Override
	public void Collision(Organism other)
	{
		if(SameType(other))
		{
			Point np = GetRandomSafePoint(GetPosition());
			Organism o = null;
			if (np.y != GetPosition().y || np.x != GetPosition().x)
			{
				o = Birth(np);
			}
			else
			{
				np = GetRandomSafePoint(other.GetPosition());
				if (np.y != other.GetPosition().y || np.x != other.GetPosition().x)
				{
					o = Birth(np);
				}
			}
			if (o != null)
			{
				world.AddOrganism(o);
			}
		}
		else
		{
			if (DidAvoid())
			{
				Point np = GetRandomSafePoint(GetPosition());
				if (np.y != GetPosition().y || np.x != GetPosition().x)
				{
					world.AddEvent(this.GetName() + " (" + this.GetPosition().x + " " + this.GetPosition().y + ") avoided " + other.GetName() + " (" + other.GetPosition().x + " " + other.GetPosition().y + ")"); 
					world.MoveOrganism(GetPosition(), np);
					return;
				}
			}
			if (DidBlock(other))
			{
				world.AddEvent(this.GetName() + " (" + this.GetPosition().x + " " + this.GetPosition().y + ") blocked " + other.GetName() + " (" + other.GetPosition().x + " " + other.GetPosition().y + ")");
			}
			else if (this.GetStrength() > other.GetStrength())
			{
				other.SetAlive(false);
				world.AddEvent(this.GetName() + " (" + this.GetPosition().x + " " + this.GetPosition().y + ") won with " + other.GetName() + " (" + other.GetPosition().x + " " + other.GetPosition().y + ")");
			}
			else
			{
				SetAlive(false);
				world.AddEvent(this.GetName() + " (" + this.GetPosition().x + " " + this.GetPosition().y + ") lost with " + other.GetName() + " (" + other.GetPosition().x + " " + other.GetPosition().y + ")");
			}
		}
	}
}

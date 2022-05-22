package POproj2;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;


public class World {
	private static final double DEFAULT_DENSITY = 0.25;
	private static final int NUMBER_OF_ORGANISMS = 11;
	
	private int height, width;
	private Organism[][] map;
	private Vector<Organism> OrganismQueue;
	private Vector<Organism> NewOrganism;
	private String Events;
	private MyFrame frame;
	private int humandirection;
	
	private void NewLife()
	{
		Point p = new Point();
		Random random = new Random();
		p.y= random.nextInt(height);
		p.x= random.nextInt(width);
		map[p.y][p.x] = new Human(this, p);
		OrganismQueue.addElement(map[p.y][p.x]);
		for (int i = 0; i < height * width * DEFAULT_DENSITY; i++)
		{
			p= new Point();
			p.y = random.nextInt(height);
			p.x = random.nextInt(width);
			while (map[p.y][p.x] != null)
			{
				p.y = random.nextInt(height);
				p.x = random.nextInt(width);
			}
			if (i % NUMBER_OF_ORGANISMS == 0)
			{
				map[p.y][p.x] = new Wolf(this, p);
				OrganismQueue.addElement(map[p.y][p.x]);
			}
			else if (i % NUMBER_OF_ORGANISMS == 1)
			{
				map[p.y][p.x] = new Sheep(this, p);
				OrganismQueue.addElement(map[p.y][p.x]);
			}
			else if (i % NUMBER_OF_ORGANISMS == 2)
			{
				map[p.y][p.x] = new Fox(this, p);
				OrganismQueue.addElement(map[p.y][p.x]);
			}
			else if (i % NUMBER_OF_ORGANISMS == 3)
			{
				map[p.y][p.x] = new Turtle(this, p);
				OrganismQueue.addElement(map[p.y][p.x]);
			}
			else if (i % NUMBER_OF_ORGANISMS == 4)
			{
				map[p.y][p.x] = new Antelope(this, p);
				OrganismQueue.addElement(map[p.y][p.x]);
			}
			else if (i % NUMBER_OF_ORGANISMS == 5)
			{
				map[p.y][p.x] = new CyberSheep(this, p);
				OrganismQueue.addElement(map[p.y][p.x]);
			}
			else if (i % NUMBER_OF_ORGANISMS == 6)
			{
				map[p.y][p.x] = new Grass(this, p);
				OrganismQueue.addElement(map[p.y][p.x]);
			}
			else if (i % NUMBER_OF_ORGANISMS == 7)
			{
				map[p.y][p.x] = new Dandelion(this, p);
				OrganismQueue.addElement(map[p.y][p.x]);
			}
			else if (i % NUMBER_OF_ORGANISMS == 8)
			{
				map[p.y][p.x] = new Guarana(this, p);
				OrganismQueue.addElement(map[p.y][p.x]);
			}
			else if (i % NUMBER_OF_ORGANISMS == 9)
			{
				map[p.y][p.x] = new Belladonna(this, p);
				OrganismQueue.addElement(map[p.y][p.x]);
			}
			else if (i % NUMBER_OF_ORGANISMS == 10)
			{
				map[p.y][p.x] = new SosnowskiHogweed(this, p);
				OrganismQueue.addElement(map[p.y][p.x]);
			}
		}
		OrganismQueue.sort(Comparator.comparing(Organism::GetInitiative).reversed());
	}
	
	private void DrawWorld()
	{
		frame = new MyFrame(this, map, height, width);
	}
	
	private void LoadLife(String path) throws FileNotFoundException, ClassNotFoundException
	{
		int i = 0;
		File file = new File(path);
		Scanner scan = new Scanner(file);
		String name = new String();
		int x=0,y=0,strength;
		
		while(scan.hasNextLine())
		{
			if(i % 4 == 0)
			{
				name = scan.nextLine();
			}
			else if(i % 4 == 1)
			{
				x = Integer.parseInt(scan.nextLine());
			}
			else if(i % 4 == 2)
			{
				y = Integer.parseInt(scan.nextLine());
			}
			else if(i % 4 == 3)
			{
				strength = Integer.parseInt(scan.nextLine());
				Point p = new Point();
				p.x=x;
				p.y=y;
				if(map[p.y][p.x] == null)
				{
					if(name.equals("Human"))
					{
						map[p.y][p.x] = new Human(this, p);
						map[p.y][p.x].SetStrength(strength);
						OrganismQueue.addElement(map[p.y][p.x]);
					}
					else if (name.equals("Wolf"))
					{
						map[p.y][p.x] = new Wolf(this, p);
						map[p.y][p.x].SetStrength(strength);
						OrganismQueue.addElement(map[p.y][p.x]);
					}
					else if (name.equals("Sheep"))
					{
						System.out.println("SCAN");
						map[p.y][p.x] = new Sheep(this, p);
						map[p.y][p.x].SetStrength(strength);
						OrganismQueue.addElement(map[p.y][p.x]);
					}
					else if (name.equals("Fox"))
					{
						map[p.y][p.x] = new Fox(this, p);
						map[p.y][p.x].SetStrength(strength);
						OrganismQueue.addElement(map[p.y][p.x]);
					}
					else if (name.equals("Turtle"))
					{
						map[p.y][p.x] = new Turtle(this, p);
						map[p.y][p.x].SetStrength(strength);
						OrganismQueue.addElement(map[p.y][p.x]);
					}
					else if (name.equals("Antelope"))
					{
						map[p.y][p.x] = new Antelope(this, p);
						map[p.y][p.x].SetStrength(strength);
						OrganismQueue.addElement(map[p.y][p.x]);
					}
					else if (name.equals("CyberSheep"))
					{
						map[p.y][p.x] = new CyberSheep(this, p);
						map[p.y][p.x].SetStrength(strength);
						OrganismQueue.addElement(map[p.y][p.x]);
					}
					else if (name.equals("Grass"))
					{
						map[p.y][p.x] = new Grass(this, p);
						map[p.y][p.x].SetStrength(strength);
						OrganismQueue.addElement(map[p.y][p.x]);
					}
					else if (name.equals("Dandelion"))
					{
						map[p.y][p.x] = new Dandelion(this, p);
						map[p.y][p.x].SetStrength(strength);
						OrganismQueue.addElement(map[p.y][p.x]);
					}
					else if (name.equals("Guarana"))
					{
						map[p.y][p.x] = new Guarana(this, p);
						map[p.y][p.x].SetStrength(strength);
						OrganismQueue.addElement(map[p.y][p.x]);
					}
					else if (name.equals("Belladonna"))
					{
						map[p.y][p.x] = new Belladonna(this, p);
						map[p.y][p.x].SetStrength(strength);
						OrganismQueue.addElement(map[p.y][p.x]);
					}
					else if (name.equals("SosnowskiHogweed"))
					{
						map[p.y][p.x] = new SosnowskiHogweed(this, p);
						map[p.y][p.x].SetStrength(strength);
						OrganismQueue.addElement(map[p.y][p.x]);
					}
				}
			}
			i++;
		}
	}
	
	public World()
	{
		height=30;
		width=30;
		map = new Organism[height][width];
		OrganismQueue = new Vector<Organism>();
		NewOrganism = new Vector<Organism>();
		Events= new String("World Created!");
		NewLife();
		DrawWorld();
	}
	
	public World(String path) throws FileNotFoundException, ClassNotFoundException
	{
		height=30;
		width=30;
		map = new Organism[height][width];
		OrganismQueue = new Vector<Organism>();
		NewOrganism = new Vector<Organism>();
		Events= new String("World Loaded!");
		LoadLife(path);
		DrawWorld();
	}
	
	public Organism GetOrganism(Point p)
	{
		return map[p.y][p.x];
	}
	
	public int GetHeight()
	{
		return height;
	}
	
	public int GetWidth()
	{
		return width;
	}
	
	public int MoveOrganism(Point op, Point np)
	{
		if(map[np.y][np.x] == null)
		{
			map[np.y][np.x]= map[op.y][op.x];
			map[np.y][np.x].SetPosition(np);
			map[op.y][op.x]=null;
			return 0;
		}
		else
		{
			map[np.y][np.x].Collision(map[op.y][op.x]);
			if (map[np.y][np.x] == null || map[np.y][np.x].GetAlive() == false)
			{
				map[np.y][np.x] = map[op.y][op.x];
				map[np.y][np.x].SetPosition(np);
				map[op.y][op.x] = null;
			}
			else if (map[op.y][op.x] != null && map[op.y][op.x].GetAlive() == false)
			{
				map[op.y][op.x] = null;
			}
			return 1;
		}
	}
	
	public void AddOrganism(Organism o)
	{
		map[o.GetPosition().y][o.GetPosition().x]=o;
		NewOrganism.addElement(o);
	}
	
	public void DelOrganism(Organism o)
	{
		map[o.GetPosition().y][o.GetPosition().x] = null;
	}
	
	public void TakeATurn()
	{
		for(int i = 0; i < OrganismQueue.size(); i++)
		{
			//System.out.println(OrganismQueue.get(i).GetName());
			OrganismQueue.get(i).Action();
		}
		
		while(NewOrganism.size() > 0)
		{
			OrganismQueue.addElement(NewOrganism.firstElement());
			NewOrganism.remove(NewOrganism.firstElement());
		}
		for(int i = 0; i < OrganismQueue.size(); i++)
		{
			if(OrganismQueue.get(i).GetAlive() == false)
			{
				OrganismQueue.remove(i);
				i--;
			}
		}
		OrganismQueue.sort(Comparator.comparing(Organism::GetInitiative).reversed());
	}
	
	public void AddEvent(String nEvent)
	{
		Events = Events + nEvent + "\n";
	}
	
	public String GetAClearEvents()
	{
		String returnable = new String(Events);
		Events = "";
		return returnable;
	}
	
	public void SetHumanDirection(int dir)
	{
		humandirection=dir;
	}
	
	public int GetHumanDirection()
	{
		return humandirection;
	}
	
	public void SaveWorld() throws IOException
	{
		File file = new File("save.txt");
		FileWriter fw = new FileWriter(file);
		PrintWriter pw = new PrintWriter(fw);
		for(int i = 0; i < OrganismQueue.size(); i++)
		{
			if(OrganismQueue.get(i).GetAlive()) 
			{
				pw.println(OrganismQueue.get(i).GetName());
				pw.println(OrganismQueue.get(i).GetPosition().x);
				pw.println(OrganismQueue.get(i).GetPosition().y);
				pw.println(OrganismQueue.get(i).GetStrength());
			}
		}
		pw.close();
		System.out.println("Save OK");
	}
}

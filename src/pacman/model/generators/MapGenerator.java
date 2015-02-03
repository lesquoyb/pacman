package pacman.model.generators;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import pacman.controller.gamelogic.Map;
import pacman.model.gameobjects.Floor;
import pacman.model.gameobjects.StartingPoint;
import pacman.model.gameobjects.Wall;
import pacman.model.gameobjects.Wormhole;

import com.badlogic.gdx.files.FileHandle;

/**
 * map format:
 *	  each digit represents an object on the map (see the static bytes values for more details)
 *	  each line must have the same amount of object
 *	  only the wormhole digit must be followed by another digit which should be its id (from 0 to 9)
 *	  each other object is followed by a space(' ')or a new line/carriage return('\n')
 * @author baptiste
 *
 */
public class MapGenerator extends Generator{

	public final static byte floor = '0';
	public final static byte wall = '1';
	public final static byte pacman = 'p';
	public final static byte superpacgum = '3';
	public final static byte Bghost = 'b';
	public final static byte Rghost = 'r';
	public final static byte Yghost = 'y';
	public final static byte Gghost = 'g';
	public final static byte wormhole = '@';
	
	private Map map;
	
	
	public MapGenerator(FileHandle f, Map m) {
		super(f);
		map = m;
	}

	@Override
	public boolean generate() {
		InputStream stream =  file.read();
		int x = 0;
		int y = 0;
		int firstLine = -1;
		ArrayList<Wormhole> wormholes = new ArrayList<Wormhole>();
		
		try {

			short input;
			
			do{
				input = (short)  stream.read();

				switch (input){
					case floor:
						map.addFloor(x,y);
						break;
					case wall:
						map.addWall(x,y);
						break;
					case pacman:
						map.addStartingPoint(x,y, StartingPoint.characters.pacman);
						break;
					case superpacgum:
						break;
					case Bghost :
						map.addStartingPoint(x,y,StartingPoint.characters.Bghost );
						break;
					case Rghost :
						map.addStartingPoint(x,y,StartingPoint.characters.Rghost );
						break;
					case  Yghost:
						map.addStartingPoint(x,y,StartingPoint.characters.Yghost );
						break;
					case Gghost :
						map.addStartingPoint(x,y,StartingPoint.characters.Gghost );
						break;
						
					case wormhole:
						byte id = (byte) stream.read();
						if ( id < '0' || id > '9'){
							errorMessage = "Le trou de ver numéro" + (wormholes.size()+1) + " à la position: "+ (x+1) +", " + (y+1) + " a un identifiant incorrecte (" + id + " )";
							return false;
						}
						wormholes.add(map.addWormhole(x,y,id));
						break;
					case 13: // carriage return
						
						// we check that there is the same number of block each line, if not we return false
						if( firstLine == -1){
							firstLine = x;
						}
						else if (firstLine != x){
							errorMessage = "le nombre d'élément par ligne n'est pas le même partout (erreur survenue à la ligne: "+ (y + 1) +" ).";
							return false;
						}
						
						y++;
						x = 0;
						break;
				}
				if(input != 10 && input != 13 && input != ' '){
					x++;
				}
			}
			while(input!= -1);
			
		} catch (IOException e) { e.printStackTrace(); }
		
		//we check that each wormhole id exists twice
		while(wormholes.size() != 0){
			if(wormholes.size()%2 != 0){
				errorMessage = "Tous les trous de vers n'ont pas un bînome.";
				return false;
			}
			int first = wormholes.get(0).id;
			for(int i = 1 ; i < wormholes.size() ; i++){
				int id = wormholes.get(i).id;
				if (id == first){
					wormholes.get(0).linked = wormholes.get(i);
					wormholes.get(i).linked = wormholes.get(0);
					wormholes.remove(i);
					break;
				}
			}
			wormholes.remove(0);
		}
		
		map.width = x;
		map.height = y;
		
		return true;
	}

}

package pacman.generators;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import pacman.gamelogic.Map;
import pacman.gameobjects.Floor;
import pacman.gameobjects.StartingPoint;
import pacman.gameobjects.Wall;
import pacman.gameobjects.Wormhole;

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
	public final static byte ghost = '4';
	public final static byte wormhole = '@';
	
	private Map map;
	
	
	public MapGenerator(FileHandle f, Map m) {
		super(f);
		map = m;
	}

	@Override
	public boolean generate() {
		InputStream stream =  file.read();
		byte x = 0;
		byte y = 0;
		byte firstLine = -1;
		ArrayList<Byte> ids = new ArrayList<Byte>();
		
		try {

			short input;
			
			do{
				input = (byte)  stream.read();

				switch (input){
					case floor:
						map.addElement(new Floor(x,y));
						break;
					case wall:
						map.addElement(new Wall(x,y));
						break;
					case pacman:
						map.addStartingPoint(new StartingPoint(x,y, StartingPoint.characters.pacman));
						break;
					case superpacgum:
						break;
					case ghost:
						map.addStartingPoint(new StartingPoint(x,y,StartingPoint.characters.ghost ));
						break;
					case wormhole:
						byte id = (byte) stream.read();
						if ( id < '0' || id > '9'){
							errorMessage = "Le trou de ver numéro" + (ids.size()+1) + " à la position: "+ (x+1) +", " + (y+1) + " a un identifiant incorrecte (" + id + " )";
							return false;
						}
						ids.add(id);
						map.addElement(new Wormhole(x,y,id));
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
		while(ids.size() != 0){
			if(ids.size()%2 != 0){
				errorMessage = "Tous les trous de vers n'ont pas un bînome.";
				return false;
			}
			byte first = ids.get(0);
			for(short i = 1 ; i < ids.size() ; i++){
				byte id = ids.get(i);
				if (id == first){
					ids.remove(i);
					break;
				}
			}
			ids.remove(0);
		}
		
		map.setWidth(x);
		map.setHeight(y);
		
		return true;
	}

}

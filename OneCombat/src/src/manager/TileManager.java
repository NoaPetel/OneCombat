package src.manager;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import src.Ronces.Ronces;
import src.map.Tile.Tile;
import system.AnimationManager;
import system.GameSystem;
import system.enumeration.TileType;
import system.object.component.monobehavior.MonoBehavior;
import system.object.component.transform.Transform;
import system.object.gameobject.GameObject;
import system.physics.FlatVector;
import system.scene.IScene;

public class TileManager extends MonoBehavior{
	
	public static TileManager instance;
	
	int x_centrage = -31;
	int y_centrage = -5;

	
	Tile[][] tile; // matrice de la carte
	int[][] tiletype; //type des tiles
	IScene sc; // scène
	int map_length;
	int map_height;
	BufferedImage[] sprites;
	int iterRonces = 0;
	int feRonces = 9;
	
		
	public TileManager(GameObject g, Transform  t, String name, int length_map, int height_map) {
		super(g,t);
		instance = this;
		this.sc = GameSystem.system.sceneManager.getCurrentScene();
		this.map_length = length_map;
		this.map_height = height_map;
		try {
			this.sprites = AnimationManager.loadSprites("ressources/tiles/Tileset.png", 6, 8);
		} catch (IOException e) {
			e.printStackTrace();
		}
		tile = new Tile[map_length][map_height];
		tiletype = new int[map_length][map_height];
		this.loadMap(name);
		affichageMap(sc);
	}
	
	public void affichageMap(IScene sc) {
		for (int j=0; j<map_height;j++) {
			for (int i=0; i<map_length;i++) {
				// tile en haut de l'écran this.tile[i][j] = new Tile(this.tiletype[i][j],(i*Tile.LENGTH_TILE)-500,(j*Tile.HEIGHT_TILE)-375);
				if(tiletype[i][j] != 0) {
					this.tile[i][j] = new Tile(this.tiletype[i][j],(i*Tile.LENGTH_TILE)+x_centrage,(j*Tile.HEIGHT_TILE)+y_centrage, this.sprites); //tile au milieu
					try {
						spawnRonces(this.tiletype[i][j], i, j);
					} catch (IOException e) {
						e.printStackTrace();
					}
					sc.addGameObject(tile[i][j]);
				}
			}
		}
	}

	
	public void loadMap(String Filename) {
		try
	    {
	      // Le fichier d'entrée
	      File file = new File("ressources/map/"+Filename);    
	      // Créer l'objet File Reader
	      FileReader fr = new FileReader(file);  
	      // Créer l'objet BufferedReader        
	      BufferedReader br = new BufferedReader(fr);   
	      String line; 
	      for(int row = 0; row<this.map_height;row++)
	      {
	    	  line = br.readLine(); // lecture de la ligne
	    	  String[] numbers = line.split(" ");
		      for (int col=0; col<numbers.length;col++) {
		            this.tiletype[col][row] = Integer.parseInt(numbers[col]);
		        }
		      
	      }
	      fr.close();     
	    }
	    catch(IOException e)
	    {
	      e.printStackTrace();
	    }

	}
	
	public void spawnRonces(int type, int i, int j) throws IOException {
				if(type == TileType.Grass.getValue() && (iterRonces%feRonces == 0))
		{
			sc.addGameObject(new Ronces((i*Tile.LENGTH_TILE)+x_centrage, (j*Tile.HEIGHT_TILE)+y_centrage-1));
		}
		
		iterRonces++;
	}
	
	public static int[][] getGrid(){
		return instance.tiletype;
	}
	public static int getLength(){
		return instance.map_length;
	}
	public static int getHeight(){
		return instance.map_height;
	}
		
	public static int[] getTile(FlatVector vec){
		float deltaX = vec.x +31;
		float deltaY = vec.y + 7; //NE PAS TOUCHER
		
		int x = (int)(deltaX / Tile.LENGTH_TILE);
		int y = (int)(deltaY / Tile.HEIGHT_TILE);
		int[] t = { x, y };
		return t;
	}
	
	public void updateTileType(Tile t) {
		int Matrice_x = (int) (t.getPosx()-x_centrage);
		int Matrice_y = (int) (t.getPosy()-y_centrage);
		this.tiletype[Matrice_x][Matrice_y] = 0;
	}
	
}

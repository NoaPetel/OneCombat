package system;

public class Constante {
	
	//Physics Properties
	public static float GRAVITY = 9.98f;
	
	public static float MINBODYSIZE = 0.01f * 0.01f;
	public static float MAXBODYSIZE = 1000f * 1000f;
	
	public static float MINDENSITY = 0.5f;
	public static float MAXDENSITY = 21.4f;
	
	public static int MINITERATIONS = 1;
	public static int MAXITERATIONS = 128;
	public static int CURRENTITERATIONS = 1;
	
	//Region Properties
	public static int REGIONSIZE = 15;
	
	//Debug Properties
	public static boolean ISDEBUGMODE = false;
	
	//AUdio Properties
	public static float SOUNDVOLUME = 0.6f;
	public static float MUSICVOLUME = 0.6f;
	
	//MAP
	public static final String NAME_MAP = "/Map_debut.txt/";  // mettre dans le dossier ressource/map
	public static int MAP_LENGTH = 57; //Taille x de la map (Colonnes)
	public static int MAP_HEIGHT = 23; //Taille y de la map (Lines)
	
}
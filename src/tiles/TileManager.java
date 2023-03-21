package tiles;

//graphics
import java.awt.Graphics2D;
import java.io.IOException;
import javax.imageio.ImageIO;

//image readers
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

//classes
import main.GamePanel;
import main.UtilityTool;
import Entity.Player;

public class TileManager {

	GamePanel gp;
	Player player;
	public Tile[] tile;
	public int mapTileNum[][];
	public int mapNumber = 0;
	
	public TileManager(GamePanel gp) {
		
		this.gp = gp;
		
		tile = new Tile[46]; //amount of titles
		mapTileNum = new int[gp.WorldCol][gp.WorldRow];
		
		getTileImage();
		if(mapNumber == 0) {
			loadMap("/maps/worldMapP.txt");
		} else if(mapNumber == 1) {
			loadMap("/maps/hut1.txt");
		} else if(mapNumber == 3) {
			loadMap("/maps/worldMapP_aftermonkey.txt");
		}
	}

	private void getTileImage() {

		// PLACEHOLDERS
		setup(0, "/background/grassBlank", false);
		setup(1, "/background/grassBlank", false);
		setup(2, "/background/grassBlank", false);
		setup(3, "/background/grassBlank", false);
		setup(4, "/background/grassBlank", false);
		setup(5, "/background/grassBlank", false);
		setup(6, "/background/grassBlank", false);
		setup(7, "/background/grassBlank", false);
		setup(8, "/background/grassBlank", false);
		setup(9, "/background/grassBlank", false);
		// PLACEHOLDERS
		
		// grass
		setup(10, "/background/grassBlank", false);
		setup(11, "/background/grass", false);
		setup(12, "/background/grassTreeShadow", false);
		setup(13, "/background/tree", true);
		setup(14, "/background/treeWithShadow", true);
		
		// water
		setup(15, "/waterTiles/water", true);
		setup(16, "/waterTiles/waterDetails", true);
		setup(17, "/waterTiles/waterT", true);
		setup(18, "/waterTiles/waterTL", true);
		setup(19, "/waterTiles/waterL", true);
		setup(20, "/waterTiles/waterBL", true);
		setup(21, "/waterTiles/waterB", true);
		setup(22, "/waterTiles/waterBR", true);
		setup(23, "/waterTiles/waterR", true);
		setup(24, "/waterTiles/waterTR", true);
		setup(25, "/waterTiles/grassTreeShadowWater2", true);
		setup(26, "/waterTiles/waterTLC", true);
		setup(27, "/waterTiles/waterTRC", true);
		
		// sand
		setup(28, "/sandTiles/sand", false);
		setup(29, "/sandTiles/sandBald", false);
		setup(30, "/sandTiles/sandBald1", false);
		setup(31, "/sandTiles/sandDetails", false);
		setup(32, "/sandTiles/tree sand", true);
		
		// water cont.
		setup(33, "/waterTiles/waterBRC", true);
		setup(34, "/waterTiles/waterBLC", true);
		
		// hut
		setup(35, "/hutTiles/hutTopLeft", true);
		setup(36, "/hutTiles/hutTopRight", true);
		setup(37, "/hutTiles/hutBotRight", true);
		setup(38, "/hutTiles/hutTopBorder", true);
		setup(39, "/hutTiles/hutLeftBoder", true);
		setup(40, "/hutTiles/hutRightBorder", true);
		setup(41, "/hutTiles/hutBotBorder", true);
		setup(42, "/hutTiles/hutInside", false);
		setup(43, "/hutTiles/invisTiles", false);
		
		// trash
		setup(44, "/sandTiles/sandTrash", true);
		setup(45, "/sandTiles/sandTrash1", true);
	}
	public void loadMap(String filePath) {
		try {
			InputStream is = getClass().getResourceAsStream(filePath);
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			
			int col = 0, row = 0;
			
			while(col < gp.WorldCol && row < gp.WorldRow) {
				
				String line = br.readLine(); //reads line of text
				
				while(col < gp.WorldCol) {
					String numbers[] = line.split(" "); //text line number array
					
					int num = Integer.parseInt(numbers[col]); //change string to int
					
					mapTileNum[col][row] = num;
					col++;
				}
				if(col == gp.WorldCol) {
					col = 0;
					row++;
				}
			}
			br.close();
			
		}catch(Exception e) {
			
		}
	}
	
	public void setup(int index, String imageName, boolean collision) {
		
		UtilityTool uTool = new UtilityTool();
		
		try {
			
			tile[index] = new Tile();
			tile[index].image = ImageIO.read(getClass().getResourceAsStream(imageName + ".png"));
			tile[index].image = uTool.scaledImage(tile[index].image, gp.TileSize, gp.TileSize);
			tile[index].collision = collision;
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	   public void draw(Graphics2D g2) {

	        int worldCol = 0;
	        int worldRow = 0;

	        while(worldCol < gp.WorldCol && worldRow < gp.WorldRow) {

	            int tileNum = mapTileNum[worldCol][worldRow];

	            int worldX = worldCol * gp.TileSize;
	            int worldY = worldRow * gp.TileSize;
	            int screenX = worldX - gp.player.worldX + gp.player.screenX;
	            int screenY = worldY - gp.player.worldY + gp.player.screenY;
	          
	            g2.drawImage(tile[tileNum].image, screenX, screenY, null);
	            worldCol++;

	            if(worldCol == gp.WorldCol) {
	                worldCol = 0;
	                worldRow++;
	            }
	        }
	    }
}
	
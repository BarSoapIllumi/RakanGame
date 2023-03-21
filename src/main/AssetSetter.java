package main;

//EXTERNAL CLASSES
import Entity.NPC_Vi;
import objects.JINX_monkey;
import objects.RAKAN_feather;
import objects.XAYAH_feather;
import objects.door;
import objects.hut;

public class AssetSetter {

	GamePanel gp;
	
	public AssetSetter(GamePanel gp) {
		this.gp = gp;
	}
	
	public void setOBJ() { // Create objects
		
		gp.obj[0] = new JINX_monkey(gp); // Create object
		gp.obj[0].worldX = 47 * gp.TileSize; // Set object x position
		gp.obj[0].worldY = 21 * gp.TileSize;// Set object y position
		
		gp.obj[1] = new RAKAN_feather(gp);
		gp.obj[1].worldX = 23 * gp.TileSize;
		gp.obj[1].worldY = 35 * gp.TileSize;
		
		gp.obj[2] = new XAYAH_feather(gp);
		gp.obj[2].worldX = 52 * gp.TileSize;
		gp.obj[2].worldY = 6 * gp.TileSize;
		
		gp.obj[3] = new hut(gp);
		gp.obj[3].worldX = 46 * gp.TileSize;
		gp.obj[3].worldY = 12 * gp.TileSize;
		
		gp.obj[4] = new hut(gp);
		gp.obj[4].worldX = 53 * gp.TileSize;
		gp.obj[4].worldY = 13 * gp.TileSize;
		
		gp.obj[5] = new hut(gp);
		gp.obj[5].worldX = 48 * gp.TileSize;
		gp.obj[5].worldY = 8 * gp.TileSize;
		
		gp.obj[6] = new door(gp);
		gp.obj[6].worldX = 5 * gp.TileSize;
		gp.obj[6].worldY = 11 * gp.TileSize;
		
	}
	public void setNPC() {
		
		gp.npc[0] = new NPC_Vi(gp); // Create new NPC
		gp.npc[0].worldX = gp.TileSize*51; // Set NPC position
		gp.npc[0].worldY = gp.TileSize*23;
	}
}

package main;

import Entity.Entity;

public class CollisionDetection {
	
	GamePanel gp;

	public CollisionDetection(GamePanel gp) {
		this.gp = gp;
	}
	
	public void checkTile(Entity entity) {
		
		int entityLeftWorldX = entity.worldX + entity.solidArea.x; // NPC/player left side world position
		int entityRightWorldX = entity.worldX + entity.solidArea.x + entity.solidArea.width;
		int entityTopWorldY = entity.worldY + entity.solidArea.y;
		int entityBotWorldY = entity.worldY + entity.solidArea.y + entity.solidArea.height;
		
		int entityLeftCol = entityLeftWorldX/gp.TileSize; // entity's tile position
		int entityRightCol = entityRightWorldX/gp.TileSize;
		int entityTopRow = entityTopWorldY/gp.TileSize;
		int entityBotRow = entityBotWorldY/gp.TileSize;
		
		int tileNum1, tileNum2;
		
		switch(entity.direction) {
		case "up":
			entityTopRow = (entityTopWorldY - entity.speed)/gp.TileSize;
			tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
			tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];
			if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) { // If entity hitbox is touching solid background
				entity.collisionOn = true; // Player/NPC can no longer move
			}
			break;
		case "down":
			entityBotRow = (entityBotWorldY + entity.speed)/gp.TileSize;
			tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityBotRow];
			tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityBotRow];
			if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
				entity.collisionOn = true;
			}
			break;
		case "left":
			entityLeftCol = (entityLeftWorldX - entity.speed)/gp.TileSize;
			tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
			tileNum2 = gp.tileM.mapTileNum[entityLeftCol][entityBotRow];
			if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
				entity.collisionOn = true;
			}
			break;
		case "right":
			entityRightCol = (entityRightWorldX + entity.speed)/gp.TileSize;
			tileNum1 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];
			tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityBotRow];
			if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
				entity.collisionOn = true;
			}
			break;
		}
	}
	
	public int checkObject(Entity entity, boolean player) { // Check for collision against objects
		
		int index = 999; // Default number, in player class
		
		for(int i = 0; i < gp.obj.length; i++) { // scan through object array
			
			if(gp.obj[i] != null) {
				
				// Get entity solid area position
				entity.solidArea.x = entity.worldX + entity.solidArea.x;
				entity.solidArea.y = entity.worldY + entity.solidArea.y;
				
				// Get object solid area position
				if(i == 0 || i == 1 || i == 2 || i == 6) { // If normal object
					gp.obj[i].solidArea.x = gp.obj[i].worldX + gp.obj[i].solidArea.x;
					gp.obj[i].solidArea.y = gp.obj[i].worldY + gp.obj[i].solidArea.y;				
				}
				if(i == 3 || i == 4 || i == 5) { // If interactable hut
						gp.obj[i].solidArea.x = gp.obj[i].worldX + gp.TileSize;
						gp.obj[i].solidArea.y = gp.obj[i].worldY + gp.TileSize;
				}
					switch(entity.direction) {
						case "up":
							entity.solidArea.y -= entity.speed;
							if(entity.solidArea.intersects(gp.obj[i].solidArea)) { // If player hitbox overlaps object hitbox
								if(gp.obj[i].collision == true) {
									entity.collisionOn = true;
								}
								if(player == true) {
									index = i;
								}
								break;
						}
						case "down":
							entity.solidArea.y += entity.speed;
							if(entity.solidArea.intersects(gp.obj[i].solidArea)) { 
							if(gp.obj[i].collision == true) {
								entity.collisionOn = true;
							}
							if(player == true) {
								index = i;
							}
						break;
					}
					case "left":
						entity.solidArea.x -= entity.speed;
						if(entity.solidArea.intersects(gp.obj[i].solidArea)) { 
							if(gp.obj[i].collision == true) {
								entity.collisionOn = true;
							}
							if(player == true) {
								index = i;
							}
							break;
						}
					case "right":
						entity.solidArea.x += entity.speed;
						if(entity.solidArea.intersects(gp.obj[i].solidArea)) {
							if(gp.obj[i].collision == true) {
								entity.collisionOn = true;
							}
						if(player == true) {
							index = i;
						}
						break;
						}
					}
				entity.solidArea.x = entity.solidArea_DX; // reset
				entity.solidArea.y = entity.solidArea_DY;
				gp.obj[i].solidArea.x = gp.obj[i].solidArea_DX;
				gp.obj[i].solidArea.y = gp.obj[i].solidArea_DY;
			}	
		}
		return index;
	}
	
	// NPC MONSTER COLLISION
	public int checkEntity(Entity entity, Entity[] target) {
		
		int index = 999;
		
		for(int i = 0; i < target.length; i++) {
			
			if(target[i] != null) {
				
				// Get player solid area position
				entity.solidArea.x = entity.worldX + entity.solidArea.x;
				entity.solidArea.y = entity.worldY + entity.solidArea.y;
				
				// Get npc solid area position
				target[i].solidArea.x = target[i].worldX + gp.TileSize;
				target[i].solidArea.y = target[i].worldY + gp.TileSize;		

				switch(entity.direction) {
					case "up":
						entity.solidArea.y -= entity.speed;
						if(entity.solidArea.intersects(target[i].solidArea)) { // If player hitbox overlaps object hitbox
							entity.collisionOn = true;
							index = i;
							}
							break;
					case "down":
						entity.solidArea.y += entity.speed;
						if(entity.solidArea.intersects(target[i].solidArea)) { 
							entity.collisionOn = true;
							index = i;
						}
						break;
					case "left":
						entity.solidArea.x -= entity.speed;
						if(entity.solidArea.intersects(target[i].solidArea)) { 
							entity.collisionOn = true;
							index = i;
							}
							break;
					case "right":
						entity.solidArea.x += entity.speed;
						if(entity.solidArea.intersects(target[i].solidArea)) {
							entity.collisionOn = true;
							index = i;
						}
						break;
				}
				entity.solidArea.x = entity.solidArea_DX; // reset
				entity.solidArea.y = entity.solidArea_DY;
				target[i].solidArea.x = target[i].solidArea_DX;
				target[i].solidArea.y = target[i].solidArea_DY;
			}	
		}
		return index;
	}
	
	public void checkPlayer(Entity entity) {
		
		// Get entity solid area position
		entity.solidArea.x = entity.worldX + entity.solidArea.x + gp.TileSize;
		entity.solidArea.y = entity.worldY + entity.solidArea.y + gp.TileSize;
		
		// Get object solid area position
		gp.player.solidArea.x = gp.player.worldX + gp.player.solidArea.x;
		gp.player.solidArea.y = gp.player.worldY + gp.player.solidArea.y;				

		switch(entity.direction) {
			case "up":
				entity.solidArea.y -= entity.speed;
				if(entity.solidArea.intersects(gp.player.solidArea)) { // If player hitbox overlaps object hitbox
					entity.collisionOn = true;
					}
					break;
			case "down":
				entity.solidArea.y += entity.speed;
				if(entity.solidArea.intersects(gp.player.solidArea)) { 
					entity.collisionOn = true;
				}
				break;
			case "left":
				entity.solidArea.x -= entity.speed;
				if(entity.solidArea.intersects(gp.player.solidArea)) { 
					entity.collisionOn = true;
					}
					break;
			case "right":
				entity.solidArea.x += entity.speed;
				if(entity.solidArea.intersects(gp.player.solidArea)) {
					entity.collisionOn = true;				}
				break;
		}
		entity.solidArea.x = entity.solidArea_DX; // reset
		entity.solidArea.y = entity.solidArea_DY;
		gp.player.solidArea.x = gp.player.solidArea_DX;
		gp.player.solidArea.y = gp.player.solidArea_DY;
		
	}
}
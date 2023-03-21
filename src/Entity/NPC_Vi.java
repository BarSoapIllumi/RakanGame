package Entity;

import java.awt.Rectangle;
import java.util.Random;
import main.GamePanel;

public class NPC_Vi extends Entity{
	
	public NPC_Vi(GamePanel gp) {
		
		super(gp);
		
		direction = "down";
		solidArea = new Rectangle(0, 0 , 48, 48);
		speed = 1;
		standing = false;
		getImage();
		setDialogue();
	}

	public void getImage() { //get image info
		
		stand1 = setup("/vi/Vi_stand 32b");
		stand2 = setup("/vi/Vi_stand 32b2");
		up1 = setup("/vi/Vi_back1");
		up2 = setup("/vi/Vi_back2");
		front1 = setup("/vi/Vi_front1");
		front2 = setup("/vi/Vi_front2");
		left1 = setup("/vi/Vi_left1");
		left2 = setup("/vi/Vi_left2");
		right1 = setup("/vi/Vi_right1");
		right2 = setup("/vi/Vi_right2");
		
	}
	
	public void setDialogue() {
		
		dialogue[1] = "Thanks for the monkey.";
		dialogue[2] = "Thanks for the feather.";
		dialogue[3] = "Yo traveller.";
		dialogue[4] = "I'm Vi, who are you?";
		dialogue[5] = gp.playerName; // This line does not show
		dialogue[6] = "Well " + gp.playerName + "...";
		dialogue[7] = "Could you help me find a few things?";
		dialogue[8] = "I need a monkey, and a purple feather in the northern trash heaps.";
		dialogue[9] = "Ah, you must be new... Zaun had dumped all their trash in the north so it looks\nlike that these days.";
		dialogue[10] = "Those houses in the north had long been abandonned...";
		dialogue[11] = "Sorry babe, gotta work now.";

	}
	
	public void setAction() { // Takes priority over one in Entity 
		
		actionLockCounter++;
		
		if(actionLockCounter == 120) {
			Random random = new Random();
			int i = random.nextInt(125) + 1; // 1 - 100
		
			if(i <= 25) { // super simple AI
				direction = "up";
			}
			if(i > 25 && i <= 50) {
				direction = "down";
			}
			if(i > 50 && i <= 75) {
				direction = "left";
			}
			if(i > 75 && i <= 100) {
				direction = "right";
			}
			if(i > 100 && i <= 125) {
				direction = "stand";
				standing = true;
			}
			actionLockCounter = 0;
		}	
	}
	
	public void speak() {
		
		if(gp.player.hasMonkey == 1 && gp.player.hasXayah == 1) {
			if(dialogue[dialogueIndex] == null) {
				dialogueIndex = 11;
			}
		}
		if(gp.player.hasMonkey == 1 && gp.player.hasXayah == 2) {
			dialogueIndex = 2;
		}
		if(gp.player.hasMonkey == 2 && gp.player.hasXayah == 1) {
			dialogueIndex = 1;
		}
		if(gp.player.hasMonkey == 2 && gp.player.hasXayah == 2) {
			gp.gameState = gp.endState;
		}
		gp.ui.currentDialogue = dialogue[dialogueIndex];
		dialogueIndex++;
		
		super.speak();
	}
	
}

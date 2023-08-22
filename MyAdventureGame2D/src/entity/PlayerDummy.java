package entity;

import main.GamePanel;

public class PlayerDummy extends Entity {
	public static final String npcName = "Dummy";

	public PlayerDummy(GamePanel gp) {
		super(gp);
		this.name = "Dummy";
		getImage();
	}

	public void getImage() {
		this.gp.getClass();
		this.gp.getClass();
		this.up1 = setup("/player/boy_up_1", 48, 48);
		this.gp.getClass();
		this.gp.getClass();
		this.up2 = setup("/player/boy_up_2", 48, 48);
		this.gp.getClass();
		this.gp.getClass();
		this.down1 = setup("/player/boy_down_1", 48, 48);
		this.gp.getClass();
		this.gp.getClass();
		this.down2 = setup("/player/boy_down_2", 48, 48);
		this.gp.getClass();
		this.gp.getClass();
		this.left1 = setup("/player/boy_left_1", 48, 48);
		this.gp.getClass();
		this.gp.getClass();
		this.left2 = setup("/player/boy_left_2", 48, 48);
		this.gp.getClass();
		this.gp.getClass();
		this.right1 = setup("/player/boy_right_1", 48, 48);
		this.gp.getClass();
		this.gp.getClass();
		this.right2 = setup("/player/boy_right_2", 48, 48);
	}
}

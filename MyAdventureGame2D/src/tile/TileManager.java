package tile;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.ImageObserver;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import ai.Node;
import main.GamePanel;
import main.UtilityTool;

public class TileManager {
	GamePanel gp;

	public Tile[] tile;

	public int[][][] mapTileNum;

	boolean drawPath = false;

	ArrayList<String> fileNames = new ArrayList<>();

	ArrayList<String> collisionStatus = new ArrayList<>();

	public TileManager(GamePanel gp) {
		this.gp = gp;
		InputStream is = getClass().getResourceAsStream("/maps/tiledata.txt");
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		try {
			String line;
			while ((line = br.readLine()) != null) {
				this.fileNames.add(line);
				this.collisionStatus.add(br.readLine());
			}
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.tile = new Tile[this.fileNames.size()];
		getTileImage();
		is = getClass().getResourceAsStream("/maps/worldmap.txt");
		br = new BufferedReader(new InputStreamReader(is));
		try {
			String line2 = br.readLine();
			String[] maxTile = line2.split(" ");
			gp.maxWorldCol = maxTile.length;
			gp.maxWorldRow = maxTile.length;
			gp.getClass();
			this.mapTileNum = new int[10][gp.maxWorldCol][gp.maxWorldRow];
			br.close();
		} catch (IOException e) {
			System.out.println("Exception!");
		}
		loadMap("/maps/worldmap.txt", 0);
		loadMap("/maps/indoor01.txt", 1);
		loadMap("/maps/dungeon01.txt", 2);
		loadMap("/maps/dungeon01b.txt", 3);
		loadMap("/maps/dungeon02.txt", 4);
	}

	public void getTileImage() {
		for (int i = 0; i < this.fileNames.size(); i++) {
			boolean collision;
			String fileName = this.fileNames.get(i);
			if (this.collisionStatus.get(i).equals("true")) {
				collision = true;
			} else {
				collision = false;
			}
			setup(i, fileName, collision);
		}
	}

	public void setup(int index, String imageName, boolean collision) {
		UtilityTool uTool = new UtilityTool();
		try {
			this.tile[index] = new Tile();
			(this.tile[index]).image = ImageIO.read(getClass().getResourceAsStream("/tiles/" + imageName));
			this.gp.getClass();
			this.gp.getClass();
			(this.tile[index]).image = uTool.scaleImage((this.tile[index]).image, 48, 48);
			(this.tile[index]).collision = collision;
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void loadMap(String filePath, int map) {
		try {
			InputStream is = getClass().getResourceAsStream(filePath);
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			int col = 0;
			int row = 0;
			while (col < this.gp.maxWorldCol && row < this.gp.maxWorldRow) {
				String line = br.readLine();
				while (col < this.gp.maxWorldCol) {
					String[] numbers = line.split(" ");
					int num = Integer.parseInt(numbers[col]);
					this.mapTileNum[map][col][row] = num;
					col++;
				}
				if (col == this.gp.maxWorldCol) {
					col = 0;
					row++;
				}
			}
			br.close();
		} catch (Exception exception) {
		}
	}

	public void draw(Graphics2D g2) {
		int worldCol = 0;
		int worldRow = 0;
		while (worldCol < this.gp.maxWorldCol && worldRow < this.gp.maxWorldRow) {
			int tileNum = this.mapTileNum[this.gp.currentMap][worldCol][worldRow];
			this.gp.getClass();
			int worldX = worldCol * 48;
			this.gp.getClass();
			int worldY = worldRow * 48;
			int screenX = worldX - this.gp.player.worldX + this.gp.player.screenX;
			int screenY = worldY - this.gp.player.worldY + this.gp.player.screenY;
			this.gp.getClass();
			this.gp.getClass();
			this.gp.getClass();
			this.gp.getClass();
			if (worldX + 48 > this.gp.player.worldX - this.gp.player.screenX
					&& worldX - 48 < this.gp.player.worldX + this.gp.player.screenX
					&& worldY + 48 > this.gp.player.worldY - this.gp.player.screenY
					&& worldY - 48 < this.gp.player.worldY + this.gp.player.screenY)
				g2.drawImage((this.tile[tileNum]).image, screenX, screenY, (ImageObserver) null);
			worldCol++;
			if (worldCol == this.gp.maxWorldCol) {
				worldCol = 0;
				worldRow++;
			}
		}
		if (this.drawPath) {
			g2.setColor(new Color(255, 0, 0, 70));
			for (Node element : this.gp.pFinder.pathList) {
				this.gp.getClass();
				int worldX = element.col * 48;
				this.gp.getClass();
				int worldY = element.row * 48;
				int screenX = worldX - this.gp.player.worldX + this.gp.player.screenX;
				int screenY = worldY - this.gp.player.worldY + this.gp.player.screenY;
				this.gp.getClass();
				this.gp.getClass();
				g2.fillRect(screenX, screenY, 48, 48);
			}
		}
	}
}

package gameState;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import audio.JukeBox;
import entity.PlayerSave;
import handlers.Keys;


public class MenuState extends GameState {
	
	private BufferedImage head;
	private   BufferedImage bg;
	
	private int currentChoice = 0;
	private String[] options = {
		"Los gehts",
		
		"Beenden"
	};
	
	private Color titleColor;
	private Font titleFont;
	
	private Font font;
	private Font font2;
	
	public MenuState(GameStateManager gsm) {
		
		super(gsm);
		
		try {
			
			
			// load floating head
			head = ImageIO.read(
				getClass().getResourceAsStream("/HUD/Hud.gif")
			).getSubimage(0, 12, 12, 11);
			
			// titles and fonts
			titleColor = Color.WHITE;
			titleFont = new Font("Times New Roman", Font.BOLD, 32);
			font = new Font("Arial", Font.BOLD, 16);
			font2 = new Font("Arial", Font.PLAIN, 10);
			
			// load sound fx
			JukeBox.load("/SFX/menuoption.mp3", "menuoption");
			JukeBox.load("/SFX/menuselect.mp3", "menuselect");
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}

			// load background
	
try {
			
			
			
			bg = ImageIO.read(
				getClass().getResourceAsStream("/Backgrounds/menubg.gif")
			);
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}

	
	}
	public void init() {
		
	}
	
	public void update() {
		
		// check keys
		handleInput();
		
	}
	
	public void draw(Graphics2D g) {
				
		g.drawImage(bg, 0, 0, null);
		//g.setColor(Color.WHITE);
		//g.fillRect(0, 0, GamePanel.WIDTH, GamePanel.HEIGHT);
	
		
		
		// draw title
		g.setColor(titleColor);
		g.setFont(titleFont);
		g.drawString("Super Javario", 10, 45);
		
		// draw menu options
		g.setFont(font);
		g.setColor(Color.BLUE);
		g.drawString("Los gehts", 145, 165);
		g.drawString("Beenden", 145, 185);
		
		// draw floating head
		if(currentChoice == 0) g.drawImage(head, 125, 154, null);
		else if(currentChoice == 1) g.drawImage(head, 125, 174, null);
		
		// other
		g.setColor(Color.GREEN);
		g.setFont(font2);
		g.drawString("Steuerung:", 20, 132);
		g.drawString("Pfeiltasten - Bewegung", 20, 147);
		g.drawString("W - Springen", 20, 162);
		g.drawString("E - Sprinten", 20, 177);
		g.drawString("R - Angreifen", 20, 192);
		g.drawString("F - Rammen", 20, 207);
	}
	
	private void select() {
		if(currentChoice == 0) {
			JukeBox.play("menuselect");
			PlayerSave.init();
			gsm.setState(GameStateManager.LEVEL1ASTATE);
		}
		else if(currentChoice == 1) {
			System.exit(0);
		}
	}
	
	public void handleInput() {
		if(Keys.isPressed(Keys.ENTER)) select();
		if(Keys.isPressed(Keys.UP)) {
			if(currentChoice > 0) {
				JukeBox.play("menuoption", 0);
				currentChoice--;
			}
		}
		if(Keys.isPressed(Keys.DOWN)) {
			if(currentChoice < options.length - 1) {
				JukeBox.play("menuoption", 0);
				currentChoice++;
			}
		}
	}
	
}











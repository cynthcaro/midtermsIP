import javax.swing.JComponent;
import javax.swing.Timer;
import java.awt.Color;
import java.awt.Graphics;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.Random;

public class Draw extends JComponent{

	private BufferedImage image;
	private BufferedImage backgroundImage;
	public URL resource = getClass().getResource("run0.png");

	// circle's position
	public int x = 0;
	public int y = 0;
	public int height = 0;
	public int width = 0;

	// animation states
	public int state = 0;

	public Random randomizer;

	public int NumberEnemy;
	Monster[] monster = new Monster [10];

	public Draw(){
		randomizer = new Random();
		spawnEnemy();

		try{
			image = ImageIO.read(resource);
			backgroundImage = ImageIO.read(getClass().getResource("background.jpg"));
		}
		catch(IOException e){
			e.printStackTrace();
		}

		height = image.getHeight();
		width = image.getWidth();

		GameStart();
	}

	public void GameStart(){
		Thread gameThread = new Thread(new Runnable(){
			public void run(){
				while(true){
					try{
							for(int c = 0; c < monster.length; c++){
								if(monster[c]!=null){
									monster[c].moveTo(x,y);
									repaint();
								}
							}
							Thread.sleep(100);
					} catch (InterruptedException e){
								e.printStackTrace();
					}
				}
			}
		});
		gameThread.start();
	}

	public void spawnEnemy(){
		if(NumberEnemy < 11){
			monster[NumberEnemy] = new Monster(randomizer.nextInt(500), randomizer.nextInt(500), this);
			NumberEnemy++;
	}
}


	public void reloadImage(){
		state++;

		if(state == 0){
			resource = getClass().getResource("run0.png");
		}
		else if(state == 1){
			resource = getClass().getResource("run1.png");
		}
		else if(state == 2){
			resource = getClass().getResource("run2.png");
		}
		else if(state == 3){
			resource = getClass().getResource("run3.png");
		}
		else if(state == 4){
			resource = getClass().getResource("run4.png");
		}
		else if(state == 5){
			resource = getClass().getResource("run5.png");
			state = 0;
		}

		try{
			image = ImageIO.read(resource);
		}
		catch(IOException e){
			e.printStackTrace();
		}
	}

	public void attackAnimation(){
		Thread thread1 = new Thread(new Runnable(){
			public void run(){
				for(int ctr = 0; ctr < 5; ctr++){
					try {
						if(ctr==4){
							resource = getClass().getResource("run0.png");
						}
						else{
							resource = getClass().getResource("attack"+ctr+".png");
						}
						
						try{
							image = ImageIO.read(resource);
						}
						catch(IOException e){
							e.printStackTrace();
						}
				    repaint();
				    Thread.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}

				for(int x=0; x<monster.length; x++){
					if(monster[x]!=null){
						if(monster[x].contact){
							monster[x].life = monster[x].life - 10;
						}
					}
				}		
			}
		});
		thread1.start();
	}

	public void attack(){
		attackAnimation();
	}

	public void moveUp(){
		y = y - 5;
		reloadImage();
		repaint();
	}

	public void moveDown(){
		y = y + 5;
		reloadImage();
		repaint();
	}

	public void moveLeft(){
		x = x - 5;
		reloadImage();
		repaint();
	}

	public void moveRight(){
		x = x + 5;
		reloadImage();
		repaint();
	}

	public void checkCollision(){
		int xChecker = x + width;
		int yChecker = y;

		for(int x=0; x<monster.length; x++){
			boolean collideX = false;
			boolean collideY = false;

			if(monster[x]!=null){
				monster[x].contact = false;

				if(yChecker > monster[x].yPos){
					if(yChecker-monster[x].yPos < monster[x].height){
						collideY = true;
					}
				}
				else{
					if(monster[x].yPos - yChecker < monster[x].height){
						collideY = true;
					}
				}

				if(xChecker > monster[x].xPos){
					if(xChecker-monster[x].xPos < monster[x].width){
						collideX = true;
					}
				}
				else{
					if(monster[x].xPos - xChecker < 5){
						collideX = true;
					}
				}
			}

			if(collideX && collideY){
				System.out.println("collision!");
				monster[x].contact = true;
			}
		}
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g.setColor(Color.YELLOW);
		g.drawImage(backgroundImage, 0, 0, this);
		g.drawImage(image, x, y, this);

	for(int c = 0; c < monster.length; c++){
			if(monster[c]!=null){
				g.drawImage(monster[c].image, monster[c].xPos, monster[c].yPos, this);
				g.setColor(Color.GREEN);
				g.fillRect(monsters[c].xPos+7, monster[c].yPos, monster[c].life, 2);
			}
		}
	}	
}
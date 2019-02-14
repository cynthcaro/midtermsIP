import javax.swing.JFrame;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class MyFrame extends JFrame implements KeyListener{

	Draw draw;
	Hero hero;

	public MyFrame(){
		this.draw = new Draw();
	}

	public void keyPressed(KeyEvent e){
		if(e.getKeyCode() == KeyEvent.VK_UP){
			draw.hero.moveUp();
			System.out.println("pos: " + hero.x + ", " + hero.y);
		}
		else if(e.getKeyCode() == KeyEvent.VK_RIGHT){
			draw.hero.moveRight();
			System.out.println("pos: " + hero.x + ", " + hero.y);
		}
		else if(e.getKeyCode() == KeyEvent.VK_DOWN){
			draw.hero.moveDown();
			System.out.println("pos: " + hero.x + ", " + hero.y);
		}
		else if(e.getKeyCode() == KeyEvent.VK_LEFT){
			draw.hero.moveLeft();
			System.out.println("pos: " + hero.x + ", " + hero.y);
		}
		else if(e.getKeyCode() == KeyEvent.VK_SPACE){
			draw.hero.attack();
			System.out.println("attack");
		}
	}

	public void keyReleased(KeyEvent e){

	}

	public void keyTyped(KeyEvent e){
		
	}

	public static void main(String args[]){
		MyFrame gameFrame = new MyFrame();
		gameFrame.setSize(600,600);
		gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gameFrame.setVisible(true);
		gameFrame.getContentPane().add(gameFrame.draw);
		gameFrame.addKeyListener(gameFrame);
		System.out.println("practical programming");
	}
}
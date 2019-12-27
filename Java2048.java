//library & framework import
import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import javax.swing.*;
//implements key event listener
public class Java2048 implements KeyListener,ActionListener {
	//declare constructor
	boolean upMovable = true, downMovable = true, rightMovable = true, leftMovable =true;
	JDialog dialog;
	JFrame frame;
	JLabel label[] = new JLabel[16];
	JPanel panel[] = new JPanel[16];
	Random rand = new Random();
	//main class
	public static void main(String[] args) {
		//Runnable interface implementation. If not have return value, use this.
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				//exception process
				try {
					new Java2048();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	//start main
	public Java2048() {
		System.out.println("Start Game 2048");
		//create frame
		frame = new JFrame("2048");
		Font font = new Font("돋움", Font.PLAIN, 40);
		frame.setSize(565, 580);//set window size
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//set window x-button
		frame.setLocationRelativeTo(null);//set absolute location
		frame.setVisible(true);//check showable
		frame.setLayout(null);//set layout
		frame.setResizable(false);//window size unchangeable
		frame.addKeyListener(this);
		//2048 board container
		Container container = frame.getContentPane();
		container.setBackground(new Color(173, 157, 142));
		//create label & panel
		for(int i = 0; i < 16; i++) {
			label[i] = new JLabel("");
			panel[i] = new JPanel();
			panel[i].setLayout(new GridBagLayout());
			panel[i].setOpaque(true);
			panel[i].setBackground(new Color(255,255,255));
			frame.add(panel[i]);
		}
		//location
		panel[0].setBounds(15 , 15, 120, 120);
		panel[1].setBounds(150, 15, 120, 120);
		panel[2].setBounds(285, 15, 120, 120);
		panel[3].setBounds(420, 15, 120, 120);

		panel[4].setBounds(15 , 150, 120, 120);
		panel[5].setBounds(150, 150, 120, 120);
		panel[6].setBounds(285, 150, 120, 120);
		panel[7].setBounds(420, 150, 120, 120);
		
		panel[8].setBounds(15, 285, 120, 120);
		panel[9].setBounds(150, 285, 120, 120);
		panel[10].setBounds(285, 285, 120, 120);
		panel[11].setBounds(420, 285, 120, 120);
		
		panel[12].setBounds(15, 420, 120, 120);
		panel[13].setBounds(150, 420, 120, 120);
		panel[14].setBounds(285, 420, 120, 120);
		panel[15].setBounds(420, 420, 120, 120);
		
		for(int i=0;i<16;i++) {
			label[i].setFont(font);
			panel[i].add(label[i]);
		}
		//Random location
		int i = rand.nextInt(16);
		int j;
		label[i].setText("2");

		do {
			j= rand.nextInt(16);
		} while(i==j);
		label[j].setText("2");
		colorTiles();
		//game over label
		JButton button;
		dialog = new JDialog(frame,"Game Over",true);
		dialog.setLayout(new GridBagLayout());
		dialog.setSize(300, 200);
		dialog.setLocationRelativeTo(null);
		button = new JButton("ok");
		JLabel gmovr = new JLabel("Game over");
		gmovr.setFont(font);
		dialog.add(gmovr);
		dialog.add(button);
		button.addActionListener(this);
	}
	//key event
	@Override
	public void keyPressed(KeyEvent event) {
		if(event.getKeyCode() == KeyEvent.VK_UP && upMovable == true){
			upMove();
			colorTiles(); 
			next();
		}
		if(event.getKeyCode() == KeyEvent.VK_DOWN && downMovable == true){
			downMove();
			colorTiles(); 
			next();
		}
		if(event.getKeyCode() == KeyEvent.VK_RIGHT && rightMovable == true){
			rightMove();
			colorTiles(); 
			next();
		}
		if(event.getKeyCode() == KeyEvent.VK_LEFT && leftMovable == true){
			leftMove();
			colorTiles(); 
			next();
		}
		if(leftMovable == false && rightMovable == false && upMovable == false && downMovable == false) {
			dialog.setVisible(true);
		}
	}
	//move exception
	private void movable() {
		upMovable = false;
		downMovable = false;
		rightMovable = false;
		leftMovable = false;
		//right move 
		int fir,sec;
		x:{ 
			for(int i = 3; i <= 15; i = i+4) {
				for(int k = 3; k > 0; k--) {
					for(int l = i; l > i - k; l--) {
						if(label[l].getText() == "") {
							if(label[l-1].getText() != "") {
								rightMovable = true;
								break x;
							}
						}
					}
				}
				for(int j = i; j > i - 3; j--) {
					if(label[j].getText() == ""){
						fir = 0;
					} else {
						fir = Integer.parseInt(label[j].getText());
					}
					if(label[j-1].getText() == ""){
						sec = 0;
					} else {
						sec = Integer.parseInt(label[j-1].getText());
					}
					if(fir == sec) {
						if(fir != 0 && sec != 0) {
							rightMovable = true;
							break x;
						}
					}
				}
			}
		}
		//left move
		y:{ 
			for(int i = 0; i <= 12; i = i+4) {
				for(int k = 3; k > 0; k--) {
					for(int l = i; l < i + k; l++) {
						if(label[l].getText() == "") {
							if(label[l+1].getText() != "") {
								leftMovable = true;
								break y;
							}
						}
					}
				}
				for(int j = i; j < i + 3; j++) {
					if(label[j].getText() == ""){
						fir = 0;
					} else {
						fir = Integer.parseInt(label[j].getText());
					}
					if(label[j+1].getText() == ""){
						sec = 0;
					} else {
						sec = Integer.parseInt(label[j+1].getText());
					}
					if(fir == sec) {
						if(fir != 0 && sec != 0){
							leftMovable = true;
							break y;
						}
					}
				}
			}
		}
		//down move
		z:{ 
			for(int i = 12; i <= 15; i++) {
				for(int k = 12; k > 0; k = k-4) {
					for(int l = i; l > i - k; l = l-4) {
						if(label[l].getText() == "") {
							if(label[l-4].getText() != "") {
								downMovable = true;
								break z;
							}
						}
					}
				}
				for(int j = i; j > i - 12; j = j-4) {
					if(label[j].getText() == ""){
						fir = 0;
					} else {
						fir = Integer.parseInt(label[j].getText());
					}
					if(label[j-4].getText() == ""){
						sec = 0;
					} else {
						sec = Integer.parseInt(label[j-4].getText());
					}
					if(fir == sec) {
						if(fir != 0 && sec != 0){
							downMovable = true;
							break z;
						}
					}
				}
			}
		}
		//up move
		u:{ 
			for(int i = 0; i <= 3; i++) {
				for(int k = 12; k > 0; k = k-4) {
					for(int l = i; l < i + k; l = l+4) {
						if(label[l].getText() == "") {
							if(label[l+4].getText() != "") {
								upMovable = true;
								break u;
							}
						}
					}
				}
				for(int j = i; j < i + 12; j = j+4) {
					if(label[j].getText() == ""){
						fir = 0;
					} else {
						fir = Integer.parseInt(label[j].getText());
					}
					if(label[j+4].getText() == ""){
						sec = 0;
					} else {
						sec = Integer.parseInt(label[j+4].getText());
					}
					if(fir == sec) {
						if(fir != 0 && sec != 0){
							upMovable = true;
							break u;
						}
					}
				}
			}
		}
	}
	//turn
	private void next() {
		boolean flag = false;
		int i;

		for( i = 0; i < 16; i++) {
			if(label[i].getText() == "") {
				flag = true;
				break;
			}
		}
		if(flag) {
			do {
				i = rand.nextInt(16);
			} while(label[i].getText() != "");
			label[i].setText("2");
			colorTiles();
		}
		movable();
		if(flag == false && upMovable == false && downMovable == false && rightMovable == false && leftMovable == false) {
			dialog.setVisible(true);
		}
	}
	//new game event
	public void actionPerformed(ActionEvent ae) {
		if (ae.getActionCommand() == "ok") {
			initialize(); 
			dialog.setVisible(false);
		}
	}
	//
	private void initialize() {
		leftMovable =true;
		rightMovable=true;
		upMovable=true;
		downMovable = true;

		for(int i=0;i<16;i++) {
			label[i].setText("");
		}
		colorTiles();
		int i = rand.nextInt(16);
		int j;
		label[i].setText("2");
		do{
			j= rand.nextInt(16);
		} while(i==j);
		label[j].setText("2");
		colorTiles();
	}
	//right move event
	private void rightMove() {
		int fir, sec, fin;
		for(int i = 3; i <= 15; i = i+4) {
			rightify(i);
			for(int j = i; j > i - 3; j--) {
				if(label[j].getText() == "") {
					fir = 0;
				} else {
					fir = Integer.parseInt(label[j].getText());
				}
				if(label[j-1].getText() == ""){
					sec = 0;
				} else {
					sec = Integer.parseInt(label[j-1].getText());
				}
				if(fir == sec) {
					fin = fir+sec;
					if(fin != 0) {
						label[j].setText(""+fin);
					} else {
						label[j].setText("");
					}
					label[j-1].setText("");
				}
			}
			rightify(i);
		}
	}
	//
	private void rightify(int i) {
		for(int k = 3; k > 0; k--) {
			for(int l = i; l > i - k; l--) {
				if(label[l].getText() == "") {
					if(label[l-1].getText() != "") {
						label[l].setText(label[l-1].getText());
						label[l-1].setText("");
					}
				}
			}
		}
	}
	//left move event
	private void leftMove() {
		int fir,sec,fin;
		for(int i = 0; i <= 12; i = i+4) {
			leftify(i);
			for(int j = i; j < i + 3; j++) {
				if(label[j].getText() == ""){
					fir = 0;
				} else {
					fir = Integer.parseInt(label[j].getText());
				}
				if(label[j+1].getText() == "") {
					sec = 0;
				} else {
					sec = Integer.parseInt(label[j+1].getText());
				}
				if(fir == sec) {
					fin = fir + sec;
					if(fin != 0){
						label[j].setText("" + fin);
					} else {
						label[j].setText("");
					}
					label[j+1].setText("");
				}
			}
			leftify(i);
		}
	}
	//
	private void leftify(int i) {
		for(int k = 3; k > 0; k--) {
			for(int l = i; l < i + k; l++) {
				if(label[l].getText() == "") {
					if(label[l+1].getText() != "") {
						label[l].setText(label[l+1].getText());
						label[l+1].setText("");
					}
				}
			}
		}
	}
	//down move event
	private void downMove() {
		int fir,sec,fin;
		for(int i = 12; i <= 15; i++) {
			downify(i);
			for(int j = i; j > i - 12; j = j-4) {
				if(label[j].getText() == ""){
					fir = 0;
				} else {
					fir = Integer.parseInt(label[j].getText());
				}
				if(label[j-4].getText()==""){
					sec = 0;
				} else {
					sec = Integer.parseInt(label[j-4].getText());
				}
				if(fir==sec) {
					fin = fir+sec;
					if(fin!=0){
						label[j].setText("" + fin);
					} else {
						label[j].setText("");
					}
					label[j-4].setText("");
				}
			}
			downify(i);
		}
	}
	//
	private void downify(int i) {
		for(int k = 12; k > 0; k = k-4) {
			for(int l = i; l > i - k; l = l-4) {
				if(label[l].getText() == "") {
					if(label[l-4].getText() != "") {
						label[l].setText(label[l-4].getText());
						label[l-4].setText("");
					}
				}
			}
		}
	}
	//up move event
	private void upMove() {
		int fir, sec, fin;
		for(int i = 0; i <= 3; i++) {
			upify(i);
			for(int j = i; j < i + 12; j = j+4) {
				if(label[j].getText() == "") {
					fir = 0;
				} else {
					fir = Integer.parseInt(label[j].getText());
				}
				if(label[j+4].getText() == "") {
					sec = 0;
				} else {
					sec = Integer.parseInt(label[j+4].getText());
				}
				if(fir == sec) {
					fin = fir + sec;
					if(fin != 0){
						label[j].setText("" + fin);
					} else {
						label[j].setText("");
					}
					label[j+4].setText("");
				}
			}
			upify(i);
		}
	}
	//
	private void upify(int i) {
		for(int k = 12; k > 0; k = k-4) {
			for(int l = i; l < i + k; l = l+4) {
				if(label[l].getText() == "") {
					if(label[l+4].getText() != "") {
						label[l].setText(label[l+4].getText());
						label[l+4].setText("");
					}
				}
			}
		}
	}
	//key event
	@Override
	public void keyReleased(KeyEvent event) {}
	@Override
	public void keyTyped(KeyEvent event) {}

	public void colorTiles() {
		for(int i=0;i<16;i++) {
			Color color = new Color(75,75,75);
			String str = label[i].getText();
			label[i].setForeground(new Color(255, 255, 255));

			if(str.equals("2")) {
				color = new Color(234, 222, 209);
				label[i].setForeground(new Color(75,75,75));
			}
			if(str.equals("4")) {
				color = new Color(233, 218, 187);
				label[i].setForeground(new Color(75,75,75));
			}
			if(str.equals("8")) {
				color = new Color(239 ,162 ,98);
			}
			if(str.equals("16")) {
				color = new Color(243 ,130 ,76);
			}
			if(str.equals("32")) {
				color = new Color(244 ,102 ,72);
			}
			if(str.equals("64")) {
				color = new Color(244, 69, 38);
			}
			if(str.equals("128")) {
				color = new Color(233 ,200 ,88);
			}
			if(str.equals("256")) {
				color = new Color(233 ,196 ,70);
			}
			if(str.equals("512")) {
				color = new Color(233 ,192 ,53);
			}
			if(str.equals("1024")) {
				color = new Color(233 ,188 ,49);
			}
			if(str.equals("2048")) {
				color = new Color(76 ,215 ,125);
			}
			if(str.equals("4096")) {
				color = new Color(128 ,255 ,125);
			}
			if(str.equals("8192")) {
				color = new Color(255 ,204 ,255);
			}
			panel[i].setBackground(color);
		}
	}
}
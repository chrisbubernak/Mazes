import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.*;

public class Main {
	private static final int H = 500;
	private static final int W = 500;
	private static Maze maze;
	private static int size = 6; //size of first maze
		
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				JFrame frame = new JFrame();
                final JPanel panel = new JPanel(){
	                	
                	protected void paintComponent(Graphics g) {
		            	super.paintComponents(g);
						g.clearRect(0, 0,getWidth(), getHeight());
						
	            		Graphics2D g2d = (Graphics2D) g.create();
	            		int width = getWidth();
	            		int height = getHeight();
	            		int n = Main.maze.getN();
	            		int cellWidth = width/n;
	            		int cellHeight = height/n;
	            	  
	            		for (int i = 0; i < n; i++) {
	            			for (int j = 0; j < n; j++) {
	            				int cur = maze.getN()*i+j;
	     			            Cell cell = maze.getCell(cur);
	        					g.fillRect(j * cellWidth, i * cellHeight, cellWidth, cellHeight);
	        					if (maze.getCurrent() == cur) {
	    	                		g2d.setColor(Color.BLUE);
	            	                g2d.fillRect(j * cellWidth, i * cellHeight, cellWidth, cellHeight);
	        					}
	          					if (maze.getGoal() == cur) {
	    	                		g2d.setColor(Color.RED);
	            	                g2d.fillRect(j * cellWidth, i * cellHeight, cellWidth, cellHeight);
	        					}
	        				
		                		g2d.setColor(Color.YELLOW);
	        					
	            				int x1 = j * cellWidth;
	            				int x2 = j * cellWidth + cellWidth;
	            				int y1 = i * cellHeight;
	            				int y2 = i * cellHeight + cellHeight;
	            				if (!cell.isConnected((cur+1))) {
	            					g2d.drawLine(x2, y1, x2 ,y2 ); //right line
	            				}
	            				if (!cell.isConnected((cur-1))) {
	            					g2d.drawLine(x1, y1 ,x1 ,y2 ); //left line
	            				}
	            				if(!cell.isConnected(cur-maze.getN())) {
	            					g2d.drawLine(x1, y1, x2, y1);
	            				}
	            				if(!cell.isConnected(cur+maze.getN())) {
	            					g2d.drawLine(x1, y2, x2, y2);
	            				}
	            			}
	            		}
	            		g2d.dispose();
                	}
            	
	            	public Dimension getPreferredSize() {
	            		return new Dimension(W, H);
	            	}
                };

                frame.getContentPane().add(panel);

                panel.addKeyListener(new KeyAdapter() {

			        @Override
			        public void keyPressed(KeyEvent e) {
			        	myKeyEvt(e, "keyPressed");
			        }

			        private void myKeyEvt(KeyEvent e, String text) {
			        	int key = e.getKeyCode();
			            int cur = maze.getCurrent();
			            Cell cell = maze.getCell(cur);
			            if (key == KeyEvent.VK_KP_LEFT || key == KeyEvent.VK_LEFT)
			            {
			            	if (cell.isConnected(cur-1)) {
			            		maze.setCurrent(cur-1);
			            	}
			            }
			            else if (key == KeyEvent.VK_KP_RIGHT || key == KeyEvent.VK_RIGHT)
			            {
			            	if (cell.isConnected(cur+1)) {
			            		maze.setCurrent(cur+1);
			            	}
			            }
			            else if (key == KeyEvent.VK_KP_UP || key == KeyEvent.VK_UP)
			            {
			            	if (cell.isConnected(cur-maze.getN())) {
			            		maze.setCurrent(cur-maze.getN());
			            	}
			            }
			            else if (key == KeyEvent.VK_KP_DOWN || key == KeyEvent.VK_DOWN)
			            {
			            	if (cell.isConnected(cur+maze.getN())) {
			            		maze.setCurrent(cur+maze.getN());
			            	}
			            }
			            panel.repaint();
			            
			            if (maze.getCurrent() == maze.getGoal()) {
							Main.drawMaze(size++);
			            }
			        }
                });
                panel.setFocusable(true);
                panel.requestFocusInWindow();

                frame.setSize(new Dimension(500, 500));
                frame.setVisible(true);
				drawMaze(size);
	        }
        });
	}
	
	private static void drawMaze(int n) {
		Main.maze = new Maze(n);
	}
}

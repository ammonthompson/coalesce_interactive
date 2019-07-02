/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package coalesce;

/**
 *
 * @author Ammon
 */
import javax.swing.*;
import java.awt.*;
//import java.awt.Graphics2D;
import java.awt.event.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
public class CoalescePanel extends JPanel{
	int width = 1400;
	int height = 750;
	GenePool tree = new GenePool();
	Timer timer = new Timer(400, new NextDrawer());
	JButton pauseButton = new JButton("PAUSE");
	JButton resetColorButton = new JButton("RESET");
	JButton treeButton = new JButton("TREE");
	boolean pause = true;
	boolean treeMaker = false;
	int xPosition;
	int yPosition;

	public CoalescePanel(){
		setPreferredSize(new Dimension(width, height));
		pauseButton.addActionListener(new PauseButtonListener());
		add( pauseButton);
		resetColorButton.addActionListener(new ColorButtonListener());
		add(resetColorButton);
		treeButton.addActionListener(new TreeButtonListener());
		add(treeButton);
		addMouseListener(new RedClickListener());
		timer.start();
	}

	public void paintComponent(Graphics g){
		super.paintComponent(g);

// draw lines connecting each circle

		for(int genCount = 0; genCount < tree.getMaxGeneration() - 1; genCount++){
			for(int ovalCount = 0; ovalCount < tree.getPopulationSize(); ovalCount++){
				if(tree.getGenePool()[genCount][ovalCount] != null){
					int parentPosition;
					if(treeMaker == false){
						parentPosition = tree.getGenePool()[genCount][ovalCount].getParentsIndex();
						Graphics2D g2 = (Graphics2D)g;
						if(tree.getGenePool()[genCount][ovalCount].isSelected()){
							g.setColor(Color.red);
							g2.setStroke(new BasicStroke(2));
							g2.drawLine(width - genCount*35 - 60 + 5, ovalCount*24 + 40 + 5, width - (genCount + 1)*35 - 60 + 5, parentPosition*24 + 40 + 5);

						}
						else{
							g.setColor(Color.black);
							g2.setStroke(new BasicStroke(1));
							g2.drawLine(width - genCount*35 - 60 + 5, ovalCount*24 + 40 + 5, width - (genCount + 1)*35 - 60 + 5, parentPosition*24 + 40 + 5);

						}
					}
					else{
						parentPosition = tree.getGenePool()[genCount][ovalCount].getParentsIndex();
						g.setColor(Color.red);
						if(tree.getGenePool()[genCount][ovalCount].isSelected()){
							g.drawLine(width - genCount*35 - 60 + 5, ovalCount*24 + 40 + 5, width - (genCount + 1)*35 - 60 + 5, parentPosition*24 + 40 + 5);
						}
					}
				}
			}
		}

// draw each individual circle

		for(int generationPosition = 0; generationPosition < tree.getMaxGeneration(); generationPosition++){
			for(int popPosition = 0; popPosition < tree.getPopulationSize(); popPosition++){
				if(tree.getGenePool()[generationPosition][popPosition] != null){
					if(treeMaker == false){
						g.setColor(tree.getGenePool()[generationPosition][popPosition].getColor());
						g.fillOval(width - generationPosition*35 - 60, popPosition*24 + 40, 10, 10);
					}
					else{
						if(tree.getGenePool()[generationPosition][popPosition].isSelected()){
							g.setColor(tree.getGenePool()[generationPosition][popPosition].getColor());
							g.fillOval(width - generationPosition*35 - 60, popPosition*24 + 40, 10, 10);
						}
					}
				}
			}
		}


	}
	private class NextDrawer implements ActionListener{
        public void actionPerformed(ActionEvent event){
            if(pause){
                    tree.setNextGeneration();
                    repaint();
            }
        }
    }
	private class PauseButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent event){
			pause = !pause;
		}
	}
	private class TreeButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent event){
			treeMaker = !treeMaker;
			repaint();
		}
	}
	private class ColorButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent event){
			for(int i = 0; i < tree.getMaxGeneration(); i++){
				for(int j = 0; j < tree.getPopulationSize(); j++){
					if(tree.getGenePool()[i][j] != null)
						tree.getGenePool()[i][j].changeSelectedTo(false);
				}
			}
			repaint();
		}
	}

	private class RedClickListener implements MouseListener {
            public void mousePressed(MouseEvent A){}
            public void mouseClicked(MouseEvent A){
                            Point point = A.getPoint();
                            xPosition = (int)point.getX();
                            yPosition = (int)point.getY();
                            int generationPoint = (width - (xPosition + 45))/35;
                            int ovalPoint = ((yPosition)-35)/24;
                            tree.getGenePool()[generationPoint][ovalPoint].select();
                            tree.traceAncestors(generationPoint, ovalPoint);
                            tree.traceDescendants(generationPoint, ovalPoint);
                            repaint();
                    }
            public void mouseReleased(MouseEvent A){}
            public void mouseDragged(MouseEvent A){}
            public void mouseMoved (MouseEvent A){}
            public void mouseEntered(MouseEvent A){}
            public void mouseExited(MouseEvent A){}
    }

}

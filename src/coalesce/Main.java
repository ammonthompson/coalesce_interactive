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
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel panel = new CoalescePanel();
		frame.getContentPane().add(panel);
		frame.pack();
		frame.setVisible(true);

    }

}

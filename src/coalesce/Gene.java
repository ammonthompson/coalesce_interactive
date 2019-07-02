/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package coalesce;

/**
 *
 * @author Ammon
 */
import java.awt.*;
import java.util.*;
public class Gene {
	Color individualColor;
	int genePoolIndexNumber;
	int actualIndex;
	boolean colorBoolean = true;
	boolean selected = false;

	int red = 150;
	int green = 150;
	int blue = 150;

	public Gene (int index){
		actualIndex = index;
		individualColor = new Color(red, green, blue);
	}
	public Gene(Color inheritanceColor, boolean inheritanceSelection){

		selected = inheritanceSelection;

		red = inheritanceColor.getRed();
		green = inheritanceColor.getGreen();
		blue = inheritanceColor.getBlue();

		if(new Random().nextInt(100) < 50){
			int x = new Random().nextInt(39);
			if(red + 19 - x < 0)
				red = 0;
			if(red + 19 - x > 255)
				red = 240;
			if(red + 19 - x < 255 && red + 19 - x > 0)
				red = red + 19 - x;
		}
		if(new Random().nextInt(100) < 50){
			int x = new Random().nextInt(39);
			if(green + 19 - x < 0)
				green = 0;
			if(green + 19 - x > 255)
				green = 240;
			if(green + 19 - x < 255 && green + 19 - x > 0)
				green = green + 19 - x;
		}
		if(new Random().nextInt(100) < 50){
			int x = new Random().nextInt(39);
			if(blue + 19 - x < 0)
				blue = 0;
			if(blue + 19 - x > 255)
				blue = 240;
			if(blue + 19 - x < 255 && blue + 19 - x > 0)
				blue = blue + 19 - x;
		}

		individualColor = new Color(red, green, blue);
	}
	public int getParentsIndex(){
		return genePoolIndexNumber;
	}
	public void setParentsIndex(int parentIndex){
		genePoolIndexNumber = parentIndex;
	}
	public int getActualIndex(){
		return actualIndex;
	}
	public void setActualIndex(int index){
		actualIndex = index;
	}
	public Color getColor(){
		return individualColor;
	}
	public void select(){
		selected = true;
	}
	public boolean isSelected(){
		return selected;
	}
	public void changeSelectedTo(boolean b){
		selected = b;
	}
}

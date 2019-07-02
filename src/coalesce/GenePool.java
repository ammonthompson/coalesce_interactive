/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package coalesce;

/**
 *
 * @author Ammon
 */
import java.util.*;
import java.awt.*;
public class GenePool {
	private int populationSize = 28;
	private int maxGeneration = 40;
	private int counter;
	private Gene[][] pool = new Gene[maxGeneration][populationSize];
	public GenePool(){
		for(int geneCount1 = 0; geneCount1 < populationSize; geneCount1++){
			pool[0][geneCount1] = new Gene(geneCount1);
		}
		for(int generationCount = 1; generationCount < maxGeneration; generationCount++){
			for(int innerCount = 0; innerCount < populationSize; innerCount++){
				pool[generationCount][innerCount] = null;
			}
		}
	}
	public int getPopulationSize(){
		return populationSize;
	}
	public int getMaxGeneration(){
		return maxGeneration;
	}
	public Gene[][] getGenePool(){
		return pool;
	}
	public void traceAncestors(int i, int j){
		boolean selectedDot = true;
		counter = 0;
		if(i <= maxGeneration){
			if(pool[i+1][counter] != null && i+1 < maxGeneration -1){
				while(selectedDot && counter < populationSize){
					if(pool[i+1][counter].getActualIndex() == pool[i][j].getParentsIndex()){
						pool[i+1][counter].select();
						selectedDot = false;
						this.traceAncestors(i+1, counter);
					}
					counter++;
				}
			}
		}
	}
	public void traceDescendants(int i, int j){
		ArrayList<Gene> tempArray1 = new ArrayList<Gene>();
		ArrayList<Gene> tempArray2 = new ArrayList<Gene>();		
		tempArray1.add(pool[i][j]);
		for(int h = i - 1; h >= 0; h--){
			for(int v = 0; v < populationSize; v++){
				for(int arrayListCount = 0; arrayListCount < tempArray1.size(); arrayListCount++){				
					if(pool[h][v].getParentsIndex() == tempArray1.get(arrayListCount).getActualIndex()){
						pool[h][v].select();
						tempArray2.add(pool[h][v]);
					}
				}
			}
			tempArray1.clear();
			for(int a = 0; a < tempArray2.size(); a++){
				tempArray1.add(tempArray2.get(a));
			}
			tempArray2.clear();
		}
	}
	public void setNextGeneration(){
		for(int geneCount2 = maxGeneration - 1; geneCount2 > 0; geneCount2--){
			for(int geneCount3 = 0; geneCount3 < populationSize; geneCount3++ ){
				pool[geneCount2][geneCount3] = pool[geneCount2 - 1][geneCount3];
			}
		}

//Create temporary ArrayList of Genes sampled at random
//from the previous generation and set their index numbers

		ArrayList<Gene> tempList = new ArrayList<Gene>();
		for(int geneCount4 = 0; geneCount4 < populationSize; geneCount4++){
			int replicateIndex = new Random().nextInt(populationSize);
			tempList.add(new Gene(pool[1][replicateIndex].getColor(), pool[1][replicateIndex].isSelected()));
			tempList.get(geneCount4).setParentsIndex(pool[1][replicateIndex].getActualIndex());
		}

//Sort through the temporrary arrayList and arrange their
//order in the main matrix first column according to index number.

		int tempIndex = 0;
		for(int geneCount5 = 0; geneCount5 < populationSize; geneCount5++){
			tempIndex = 0;
			for(int compare = 0; compare < tempList.size(); compare++){
				if(tempList.get(tempIndex).getParentsIndex() >= tempList.get(compare).getParentsIndex()){
					tempIndex = compare;
				}
			}
			pool[0][geneCount5] = tempList.get(tempIndex);
			pool[0][geneCount5].setActualIndex(geneCount5);
			tempList.remove(tempIndex);
		}

	}

}

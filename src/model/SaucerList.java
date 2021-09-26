package model;

import java.util.ArrayList;
import java.util.List;

public class SaucerList {
private List<Saucer> saucers;
	
	public SaucerList() {
		saucers = new ArrayList<Saucer>();
	}
	
	public void addSaucer(Saucer saucer) {
		saucers.add(saucer);
	}
	
	public List<Saucer> getSaucers(){
		return saucers;
	}
}

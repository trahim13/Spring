package impls.sony;


import interfaces.Head;

public class SonyHead implements Head {
	
	public void calc(){
		System.out.println("Thinking about Sony...");
	}

	private String color;

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}
}

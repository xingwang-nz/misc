package nz.co.ecngroup.droolstest.helper;

public class Car {
	private Color color;

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Car [color=");
		builder.append(color);
		builder.append("]");
		return builder.toString();
	}
	
}

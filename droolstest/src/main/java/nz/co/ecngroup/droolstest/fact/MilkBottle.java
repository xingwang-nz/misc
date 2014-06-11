package nz.co.ecngroup.droolstest.fact;

public class MilkBottle {
	private boolean hasMilk;

	public boolean isHasMilk() {
		return hasMilk;
	}

	public void setHasMilk(boolean hasMilk) {
		this.hasMilk = hasMilk;
	}
	
	private int count;
	public void increment() {
		count++;
	}
	
	public int getCount() {
		return count;
	}
	
}

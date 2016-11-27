package events;

public abstract class Events {
	
	private long d;
	
	public Events( long d) {
		this.d = d;
	}
	
	public long getDate() {
		return this.d;
	}
		
	public abstract void execute();

}

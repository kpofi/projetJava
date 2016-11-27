package events;

public class EvenementMessage extends Events {
	
	private String message;
	
	public EvenementMessage (long date, String s) {
		super(date);
		this.message = s;
	}
	
	public void execute() {
		System.out.println(this.getDate() + this.message);
	}
	

}

package metier.forms;

import javax.swing.JFrame;

public class FormException extends Exception{
	
	private String consigne;
	
	public FormException() {
		super("erreur de formulaire");
	}
	
	public FormException(String st) {
		super(st);
	}
	
	public FormException(String st,String consigne) {
		super(st);
		this.consigne=consigne;
	}
}



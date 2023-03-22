package metier.Form;

import java.util.Scanner;

public class FormException extends Exception{
	
	private String consigne;
	
	
	public FormException() { 
		super("Erreur dans le formulaire !!!");	
	}

	
	public FormException(String msg) { super(msg);}

	public FormException(String msg,String consigne) { 
		super(msg);
		this.consigne=consigne;
	}


	public String getConsigne() {
		return consigne;
	}
	
}

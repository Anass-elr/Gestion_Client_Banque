package presentation.modele;

import java.time.LocalDate;
import java.time.LocalTime;

public class Log {
    private LocalDate date;
    private LocalTime time;
    private TypeLog type;
    private String message;
    

    public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public LocalTime getTime() {
		return time;
	}

	public void setTime(LocalTime time) {
		this.time = time;
	}

	public TypeLog getType() {
		return type;
	}

	public void setType(TypeLog type) {
		this.type = type;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Log(LocalDate date, String msg){
        this.date = date;
        this.message = msg;
    }

    public Log(LocalDate date, LocalTime time, TypeLog type, String msg){
        this.date = date;
        this.message = msg;
        this.time = time;
        this.type = type;
    }

    @Override
    public String toString() {
        String logStr = "[" + date + "]["+time+"][{"+type+"}] : " +  message;

        return logStr;
    }
}

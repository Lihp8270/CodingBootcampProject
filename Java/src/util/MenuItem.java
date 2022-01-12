package util;

/**
 * Used by the Menu class to store runnable commands.
 * @author JS
 *
 */
public class MenuItem {
	
	String description;
	Runnable command;
	
	public MenuItem() {
		
	}
	
	public MenuItem(String description,Runnable command ) {
		this.description = description;
		this.command =command;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Runnable getCommand() {
		return command;
	}

	public void setCommand(Runnable command) {
		this.command = command;
	}
	
	public void run() {
		command.run();
	}

}

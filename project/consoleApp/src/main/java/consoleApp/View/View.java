package consoleApp.View;

import java.util.Scanner;
import org.apache.log4j.Logger;

public abstract class View 
{
	protected static Scanner scanner = new Scanner(System.in);
	protected Logger log;
	
	public View()
	{
		log = Logger.getLogger(this.getClass());
	}
	
	public abstract View navigate();
}
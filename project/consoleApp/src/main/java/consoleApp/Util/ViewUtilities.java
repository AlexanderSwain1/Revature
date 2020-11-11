package consoleApp.Util;

import java.util.Scanner;

import org.apache.log4j.Logger;

import consoleApp.Program;

public class ViewUtilities 
{
	private static Logger log = Logger.getLogger(ViewUtilities.class);
	public static void showInvalidInputMessage()
	{
		log.info("You response is invalid. Please try again.\n");
	}
	public static void showInvalidUserCredentialMessage()
	{
		log.info("The credentials you entered is incorrect. Please try again.\n");
	}
	public static int getIntResponse(Scanner s)
	{
		int response = -1;
		while (response == -1)
		{
			log.info("How may I help you?: ");
			try
			{
				response = Integer.parseInt(s.next().trim());
			}
			catch (NumberFormatException e)
			{
				ViewUtilities.showInvalidInputMessage();
				log.error(e.getMessage());
			}
		}
		return response;
	}
	public static double getDoubleResponse(Scanner s, String message)
	{
		double response = -1;
		while (response <= 0)
		{
			log.info(message);
			try
			{
				response = Double.parseDouble(s.next().trim());
			}
			catch (NumberFormatException e)
			{
				ViewUtilities.showInvalidInputMessage();
				log.error(e.getMessage());
			}
			if (response <= 0)
				log.info("Value must me greater than 0\n");
		}
		return response;
	}
}

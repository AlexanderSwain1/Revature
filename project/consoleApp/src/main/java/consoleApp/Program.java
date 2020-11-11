package consoleApp;

import java.util.Scanner;

import org.apache.log4j.Logger;

import consoleApp.View.View;
import consoleApp.Views.MainView;

public class Program
{
	public static void main(String[] args) 
	{
		View currentView = new MainView();
		
		while (currentView != null)
			currentView = currentView.navigate();
	}
}

package consoleApp;

import java.util.Scanner;

import consoleApp.Views.MainView;
import consoleApp.Views.View;

public class Program
{
	public static Scanner consoleScanner = new Scanner(System.in);//fix
	
	public static void main(String[] args) 
	{
		View currentView = new MainView();
		
		while (currentView != null)
			currentView = currentView.navigate();
	}
}

package main;

import java.util.Scanner;

import game_tv.TestGameTV;
import net.bytebuddy.agent.builder.AgentBuilder.CircularityLock.Default;
import weather_reporting.TestWeatherReporting;

public class TestAssessment {

	public static void main(String[] args) throws Exception {
		
		System.out.println("Test Assessment : Select one of the options");
		while(true) {
		System.out.println("1. Test GameTV ");
		System.out.println("2. Test Weather Reporting");
		System.out.println("3. Exit");
		
		Scanner s = new Scanner(System.in);
		String choice = s.nextLine();
		switch (choice) {
			case "1":
				TestGameTV testGameTv = new TestGameTV(); 
				testGameTv.run();
				break;
			case "2":
				TestWeatherReporting testWeatherReporting = new TestWeatherReporting();
				testWeatherReporting.run();
				break;
			case "3" :
				System.out.println("Good Bye!");
				System.exit(0);
			default:
				System.out.println("Please enter valid input");
				continue;
			}
		}
	}
}

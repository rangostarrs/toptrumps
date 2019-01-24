package commandline;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class TopTrumpsController {

	private TopTrumpsModel ContrModel;
	private TopTrumpsView ContrView;

	TopTrumpsController(TopTrumpsModel model, TopTrumpsView view) {
		ContrModel = model;
		ContrView = view;
	}

	public static void startGame() throws FileNotFoundException {

		System.out.print("--------------------\n" + "---  Top Trumps  ---\n" + "--------------------\n");

		System.out.print("Do you want to see past results or play a game?\n\n" + "   1: Print Game Statistics\n"
				+ "   2: Play game\n\n");

		System.out.print("Enter the number for your selection:\n");

		Scanner keyboard = new Scanner(System.in);

		int choice = keyboard.nextInt();

		if (choice == 1) {

			System.out.println("Loading game statistics");
			// call the game statistics method
		}

		if (choice == 2) {

			System.out.println("Loading deck");
			TopTrumpsModel.addToArrayList();
			// call the file reader method

		}

	}

}
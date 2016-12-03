package com.mondimedia.memory;

import java.util.Scanner;

import com.mondimedia.memory.common.BusinessException;

/**
 * Main Start of the application
 * @author <a href="mailto:emad.omara85@gmail.com">Emad Omara</a>
 *
 */
public class DataBase {

	private static final String QUIT = "quit";

	public static void main(String[] args) {

		while (true) {

			// String input = System.console().readLine();
			Scanner scanner = new Scanner(System.in);

			String input = scanner.nextLine();
			if (QUIT.equals(input)) {
				System.out.println("Exit!");
				System.exit(0);
			}

			try {
				RequestHandler.instance().processRequest(input);
			} catch (BusinessException e) {
				System.out.println("Error : " + e.getMessage());
			}
		}

	}

}

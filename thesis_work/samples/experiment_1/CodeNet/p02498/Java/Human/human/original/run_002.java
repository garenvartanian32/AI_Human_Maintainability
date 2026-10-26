import java.io.*;

class Main{
	public static void main(String args[]) {

		String word;
		BufferedReader input = new BufferedReader(new InputStreamReader(
				System.in));
		try {

			word = input.readLine();

			for (int i = 0; i < word.length(); i++) {
				char c = word.charAt(i);
				if (Character.isUpperCase(c)) {
					System.out.print(Character.toLowerCase(c));
				} else if (Character.isLowerCase(c)) {
					System.out.print(Character.toUpperCase(c));
				} else {
					System.out.print(c);
				}
			}
			System.out.print("\n");

		} catch (IOException e) {

		}
		return;
	}

}
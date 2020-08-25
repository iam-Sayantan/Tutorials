import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexDemo {

	public static void main(String[] args) {
		try(Scanner sc = new Scanner(System.in))
		{
			while(true) {
				System.out.println("Enter Regex Pattern: ");
				String regexPattern = sc.nextLine();
				Pattern pattern = Pattern.compile(regexPattern);
				Matcher matcher = pattern.matcher("Hi! I am Sayantan Sengupta");
				boolean found = false;
				while(matcher.find())
				{
					System.out.println("found the text: "
										+ matcher.group()+ " starting at index "
										+ matcher.start()
										+ " and ending at index "
										+ matcher.end());
				}
				if (!found) {
					System.out.println("Match not found");
				}
			}
		}
	}

}

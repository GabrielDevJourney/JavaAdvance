package Streams;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static java.util.Arrays.stream;

public class Main {
	public static void main(String[] args) {

		String prayer = "Oh Lord, won't you buy me a trash Mercedes Benz\n" +
				"My friends all drive trash Porsches, I must make trash amends\n" +
				"Worked hard all my trash lifetime, no help from my trash friends\n" +
				"So Lord, won't you buy me a trash Mercedes Benz";
		Stream<String> newPrayer = stream(prayer.split("\\W+"))
				.map(String::toUpperCase)
				.filter(word -> !word.equals("TRASH"))
				.reduce("", (word1, word2) -> word1 + " " + word2)
				.lines();
		newPrayer.forEach(System.out ::println);
	}
}

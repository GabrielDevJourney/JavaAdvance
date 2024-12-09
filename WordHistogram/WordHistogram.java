import java.util.*;

public class WordHistogram implements Iterable<String> {

	HashMap<String, Integer> wordHistogram = new HashMap<String, Integer>();
	String[] arrayWords;

	@Override
	public Iterator<String> iterator() {
		return null;
	}

	public void analizeString(String text) {
		arrayWords = text.toLowerCase().split(" ");
		for (String word : arrayWords) {
			if (!wordHistogram.containsKey(word)) {
				wordHistogram.put(word, 1);
			} else {
				wordHistogram.put(word, wordHistogram.get(word) + 1);
			}
		}
		ArrayList<String> words = new ArrayList<String>(wordHistogram.keySet());
		Collections.sort(words);
		for (String word : words) {
			System.out.println(word + "\t" + wordHistogram.get(word));
		}
	}




	public void get(String wordToCheck) {
		ArrayList<String> words = new ArrayList<String>(wordHistogram.keySet());
		Collections.sort(words);
		for (String word : words) {
			if (wordToCheck.equals(word)) {
				System.out.println(word + "\t" + wordHistogram.get(word));
			}
		}
	}
}

import java.util.*;

public class WordHistogram implements Iterable<String> {

	HashMap<String, Integer> wordHistogram = new HashMap<>();

	public class FrequencyComparator implements Comparator<String> {
		@Override
		public int compare(String word1, String word2) {
			//keep comparing each word with next based on frequency count Integer value, meaning the highest
			// occurance word is being "carried" to next comparison until everything is sorted from high to low
			return wordHistogram.get(word2).compareTo(wordHistogram.get(word1));
		}
	}


	@Override
	public Iterator<String> iterator() {
		ArrayList<String> words = new ArrayList<>(wordHistogram.keySet());
		// Sorts by comparing each word's frequency count (Integer value) from the HashMap
		// not by comparing the words (String keys) themselves
		//can either use list.sort or Collections.sort
		words.sort(new FrequencyComparator());
		return words.iterator();
	}


	public void analizeString(String text) {
		//pass every word to array but in lowercase
		String[] arrayWords = text.toLowerCase().split(" ");

		for (String word : arrayWords) {//will get the closest array possible

			//wordHistogram.put(word, wordHistogram.containsKey(word) ? wordHistogram.get(word) + 1 : 1);

			if (!wordHistogram.containsKey(word)) {
				wordHistogram.put(word, 1);
			} else {
				wordHistogram.put(word, wordHistogram.get(word) + 1);
			}
		}
		printHistogram();
	}


	public void get(String wordToCheck) {

		if (wordHistogram.containsKey(wordToCheck.toLowerCase())) {
			int count = wordHistogram.get(wordToCheck);
			System.out.printf("%-15s", wordToCheck);
			System.out.print(" ");
			for (int i = 0; i < count; i++) {
				System.out.print("*");
			}
			System.out.println(" (" + count + ")");
		}else{
			System.out.println("Word '" + wordToCheck + "' was not found in the text");

		}

	}

	private void printHistogram() {
		for (String word : this) {  // Using existing iterator that sorts by frequency
			int count = wordHistogram.get(word);
			System.out.printf("%-15s", word);  // Word with 15 spaces padding
			System.out.print(" ");  // Space after word
			for (int i = 0; i < count; i++) {
				System.out.print("*");  // Print * for each occurrence
			}
			System.out.println(" (" + count + ")");  // Show occurances at the end
		}
	}

}

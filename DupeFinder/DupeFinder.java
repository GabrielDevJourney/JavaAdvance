import Fruits.Fruit;

import java.util.*;


public class DupeFinder<E extends Comparable<E>> { //extend to be able to compare things within not the object itself

	private Collection<E> fruits;

	public DupeFinder(Collection<E> fruits) {
		this.fruits = fruits;
	}


	public int checkDupes() {
		//this method returns the number of duplicate elements
		Set<E> toCheck = new HashSet<>(fruits);
		return fruits.size() - toCheck.size();
	}

	public List<E> getDupes() {
		Set<E> uniqueSet = new HashSet<>(fruits);
		List<E> dupeFruits = new ArrayList<>();

		for (E fruit : uniqueSet) {
			if (Collections.frequency(fruits, fruit) > 1) {
				dupeFruits.add(fruit);
			}
		}
		return dupeFruits;

	}

	public List<E> sortedDupes() {
		List<E> list = getDupes();
		//this will use sort based on compare to method override
		Collections.sort(list);
		Collections.reverse(list);
		return list;
	}
}

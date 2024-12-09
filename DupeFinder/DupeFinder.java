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
		List<E> duplicateFruits = findDuplicates();
		for (E fruit : duplicateFruits) {
			System.out.println(fruit);
		}
		return duplicateFruits;
	}

	private List<E> findDuplicates() {
		Set<E> uniqueSet = new HashSet<>(fruits);
		//will store only repeated elements
		List<E> duplicateFruits = new ArrayList<>();

		for (E fruit : uniqueSet) {
			//check for that unique fruit in bigger collection and if is repeated then pass only repeated to
			//duplicateFrutis list
			if (Collections.frequency(fruits, fruit) > 1) {
				duplicateFruits.add(fruit);
			}
		}
		return duplicateFruits;
	}

	public List<E> sortedDupes() {
		List<E> list = findDuplicates();
		//this will use sort based on compare to method override
		Collections.sort(list);

		System.out.println("\nSorted duplicates:");
		for (E fruit : list) {
			System.out.println(fruit);
		}

		return list;
	}
}

import Fruits.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class Main {
	public static void main(String[] args) {

		//using array list and not hashset otherwise duplicates will not be allowed
		Collection<Fruit> fruits = new ArrayList<>();

		DupeFinder finder = new DupeFinder(fruits);

		Fruit pear1 = new Pear(10);
		Fruit pear2 = new Pear(10);
		Fruit pear3 = new Pear(3);
		Fruit blueberry1 = new Blueberry(8);
		Fruit blueberry2 = new Blueberry(8);
		Fruit blueberry3 = new Blueberry(9);
		Fruit blueberry4 = new Blueberry(9);
		Fruit banana = new Banana(2);
		Fruit banana2 = new Banana(2);

		fruits.add(pear1);
		fruits.add(pear2);
		fruits.add(pear3);
		fruits.add(blueberry1);
		fruits.add(blueberry2);
		fruits.add(blueberry3);
		fruits.add(blueberry4);
		fruits.add(banana);
		fruits.add(banana2);



		System.out.println(finder.checkDupes());
		System.out.println();
		finder.getDupes();
		System.out.println();
		finder.sortedDupes();

	}
}

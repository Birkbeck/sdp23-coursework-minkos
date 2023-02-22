package sml;

import java.sql.SQLOutput;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import java.util.List;
import java.util.ArrayList;

// TODO: write a JavaDoc for the class
/**
 * Class for Labels .....KIV....elaborate
 */

/**
 *
 * @author ...
 */
public final class Labels {
	private final Map<String, Integer> labels = new HashMap<>();

	private ArrayList<String> checkDuplicates = new ArrayList<>();

	private int counter = 0;

	/**
	 * Adds a label with the associated address to the map.
	 *
	 * @param label the label
	 * @param address the address the label refers to
	 */
	public void addLabel(String label, int address) {
		Objects.requireNonNull(label);
		// TODO: Add a check that there are no label duplicates.

		checkDuplicates.add(label);
		counter += 1;
		if (checkDuplicates.size() > 1) {
			String current = checkDuplicates.get(counter-1);
			for (int i = 0; i < checkDuplicates.size()-1; i++) {
				if (checkDuplicates.get(i).equals(current)) {
					System.out.println("Duplicated labels found. Please correct txt file.");
					return;
				}
			}
		}

		labels.put(label, address);
	}

	/**
	 * Returns the address associated with the label.
	 *
	 * @param label the label
	 * @return the address the label refers to
	 */
	public int getAddress(String label) throws NullPointerException {
		// TODO: Where can NullPointerException be thrown here?
		//       (Write an explanation.)
		// Ans: A specification of the throws clause that outputs a NullPointerException when a reference is used that
		//  	....points to no location in memory, which is null, like it was referencing an object.
		//       Add code to deal with non-existent labels.
		return labels.get(label);
	}

	/**
	 * representation of this instance,
	 * in the form "[label -> address, label -> address, ..., label -> address]"
	 *
	 * @return the string representation of the labels map
	 */
	@Override
	public String toString() {
		// TODO: Implement the method using the Stream API (see also class Registers).
		return "";
	}

	// TODO: Implement equals and hashCode (needed in class Machine).
	public boolean equals(Object o) {

		return true;
	}


	public int hashCode() {
		return 1;
	}

	/**
	 * Removes the labels
	 */
	public void reset() {
		labels.clear();
	}
}

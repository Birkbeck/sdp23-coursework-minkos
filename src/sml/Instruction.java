package sml;

// TODO: write a JavaDoc for the class
/**
 * A abstract class  .....KIV....elaborate
 */

import java.util.Objects;

/**
 * Represents an abstract instruction.
 *
 * @author ...
 */
public abstract class Instruction {
	protected final String label;
	protected final String opcode;

	/**
	 * Constructor: an instruction with a label and an opcode
	 * (opcode must be an operation of the language)
	 *
	 * @param label optional label (can be null)
	 * @param opcode operation name
	 */
	public Instruction(String label, String opcode) {
		this.label = label;
		this.opcode = opcode;
	}

	public String getLabel() {
		return label;
	}

	public String getOpcode() {
		return opcode;
	}

	public static int NORMAL_PROGRAM_COUNTER_UPDATE = -1;

	/**
	 * Executes the instruction in the given machine.
	 *
	 * @param machine the machine the instruction runs on
	 * @return the new program counter (for jump instructions)
	 *          or NORMAL_PROGRAM_COUNTER_UPDATE to indicate that
	 *          the instruction with the next address is to be executed
	 */

	public abstract int execute(Machine machine);

	protected String getLabelString() {
		return (getLabel() == null) ? "" : getLabel() + ": ";
	}

	// TODO: What does abstract in the declaration below mean?
	//       Abstract used here means that the actual implementation of the function, toString(), has yet to be determined.
	//	 Its actual functionality could be overriden by functions in other classes via inheritance, thereafter, the actual functionality is implemented.
	// 	 Abstract over here gives a rough outline in terms of naming convention of those functionalities that will be implemented in other classes.
	// 	 In this case, we know that other functions in other classes that are implementing this abstract function will return a String. 
	@Override
	public abstract String toString();

	// TODO: Make sure that subclasses also implement equals and hashCode (needed in class Machine).

	public boolean equals(Object o) {

		return true;
	}


	public int hashCode() {
		return 1;
	}

}

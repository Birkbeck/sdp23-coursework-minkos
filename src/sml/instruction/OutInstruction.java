package sml.instruction;

import sml.Instruction;
import sml.Machine;
import sml.RegisterName;


public class OutInstruction extends Instruction {

    public static final String OP_CODE = "Out";
    private RegisterName register;

    /**
     * Constructor: an instruction with a label and an opcode
     * (opcode must be an operation of the language)
     *
     * @param label  optional label (can be null)
     * @param opcode operation name
     */
    public OutInstruction(String label, RegisterName register) {
        super(label, OP_CODE);
        this.register = register;
    }

    @Override
    public int execute(Machine machine) {
        int value1 = machine.getRegisters().get(register);

        System.out.println("Output: " + value1);

        return NORMAL_PROGRAM_COUNTER_UPDATE;
    }

    @Override
    public String toString() {
        return getLabelString() + getOpcode() + " " + register;
    }
}

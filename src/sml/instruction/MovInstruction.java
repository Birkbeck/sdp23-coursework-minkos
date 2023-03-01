package sml.instruction;

import sml.Instruction;
import sml.Machine;
import sml.RegisterName;

public class MovInstruction extends Instruction {

    private final RegisterName register;
    private final int value;

    public static final String OP_CODE = "mov";
    /**
     * Constructor: an instruction with a label and an opcode
     * (opcode must be an operation of the language)
     *
     * @param opcode  operation name
     * @param label   optional label (can be null)
     * @param result
     */
    public MovInstruction(String label, RegisterName register, int value) {
        super(label, OP_CODE);
        this.register = register;
        this.value = value;
    }

    @Override
    public int execute(Machine machine) {
        machine.getRegisters().set(register, value);
        return NORMAL_PROGRAM_COUNTER_UPDATE;
    }

    @Override
    public String toString() {
        return getLabelString() + getOpcode() + " " + register + " " + value;
    }
}

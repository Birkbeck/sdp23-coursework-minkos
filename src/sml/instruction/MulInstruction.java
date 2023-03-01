package sml.instruction;

import sml.Instruction;
import sml.Machine;
import sml.RegisterName;

public class MulInstruction extends Instruction {

    public static final String OP_CODE = "Mul";

    private final RegisterName result;

    private final RegisterName source;
    /**
     * Constructor: an instruction with a label and an opcode
     * (opcode must be an operation of the language)
     *
     * @param label  optional label (can be null)
     * @param opcode operation name
     */
    public MulInstruction(String label, RegisterName result, RegisterName source) {
        super(label, OP_CODE);
        this.result = result;
        this.source = source;
    }

    @Override
    public int execute(Machine machine) {

        int value1 = machine.getRegisters().get(result);
        int value2 = machine.getRegisters().get(source);
        machine.getRegisters().set(result, value1*value2);
        return NORMAL_PROGRAM_COUNTER_UPDATE;

    }

    @Override
    public String toString() {
        return getLabelString() + getOpcode() + " " + result + " " + source;
    }
}

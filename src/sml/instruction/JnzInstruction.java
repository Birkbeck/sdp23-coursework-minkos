package sml.instruction;

import sml.Instruction;
import sml.Machine;
import sml.RegisterName;



public class JnzInstruction extends Instruction {

    public static final String OP_CODE = "jnz";

    private RegisterName register;

    private String ops;

    private RegisterName register1;

    private RegisterName register2;

    /**
     * Constructor: an instruction with a label and an opcode
     * (opcode must be an operation of the language)
     *
     * @param label  optional label (can be null)
     * @param opcode operation name
     */
    public JnzInstruction(String label, RegisterName register, String ops, RegisterName register1, RegisterName register2) {
        super(label, OP_CODE);
        this.register = register;
        this.ops = ops;
        this.register1 = register1;
        this.register2 = register2;
    }

    @Override
    public int execute(Machine machine) {
        int value1 = machine.getRegisters().get(register);

        int value2 = machine.getRegisters().get(register1);
        int value3 = machine.getRegisters().get(register2);

        if (!String.valueOf(value1).equals(0)) {
            if ("mul".equals(ops)) {
                machine.getRegisters().set(register1, value2*value3);
                return NORMAL_PROGRAM_COUNTER_UPDATE;
            }
        }
        return NORMAL_PROGRAM_COUNTER_UPDATE;
    }

    @Override
    public String toString() {
        return null;
    }
}

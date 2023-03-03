package sml.instruction;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sml.Instruction;
import sml.Machine;
import sml.Registers;

import static sml.Registers.Register.EAX;
import static sml.Registers.Register.EBX;
import static sml.Registers.Register.ECX;

public class JnzInstructionTest {

    private Machine machine;
    private Registers registers;

    @BeforeEach
    void setUp() {
        machine = new Machine(new Registers());
        registers = machine.getRegisters();
        //...
    }

    @AfterEach
    void tearDown() {
        machine = null;
        registers = null;
    }

    @Test
    void executeValidOpsMov() {

        // Dealing with register, EAX. Set to branching off.
        int branchOff = 1;
        registers.set(EAX, branchOff); // Set to 1 for branching; If 0, then no branching

        // Set variables to pass in as parameters when instantiating JnzInstruction class for testing
        String ops = "mov";
        int movOpsValue = 7;
        String branchingLabel = "f3";

        // Only EBX register is used here. Not other registers.
        // Instantiate JnzInstruction class for testing.
        Instruction instruction = new JnzInstruction(null, EAX, ops, EBX, ECX, movOpsValue, branchingLabel);
        instruction.execute(machine);

        // Ensures branching occurs
        Assertions.assertEquals(branchOff, machine.getRegisters().get(EAX));
        // Checks move operation occurs correctly.
        Assertions.assertEquals(movOpsValue, machine.getRegisters().get(EBX));
    }

    @Test
    void executeValidOpsMul() {

        // Dealing with register, EAX. Set to branching off.
        int branchOff = 1;
        registers.set(EAX, branchOff); // Set to 1 for branching; If 0, then no branching

        // Set variables to pass in as parameters when instantiating JnzInstruction class for testing
        String ops = "mul";
        int movOpsValue = 3; // Dummy entry
        int value1 = 5;
        int value2 = 6;
        registers.set(EBX, value1);
        registers.set(ECX, value2);
        String branchingLabel = "f3";

        // Variable to check value of register.
        int res = value1*value2;

        // Only EBX register is used here. Not other registers.
        // Instantiate JnzInstruction class for testing.
        Instruction instruction = new JnzInstruction(null, EAX, ops, EBX, ECX, movOpsValue, branchingLabel);
        instruction.execute(machine);
        // Ensures branching occurs
        Assertions.assertEquals(branchOff, machine.getRegisters().get(EAX));
        // Checks multiply operation occurs correctly.
        Assertions.assertEquals(res, machine.getRegisters().get(EBX));
    }

    @Test
    void executeValidOpsAdd() {

        // Dealing with register, EAX. Set to branching off.
        int branchOff = 1;
        registers.set(EAX, branchOff); // Set to 1 for branching; If 0, then no branching

        // Set variables to pass in as parameters when instantiating JnzInstruction class for testing
        String ops = "add";
        int movOpsValue = 3; // Dummy entry
        int value1 = 20;
        int value2 = 8;
        registers.set(EBX, value1);
        registers.set(ECX, value2);
        String branchingLabel = "f3";

        // Variable to check value of register.
        int res = value1+value2;

        // Only EBX register is used here. Not other registers.
        // Instantiate JnzInstruction class for testing.
        Instruction instruction = new JnzInstruction(null, EAX, ops, EBX, ECX, movOpsValue, branchingLabel);
        instruction.execute(machine);
        // Ensures branching occurs
        Assertions.assertEquals(branchOff, machine.getRegisters().get(EAX));
        // Checks addition operation occurs correctly.
        Assertions.assertEquals(res, machine.getRegisters().get(EBX));
    }

    @Test
    void executeValidOpsSub() {

        // Dealing with register, EAX. Set to branching off.
        int branchOff = 1;
        registers.set(EAX, branchOff); // Set to 1 for branching; If 0, then no branching

        // Set variables to pass in as parameters when instantiating JnzInstruction class for testing
        String ops = "sub";
        int movOpsValue = 3; // Dummy entry
        int value1 = 50;
        int value2 = 20;
        registers.set(EBX, value1);
        registers.set(ECX, value2);
        String branchingLabel = "f3";

        // Variable to check value of register.
        int res = value1-value2;

        // Only EBX register is used here. Not other registers.
        // Instantiate JnzInstruction class for testing.
        Instruction instruction = new JnzInstruction(null, EAX, ops, EBX, ECX, movOpsValue, branchingLabel);
        instruction.execute(machine);
        // Ensures branching occurs
        Assertions.assertEquals(branchOff, machine.getRegisters().get(EAX));
        // Checks subtraction operation occurs correctly.
        Assertions.assertEquals(res, machine.getRegisters().get(EBX));
    }


}

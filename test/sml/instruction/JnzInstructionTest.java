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

        int branchOff = 1;
        registers.set(EAX, branchOff); // Set to 1 for branching; If 0, then no branching

        String ops = "mov";
        int movOpsValue = 7;

        Instruction instruction = new JnzInstruction(null, EAX, ops, EBX, ECX, movOpsValue); // Only EBX is used here
        instruction.execute(machine);
        Assertions.assertEquals(branchOff, machine.getRegisters().get(EAX));
        Assertions.assertEquals(movOpsValue, machine.getRegisters().get(EBX));
    }

    @Test
    void executeValidOpsMul() {

        int branchOff = 1;
        registers.set(EAX, branchOff); // Set to 1 for branching; If 0, then no branching

        String ops = "mul";
        int movOpsValue = 3; // Dummy entry
        int value1 = 5;
        int value2 = 6;
        registers.set(EBX, value1);
        registers.set(ECX, value2);
        int res = value1*value2;

        Instruction instruction = new JnzInstruction(null, EAX, ops, EBX, ECX, movOpsValue);
        instruction.execute(machine);
        Assertions.assertEquals(branchOff, machine.getRegisters().get(EAX));
        Assertions.assertEquals(res, machine.getRegisters().get(EBX));
    }

    @Test
    void executeValidOpsAdd() {

        int branchOff = 1;
        registers.set(EAX, branchOff); // Set to 1 for branching; If 0, then no branching

        String ops = "add";
        int movOpsValue = 3; // Dummy entry
        int value1 = 20;
        int value2 = 8;
        registers.set(EBX, value1);
        registers.set(ECX, value2);
        int res = value1+value2;

        Instruction instruction = new JnzInstruction(null, EAX, ops, EBX, ECX, movOpsValue);
        instruction.execute(machine);
        Assertions.assertEquals(branchOff, machine.getRegisters().get(EAX));
        Assertions.assertEquals(res, machine.getRegisters().get(EBX));
    }

    @Test
    void executeValidOpsSub() {

        int branchOff = 1;
        registers.set(EAX, branchOff); // Set to 1 for branching; If 0, then no branching

        String ops = "sub";
        int movOpsValue = 3; // Dummy entry
        int value1 = 50;
        int value2 = 20;
        registers.set(EBX, value1);
        registers.set(ECX, value2);
        int res = value1-value2;

        Instruction instruction = new JnzInstruction(null, EAX, ops, EBX, ECX, movOpsValue);
        instruction.execute(machine);
        Assertions.assertEquals(branchOff, machine.getRegisters().get(EAX));
        Assertions.assertEquals(res, machine.getRegisters().get(EBX));
    }


}

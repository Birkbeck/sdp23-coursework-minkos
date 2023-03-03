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
public class MulInstructionTest {

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
    void executeValid() {

        // Set values for registers
        registers.set(EAX, 5);
        registers.set(EBX, 6);

        // Instantiate MulInstruction class for testing.
        Instruction instruction = new MulInstruction(null, EAX, EBX);
        instruction.execute(machine);

        // Checks multiply operation occurs correctly.
        Assertions.assertEquals(30, machine.getRegisters().get(EAX));
    }
}

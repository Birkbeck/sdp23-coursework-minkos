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
public class SubInstructionTest {

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
        registers.set(EAX, 10);
        registers.set(EBX, 7);

        // Instantiate SubInstruction class for testing.
        Instruction instruction = new SubInstruction(null, EAX, EBX);
        instruction.execute(machine);

        // Checks subtraction operation occurs correctly.
        Assertions.assertEquals(3, machine.getRegisters().get(EAX));
    }
}

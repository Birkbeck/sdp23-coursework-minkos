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

public class MovInstructionTest {

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

        // Set variables to pass in as parameters when instantiating MovInstruction class for testing
        int argument = 10;

        // Instantiate MovInstruction class for testing.
        Instruction instruction = new MovInstruction(null, EAX, argument);
        instruction.execute(machine);

        //Checks move operation is executed correctly.
        Assertions.assertEquals(argument, machine.getRegisters().get(EAX));
    }
}

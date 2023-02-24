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
        int argument = 10;
        Instruction instruction = new MovInstruction(null, EAX, argument);
        instruction.execute(machine);
        Assertions.assertEquals(argument, machine.getRegisters().get(EAX));
    }

    @Test
    void executeValidTwo() {
        int argument2 = 15;
        Instruction instruction = new MovInstruction(null, EAX, argument2);
        instruction.execute(machine);
        Assertions.assertEquals(15, machine.getRegisters().get(EAX));
    }
}

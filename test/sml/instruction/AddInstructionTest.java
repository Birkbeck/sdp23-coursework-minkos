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

class AddInstructionTest {
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

    // Instantiate AddInstruction class for testing.
    Instruction instruction = new AddInstruction(null, EAX, EBX);
    instruction.execute(machine);

    // Checks addition operation occurs correctly.
    Assertions.assertEquals(11, machine.getRegisters().get(EAX));
  }

  @Test
  void executeValidTwo() {

    // Set values for registers
    registers.set(EAX, -5);
    registers.set(EBX, 6);

    // Instantiate AddInstruction class for testing.
    Instruction instruction = new AddInstruction(null, EAX, EBX);
    instruction.execute(machine);

    // Checks addition operation occurs correctly.
    Assertions.assertEquals(1, machine.getRegisters().get(EAX));
  }
}
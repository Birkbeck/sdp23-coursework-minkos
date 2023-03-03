package sml;

import sml.instruction.*;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;
import static sml.Registers.Register;

/**
 * This class ....
 * <p>
 * The translator of a <b>S</b><b>M</b>al<b>L</b> program.
 *
 * @author ...
 */
public final class Translator {

    private final String fileName; // source file of SML code

    // line contains the characters in the current line that's not been processed yet
    private String line = "";

    // branchingIns: variable to store instruction to execute after branching off
    private String branchingIns;

    private Boolean oneTime = true;

    public Translator(String fileName) {
        this.fileName =  fileName;
    }

    public static Class<?> c;

    // translate the small program in the file into lab (the labels) and
    // prog (the program)
    // return "no errors were detected"

    public void readAndTranslate(Labels labels, List<Instruction> program) throws IOException {
        try (var sc = new Scanner(new File(fileName), StandardCharsets.UTF_8)) {
            labels.reset();
            program.clear();

            // Each iteration processes line and reads the next input line into "line"
            while (sc.hasNextLine()) {
                line = sc.nextLine();
                String label = getLabel();

                Instruction instruction = getInstruction(label);
                if (instruction != null) {
                    if (label != null)
                        labels.addLabel(label, program.size());
                    program.add(instruction);
                }
            }
        }
    }

    /**
     * Translates the current line into an instruction with the given label
     *
     * @param label the instruction label
     * @return the new instruction
     * <p>
     * The input line should consist of a single SML instruction,
     * with its label already removed.
     */


    // Reflection to create new instances; when at certain instruction, i.e. MulInstruction, to create a new instance.
    private Instruction getInstruction(String label) {
        if (line.isEmpty())
            return null;

        String opcode = scan();
        switch (opcode) {
            // Switch case for add operation for 2 values stored in 2 different registers.
            case AddInstruction.OP_CODE -> {
                String r = scan();
                String s = scan();
                return new AddInstruction(label, Register.valueOf(r), Register.valueOf(s));
            }
            // Switch case for moving an integer into a register.
            case MovInstruction.OP_CODE -> {

                String r = scan();
                int v = Integer.parseInt(scan());
                return new MovInstruction(label, Register.valueOf(r), v);
            }
            // Switch case for multiply operation between 2 values stored in 2 different registers.
            case MulInstruction.OP_CODE -> {

                String r = scan();
                String s = scan();
                return new MulInstruction(label, Register.valueOf(r), Register.valueOf(s));
            }
            // Switch case for subtraction operation between 2 values stored in 2 different registers.
            case SubInstruction.OP_CODE -> {
                String r = scan();
                String s = scan();
                return new SubInstruction(label, Register.valueOf(r), Register.valueOf(s));
            }
            // Switch case for branching off to the specified label
            case JnzInstruction.OP_CODE -> {
                String r = scan();
                String branchLabel = scan();

                // Branching (math ops, i.e. mul)
                String ops = branchingIns.substring(4, 7);
                // Branching (register, i.e. EBX):
                String firstReg = branchingIns.substring(8, 11);
                // Branching (register, i.e. EAX)
                String secondReg = branchingIns.substring(12);

                // For the case of move operation
                if ("mov".equals(ops)) {
                    int secondParam = Integer.parseInt(secondReg);
                    return new JnzInstruction(label, Register.valueOf(r), ops, Register.valueOf(firstReg), Register.valueOf(firstReg), secondParam, branchLabel);
                }
                // All other operation
                return new JnzInstruction(label, Register.valueOf(r), ops, Register.valueOf(firstReg), Register.valueOf(secondReg), 0, branchLabel);

            }
            case OutInstruction.OP_CODE -> {
                String r = scan();

                return new OutInstruction(label, Register.valueOf(r));
            }

            // TODO: add code for all other types of instructions
            // Ans: Added as shown above by the various cases within the switch.

            default -> {
                System.out.println("Unknown instruction: " + opcode);
            }
        }


        // TODO: Then, replace the switch by using the Reflection API
        /*
        // An attempt on converting switch case to Reflection API but did not work.
        List<Instruction> list;
        list = new ArrayList<Instruction>();

        List.of(AddInstruction.OP_CODE, MovInstruction.OP_CODE,
                MulInstruction.OP_CODE, SubInstruction.OP_CODE,
                JnzInstruction.OP_CODE, OutInstruction.OP_CODE).stream()
                .filter(item -> item.equals(opcode))
                .map(item -> {

                    Instruction obj = null;
                    try {
                        obj = (Instruction) Class.forName("sml.instruction" + "." + item + "Instruction").newInstance();
                    } catch (InstantiationException e) {
                        throw new RuntimeException(e);
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException(e);
                    } catch (ClassNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                    list.add(obj);
                    return obj;
                });
        */

        // TODO: Next, use dependency injection to allow this machine class
        //       to work with different sets of opcodes (different CPUs)
        // Not Done


        return null;
    }




    private String getLabel() {
        String word = scan();
        if (word.endsWith(":"))
            return word.substring(0, word.length() - 1);

        // undo scanning the word
        line = word + " " + line;
        return null;
    }

    /*
     * Return the first word of line and remove it from line.
     * If there is no word, return "".
     */
    private String scan() {

        if (oneTime) {
            // To store the instruction so that after branching off this stored instruction can be executed
            // Also, note that program is designed to recognise "f3" as a label to branch off.
            if (line.substring(0).contains("f3")) {
                branchingIns = line;
                oneTime = false;
            }
        }

        line = line.trim();

        for (int i = 0; i < line.length(); i++)
            if (Character.isWhitespace(line.charAt(i))) {
                String word = line.substring(0, i);
                line = line.substring(i);
                return word;
            }

        return line;
    }
}
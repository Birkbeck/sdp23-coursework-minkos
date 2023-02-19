package sml;

import sml.instruction.*;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Scanner;

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

    private String branchingIns;

    private Boolean oneTime = true;

    public Translator(String fileName) {
        this.fileName =  fileName;
    }

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
    private Instruction getInstruction(String label) {
        if (line.isEmpty())
            return null;

        String opcode = scan();
        switch (opcode) {
            case AddInstruction.OP_CODE -> {
                String r = scan();
                String s = scan();
                return new AddInstruction(label, Register.valueOf(r), Register.valueOf(s));
            }
            case MovInstruction.OP_CODE -> {
                System.out.println("Move op!");

                String r = scan();
                int v = Integer.parseInt(scan());
                // System.out.println("r: " + r); // EAX
                // System.out.println("v: " + v); // 6
                return new MovInstruction(label, Register.valueOf(r), v);
            }
            case MulInstruction.OP_CODE -> {
                System.out.println("Multiply op!");

                String r = scan();
                String s = scan();

                System.out.println("r mul: " + r);
                System.out.println("s mul: " + s);
                return new MulInstruction(label, Register.valueOf(r), Register.valueOf(s));
            }
            case SubInstruction.OP_CODE -> {
                String r = scan();
                String s = scan();
                return new SubInstruction(label, Register.valueOf(r), Register.valueOf(s));
            }
            case JnzInstruction.OP_CODE -> {
                String r = scan();

                String ops = branchingIns.substring(4, 7);
                String firstReg = branchingIns.substring(8, 11);
                String secondReg = branchingIns.substring(12, 15);

                System.out.println("Branching (all): " + branchingIns);
                System.out.println("Branching (Should be mul): " + branchingIns.substring(4, 7));
                System.out.println("Branching (Should be EBX): " + branchingIns.substring(8, 11));
                System.out.println("Branching (Should be EAX): " + branchingIns.substring(12, 15));
                // KIV; should pass in i.e. sub EAX ECX, not f3 (b)...error below.................
                return new JnzInstruction(label, Register.valueOf(r), ops, Register.valueOf(firstReg), Register.valueOf(secondReg));

            }
            case OutInstruction.OP_CODE -> {
                String r = scan();

                return new OutInstruction(label, Register.valueOf(r));
            }

            // TODO: add code for all other types of instructions

            // TODO: Then, replace the switch by using the Reflection API

            // TODO: Next, use dependency injection to allow this machine class
            //       to work with different sets of opcodes (different CPUs)

            default -> {
                System.out.println("Unknown instruction: " + opcode);
            }
        }
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
            if (line.substring(0).contains("f3")) {
                branchingIns = line;
                System.out.println("Persisted: " + branchingIns);
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
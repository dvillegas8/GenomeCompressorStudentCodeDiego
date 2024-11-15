/******************************************************************************
 *  Compilation:  javac GenomeCompressor.java
 *  Execution:    java GenomeCompressor - < input.txt   (compress)
 *  Execution:    java GenomeCompressor + < input.txt   (expand)
 *  Dependencies: BinaryIn.java BinaryOut.java
 *  Data files:   genomeTest.txt
 *                virus.txt
 *
 *  Compress or expand a genomic sequence using a 2-bit code.
 ******************************************************************************/

/**
 *  The {@code GenomeCompressor} class provides static methods for compressing
 *  and expanding a genomic sequence using a 2-bit code.
 *
 *  @author Robert Sedgewick
 *  @author Kevin Wayne
 *  @author Zach Blick
 */
public class GenomeCompressor {

    /**
     * Reads a sequence of 8-bit extended ASCII characters over the alphabet
     * { A, C, T, G } from standard input; compresses and writes the results to standard output.
     */
    public static void compress() {
        // Map where index is the letters and value is the code assigned to the letter
        int[] codes = new int['T' + 1];
        // Code for letter A
        codes['A'] = 0b00;
        // Code for letter C
        codes['C'] = 0b01;
        // Code for letter G
        codes['G'] = 0b10;
        // Code for letter T
        codes['T'] = 0b11;
        String s = BinaryStdIn.readString();
        int n = s.length();
        // Metadata which tells us how many codes are going to be written
        BinaryStdOut.write(n);
        for(int i = 0; i < n;i++){
            BinaryStdOut.write(codes[s.charAt(i)], 2);
        }
        BinaryStdOut.close();
    }

    /**
     * Reads a binary sequence from standard input; expands and writes the results to standard output.
     */
    public static void expand() {
        // Map where index is the code and the value is the letter/char
        char[] letters = new char[4];
        letters[0] = 'A';
        letters[1] = 'C';
        letters[2] = 'G';
        letters[3] = 'T';
        // Get the number of codes in the file
        int size = BinaryStdIn.readInt();
        // Int to store the 2 bit codes
        int letter = 0;
        for(int i = 0; i < size; i++){
            letter = BinaryStdIn.readInt(2);
            BinaryStdOut.write(letters[letter]);
        }
        BinaryStdOut.close();
    }


    /**
     * Main, when invoked at the command line, calls {@code compress()} if the command-line
     * argument is "-" an {@code expand()} if it is "+".
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {

        if      (args[0].equals("-")) compress();
        else if (args[0].equals("+")) expand();
        else throw new IllegalArgumentException("Illegal command line argument");
    }
}
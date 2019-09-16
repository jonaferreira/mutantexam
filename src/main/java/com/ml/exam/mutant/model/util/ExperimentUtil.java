package com.ml.exam.mutant.model.util;

import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This class has the logic to study the human dna and determinate if it is mutant or not. 
 * 
 * @author Jonathan Ivan Ferreira
 */
public class ExperimentUtil {
	
	private static final Logger logger = LoggerFactory.getLogger(ExperimentUtil.class);
	
	private final static String MUTANT_SECUENCES = "AAAA|CCCC|TTTT|GGGG";
	private final static int LONG_MUTANT_GENE = 3;
	
	/**
	 * This method check if a human dna is mutant. True if it is mutant.
	 * @param dna
	 * @return boolean
	 */
	public static boolean isMutant(String[] dna) {
		
		logger.info("This method is validating if a human dna is mutant or not.");
		
		Pattern pattern = Pattern.compile(MUTANT_SECUENCES);
		
		int horizontals = countSequencesOfMutantDnaHorizontally(dna, pattern);
		int verticals = countSequencesOfMutantDnaVertically(dna, pattern);
		int diagonalsFromRigthToLeft = countSequencesOfMutantDnaDiagonallyFromRigthToLeft(dna, pattern);	
		int diagonalsFromLeftToRigth = countSequencesOfMutantDnaDiagonallyFromLeftToRigth(dna, pattern);
		
		return (horizontals + verticals + diagonalsFromRigthToLeft + diagonalsFromLeftToRigth) > 1;
	}
	
	/**
	 * It counts the amount sequences of mutant dna diagonally from right to left.
	 * @param dna
	 * @param pattern
	 * @return int
	 */
	private static int countSequencesOfMutantDnaDiagonallyFromRigthToLeft(String[] dna, Pattern pattern) {
		logger.info("It is counting the sequences of diagonally from rigth to left.");
		
		int diagonals = 0;
		for (int i = 0; i < dna.length - LONG_MUTANT_GENE; i++) {
			for (int j = 0; j < dna.length - LONG_MUTANT_GENE; j++) {
				StringBuilder diagonalBuilder = new StringBuilder();
				diagonalBuilder.append(dna[i].charAt(j));
				diagonalBuilder.append(dna[i + 1].charAt(j + 1));
				diagonalBuilder.append(dna[i + 2].charAt(j + 2));
				diagonalBuilder.append(dna[i + 3].charAt(j + 3));

				diagonals += sequenceIsMatched(pattern, diagonalBuilder.toString());
			}
		}
		return diagonals;
	}

	/**
	 * It counts the amount sequences of mutant dna diagonally from left to right.
	 * @param dna
	 * @param pattern
	 * @return int
	 */
	private static int countSequencesOfMutantDnaDiagonallyFromLeftToRigth(String[] dna, Pattern pattern) {
		logger.info("It is counting the sequences of diagonally from left to rigth.");
		
		int diagonals = 0;
		for (int i = 0; i < dna.length - LONG_MUTANT_GENE; i++) {
			for (int j = dna.length; j > LONG_MUTANT_GENE; j--) {
				StringBuilder diagonalBuilder = new StringBuilder();
				diagonalBuilder.append(dna[i].charAt(j-1));
				diagonalBuilder.append(dna[i + 1].charAt(j - 2));
				diagonalBuilder.append(dna[i + 2].charAt(j - 3));
				diagonalBuilder.append(dna[i + 3].charAt(j - 4));

				diagonals += sequenceIsMatched(pattern, diagonalBuilder.toString());
			}
		}
		return diagonals;
	}
	
	/**
	 * It counts the amount sequences of mutant dna vertically.
	 * @param dna
	 * @param pattern
	 * @return int 
	 */
	private static int countSequencesOfMutantDnaVertically(String[] dna, Pattern pattern) {
		logger.info("It is counting the sequences of mutant dna vertically.");
		
		int verticals = 0;
		for (int i = 0; i < dna.length; i++) {
			StringBuilder verticalBuiler = new StringBuilder();
			for (int j = 0; j < dna.length; j++) {
				verticalBuiler.append(dna[j].charAt(i));
			}

			verticals += sequenceIsMatched(pattern, verticalBuiler.toString());
		}
		return verticals;
	}
	
	/**
	 * It counts the amount sequences of mutant dna horizontally.
	 * @param dna
	 * @param pattern
	 * @return int
	 */
	private static int countSequencesOfMutantDnaHorizontally(String[] dna, Pattern pattern) {
		logger.info("It is counting the sequences of mutant dna horizontally.");
		
		int horizontals = 0;
		for (String genome : dna) {
			horizontals += sequenceIsMatched(pattern, genome);
		}
		return horizontals;
	}

	/**
	 * It checks if sequence is matched with a gen mutant. Return 1 if It is matched. O isn't matched.
	 * @param pattern
	 * @param genome
	 * @return int
	 */
	private static int sequenceIsMatched(Pattern pattern, String genome) {
		return pattern.matcher(genome).find() ? 1 : 0;
	}
	
	/**
	 * It validates if the dna mutant has the correct formant. True if it is correct.
	 * @param dna
	 * @return boolean
	 */
	public static boolean validateMoleculesHumanDna(String[] dna) {
		logger.info("It is validating if a human dna has only molecules valid.");
		
		if(dna!=null && dna.length>0){
			for (String item : dna) {
				if (Pattern.compile("[^AaCcGgTt]").matcher(item).find()){
					return false;
				}
			}
			return true;
		}else{
			return false;
		}
	}

	/**
	 * It validates if the dna mutant has the equals number of molecules with the long each genome. True if it is correct.
	 * @param dna
	 * @return boolean
	 */
	public static boolean validateHumanDnaIsSquare(String[] dna) {
		logger.info("It is validating if a human dna is square.");
		
		int numberMolecules = dna.length;
		
		for (String genome : dna) {
			if(numberMolecules!=genome.length()){
				return false;
			}
		}
		
		return true;
	}

}

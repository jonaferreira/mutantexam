package com.ml.exam.mutant.model.util;

import java.util.Arrays;

import org.mockito.ArgumentMatcher;

import com.ml.exam.mutant.controller.dto.DnaDTO;

public class DnaDtoMatcher implements ArgumentMatcher<DnaDTO> {

    private final DnaDTO expected;
    
    public DnaDtoMatcher(DnaDTO expected) {
        this.expected = expected;
    }
    
//	@Override
//	public boolean matches(Object obj) {
//		if (!(obj instanceof DnaDTO)) {
//            return false;
//        }
//		DnaDTO actual = (DnaDTO) obj;
//
//        return Arrays.deepEquals(expected.getDna(),actual.getDna());
//    }

	@Override
	public boolean matches(DnaDTO argument) {
		DnaDTO actual = (DnaDTO) argument;

        return Arrays.deepEquals(expected.getDna(),actual.getDna());
	}
	
}

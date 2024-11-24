package expressionTrees;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Expression Tree test methods
 * You should add additional test cases
 * @author gosnat
 * @version Fall 2020
 */

class TestExpressionTrees {
	private ExpressionTree et;
	
	@BeforeEach
	void setup() {
		et = new ExpressionTree("( ( 2 + ( 4 * 6 ) ) - ( 8 / 2 ) ) ");
	}

	@Test
	void testEvaluation() {
		assertEquals(22, et.evaluateTree());
	}
	
	@Test
	void testInFix() {
		assertEquals("( ( 2 + ( 4 * 6 ) ) - ( 8 / 2 ) ) ", et.toStringInFix());
	}
	
	@Test
	void testPostFix() {
		assertEquals("2 4 6 * + 8 2 / - ", et.toStringPostFix());
	}

}
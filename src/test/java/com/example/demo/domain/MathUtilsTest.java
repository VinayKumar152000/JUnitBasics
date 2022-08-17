package com.example.demo.domain;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestReporter;

//this makes the instance of mathutiltest created only once,permethod is default that for each method
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@DisplayName("Running MathUtils")
public class MathUtilsTest {

	// @Test annoatation used to declare a method as test method -> informs junit
	// engine what methods to run
	// defautl state of junit test method is success- junit also validates test
	// cases for us

	MathUtils mathUtils;

	// method with before all and after all need to be static,than only we can call
	// it,as we don't have instance(in case of perclass firslty instance is created
	// of class,than we can have it as non static method
	@BeforeAll
	static void beforeAllInit() {
		System.out.println("runs even before instance of mathutilstest is created for each method");
	}

	@AfterAll
	static void afterAllInit() {
		System.out.println("runs even after instance of mathutilstest is terminated for each method");
	}

	// before each method this method runs
	// junit will do dependency injection for testinfo,testreporter,both of them are
	// interfaces,there internal implemantion
	// is provided by junit

	TestInfo testinfo;// gives information about a test
	TestReporter testreporter;

	@BeforeEach
	void init(TestInfo testinfo, TestReporter testreporter) {
		mathUtils = new MathUtils();
		this.testinfo = testinfo;
		this.testreporter = testreporter;// it lets write to junit output or junit report
	}

	@AfterEach
	void after() {
		System.out.println("Runs after each test method get executed");
	}

	// nested test class- to group similar kind of test as single unit,like testing
	// add of positive and negative numbers
	// if any of child test fails for addTest it also fails
	@Nested
	@DisplayName("Sum Method")
	class AddTest {

		@Test
		@DisplayName("Testing Sum of Two Postive Numbers") // name of method to be showed on console
		void testPositive() {
//			fail("Not yet implemented");
//			System.out.println("TestCase");
			// there are various assert method we can use it based on our need
//			MathUtils mathUtils = new MathUtils();
			int expectedResult = 2;
			int acutalResult = mathUtils.add(1, 1);
			System.out.println(testinfo.getDisplayName() + " ");
			testreporter.publishEntry(testinfo.getDisplayName());

			assertEquals(expectedResult, acutalResult);
		}

		@Test
		@DisplayName("Testing Sum of Two Negative Numbers") // name of method to be showed on console
		void testNegative() {
//			fail("Not yet implemented");
//			System.out.println("TestCase");
			// there are various assert method we can use it based on our need
//			MathUtils mathUtils = new MathUtils();
			int expectedResult = -2;
			int acutalResult = mathUtils.add(-1, -1);
//passing simple string as third arg shows message eithertest fails or passes,but passing lambda will make it to shows only
			// when test fails (we provided assert a supplier which needs to be run when
			// test fails)
			assertEquals(expectedResult, acutalResult, () -> "Expected is" + expectedResult + "but got" + acutalResult);
		}
	}

	/***
	 * @RepeatedTest(3) makes us to repeat test any number of times we mention,we
	 * can perform task based on repetionnumber //@Tag we can give tag to similar
	 * type methods ,based on the tag we can perform certain taks on them or run
	 * them //like we can give tag as math or circle to circle area method
	 * 
	 */
	@Test
	@DisplayName("Multiply two nums")
	void testMultiply() {
		// if we want to test multiple asseritons at once we can use assertall
		assertAll(() -> assertEquals(2, mathUtils.multiply(2, 1)), () -> assertEquals(6, mathUtils.multiply(2, 3)),
				() -> assertEquals(-2, mathUtils.multiply(2, -1)));

	}
	// test driven dev- based on our test cases we write a our development
	// code-.firstly test than dev code

	/****
	 * for each test method a new instance of the class MathUtilstest will be
	 * created by junit. so it is not recommended to make test methods dependent on
	 * each other,because dependency change by one test method will not reflect in
	 * other test methods must be independent of each other
	 ***/

	@Test
	@Disabled // this test case will not run
	void computeArea() {
//		MathUtils mathUtils = new MathUtils();
		assertEquals(314.1592653589793, mathUtils.computeArea(10));
	}

	@Test

	void divideTest() {
		boolean isServerUp = true;
//		MathUtils mathUtils = new MathUtils();
		// used to check exception thrown by method is correct or not
//		assertThrows(NullPointerException.class, () -> mathUtils.divide(1, 0));
		assumeTrue(isServerUp);// if this assumption is true than only run our test case
		assertThrows(ArithmeticException.class, () -> mathUtils.divide(1, 0));
	}

	// other methods for conditoinal execution
	// EnabledonOs(Os.Linux),EnabledonJRe(jre.java11) etc.

}

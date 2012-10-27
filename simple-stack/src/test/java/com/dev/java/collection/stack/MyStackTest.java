/**
 * 
 */
package com.dev.java.collection.stack;

import org.junit.Test;

import junit.framework.Assert;

/**
 * @author tmnuwan12
 * Simple Junit Test for MyStack
 */

public class MyStackTest {

	@Test
	public void addItemsToStack()
	{
		int stackSize = 12;
		MyStack<String> myStack = new MyStack<String>();
		myStack.push("start");
		myStack.push("test1");
		myStack.push("test2");
		myStack.push("test3");
		myStack.push("test4");
		myStack.push("test5");
		myStack.push("test6");
		myStack.push("test7");
		myStack.push("test8");
		myStack.push("test9");
		myStack.push("test10");
		myStack.push("end");
		
		Assert.assertEquals(stackSize, myStack.getSize());
	}
	
	@Test
	public void retriveItemsFromStack()
	{
		int emptyStackSize = 0;
		MyStack<String> myStack = new MyStack<String>();
		myStack.push("start");
		myStack.push("test1");
		myStack.push("test2");
		myStack.push("test3");
		myStack.push("test4");
		myStack.push("test5");
		myStack.push("test6");
		myStack.push("test7");
		myStack.push("test8");
		myStack.push("test9");
		myStack.push("test10");
		myStack.push("end");
		
		Assert.assertEquals("end", myStack.pop());
		Assert.assertEquals("test10", myStack.pop());
		Assert.assertEquals("test9", myStack.pop());
		Assert.assertEquals("test8", myStack.pop());
		Assert.assertEquals("test7", myStack.pop());
		Assert.assertEquals("test6", myStack.pop());
		Assert.assertEquals("test5", myStack.pop());
		Assert.assertEquals("test4", myStack.pop());
		Assert.assertEquals("test3", myStack.pop());
		Assert.assertEquals("test2", myStack.pop());
		Assert.assertEquals("test1", myStack.pop());
		Assert.assertEquals("start", myStack.pop());
		
		Assert.assertEquals(emptyStackSize , myStack.getSize());
	}
	
	@Test(expected = NullPointerException.class)
	public void retriveItemsFromEmptyStack()
	{
		MyStack<String> myStack = new MyStack<String>();
		myStack.push("test1");
		myStack.push("test2");
		myStack.push("test3");
		Assert.assertEquals("test3", myStack.pop());
		Assert.assertEquals("test2", myStack.pop());
		Assert.assertEquals("test1", myStack.pop());
		myStack.pop();
		
	}
	
}

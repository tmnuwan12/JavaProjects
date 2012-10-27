/**
 * 
 */
package com.dev.java.collection.stack;

/**
 * @author tmnuwan12
 * Data structure for Stack.
 * Node always points to the previous node in the stack.
 *
 */
public class Node<E> {

	E item;
	
	Node<E> prev;
	
	Node(E e, Node<E> prev)
	{
		this.item = e;
		this.prev = prev;
		
	}
	

}

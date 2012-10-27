/**
 * 
 */
package com.dev.java.collection.stack;

/**
 * @author tmnuwan12 Simple Stack implementation
 * 
 */
public class MyStack<E> {

	private Node<E> current;

	private int size = 0;

	public MyStack() {

	}

	/**
	 * When items are getting push to the stack new item's previous points to
	 * the current element. Stack size get increased by one.
	 * 
	 * @param e
	 */
	@SuppressWarnings("unused")
	public void push(E e) {
		Node<E> c = current;

		Node<E> newNode = new Node<>(e, current);
		current = newNode;
		size++;

	}

	/**
	 * Current items get removed. All its referenced get nullified to help
	 * garbage collection. Previous item to the current item becomes the last
	 * item on the stack now. Stack size get reduced by one. Throws 
	 * un-checked exception when attempting to fetch items from an empty stack.
	 * 
	 * @return
	 */
	public E pop() {
		Node<E> c = current;
		E e = (E) c.item;
		Node<E> newCurrent = c.prev;
		current = newCurrent;
		c.prev = null; // get rid of the head and help GC
		size--;
		if(e == null)
			throw new NullPointerException("Empty stack, no elements found");
		return e;

	}

	/**
	 * Returns the size of the stack.
	 * @return
	 */
	public int getSize() {
		return size;
	}
}

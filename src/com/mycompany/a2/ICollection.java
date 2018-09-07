package com.mycompany.a2;

public interface ICollection<E> {
     
    public IIterator<E> getIterator();
    
    
    /**
     * Add an element to the collection.
     * @param e The element you want to add.
     * @throws ClassCastException if the class of the specified element
     *         prevents it from being added to this collection
     * @throws IllegalArgumentException if some property of the element
     *         prevents it from being added to this collection
     * @throws NullPointerException if the specified element is null and this
     *         collection does not permit null elements
     */
    public boolean add(GameObject e);
    

}

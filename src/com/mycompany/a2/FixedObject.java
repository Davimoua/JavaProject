package com.mycompany.a2;

/* The fixedObject class is overriding the location setter (setLocationX and setLocationY) 
 * to implement an empty body implementation. The fixed objects are not allowed to move 
 * 
 */
public abstract class FixedObject extends GameObject{
	public FixedObject() {
		
	}
	
	@Override
	public void setLocationX(float x) {
		//Empty Body Implementations
	}
	
	@Override
	public void setLocationY(float y) {
		//Empty Body Implementations
	}
}

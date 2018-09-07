package com.mycompany.a2;

import java.util.Vector;

public abstract class GameObject {
	private static final String NAME = "GameObject";
	private int size;
	private int color;
	private Vector<Float> location;
	
	public GameObject() {
		location = new Vector<Float>();
		location.setSize(2);
	}
	
	public String getName() {
		return NAME;
	}
	
	public GameObject(int size, int objectColor) {
		this.size = size;
		color = objectColor;
	}
	
	public float getLocationX() {
		return location.get(0);
	}
	
	public float getLocationY() {
		return location.get(1);
	}
	
	public void setLocationX(float x) {
		location.set(0, x);
	}
	
	public void setLocationY(float y) {
		location.set(1, y);
	}
	
	public void setLocation(float x, float y) {
		location.set(0, x);
		location.set(1, y);
	}
	
	public int getSize() {
		return this.size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	
	public int getColor(){
		return color;
	}
	
	public void setColor(int color) {
		this.color = color;
	}

}

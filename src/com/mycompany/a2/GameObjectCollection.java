package com.mycompany.a2;

import java.util.ArrayList;

public class GameObjectCollection implements ICollection<GameObject>{
	private ArrayList<GameObject> go;
	
	public IIterator<GameObject> getIterator() {
		return new Iterator(go);
	} 
	
	public GameObjectCollection() {
		go = new ArrayList<GameObject>();
	}
	

	public GameObject get(int i) {
		return go.get(i);
	}
	
	
	public boolean add(GameObject obj) {
		return go.add(obj);
	}
	
	
	public int getSize() {
		return go.size();
	}

	private class Iterator implements IIterator<GameObject> {
		private ArrayList<GameObject> gameObjectList;
		private int curIndex;
		
		public Iterator(ArrayList<GameObject> gameObjectList) {
			this.gameObjectList = gameObjectList;
			curIndex = 0;
		}
		
		public boolean hasNext() {
			return curIndex < gameObjectList.size();
		}
		
		public GameObject getNext() {
			GameObject returnObject = gameObjectList.get(curIndex);
			curIndex++;
			return returnObject;
		}
		
		public void remove() {
			gameObjectList.remove(curIndex);
		}
	}
}

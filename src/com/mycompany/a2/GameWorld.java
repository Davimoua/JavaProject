package com.mycompany.a2;

import com.codename1.ui.events.ActionListener;
import com.codename1.charts.util.ColorUtil;
import com.codename1.io.Log;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import java.lang.String;
import java.util.Vector;
import java.util.Observable;
import java.util.Observer;
import java.util.Random;

public class GameWorld extends Observable {
	private int gameSizeWidth = 1024;
	private int gameSizeHeight = 768;
	//private Vector<GameObject> goc;
	private GameObjectCollection goc;
	private GameObject objects;
	private int birds=2; private int cars=1;private int pylons=9; private int fuelCans=2; private int npc=3;
	private Cars car; private Birds bird; private Pylons pylon; private FuelCans can;
	private int numLives, clockElapse, highestPylonNum;
	private Random num;
	private boolean sound;
	
	public GameWorld() {
		//goc = new Vector<GameObject>();
		goc = new GameObjectCollection();
		num = new Random();
		numLives = 3;
		clockElapse = 0;
		sound = true;
	}
	
	public void init() {
		createBirds();
		createPylons();
		createCar();
		createNPC();
		createFuelCans();
	}
	
	private void createBirds() {
		 for(int i=0; i<birds;i++) {
			bird = new Birds();
			bird.setLocation(num.nextInt(gameSizeWidth), num.nextInt(gameSizeHeight));
			bird.setHeading(num.nextInt(360));
			goc.add(bird);
		} 
	}
	
	private void createPylons() {
		 for(int i=0; i<pylons;i++) {
			int tmp = i;
			pylon = new Pylons();
			pylon.setColor(ColorUtil.BLUE);
			pylon.setSize(10);
			//Pylons p = (Pylons) objects;
			pylon.setSequenceNumber(tmp+1);
			switch (i){
				case 0: {
					pylon.setLocation(200.0f, 200.0f);
					goc.add(pylon);
					break;
				}
				case 1: {
					pylon.setLocation(300.0f, 700.0f);
					goc.add(pylon);
					break;
				}
				case 2: {
					pylon.setLocation(700.0f, 1100.0f);
					goc.add(pylon);
					break;
				}
				case 3: {
					pylon.setLocation(400.0f, 400.0f);
					goc.add(pylon);
					break;
				}
				default:
					break;
			}
			
		}
	}
	
	private void createCar() {
		 for(int i=0; i<cars;i++) {
			car = new Cars();
			car.setSize(40);
			//Cars c = (Cars) objects;
			car.setHeading(0);
			car.setSpeed(0);
			IIterator<GameObject> iterator = goc.getIterator();
			while(iterator.hasNext()) {
				GameObject obj = iterator.getNext();
				if(iterator.getNext() instanceof Pylons)
					pylon = (Pylons) obj;
					if(pylon.getSequenceNumber() == 1) {
						float xPylon1 = pylon.getLocationX();
						float yPylon1 = pylon.getLocationY();
						car.setLocation(xPylon1, yPylon1);
						break;
					}
					else
						continue;
			}  
			goc.add(car);
		} 
	}
	
	private void createNPC() {
		int counterX = 20;
		for(int i=0; i<npc; i++) {
			NonPlayerCar npc = new NonPlayerCar();
			int tmp = num.nextInt(2)+1;
			if(tmp ==1 )
				npc.setStrategy(new StrategyPylon(npc,pylon));
			else
				npc.setStrategy(new StrategyCar(npc,car));
			IIterator<GameObject> iterator = goc.getIterator();
			while(iterator.hasNext()) {
				GameObject obj = iterator.getNext();
				if(iterator.getNext() instanceof Pylons)
					pylon = (Pylons) obj;
					//npc.setStrategy(new StrategyPylon(npc,pylon));
					if(pylon.getSequenceNumber() == 1) {
						float xPylon1 = pylon.getLocationX();
						float yPylon1 = pylon.getLocationY();
						npc.setLocation(xPylon1+counterX, yPylon1+counterX);
						counterX += 20;
						break;
					}
					else
						continue;
			}  
			goc.add(npc);
		}
	}

	
	private void createFuelCans() {
		for(int i=0; i<fuelCans;i++) {
			can = new FuelCans();
			int tmp = num.nextInt(10)+1;
			can.setSize(tmp);
			can.setCapacity(can.getSize());
			can.setLocation(num.nextInt(gameSizeWidth), num.nextInt(gameSizeHeight));
			goc.add(can);
		}
	
	}
	
	public void accelerate() {
		IIterator<GameObject> iterator = goc.getIterator();
		while(iterator.hasNext()) {
			GameObject game = iterator.getNext();
			if(game instanceof Cars) {
				car = (Cars) game;
				if(car.getFuelLevel() == 0)
					car.setSpeed(0);
				else if(car.getSpeed() == car.getMaxSpeed())
					car.setSpeed(car.getMaxSpeed());
				else {
					car.increaseSpeed();
					car.reduceFuelLevel();
				}
			}
		}
		updateObservers();
	}
	
	public void brake() {
		IIterator<GameObject> iterator = goc.getIterator();
		while(iterator.hasNext()) {
			GameObject game = iterator.getNext();
			if(game instanceof Cars) {
				car = (Cars) game;
				car.decreaseSpeed();
			}
		}
		updateObservers();
	}
	
	public void left() {
		IIterator<GameObject> iterator = goc.getIterator();
		while(iterator.hasNext()) {
			GameObject game = iterator.getNext();
			if(game instanceof Cars) {
				car = (Cars) game;
				car.updateSteering();
			}
			else if(game instanceof NonPlayerCar) {
				NonPlayerCar npc;
				npc = (NonPlayerCar) game;
				npc.updateSteering();
			}
		}
		updateObservers();
		//IIterator<GameObject> iterator = this.getIterator();
	}
	
	public void right() {
		IIterator<GameObject> iterator = goc.getIterator();
		while(iterator.hasNext()) {
			GameObject game = iterator.getNext();
			if(game instanceof Cars) {
				car = (Cars) game;
				car.updateSteering();
			}
			else if(game instanceof NonPlayerCar) {
				NonPlayerCar npc;
				npc = (NonPlayerCar) game;
				npc.updateSteering();
			}
		}
		updateObservers();
	}
	
	public void npcCollision() {
		GameObject game;
		car.increaseDamage();
		IIterator<GameObject> iterator = goc.getIterator();
		while(iterator.hasNext()) {
			game = iterator.getNext();
			if(game instanceof NonPlayerCar) {
				NonPlayerCar npc;
				npc = (NonPlayerCar) game;
				npc.increaseDamage();
			}
		}
		updateObservers();
	}
	
	public void carCollision() {
		GameObject game;
		int i=0;
		int carLocation=i;
		IIterator<GameObject> iterator = goc.getIterator();
		while(iterator.hasNext()) {
			game = iterator.getNext();
			if(game instanceof Cars) {
				car = (Cars) game;
				carLocation = i;
				break;
			}
		}
		car.increaseDamage();
		int carDamage = car.getDamageLevel();
		int brightRed;
		switch(carDamage) {
			case 1:
				brightRed = ColorUtil.rgb(235,0,0);
				car.setMaxSpeed(40);
				if(car.getSpeed() > car.getMaxSpeed())
					car.setSpeed(car.getMaxSpeed());
				goc.get(carLocation).setColor(brightRed);
				break;
			case 2:
				brightRed = ColorUtil.rgb(215,0,0);
				car.setMaxSpeed(30);
				goc.get(carLocation).setColor(brightRed);
				break;
			case 3:
				brightRed = ColorUtil.rgb(195,0,0);
				car.setMaxSpeed(20);
				if(car.getSpeed() > car.getMaxSpeed())
					car.setSpeed(car.getMaxSpeed());
				goc.get(carLocation).setColor(brightRed);
				break;
			case 4:
				System.out.println("Life is lost");
				car.setDamage(0);
				carCollision();
				if(numLives> 0)
					numLives--;
				if(numLives <= 0) {
					System.out.println("GameOver");	
					init();
				}
				break;
			default: 
				break;
		}
		updateObservers();
	}
	
	public void pylonCollision() {
		try {
			Command cOk = new Command("Ok");
			Command cCancel = new Command("Cancel");
			Command[] cmds = new Command [] {cOk, cCancel};
			TextField myTf = new TextField();
			Command c = Dialog.show("Enter the next cumulative Pylon Collision", myTf, cmds);
			if(c == cOk) {
				String tmp = myTf.getText();
				int result = Integer.parseInt(tmp);
				if(result >= 1 && result <= 9) {				
					    if(result == car.getLastPylonReached()+1) 
					    	car.pretendPylonReach();
						else
							pylonCollision();
				}
				else {
					System.out.print("Please enter a valid number between 1-9");
					pylonCollision();
				}
			}
			updateObservers();
		}
		catch(NumberFormatException nfe) {
			pylonCollision();
		}
	}
	
	public void fuelCollision() {
		IIterator<GameObject> iterator = goc.getIterator();
		IIterator<GameObject> iteratorTwo = goc.getIterator();
		while(iterator.hasNext()) {
			if(iterator.getNext() instanceof Cars){
				break;
			}
		}
		while(iteratorTwo.hasNext()) {
			GameObject obj = iterator.getNext();
			if(obj instanceof FuelCans) {
				can = (FuelCans) obj;
				if(can.getCapacity() == 0) {
					createFuelCans();
					continue;
				}
				int tmp = can.getCapacity(); 
				car.setFuelLevel(car.getFuelLevel() + tmp);
				can.setCapacity(0);
				
				break;
			}
		}
		updateObservers();
	}
	
	public void birdCollision() {
			car.increaseDamage();
			int carDamage = car.getDamageLevel();
			int brightRed;
			switch(carDamage) {
				case 1:
					brightRed = ColorUtil.rgb(235,0,0);
					car.setMaxSpeed(40);
					if(car.getSpeed() > car.getMaxSpeed())
						car.setSpeed(car.getMaxSpeed());
					car.setColor(brightRed);
					break;
				case 2:
					brightRed = ColorUtil.rgb(215,0,0);
					car.setMaxSpeed(30);
					if(car.getSpeed() > car.getMaxSpeed())
						car.setSpeed(car.getMaxSpeed());
					car.setColor(brightRed);
					break;
				case 3:
					brightRed = ColorUtil.rgb(195,0,0);
					car.setMaxSpeed(20);
					if(car.getSpeed() > car.getMaxSpeed())
						car.setSpeed(car.getMaxSpeed());
					car.setColor(brightRed);
					break;
				case 4:
					System.out.println("Life is lost");
					car.setDamage(0);
					carCollision();
					if(numLives> 0)
						numLives--;
					if(numLives <= 0) {
						System.out.println("GameOver");	
						init();
					}
					break;
				default: 
					break;
				}
		updateObservers();
	}
	
	public void clockTick() {
		IIterator<GameObject> iterator = goc.getIterator();
		while(iterator.hasNext()) {
			objects = iterator.getNext();
			if(objects instanceof MoveableObject) {
				if(objects instanceof Birds) {
					bird = (Birds) objects;
					bird.move();
				}
				else if(objects instanceof Cars){
					car = (Cars) objects;
					if(car.getFuelLevel() > 0 ) {
						car.reduceFuelLevel();
						if((car.getSpeed()) < car.getMaxSpeed())
							car.increaseSpeed();
						car.move();
					}
					else 
						System.out.print("Game Over");
						
				}
			}
		}
		clockElapse++;
		updateObservers();
	}
	
	public void displayGameStatus() {

		IIterator<GameObject> iterator = goc.getIterator();
		while(iterator.hasNext()) {
			objects = iterator.getNext();
			if(objects instanceof Cars)
				car = (Cars) objects;
		}
		
		System.out.println("Number of Lives: " + numLives);
		System.out.println("Current Clock Value: " + clockElapse);
		System.out.println("Highest Plyon Reach: " + car.getLastPylonReached());
		System.out.println("Current Fuel Level: " + car.getFuelLevel());
		System.out.println("Car Current Damage: " + car.getDamageLevel());
	}
	
	public void showMap() {
		/*for(GameObject list: go) {
			System.out.println(list);
		}  */
		StringBuilder sb = new StringBuilder();
		IIterator<GameObject> iterator = goc.getIterator();
		while(iterator.hasNext()) {
			GameObject gameObject = iterator.getNext();
			sb.append(gameObject.toString());
			sb.append("\n");
		} 
		Log.p(sb.toString()); 
	} 
	
	public void exit() {
		Boolean bOk = Dialog.show("Exit Game Option", "Do you want to exit the game?", "OK", "Cancel");
		if(bOk){
			Display.getInstance().exitApplication();
		} 
	}
	
	public void help() {
		StringBuilder helpInfo = new StringBuilder("Keybinds availble to the user: \n");
		helpInfo.append(createKeyBindHelpText("a", "accelerate")).append("\n");
		helpInfo.append(createKeyBindHelpText("b", "brake")).append("\n");
		helpInfo.append(createKeyBindHelpText("l", "left")).append("\n");
		helpInfo.append(createKeyBindHelpText("r", "right")).append("\n");
		helpInfo.append(createKeyBindHelpText("f", "fuel can")).append("\n");
		helpInfo.append(createKeyBindHelpText("t", "tick")).append("\n");
		helpInfo.append(createKeyBindHelpText("e", "exit")).append("\n");
		Dialog.show("Key Commands", helpInfo.toString(),"OK", null);
	}
	
	private String createKeyBindHelpText(String key, String message) {
        return "'" + key + "' - " + message;
    }
	
	public int getTick() {
		return clockElapse;
	}
	
	public int getLives() {
		return numLives;
	}
	
	public int getLastPylon() {
		return car.getLastPylonReached();
	}
	
	public boolean getSound() {
		return sound;
	}
	public String getSoundString() {
		if(sound)
			return "ON";
		else
			return "OFF";
	}
	public void setSound(boolean value) {
		sound = value;
		System.out.print("Sound has been triggered");
		updateObservers();
	}
	public void turnSoundOnOff() {
		setSound(!getSound());
	}
	public int getFuelLevel() {
		return car.getFuelLevel();
	}
	public int getCarDamage() {
		return car.getDamageLevel();
	}
	
	public void displayAboutInfo() {
        StringBuilder aboutMsg = new StringBuilder();
        aboutMsg.append("Name: ").append("David Moua").append("\n");
        aboutMsg.append("Course Name").append("CSC 133").append("\n");
        aboutMsg.append("Assignment #: ").append("2");
        Dialog.show("About", aboutMsg.toString(), "Ok", null);
    }
	
	public void switchStrategy() {
		IIterator<GameObject> iterator = goc.getIterator();
		while(iterator.hasNext()) {
			GameObject obj = iterator.getNext();
			if(obj instanceof NonPlayerCar){
				NonPlayerCar npc;
				npc = (NonPlayerCar) obj;
				if(npc.getStrategy() instanceof StrategyCar ){
					npc.setStrategy(new StrategyPylon(npc, getPylon(npc.getLastPylonReached(),npc)));
				}
				else if(npc.getStrategy() instanceof StrategyPylon) {
					npc.setStrategy(new StrategyCar(npc, car));
				}
				
			}
		} 
		updateObservers();
	}
	
	public Pylons getPylon(int lastPylon, NonPlayerCar npc) {
		Pylons py = null;
		npc.increaseDamage();
		IIterator<GameObject> pylonIt = goc.getIterator();
		while(pylonIt.hasNext()){
			GameObject obj = pylonIt.getNext();
			if(obj instanceof Pylons ) {
				py = (Pylons) obj;
				if(npc.getLastPylonReached()+1 == py.getSequenceNumber()) {
					py = (Pylons) obj;
				}
				else
					continue;
			}
		}
		return py;
	}
	
	@Override
	public void addObserver(Observer observer) {
		// TODO Auto-generated method stub
		super.addObserver(observer);
		updateObservers();
	}
	
	private void updateObservers() {
		setChanged();
		notifyObservers();
	}
	
	
}

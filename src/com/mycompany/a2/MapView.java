package com.mycompany.a2;

import java.util.Observable;
import java.util.Observer;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Container;
import com.codename1.ui.layouts.Layout;
import com.codename1.ui.plaf.Border;

public class MapView extends Container implements Observer {

	public MapView() {
		super();
		setStyle();
	}
	
	public MapView(Layout layout) {
		super(layout);
		setStyle();
	}
	public void update(Observable observable, Object data) {
		// TODO Auto-generated method stub
		GameWorld gw = (GameWorld) observable;
		gw.showMap();
		
	}
	
	private void setStyle() {
		this.getAllStyles().setBorder(Border.createLineBorder(2,ColorUtil.LTGRAY));
	}

}

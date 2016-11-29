package com.sitanems.event.util;

import java.util.ArrayList;
import java.util.List;

public class BasicEventGenerator implements IEventGenerator{
	private List<IEventListener> listenerList;
	
	public List<IEventListener> getListenerList() {
		return listenerList;
	}

	public void setListenerList(List<IEventListener> listenerList) {
		this.listenerList = listenerList;
	}

	public BasicEventGenerator() {
		listenerList = new ArrayList<IEventListener>();
	}

	@Override
	public void addListener(IEventListener listener) {
		// TODO Auto-generated method stub
		listenerList.add(listener);
	}

	@Override
	public void removeListener(IEventListener listener) {
		// TODO Auto-generated method stub
		listenerList.remove(listener);
	}

	@Override
	public void publish(IEvent event) {
		// TODO Auto-generated method stub
		for (int i = 0; i < listenerList.size(); i++)
		{
			listenerList.get(i).handleData(event);
		}
	}

}

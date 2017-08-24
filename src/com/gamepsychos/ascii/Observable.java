package com.gamepsychos.ascii;

public interface Observable<T> {

	public void regiserObserver(Observer<T> observer);
	
	public void notifyObservers(Message msg);
	
}

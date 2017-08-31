
public class MenuItem {
	final String sLabel;
	final Action a;
	
	public MenuItem(String _Label,Action action){
		a=action;
		sLabel=_Label;	
	}

	public String getLabel() {
		return sLabel;
	}
	public void performAction(){
		a.performAction();
		
	}
	
	

}

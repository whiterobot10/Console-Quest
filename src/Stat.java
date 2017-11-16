import java.util.ArrayList;

public class Stat {
	int iBase, iWeaponMod, iHelmetMod, iCharmMod, iTempChange;
	ArrayList<StatChange> TempModifiers= new ArrayList<StatChange>();

	public Stat() {
	}

	public Stat(int base) {
		iBase = base;
	}

	public int getValue() {
		int returnValue = iBase + iWeaponMod + iHelmetMod + iCharmMod + iTempChange;
		for(StatChange s:TempModifiers){
			returnValue+=s.change;
		}
		if (returnValue > 0) {
			return returnValue;
		} else {
			return 0;
		}

	}

	public boolean compareOrigins(Entity entity) {
		for(StatChange s:TempModifiers){
		if(entity==s.inflicter){
			return true;
		}	
		}
		return false;
	}

}

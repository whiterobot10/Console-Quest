
public class InvalidFrameExeption extends RuntimeException {

	public InvalidFrameExeption(String key) {
		super("The frame \""+key + "\" does not exist.");
	}

}

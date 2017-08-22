import java.util.Random;
import java.util.Scanner;

public class TextAdventure {

	// Declare variables that can be used across all methods
	
	// Scanner used for user input
	static Scanner input;
	// The name of the character
	static String characterName;
	// The players level
	static int characterLevel;
	// The players hit points
	static double characterHP;
	// true if the player has a sword and false otherwise
	static boolean hasSword;
	
	public static void main(String[] args) {
		
		// Create a Scanner to read user input
		input = new Scanner(System.in);
		
		// Get the players name
		characterName = getCharacterName();
		
		// Set the players level, HP, and specify they don't start with a sword
		characterLevel = 1;
		characterHP = 25;
		hasSword = false;
		
		// Call the intro method
		intro();

	}
	
	// This function asks the user for a name
	// then returns the name they enter
	public static String getCharacterName(){
		String name;
		System.out.println("Greetings Adventurer! What is your name?");
		name = input.nextLine();
		return name;
	}
	
	public static void intro(){
		// Prints out a message
		System.out.println("And so your adventure begins...");
		System.out.println("Before you is a large cave mouth. Darkness is before you. Dare you enter the Cave of Wonders?");
        System.out.println("1. Enter the Cave");
        System.out.println("2. Run away to the safety of your warm bed");
        // Gets user input
        int choice = input.nextInt();
        if(choice == 1){
        	// If the user entered 1, enter the cave
        	enterCave();
        } else {
        	// Otherwise, you run away!
        	runAway();
        }
	}
	
	public static void enterCave(){
		//The player enters the cave
		System.out.println("You made it to the entrance of the cave.");
		// Increase the players level
        characterLevel++;
        System.out.println("Your bravery has rewarded you! Congratulations, you are now level " + characterLevel);
        // The player triggers a trap!
        trap();
        // The player continues
        System.out.println("You pick yourself up from the cave floor and brush a cloud of dust from your clothes.");
        System.out.println("The cave narrows as you press on until you manage to squeeze through an opening into a large natural cavern.");
        // The player finds a chest
        chest();
	}
	
	
	public static void trap(){
		// Traveling forward, a trap is triggered
		System.out.println("You travel forward into the dark cave. You feel something press against your leg...");
        System.out.println("It's a trap! Suddenly, you're being bombarded by rocks from above.");
        
        // Generate a random number between 0 and 4
        int rockDamage;
        Random randomGenerator = new Random();
        rockDamage = randomGenerator.nextInt(5);
        
        // Reduce the players hp by the amount generated
        characterHP -= rockDamage;
        
        // Print out a message
        System.out.println("The rock slide hits you for " + rockDamage + "!");
        System.out.println("You have " + characterHP + " health remaining.");
	}
	
	public static void chest(){
		// Check if the player would like to open a chest
		System.out.println("Before you is a treasure chest.");
		System.out.println("1. Open the chest.");
        System.out.println("2. Ignore the chest. Obvious trap is obvious.");
        int choice = input.nextInt();
         
        // If the player opens the chest, they receive a sword
        if(choice == 1){
            System.out.println("Uneasy after your brush with death, you slowly open the chest to reveal a magical sword!");
            hasSword = true;
        }
        
        dragonsCavern();
	}
	
	
	public static void dragonsCavern(){
		// The dragons cavern
		System.out.println("You leave the cavern with the chest and continue your journey");
        System.out.println("Up ahead the air grows warm and wet. You hear snoring.");
        System.out.println("Peeking around a corner you see a monstrous shape! A dragon sleeps in the room ahead.");
        System.out.println("You swallow hard and decide it is time to leave.");
        System.out.println("As you turn your foot finds a rock. It thuds loudly against the cave wall. The dragon wakes up!");
         
        // Ask if the player would like to fight the dragon or run for their life
        System.out.println("1. Fight the Dragon.");
        System.out.println("2. Run for your life.");
        int choice = input.nextInt();
        if(choice == 1){
        	// Fight the dragon
            System.out.println("You feel courage surge through your body as you charge the dragon!");
            if(hasSword){
            	// If the player has a sword, they win
                System.out.println("You raise the magic sword and plunge it into the dragon slaying the beast.");
                System.exit(0);
            }else {
            	// Otherwise, they lose
                System.out.println("You pummel the dragon with your fists to no avail. The dragon snarls and swallows you whole. Game Over!");
                System.exit(0);
            }            
        }else {
            // The player flees
            System.out.println("As you run away to hide in the safety of your warm bed, the dragon yawns and returns to its slumber.");
            System.exit(0);
        }
	}
	
	public static void runAway(){
		System.out.println(characterName + " runs home and goes to sleep.");
        System.exit(0);
	}

}

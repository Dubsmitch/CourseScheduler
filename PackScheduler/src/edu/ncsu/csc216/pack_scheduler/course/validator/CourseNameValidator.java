package edu.ncsu.csc216.pack_scheduler.course.validator;

public class CourseNameValidator {


	/** state for letter **/
	private final State stateLetter = new LetterState();
	/** state for Digit **/
	private final State stateNumber = new NumberState();
	/** state for Suffix **/
	private final State stateSuffix = new SuffixState();
	/** initial State **/
	private State stateInitial = new InitialState();
	/** current State **/
	private State currentState = stateInitial;

	/** checks state **/
	private boolean validEndState;
	/** counts the number of Letters **/
	protected int letterCount;
	/** counts the total number of Digits **/
	private int digitCount;

	public boolean isValid(String courseName) throws InvalidTransitionException {
		letterCount = 0;
		digitCount = 0;
		char ch;
		
		for (int i = 0; i < courseName.length(); i++) {
			ch = courseName.charAt(i);
			if (Character.isLetter(ch)) {
				currentState.onLetter();
			} else if (Character.isDigit(ch)) {
				currentState.onDigit();
			} else {
				currentState.onOther();
			}
		
		}
		
		return validEndState;
		
		
	}

	abstract public class State {
		
		public State() {
			stateInitial = stateLetter;
		}
		
		public abstract void onLetter() throws InvalidTransitionException;
			
		public abstract void onDigit() throws InvalidTransitionException;
		
		public void onOther() throws InvalidTransitionException {
			throw new InvalidTransitionException ("Course name can only"
					+ " contain letters and digits.");
		}
	}
	
	public class LetterState extends State {
		/** Total number of Letters accepted **/
		private final int MAX_LETTERS = 4;

		
		@Override
		public void onLetter() throws InvalidTransitionException {
			if (letterCount >= MAX_LETTERS) {
				throw new InvalidTransitionException("Course name cannot have"
						+ "more than 4 letters");
			} else {
			letterCount++;
			}
		}

		@Override
		public void onDigit() {
			digitCount++;
			currentState = stateNumber;
		}
	}
	
	public class NumberState extends State{
		/** Number of Digits in an acceptable course name **/
		private final int DIGITS = 3;


		@Override
		public void onLetter() throws InvalidTransitionException {
			if (digitCount < DIGITS) {
				throw new InvalidTransitionException("Course name must"
						+ "have at three numbers.");
			} else if (digitCount == 3) {
				validEndState = true;
				currentState = stateSuffix;
			} else {
				throw new InvalidTransitionException("Course name"
						+ " must have 3 numbers.");
			}
		}

		@Override
		public void onDigit() throws InvalidTransitionException {
			if (digitCount == 3) {
				throw new InvalidTransitionException("Course name"
						+ " must have 3 numbers.");
			} else {
				digitCount++;
				if (digitCount == 3) {
					validEndState = true;
				}
			}
		}
	}

	public class SuffixState extends State{


		@Override
		public void onLetter() throws InvalidTransitionException {
			throw new InvalidTransitionException("Course name"
					+ " can only have 1 suffix");
		}

		@Override
		public void onDigit() throws InvalidTransitionException {
			throw new InvalidTransitionException("Course name"
					+ " can only have 1 suffix");		}
	}
	
	public class InitialState extends State{

		@Override
		public void onLetter() {
			currentState = stateLetter;
			letterCount++;

			
		}

		@Override
		public void onDigit() throws InvalidTransitionException {
			throw new InvalidTransitionException("Course name must start"
					+ " with a letter.");
		}
	}
	}


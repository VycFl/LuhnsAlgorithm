
public final class HelperMethods {

	public static boolean verifyLength(String cardNum) {
		return cardNum.length() >= 13 && cardNum.length() <= 19;
	}
	
	public static boolean verifyStartingNumber(String cardNum) {
		int firstNum = Character.getNumericValue(cardNum.charAt(0));
		
		return firstNum == 3 || firstNum == 4 || firstNum == 5 || firstNum == 6;
	}
	
	public static int calc1(String cardNum) {
		int sum = 0;
		
		for(int i = cardNum.length() - 2; i >= 0; i = i - 2) {
			int doubleNum = 2 * Character.getNumericValue(cardNum.charAt(i));
			
			if(doubleNum >= 10) {
				sum += Character.getNumericValue(Integer.toString((doubleNum)).charAt(0)) + Character.getNumericValue(Integer.toString((doubleNum)).charAt(1));
			}
			else {
				sum += Character.getNumericValue(Integer.toString((doubleNum)).charAt(0));
			}
		}
		return sum;
	}
	
	public static int calc2(String cardNum) {
		int sum = 0;
		
		int startPosition = cardNum.length() - 1;
		
		for(int i = startPosition; i >= 0; i = i - 2) {
			sum += Character.getNumericValue(cardNum.charAt(i));
		}
		return sum;
	}
	
}

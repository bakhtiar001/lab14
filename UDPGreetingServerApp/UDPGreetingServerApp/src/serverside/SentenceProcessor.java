package serverside;

public class SentenceProcessor {
	
	private int size = 65535; 
	
	private String sentence;
	
	
	public SentenceProcessor(byte[] byteData) {
		
		this.sentence = new String(byteData);
	}
	
	public String getSentence() {
		
		return sentence;
	}
	
	/**
	 * This method convert value into an array of byte
	 * @param length
	 * @return
	 */
	public byte[] convertToByteArray(int value) {
		
		byte[] outData = new byte[size];
		
		String stringValue = Integer.valueOf(value).toString();
		
        outData = stringValue.getBytes();
        
        return outData;
	}
	
	/**
	 * This method count the number of characters in a sentence
	 * @return
	 */
	public int countCharacters() {
		
        int index = 0;
        char currentChar = sentence.charAt(index);
        while (currentChar != '\0') {
        	
        	currentChar = sentence.charAt(index++);
        }
        

        return index - 1;
	}
	
	public int countVowels() {
        int count = 0;
        String vowels = "AEIOUaeiou";
        
        for (int i = 0; i < sentence.length(); i++) {
            char c = sentence.charAt(i);
            if (vowels.indexOf(c) != -1) {
                count++;
            }
        }
        
        return count;
    }
    
    public int countConsonants() {
        int count = 0;
        String consonants = "BCDFGHJKLMNPQRSTVWXYZbcdfghjklmnpqrstvwxyz";
        
        for (int i = 0; i < sentence.length(); i++) {
            char c = sentence.charAt(i);
            if (consonants.indexOf(c) != -1) {
                count++;
            }
        }
        
        return count;
    }
    
    public int countPunctuationMarks() {
        int count = 0;
        String punctuationMarks = ".?!;:,";
        
        for (int i = 0; i < sentence.length(); i++) {
            char c = sentence.charAt(i);
            if (punctuationMarks.indexOf(c) != -1) {
                count++;
            }
        }
        
        return count;
    }
	
}

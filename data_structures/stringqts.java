 /**
  * @author Ketan Kokane
 File to solve strings related questions in
 Problems:
 URLify a string(replace ' ' with '%20')
 RemoveChars (remove a list of characters from a given string)
  */
import java.util.HashSet;

public class stringqts {
    public static void main(String[] args) {

        String result = URLify("abc  b a        ", 8);
        //System.out.println(result);
        System.out.println(removeChars("aaaaade","ad"));
    }

    /**
     * idea was to move the characters to the right to make space for '%20'
     * resembles deletion in array
     * @param input
     * @param trueLength
     * @return
     */
    public static String URLify(String input, int trueLength) {
        char[] inputArray = input.toCharArray();
        char[] replaceWith = {'%', '2', '0'};

        for (int currentIndex = 0; currentIndex < trueLength; currentIndex++) {
            if (inputArray[currentIndex] == ' ') {
                // move the character
                for (int j = trueLength - 1; j > currentIndex; j--) {
                    inputArray[j + 2] = inputArray[j];
                }
                // replace
                for (int k = 0; k < 3; k++) {
                    inputArray[currentIndex] = replaceWith[k];
                    currentIndex++;
                }
                currentIndex--;
                trueLength+=2;
            }

        }

        return new String(inputArray);
    }

    /**
     * same as URLify
     * resembles deletion in array
     * used Hashset to lookup for a character that needs to be deleted o(m+n) with o(m) memory
     * @param str
     * @param remove
     * @return
     */
    public static String removeChars( String str, String remove ){
        HashSet<Character> elementsToBeRemoved = new HashSet<>();
        for (int i = 0; i < remove.length(); i++) {
            elementsToBeRemoved.add(remove.charAt(i));
        }
        char [] inputCharArray = str.toCharArray();
        int placesToMove = 0;
        int index ;
        for ( index = 0; index < inputCharArray.length; index++) {
            if(elementsToBeRemoved.contains(inputCharArray[index])){
                placesToMove++;
            }
            else if(placesToMove > 0){
                inputCharArray[index -placesToMove] = inputCharArray[index];
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < index - placesToMove; i++) {
            sb.append(inputCharArray[i]);
        }
        return sb.toString();
    }
}

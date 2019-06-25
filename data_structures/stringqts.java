 /**
  * @author Ketan Kokane
 File to solve strings related questions in
 Problems:
 URLify a string(replace ' ' with '%20')
 RemoveChars (remove a list of characters from a given string)
 Reverse Words in a string
 Palindrome permuation
  */
import java.util.HashSet;

public class stringqts {
    public static void main(String[] args) {

        System.out.println(('0' - '3'));
        String result = URLify("abc  b a        ", 8);
        System.out.println(result);
        System.out.println(removeChars("aaaaade","ad"));
        reverseWords("ketan am i");
        System.out.println(PalindromePermutation("tactcao"));
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

    private static char[] reverse(char[] input,int left, int right){
        char temp;
        for(;left < right;left++, right--){
            temp = input[left];
            input[left] = input[right];
            input[right] = temp;
        }
        return input;
    }
    public static String reverseWords(String inputStr){
        char[] input = inputStr.toCharArray();
        input = reverse(input, 0, input.length-1);
    
        int left , right;
        left = 0; right = 1;

        while(right < input.length){
            if(input[right] != ' '){
                right ++;
            }
            else{
                input = reverse(input, left, right-1);
                left = right + 1;
                right = right + 2;
            }
        }
        if (left < input.length - 1){
            input = reverse(input, left, right-1);
        }
        return new String(input);
    }

    /**
     * Palindrome Permutation: Given a string, 
     * write a function to check if it is a permutation of a palin­ drome.
     *  A palindrome is a word or phrase that is the same forwards and backwards. A permutation is a rearrangement of letters. 
     * The palindrome does not need to be limited to just dictionary words.
     * @param input
     * @return
     */
    public static boolean PalindromePermutation(String input){
        input = input.toLowerCase();
        int [] occurances = new int [256];
        int index = 0;
        boolean oddLengthString =false;
        for(; index < input.length(); index ++){ 
            occurances[(int)input.charAt(index)] = occurances[(int)input.charAt(index)] + 1; 
        }

        if (index % 2 != 0 ){
            oddLengthString = true;
        }

        for (int i = 0; i < 256; i++) {
            if(occurances[i]%2 != 0){
                if (oddLengthString){
                    oddLengthString = false;
                }
                else{
                    return false;
                }
            }
        }
        return true;
    }
}

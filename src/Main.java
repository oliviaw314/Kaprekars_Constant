import java.util.HashSet;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        System.out.println(kaprekar(1111));
        System.out.println(kaprekar(92));
        System.out.println(kaprekar(6621));
        System.out.println(kaprekar(6554));
        System.out.println(kaprekar(1234));
    }

    public static int kaprekar(int number) {
        int count = 0;
        //has at least 2 different digits and is 4 digit
        if (hasMin2DiffDigits(number) && String.valueOf(number).length()<=4) {
            //pad to ensure it's a 4-digit number
            number=Integer.parseInt(String.format("%04d",number));

            while (number != 6174) {
                //find ascending and descending order and subtract to form new number
                //bubble sort number by ascending
                StringBuilder ascendingNumber = new StringBuilder(String.valueOf(number));
                boolean swapped = true;
                while (swapped) {
                    swapped = false;
                    for (int i = 0; i < ascendingNumber.length() - 1; i++) {
                        char firstChar = ascendingNumber.charAt(i);
                        char secondChar = ascendingNumber.charAt(i + 1);
                        if (firstChar > secondChar) {
                            ascendingNumber.setCharAt(i + 1, firstChar);
                            ascendingNumber.setCharAt(i, secondChar);
                            swapped = true;
                        }
                    }
                }
                int ascendingNum = Integer.parseInt(ascendingNumber.toString());

                //sort number by descending - by flipping ascending integer
                String reversed = ascendingNumber.reverse().toString();
                int descendingNum = Integer.parseInt(reversed);

                number=descendingNum-ascendingNum; //subtract to find new number
                //pad the number with leading zeros to ensure it's a four-digit number
                number = Integer.parseInt(String.format("%04d", number));

                // Check for repeating digits (like 0000, 1111, etc.)
                if (number == 0) {
                    break; // Exit the loop if the result is zero
                }

                count++;
            }
        }
        return count;
    }

    private static boolean hasMin2DiffDigits(int number) {
        //sets are useful as they automatically get rid of duplicates - ensuring only unique numbers
        Set<Character> digitSet = new HashSet<>();
        for (int i=0; i<String.valueOf(number).length(); i++) {
            digitSet.add(String.valueOf(number).charAt(i));
        }
        return digitSet.size()>=2;
    }
}
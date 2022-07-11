package tests;

import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Test;
import org.apache.commons.lang3.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toMap;

public class UnitTestsJava8 {

//    Coding task. Revert the order of the words inside the String:
//    String myString = “str1 str2 str3 str4 str5 str6 … srt999999”

    @Test
    public void test1Option1() {
        String myString = "str1 str2 str3 str4 str5 str6";

        List<String> reversedList = Arrays.asList(myString.split(" "))
                .stream()
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toList());

        StringBuilder stringBuilder = new StringBuilder();

        for (String s : reversedList) {
            stringBuilder.append(String.format("%s ", s));
        }

        System.out.println("String reversed string = " + stringBuilder);
    }

    @Test
    public void test1Option2() {
        String myString = "str1 str2 str3 str4 str5 str6";

        String[] newArray = myString.split(" ");

        StringBuilder stringBuilder = new StringBuilder(newArray[newArray.length - 1]);

        for (int i = newArray.length - 2; i >= 0; i--) {
            stringBuilder.append(String.format(" %s", newArray[i]));
        }

        System.out.println("String reversed string = " + stringBuilder);
    }


    //    2. Stream API task.  convert User[] to Map<Int, User>
//    class User{
//        Integer id
//        String name
//    }
    @AllArgsConstructor
    private class User {
        Integer id;
        String name;

        public String toString() {
            return "User{" + "name = '" + name + '\'' + '}';
        }
    }

    @Test
    public void test2Option1() {
        User[] userArray = {
                new User(2, "John"),
                new User(45, "Ann")
        };

        Map<Integer, User> userMap = Arrays.stream(userArray)
                .collect(toMap(user -> user.id, user -> user));

        System.out.println(userMap);
    }

//    3. Calculate and log into the console in the desc order of how many times the particular char (any case) is mentioned in the String.
//    String str = “kasjfhbfglaerhbgqejwhcdbvlqwhdbvqwerhbgvehvblqehcbvqwehrbvlqehb”
//    The result example:
//    k – 15
//    y – 13

    @Test
    public void test3Option1() {
        String str = "kasjfhbfglaerhbgqejwhcdbvlqwhdbvqwerhbgvehvblqehcbvqwehrbvlqehb";

        char[] charArray = str.toCharArray();
        Arrays.sort(charArray);

        StringBuilder reversedString = new StringBuilder(String.valueOf(charArray)).reverse();

        System.out.println(reversedString);

        char[] dscArray = reversedString.toString().toCharArray();

        for (int i = 0; i < dscArray.length - 1; i++) {
            if (dscArray[i] != dscArray[i + 1] || dscArray[i] == dscArray[dscArray.length - 1]) {
                int count = StringUtils.countMatches(reversedString, dscArray[i]);
                System.out.println(dscArray[i] + "=" + count);
            }
        }
    }


    @Test
    public void test3Option2() {
        String str = "kasjfhbfglaerhbgqejwhcdbvlqwhdbvqwerhbgvehvblqehcbvqwehrbvlqehb";

        Map<Character, Integer> myMap = new HashMap<>();

        int count;

        for (int i = 0; i < str.length(); i++) {
            if (myMap.isEmpty() || !myMap.containsKey(str.charAt(i))) {
                myMap.put(str.charAt(i), 1);
            } else if (myMap.containsKey(str.charAt(i))) {
                count = myMap.get(str.charAt(i));
                myMap.replace(str.charAt(i), ++count);
            }
        }

        Map<Character, Integer> dscMap = myMap.entrySet()
                .stream()
                .sorted(Collections.reverseOrder(Map.Entry.comparingByKey()))
                .collect(toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e2,
                        LinkedHashMap::new));

        System.out.println(dscMap);
    }

//4. Calculate the number of words where vowels > consonants:
//    String str = "ertrew tjbcjd fufuguo mfkgphpj4 ltkylhp eeeeekd igjjdml4 iiiiihjgn"

    @Test
    public void test4Option1() {

        String str = "ertrew tjbcjd fufuguo mfkgphpj4 ltkylhp eeeeekd igjjdml4 iiiiihjgn";

        String[] newArray = str.split(" ");
        String vowStr = "AEIOUaeiou";
        for (String s : newArray) {
            int vowels = 0;
            for (int i = 0; i < s.length(); i++) {
                if (vowStr.indexOf(s.charAt(i)) != -1) {
                    vowels++;
                }
            }
            if (vowels > (s.length() - 1 - vowels)) {
                System.out.println(s);
            }
        }
    }
}

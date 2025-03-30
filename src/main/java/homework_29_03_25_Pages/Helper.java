package homework_29_03_25_Pages;

import java.util.List;
import java.util.Random;

public class Helper {
    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static final Random RANDOM = new Random();

    public static String generateRandomText(int length) {
        return RANDOM.ints(length, 0, CHARACTERS.length())
                .mapToObj(CHARACTERS::charAt)
                .collect(StringBuilder::new, StringBuilder::append, StringBuilder::append)
                .toString();
    }

    public static boolean areAllNamesContainingSearch(List<String> names, String search) {
        if (names.isEmpty() || search == null) {
            return false;
        }
        return names.stream().allMatch(name -> name.contains(search.toLowerCase()));
    }


}

package PenManager;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * The Nib class is used to keep information on the various types of nibs. The nibs are stored in an ArrayList
 * and can be accessed without class instantiation. The primary use of this class would be to populate
 * relevant ChoiceBoxes.
 */
public final class Nib {
    /**
     * Returns an ArrayList containing the most common and generic nib types.
     *
     * @return an ArrayList with common nibs.
     */
    public static ArrayList<String> getNibTypes() {
        return new ArrayList<>(Arrays.asList("Extra-Fine",
                "Fine",
                "Medium",
                "Broad",
                "Italic",
                "Flex",
                "Other"));
    }


}

package PenManager;

import java.util.ArrayList;
import java.util.Arrays;

public final class Nib {

    private static ArrayList<String> nibTypes;

    public static ArrayList<String> getNibTypes() {
        addNibTypes();
        return nibTypes;
    }

    private static void addNibTypes() {
        nibTypes = new ArrayList<>(Arrays.asList("Extra-Fine",
                "Fine",
                "Medium",
                "Broad",
                "Italic",
                "Flex",
                "Other"));
    }


}

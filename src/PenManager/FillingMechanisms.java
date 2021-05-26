package PenManager;

import java.util.ArrayList;
import java.util.Arrays;

//Used to refer to the most common types of filling mechanism.
public final class FillingMechanisms {

    private static ArrayList<String> mechanismTypes;

    public static ArrayList<String> getMechanismTypes() {
        addMechanismTypes();
        return mechanismTypes;
    }

    private static void addMechanismTypes() {
        mechanismTypes = new ArrayList<>(Arrays.asList("Converter",
                "Cartridge",
                "Piston",
                "Vacuum",
                "Eyedropper",
                "Other"));
    }


}

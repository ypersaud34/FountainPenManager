package PenManager;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * The FillingMechanism class is used to keep information on the various types of filling mechanisms. The mechanisms
 * are stored in an ArrayList and can be accessed without class instantiation. The primary use of this class would be
 * to populate relevant ChoiceBoxes.
 */
public final class FillingMechanisms {
    /**
     * @return an ArrayList with common mechanisms.
     */
    public static ArrayList<String> getMechanismTypes() {
        return new ArrayList<>(Arrays.asList("Converter",
                "Cartridge",
                "Piston",
                "Vacuum",
                "Eyedropper",
                "Other"));
    }

}

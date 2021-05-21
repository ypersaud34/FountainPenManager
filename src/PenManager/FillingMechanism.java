package PenManager;

import java.util.ArrayList;
import java.util.Arrays;

//Used to refer to the most common types of filling mechanism.
public final class FillingMechanism {
    public static final String CONVERTER = "Converter";
    public static final String CARTRIDGE = "Cartridge";
    public static final String PISTON = "Piston";
    public static final String VACUUM = "Vacuum";
    public static final String EYEDROPPER= "Eyedropper";

    public static ArrayList<String> getMechanismTypes(){
        return new ArrayList<>(Arrays.asList(CONVERTER,CARTRIDGE,PISTON,VACUUM,EYEDROPPER));
    }

}

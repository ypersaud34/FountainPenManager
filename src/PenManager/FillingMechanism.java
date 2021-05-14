package PenManager;

import java.util.ArrayList;

//Used to refer to the most common types of filling mechanism.
public final class FillingMechanism {
    public static final String CONVERTER = "Converter";
    public static final String CARTRIDGE = "Cartridge";
    public static final String PISTON = "Piston";
    public static final String VACUUM = "Vacuum";
    public static final String EYEDROPPER= "Eyedropper";

    public static ArrayList<String> getMechanismTypes(){
        ArrayList<String> mechanismTypes= new ArrayList<>();
        mechanismTypes.add(CONVERTER);
        mechanismTypes.add(CARTRIDGE);
        mechanismTypes.add(PISTON);
        mechanismTypes.add(VACUUM);
        mechanismTypes.add(EYEDROPPER);
        return mechanismTypes;
    }

}

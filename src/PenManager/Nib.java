package PenManager;

import java.util.ArrayList;
import java.util.LinkedList;

//Used to refer to the most common types of nib sizes/types.
public final class Nib {
    public static final String EXTRAFINE = "Extra-Fine";
    public static final String FINE = "Fine";
    public static final String MEDIUM = "Medium";
    public static final String BROAD= "Broad";
    public static final String ITALIC = "Italic";
    public static final String FLEX = "Flex";

    public static ArrayList<String> getNibTypes(){
        ArrayList<String> nibTypes= new ArrayList<>();
        nibTypes.add(EXTRAFINE);
        nibTypes.add(FINE);
        nibTypes.add(MEDIUM);
        nibTypes.add(BROAD);
        nibTypes.add(ITALIC);
        nibTypes.add(FLEX);
        return nibTypes;
    }


}

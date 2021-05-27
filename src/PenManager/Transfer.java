package PenManager;


/**
 * The Transfer class allows a FountainPen object to be accessed by other classes if needed. The object can be stored
 * by accessing the setDataToTransfer method and retrieved by the getDataToTransfer method. Methods are static and
 * public, allowing them to be accessed across different classes.
 */
public class Transfer {

    private static FountainPen penToTransfer;

    /**
     * @return the stored FountainPen object.
     */
    public static FountainPen getDataToTransfer() {
        return penToTransfer;
    }

    /**
     * @param penToTransfer - the pen to store.
     */
    public static void setDataToTransfer(FountainPen penToTransfer) {
        Transfer.penToTransfer = penToTransfer;
    }
}

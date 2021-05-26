package PenManager;

public class Transfer {
    private static FountainPen penToTransfer;

    public static FountainPen getPenToTransfer() {
        return penToTransfer;
    }

    public static void setDataToTransfer(FountainPen penToTransfer) {
        Transfer.penToTransfer = penToTransfer;
    }
}

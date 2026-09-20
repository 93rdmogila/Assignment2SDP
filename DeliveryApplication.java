import FactoryMethod.*;
import AbstractFactory.*;
public class DeliveryApplication {
    private final Logistics logistics;
    private final Button button;
    private final CheckBox checkbox;
    public DeliveryApplication(Logistics logistics, GUIFactory guiFactory) {
        this.logistics = logistics;
        this.button = guiFactory.createButton();
        this.checkbox = guiFactory.createCheckBox();
    }
    public void run(String cargo, String destination) {
        button.paint();
        checkbox.paint();
        logistics.planDelivery(cargo, destination);
    }
}
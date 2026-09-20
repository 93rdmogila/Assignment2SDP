package AbstractFactory;

public class MacCheckBox implements CheckBox{
    @Override 
    public void paint(){
        System.out.println("Rendering Mac CheckBox");
    }
}

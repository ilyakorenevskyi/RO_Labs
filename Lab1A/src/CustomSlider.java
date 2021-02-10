import javax.swing.*;

public class CustomSlider extends JSlider{
    CustomSlider(int min, int max, int initialValue){
        super(min,max,initialValue);
    }
    public synchronized int syncGetValue(){
        return getValue();
    }
    public synchronized void syncSetValue(int value){
        setValue(value);
    }
    public synchronized void addValue(int value){
        setValue(getValue()+value);
    }
}

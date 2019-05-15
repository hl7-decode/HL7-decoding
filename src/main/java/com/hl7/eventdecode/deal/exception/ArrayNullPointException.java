package app.test.exception;

/**
 * ArrayNullPointException
 */
public class ArrayNullPointException extends Exception {

    public ArrayNullPointException(){
        super("超出范围，请核实相关信息");
    }
}
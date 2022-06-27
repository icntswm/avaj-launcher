package avajLauncher.Exceptions;

public class ExtraArgumentException extends Exception{
    public ExtraArgumentException(String str, int number) {
        super("Лишний аргумент в строке '" + str +"'(№" + number + ")!");
    }
}

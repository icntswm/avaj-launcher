package avajLauncher.Exceptions;

public class EmptyFileException extends Exception{
    public EmptyFileException() {
        super("Пустой файл");
    }
}

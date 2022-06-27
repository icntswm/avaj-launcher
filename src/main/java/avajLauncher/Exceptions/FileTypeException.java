package avajLauncher.Exceptions;

public class FileTypeException extends Exception{
    public FileTypeException() {
        super("Неверный тип файла. Поддерживаются типы: [.txt] [.md5]");
    }
}

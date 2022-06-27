package avajLauncher.Exceptions;

public class MD5Exception extends Exception{
    public MD5Exception(String md5, int number) {
        super("Неизвестный хэш-ключ '" + md5 + "'(№" + number + ")");
    }
}

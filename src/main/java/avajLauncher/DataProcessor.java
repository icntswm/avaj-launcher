package avajLauncher;

import avajLauncher.Aircraft.AircraftFactory;
import avajLauncher.Aircraft.Flyable;
import avajLauncher.Exceptions.EmptyFileException;
import avajLauncher.Exceptions.ExtraArgumentException;
import avajLauncher.Exceptions.FileTypeException;
import avajLauncher.Exceptions.MD5Exception;

import java.io.*;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DataProcessor {
    private static int numberOfSimulations;
    private static List<Flyable> flyables;
    private static Boolean itMD5 = false;
    private static FileInputStream fin = null;
    private static FileOutputStream fout = null;

    public static HashMap<String, String> md5Hash = new HashMap<>(){{
        put("2ab8b43468e8b92b0fc5c81e70e35a2d", "Helicopter");
        put("554cd647d6b135f7e36ab1214c5e816a", "JetPlane");
        put("994736b4f0aec72f6e5ae580051d012f", "Baloon");
    }};

    public static String checkMD5(String md5, int numStr) throws MD5Exception {
        if (!md5Hash.containsKey(md5))
            throw new MD5Exception(md5, numStr);
        return md5Hash.get(md5);
    }
    public static void parseData(ArrayList<String> data) {
        for (int i = 0; i < data.size(); ++i) {
            if (i == 0) {
                try {
                    numberOfSimulations = Integer.parseInt(data.get(0).trim());
                } catch (NumberFormatException e) {
                    System.err.println("Невозможно преобразовать '" + data.get(0).trim() + "' в число.");
                }
                flyables = new ArrayList<>(data.size() - 1);
                continue;
            }
            String[] arrStrData = data.get(i).trim().split("\\s+");
            try {
                if (arrStrData.length > 5)
                    throw new ExtraArgumentException(data.get(i), i);
                if (itMD5)
                    flyables.add(AircraftFactory.newAircraft(checkMD5(arrStrData[0], i + 1), arrStrData[1], Integer.parseInt(arrStrData[2]), Integer.parseInt(arrStrData[3]), Integer.parseInt(arrStrData[4])));
                else
                    flyables.add(AircraftFactory.newAircraft(arrStrData[0], arrStrData[1], Integer.parseInt(arrStrData[2]), Integer.parseInt(arrStrData[3]), Integer.parseInt(arrStrData[4])));
            } catch (NumberFormatException e) {
                System.err.println("Невозможно преобразовать значение в строке'" + data.get(i).trim() + "'(№" + (i + 1) + ") в число.");
                System.exit(1);
            } catch (ArrayIndexOutOfBoundsException e) {
                System.err.println("В строке '" + data.get(i).trim() + "'(№" + (i + 1) + ") не хватает данных.");
                System.exit(1);
            } catch (ExtraArgumentException e) {
                System.err.println(e.getMessage());
                System.exit(1);
            } catch (MD5Exception e) {
                System.err.println(e.getMessage());
                System.exit(1);
            }
        }
    }
    private static String cutTypeFile(String file) {
        int i = file.length() - 1;
        for (; i >= 0 && file.charAt(i) != '.'; --i);
        if (i == 0)
            return null;
        return file.substring(i);
    }
    public static void initData(String file) {
        ArrayList<String> dataFromScenario = new ArrayList<>();
        String type = cutTypeFile(file);

        try {
            if (type == null || (!type.equals(".txt") && !type.equals(".md5")))
                throw new FileTypeException();
            if (type.equals(".md5"))
                itMD5 = true;
            fin = new FileInputStream(file);
            int c = -1;
            StringBuilder str = new StringBuilder();
            Boolean fileIsEmpty = true;
            while ((c = fin.read()) != -1) {
                fileIsEmpty = false;
                if ((char) c == '\n') {
                    dataFromScenario.add(String.valueOf(str));
                    str = new StringBuilder();
                    continue;
                }
                str.append((char) c);
            }
            if (!str.isEmpty())
                dataFromScenario.add(String.valueOf(str));
            if (fileIsEmpty)
                throw new EmptyFileException();
        } catch (IOException e) {
            System.err.println("Не удалось найти файл '" + file + "'");
            System.exit(1);
        } catch (FileTypeException e) {
            System.err.println(e.getMessage());
            System.exit(1);
        } catch (EmptyFileException e) {
            System.err.println(e.getMessage());
            System.exit(1);
        }
        parseData(dataFromScenario);
    }
    public static void redirectingOutputToFile() {
        try {
            fout = new FileOutputStream("simulation.txt");
            PrintStream out = new PrintStream(fout);
            System.setOut(out);
        } catch (FileNotFoundException e) {
            System.err.println("Не удалось создать файл 'simulation.txt'");
        }
    }
    public static String encryptionMD5(String s){
        MessageDigest m = null;
        try {
            m = MessageDigest.getInstance("MD5");
        } catch (Exception e) {
            e.printStackTrace();
        }
        m.update(s.getBytes(),0,s.length());
        return new BigInteger(1,m.digest()).toString(16);
    }
    public static void closeFiles() {
        try {
            if (fin != null)
                fin.close();
            if (fout != null)
                fout.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<Flyable> getFlyables() {
        return flyables;
    }
    public static int getNumberOfSimulations() {
        return numberOfSimulations;
    }
}

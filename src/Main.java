import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        String gamesPath = "/Users/andrejzemlacev/Desktop/Games/";
        StringBuilder log = new StringBuilder();

        if (prepareInstallDir(gamesPath)) {
            addToLog(log, "Начальная директория подготовлена");
            createDir(gamesPath, new String[]{"src", "res", "savegames", "temp"}, log);
            createDir(gamesPath + "src/", new String[]{"main", "test"}, log);
            createFile(gamesPath + "src/main/", "Main.java", log);
            createFile(gamesPath + "src/main/", "Utils.java", log);
            createDir(gamesPath + "res/", new String[]{"drawables", "vectors", "icons"}, log);
            createFile(gamesPath + "temp/", "temp.txt", log);
            saveLogs(gamesPath + "temp/temp.txt", log);
        } else {
            addToLog(log, "Начальная директория не была подготовлена, проверьте пути и попробуйте снова!");
        }
    }

    private static void saveLogs(String path, StringBuilder log) {
        File file = new File(path);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write(log.toString());
            System.out.println("Лог успешно записан!");
        } catch (IOException e) {
            System.out.println("Ошибка записи лога: " + e.getMessage());
        }
    }

    public static void createDir(String path, String[] directories, StringBuilder log) {
        for (String dir : directories) {
            File file = new File(path + dir);
            if (!file.exists()) {
                if (file.mkdir()) {
                    addToLog(log, "Создана папка " + file.getAbsolutePath());
                } else {
                    addToLog(log, "Папка " + file.getAbsolutePath() + " не была создана!");
                }
            }
        }
    }

    public static void createFile(String path, String fileName, StringBuilder log) {
        File newFile = new File(path, fileName);
        try {
            boolean created = newFile.createNewFile();
            if (created)
                addToLog(log, "Создан файл " + newFile.getAbsolutePath());
        } catch (IOException e) {
            addToLog(log, "Ошибка создания файла " + fileName + " в дирекории " + path + ": " + e.getMessage());
        }
    }

    public static boolean prepareInstallDir(String path) {
        File file = new File(path);
        return file.mkdirs();
    }

    public static void addToLog(StringBuilder log, String text) {
        log.append(text).append("\n");
        System.out.println(text);
    }
}

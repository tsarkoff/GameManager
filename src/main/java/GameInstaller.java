import java.io.*;

public class GameInstaller {
    public static void install() {
        // check if root directory "Games" is available
        if (!checkRootFolder()) {
            return;
        }
        // create folders
        for (String dir : GameFileStruct.dirs) {
            if (!createDir(GameFileStruct.ROOT_DIR + dir)) {
                return;
            }
        }
        // create files
        for (String file : GameFileStruct.files) {
            if (!createFile(GameFileStruct.ROOT_DIR + file)) {
                return;
            }
        }
        // записать лог установки в лог файл
        GameLogger.flushToLogFile();
    }

    private static boolean checkRootFolder() {
        File dir = new File(GameFileStruct.ROOT_DIR);
        if (dir.isDirectory()) {
            GameLogger.writeLog("Корневой каталог: " + dir.getAbsolutePath() + " найден. Продолжаем установку!", GameLogger.INFO);
            return true;
        }
        GameLogger.writeLog("Каталог " + dir.getAbsolutePath() + " отсутствует. Дальнейшая установка невозможна. Создайте каталог вручную и перезапустите установку.", GameLogger.FATAL);
        return false;
    }

    private static boolean createDir(String directory) {
        File d = new File(directory);
        if (!d.isDirectory() || !d.exists()) {
            if (!d.mkdir()) {
                GameLogger.writeLog("Логическая ошибка создания каталога: " + d.getPath(), GameLogger.FATAL);
                return false;
            }
        }
        GameLogger.writeLog("Каталог уже существует или успешно создан: " + d.getPath(), GameLogger.INFO);
        return true;
    }

    private static boolean createFile(String file) {
        File f = new File(file);
        if (!f.isFile() || !f.exists()) {
            try {
                if (!f.createNewFile()) {
                    throw new IOException("Логическая ошибка создания файла");
                }
            } catch (IOException e) {
                GameLogger.writeLog("Ошибка создания файла: " + f.getPath() + "(" + e.getMessage() + ")", GameLogger.FATAL);
                return false;
            }
        }
        GameLogger.writeLog("Файл уже существует или успешно создан: " + f.getPath(), GameLogger.INFO);
        return true;
    }
}

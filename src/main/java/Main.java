import java.io.File;
import static java.lang.String.format;

public class Main {
    public static final int GAME_STATE_COUNT = 3;

    public static void main(String[] args) {
        System.out.println("====== СОЗДАНИЕ КАТАЛОГОВ И ФАЙЛОВ =======");
        GameInstaller.install();

        System.out.println("\n====== СОХРАНЕНИЕ СНИМКОВ ИГРЫ В dat ФАЙЛЫ =======");
        String saveDirAbsPath =
                new File(GameFileStruct.ROOT_DIR + GameFileStruct.SAVEGAMES_DIR)
                        .getAbsoluteFile()
                        + File.separator;
        String[] datFileNames = new String[GAME_STATE_COUNT];
        for (int i = 1; i <= GAME_STATE_COUNT; i++) {
            GameProgress gp = new GameProgress(i * 10, i * 10_000, i * 111, Math.round(i * 11F));
            datFileNames[i - 1] = saveDirAbsPath + format(GameFileStruct.DAT_FILE, i);
            GameSaver.saveGame(datFileNames[i - 1], gp);

            System.out.println("СНИМОК : " + i + "\n" + i + " Создан снимок GameProgress : " + gp);
            System.out.println(i + " Создан путь к dat файлу : " + datFileNames[i - 1]);
            System.out.println(i + " Сохранен снимок Игры в dat файл : " + format(GameFileStruct.DAT_FILE, i));
        }

        System.out.println("\n====== УПАКОВКА СНИМКОВ ИГРЫ В zip ФАЙЛ =======");
        String zipFileName = saveDirAbsPath + GameFileStruct.ZIP_FILE;
        GameSaver.zipFiles(zipFileName, datFileNames);
        System.out.println("Создан zip файл : " + zipFileName);

        System.out.println("\n====== УДАЛЕНИЕ dat ФАЙЛОВ СНИМКОВ ИГРЫ =======");
        for (String datFileName : datFileNames) {
            if (!new File(datFileName).delete()) {
                System.out.println("Ошибка удаления dat файла : " + datFileName);
            } else {
                System.out.println("Файл dat удален : " + datFileName);
            }
        }

        System.out.println("\n====== РАСПАКОВКА АРХИВА с dat ФАЙЛАМИ =======");
        GameSaver.openZip(zipFileName, saveDirAbsPath);
        System.out.println("Файлы dat распакованы в каталог : " + saveDirAbsPath);

        System.out.println("\n====== ЗАГРУЗКА СНИМКА ИГРЫ ИЗ dat ФАЙЛА =======");
        int datFileNumber = 2;
        GameProgress gpLoaded = GameSaver.openProgress(saveDirAbsPath + format(GameFileStruct.DAT_FILE, datFileNumber));
        System.out.println("Снимок Игры загружен из файла " + format(GameFileStruct.DAT_FILE, datFileNumber) + " : " + gpLoaded);
    }
}

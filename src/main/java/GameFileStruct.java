// ROOT FOLDER = directory "Games" (in the current Project Root Folder)
public final class GameFileStruct {
    public static final String ROOT_DIR = "Games/";
    public static final String LOG_FILE = "temp/temp.txt";
    public static final String SAVEGAMES_DIR = "savegames";
    public static final String ZIP_FILE = "packed_game.zip";
    public static String[] dirs = {"src", "src/main", "src/test", "res", "res/drawables", "res/vectors", "res/icons", SAVEGAMES_DIR, "temp"};
    public static String[] files = {"src/main/Main.java", "src/main/Utils.java", LOG_FILE};
    public static final String DAT_FILE = "save%s.dat";
}

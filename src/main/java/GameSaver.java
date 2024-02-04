import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class GameSaver {
    public static void saveGame(String datFileAbsolutePath, GameProgress gp) {
        try (FileOutputStream fos = new FileOutputStream(datFileAbsolutePath);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(gp);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }

    public static void zipFiles(String zipFileAbsolutePath, String[] packFilesAbsolutePath) {
        try (ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(zipFileAbsolutePath))) {
            for (String packFile : packFilesAbsolutePath) {
                try (FileInputStream fis = new FileInputStream(packFile)) {
                    File f = new File(packFile);
                    String fileName = f.getName();
                    ZipEntry entry = new ZipEntry(fileName);
                    zos.putNextEntry(entry);
                    byte[] buffer = new byte[fis.available()];
                    fis.read(buffer);
                    zos.write(buffer);
                    zos.closeEntry();
                    //System.out.println("ZipOutputStream: " + Arrays.toString(buffer));
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void openZip(String zipFileAbsolutePath, String zipDirAbsolutePath) {
        try (ZipInputStream zis = new ZipInputStream(new FileInputStream(zipFileAbsolutePath))) {
            ZipEntry entry;
            String datFileAbsNamePath;
            while ((entry = zis.getNextEntry()) != null) {
                datFileAbsNamePath = zipDirAbsolutePath + entry.getName();
                FileOutputStream fos = new FileOutputStream(datFileAbsNamePath);
                for (int c = zis.read(); c != -1; c = zis.read()) {
                    fos.write(c);
                }
                fos.flush();
                fos.close();
                zis.closeEntry();
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static GameProgress openProgress(String datFileAbsolutePath) {
        GameProgress gp = null;
        try (FileInputStream fis = new FileInputStream(datFileAbsolutePath);
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            gp = (GameProgress) ois.readObject();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return gp;
    }
}

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileService {
    private String folderOutput;

    public FileService(String folderOutput) {
        this.folderOutput = folderOutput;
        new File(folderOutput).mkdirs();
    }

    public void simpanFile(String path, String konten) {
        try {
            FileWriter writer = new FileWriter(path);
            writer.write(konten);
            writer.close();
            System.out.println("✓ Slip gaji disimpan: " + path);
        } catch (IOException e) {
            System.out.println("✗ Gagal menyimpan file: " + e.getMessage());
        }
    }

    public String getFolderOutput() {
        return folderOutput;
    }
}
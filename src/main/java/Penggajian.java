import java.util.List;

public class Penggajian {
    private String periode;
    private FileService fileService;

    public Penggajian(String periode, String folderOutput) {
        this.periode = periode;
        this.fileService = new FileService(folderOutput);
    }

    public void prosesSemuaPenggajian(Company company) {
        List<Pegawai> daftarPegawai = company.getDaftarPegawai();

        System.out.println("=========================================");
        System.out.println("   PENGGAJIAN " + periode.toUpperCase() + " - " + company.getNamaPerusahaan());
        System.out.println("=========================================");

        for (Pegawai p : daftarPegawai) {
            String slip = p.generateSlipGaji();
            System.out.println(slip);
            System.out.println();

            String filename = fileService.getFolderOutput() + "/slip_" + p.getId() + ".txt";
            fileService.simpanFile(filename, slip);

            if (p instanceof PegawaiHarian) {
                PegawaiHarian ph = (PegawaiHarian) p;
                System.out.println("--- SLIP GAJI MINGGUAN untuk " + ph.getNama() + " ---");

                for (int minggu = 1; minggu <= 4; minggu++) {
                    int hariKerja = (minggu % 2 == 0) ? 5 : 6;
                    String slipMingguan = ph.generateSlipGajiMingguan(minggu, hariKerja);
                    System.out.println(slipMingguan);

                    String filenameMingguan = fileService.getFolderOutput() +
                            "/slip_mingguan_" + ph.getId() + "_minggu" + minggu + ".txt";
                    fileService.simpanFile(filenameMingguan, slipMingguan);
                }
                System.out.println();
            }
        }
    }
}
import java.util.List;

public class Penggajian {
    private String periode;
    private FileService fileService;
    private Company company;  // Company dibuat internal di Penggajian

    public Penggajian(String periode, String folderOutput) {
        this.periode = periode;
        this.fileService = new FileService(folderOutput);
        this.company = new Company("Filkom Berkarya");  // Buat Company di sini
        setupPegawai();  // Setup semua pegawai
    }

    // Setup semua pegawai di dalam Penggajian
    private void setupPegawai() {
        // Pegawai Biasa
        company.tambahPegawai(new Pegawai("P001", "Budi Santoso", "Staff", 4500000));
        company.tambahPegawai(new Pegawai("P002", "Siti Rahayu", "Staff", 4200000));
        company.tambahPegawai(new Pegawai("P003", "Eko Prasetyo", "Admin", 4800000));

        // Manager
        company.tambahPegawai(new Manager("M001", "Ahmad Fauzi", 8500000, 6, 700000));
        company.tambahPegawai(new Manager("M002", "Dewi Sartika", 9000000, 4, 750000));

        // Sales
        company.tambahPegawai(new Sales("S001", "Citra Dewi", 4000000, 28000000, 0.1));
        company.tambahPegawai(new Sales("S002", "Rizky Pratama", 3800000, 12000000, 0.1));

        // Pegawai Harian
        company.tambahPegawai(new PegawaiHarian("H001", "Doni Prasetyo", 85000, 22));
        company.tambahPegawai(new PegawaiHarian("H002", "Mila Sari", 75000, 18));
        company.tambahPegawai(new PegawaiHarian("H003", "Agus Santoso", 90000, 25));
    }

    // Method baru - tanpa parameter Company
    public void prosesSemuaPenggajian() {
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

    public Company getCompany() {
        return company;
    }
}
public class PenggajianAPP {
    public static void main(String[] args) {
        Company company = new Company("Filkom Berkarya");

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

        Penggajian penggajian = new Penggajian("Mei 2026", "output");

        penggajian.prosesSemuaPenggajian(company);
    }
}
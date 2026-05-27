import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Test Unit - Sistem Penggajian Filkom Berkarya")
public class TestUnit {
    private Pegawai pegawaiUmum1;
    private Pegawai pegawaiUmum2;
    private Manager manager1;
    private Manager manager2;
    private Sales sales1;
    private Sales sales2;
    private PegawaiHarian pegawaiHarian1;
    private Company company;
    private Penggajian penggajian;

    @BeforeEach
    public void setUp() {
        pegawaiUmum1 = new Pegawai("P001", "Budi Santoso", "Staff", 4500000);
        pegawaiUmum2 = new Pegawai("P002", "Siti Rahayu", "Staff", 4200000);

        manager1 = new Manager("M001", "Ahmad Fauzi", 8500000, 6, 700000);
        manager2 = new Manager("M002", "Dewi Sartika", 9000000, 4, 750000);

        sales1 = new Sales("S001", "Citra Dewi", 4000000, 28000000, 0.1);
        sales2 = new Sales("S002", "Rizky Pratama", 3800000, 12000000, 0.1);

        pegawaiHarian1 = new PegawaiHarian("H001", "Doni Prasetyo", 85000, 22);

        company = new Company("Filkom Berkarya");
        penggajian = new Penggajian("Mei 2026", "output");
    }

    @Test
    @DisplayName("Pegawai Umum - Hitung Gaji Pokok")
    public void testPegawaiUmumHitungGajiPokok() {
        assertEquals(4500000, pegawaiUmum1.hitungGaji());
    }

    @Test
    @DisplayName("Pegawai Umum - Generate Slip Gaji")
    public void testPegawaiUmumGenerateSlipGaji() {
        String slip = pegawaiUmum1.generateSlipGaji();
        assertNotNull(slip);
        assertTrue(slip.contains("Budi Santoso"));
        assertTrue(slip.contains("Staff"));
    }

    @Test
    @DisplayName("Manager - Hitung Total Gaji")
    public void testManagerHitungTotalGaji() {
        double expected = 8500000 + (6 * 700000);
        assertEquals(expected, manager1.hitungGaji());
    }

    @Test
    @DisplayName("Sales - Target Tercapai & Hitung Gaji")
    public void testSalesTargetTercapaiHitungGaji() {
        assertTrue(sales1.targetTercapai());
        double expected = 4000000 + (28000000 * 0.1);
        assertEquals(expected, sales1.hitungGaji());
    }

    @Test
    @DisplayName("Pegawai Harian - Hitung Gaji Bulanan")
    public void testPegawaiHarianHitungGajiPokok() {
        assertEquals(85000 * 22, pegawaiHarian1.hitungGaji());
    }

    @Test
    @DisplayName("Pegawai Harian - Hitung Gaji Mingguan")
    public void testPegawaiHarianHitungGajiMingguan() {
        assertEquals(85000 * 5, pegawaiHarian1.hitungGajiMingguan(5));
    }

    @Test
    @DisplayName("Company - Tambah dan Hitung Pegawai")
    public void testCompanyTambahPegawai() {
        company.tambahPegawai(pegawaiUmum1);
        company.tambahPegawai(manager1);
        company.tambahPegawai(sales1);
        company.tambahPegawai(pegawaiHarian1);
        assertEquals(4, company.getDaftarPegawai().size());
    }

    @Test
    @DisplayName("Penggajian - Proses Penggajian (Tanpa Parameter Company)")
    public void testPenggajianProsesSemuaPenggajian() {
        // Karena Penggajian sudah memiliki Company internal
        assertDoesNotThrow(() -> penggajian.prosesSemuaPenggajian());
    }

    @Test
    @DisplayName("Penggajian - Proses dengan Pegawai Harian (4 Slip Mingguan)")
    public void testPenggajianWithPegawaiHarian() {
        company.tambahPegawai(pegawaiHarian1);
        assertDoesNotThrow(() -> penggajian.prosesSemuaPenggajian());
    }
}
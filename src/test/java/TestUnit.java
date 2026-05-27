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
        // Pegawai Umum
        pegawaiUmum1 = new Pegawai("P001", "Budi Santoso", "Staff", 4500000);
        pegawaiUmum2 = new Pegawai("P002", "Siti Rahayu", "Staff", 4200000);

        // Manager
        manager1 = new Manager("M001", "Ahmad Fauzi", 8500000, 6, 700000);
        manager2 = new Manager("M002", "Dewi Sartika", 9000000, 4, 750000);

        // Sales
        sales1 = new Sales("S001", "Citra Dewi", 4000000, 28000000, 0.1);
        sales2 = new Sales("S002", "Rizky Pratama", 3800000, 12000000, 0.1);

        // Pegawai Harian
        pegawaiHarian1 = new PegawaiHarian("H001", "Doni Prasetyo", 85000, 22);

        // Company dan Penggajian
        company = new Company("Filkom Berkarya");
        penggajian = new Penggajian("Mei 2026", "output");
    }

    // ===== TEST PEGAWAI UMUM =====
    @Test
    @DisplayName("Pegawai Umum - Hitung Gaji Pokok")
    public void testPegawaiUmumHitungGajiPokok() {
        assertEquals(4500000, pegawaiUmum1.hitungGaji());
    }

    @Test
    @DisplayName("Pegawai Umum - Get ID")
    public void testPegawaiUmumGetId() {
        assertEquals("P001", pegawaiUmum1.getId());
    }

    @Test
    @DisplayName("Pegawai Umum - Get Nama")
    public void testPegawaiUmumGetNama() {
        assertEquals("Budi Santoso", pegawaiUmum1.getNama());
    }

    @Test
    @DisplayName("Pegawai Umum - Get Jabatan")
    public void testPegawaiUmumGetJabatan() {
        assertEquals("Staff", pegawaiUmum1.getJabatan());
    }

    @Test
    @DisplayName("Pegawai Umum - Generate Slip Gaji")
    public void testPegawaiUmumGenerateSlipGaji() {
        String slip = pegawaiUmum1.generateSlipGaji();
        assertNotNull(slip);
        assertTrue(slip.contains("P001"));
        assertTrue(slip.contains("Budi Santoso"));
        assertTrue(slip.contains("Staff"));
    }

    // ===== TEST MANAGER =====
    @Test
    @DisplayName("Manager - Hitung Gaji Pokok")
    public void testManagerHitungGajiPokok() {
        assertEquals(8500000, manager1.getGajiPokok());
    }

    @Test
    @DisplayName("Manager - Hitung Tunjangan (6 bawahan × 700K)")
    public void testManagerHitungTunjangan() {
        double expected = 6 * 700000;
        assertEquals(expected, manager1.hitungTunjangan());
    }

    @Test
    @DisplayName("Manager - Hitung Total Gaji (Pokok + Tunjangan)")
    public void testManagerHitungTotalGaji() {
        double expected = 8500000 + (6 * 700000);
        assertEquals(expected, manager1.hitungGaji());
    }

    @Test
    @DisplayName("Manager - Get Jumlah Bawahan")
    public void testManagerGetJumlahBawahan() {
        assertEquals(6, manager1.getJumlahBawahan());
    }

    @Test
    @DisplayName("Manager - Generate Slip Gaji")
    public void testManagerGenerateSlipGaji() {
        String slip = manager1.generateSlipGaji();
        assertNotNull(slip);
        assertTrue(slip.contains("M001"));
        assertTrue(slip.contains("Ahmad Fauzi"));
        assertTrue(slip.contains("Manager"));
    }

    // ===== TEST SALES (TARGET TERCAPAI) =====
    @Test
    @DisplayName("Sales - Target Tercapai Check")
    public void testSalesTargetTercapai() {
        assertTrue(sales1.targetTercapai(), "Sales1 seharusnya tercapai (28jt >= 15jt)");
    }

    @Test
    @DisplayName("Sales (Target Tercapai) - Hitung Bonus")
    public void testSalesTargetTercapaiHitungBonus() {
        double expected = 28000000 * 0.1;
        assertEquals(expected, sales1.hitungBonus());
    }

    @Test
    @DisplayName("Sales (Target Tercapai) - Hitung Total Gaji")
    public void testSalesTargetTercapaiHitungGaji() {
        double expected = 4000000 + (28000000 * 0.1);
        assertEquals(expected, sales1.hitungGaji());
    }

    @Test
    @DisplayName("Sales (Target Tercapai) - Generate Slip Gaji")
    public void testSalesTargetTercapaiGenerateSlipGaji() {
        String slip = sales1.generateSlipGaji();
        assertNotNull(slip);
        assertTrue(slip.contains("TERCAPAI"));
        assertTrue(slip.contains("Citra Dewi"));
    }

    // ===== TEST SALES (TARGET TIDAK TERCAPAI) =====
    @Test
    @DisplayName("Sales - Target Tidak Tercapai Check")
    public void testSalesTargetTidakTercapai() {
        assertFalse(sales2.targetTercapai(), "Sales2 seharusnya tidak tercapai (12jt < 15jt)");
    }

    @Test
    @DisplayName("Sales (Target Tidak Tercapai) - Bonus = 0")
    public void testSalesTargetTidakTercapaiHitungBonus() {
        assertEquals(0, sales2.hitungBonus());
    }

    @Test
    @DisplayName("Sales (Target Tidak Tercapai) - Hitung Total Gaji")
    public void testSalesTargetTidakTercapaiHitungGaji() {
        double expected = 3800000;
        assertEquals(expected, sales2.hitungGaji());
    }

    // ===== TEST PEGAWAI HARIAN =====
    @Test
    @DisplayName("Pegawai Harian - Hitung Gaji Pokok (85K × 22 hari)")
    public void testPegawaiHarianHitungGajiPokok() {
        assertEquals(85000 * 22, pegawaiHarian1.hitungGaji());
    }

    @Test
    @DisplayName("Pegawai Harian - Hitung Gaji Mingguan (85K × 5 hari)")
    public void testPegawaiHarianHitungGajiMingguan() {
        assertEquals(85000 * 5, pegawaiHarian1.hitungGajiMingguan(5));
    }

    @Test
    @DisplayName("Pegawai Harian - Hitung Gaji Mingguan Berbeda (85K × 6 hari)")
    public void testPegawaiHarianHitungGajiMingguanBerbeda() {
        assertEquals(85000 * 6, pegawaiHarian1.hitungGajiMingguan(6));
    }

    @Test
    @DisplayName("Pegawai Harian - Generate Slip Gaji Bulanan")
    public void testPegawaiHarianGenerateSlipGaji() {
        String slip = pegawaiHarian1.generateSlipGaji();
        assertNotNull(slip);
        assertTrue(slip.contains("H001"));
        assertTrue(slip.contains("Doni Prasetyo"));
        assertTrue(slip.contains("Pegawai Harian Lepas"));
    }

    @Test
    @DisplayName("Pegawai Harian - Generate Slip Gaji Mingguan")
    public void testPegawaiHarianGenerateSlipGajiMingguan() {
        String slip = pegawaiHarian1.generateSlipGajiMingguan(1, 5);
        assertNotNull(slip);
        assertTrue(slip.contains("Minggu 1"));
        assertTrue(slip.contains("H001"));
    }

    // ===== TEST COMPANY =====
    @Test
    @DisplayName("Company - Get Nama Perusahaan")
    public void testCompanyGetNamaPerusahaan() {
        assertEquals("Filkom Berkarya", company.getNamaPerusahaan());
    }

    @Test
    @DisplayName("Company - Tambah Pegawai")
    public void testCompanyTambahPegawai() {
        company.tambahPegawai(pegawaiUmum1);
        assertEquals(1, company.getDaftarPegawai().size());
    }

    @Test
    @DisplayName("Company - Tambah Multiple Pegawai")
    public void testCompanyTambahMultiplePegawai() {
        company.tambahPegawai(pegawaiUmum1);
        company.tambahPegawai(manager1);
        company.tambahPegawai(sales1);
        assertEquals(3, company.getDaftarPegawai().size());
    }

    @Test
    @DisplayName("Company - Get Daftar Pegawai (Empty)")
    public void testCompanyGetDaftarPegawaiEmpty() {
        assertTrue(company.getDaftarPegawai().isEmpty());
    }

    @Test
    @DisplayName("Company - Get Daftar Pegawai (Not Null)")
    public void testCompanyGetDaftarPegawaiNotNull() {
        assertNotNull(company.getDaftarPegawai());
    }

    // ===== TEST PENGGAJIAN =====
    @Test
    @DisplayName("Penggajian - Proses Penggajian dengan Multiple Jenis Pegawai")
    public void testPenggajianProsesSemuaPenggajian() {
        company.tambahPegawai(pegawaiUmum1);
        company.tambahPegawai(manager1);
        company.tambahPegawai(sales1);
        company.tambahPegawai(pegawaiHarian1);

        // Tidak throw exception = test passed
        assertDoesNotThrow(() -> penggajian.prosesSemuaPenggajian(company));
    }

    @Test
    @DisplayName("Penggajian - Proses dengan PegawaiHarian (Auto Generate 4 Slip Mingguan)")
    public void testPenggajianWithPegawaiHarian() {
        company.tambahPegawai(pegawaiHarian1);
        assertDoesNotThrow(() -> penggajian.prosesSemuaPenggajian(company));
    }
}
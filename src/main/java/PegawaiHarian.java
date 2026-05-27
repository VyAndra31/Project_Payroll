public class PegawaiHarian extends Pegawai {

    private double tarifHarian;
    private int jumlahHariKerja;

    public PegawaiHarian(String id, String nama, double tarifHarian, int jumlahHariKerja) {
        super(id, nama, "Pegawai Harian Lepas", tarifHarian * jumlahHariKerja);
        this.tarifHarian = tarifHarian;
        this.jumlahHariKerja = jumlahHariKerja;
    }

    @Override
    public double hitungGaji() {
        return tarifHarian * jumlahHariKerja;
    }

    public double hitungGajiMingguan(int hariKerjaMingguIni) {
        return tarifHarian * hariKerjaMingguIni;
    }

    @Override
    public String generateSlipGaji() {
        return super.generateSlipGaji() +
                "\nTarif Harian     : Rp " + String.format("%,.2f", tarifHarian) +
                "\nHari Kerja       : " + jumlahHariKerja + " hari" +
                "\n--------------------------------------------" +
                "\nTotal Gaji       : Rp " + String.format("%,.2f", hitungGaji()) + "\n";
    }

    public String generateSlipGajiMingguan(int mingguKe, int hariKerjaMingguIni) {
        return "============================================\n" +
                "      SLIP GAJI MINGGUAN (Minggu " + mingguKe + ")      \n" +
                "============================================\n" +
                "ID Pegawai    : " + getId() + "\n" +
                "Nama          : " + getNama() + "\n" +
                "Jabatan       : " + getJabatan() + "\n" +
                "--------------------------------------------\n" +
                "Tarif Harian  : Rp " + String.format("%,.2f", tarifHarian) + "\n" +
                "Hari Kerja    : " + hariKerjaMingguIni + " hari\n" +
                "--------------------------------------------\n" +
                "Total Gaji    : Rp " + String.format("%,.2f", hitungGajiMingguan(hariKerjaMingguIni)) + "\n" +
                "============================================\n";
    }

    public double getTarifHarian() { return tarifHarian; }
    public int getJumlahHariKerja() { return jumlahHariKerja; }
}
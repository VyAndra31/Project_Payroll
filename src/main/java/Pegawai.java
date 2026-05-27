public class Pegawai {
    protected String id;
    protected String nama;
    protected String jabatan;
    protected double gajiPokok;

    public Pegawai(String id, String nama, String jabatan, double gajiPokok) {
        this.id = id;
        this.nama = nama;
        this.jabatan = jabatan;
        this.gajiPokok = gajiPokok;
    }

    public double hitungGaji() {
        return gajiPokok;
    }

    public String generateSlipGaji() {
        return "============================================\n" +
                "                SLIP GAJI                    \n" +
                "============================================\n" +
                "ID Pegawai    : " + id + "\n" +
                "Nama          : " + nama + "\n" +
                "Jabatan       : " + jabatan + "\n" +
                "Gaji Pokok    : Rp " + String.format("%,.2f", gajiPokok) + "\n" +
                "--------------------------------------------\n" +
                "Total Gaji    : Rp " + String.format("%,.2f", hitungGaji()) + "\n";
    }

    public String getId() { return id; }
    public String getNama() { return nama; }
    public String getJabatan() { return jabatan; }
    public double getGajiPokok() { return gajiPokok; }

}
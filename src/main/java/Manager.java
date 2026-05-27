public class Manager extends Pegawai {
    private int jumlahBawahan;
    private double tunjanganPerBawahan;

    public Manager(String id, String nama, double gajiPokok, int jumlahBawahan, double tunjanganPerBawahan) {
        super(id, nama, "Manager", gajiPokok);
        this.jumlahBawahan = jumlahBawahan;
        this.tunjanganPerBawahan = tunjanganPerBawahan;
    }

    public double hitungTunjangan() {
        return jumlahBawahan * tunjanganPerBawahan;
    }

    @Override
    public double hitungGaji() {
        return super.hitungGaji() + hitungTunjangan();
    }

    @Override
    public String generateSlipGaji() {
        return super.generateSlipGaji() +
                "Jumlah Bawahan         : " + jumlahBawahan + " orang\n" +
                "Tunjangan per Bawahan  : Rp " + String.format("%,.2f", tunjanganPerBawahan) + "\n" +
                "Total Tunjangan        : Rp " + String.format("%,.2f", hitungTunjangan()) + "\n" +
                "--------------------------------------------\n" +
                "Total Gaji             : Rp " + String.format("%,.2f", hitungGaji()) + "\n";
    }

    public int getJumlahBawahan() { return jumlahBawahan; }
    public double getTunjanganPerBawahan() { return tunjanganPerBawahan; }
}
public class Sales extends Pegawai {
    private double pendapatanPenjualan;
    private double bonusJikaTargetTercapai;
    private static final double TARGET_PENJUALAN = 15000000;

    public Sales(String id, String nama, double gajiPokok, double pendapatanPenjualan, double bonusJikaTargetTercapai) {
        super(id, nama, "Sales", gajiPokok);
        this.pendapatanPenjualan = pendapatanPenjualan;
        this.bonusJikaTargetTercapai = bonusJikaTargetTercapai;
    }

    public boolean targetTercapai() {
        return pendapatanPenjualan >= TARGET_PENJUALAN;
    }

    public double hitungBonus() {
        return targetTercapai() ? pendapatanPenjualan * bonusJikaTargetTercapai : 0;
    }

    @Override
    public double hitungGaji() {
        return super.hitungGaji() + hitungBonus();
    }

    @Override
    public String generateSlipGaji() {
        String status = targetTercapai() ? "TERCAPAI ✓" : "BELUM TERCAPAI ✗";
        return super.generateSlipGaji() +
                "Pendapatan Penjualan : Rp " + String.format("%,.2f", pendapatanPenjualan) + "\n" +
                "Target Penjualan     : Rp " + String.format("%,.2f", TARGET_PENJUALAN) + "\n" +
                "Status Target        : " + status + "\n" +
                "Bonus                : Rp " + String.format("%,.2f", hitungBonus()) + "\n" +
                "--------------------------------------------\n" +
                "Total Gaji           : Rp " + String.format("%,.2f", hitungGaji()) + "\n";
    }

    public double getPendapatanPenjualan() { return pendapatanPenjualan; }
}
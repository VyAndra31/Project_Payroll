import java.util.ArrayList;
import java.util.List;

public class Company {
    private String namaPerusahaan;
    private List<Pegawai> daftarPegawai;

    public Company(String namaPerusahaan) {
        this.namaPerusahaan = namaPerusahaan;
        this.daftarPegawai = new ArrayList<>();
    }

    public void tambahPegawai(Pegawai p) {
        daftarPegawai.add(p);
    }

    public List<Pegawai> getDaftarPegawai() {
        return daftarPegawai;
    }

    public String getNamaPerusahaan() {
        return namaPerusahaan;
    }
}
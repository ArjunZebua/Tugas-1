package pelatihan1.tes1;

import java.util.ArrayList;
import java.util.Scanner;

class Mahasiswa {
    String nama;
    String nim;
    double tugas, uts, uas, nilaiAkhir;
    char grade;

    public Mahasiswa(String nama, String nim, double tugas, double uts, double uas) {
        this.nama = nama;
        this.nim = nim;
        this.tugas = tugas;
        this.uts = uts;
        this.uas = uas;
        this.nilaiAkhir = hitungNilaiAkhir(tugas, uts, uas);
        this.grade = tentukanGrade(this.nilaiAkhir);
    }

    private double hitungNilaiAkhir(double tugas, double uts, double uas) {
        return (0.3 * tugas) + (0.3 * uts) + (0.4 * uas);
    }

    private char tentukanGrade(double nilai) {
        if (nilai >= 85) return 'A';
        else if (nilai >= 70) return 'B';
        else if (nilai >= 60) return 'C';
        else if (nilai >= 50) return 'D';
        else return 'E';
    }
}

public class AplikasiMahasiswa {
    static ArrayList<Mahasiswa> daftarMahasiswa = new ArrayList<>();
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int pilihan;
        do {
            tampilMenu();
            System.out.print("Pilih menu: ");
            pilihan = scanner.nextInt();
            scanner.nextLine(); 
            switch (pilihan) {
                case  1 :
                inputData();
                break;
                case  2 :
               tampilData();
                break;
                case 3 :
                cariMahasiswa();
                break;
                case 4 :
                hapusMahasiswa();
                break;
                case 0 :
                System.out.println("keluar dari program");
                break;
                default :
                System.out.println("pilihan tidak valid");
            }
        } while (pilihan != 0);
    }

    static void tampilMenu() {
        System.out.println("\n=== MENU APLIKASI MAHASISWA ===");
        System.out.println("1. Input Data Mahasiswa");
        System.out.println("2. Tampilkan Semua Data Mahasiswa");
        System.out.println("3. Cari Mahasiswa (berdasarkan NIM)");
        System.out.println("4. Hapus Mahasiswa (berdasarkan NIM)");
        System.out.println("0. Keluar");
    }

    static void inputData() {
        System.out.print("Masukkan Nama: ");
        String nama = scanner.nextLine();

        System.out.print("Masukkan NIM: ");
        String nim = scanner.nextLine();

        double tugas = inputNilai("Nilai Tugas");
        double uts = inputNilai("Nilai UTS");
        double uas = inputNilai("Nilai UAS");

        Mahasiswa mhs = new Mahasiswa(nama, nim, tugas, uts, uas);
        daftarMahasiswa.add(mhs);
        System.out.println("Data berhasil ditambahkan.");
    }

    static double inputNilai(String jenis) {
        double nilai;
        do {
            System.out.print(jenis + " (0-100): ");
            nilai = scanner.nextDouble();
            if (nilai < 0 || nilai > 100) {
                System.out.println("Nilai harus antara 0 hingga 100.");
            }
        } while (nilai < 0 || nilai > 100);
        scanner.nextLine();
        return nilai;
    }

    static void tampilData() {
        if (daftarMahasiswa.isEmpty()) {
            System.out.println("Belum ada data mahasiswa.");
            return;
        }

        System.out.println("\n=== DATA MAHASISWA ===");
        System.out.printf("%-20s %-10s %-6s %-6s %-6s %-6s %-6s\n", 
            "Nama", "NIM", "TGS", "UTS", "UAS", "NA", "GRD");

        for (Mahasiswa m : daftarMahasiswa) {
            System.out.printf("%-20s %-10s %-6.1f %-6.1f %-6.1f %-6.1f %-6c\n",
                m.nama, m.nim, m.tugas, m.uts, m.uas, m.nilaiAkhir, m.grade);
        }
    }

    static void cariMahasiswa() {
        System.out.print("Masukkan NIM untuk dicari: ");
        String cariNim = scanner.nextLine();
        boolean ditemukan = false;

        for (Mahasiswa m : daftarMahasiswa) {
            if (m.nim.equalsIgnoreCase(cariNim)) {
                System.out.println("Mahasiswa ditemukan:");
                System.out.println("Nama: " + m.nama);
                System.out.println("NIM: " + m.nim);
                System.out.println("Nilai Akhir: " + m.nilaiAkhir);
                System.out.println("Grade: " + m.grade);
                ditemukan = true;
                break;
            }
        }

        if (!ditemukan) {
            System.out.println("Mahasiswa dengan NIM tersebut tidak ditemukan.");
        }
    }

    static void hapusMahasiswa() {
        System.out.print("Masukkan NIM mahasiswa yang akan dihapus: ");
        String nim = scanner.nextLine();
        boolean dihapus = daftarMahasiswa.removeIf(m -> m.nim.equalsIgnoreCase(nim));
        if (dihapus) {
            System.out.println("Data mahasiswa berhasil dihapus.");
        } else {
            System.out.println("Data tidak ditemukan.");
        }
    }
}

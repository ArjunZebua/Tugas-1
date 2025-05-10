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

public class ManajemenNilaiMahasiswa {
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
                case 1:
                    inputData();
                    break;
                case 2:
                    tampilData();
                    break;
                case 3:
                    System.out.println("Terima kasih!");
                    break;
                default:
                    System.out.println("Pilihan tidak valid");
            }
        } while (pilihan != 3);
    }

    static void tampilMenu() {
        System.out.println("\n=== MENU MANAJEMEN NILAI MAHASISWA ===");
        System.out.println("1. Input Data Mahasiswa");
        System.out.println("2. Tampilkan Data Mahasiswa");
        System.out.println("3. Keluar");
    }

    static void inputData() {
        System.out.print("Masukkan jumlah mahasiswa: ");
        int jumlah = scanner.nextInt();
        scanner.nextLine();
        
        for (int i = 1; i <= jumlah; i++) {
            System.out.println("Mahasiswa ke-" + i);
            
            System.out.print("Nama    : ");
            String nama = scanner.nextLine();

            System.out.print("NIM     : ");
            String nim = scanner.nextLine();

            System.out.print("Nilai Tugas: ");
            double tugas = scanner.nextDouble();
            scanner.nextLine();
            
            System.out.print("Nilai UTS  : ");
            double uts = scanner.nextDouble();
            scanner.nextLine();
            
            System.out.print("Nilai UAS  : ");
            double uas = scanner.nextDouble();
            scanner.nextLine();

            Mahasiswa mhs = new Mahasiswa(nama, nim, tugas, uts, uas);
            daftarMahasiswa.add(mhs);
        }
    }

    static void tampilData() {
        if (daftarMahasiswa.isEmpty()) {
            System.out.println("Belum ada data mahasiswa.");
            return;
        }

        System.out.println("\n=== DATA MAHASISWA ===");
        
        for (int i = 0; i < daftarMahasiswa.size(); i++) {
            Mahasiswa m = daftarMahasiswa.get(i);
            System.out.println("Mahasiswa ke-" + (i+1));
            System.out.println("Nama        : " + m.nama);
            System.out.println("NIM         : " + m.nim);
            System.out.println("Nilai Akhir : " + m.nilaiAkhir);
            System.out.println("Grade       : " + m.grade);
            System.out.println("---------------------------");
        }
    }
}
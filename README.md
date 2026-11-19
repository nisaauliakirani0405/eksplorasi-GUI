# Daftar Tugas Kuliah (Checklist) - GUI Java Swing
## Deskripsi Program
Aplikasi sederhana untuk mencatat tugas. Tugas yang ditambahkan muncul sebagai checklist, lalu tugas yang dicentang bisa dihapus sekaligus.

## 5 Komponen Utama GUI yang Digunakan
### 1. Top-level Container Class: `JFrame`
Class `DaftarTugasCek` meng-extend `JFrame`, jadi semua komponen lain ditaruh di dalamnya.

### 2. Intermediate Container Class: `JPanel` dan `JScrollPane`
- `JPanel` dipakai untuk mengelompokkan komponen: panel atas (input), panel daftar (checkbox), dan panel bawah (tombol).
- `JScrollPane` membungkus panel daftar supaya bisa di-scroll kalau tugasnya banyak.

### 3. Atomic Component Class
|    Class      |         Fungsi di program                        |
| `JLabel`      | Teks "Tugas:" dan jumlah tugas                   |
| `JTextField`  | Kolom input nama tugas                           |
| `JButton`     | Tombol Tambah, Hapus yang dicentang, Hapus semua |
| `JCheckBox`   | Checklist untuk tiap tugas                       |
| `JOptionPane` | Kotak dialog peringatan                          |

### 4. Layout Classes (4 layout)
|      Layout      |                  Dipakai di                                 |
| `BorderLayout`   | Jendela utama (atas: input, tengah: daftar, bawah: tombol)  |
| `FlowLayout`     | Panel input (label, text field, tombol Tambah)              |
| `BoxLayout`      | Panel daftar, checkbox disusun ke bawah                     |
| `GridLayout`     | Panel bawah (3 kolom: 2 tombol dan 1 label)                 |

### 5. Event Listener
|       Class/Interface        |                Dipakai untuk                                     |
| `ActionListener` (interface) | Klik tombol Tambah, Hapus yang dicentang, Hapus semua            |
| `KeyAdapter` (adapter class) | Menambah tugas saat menekan tombol Enter (`override keyPressed`) |

## Screenshot

**Tampilan awal**
![Tampilan awal](screenshots/tampilan-awal.png)

**Setelah menambah tugas dan mencentang beberapa tugas**
![Setelah ditambah](screenshots/setelah-ditambah.png)

**Setelah menghapus tugas yang dicentang**
![Setelah dihapus](screenshots/setelah-dihapus.png)


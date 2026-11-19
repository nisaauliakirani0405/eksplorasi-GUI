import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

// JFrame = Top-level Container
public class DaftarTugasCek extends JFrame {

    JPanel panelDaftar;                  
    ArrayList<JCheckBox> semuaTugas;     
    JTextField txtTugas;
    JButton btnTambah, btnHapus, btnBersih;
    JLabel lblJumlah;

    public DaftarTugasCek() {
        setTitle("Daftar Tugas Kuliah (Checklist)");
        setSize(420, 380);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        semuaTugas = new ArrayList<JCheckBox>();

        // Layout 1: BorderLayout untuk frame utama
        setLayout(new BorderLayout(5, 5));

        // Panel atas pakai Layout 2: FlowLayout
        JPanel panelAtas = new JPanel(new FlowLayout());
        txtTugas = new JTextField(18);
        btnTambah = new JButton("Tambah");
        panelAtas.add(new JLabel("Tugas:"));
        panelAtas.add(txtTugas);
        panelAtas.add(btnTambah);

        // Panel daftar pakai Layout 3: BoxLayout (disusun ke bawah)
        panelDaftar = new JPanel();
        panelDaftar.setLayout(new BoxLayout(panelDaftar, BoxLayout.Y_AXIS));

        // dibungkus panel lain supaya checkbox rapat di atas
        JPanel pembungkus = new JPanel(new BorderLayout());
        pembungkus.add(panelDaftar, BorderLayout.NORTH);
        JScrollPane scroll = new JScrollPane(pembungkus);

        // Panel bawah pakai Layout 4: GridLayout
        JPanel panelBawah = new JPanel(new GridLayout(1, 3, 5, 5));
        btnHapus = new JButton("Hapus yang dicentang");
        btnBersih = new JButton("Hapus semua");
        lblJumlah = new JLabel("Jumlah tugas: 0", SwingConstants.CENTER);
        panelBawah.add(btnHapus);
        panelBawah.add(btnBersih);
        panelBawah.add(lblJumlah);

        add(panelAtas, BorderLayout.NORTH);
        add(scroll, BorderLayout.CENTER);
        add(panelBawah, BorderLayout.SOUTH);

        // Event Listener (interface ActionListener)
        btnTambah.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                tambahTugas();
            }
        });

        btnHapus.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                hapusYangDicentang();
            }
        });

        btnBersih.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                panelDaftar.removeAll();
                semuaTugas.clear();
                refresh();
            }
        });

        // Event Adapter Class: KeyAdapter, tekan Enter untuk menambah
        txtTugas.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    tambahTugas();
                }
            }
        });
    }

    void tambahTugas() {
        String tugas = txtTugas.getText().trim();
        if (tugas.equals("")) {
            JOptionPane.showMessageDialog(this, "Tugas tidak boleh kosong!");
            return;
        }
        JCheckBox cek = new JCheckBox(tugas);
        semuaTugas.add(cek);
        panelDaftar.add(cek);
        txtTugas.setText("");
        refresh();
    }

    void hapusYangDicentang() {
        int jumlahDihapus = 0;

        // loop dari belakang supaya index tidak kacau saat ada yang dihapus
        for (int i = semuaTugas.size() - 1; i >= 0; i--) {
            JCheckBox cek = semuaTugas.get(i);
            if (cek.isSelected()) {
                panelDaftar.remove(cek);
                semuaTugas.remove(i);
                jumlahDihapus++;
            }
        }

        if (jumlahDihapus == 0) {
            JOptionPane.showMessageDialog(this, "Centang dulu tugas yang mau dihapus!");
        }
        refresh();
    }

    // gambar ulang tampilan setelah ada perubahan
    void refresh() {
        panelDaftar.revalidate();
        panelDaftar.repaint();
        lblJumlah.setText("Jumlah tugas: " + semuaTugas.size());
    }

    public static void main(String[] args) {
        new DaftarTugasCek().setVisible(true);
    }
}
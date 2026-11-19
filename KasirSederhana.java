import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

// JFrame = Top-level Container
public class KasirSederhana extends JFrame {

    JTextField txtNama, txtHarga, txtJumlah, txtBayar;
    JCheckBox cbMember;
    JButton btnTambah, btnBayar, btnReset;
    JTextArea areaStruk;
    JLabel lblTotal, lblKembalian;

    double total = 0; 

    public KasirSederhana() {
        setTitle("Kasir Sederhana");
        setSize(450, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Layout 1: BorderLayout untuk frame utama
        setLayout(new BorderLayout(5, 5));

        // Panel input (Intermediate Container) pakai Layout 2: GridLayout
        JPanel panelInput = new JPanel(new GridLayout(4, 2, 5, 5));
        txtNama = new JTextField();
        txtHarga = new JTextField();
        txtJumlah = new JTextField();
        cbMember = new JCheckBox("Member (diskon 10%)");
        btnTambah = new JButton("Tambah Barang");

        panelInput.add(new JLabel("  Nama barang:"));
        panelInput.add(txtNama);
        panelInput.add(new JLabel("  Harga (Rp):"));
        panelInput.add(txtHarga);
        panelInput.add(new JLabel("  Jumlah:"));
        panelInput.add(txtJumlah);
        panelInput.add(cbMember);
        panelInput.add(btnTambah);

        // Area struk di tengah, dibungkus JScrollPane
        areaStruk = new JTextArea();
        areaStruk.setEditable(false);
        areaStruk.setFont(new Font("Monospaced", Font.PLAIN, 13));
        mulaiStruk();
        JScrollPane scroll = new JScrollPane(areaStruk);

        // Panel bawah pakai Layout 3: GridLayout 3 baris
        JPanel panelBawah = new JPanel(new GridLayout(3, 1));
        lblTotal = new JLabel("Total: Rp 0", SwingConstants.CENTER);
        lblTotal.setFont(new Font("Arial", Font.BOLD, 16));
        lblKembalian = new JLabel("Kembalian: Rp 0", SwingConstants.CENTER);

        // Baris tengah panel bawah pakai Layout 4: FlowLayout
        JPanel panelBayar = new JPanel(new FlowLayout());
        txtBayar = new JTextField(8);
        btnBayar = new JButton("Bayar");
        btnReset = new JButton("Reset");
        panelBayar.add(new JLabel("Uang bayar:"));
        panelBayar.add(txtBayar);
        panelBayar.add(btnBayar);
        panelBayar.add(btnReset);

        panelBawah.add(lblTotal);
        panelBawah.add(panelBayar);
        panelBawah.add(lblKembalian);

        add(panelInput, BorderLayout.NORTH);
        add(scroll, BorderLayout.CENTER);
        add(panelBawah, BorderLayout.SOUTH);

        // Event Listener (interface ActionListener)
        btnTambah.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                tambahBarang();
            }
        });

        btnBayar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                bayar();
            }
        });

        btnReset.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                reset();
            }
        });

        // checkbox member: total langsung dihitung ulang kalau dicentang
        cbMember.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                hitungTotal();
            }
        });

        // Event Adapter Class: KeyAdapter, Enter di kolom jumlah = tambah barang
        txtJumlah.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    tambahBarang();
                }
            }
        });
    }

    void mulaiStruk() {
        areaStruk.setText("===== STRUK BELANJA =====\n");
    }

    void tambahBarang() {
        String nama = txtNama.getText().trim();
        if (nama.equals("")) {
            JOptionPane.showMessageDialog(this, "Nama barang tidak boleh kosong!");
            return;
        }

        try {
            double harga = Double.parseDouble(txtHarga.getText());
            int jumlah = Integer.parseInt(txtJumlah.getText());

            if (harga <= 0 || jumlah <= 0) {
                JOptionPane.showMessageDialog(this, "Harga dan jumlah harus lebih dari 0!");
                return;
            }

            double subtotal = harga * jumlah;
            total = total + subtotal;

            areaStruk.append(nama + " x" + jumlah + " @" + (long) harga
                    + " = " + (long) subtotal + "\n");

            txtNama.setText("");
            txtHarga.setText("");
            txtJumlah.setText("");
            txtNama.requestFocus();
            hitungTotal();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Harga dan jumlah harus berupa angka!");
        }
    }

    // menghitung total akhir (sudah termasuk diskon kalau member)
    double hitungTotal() {
        double diskon = 0;
        if (cbMember.isSelected()) {
            diskon = total * 0.1;
        }
        double totalAkhir = total - diskon;

        String teks = "Total: Rp " + (long) totalAkhir;
        if (diskon > 0) {
            teks = teks + " (diskon Rp " + (long) diskon + ")";
        }
        lblTotal.setText(teks);
        return totalAkhir;
    }

    void bayar() {
        double totalAkhir = hitungTotal();
        if (total == 0) {
            JOptionPane.showMessageDialog(this, "Belum ada barang yang dibeli!");
            return;
        }

        try {
            double uang = Double.parseDouble(txtBayar.getText());
            if (uang < totalAkhir) {
                JOptionPane.showMessageDialog(this, "Uang kurang Rp " + (long) (totalAkhir - uang));
                return;
            }
            double kembalian = uang - totalAkhir;
            lblKembalian.setText("Kembalian: Rp " + (long) kembalian);

            areaStruk.append("-------------------------\n");
            areaStruk.append("Total    : " + (long) totalAkhir + "\n");
            areaStruk.append("Bayar    : " + (long) uang + "\n");
            areaStruk.append("Kembalian: " + (long) kembalian + "\n");
            areaStruk.append("Terima kasih!\n");
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Uang bayar harus berupa angka!");
        }
    }

    void reset() {
        total = 0;
        txtNama.setText("");
        txtHarga.setText("");
        txtJumlah.setText("");
        txtBayar.setText("");
        cbMember.setSelected(false);
        lblTotal.setText("Total: Rp 0");
        lblKembalian.setText("Kembalian: Rp 0");
        mulaiStruk();
    }

    public static void main(String[] args) {
        new KasirSederhana().setVisible(true);
    }
}
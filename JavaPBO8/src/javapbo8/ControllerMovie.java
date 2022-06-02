package javapbo8;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JOptionPane;
import javax.swing.JTable;

public class ControllerMovie {
     ModelMovie modelMovie;
     ViewMovie viewMovie;
     public String dataterpilih;
     public ControllerMovie(ModelMovie modelMovie, ViewMovie viewMovie) {
        this.modelMovie = modelMovie;
        this.viewMovie = viewMovie;
        if (modelMovie.getBanyakData()!=0) {
            String dataMovie[][] = modelMovie.MovieList();
            viewMovie.tabel.setModel((new JTable(dataMovie, viewMovie.namaKolom)).getModel());
        }
        else {
            JOptionPane.showMessageDialog(null, "Data Kosong");
        }
         viewMovie.btnTambah.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                String judul = viewMovie.getJudul();
                String alur = viewMovie.getAlur();
                String penokohan = viewMovie.getPenokohan();
                String akting = viewMovie.getAkting();
                modelMovie.insertmovie(judul, alur, penokohan, akting);
         
            String dataMovie[][] = modelMovie.MovieList();
            viewMovie.tabel.setModel((new JTable(dataMovie, viewMovie.namaKolom)).getModel());
            }
        });
    
          viewMovie.btnUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                 String judul = viewMovie.getJudul();
                String alur = viewMovie.getAlur();
                String penokohan = viewMovie.getPenokohan();
                String akting = viewMovie.getAkting();
                modelMovie.updateMovie(judul, alur, penokohan, akting);
                
                String dataMovie[][] = modelMovie.MovieList();
            viewMovie.tabel.setModel((new JTable(dataMovie, viewMovie.namaKolom)).getModel());
            }
        });

        viewMovie.tabel.addMouseListener(new MouseAdapter(){    
        public void mouseClicked(MouseEvent e) {
                super.mousePressed(e);
                int baris = viewMovie.tabel.getSelectedRow();

                dataterpilih = viewMovie.tabel.getValueAt(baris, 0).toString();
                System.out.println(dataterpilih);     
            }       
        });
    
    viewMovie.btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                int input = JOptionPane.showConfirmDialog(null,
                        "Apa anda ingin menghapus Film " + dataterpilih + "?", "Pilih Opsi...", JOptionPane.YES_NO_OPTION);
                if (input == 0) {
                    modelMovie.deleteMovie(dataterpilih);
                    String dataMovie[][] = modelMovie.MovieList();
                    viewMovie.tabel.setModel((new JTable(dataMovie, viewMovie.namaKolom)).getModel());
                } else {
                    JOptionPane.showMessageDialog(null, "Tidak Jadi Dihapus");
                }
            }
        });
}
}
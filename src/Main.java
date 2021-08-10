import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Main {
    private JPanel panelMain;
    private JTextField textName;
    private JTextField textEmail;
    private JTextField textUser;
    private JTextField textPass;
    private JButton btnInsert;
    private JTable tableData;
    private JButton btnUpdate;
    private JButton btnDelete;
    private JButton btnClear;
    String dataName, dataEmail, dataUser, dataPass;

    public Main() {
        new ViewData(tableData);
        ViewData view = new ViewData(tableData);

        tableData.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                String tempName, tempEmail, tempUser, tempPass;
                DefaultTableModel model = (DefaultTableModel) tableData.getModel();

                if (!e.getValueIsAdjusting()) {
                    if (tableData.getSelectedRow() > -1) {
                        tempName = model.getValueAt(tableData.getSelectedRow(), 3).toString();
                        tempEmail = model.getValueAt(tableData.getSelectedRow(), 4).toString();
                        tempUser = model.getValueAt(tableData.getSelectedRow(), 1).toString();
                        tempPass = model.getValueAt(tableData.getSelectedRow(), 2).toString();

                        textName.setText(tempName);
                        textEmail.setText(tempEmail);
                        textUser.setText(tempUser);
                        textPass.setText(tempPass);
                    }
                }
            }
        });

        btnInsert.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dataName = textName.getText();
                dataEmail = textEmail.getText();
                dataUser = textUser.getText();
                dataPass = textPass.getText();
                new InsertData(dataName, dataEmail, dataUser, dataPass);
                new ViewData(tableData);
            }
        });

        btnUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dataName = textName.getText();
                dataEmail = textEmail.getText();
                dataUser = textUser.getText();
                dataPass = textPass.getText();
                new UpdateData(dataName, dataEmail, dataUser, dataPass);
                new ViewData(tableData);
            }
        });

        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dataUser = textUser.getText();
                new DeleteData(dataUser);
                new ViewData(tableData);
            }
        });

        btnClear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textName.setText("");
                textEmail.setText("");
                textUser.setText("");
                textPass.setText("");
            }
        });
    }

    public static void main(String[] args) throws SQLException {
        JFrame frame = new JFrame("CRUD Program");
        frame.setContentPane(new Main().panelMain);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(700, 400));
        frame.pack();
        frame.setVisible(true);
    }
}

package View;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicButtonUI;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Omar  Davide Xavier
 */
public class Calculadora  implements ActionListener {
    
    public Calculadora() {
        instanciarComponentes();
        configurarJanela();
    }

    JFrame frame;
    JLabel lHistorico, lVisor;

    Color MAIN_BLUE = new Color(216, 228, 240);
    Color LIGHT_BLUE = new Color(243, 246, 251);
    Color DARK_BLUE = new Color(64, 81, 108);

    Font FONT_PLAIN = new Font(null, Font.PLAIN, 14);
    Font FONT_BOLD = new Font(null, Font.BOLD, 30);

    boolean isNextNumber = false, isNextHistory = false;

    private void configurarJanela() {
        JPanel pGlobal = new JPanel();
        pGlobal.setLayout(new BorderLayout());
        pGlobal.setBorder(new EmptyBorder(20, 20, 20, 20));
        pGlobal.setBackground(MAIN_BLUE);

        JMenuBar menuBar = new JMenuBar();
        menuBar.setBackground(LIGHT_BLUE);
        menuBar.add(getMenuView());
        menuBar.add(getMenuEdit());
        menuBar.add(getMenuHelp());

        frame.setJMenuBar(menuBar);
        pGlobal.add(getPainelVisor(), BorderLayout.NORTH);
        pGlobal.add(getPainelButoes(), BorderLayout.CENTER);

        frame.add(pGlobal);
        frame.pack();
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    private JMenu getMenuView() {
        JMenu menu = new JMenu("View");
        // itens, etc ...
        return menu;
    }

    private JMenu getMenuEdit() {
        JMenu menu = new JMenu("Edit");
        // itens, etc ...
        return menu;
    }

    private JMenu getMenuHelp() {
        JMenu menu = new JMenu("Help");
        // itens, etc ...
        return menu;
    }

    private JPanel getPainelVisor() {
        JPanel painel = new JPanel(new BorderLayout());
        painel.setBorder(BorderFactory.createLineBorder(LIGHT_BLUE));
        painel.setPreferredSize(new Dimension(0, 70));
        JPanel painelComMargem = new JPanel(new GridLayout(2, 1));
        painelComMargem.setBorder(new EmptyBorder(0, 10, 0, 10));

        painel.setBackground(LIGHT_BLUE);
        painelComMargem.setBackground(LIGHT_BLUE);

        painelComMargem.add(lHistorico);
        painelComMargem.add(lVisor);

        painel.add(painelComMargem, BorderLayout.CENTER);

        return painel;
    }

    private JPanel getPainelButoes() {
        JPanel painel = new JPanel(new GridBagLayout());
        painel.setBackground(MAIN_BLUE);
        painel.setBorder(new EmptyBorder(20, 0, 0, 0));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(5, 5, 5, 5);

        /*
            Comandos M?
         */
        gbc.gridx = 0;
        gbc.gridy = 0;
        for (String str : new String[]{"MC", "MR", "MS", "M+", "M-"}) {
            adicionarNovoBotao(str, painel, gbc, false);
            gbc.gridx++;
        }
        /*
            Comandos Especiais
         */
        gbc.gridx = 0;
        gbc.gridy = 1;
        for (String str : new String[]{"←", "CE", "C", "±", "√"}) {
            adicionarNovoBotao(str, painel, gbc, false);
            gbc.gridx++;
        }
        /*
            Dígitos
         */
        gbc.gridx = 0;
        gbc.gridy = 2;
        for (int i = 7; i > 0; i -= 3) {
            for (int j = 0; j < 3; j++) {
                adicionarNovoBotao((i + j) + "", painel, gbc, true);
                gbc.gridx++;
            }
            gbc.gridx = 0;
            gbc.gridy++;
        }
        gbc.gridwidth = 2;
        adicionarNovoBotao("0", painel, gbc, true);
        gbc.gridwidth = 1;
        gbc.gridx = 2;
        adicionarNovoBotao(".", painel, gbc, true);
        /*
            Operações
         */
        gbc.gridx = 3;
        gbc.gridy = 2;
        for (String str : new String[]{"/", "*", "-", "+"}) {
            adicionarNovoBotao(str, painel, gbc, false);
            gbc.gridy++;
        }

        gbc.gridx++;
        gbc.gridy = 2;
        for (String str : new String[]{"%", "⅟x"}) {
            adicionarNovoBotao(str, painel, gbc, false);
            gbc.gridy++;
        }
        gbc.gridheight = 2;
        adicionarNovoBotao("=", painel, gbc, true);

        return painel;
    }

    private void adicionarNovoBotao(String texto, JPanel painel, GridBagConstraints gbc, boolean destacar) {
        JButton btn = new JButton(texto);
        btn.setUI(new BasicButtonUI());
        btn.setForeground(DARK_BLUE);
        btn.setFont(new Font(null, Font.PLAIN, 14));
        btn.setFocusPainted(false);
        if (destacar) {
            btn.setFont(new Font(null, Font.BOLD, 20));
            btn.setBackground(LIGHT_BLUE);
        }

        btn.addActionListener(this);

        painel.add(btn, gbc);
    }

    private void instanciarComponentes() {
        frame = new JFrame("Calculator");
        lHistorico = new JLabel("", SwingConstants.TRAILING);
        lVisor = new JLabel("", SwingConstants.TRAILING);

        lHistorico.setFont(FONT_PLAIN);
        lVisor.setFont(FONT_BOLD);
    }

    public static void main(String[] args) {
        new Calculadora();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();

        if (isNextHistory) {
            isNextHistory = false;
            lHistorico.setText("");
        }

        if (isOperator(cmd)) {
            if (cmd.equals("=")) {
                if (lHistorico.getText().length() < 3) {
                    return;
                }

                lHistorico.setText(lHistorico.getText() + " " + lVisor.getText());
                lVisor.setText(eval(lHistorico.getText()));
                lHistorico.setText(lHistorico.getText() + " " + cmd);
                isNextHistory = true;
            } else {
                if (lHistorico.getText().length() >= 3) {
                    lHistorico.setText(lHistorico.getText() + " " + lVisor.getText());
                    String result = eval(lHistorico.getText());
                    lHistorico.setText(result);
                    lVisor.setText(result);
                    lHistorico.setText(lHistorico.getText() + " " + cmd);
                } else {
                    lHistorico.setText(lHistorico.getText() + " " + lVisor.getText() + " " + cmd);
                }
            }

            isNextNumber = true;

        } else if (cmd.equals("C") || cmd.equals("CE")) {

            lVisor.setText("");
            lHistorico.setText("");

        } else if (cmd.equals("←")) {
            if (lVisor.getText().length() > 1) {
                lVisor.setText(lVisor.getText().substring(0, lVisor.getText().length() - 1));
            } else {
                lVisor.setText("0");
                isNextNumber = true;
            }

        } else if (isNumber(cmd) || (cmd.equals(".") && !lVisor.getText().contains("."))) {

            if (isNextNumber) {
                lVisor.setText(cmd);
                isNextNumber = false;
            } else {
                lVisor.setText(lVisor.getText() + cmd);
            }
        }
    }

    private String eval(String text) {
        String[] elements = text.trim().split(" ");
        double a = Double.parseDouble(elements[0]), b = Double.parseDouble(elements[2]);

        switch (elements[1]) {
            case "+":
                return formatValue(a + b);
            case "-":
                return formatValue(a - b);
            case "*":
                return formatValue(a * b);
            case "/":
                return formatValue(a / b);
        }
        return "0";
    }

    private boolean isNumber(String text) {
        try {
            double aux = Double.parseDouble(text);

        } catch (Exception ex) {
            return false;
        }
        return true;
    }

    private boolean isOperator(String text) {
        for (String str : new String[]{"/", "*", "-", "+", "="}) {
            if (text.equals(str)) {
                return true;
            }
        };
        return false;
    }

    private String formatValue(double value) {

        if ((int) value == value) {
            return ((int) value) + "";
        }
        return value + "";
    }
}

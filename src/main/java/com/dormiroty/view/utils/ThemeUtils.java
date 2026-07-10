package com.dormiroty.view.utils;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicButtonUI;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import java.awt.*;

/**
 * Utility class providing consistent, modern styling for the entire application.
 */
public class ThemeUtils {

    // ─── Modern Color Palette ───────────────────────────────────────────────
    public static final Color PRIMARY       = new Color(0x1976D2); // Material Blue 700
    public static final Color PRIMARY_LIGHT = new Color(0x42A5F5); // Material Blue 400
    public static final Color PRIMARY_DARK  = new Color(0x0D47A1); // Material Blue 900
    public static final Color SECONDARY     = new Color(0xFF9800); // Orange
    public static final Color SUCCESS       = new Color(0x43A047); // Green 600
    public static final Color DANGER        = new Color(0xE53935); // Red 600
    public static final Color WARNING       = new Color(0xFB8C00); // Amber

    public static final Color BG_PANEL      = new Color(0xF5F5F5); // Grey 100
    public static final Color CARD_BG       = Color.WHITE;
    public static final Color TEXT_PRIMARY  = new Color(0x212121);
    public static final Color TEXT_SECONDARY= new Color(0x757575);
    public static final Color BORDER_COLOR  = new Color(0xE0E0E0);
    public static final Color TABLE_ROW_ALT = new Color(0xF5F8FF); // Light blue tint

    // Font sizes
    public static final int FONT_SIZE_SMALL  = 12;
    public static final int FONT_SIZE_NORMAL = 14;
    public static final int FONT_SIZE_LARGE  = 16;
    public static final int FONT_SIZE_TITLE  = 20;

    // ─── Fonts ──────────────────────────────────────────────────────────────
    private static Font labelFont;
    private static Font fieldFont;
    private static Font buttonFont;
    private static Font titleFont;
    private static Font tableHeaderFont;
    private static Font tableFont;

    static {
        labelFont = new Font("Segoe UI", Font.PLAIN, FONT_SIZE_NORMAL);
        fieldFont = new Font("Segoe UI", Font.PLAIN, FONT_SIZE_NORMAL);
        buttonFont = new Font("Segoe UI", Font.BOLD, FONT_SIZE_NORMAL);
        titleFont = new Font("Segoe UI", Font.BOLD, FONT_SIZE_TITLE);
        tableHeaderFont = new Font("Segoe UI", Font.BOLD, FONT_SIZE_SMALL);
        tableFont = new Font("Segoe UI", Font.PLAIN, FONT_SIZE_SMALL);
    }

    // ─── Panel / Layout Helpers ─────────────────────────────────────────────

    /**
     * Creates the main container panel for each feature tab.
     * Uses BorderLayout with consistent padding.
     */
    public static JPanel createMainPanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBackground(BG_PANEL);
        panel.setBorder(new EmptyBorder(15, 15, 15, 15));
        return panel;
    }

    /**
     * Creates a titled section panel (card-style group for form fields).
     */
    public static JPanel createFormPanel(String title, int cols) {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(CARD_BG);
        panel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder(
                        BorderFactory.createLineBorder(BORDER_COLOR, 1, true),
                        title,
                        javax.swing.border.TitledBorder.LEFT,
                        javax.swing.border.TitledBorder.TOP,
                        new Font("Segoe UI", Font.BOLD, FONT_SIZE_LARGE),
                        PRIMARY
                ),
                new EmptyBorder(10, 10, 10, 10)
        ));
        return panel;
    }

    /**
     * Adds a labeled field row to a GridBagLayout form panel.
     */
    public static void addFormRow(JPanel panel, int row, String labelText, JComponent field) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(4, 4, 4, 4);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Label
        JLabel label = createLabel(labelText);
        gbc.gridx = 0;
        gbc.gridy = row;
        gbc.weightx = 0.0;
        gbc.anchor = GridBagConstraints.EAST;
        panel.add(label, gbc);

        // Field
        gbc.gridx = 1;
        gbc.weightx = 1.0;
        gbc.anchor = GridBagConstraints.WEST;
        panel.add(field, gbc);
    }

    // ─── Button Styling ─────────────────────────────────────────────────────

    /**
     * Creates a styled button with the given text and primary style.
     */
    public static JButton createButton(String text, Color bgColor) {
        JButton btn = new JButton(text);
        btn.setFont(buttonFont);
        btn.setBackground(bgColor);
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        btn.setOpaque(true);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.setPreferredSize(new Dimension(110, 36));
        btn.setUI(new BasicButtonUI());
        btn.setBorder(new EmptyBorder(8, 18, 8, 18));

        // Hover effect
        btn.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent e) {
                btn.setBackground(brighten(bgColor, 0.15f));
            }
            @Override
            public void mouseExited(java.awt.event.MouseEvent e) {
                btn.setBackground(bgColor);
            }
        });

        return btn;
    }

    public static JButton createPrimaryButton(String text) {
        return createButton(text, PRIMARY);
    }

    public static JButton createSuccessButton(String text) {
        return createButton(text, SUCCESS);
    }

    public static JButton createDangerButton(String text) {
        return createButton(text, DANGER);
    }

    public static JButton createWarningButton(String text) {
        return createButton(text, WARNING);
    }

    /**
     * Creates a button panel with right-aligned buttons.
     */
    public static JPanel createButtonPanel(JButton... buttons) {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 8, 0));
        panel.setOpaque(false);
        for (JButton btn : buttons) {
            panel.add(btn);
        }
        return panel;
    }

    // ─── Label / Field Styling ──────────────────────────────────────────────

    public static JLabel createLabel(String text) {
        JLabel lbl = new JLabel(text);
        lbl.setFont(labelFont);
        lbl.setForeground(TEXT_PRIMARY);
        return lbl;
    }

    public static JTextField createTextField(int cols) {
        JTextField tf = new JTextField(cols);
        tf.setFont(fieldFont);
        tf.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(BORDER_COLOR, 1, true),
                new EmptyBorder(6, 8, 6, 8)
        ));
        return tf;
    }

    public static JComboBox<String> createComboBox(String[] items) {
        JComboBox<String> cb = new JComboBox<>(items);
        cb.setFont(fieldFont);
        cb.setBackground(Color.WHITE);
        return cb;
    }

    // ─── Table Styling ──────────────────────────────────────────────────────

    /**
     * Applies modern styling to a JTable.
     */
    public static void styleTable(JTable table) {
        table.setFont(tableFont);
        table.setRowHeight(32);
        table.setBackground(Color.WHITE);
        table.setForeground(TEXT_PRIMARY);
        table.setGridColor(BORDER_COLOR);
        table.setShowGrid(true);
        table.setShowHorizontalLines(true);
        table.setShowVerticalLines(false);
        table.setIntercellSpacing(new Dimension(0, 0));
        table.setSelectionBackground(new Color(0xBBDEF5)); // Light blue selection
        table.setSelectionForeground(TEXT_PRIMARY);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);

        // Alternating row colors
        table.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                                                           boolean isSelected, boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                if (!isSelected) {
                    c.setBackground(row % 2 == 0 ? Color.WHITE : TABLE_ROW_ALT);
                }
                setBorder(new EmptyBorder(0, 8, 0, 8));
                return c;
            }
        });

        // Header styling
        JTableHeader header = table.getTableHeader();
        header.setFont(tableHeaderFont);
        header.setBackground(PRIMARY);
        header.setForeground(Color.WHITE);
        header.setPreferredSize(new Dimension(header.getWidth(), 38));
        header.setReorderingAllowed(false);

        // Round header corners would require custom painting, skip for simplicity
    }

    // ─── Utility ────────────────────────────────────────────────────────────

    /**
     * Brightens a color by a given factor (0.0 – 1.0).
     */
    private static Color brighten(Color color, float factor) {
        int r = Math.min(255, (int) (color.getRed() * (1 + factor)));
        int g = Math.min(255, (int) (color.getGreen() * (1 + factor)));
        int b = Math.min(255, (int) (color.getBlue() * (1 + factor)));
        return new Color(r, g, b, color.getAlpha());
    }
}

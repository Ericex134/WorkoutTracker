package ui;

import javax.swing.*;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ViewWeightUI extends JFrame implements ActionListener {
    private JScrollPane pane;
    private JTextPane text;
    private JButton confirmButton;
    private JPanel buttonPanel;
    private Font textFont;

    //CONSTRUCTER
    public ViewWeightUI(String string) {
        initializeFrame();
        initializeText();
        text.setText(string);

        pane = new JScrollPane(text);
        pane.setPreferredSize(new Dimension(480, 570));
        pane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        confirmButton = new JButton("OK");
        confirmButton.addActionListener(this);

        buttonPanel = new JPanel();
        buttonPanel.setPreferredSize(new Dimension(500, 100));
        confirmButton.setBorderPainted(true);
        confirmButton.setPreferredSize(new Dimension(200, 60));

        buttonPanel.add(confirmButton);
        this.add(pane);
        this.add(buttonPanel);
        this.setVisible(true);
    }

    //MODIFIES:this
    //EFFECTS: initializes and sets up the JFrame window
    public void initializeFrame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(new Dimension(500, 720));
        setResizable(false);
        setTitle("Weight Progress");
        setLayout(new FlowLayout());

    }

    //MODIFIES: this
    //EFFECTS: initializes and sets up the text panes
    public void initializeText() {
        textFont = new Font("Arial", Font.PLAIN, 25);
        text = new JTextPane();
        text.setEditable(false);
        text.setFont(textFont);
        StyledDocument doc = text.getStyledDocument();
        SimpleAttributeSet center = new SimpleAttributeSet();
        StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
        doc.setParagraphAttributes(0, doc.getLength(), center, false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == confirmButton) {
            this.dispose();
        }
    }
}

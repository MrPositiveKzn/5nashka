package ru.ivmiit;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.HashMap;
import java.util.Map;

public class View {
    private static final String TITLE = "5nashki";
    private static final Dimension DIMENSION = new Dimension(300, 300);

    private JPanel panel = new JPanel();
    private JFrame frame = new JFrame();
    private JButton[][] buttons = new JButton[4][4];
    private Map<JButton, Point> buttonLocationMap = new HashMap<>();
    private Presenter presenter;

    public View() {
        initFrame();
        initPanel();
        initButtons();
        frame.setVisible(true);
    }

    protected void initFrame() {
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setTitle(TITLE);
        frame.setSize(DIMENSION);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.getContentPane().add(panel);
    }

    private void initPanel() {
        panel.setLayout(new GridLayout(4, 4, 0, 0));
    }

    protected void initButtons() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                JButton button = new JButton();
                buttons[j][i] = button;
                buttonLocationMap.put(button, new Point(j, i));
                panel.add(button);
                button.addActionListener(this::onButtonClick);
            }
        }
    }

    private void onButtonClick(ActionEvent event) {
        Point point = buttonLocationMap.get(event.getSource());
        if (point != null) {
            presenter.onButtonClick(point);
        }
    }

    public void setPresenter(Presenter presenter) {
        this.presenter = presenter;
    }

    public void setButtonLabel(int x, int y, String label) {
        buttons[x][y].setText(label);
    }

    public void setButtonVisible(int x, int y, boolean visible) {
        buttons[x][y].setVisible(visible);
    }

    protected boolean win() {
        boolean status = true;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (i == 3 && j > 2)
                    break;
                if (Integer.valueOf(buttons[j][i].getText()) != i * 4 + j + 1) {
                    status = false;
                }
            }
        }
        if (status) {
            panel.removeAll();
            buttonLocationMap.clear();
            initButtons();
        }
        return status;
    }
}

package ru.ivmiit;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Presenter {
    private View view;
    private List<Integer> numbers;
    private Point hidden;

    public Presenter(View view) {
        this.view = view;
        initNumbers();
        setButtonLabels();
        hideZero();
    }

    public List<Integer> getNumbers() {
        return numbers;
    }

    public void setNumbers(List<Integer> numbers) {

        this.numbers = numbers;
    }

    private void initNumbers() {
        setNumbers(new ArrayList<>(16));
        System.out.println(numbers);
        for (int i = 0; i < 16; i++) {
            numbers.add(i);
        }
        System.out.println(numbers);
        do {
            Collections.shuffle(numbers);
        }
        while (!canBeSolved(numbers));
        System.out.println(numbers);
    }

    private boolean canBeSolved(List<Integer> numbers) {
        int sum = 0;
        for (int i = 0; i < 16; i++) {
            if (numbers.get(i) == 0) {
                sum += i / 4;
                System.out.println(sum);
                System.out.println(i);
                continue;
            }
            for (int j = i + 1; j < 16; j++) {
                if (numbers.get(j) < numbers.get(i))
                    sum ++;
            }
        }
        System.out.println(sum % 2 == 0);
        return sum % 2 == 0;
    }

    private void setButtonLabels() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                view.setButtonLabel(i, j, numbers.get(i * 4 + j).toString());
            }
        }
    }

    private void hideZero() {
        int index = numbers.indexOf(0);
        int x = index / 4;
        int y = index % 4;
        hidden = new Point(x, y);
        view.setButtonVisible(x, y, false);
    }

    public void onButtonClick(Point point) {
        System.out.printf("Clicked {x=%d, y=%d}%n", point.getX(), point.getY());
        System.out.println(numbers);
        int x0 = point.getX();
        int y0 = point.getY();
        int xRight = x0 + 1;
        int xLeft = x0 - 1;
        int yTop = y0 - 1;
        int yDown = y0 + 1;

        if (xRight > 3) {
        }
        if (xLeft < 0) {
        }
        if (yTop < 0) {
        }
        if (yDown > 3) {
        }

        int num0list = numbers.indexOf(0); //индекс 0
        int num0Label = 0; //лайб 0
        int numActionList = numbers.indexOf(numbers.get(point.getX() * 4 + point.getY()));
        int numLabelAction = numbers.get(point.getX() * 4 + point.getY());
        System.out.println(num0list + ", " + num0Label + ", " + numActionList + ", " + numLabelAction);

        if (num0list == numActionList - 1 || num0list == numActionList + 1 || num0list == numActionList - 4 || num0list == numActionList + 4) {
            numbers.set(num0list, numLabelAction);
            view.setButtonLabel(num0list / 4, num0list % 4, String.valueOf(numLabelAction));
            view.setButtonVisible(num0list / 4, num0list % 4, true);
            numbers.set(numActionList, num0Label);
            view.setButtonLabel(point.getX(), point.getY(), String.valueOf(num0Label));
            view.setButtonVisible(point.getX(), point.getY(), false);
        }

        if (view.win()) {
            JOptionPane.showMessageDialog(null, "Мужииик", "You have a very big balls...", JOptionPane.INFORMATION_MESSAGE);
            initNumbers();
            setButtonLabels();
            hideZero();
        }
    }
}

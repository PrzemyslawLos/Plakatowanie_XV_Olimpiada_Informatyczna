/*************************************************************************
 * Przemysław Loś
 *************************************************************************/

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int numberOfBuildings = scanner.nextInt();
        List<Building> buildings = new ArrayList<>();

        for (int i = 0; i < numberOfBuildings; i++) {
            int width = scanner.nextInt();
            int height = scanner.nextInt();
            buildings.add(new Building(width, height));
        }

        scanner.close();
        countBanners(numberOfBuildings, buildings);
    }

    public static void countBanners(int numberOfBuildings, List<Building> buildings) {
        int counter = 0;
        Stack stack = new Stack(numberOfBuildings);
        for (int i = 0; i < numberOfBuildings; i++) {
            while (stack.firstFree != 0 && stack.top() > buildings.get(i).getHeight()) {
                stack.pop();
            }
            if (stack.firstFree == 0 || stack.top() < buildings.get(i).getHeight()) {
                stack.push(buildings.get(i).getHeight());
                counter++;
            }

        }
        System.out.println(counter);
    }

    static class Building {
        private int width;
        private int height;

        public Building(int width, int height) {
            this.width = width;
            this.height = height;
        }

        public int getWidth() {
            return width;
        }

        public int getHeight() {
            return height;
        }
    }

    static class Stack {
        int[] tab;
        int firstFree;

        Stack(int x) {
            firstFree = 0;
            tab = new int[x];
        }

        void push(int x) throws ArrayIndexOutOfBoundsException {
            if (firstFree < tab.length) {
                tab[firstFree] = x;
                firstFree++;
            } else {
                throw new ArrayIndexOutOfBoundsException("The stack is filled");
            }
        }

        int pop() throws IndexOutOfBoundsException {
            if (firstFree <= 0) {
                throw new IndexOutOfBoundsException("The stack is empty");
            }
            int temporary = tab[firstFree - 1];
            firstFree--;
            return temporary;
        }

        int top() {
            return tab[firstFree - 1];
        }
    }
}

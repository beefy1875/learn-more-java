package machine;

import java.util.*;

class CoffeMachineWorkings {
    private int water;
    private int milk;
    private int beans;
    private int cups;
    private int dollar;
    State state;

    CoffeMachineWorkings(int water, int milk, int beans, int cups, int dollar) {
        this.water = water;
        this.milk = milk;
        this.beans = beans;
        this.cups = cups;
        this.dollar = dollar;
        this.state = State.WAITING_FOR_ACTION;
    }

    void printStatus() {
        System.out.println("The coffee machine has:\n" +
                water + " of water\n" +
                milk + " of milk\n" +
                beans + " of coffee beans\n" +
                cups + " of disposable cups\n" +
                dollar + " of money\n");

    }

    void take() {
        System.out.println("I gave you $" + this.dollar);
        this.dollar = 0;
    }

    void buyCoffee(String selection) {
        this.state = State.WAITING_FOR_ACTION;
        if(selection.equals("back")) {
            return;
        }

        if(selection.equals("1")) {
            if(this.water  < 250) {
                System.out.println("Sorry, not enough water");
                return;
            }
            if(this.beans  < 16) {
                System.out.println("Sorry, not enough beans");
                return;
            }
            if(this.cups  < 1) {
                System.out.println("Sorry, not enough cups");
                return;
            }
            System.out.println("I have enough resources, making you a coffee!");
            this.water -= 250;
            this.beans -= 16;
            this.cups--;
            this.dollar += 4;


        } else if (selection.equals("2")) {
            if(this.water  < 350) {
                System.out.println("Sorry, not enough water");
                return;
            }
            if(this.milk  < 75) {
                System.out.println("Sorry, not enough water");
                return;
            }
            if(this.beans  < 20) {
                System.out.println("Sorry, not enough beans");
                return;
            }
            if(this.cups  < 1) {
                System.out.println("Sorry, not enough cups");
                return;
            }
            System.out.println("I have enough resources, making you a coffee!");

            this.water -= 350;
            this.milk -= 75;
            this.beans -= 20;
            this.cups--;
            this.dollar += 7;

        } else if (selection.equals("3")) {
            if(this.water  < 200) {
                System.out.println("Sorry, not enough water");
                return;
            }
            if(this.milk  < 100) {
                System.out.println("Sorry, not enough water");
                return;
            }
            if(this.beans  < 12) {
                System.out.println("Sorry, not enough beans");
                return;
            }
            if(this.cups  < 1) {
                System.out.println("Sorry, not enough cups");
                return;
            }
            System.out.println("I have enough resources, making you a coffee!");

            this.water -= 200;
            this.milk -= 100;
            this.beans -= 12;
            this.cups--;
            this.dollar += 6;

        }
    }

    void runCommand(String input) {
        if(this.state == State.WAITING_FOR_ACTION) {
            switch (input) {
                case "buy":
                    this.state = State.WAITING_FOR_COFFEE_CHOICE;
                    break;
                case "fill":
                    this.state = State.WAITING_FOR_REFILL_AMOUNTS;
                    break;
                case "take":
                    take();
                    break;
                case "remaining":
                    printStatus();
            }
        } else if(this.state == State.WAITING_FOR_COFFEE_CHOICE) {
            buyCoffee(input);

        } else if(this.state == State.WAITING_FOR_REFILL_AMOUNTS) {
            String[] parts = input.split(",");
            this.water += Integer.valueOf(parts[0]);
            this.milk += Integer.valueOf(parts[1]);
            this.beans += Integer.valueOf(parts[2]);
            this.cups += Integer.valueOf(parts[3]);
            this.state = State.WAITING_FOR_ACTION;
        }
    }



}

enum State {
    WAITING_FOR_ACTION, WAITING_FOR_COFFEE_CHOICE, WAITING_FOR_REFILL_AMOUNTS
}

public class CoffeeMachine {

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        CoffeMachineWorkings c = new CoffeMachineWorkings(400, 540, 120,9, 550);

        while(true) {
            if(c.state == State.WAITING_FOR_ACTION) {
                System.out.println("Write action (buy, fill, take, remaining, exit): ");
                String action = s.nextLine();

                if(action.equals("exit")) {
                    break;
                }
                c.runCommand(action);
            }
            if(c.state == State.WAITING_FOR_COFFEE_CHOICE) {
                System.out.println("What do you want to buy? 1 - espresso, 2 - latte, 3- cappuccino, back - to main menu");
                String input = s.nextLine();
                c.runCommand(input);

            }
            if(c.state == State.WAITING_FOR_REFILL_AMOUNTS) {
                System.out.println("Write how many ml of water do you want to add: ");
                String input = s.nextLine();
                System.out.println("Write how many ml of milk do you want to add: ");
                input = input + "," + s.nextLine();
                System.out.println("Write how many grams of coffee beans do you want to add: ");
                input = input + "," + s.nextLine();
                System.out.println("Write how many disposable cups of coffee do you want to add: ");
                input = input + "," + s.nextLine();
                c.runCommand(input);

            }


        }
    }
}

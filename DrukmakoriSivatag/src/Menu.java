import java.util.Scanner;

public class Menu {
    interface MenuOption {
        void execute();
        String getName();
    }

    public static final MenuOption[] MAIN_MENU = {
            new MenuOption() {
                public void execute() {
                    runMenu(MOVE_SUBMENU);
                }
                public String getName() {
                    return "Move";
                }
            },
            new MenuOption() {
                public void execute() {
                    runMenu( TIME_TICK_SUBMENU);
                }
                public String getName() {
                    return "Time Tick";
                }
            },
            new MenuOption() {
                public void execute() {
                    runMenu(PIPE_ACTIONS_SUBMENU);
                }
                public String getName() {
                    return "Pipe actions";
                }
            },
            new MenuOption() {
                public void execute() {
                    runMenu(PUMP_ACTIONS_SUBMENU);
                }
                public String getName() {
                    return "Pump actions";
                }
            }
    };

    private static final MenuOption[] MOVE_SUBMENU = {
            new MenuOption() {
                public void execute() {
                    System.out.println("Move to pipe from pump");
                }
                public String getName() {
                    return "Move to pipe from pump";
                }
            },
            new MenuOption() {
                public void execute() {
                    System.out.println("Move to pump from pipe");
                }
                public String getName() {
                    return "Move to pump from pipe";
                }
            },
            new MenuOption() {
                public void execute() {
                    System.out.println("Move to cistern from pipe");
                }
                public String getName() {
                    return "Move to cistern from pipe";
                }
            },
            new MenuOption() {
                public void execute() {
                    System.out.println("Move to spring from pipe");
                }
                public String getName() {
                    return "Move to spring from pipe";
                }
            }
    };

    private static final MenuOption[] TIME_TICK_SUBMENU = {
            new MenuOption() {
                public void execute() {
                    runMenu( WATER_FLOWS_SUBMENU);
                }
                public String getName() {
                    return "Water flows";
                }
            },
            new MenuOption() {
                public void execute() {
                    System.out.println("Pumping into broken pump");
                }
                public String getName() {
                    return "Pumping into broken pump";
                }
            },
            new MenuOption() {
                public void execute() {
                    System.out.println("Network branch filled up");
                }
                public String getName() {
                    return "Network branch filled up";
                }
            }
    };

    private static final MenuOption[] WATER_FLOWS_SUBMENU = {
            new MenuOption() {
                public void execute() {
                    System.out.println("Water flows from spring to pipe");
                }
                public String getName() {
                    return "Water flows from spring to pipe";
                }
            },
            new MenuOption() {
                public void execute() {
                    System.out.println("Cistern drains water");
                }
                public String getName() {
                    return "Cistern drains water";
                }
            },
            new MenuOption() {
                public void execute() {
                    System.out.println("Pump pumps");
                }
                public String getName() {
                    return "Pump pumps";
                }
            },
            new MenuOption() {
                public void execute() {
                    System.out.println("Water flows into pipe");
                }
                public String getName() {
                    return "Water flows into pipe";
                }
            },
            new MenuOption() {
                public void execute() {
                    System.out.println("Water is drained from pipe");
                }
                public String getName() {
                    return "Water is drained from pipe";
                }
            },
            new MenuOption() {
                public void execute() {
                    System.out.println("Water leaks from punctured pipe");
                }
                public String getName() {
                    return "Water leaks from punctured pipe";
                }
            },
            new MenuOption() {
                public void execute() {
                    System.out.println("Water leaks from pipe with free end");
                }
                public String getName() {
                    return "Water leaks from pipe with free end";
                }
            }
    };

    private static final MenuOption[] PIPE_ACTIONS_SUBMENU = {
            new MenuOption() {
                public void execute() {
                    System.out.println("Connect pipe");
                }
                public String getName() {
                    return "Connect pipe";
                }
            },
            new MenuOption() {
                public void execute() {
                    System.out.println("Disconnect pipe");
                }
                public String getName() {
                    return "Disconnect pipe";
                }
            },
            new MenuOption() {
                public void execute() {
                    System.out.println("Fix pipe");
                }
                public String getName() {
                    return "Fix pipe";
                }
            },
            new MenuOption() {
                public void execute() {
                    System.out.println("Place pipe");
                }
                public String getName() {
                    return "Place pipe";
                }
            },
            new MenuOption() {
                public void execute() {
                    System.out.println("Pickup pipe");
                }
                public String getName() {
                    return "Pickup pipe";
                }
            },
            new MenuOption() {
                public void execute() {
                    System.out.println("Puncture pipe");
                }
                public String getName() {
                    return "Puncture pipe";
                }
            }
    };

    private static final MenuOption[] PUMP_ACTIONS_SUBMENU = {
            new MenuOption() {
                public void execute() {
                    System.out.println("Fix pump");
                }
                public String getName() {
                    return "Fix pump";
                }
            },
            new MenuOption() {
                public void execute() {
                    System.out.println("Place pump");
                }
                public String getName() {
                    return "Place pump";
                }
            },
            new MenuOption() {
                public void execute() {
                    System.out.println("Mechanic sets pump");
                }
                public String getName() {
                    return "Mechanic sets pump";
                }
            },
            new MenuOption() {
                public void execute() {
                    System.out.println("Saboteur sets pump");
                }
                public String getName() {
                    return "Saboteur sets pump";
                }
            },
            new MenuOption() {
                public void execute() {
                    System.out.println("Take pump");
                }
                public String getName() {
                    return "Take pump";
                }
            }
    };

    public static void runMenu(MenuOption[] menu) {
        Scanner scanner = new Scanner(System.in);
        MenuOption[] currentMenu = menu;
        boolean isMainMenu = (currentMenu == MAIN_MENU);

        while (true) {
            System.out.println("Choose an option:");
            for (int i = 0; i < currentMenu.length; i++) {
                System.out.println("["  + (i + 1) + "] - " + currentMenu[i].getName());
            }
            if(isMainMenu) {
                System.out.println("[X] - Exit program");
            } else {
                System.out.println("[X] - Step back");
            }
            String option = scanner.nextLine().trim().toUpperCase();

            try {
                int command = Integer.parseInt(option);
                if(command > currentMenu.length || command < 1) continue;
                currentMenu[command-1].execute();
            } catch (NumberFormatException ex) {
                if(option.equals("X")){
                    break;
                }
            }
        }
    }


}

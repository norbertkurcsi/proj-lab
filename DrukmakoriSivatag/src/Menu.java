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
                    System.out.println(getName());
                    Initialize.initMoveToPipeFromPump();
                }
                public String getName() {
                    return "Move to pipe from pump";
                }
            },
            new MenuOption() {
                public void execute() {
                    System.out.println(getName());
                    Initialize.initMovetoPumpFromPipe();
                }
                public String getName() {
                    return "Move to pump from pipe";
                }
            },
            new MenuOption() {
                public void execute() {
                    System.out.println(getName());
                    Initialize.initMoveToCisternFromPipe();
                }
                public String getName() {
                    return "Move to cistern from pipe";
                }
            },
            new MenuOption() {
                public void execute() {
                    System.out.println(getName());
                    Initialize.initMoveToSpringFromPipe();
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
                    Initialize.initPumpingIntoBrokenPump();
                }
                public String getName() {
                    return "Pumping into broken pump";
                }
            },
            new MenuOption() {
                public void execute() {
                    System.out.println(getName());
                    Initialize.initNetworkBranchFilledUp();
                }
                public String getName() {
                    return "Network branch filled up";
                }
            }
    };

    private static final MenuOption[] WATER_FLOWS_SUBMENU = {
            new MenuOption() {
                public void execute() {
                    System.out.println(getName());
                    Initialize.initWaterFlowsFromSpingToPipe();
                }
                public String getName() {
                    return "Water flows from spring to pipe";
                }
            },
            new MenuOption() {
                public void execute() {
                    System.out.println(getName());
                    Initialize.initCisternDrainsWater();
                }
                public String getName() {
                    return "Cistern drains water";
                }
            },
            new MenuOption() {
                public void execute() {
                    System.out.println(getName());
                    Initialize.initPumpPumps();
                }
                public String getName() {
                    return "Pump pumps";
                }
            },
            new MenuOption() {
                public void execute() {
                    System.out.println(getName());
                    //Initialize.initWaterFlowsIntoPipe();
                }
                public String getName() {
                    return "Water flows into pipe";
                }
            },
            new MenuOption() {
                public void execute() {
                    System.out.println(getName());
                    //Initialize.initWaterIsDrainedFromPipe();
                }
                public String getName() {
                    return "Water is drained from pipe";
                }
            },
            new MenuOption() {
                public void execute() {
                    System.out.println(getName());
                    //Initialize.initWaterLeaksFromPuncturedPipe();
                }
                public String getName() {
                    return "Water leaks from punctured pipe";
                }
            },
            new MenuOption() {
                public void execute() {
                    System.out.println(getName());
                    //Initialize.initWaterLeaksFromPipeWithFreeEnd();
                }
                public String getName() {
                    return "Water leaks from pipe with free end";
                }
            }
    };

    private static final MenuOption[] PIPE_ACTIONS_SUBMENU = {
            new MenuOption() {
                public void execute() {
                    System.out.println(getName());
                    Initialize.initConnectPipe();
                }
                public String getName() {
                    return "Connect pipe";
                }
            },
            new MenuOption() {
                public void execute() {
                    System.out.println(getName());
                    Initialize.initDisconnectPipe();
                }
                public String getName() {
                    return "Disconnect pipe";
                }
            },
            new MenuOption() {
                public void execute() {
                    System.out.println(getName());
                    Initialize.initFixPipe();
                }
                public String getName() {
                    return "Fix pipe";
                }
            },
            new MenuOption() {
                public void execute() {
                    System.out.println(getName());
                    Initialize.initPlacePipe();
                }
                public String getName() {
                    return "Place pipe";
                }
            },
            new MenuOption() {
                public void execute() {
                    System.out.println(getName());
                    Initialize.initPickupPipe();
                }
                public String getName() {
                    return "Pickup pipe";
                }
            },
            new MenuOption() {
                public void execute() {
                    System.out.println(getName());
                    Initialize.initPuncturePipe();
                }
                public String getName() {
                    return "Puncture pipe";
                }
            }
    };

    private static final MenuOption[] PUMP_ACTIONS_SUBMENU = {
            new MenuOption() {
                public void execute() {
                    System.out.println(getName());
                    Initialize.initFixPump();
                }
                public String getName() {
                    return "Fix pump";
                }
            },
            new MenuOption() {
                public void execute() {
                    System.out.println(getName());
                    Initialize.initPlacePump();
                }
                public String getName() {
                    return "Place pump";
                }
            },
            new MenuOption() {
                public void execute() {
                    System.out.println(getName());
                    Initialize.initMechanicSetsPump();
                }
                public String getName() {
                    return "Mechanic sets pump";
                }
            },
            new MenuOption() {
                public void execute() {
                    System.out.println(getName());
                    Initialize.initSaboteurSetsPump();
                }
                public String getName() {
                    return "Saboteur sets pump";
                }
            },
            new MenuOption() {
                public void execute() {
                    System.out.println(getName());
                    Initialize.initTakePump();
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

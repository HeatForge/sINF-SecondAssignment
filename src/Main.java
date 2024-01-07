import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        System.out.println("Hra Diamanty. Zhomaždi všetky drahokamy v jednej miestnosti.");

        Integer[][] rooms = {{1,1,1}, {1,1,1}, {1,1,1}};

        Integer[] coordinates = {1,1};

        processInput(rooms, coordinates, false);

    }

    public static void movementInRooms(Integer[][] roomTable, Integer[] currentRoomIndex , String movementDirection, Boolean playerHoldingToken){
        if (movementDirection.equals("N")){

            currentRoomIndex[1]--;

            Integer x = currentRoomIndex[0];
            Integer y = currentRoomIndex[1];

            printStatus(roomTable, currentRoomIndex, roomTable[x][y], playerHoldingToken);


        }else if (movementDirection.equals("E")){

            currentRoomIndex[0]++;

            Integer x = currentRoomIndex[0];
            Integer y = currentRoomIndex[1];

            printStatus(roomTable, currentRoomIndex, roomTable[x][y], playerHoldingToken);

        }else if (movementDirection.equals("S")){

            currentRoomIndex[1]++;

            Integer x = currentRoomIndex[0];
            Integer y = currentRoomIndex[1];

            printStatus(roomTable, currentRoomIndex, roomTable[x][y], playerHoldingToken);

        }else if (movementDirection.equals("W")){

            currentRoomIndex[0]--;

            Integer x = currentRoomIndex[0];
            Integer y = currentRoomIndex[1];

            printStatus(roomTable, currentRoomIndex, roomTable[x][y], playerHoldingToken);

        }
    }

    public static void printStatus(Integer[][] roomTable,Integer[] currentRoomIndex, Integer numberOfDiamonds, Boolean playerHoldingToken){

        System.out.println("Si v miestnosti " + currentRoomIndex[0] + "," + currentRoomIndex[1] + ". Vidíš " + numberOfDiamonds + "diamant.");
        processInput(roomTable, currentRoomIndex, playerHoldingToken);

    }

    public static void processInput(Integer[][] roomTable, Integer[] currentRoomIndex, Boolean playerHoldingToken){
        Scanner input = new Scanner(System.in);
        System.out.print(">");
        String playerInput = input.next();

        switch (playerInput) {
            case "N", "E", "S", "W" -> movementInRooms(roomTable, currentRoomIndex, playerInput, playerHoldingToken);
            case "VEZMI", "VEZ" -> {
                if (!playerHoldingToken) {
                    playerHoldingToken = true;
                    roomTable[currentRoomIndex[0]][currentRoomIndex[1]]--;
                    processInput(roomTable, currentRoomIndex, playerHoldingToken);
                }
            }
            case "POLOŽ", "POL" -> {
                if (playerHoldingToken) {
                    playerHoldingToken = false;
                    roomTable[currentRoomIndex[0]][currentRoomIndex[1]]++;
                    processInput(roomTable, currentRoomIndex, playerHoldingToken);
                }
            }
            case "KONIEC", "KON" -> {
                for (Integer x = 0; x < roomTable.length; x++){
                    for (Integer y = 0; y < roomTable[x].length; y++){
                        if (roomTable[x][y] == 9){
                            System.out.println("HURÁ VYHRAL SI :)");
                        }
                    }
                }
            }

            case "DEBUG" -> {
                for (Integer x = 0; x < roomTable.length; x++){
                    for (Integer y = 0; y < roomTable[x].length; y++){
                        System.out.println(x + "," + y + " - " + roomTable[x][y]);
                    }
                }
                processInput(roomTable, currentRoomIndex, playerHoldingToken);
            }
            case "GATHER" -> {
                for (Integer x = 0; x < roomTable.length; x++){
                    for (Integer y = 0; y < roomTable[x].length; y++){
                       roomTable[x][y] = 0;
                    }
                }
                roomTable[1][1] = 9;
                processInput(roomTable, currentRoomIndex, playerHoldingToken);
            }
            default -> {
                System.out.println("Unknown command.");
                processInput(roomTable, currentRoomIndex, playerHoldingToken);
            }
        }

    }


}
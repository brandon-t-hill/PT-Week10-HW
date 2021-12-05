package application;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import dao.PokemonDAO;
import entity.PokeType;
import entity.Pokemon;

public class Menu {
  private PokemonDAO pokeDao = new PokemonDAO();
  private Scanner scanner = new Scanner(System.in);
  private List<String> options = Arrays.asList("Display Pokemon", "Display a Pokemon",
      "Create a Pokemon", "Update a Pokemon", "Delete a Pokemon");
  private List<String> updateOptions = Arrays.asList("Update Pokemon Name",
      "Update Pokemon Primary Type", "Update Pokemon Secondary Type");

  public void start() {
    String selection = "";

    do {
      printMenu();
      selection = scanner.nextLine();
      try {
        if (selection.equals("1")) {
          displayPokemon();
        } else if (selection.equals("2")) {
          displayMon();
        } else if (selection.equals("3")) {
          createMon();
        } else if (selection.equals("4")) {
          updateMon();
        } else if (selection.equals("5")) {
          deleteMon();
        }
      } catch (SQLException e) {
        e.printStackTrace();
      }

      System.out.println("Press enter to continue...");
      scanner.nextLine();
    } while (!selection.equals("-1"));
  }

  private void printMenu() {
    System.out.println("Select an option:");
    for (int i = 0; i < options.size(); i++) {
      System.out.printf("%3d) %s%n", i+1, options.get(i));
    }
    System.out.printf("%3d) %s%n", -1, "Exit");
  }

  private void displayPokemon() throws SQLException {
    List<Pokemon> pokemon = pokeDao.getPokemon();
    for (Pokemon mon : pokemon) {
      System.out.printf("%3d) %-12s %-10s %-10s%n", mon.getPokemonId(), mon.getName(),
          mon.getType1(), mon.getType2());
    }
  }

  private void displayMon() throws SQLException {
    System.out.println("Enter Pokemon id: ");
    int id = Integer.parseInt(scanner.nextLine());
    Pokemon mon = pokeDao.getPokemonById(id);
    if (mon != null) {
      System.out.printf("%3d) %-12s %-10s %-10s%n", mon.getPokemonId(), mon.getName(),
          mon.getType1(), mon.getType2());
    } else {
      System.out.println("There is no pokemon at that id");
    }
  }

  private void createMon() throws SQLException {
    System.out.println("Enter Pokemon Name:");
    String name = scanner.nextLine();
    System.out.println("Pick Pokemon's Primary Type:");
    typeMenu();
    int type1 = Integer.parseInt(scanner.nextLine());
    System.out.println("Pick Pokemon's Secondary Type:");
    System.out.printf("%3d) %s%n", -1, "None");
    typeMenu();
    int type2 = Integer.parseInt(scanner.nextLine());
    pokeDao.createNewPokemon(name, type1, type2);
  }

  private void typeMenu() {
    for (PokeType p : PokeType.values()) {
      System.out.printf("%3d) %s%n", p.getID(), p);
    }
  }

  private void updateMon() throws SQLException {
    String selection = "";
    String name = "";
    int type;
    System.out.println("Enter ID of Pokemon to update:");
    int id = Integer.parseInt(scanner.nextLine());
    if (pokeDao.getPokemonById(id) != null) {
      updateMenu();
      selection = scanner.nextLine();
      if (selection.equals("1")) {
        System.out.println("Enter new name:");
        name = scanner.nextLine();
        pokeDao.updatePokemonName(name, id);
      } else if (selection.equals("2")) {
        System.out.println("Enter new Primary Type:");
        typeMenu();
        type = Integer.parseInt(scanner.nextLine());
        if(type >=1 && type <= PokeType.values().length) {
          pokeDao.updatePokemonType1(type, id);
        }
      } else if (selection.equals("3")) {
        System.out.println("Enter new Secondary Type:");
        System.out.printf("%3d) %s%n", -1, "None");
        typeMenu();
        type = Integer.parseInt(scanner.nextLine());
        if (type == -1 || (type >=1 && type <= PokeType.values().length)){
          pokeDao.updatePokemonType2(type, id);
        }
      }
    } else {
      System.out.println("There is no Pokemon at that id");
    }

  }

  private void updateMenu() {
    for (int i = 0; i < updateOptions.size(); i++) {
      System.out.printf("%3d) %s%n", i + 1, updateOptions.get(i));
    }
  }
  
  private void deleteMon() throws SQLException {
    System.out.println("Enter ID of Pokemon to delete:");
    int id = Integer.parseInt(scanner.nextLine());
    if(pokeDao.getPokemonById(id) != null) {
      pokeDao.deletePokemon(id);
      System.out.println("Pokemon #" + id + " deleted");
    } else {
      System.out.println("There is no Pokemon at that id");
    }
  }
}

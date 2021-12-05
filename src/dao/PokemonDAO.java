package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import entity.Pokemon;

public class PokemonDAO {
  private Connection connection;
  private final String GET_POKEMON_QUERY = "SELECT * FROM Pokemon";
  private final String GET_POKEMON_BY_ID_QUERY = "SELECT * FROM Pokemon WHERE id = ?";
  private final String NEW_POKEMON_QUERY = "INSERT INTO Pokemon(name, type1, type2) VALUES(?, ?, ?)";
  private final String UPDATE_POKEMON_NAME_QUERY = "UPDATE Pokemon SET name = ? WHERE id = ?";
  private final String UPDATE_POKEMON_TYPE1_QUERY = "UPDATE Pokemon SET type1 = ? WHERE id = ?";
  private final String UPDATE_POKEMON_TYPE2_QUERY = "UPDATE Pokemon SET type2 = ? WHERE id = ?";
  private final String DELETE_POKEMON_QUERY = "DELETE FROM Pokemon WHERE id = ?";

  public PokemonDAO() {
    connection = DBConnection.getConnection();
  }

  public List<Pokemon> getPokemon() throws SQLException {
    ResultSet rs = connection.prepareStatement(GET_POKEMON_QUERY).executeQuery();
    List<Pokemon> pokemon = new ArrayList<>();

    while (rs.next()) {
      pokemon.add(new Pokemon(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4)));
    }
    return pokemon;
  }

  public Pokemon getPokemonById(int id) throws SQLException {
    PreparedStatement ps = connection.prepareStatement(GET_POKEMON_BY_ID_QUERY);
    ps.setInt(1, id);
    ResultSet rs = ps.executeQuery();
    if(rs.next()) {
      return new Pokemon(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4));
    }
    return null;
  }

  public void createNewPokemon(String name, int type1, int type2) throws SQLException {
    PreparedStatement ps;
    ps = connection.prepareStatement(NEW_POKEMON_QUERY);
    if (type2 == -1) {
      ps.setNull(3, Types.INTEGER);
    } else {
      ps.setInt(3, type2);
    }
    ps.setString(1, name);
    ps.setInt(2, type1);
    ps.executeUpdate();
  }

  public void updatePokemonName(String name, int id) throws SQLException {
    if (getPokemonById(id) != null) {
      PreparedStatement ps = connection.prepareStatement(UPDATE_POKEMON_NAME_QUERY);
      ps.setString(1, name);
      ps.setInt(2, id);
      ps.executeUpdate();
    }
  }
  
  public void updatePokemonType1(int type1, int id) throws SQLException {
    if (getPokemonById(id) != null) {
      PreparedStatement ps = connection.prepareStatement(UPDATE_POKEMON_TYPE1_QUERY);
      ps.setInt(1, type1);
      ps.setInt(2, id);
      ps.executeUpdate();
    }
  }
  
  public void updatePokemonType2(int type2, int id) throws SQLException {
    if (getPokemonById(id) != null) {
      PreparedStatement ps = connection.prepareStatement(UPDATE_POKEMON_TYPE2_QUERY);
      if(type2 == -1) {
        ps.setNull(1, Types.INTEGER);
      } else {
        ps.setInt(1, type2);
      }
      ps.setInt(2, id);
      ps.executeUpdate();
    }
  }
  
  public void deletePokemon(int id) throws SQLException {
    if (getPokemonById(id) != null) {
      PreparedStatement ps = connection.prepareStatement(DELETE_POKEMON_QUERY);
      ps.setInt(1, id);
      ps.executeUpdate();
    }
  }
}

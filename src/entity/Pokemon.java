package entity;

public class Pokemon {
  private int pokemonId;
  private String name;
  private PokeType type1;
  private PokeType type2;
  
  public Pokemon(int pokemonId, String name, int type1, int type2) {
    this.setPokemonId(pokemonId);
    this.setName(name);
    this.setType1(type1);
    this.setType2(type2);
  }

  public int getPokemonId() {
    return pokemonId;
  }

  public void setPokemonId(int pokemonId) {
    this.pokemonId = pokemonId;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public PokeType getType1() {
    return type1;
  }

  public void setType1(int type1) {
    this.type1 = PokeType.fromId(type1);
  }

  public PokeType getType2() {
    return type2;
  }

  public void setType2(int type2) {
    this.type2 = PokeType.fromId(type2);
  }
}

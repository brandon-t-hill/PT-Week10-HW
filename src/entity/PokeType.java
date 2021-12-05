package entity;

public enum PokeType {
  Normal(1),
  Fire(2),
  Water(3),
  Grass(4),
  Electric(5),
  Ice(6),
  Fighting(7),
  Poison(8),
  Ground(9),
  Flying(10),
  Psychic(11),
  Bug(12),
  Rock(13),
  Ghost(14),
  Dark(15),
  Dragon(16),
  Steel(17),
  Fairy(18);
  
  /*
   *  The following code was taken from https://stackoverflow.com/a/33492702
   *  It allows me to reference Pokemon types by an int ID without the overhead of a switch statement
   */
  private int ID;
  private PokeType(int id) {
    this.ID = id;
  }
  
  public static PokeType fromId(int id) {
    for (PokeType type : values()) {
      if(type.getID() == id) {
        return type;
      }
    }
    return null;
  }

  public int getID() {
    return ID;
  }
}

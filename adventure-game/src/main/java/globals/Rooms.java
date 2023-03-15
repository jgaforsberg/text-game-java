package globals;
/*
 * Constants for referencing keys of Room values inside the HashMap room map
 * Rooms coordinated from south to north for each row (lateral connections),
 * disregarding horizontal connections starting from StartRoom
 */

// TODO : Implement HashMap room map
public enum Rooms {
    StartRoom, CorridorRoom, CrossroadsRoom,
    PotionRoom, LibraryRoom,
    WiseSageRoom, TrapRoom, MediumMonsterRoom,
    TreasureRoom, DragonRoom, LockedRoom,
    DarkRoom, FountainRoom,
    MinorMonsterRoom, TreasureKeyRoom,
    NOEXIT;
}

package pt.afsmeira.ffadventuresheet.db

import pt.afsmeira.ffadventuresheet.model.Book
import pt.afsmeira.ffadventuresheet.model.BookStat
import pt.afsmeira.ffadventuresheet.model.Stat

/**
 * The initial state for [FFAdventureSheetDatabase].
 */
object InitialState {

    val books: List<Book> = listOf(
        Book(name = "The Warlock of Firetop Mountain",    coverUrl = "https://www.fightingfantasycollector.co.uk/FF1_Original.jpg"),
        Book(name = "The Citadel of Chaos",               coverUrl = "https://www.fightingfantasycollector.co.uk/FF2_Original.jpg"),
        Book(name = "The Forest of Doom",                 coverUrl = "https://www.fightingfantasycollector.co.uk/FF_Original.jpg"),
        Book(name = "Starship Traveller",                 coverUrl = "https://www.fightingfantasycollector.co.uk/FF3_Original.jpg"),
        Book(name = "City of Thieves",                    coverUrl = "https://www.fightingfantasycollector.co.uk/FF5_Original.jpg"),
        Book(name = "Deathtrap Dungeon",                  coverUrl = "https://www.fightingfantasycollector.co.uk/FF6_Original.jpg"),
        Book(name = "Island of the Lizard King",          coverUrl = "https://www.fightingfantasycollector.co.uk/FF7_Original.jpg"),
        Book(name = "Scorpion Swamp",                     coverUrl = "https://www.fightingfantasycollector.co.uk/FF8_ZigZag.jpg"),
        Book(name = "Caverns of the Snow Witch",          coverUrl = "https://www.fightingfantasycollector.co.uk/FF9_Zigzag.jpg"),
        Book(name = "House of Hell",                      coverUrl = "https://www.fightingfantasycollector.co.uk/FF10_zigzag.jpg"),
        Book(name = "Talisman of Death",                  coverUrl = "https://www.fightingfantasycollector.co.uk/FF11_ZigZag.jpg"),
        Book(name = "Space Assassin",                     coverUrl = "https://www.fightingfantasycollector.co.uk/FF12_ZigZag.jpg"),
        Book(name = "Freeway Fighter",                    coverUrl = "https://www.fightingfantasycollector.co.uk/FF13_ZigZag.jpg"),
        Book(name = "Temple of Terror",                   coverUrl = "https://www.fightingfantasycollector.co.uk/FF14_ZigZag.jpg"),
        Book(name = "The Rings of Kether",                coverUrl = "https://www.fightingfantasycollector.co.uk/FF15_Zigzag.jpg"),
        Book(name = "Seas of Blood",                      coverUrl = "https://www.fightingfantasycollector.co.uk/FF16_Zigzag.jpg"),
        Book(name = "Appointment with F.E.A.R.",          coverUrl = "https://www.fightingfantasycollector.co.uk/FF17_Zigzag.jpg"),
        Book(name = "Rebel Planet",                       coverUrl = "https://www.fightingfantasycollector.co.uk/FF18_Zigzag.jpg"),
        Book(name = "Demons of the Deep",                 coverUrl = "https://www.fightingfantasycollector.co.uk/FF19_Zigzag.jpg"),
        Book(name = "Sword of the Samurai",               coverUrl = "https://www.fightingfantasycollector.co.uk/FF20_Zigzag.jpg"),
        Book(name = "Trial of Champions",                 coverUrl = "https://www.fightingfantasycollector.co.uk/FF21_Zigzag.jpg"),
        Book(name = "Robot Commando",                     coverUrl = "https://www.fightingfantasycollector.co.uk/FF22_Zigzag.jpg"),
        Book(name = "Masks of Mayhem",                    coverUrl = "https://www.fightingfantasycollector.co.uk/FF23_ZigZag.jpg"),
        Book(name = "Creature of Havoc",                  coverUrl = "https://www.fightingfantasycollector.co.uk/FF24_Zigzag.jpg"),
        Book(name = "Beneath Nightmare Castle",           coverUrl = "https://www.fightingfantasycollector.co.uk/FF25_Dragon_Both.jpg"),
        Book(name = "Crypt of the Sorcerer",              coverUrl = "https://www.fightingfantasycollector.co.uk/FF26_Dargon_Both.jpg"),
        Book(name = "Star Strider",                       coverUrl = "https://www.fightingfantasycollector.co.uk/FF27_Dragon_both.jpg"),
        Book(name = "Phantoms of Fear",                   coverUrl = "https://www.fightingfantasycollector.co.uk/FF28_Dragon_both.jpg"),
        Book(name = "Midnight Rogue",                     coverUrl = "https://www.fightingfantasycollector.co.uk/FF29_Dargon_Both.jpg"),
        Book(name = "Chasms of Malice",                   coverUrl = "https://www.fightingfantasycollector.co.uk/FF30_Dragon_Both.jpg"),
        Book(name = "Battleblade Warrior",                coverUrl = "https://www.fightingfantasycollector.co.uk/FF31_Dragon_Front.jpg"),
        Book(name = "Slaves of the Abyss",                coverUrl = "https://www.fightingfantasycollector.co.uk/FF32_Dragon_Both.jpg"),
        Book(name = "Sky Lord",                           coverUrl = "https://www.fightingfantasycollector.co.uk/FF33_Dragon_Both.jpg"),
        Book(name = "Stealer of Souls",                   coverUrl = "https://www.fightingfantasycollector.co.uk/FF34_Dragon_front.jpg"),
        Book(name = "Daggers of Darkness",                coverUrl = "https://vignette2.wikia.nocookie.net/fightingfantasy/images/1/19/Figfan35.jpg"),
        Book(name = "Armies of Death",                    coverUrl = "https://www.fightingfantasycollector.co.uk/FF36_Dragon_Both.jpg"),
        Book(name = "Portal of Evil",                     coverUrl = "https://www.fightingfantasycollector.co.uk/FF37_Dragon_Spine.jpg"),
        Book(name = "Vault of the Vampire",               coverUrl = "https://www.fightingfantasycollector.co.uk/FF38_Dragon_Spine.jpg"),
        Book(name = "Fangs of Fury",                      coverUrl = "https://www.fightingfantasycollector.co.uk/FF39_Dragon_spine.jpg"),
        Book(name = "Dead of Night",                      coverUrl = "https://www.fightingfantasycollector.co.uk/FF40_Dragon_Spine.jpg"),
        Book(name = "Master of Chaos",                    coverUrl = "https://www.fightingfantasycollector.co.uk/FF41_Dargon_Spine.jpg"),
        Book(name = "Black Vein Prophecy",                coverUrl = "https://www.fightingfantasycollector.co.uk/FF42_Dragon_Spine.jpg"),
        Book(name = "The Keep of the Lich-Lord",          coverUrl = "https://www.fightingfantasycollector.co.uk/FF43_Dragon_spine.jpg"),
        Book(name = "Legend of the Shadow Warriors",      coverUrl = "https://www.fightingfantasycollector.co.uk/FF44_Dragon_Spine.jpg"),
        Book(name = "Spectral Stalkers",                  coverUrl = "https://www.fightingfantasycollector.co.uk/FF45_Dragon_spine.jpg"),
        Book(name = "Tower of Destruction",               coverUrl = "https://www.fightingfantasycollector.co.uk/FF46.jpg"),
        Book(name = "The Crimson Tide",                   coverUrl = "https://www.fightingfantasycollector.co.uk/FF47_Black_Dragon.jpg"),
        Book(name = "Moonrunner",                         coverUrl = "https://www.fightingfantasycollector.co.uk/FF48_Dragon_spine.jpg"),
        Book(name = "Siege of Sardath",                   coverUrl = "https://www.fightingfantasycollector.co.uk/FF49_Dragon_spine.jpg"),
        Book(name = "Return to Firetop Mountain",         coverUrl = "https://www.fightingfantasycollector.co.uk/FF50.jpg"),
        Book(name = "Island of the Undead",               coverUrl = "https://www.fightingfantasycollector.co.uk/FF51.jpg"),
        Book(name = "Night Dragon",                       coverUrl = "https://www.fightingfantasycollector.co.uk/FF52.jpg"),
        Book(name = "Spellbreaker",                       coverUrl = "https://www.fightingfantasycollector.co.uk/FF53.jpg"),
        Book(name = "Legend of Zagor",                    coverUrl = "https://www.fightingfantasycollector.co.uk/FF54.jpg"),
        Book(name = "Deathmoor",                          coverUrl = "https://www.fightingfantasycollector.co.uk/FF55.jpg"),
        Book(name = "Knights of Doom",                    coverUrl = "https://www.fightingfantasycollector.co.uk/FF56.jpg"),
        Book(name = "Magehunter",                         coverUrl = "https://www.fightingfantasycollector.co.uk/FF57.jpg"),
        Book(name = "Revenge of the Vampire",             coverUrl = "https://www.fightingfantasycollector.co.uk/FF58.jpg"),
        Book(name = "Curse of the Mummy",                 coverUrl = "https://www.fightingfantasycollector.co.uk/FF59.jpg"),
        Book(name = "Eye of the Dragon",                  coverUrl = "https://vignette.wikia.nocookie.net/fightingfantasy/images/b/bc/60_V2(W1)_01.jpg"),
        Book(name = "Bloodbones",                         coverUrl = "https://vignette.wikia.nocookie.net/fightingfantasy/images/a/a4/61_V2(W1)_01.jpg"),
        Book(name = "Howl of the Werewolf",               coverUrl = "https://vignette.wikia.nocookie.net/fightingfantasy/images/c/c5/HowlShield.jpg"),
        Book(name = "Stormslayer",                        coverUrl = "https://vignette.wikia.nocookie.net/fightingfantasy/images/1/15/StormslayerShield.jpg"),
        Book(name = "Night of the Necromancer",           coverUrl = "https://vignette.wikia.nocookie.net/fightingfantasy/images/1/1d/Ff7new_sml.jpg"),
        Book(name = "Blood of the Zombies",               coverUrl = "https://cdn1-www.gamerevolution.com/assets/uploads/2012/10/fighting-fantasy-blood-of-the-zombies.jpg"),
        Book(name = "Sorcery! The Shamutanti Hills",      coverUrl = "https://www.fightingfantasycollector.co.uk/Sorcery_1__Red.jpg"),
        Book(name = "Sorcery! Kharé - Cityport of Traps", coverUrl = "https://www.fightingfantasycollector.co.uk/Sorcery_2_Red.jpg"),
        Book(name = "Sorcery! The Seven Serpents",        coverUrl = "https://www.fightingfantasycollector.co.uk/Sorcery_3_Red.jpg"),
        Book(name = "Sorcery! The Crown of Kings",        coverUrl = "https://www.fightingfantasycollector.co.uk/Sorcery_4_Red.jpg")
    )

    val stats: List<Stat> = listOf(
        Stat(name = "Skill",      type = Stat.Type.INT,     setup = true),
        Stat(name = "Stamina",    type = Stat.Type.INT,     setup = true),
        Stat(name = "Luck",       type = Stat.Type.INT,     setup = true),
        Stat(name = "Potion",     type = Stat.Type.SPECIAL, setup = true,  baseValue = "2"),
        Stat(name = "Provisions", type = Stat.Type.BUTTON,  setup = false, baseValue = "10"),
        Stat(name = "Gold",       type = Stat.Type.INT,     setup = false, baseValue = "0")
    )

    val bookStats: List<BookStat> = listOf(
        BookStat(1, 1),
        BookStat(1, 2),
        BookStat(1, 3),
        BookStat(1, 4),
        BookStat(1, 5),
        BookStat(1, 6)
    )
}

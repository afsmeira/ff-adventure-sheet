-- We want the DB to start pre-populated, and that must be done through an asset file.
-- That asset file is generate by running these SQL statements in sqlite:
--      `sqlite3 ff-adventure-sheet-v01.db < ff-adventure-sheet-v01.sql`
-- Alongside this, Room uses the Entity classes and Daos to generate a schema file. The schema file
-- also has SQL statements, and could create the DB tables (and thus we wouldn't need to do it
-- here), and indexes, but the current SQL runs before that and we can't run just INSERT statements
-- because there are no tables yet.
-- The problem is that we must keep the DDL of this file in sync with the Entity classes and daos.
-- **However**, we are letting Room create tables that are not necessary upfront and indexes on
-- foreign keys.
-- TODO Investigate initializing the DB through code

CREATE TABLE book (
    id        INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
    name      TEXT NOT NULL,
    cover_url TEXT NOT NULL
);

-- TODO It's not great to depend on "random" internet sites, so the images should be hosted somewhere under my control
INSERT INTO book (name, cover_url) VALUES
    ("The Warlock of Firetop Mountain",    "https://www.fightingfantasycollector.co.uk/FF1_Original.jpg"),
    ("The Citadel of Chaos",               "https://www.fightingfantasycollector.co.uk/FF2_Original.jpg"),
    ("The Forest of Doom",                 "https://www.fightingfantasycollector.co.uk/FF_Original.jpg"),
    ("Starship Traveller",                 "https://www.fightingfantasycollector.co.uk/FF3_Original.jpg"),
    ("City of Thieves",                    "https://www.fightingfantasycollector.co.uk/FF5_Original.jpg"),
    ("Deathtrap Dungeon",                  "https://www.fightingfantasycollector.co.uk/FF6_Original.jpg"),
    ("Island of the Lizard King",          "https://www.fightingfantasycollector.co.uk/FF7_Original.jpg"),
    ("Scorpion Swamp",                     "https://www.fightingfantasycollector.co.uk/FF8_ZigZag.jpg"),
    ("Caverns of the Snow Witch",          "https://www.fightingfantasycollector.co.uk/FF9_Zigzag.jpg"),
    ("House of Hell",                      "https://www.fightingfantasycollector.co.uk/FF10_zigzag.jpg"),
    ("Talisman of Death",                  "https://www.fightingfantasycollector.co.uk/FF11_ZigZag.jpg"),
    ("Space Assassin",                     "https://www.fightingfantasycollector.co.uk/FF12_ZigZag.jpg"),
    ("Freeway Fighter",                    "https://www.fightingfantasycollector.co.uk/FF13_ZigZag.jpg"),
    ("Temple of Terror",                   "https://www.fightingfantasycollector.co.uk/FF14_ZigZag.jpg"),
    ("The Rings of Kether",                "https://www.fightingfantasycollector.co.uk/FF15_Zigzag.jpg"),
    ("Seas of Blood",                      "https://www.fightingfantasycollector.co.uk/FF16_Zigzag.jpg"),
    ("Appointment with F.E.A.R.",          "https://www.fightingfantasycollector.co.uk/FF17_Zigzag.jpg"),
    ("Rebel Planet",                       "https://www.fightingfantasycollector.co.uk/FF18_Zigzag.jpg"),
    ("Demons of the Deep",                 "https://www.fightingfantasycollector.co.uk/FF19_Zigzag.jpg"),
    ("Sword of the Samurai",               "https://www.fightingfantasycollector.co.uk/FF20_Zigzag.jpg"),
    ("Trial of Champions",                 "https://www.fightingfantasycollector.co.uk/FF21_Zigzag.jpg"),
    ("Robot Commando",                     "https://www.fightingfantasycollector.co.uk/FF22_Zigzag.jpg"),
    ("Masks of Mayhem",                    "https://www.fightingfantasycollector.co.uk/FF23_ZigZag.jpg"),
    ("Creature of Havoc",                  "https://www.fightingfantasycollector.co.uk/FF24_Zigzag.jpg"),
    ("Beneath Nightmare Castle",           "https://www.fightingfantasycollector.co.uk/FF25_Dragon_Both.jpg"),
    ("Crypt of the Sorcerer",              "https://www.fightingfantasycollector.co.uk/FF26_Dargon_Both.jpg"),
    ("Star Strider",                       "https://www.fightingfantasycollector.co.uk/FF27_Dragon_both.jpg"),
    ("Phantoms of Fear",                   "https://www.fightingfantasycollector.co.uk/FF28_Dragon_both.jpg"),
    ("Midnight Rogue",                     "https://www.fightingfantasycollector.co.uk/FF29_Dargon_Both.jpg"),
    ("Chasms of Malice",                   "https://www.fightingfantasycollector.co.uk/FF30_Dragon_Both.jpg"),
    ("Battleblade Warrior",                "https://www.fightingfantasycollector.co.uk/FF31_Dragon_Front.jpg"),
    ("Slaves of the Abyss",                "https://www.fightingfantasycollector.co.uk/FF32_Dragon_Both.jpg"),
    ("Sky Lord",                           "https://www.fightingfantasycollector.co.uk/FF33_Dragon_Both.jpg"),
    ("Stealer of Souls",                   "https://www.fightingfantasycollector.co.uk/FF34_Dragon_front.jpg"),
    ("Daggers of Darkness",                "https://vignette2.wikia.nocookie.net/fightingfantasy/images/1/19/Figfan35.jpg"),
    ("Armies of Death",                    "https://www.fightingfantasycollector.co.uk/FF36_Dragon_Both.jpg"),
    ("Portal of Evil",                     "https://www.fightingfantasycollector.co.uk/FF37_Dragon_Spine.jpg"),
    ("Vault of the Vampire",               "https://www.fightingfantasycollector.co.uk/FF38_Dragon_Spine.jpg"),
    ("Fangs of Fury",                      "https://www.fightingfantasycollector.co.uk/FF39_Dragon_spine.jpg"),
    ("Dead of Night",                      "https://www.fightingfantasycollector.co.uk/FF40_Dragon_Spine.jpg"),
    ("Master of Chaos",                    "https://www.fightingfantasycollector.co.uk/FF41_Dargon_Spine.jpg"),
    ("Black Vein Prophecy",                "https://www.fightingfantasycollector.co.uk/FF42_Dragon_Spine.jpg"),
    ("The Keep of the Lich-Lord",          "https://www.fightingfantasycollector.co.uk/FF43_Dragon_spine.jpg"),
    ("Legend of the Shadow Warriors",      "https://www.fightingfantasycollector.co.uk/FF44_Dragon_Spine.jpg"),
    ("Spectral Stalkers",                  "https://www.fightingfantasycollector.co.uk/FF45_Dragon_spine.jpg"),
    ("Tower of Destruction",               "https://www.fightingfantasycollector.co.uk/FF46.jpg"),
    ("The Crimson Tide",                   "https://www.fightingfantasycollector.co.uk/FF47_Black_Dragon.jpg"),
    ("Moonrunner",                         "https://www.fightingfantasycollector.co.uk/FF48_Dragon_spine.jpg"),
    ("Siege of Sardath",                   "https://www.fightingfantasycollector.co.uk/FF49_Dragon_spine.jpg"),
    ("Return to Firetop Mountain",         "https://www.fightingfantasycollector.co.uk/FF50.jpg"),
    ("Island of the Undead",               "https://www.fightingfantasycollector.co.uk/FF51.jpg"),
    ("Night Dragon",                       "https://www.fightingfantasycollector.co.uk/FF52.jpg"),
    ("Spellbreaker",                       "https://www.fightingfantasycollector.co.uk/FF53.jpg"),
    ("Legend of Zagor",                    "https://www.fightingfantasycollector.co.uk/FF54.jpg"),
    ("Deathmoor",                          "https://www.fightingfantasycollector.co.uk/FF55.jpg"),
    ("Knights of Doom",                    "https://www.fightingfantasycollector.co.uk/FF56.jpg"),
    ("Magehunter",                         "https://www.fightingfantasycollector.co.uk/FF57.jpg"),
    ("Revenge of the Vampire",             "https://www.fightingfantasycollector.co.uk/FF58.jpg"),
    ("Curse of the Mummy",                 "https://www.fightingfantasycollector.co.uk/FF59.jpg"),
    ("Eye of the Dragon",                  "https://vignette.wikia.nocookie.net/fightingfantasy/images/b/bc/60_V2(W1)_01.jpg"),
    ("Bloodbones",                         "https://vignette.wikia.nocookie.net/fightingfantasy/images/a/a4/61_V2(W1)_01.jpg"),
    ("Howl of the Werewolf",               "https://vignette.wikia.nocookie.net/fightingfantasy/images/c/c5/HowlShield.jpg"),
    ("Stormslayer",                        "https://vignette.wikia.nocookie.net/fightingfantasy/images/1/15/StormslayerShield.jpg"),
    ("Night of the Necromancer",           "https://vignette.wikia.nocookie.net/fightingfantasy/images/1/1d/Ff7new_sml.jpg"),
    ("Blood of the Zombies",               "https://cdn1-www.gamerevolution.com/assets/uploads/2012/10/fighting-fantasy-blood-of-the-zombies.jpg"),
    ("Sorcery! The Shamutanti Hills",      "https://www.fightingfantasycollector.co.uk/Sorcery_1__Red.jpg"),
    ("Sorcery! Kharé - Cityport of Traps", "https://www.fightingfantasycollector.co.uk/Sorcery_2_Red.jpg"),
    ("Sorcery! The Seven Serpents",        "https://www.fightingfantasycollector.co.uk/Sorcery_3_Red.jpg"),
    ("Sorcery! The Crown of Kings",        "https://www.fightingfantasycollector.co.uk/Sorcery_4_Red.jpg");

CREATE TABLE stat (
    id              INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
    name            TEXT NOT NULL,
    type            TEXT NOT NULL,
    possible_values TEXT NOT NULL
);

INSERT INTO stat (name, type, possible_values) VALUES
    -- stats used in every book
    ("Skill",   "INT", "[]"),
    ("Stamina", "INT", "[]"),
    ("Luck",    "INT", "[]"),

    -- stats used in more than one book
    ("Equipment",                     "TEXT",       "[]"),
    ("Provisions",                    "INT",        "[]"),
    ("Gold",                          "INT",        "[]"),
    ("Jewels",                        "TEXT",       "[]"),
    ("Notes",                         "TEXT",       "[]"),
    ("Potions",                       "TEXT",       "[]"),
    ("Armour",                        "INT",        "[]"),
    ("Weapons Strength",              "INT",        "[]"),
    ("Shields",                       "INT",        "[]"),
    ("Money",                         "INT",        "[]"),
    ("Fear",                          "INT",        "[]"),
    ("Clues",                         "TEXT",       "[]"),
    ("Fuel",                          "TEXT",       "[]"),
    ("Credits",                       "INT",        "[]"),
    ("Oxygen",                        "INT",        "[]"),
    ("Treasure",                      "TEXT",       "[]"),
    ("Abilities",                     "TEXT",       "[]"),
    ("Spells",                        "TEXT",       "[]"),
    ("Honour",                        "INT",        "[]"),
    ("Time",                          "INT",        "[]"),
    ("Armour",                        "TEXT",       "[]"), -- active armour, other armours go to equipment
    ("Faith",                         "INT",        "[]"),
    ("Magic",                         "INT",        "[]"),
    ("Weapon",                        "TEXT",       "[]"), -- active weapon, other weapons go to equipment
    ("Poison",                        "INT",        "[]"),
    ("Codewords",                     "TEXT",       "[]"),
    ("Day of the Week",               "MULTI_OPT",  "[""Stormsday"", ""Moonsday"", ""Fireday"", ""Earthday"", ""Windsday"", ""Seaday"", ""Highday""]"),
    ("Medkit",                        "INT",        "[]"),
    ("Bonuses, Penalties and Curses", "TEXT",       "[]"),
    ("Libra - Goddess of Justice",    "SINGLE_OPT", "[""Revitalization"", ""Escape"", ""Removal of Curses and Diseases""]"),

    -- the citadel of chaos
    ("Spells", "MULTI_OPT_REPEAT", "[""Creature Copy"", ""E.S.P."", ""Fire"", ""Fool's Gold"", ""Illusion"", ""Levitation"", ""Luck"", ""Shielding"", ""Skill"", ""Stamina"", ""Strength"", ""Weakness""]"),

    -- starship traveller
    ("Science Officer - Skill",       "INT", "[]"),
    ("Science Officer - Stamina",     "INT", "[]"),
    ("Medical Officer - Skill",       "INT", "[]"),
    ("Medical Officer - Stamina",     "INT", "[]"),
    ("Engineering Officer - Skill",   "INT", "[]"),
    ("Engineering Officer - Stamina", "INT", "[]"),
    ("Security Officer - Skill",      "INT", "[]"),
    ("Security Officer - Stamina",    "INT", "[]"),
    ("Security Guard 1 - Skill",      "INT", "[]"),
    ("Security Guard 1 - Stamina",    "INT", "[]"),
    ("Security Guard 2 - Skill",      "INT", "[]"),
    ("Security Guard 2 - Stamina",    "INT", "[]"),

    -- scorpion swamp
    ("Spell Gems and Spells", "MULTI_OPT_REPEAT", "[""Skill"", ""Stamina"", ""Luck"", ""Fire"", ""Ice"", ""Illusion"", ""Friendship"", ""Growth"", ""Bless"", ""Fear"", ""Withering"", ""Curse""]"),

    -- space assassin
    ("Weapons",   "MULTI_OPT_REPEAT", "[""Electric Lash"", ""Assault Blaster"", ""Grenade"", ""Gravity Bomb""]"),
    ("Pep Pills", "INT",              "[]"),

    -- freeway fighter
    ("Firepower",         "INT",  "[]"),
    ("Rockets",           "INT",  "[]"),
    ("Iron Spikes",       "INT",  "[]"),
    ("Oil",               "INT",  "[]"),
    ("Car Modifications", "TEXT", "[]"),
    ("Spare Wheels",      "INT",  "[]"),

    -- temple of terror
    ("Spells", "MULTI_OPT", "[""Open Door"", ""Creature Sleep"", ""Magic Arrow"", ""Language"", ""Read Symbols"", ""Light"", ""Fire"", ""Jump"", ""Detect Trap"", ""Create Water""]"),

    -- the rings of kether
    ("Smart Missiles", "INT", "[]"),
    ("Energy Tablets", "INT", "[]"),

    -- seas of blood
    ("Crew Strike",   "INT",  "[]"),
    ("Crew Strength", "INT",  "[]"),
    ("Booty",         "TEXT", "[]"),

    -- appointment with FEAR
    ("Super Power", "SINGLE_OPT", "[""Super Strength"", ""Psi-Powers"", ""Enhanced Technological Skill"", ""Energy Blast""]"),
    ("Hero Points", "INT", "[]"),

    -- rebel planet
    ("Code Clues", "TEXT", "[]"),

    -- sword of the samurai
    ("Special Skill", "SINGLE_OPT", "[""Kyujutsu"", ""Iaijutsu"", ""Karumijutsu"", ""Ni-to-Kenjutsu""]"),

    -- robot commando
    ("Robot",             "TEXT",       "[]"),
    ("Speed",             "SINGLE_OPT", "[""Slow"", ""Medium"", ""Fast"", ""Very Fast""]"),
    ("Bonus",             "INT",        "[]"),
    ("Special Abilities", "TEXT",       "[]"),

    -- beneath nightmare castle
    ("Willpower", "INT", "[]"),

    -- star strider
    ("Calculations", "TEXT", "[]"),

    -- phantoms of fear
    ("Power", "INT", "[]"),

    -- midnight rogue
    ("Backpack Items", "TEXT",      "[]"),
    ("Special Skills", "MULTI_OPT", "[""Climb"", ""Hide"", ""Pick Lock"", ""Pick Pocket"", ""Secret Signs"", ""Sneak"", ""Spot Hidden""]"),

    -- chasms of malice
    ("Tabasha the Bazouk", "TEXT",      "[]"),
    ("Khuddam",            "MULTI_OPT", "[""Geshrak"", ""Gurskut"", ""Friankara"", ""Barkek"", ""Griffkek"", ""Churka"", ""Kahhrac""]"),
    ("Cyphers",            "TEXT",      "[]"),

    -- battleblade warrior
    ("Special Items", "TEXT", "[]"),

    -- sky lord
    ("Starhip", "TEXT", "[]"),
    ("Rating",  "INT",  "[]"),
    ("Lasers",  "INT",  "[]"),

    -- daggers of darkness
    ("Medallions", "MULTI_OPT", "[""Yigenik"", ""Uruz"", ""Bogomil"", ""Hulugu"", ""Korkut"", ""Kazilik""]"),

    -- armies of death
    ("Soldiers",     "TEXT",       "[]"),
    ("Situation",    "SINGLE_OPT", "[""Superior"", ""Even"", ""Inferior""]"),
    ("Total Troops", "INT",        "[]"),

    -- vault of the vampire
    ("Afflictions", "TEXT", "[]"),

    -- fangs of fury
    ("Citadel Walls", "INT",  "[]"),
    ("Cubes",         "TEXT", "[]"),
    ("Gems",          "TEXT", "[]"),

    -- dead of night
    ("Talents",    "MULTI_OPT", "[""Banish Undead"", ""Darkveil"", ""Heal"", ""Holy Circle"", ""Meditation"", ""Sense Demon"", ""Speak Demon""]"),
    ("Holy Water", "INT",       "[]"),
    ("Evil",       "INT",       "[]"),

    -- master of chaos
    ("Skills",    "MULTI_OPT", "[""Acute Hearing"", ""Animal Wisdom"", ""Blindsight"", ""Climbing"", ""Move Silently"", ""Tracking""]"),
    ("Notoriety", "INT",       "[]"),

    -- keep of the lich-lord
    ("Resolve",     "INT", "[]"),
    ("Alarm value", "INT", "[]"),

    -- spectral stalkers
    ("Trail",                     "INT",  "[]"),
    ("Potions and Magical Items", "TEXT", "[]"),

    -- tower of destruction
    ("Enemy Encounter", "TEXT", "[]"),
    ("Possessions",     "TEXT", "[]"),

    -- the crimson tide
    ("Ferocity", "INT", "[]"),
    ("Age",      "INT", "[]"),

    -- moonrunner
    ("Skills", "MULTI_OPT", "[""Acrobatics"", ""Climb"", ""Combat"", ""Con"", ""Disguise"", ""Lock Picking"", ""Sleight of Hand"", ""Sneak"", ""Tracking""]"),

    -- siege of sardath
    ("Arrows",           "INT", "[]"),
    ("Bundles of Herbs", "INT", "[]"),

    -- island of the undead
    ("Presence", "INT", "[]"),

    -- night dragon
    ("Nemesis Points", "INT", "[]"),

    -- legend of zagor
    ("Talismans",     "TEXT",      "[]"),
    ("Daggers",       "TEXT",      "[]"),
    ("Advantages",    "MULTI_OPT", "[""Can't be surprised"", ""Can use any weapons"", ""Add 2 to attack strength vs. stone monsters"", ""Add 2 to skill when testing spot skill""]"),
    ("Disadvantages", "MULTI_OPT", "[""Can't wear plate mail"", ""No bonus to attack strength with chain mail"", ""Subtract 2 from attack strength with crossbow"", ""Can't use longbow or two-handed weapon"", ""Can't use metal armor, bow or two-handed weapon""]"),

    -- knights of doom
    ("Special Skills", "MULTI_OPT", "[""Battle Tactics"", ""Ride"", ""Target"", ""Tracking"", ""Weapon"", ""Arcane Lore"", ""Banish Spirit"", ""Commune"", ""Holy Strike""]"),

    -- magehunter
    ("Companion Skill",   "INT", "[]"),
    ("Companion Stamina", "INT", "[]"),

    -- revenge of the vampire
    ("Blood Points", "INT", "[]"),

    -- howl of the werewolf
    ("Change", "INT", "[]"),

    -- stormslayer
    ("Damage", "INT", "[]"),

    -- night of the necromancer
    ("Will",         "INT", "[]"),
    ("Host Skill",   "INT", "[]"),
    ("Host Stamina", "INT", "[]"),

    -- blood of the zombies
    ("Dollars",                        "INT", "[]"),
    ("Grenades",                       "INT", "[]"),
    ("Total Number of Zombies Killed", "INT", "[]"),
    ("Number",                         "INT", "[]");

CREATE TABLE adventure (
    id             INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
    created_at     INTEGER NOT NULL,
    updated_at     INTEGER NOT NULL,
    book_id        INTEGER NOT NULL REFERENCES book(id),
    last_paragraph INTEGER NOT NULL
);

CREATE TABLE book_stat (
    book_id INTEGER NOT NULL REFERENCES book(id),
    stat_id INTEGER NOT NULL REFERENCES stat(id),
    PRIMARY KEY (book_id, stat_id)
);

-- massive insert divided into several inserts because sqlite crashes otherwise
INSERT INTO book_stat (book_id, stat_id) VALUES
	(1,1),
	(1,2),
	(1,3),
	(1,4),
	(1,5),
	(1,6),
	(1,7),
	(1,8),
	(1,9),

	(2,1),
	(2,2),
	(2,3),
	(2,4),
	(2,6),
	(2,8),
	(2,26),
	(2,34),

	(3,1),
	(3,2),
	(3,3),
	(3,4),
	(3,5),
	(3,6),
	(3,7),
	(3,8),
	(3,9),

	(4,1),
	(4,2),
	(4,3),
	(4,35),
	(4,36),
	(4,37),
	(4,38),
	(4,39),
	(4,40),
	(4,41),
	(4,42),
	(4,43),
	(4,44),
	(4,45),
	(4,46),
	(4,11),
	(4,12),

	(5,1),
	(5,2),
	(5,3),
	(5,4),
	(5,5),
	(5,6),
	(5,7),
	(5,8),
	(5,9),

	(6,1),
	(6,2),
	(6,3),
	(6,4),
	(6,5),
	(6,6),
	(6,7),
	(6,8),
	(6,9),

	(7,1),
	(7,2),
	(7,3),
	(7,4),
	(7,5),
	(7,6),
	(7,7),
	(7,8),
	(7,9),

	(8,1),
	(8,2),
	(8,3),
	(8,4),
	(8,47),

	(9,1),
	(9,2),
	(9,3),
	(9,4),
	(9,5),
	(9,6),
	(9,7),
	(9,8),
	(9,9),

	(10,1),
	(10,2),
	(10,3),
	(10,4),
	(10,8),
	(10,14);

INSERT INTO book_stat (book_id, stat_id) VALUES
	(11,1),
	(11,2),
	(11,3),
	(11,4),
	(11,5),
	(11,6),
	(11,7),
	(11,8),
	(11,9),

	(12,1),
	(12,2),
	(12,3),
	(12,4),
	(12,10),
	(12,48),
	(12,49),

	(13,1),
	(13,2),
	(13,3),
	(13,4),
	(13,31),
	(13,17),
	(13,50),
	(13,51),
	(13,52),
	(13,53),
	(13,54),
	(13,55),
	(13,10),
	(13,16),

	(14,1),
	(14,2),
	(14,3),
	(14,4),
	(14,5),
	(14,6),
	(14,56),

	(15,1),
	(15,2),
	(15,3),
	(15,4),
	(15,11),
	(15,12),
	(15,57),
	(15,58),
	(15,13),

	(16,1),
	(16,2),
	(16,3),
	(16,4),
	(16,59),
	(16,60),
	(16,61),

	(17,1),
	(17,2),
	(17,3),
	(17,15),
	(17,62),
	(17,63),

	(18,1),
	(18,2),
	(18,3),
	(18,4),
	(18,13),
	(18,64),

	(19,1),
	(19,2),
	(19,3),
	(19,4),
	(19,5),
	(19,6),
	(19,7),
	(19,8),
	(19,9),

	(20,1),
	(20,2),
	(20,3),
	(20,5),
	(20,8),
	(20,22),
	(20,65);

INSERT INTO book_stat (book_id, stat_id) VALUES
	(21,1),
	(21,2),
	(21,3),
	(21,4),
	(21,8),

	(22,1),
	(22,2),
	(22,3),
	(22,4),
	(22,10),
	(22,66),
	(22,67),
	(22,68),
	(22,69),
	(22,31),

	(23,1),
	(23,2),
	(23,3),
	(23,4),
	(23,5),
	(23,6),
	(23,7),
	(23,8),
	(23,9),

	(24,1),
	(24,2),
	(24,3),
	(24,4),
	(24,8),

	(25,1),
	(25,2),
	(25,3),
	(25,4),
	(25,5),
	(25,6),
	(25,9),
	(25,70),

	(26,1),
	(26,2),
	(26,3),
	(26,4),
	(26,8),
	(26,19),

	(27,1),
	(27,2),
	(27,3),
	(27,23),
	(27,18),
	(27,14),
	(27,15),
	(27,71),

	(28,1),
	(28,2),
	(28,3),
	(28,4),
	(28,5),
	(28,8),
	(28,9),
	(28,72),

	(29,1),
	(29,2),
	(29,3),
	(29,4),
	(29,5),
	(29,8),
	(29,73),
	(29,74),

	(30,1),
	(30,2),
	(30,3),
	(30,5),
	(30,75),
	(30,76),
	(30,77),
	(30,16),
	(30,20),
	(30,21);

INSERT INTO book_stat (book_id, stat_id) VALUES
	(31,1),
	(31,2),
	(31,3),
	(31,4),
	(31,5),
	(31,78),

	(32,1),
	(32,2),
	(32,3),
	(32,4),
	(32,5),
	(32,6),
	(32,7),
	(32,8),
	(32,9),
	(32,23),

	(33,1),
	(33,2),
	(33,3),
	(33,4),
	(33,5),
	(33,8),
	(33,12),
	(33,17),
	(33,18),
	(33,79),
	(33,80),
	(33,81),

	(34,1),
	(34,2),
	(34,3),
	(34,4),
	(34,5),
	(34,8),
	(34,19),

	(35,1),
	(35,2),
	(35,3),
	(35,4),
	(35,5),
	(35,6),
	(35,8),
	(35,20),
	(35,21),
	(35,28),
	(35,82),

	(36,1),
	(36,2),
	(36,3),
	(36,6),
	(36,19),
	(36,83),
	(36,84),

	(37,1),
	(37,2),
	(37,3),
	(37,4),
	(37,5),
	(37,6),
	(37,9),

	(38,1),
	(38,2),
	(38,3),
	(38,4),
	(38,5),
	(38,8),
	(38,19),
	(38,25),
	(38,21),
	(38,86),

	(39,1),
	(39,2),
	(39,3),
	(39,4),
	(39,5),
	(39,6),
	(39,8),
	(39,87),
	(39,88),
	(39,89),

	(40,1),
	(40,2),
	(40,3),
	(40,4),
	(40,5),
	(40,6),
	(40,8),
	(40,90),
	(40,91),
	(40,92);

INSERT INTO book_stat (book_id, stat_id) VALUES
	(41,1),
	(41,2),
	(41,3),
	(41,4),
	(41,5),
	(41,8),
	(41,93),
	(41,94),
	(41,19),

	(42,1),
	(42,2),
	(42,3),
	(42,4),
	(42,5),
	(42,8),
	(42,19),

	(43,1),
	(43,2),
	(43,3),
	(43,4),
	(43,5),
	(43,8),
	(43,19),
	(43,95),
	(43,96),

	(44,1),
	(44,2),
	(44,3),
	(44,4),
	(44,5),
	(44,6),
	(44,8),
	(44,24),
	(44,27),

	(45,1),
	(45,2),
	(45,3),
	(45,4),
	(45,5),
	(45,6),
	(45,27),
	(45,97),
	(45,98),

	(46,1),
	(46,2),
	(46,3),
	(46,4),
	(46,5),
	(46,22),
	(46,23),
	(46,99),
	(46,100),

	(47,1),
	(47,2),
	(47,3),
	(47,4),
	(47,5),
	(47,8),
	(47,101),
	(47,102),
	(47,13),

	(48,1),
	(48,2),
	(48,3),
	(48,4),
	(48,5),
	(48,6),
	(48,8),
	(48,103),

	(49,1),
	(49,2),
	(49,3),
	(49,4),
	(49,8),
	(49,6),
	(49,104),
	(49,105),
	(49,30),

	(50,1),
	(50,2),
	(50,3),
	(50,4),
	(50,19),
	(50,24),
	(50,27);

INSERT INTO book_stat (book_id, stat_id) VALUES
	(51,1),
	(51,2),
	(51,3),
	(51,4),
	(51,5),
	(51,106),

	(52,1),
	(52,2),
	(52,3),
	(52,4),
	(52,5),
	(52,19),
	(52,22),
	(52,23),
	(52,107),

	(53,1),
	(53,2),
	(53,3),
	(53,4),
	(53,5),
	(53,6),
	(53,8),
	(53,19),
	(53,25),

	(54,1),
	(54,2),
	(54,3),
	(54,5),
	(54,6),
	(54,8),
	(54,19),
	(54,26),
	(54,108),
	(54,109),
	(54,110),
	(54,111),

	(55,1),
	(55,2),
	(55,3),
	(55,4),
	(55,5),
	(55,6),
	(55,8),
	(55,9),

	(56,1),
	(56,2),
	(56,3),
	(56,4),
	(56,6),
	(56,8),
	(56,22),
	(56,23),
	(56,27),
	(56,112),

	(57,1),
	(57,2),
	(57,3),
	(57,4),
	(57,5),
	(57,8),
	(57,113),
	(57,114),
	(57,13),

	(58,1),
	(58,2),
	(58,3),
	(58,4),
	(58,5),
	(58,19),
	(58,25),
	(58,115),

	(59,1),
	(59,2),
	(59,3),
	(59,4),
	(59,5),
	(59,6),
	(59,8),
	(59,19),
	(59,28),

	(60,1),
	(60,2),
	(60,3),
	(60,4),
	(60,8);

INSERT INTO book_stat (book_id, stat_id) VALUES
	(61,1),
	(61,2),
	(61,3),
	(61,4),
	(61,5),
	(61,6),
	(61,8),
	(61,23),
	(61,29),

	(62,1),
	(62,2),
	(62,3),
	(62,4),
	(62,5),
	(62,6),
	(62,8),
	(62,29),
	(62,116),

	(63,1),
	(63,2),
	(63,3),
	(63,4),
	(63,5),
	(63,6),
	(63,8),
	(63,9),
	(63,23),
	(63,29),
	(63,30),
	(63,117),

	(64,1),
	(64,2),
	(64,3),
	(64,4),
	(64,29),
	(64,118),
	(64,119),
	(64,120),

	(65,2),
	(65,4),
	(65,8),
	(65,27),
	(65,31),
	(65,121),
	(65,122),
	(65,123),

	(66,1),
	(66,2),
	(66,3),
	(66,4),
	(66,5),
	(66,6),
	(66,19),
	(66,32),
	(66,33),

	(67,1),
	(67,2),
	(67,3),
	(67,4),
	(67,5),
	(67,6),
	(67,19),
	(67,32),
	(67,33),

	(68,1),
	(68,2),
	(68,3),
	(68,4),
	(68,5),
	(68,6),
	(68,19),
	(68,32),
	(68,33),

	(69,1),
	(69,2),
	(69,3),
	(69,4),
	(69,5),
	(69,6),
	(69,19),
	(69,32),
	(69,33);

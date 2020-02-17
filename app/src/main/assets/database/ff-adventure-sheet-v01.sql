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
    possible_values TEXT
);

INSERT INTO stat (name, type, possible_values) VALUES
    -- stats used in every book
    ("Skill",   "INT", null),
    ("Stamina", "INT", null),
    ("Luck",    "INT", null),

    -- stats used in more than one book
    ("Equipment",                     "TEXT",       null),
    ("Provisions",                    "INT",        null),
    ("Gold",                          "INT",        null),
    ("Jewels",                        "TEXT",       null),
    ("Notes",                         "TEXT",       null),
    ("Potions",                       "TEXT",       null),
    ("Armour",                        "INT",        null),
    ("Weapons Strength",              "INT",        null),
    ("Shields",                       "INT",        null),
    ("Money",                         "INT",        null),
    ("Fear",                          "INT",        null),
    ("Clues",                         "TEXT",       null),
    ("Fuel",                          "TEXT",       null),
    ("Credits",                       "INT",        null),
    ("Oxygen",                        "INT",        null),
    ("Treasure",                      "TEXT",       null),
    ("Abilities",                     "TEXT",       null),
    ("Spells",                        "TEXT",       null),
    ("Honour",                        "INT",        null),
    ("Time",                          "INT",        null),
    ("Armour",                        "TEXT",       null), -- active armour, other armours go to equipment
    ("Faith",                         "INT",        null),
    ("Magic",                         "INT",        null),
    ("Weapon",                        "TEXT",       null), -- active weapon, other weapons go to equipment
    ("Poison",                        "INT",        null),
    ("Codewords",                     "TEXT",       null),
    ("Day of the Week",               "MULTI_OPT",  "Stormsday/Moonsday/Fireday/Earthday/Windsday/Seaday/Highday"),
    ("Medkit",                        "INT",        null),
    ("Bonuses, Penalties and Curses", "TEXT",       null),
    ("Libra - Goddess of Justice",    "SINGLE_OPT", "Revitalization/Escape/Removal of Curses and Diseases"),

    -- the citadel of chaos
    ("Spells", "MULTI_OPT_REPEAT", "Creature Copy/E.S.P./Fire/Fool's Gold/Illusion/Levitation/Luck/Shielding/Skill/Stamina/Strength/Weakness"),

    -- starship traveller
    ("Science Officer Skill",       "INT", null),
    ("Science Officer Stamina",     "INT", null),
    ("Medical Officer Skill",       "INT", null),
    ("Medical Officer Stamina",     "INT", null),
    ("Engineering Officer Skill",   "INT", null),
    ("Engineering Officer Stamina", "INT", null),
    ("Security Officer Skill",      "INT", null),
    ("Security Officer Stamina",    "INT", null),
    ("Security Guard 1 Skill",      "INT", null),
    ("Security Guard 1 Stamina",    "INT", null),
    ("Security Guard 2 Skill",      "INT", null),
    ("Security Guard 2 Stamina",    "INT", null),

    -- scorpion swamp
    ("Spell Gems and Spells", "MULTI_OPT_REPEAT", "Skill/Stamina/Luck/Fire/Ice/Illusion/Friendship/Growth/Bless/Fear/Withering/Curse"),

    -- space assassin
    ("Weapons",   "MULTI_OPT_REPEAT", "Electric Lash/Assault Blaster/Grenade/Gravity Bomb"),
    ("Pep Pills", "INT",              null),

    -- freeway fighter
    ("Firepower",         "INT",  null),
    ("Rockets",           "INT",  null),
    ("Iron Spikes",       "INT",  null),
    ("Oil",               "INT",  null),
    ("Car Modifications", "TEXT", null),
    ("Spare Wheels",      "INT",  null),

    -- temple of terror
    ("Spells", "MULTI_OPT", "Open Door/Creature Sleep/Magic Arrow/Language/Read Symbols/Light/Fire/Jump/Detect Trap/Create Water"),

    -- the rings of kether
    ("Smart Missiles", "INT", null),
    ("Energy Tablets", "INT", null),

    -- seas of blood
    ("Crew Strike",   "INT",  null),
    ("Crew Strength", "INT",  null),
    ("Booty",         "TEXT", null),

    -- appointment with FEAR
    ("Super Power", "SINGLE_OPT", "Super Strength/Psi-Powers/Enhanced Technological Skill/Energy Blast"),
    ("Hero Points", "INT", null),

    -- rebel planet
    ("Code Clues", "TEXT", null),

    -- sword of the samurai
    ("Special Skill", "SINGLE_OPT", "Kyujutsu/Iaijutsu/Karumijutsu/Ni-to-Kenjutsu"),

    -- robot commando
    ("Robot",             "TEXT",       null),
    ("Speed",             "SINGLE_OPT", "Slow/Medium/Fast/Very Fast"),
    ("Bonus",             "INT",        null),
    ("Special Abilities", "TEXT",       null),

    -- beneath nightmare castle
    ("Willpower", "INT", null),

    -- star strider
    ("Calculations", "TEXT", null),

    -- phantoms of fear
    ("Power", "INT", null),

    -- midnight rogue
    ("Backpack Items", "TEXT",      null),
    ("Special Skills", "MULTI_OPT", "Climb/Hide/Pick Lock/Pick Pocket/Secret Signs/Sneak/Spot Hidden"),

    -- chasms of malice
    ("Tabasha the Bazouk", "TEXT",      null),
    ("Khuddam",            "MULTI_OPT", "Geshrak/Gurskut/Friankara/Barkek/Griffkek/Churka/Kahhrac"),
    ("Cyphers",            "TEXT",      null),

    -- battleblade warrior
    ("Special Items", "TEXT", null),

    -- sky lord
    ("Starhip", "TEXT", null),
    ("Rating",  "INT",  null),
    ("Lasers",  "INT",  null),

    -- daggers of darkness
    ("Medallions", "MULTI_OPT", "Yigenik/Uruz/Bogomil/Hulugu/Korkut/Kazilik"),

    -- armies of death
    ("Soldiers",     "TEXT",       null),
    ("Situation",    "SINGLE_OPT", "Superior/Even/Inferior"),
    ("Total Troops", "INT",        null),

    -- vault of the vampire
    ("Afflictions", "TEXT", null),

    -- fangs of fury
    ("Citadel Walls", "INT",  null),
    ("Cubes",         "TEXT", null),
    ("Gems",          "TEXT", null),

    -- dead of night
    ("Talents",    "MULTI_OPT", "Banish Undead/Darkveil/Heal/Holy Circle/Meditation/Sense Demon/Speak Demon"),
    ("Holy Water", "INT",       null),
    ("Evil",       "INT",       null),

    -- master of chaos
    ("Skills",    "MULTI_OPT", "Acute Hearing/Animal Wisdom/Blindsight/Climbing/Move Silently/Tracking"),
    ("Notoriety", "INT",       null),

    -- keep of the lich-lord
    ("Resolve",     "INT", null),
    ("Alarm value", "INT", null),

    -- spectral stalkers
    ("Trail",                     "INT",  null),
    ("Potions and Magical Items", "TEXT", null),

    -- tower of destruction
    ("Enemy Encounter", "TEXT", null),
    ("Possessions",     "TEXT", null),

    -- the crimson tide
    ("Ferocity", "INT", null),
    ("Age",      "INT", null),

    -- moonrunner
    ("Skills", "MULTI_OPT", "Acrobatics/Climb/Combat/Con/Disguise/Lock Picking/Sleight of Hand/Sneak/Tracking"),

    -- siege of sardath
    ("Arrows",           "INT", null),
    ("Bundles of Herbs", "INT", null),

    -- island of the undead
    ("Presence", "INT", null),

    -- night dragon
    ("Nemesis Points", "INT", null),

    -- legend of zagor
    ("Talismans",     "TEXT",      null),
    ("Daggers",       "TEXT",      null),
    ("Advantages",    "MULTI_OPT", "Can't be surprised/Can use any weapons/Add 2 to attack strength vs. stone monsters/Add 2 to skill when testing spot skill"),
    ("Disadvantages", "MULTI_OPT", "Can't wear plate mail/No bonus to attack strength with chain mail/Subtract 2 from attack strength with crossbow/Can't use longbow or two-handed weapon/Can't use metal armor, bow or two-handed weapon"),

    -- knights of doom
    ("Special Skills", "MULTI_OPT", "Battle Tactics/Ride/Target/Tracking/Weapon/Arcane Lore/Banish Spirit/Commune/Holy Strike"),

    -- magehunter
    ("Companion Skill",   "INT", null),
    ("Companion Stamina", "INT", null),

    -- revenge of the vampire
    ("Blood Points", "INT", null),

    -- howl of the werewolf
    ("Change", "INT", null),

    -- stormslayer
    ("Damage", "INT", null),

    -- night of the necromancer
    ("Will",         "INT", null),
    ("Host Skill",   "INT", null),
    ("Host Stamina", "INT", null),

    -- blood of the zombies
    ("Dollars",                        "INT", null),
    ("Grenades",                       "INT", null),
    ("Total Number of Zombies Killed", "INT", null),
    ("Number",                         "INT", null);

CREATE TABLE adventure (
    id             INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
    created_at     INTEGER NOT NULL,
    updated_at     INTEGER NOT NULL,
    book_id        INTEGER NOT NULL REFERENCES book(id),
    last_paragraph INTEGER NOT NULL
);

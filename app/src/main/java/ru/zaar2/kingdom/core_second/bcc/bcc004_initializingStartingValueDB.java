package ru.zaar2.kingdom.core_second.bcc;

import ru.zaar2.kingdom.core_second.Randomized;

public final class bcc004_initializingStartingValueDB {
    Randomized randomized = new Randomized();
    //------------------TABLE_resources
    public final int def_POPULATION_RESOURCES = (int) (70 + randomized.random(30));
    public final int def_BUDGET_RESOURCES = (int) (1600 + randomized.random(800));
    public final int def_YEARS_RESOURCES = 1;
    public final int def_ACREAGE_RESOURCES = (int) (900 + randomized.random(200));
    //------------------TABLE_indicators
    public final int def_INCREASE_IN_POPULATION_INDICATORS = 0;
    public final int def_MORTALITY = 0;
    public final int def_MIGRATORY_INCREASE_IN_POPULATION = (int) (randomized.random(10));
    public final int def_LAND_IN_OWNERSHIP = def_ACREAGE_RESOURCES;
    public final int def_GRAIN_RESERVE = def_BUDGET_RESOURCES;
    public final int def_CROP_YIELDS = calc_cropYields(0);
    public final int def_LAND_VALUE = (int) (10 + randomized.random(20));
    public final int def_PERSON_CAN_HANDLE = 14;
    public final int def_ARMY_RESOURCES = 0;
    public final int def_STR_WARRIORS_IN_A_RAID = 0;
    public final int def_UNEMPLOYED_PERSON = def_POPULATION_RESOURCES;
    public final int def_GRAIN_LOSS = (int) (randomized.random(def_BUDGET_RESOURCES / 4));
    public final int def_AGGRESSOR = (int) (randomized.random(def_POPULATION_RESOURCES / 10, (int) (def_POPULATION_RESOURCES / 2)));
    public final int def_DISTANCE_TO = (int) (randomized.random(30, 10));

    //------------------TABLE_accessory
    public final int def_CURRENTLY_QUESTION_ACCESSORY = 0;
    public final int def_SOWN_LAND = 0;
    public final int def_ACCUMULATION_SABOTEUR_ACCESSORY = 0;
    public final int def_ACCUMULATION_LAND_PRODUCTIVITY_ACCESSORY = 0;
    public final int def_GRAIN_PER_CITIZEN_ACCESSORY = 0;
    public final int def_DEAD_IN_BATTLES_ACCESSORY = 0;
    public final int def_BORN_ACCESSORY = 0;
    public final int def_DEAD_EPIDEMIC_ACCESSORY = 0;
    public final int def_DEAD_IN_FIRE_ACCESSORY = 0;
    public final int def_GRAIN_BURNT_DOWN_ACCESSORY = 0;
    public final int def_PLUNDER_GRAIN = 0;
    public final int def_LOSS_GRAIN_IN_DIVERSION = 0;
    public final int def_LOSS_GRAIN_TO_RATS = 0;
    public final int def_DEAD_IN_REBELLION = 0;
    public final int def_DEAD_IN_STARVATION = 0;
    public final int def_RAID_CAPTURE_LAND = 0;
    public final int def_RAID_CAPTURE_GRAIN = 0;
    public final int def_RAID_CAPTURE_PEOPLE = 0;
    public final int def_RAID_DEAD = 0;
    public final int def_RAID_YEAR_OF_RETURN = 0;
    public final int def_DEPLETION = 0;
    public final int def_CROP_YIELDS_CUMULATIVE_EFFECT_ACCESSORY = 0;

    private int calc_cropYields(int sign) {
        int result;
        result = (int) randomized.random(1, 8);
        int a = (int) randomized.random(1, 30);
        if (a >= 25) {
            result = 10;
        }
        if (a <= 5) {
            result = 1;
        }
        return result;
    }
}
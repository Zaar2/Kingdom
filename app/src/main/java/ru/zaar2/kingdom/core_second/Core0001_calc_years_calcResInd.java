package ru.zaar2.kingdom.core_second;

import android.content.Context;

import java.util.ArrayList;

import ru.zaar2.kingdom.R;

public class Core0001_calc_years_calcResInd extends Core000_calc {

    public Core0001_calc_years_calcResInd(Context context) {
        initBundle(
                context.getResources().getString(R.string.strDB_accessory),
                context.getResources().getString(R.string.strDB_resources),
                context.getResources().getString(R.string.strDB_indicators),
                context
        );
    }

    public void start(Context context) {
        resources_data_bundle.putInt(
                context.getResources().getString(R.string.strDB_budget_resources),
                indicators_data_bundle.getInt(context.getResources().getString(R.string.strDB_grainReserve_indicator))
        );
        indicators_data_bundle.putInt(
                context.getResources().getString(R.string.strDB_grainReserve_indicator),
                -1
        );
//____________________________________________________________
        resources_data_bundle.putInt(
                context.getResources().getString(R.string.strDB_acreage_resources),
                indicators_data_bundle.getInt(context.getResources().getString(R.string.strDB_landInOwnership_indicator))
        );
        indicators_data_bundle.putInt(
                context.getResources().getString(R.string.strDB_landInOwnership_indicator),
                -1
        );
//____________________________________________________________
        indicators_data_bundle.putInt(
                context.getResources().getString(R.string.strDB_increaseInPopulation_indicator),
                -1
        );
        indicators_data_bundle.putInt(
                context.getResources().getString(R.string.strDB_mortality_indicator),
                -1
        );
        indicators_data_bundle.putInt(
                context.getResources().getString(R.string.strDB_migratoryIncreaseInPopulation_indicator),
                -1
        );
        indicators_data_bundle.putInt(
                context.getResources().getString(R.string.strDB_grainLoss_indicator),
                0
        );
//        indicators_data_bundle.putInt(
//                context.getResources().getString(R.string.strDB_cropYields_indicator),
//                -1
//        );
        indicators_data_bundle.putInt(
                context.getResources().getString(R.string.strDB_landValue_indicator),
                -1
        );
    }

    public void calc_indicators_born_dead(Context context) {
        int population = resources_data_bundle.getInt(context.getResources().getString(R.string.strDB_population_resources));
        int mortality = (int) (randomized.random() * 2.5 * population / 100);
        indicators_data_bundle.putInt(
                context.getResources().getString(R.string.strDB_mortality_indicator),
                mortality
        );
        int grain_per_citizen = accessory_data_bundle.getInt(context.getResources().getString(R.string.strDB_grain_per_citizen_accessory));
        int increaseInPopulation = (int) (randomized.random() * (3 + grain_per_citizen / 25) / 100 * population);
        indicators_data_bundle.putInt(
                context.getResources().getString(R.string.strDB_increaseInPopulation_indicator),
                increaseInPopulation
        );
    }

    public void preCalc_resources_grain(Context context) {
        int cropYields = indicators_data_bundle.getInt(
                context.getResources().getString(R.string.strDB_cropYields_indicator)
        );
        if (cropYields <= 0) {
            cropYields = 1;
        }
        indicators_data_bundle.putInt(
                context.getResources().getString(R.string.strDB_cropYields_indicator),
                cropYields
        );
        int sown_land = accessory_data_bundle.getInt(context.getResources().getString(R.string.strDB_sown_land_accessory));
        int harvest = sown_land * cropYields;
        int grain = resources_data_bundle.getInt(context.getResources().getString(R.string.strDB_budget_resources));
        grain += harvest;
        resources_data_bundle.putInt(
                context.getResources().getString(R.string.strDB_budget_resources),
                grain
        );
    }

    private int calc_cropYields(int sign) {
        int result;
        result = (int) randomized.random(1, 8);
        int a = (int) randomized.random(1, 30);
        if (a >= 25 && sign > 0) {
            result = 10;
        }
        if (a <= 5 && sign < 0) {
            result = 1;
        }
        return result;
    }

    public void calc_indicator_migratory(Context context) {
        int population = resources_data_bundle.getInt(context.getResources().getString(R.string.strDB_population_resources));
        int migratory = (int) (randomized.random() * (population / 4));
        indicators_data_bundle.putInt(
                context.getResources().getString(R.string.strDB_migratoryIncreaseInPopulation_indicator),
                migratory
        );
    }

    /**
     * <p>Если зерна на прокорм не дали, то все умерли и игра заканчивается</p>
     */
    public void calc_defeat_dueTo_starvation(Context context, ArrayList<Integer> eventsList){
        if (accessory_data_bundle.getInt(context.getResources().getString(R.string.strDB_grain_per_citizen_accessory))==0){
            eventsList.add(R.string.event_defeat_dueTo_starvation);
        }
    }

    /**
     * <p></p>
     */
    public void calc_event_0_fire_diversion(Context context, ArrayList<Integer> eventsList) {
        if (randomized.random(15) == 2 && randomized.random(15) == 10) {
            int dead, burnt_down;
            int population, grain, grainLoss, mortality;

            population = resources_data_bundle.getInt(context.getResources().getString(R.string.strDB_population_resources));
            grain = resources_data_bundle.getInt(context.getResources().getString(R.string.strDB_budget_resources));
            grainLoss = indicators_data_bundle.getInt(context.getResources().getString(R.string.strDB_grainLoss_indicator));
            mortality = indicators_data_bundle.getInt(context.getResources().getString(R.string.strDB_mortality_indicator));

            dead = (int) ((randomized.random() / 3 + 0.2) * population);
            burnt_down = (int) ((randomized.random() / 4 + 0.15f) * grain);
            grainLoss += burnt_down;
            mortality += dead;

            indicators_data_bundle.putInt(
                    context.getResources().getString(R.string.strDB_mortality_indicator),
                    mortality
            );
            indicators_data_bundle.putInt(
                    context.getResources().getString(R.string.strDB_grainLoss_indicator),
                    grainLoss
            );
            accessory_data_bundle.putInt(
                    context.getResources().getString(R.string.strDB_dead_in_fire_accessory),
                    dead
            );
            accessory_data_bundle.putInt(
                    context.getResources().getString(R.string.strDB_grain_burnt_down_accessory),
                    burnt_down
            );

            eventsList.add(R.string.event_fire);
        } else {
            calc_event_6_diversion(context, eventsList);
        }
    }

    /**
     * <p></p>
     */
    public void calc_event_6_diversion(Context context, ArrayList<Integer> eventsList) {
        if (randomized.random(20) == 5 && randomized.random(15) == 10) {
            int burnt_down, grain, grainLoss;

            grain = resources_data_bundle.getInt(context.getResources().getString(R.string.strDB_budget_resources));
            grainLoss = indicators_data_bundle.getInt(context.getResources().getString(R.string.strDB_grainLoss_indicator));
            burnt_down = (int) ((randomized.random() / 3 + 0.25) * grain);
            grainLoss += burnt_down;

            indicators_data_bundle.putInt(
                    context.getResources().getString(R.string.strDB_grainLoss_indicator),
                    grainLoss
            );
            accessory_data_bundle.putInt(
                    context.getResources().getString(R.string.strDB_loss_grain_in_diversion_accessory),
                    burnt_down
            );

            eventsList.add(R.string.event_diversion);
        }
    }

    /**
     * <p></p>
     */
    public void calc_event_1_rats(Context context, ArrayList<Integer> eventsList) {
        if (randomized.random(15) < 6) {
            int grain = resources_data_bundle.getInt(context.getResources().getString(R.string.strDB_budget_resources));
            int grainLoss = indicators_data_bundle.getInt(context.getResources().getString(R.string.strDB_grainLoss_indicator));
            int grain_loss_to_rats = (int) (grain / (randomized.random(7, 15)));
            accessory_data_bundle.putInt(
                    context.getResources().getString(R.string.strDB_loss_grain_to_rats_accessory),
                    grain_loss_to_rats
            );
            grainLoss += grain_loss_to_rats;
            indicators_data_bundle.putInt(
                    context.getResources().getString(R.string.strDB_grainLoss_indicator),
                    grainLoss
            );
            eventsList.add(R.string.event_rats);
        }
    }

    /**
     * <p></p>
     */
    public void calc_event_2_migratory(Context context, ArrayList<Integer> eventsList) {
        if (
                indicators_data_bundle.getInt(context.getResources().getString(R.string.strDB_distance_to_indicator)) < 25
                        && randomized.random() > 0.4
        ) {
            int migratory = indicators_data_bundle.getInt(context.getResources().getString(R.string.strDB_migratoryIncreaseInPopulation_indicator));
            migratory += (int) (randomized.random() * migratory);
            indicators_data_bundle.putInt(
                    context.getResources().getString(R.string.strDB_migratoryIncreaseInPopulation_indicator),
                    migratory
            );
            eventsList.add(R.string.event_migratory);
        }
    }

    /**
     * <p></p>
     */
    public void calc_event_3_rebellion(Context context, ArrayList<Integer> eventsList) {
        if (
                accessory_data_bundle.getInt(context.getResources().getString(R.string.strDB_grain_per_citizen_accessory)) < 10
                        || indicators_data_bundle.getInt(context.getResources().getString(R.string.strDB_mortality_indicator)) >
                        (resources_data_bundle.getInt(context.getResources().getString(R.string.strDB_population_resources)) * 7)
        ) {
            int population = resources_data_bundle.getInt(context.getResources().getString(R.string.strDB_population_resources));
            int defenders = indicators_data_bundle.getInt(context.getResources().getString(R.string.strDB_army_indicator));
            int dead_starvation = accessory_data_bundle.getInt(context.getResources().getString(R.string.strDB_dead_in_starvation_accessory));
            if ((population - defenders) / 3 < defenders && defenders < dead_starvation) {
                //rebellion defeated
                int rebels = (int) ((population - defenders) * (randomized.random() / 5 + 0.2));
                int mortality = indicators_data_bundle.getInt(context.getResources().getString(R.string.strDB_mortality_indicator));
                mortality += rebels;
                indicators_data_bundle.putInt(
                        context.getResources().getString(R.string.strDB_mortality_indicator),
                        mortality
                );
                accessory_data_bundle.putInt(
                        context.getResources().getString(R.string.strDB_dead_in_rebellion_accessory),
                        rebels
                );
                eventsList.add(R.string.event_victory_over_rebellion);
            } else {
                //rebellion won
                eventsList.add(R.string.event_defeat_dueTo_rebellion);
            }
        }
    }

    /**
     * <p></p>t
     */
    public void calc_event_4_5_epidemic_demographic(Context context, ArrayList<Integer> eventsList) {
        if (randomized.random(15) == 2 && randomized.random(15) == 10) {
            //epidemic
            int population = resources_data_bundle.getInt(context.getResources().getString(R.string.strDB_population_resources));
            int epidemic = (int) (randomized.random() * 50 + 5);
            accessory_data_bundle.putInt(
                    context.getResources().getString(R.string.strDB_dead_epidemic_accessory),
                    epidemic
            );
            int mortality = indicators_data_bundle.getInt(context.getResources().getString(R.string.strDB_mortality_indicator));
            mortality += population / 100 * epidemic;
            indicators_data_bundle.putInt(
                    context.getResources().getString(R.string.strDB_mortality_indicator),
                    mortality
            );
            eventsList.add(R.string.event_epidemic);
        } else {
            //demographic explosion
            if (randomized.random(50) > 20) {
                int population = resources_data_bundle.getInt(context.getResources().getString(R.string.strDB_population_resources));
                int increase_population = indicators_data_bundle.getInt(context.getResources().getString(R.string.strDB_increaseInPopulation_indicator));
                int born_demographic = (int) ((randomized.random() / 3 + 0.2) * population);
                increase_population += born_demographic;
                indicators_data_bundle.putInt(
                        context.getResources().getString(R.string.strDB_increaseInPopulation_indicator),
                        increase_population
                );
                eventsList.add(R.string.event_demographic_explosion);
            }
        }
    }

    /**
     * <p></p>
     */
    public void calc_event_7_8_personCanHandle_increased_decline(Context context, ArrayList<Integer> eventsList) {
        if (randomized.random(15) == 7 && randomized.random(15) == 12) {
            //increased
            int canHandle = indicators_data_bundle.getInt(context.getResources().getString(R.string.strDB_personCanHandle_indicator));
            canHandle += 5;
            indicators_data_bundle.putInt(
                    context.getResources().getString(R.string.strDB_personCanHandle_indicator),
                    canHandle
            );
            eventsList.add(R.string.event_personCanHandle_increased);
        } else {
            //decline
            if (
                    indicators_data_bundle.getInt(context.getResources().getString(R.string.strDB_unemployed_person_indicator)) >
                            indicators_data_bundle.getInt(context.getResources().getString(R.string.strDB_population_resources)) / 3
            ) {
                int canHandle = indicators_data_bundle.getInt(context.getResources().getString(R.string.strDB_personCanHandle_indicator));
                canHandle -= 5;
                if (canHandle <= 0) canHandle = 1;
                indicators_data_bundle.putInt(
                        context.getResources().getString(R.string.strDB_personCanHandle_indicator),
                        canHandle
                );
                eventsList.add(R.string.event_personCanHandle_decline);
            }
        }
    }

    /**
     * <p>Расчет истощения земли или повышения урожайности.</p>
     * <p>-Засев более 95% земли, вызывает истощение почвы.</p>
     * <p>-Засев менее половины земли, вызывает повышение урожайности.</p>
     */
    public void calc_event_9_10_cropYieldsImproved_landDepletion(Context context, ArrayList<Integer> eventsList) {
        int adding = 0;
        int cropYield = indicators_data_bundle.getInt(
                context.getResources().getString(R.string.strDB_cropYields_indicator)
        );
        int sign = 0;
        int cumulativeDepletion = accessory_data_bundle.getInt(context.getResources().getString(R.string.strDB_cropYields_cumulativeEffectDepletion_accessory));
        if (
            //landDepletion
                accessory_data_bundle.getInt(context.getResources().getString(R.string.strDB_sown_land_accessory)) >
                        (resources_data_bundle.getInt(context.getResources().getString(R.string.strDB_acreage_resources)) * 0.95)
        ) {
            if (cumulativeDepletion < 4) {
                cumulativeDepletion++;
            } else cumulativeDepletion = 4;
            sign = -1;
            accessory_data_bundle.putInt(
                    context.getResources().getString(R.string.strDB_cropYields_cumulativeEffectDepletion_accessory),
                    cumulativeDepletion
            );
            eventsList.add(R.string.event_land_depletion);
        } else {
            //improved yields
            if (
                    accessory_data_bundle.getInt(context.getResources().getString(R.string.strDB_sown_land_accessory)) <=
                            (resources_data_bundle.getInt(context.getResources().getString(R.string.strDB_acreage_resources)) / 2)
            ) {
                if (cumulativeDepletion > 0) {
                    cumulativeDepletion--;
                } else cumulativeDepletion = 0;
                sign = 1;
                eventsList.add(R.string.event_improved_yields);
            }
        }
        if (sign != 0)
            adding += ((int) (randomized.random(1, (int) (cropYield / 2))) * sign);
        if (sign < 0) adding += cumulativeDepletion;
        if (adding != 0) {
            cropYield += (int) (adding);
            if (cropYield <= 0) {
                cropYield = 1;
            }
        }
        indicators_data_bundle.putInt(
                context.getResources().getString(R.string.strDB_cropYields_indicator),
                cropYield
        );
    }

    /**
     * <p></p>
     */
    public void calc_event_11_plunder(Context context, ArrayList<Integer> eventsList) {
        if (randomized.random(15) > 12) {
            int grain = resources_data_bundle.getInt(context.getResources().getString(R.string.strDB_budget_resources));
            int stolen = (int) ((randomized.random() / 4 + 0.2) * grain);
            int grainLoss = indicators_data_bundle.getInt(context.getResources().getString(R.string.strDB_grainLoss_indicator));
            grainLoss += stolen;
            accessory_data_bundle.putInt(
                    context.getResources().getString(R.string.strDB_plunder_grain_accessory),
                    stolen
            );
            indicators_data_bundle.putInt(
                    context.getResources().getString(R.string.strDB_grainLoss_indicator),
                    grainLoss
            );
            eventsList.add(R.string.event_plunder);
        }
    }

    /**
     * <p></p>
     */
    public void calc_event_12_aggressor(Context context, ArrayList<Integer> eventsList) {
        if (indicators_data_bundle.getInt(context.getResources().getString(R.string.strDB_distance_to_indicator)) > 0) {
            if (
                    indicators_data_bundle.getInt(context.getResources().getString(R.string.strDB_distance_to_indicator)) < 17
                            && randomized.random(10) < 3
            ) {
                eventsList.add(R.string.event_aggressor);
                int defenders = indicators_data_bundle.getInt(context.getResources().getString(R.string.strDB_army_indicator));
                int enemies = indicators_data_bundle.getInt(context.getResources().getString(R.string.strDB_aggressor_indicator));
                if (enemies > defenders) {
                    //loss
                    eventsList.add(R.string.event_defeat_in_war);
                } else {
                    //win
                    calc_deadInAttack(defenders, enemies, context);
                    eventsList.add(R.string.event_win_in_war);
                }
                indicators_data_bundle.putInt(
                        context.getResources().getString(R.string.strDB_aggressor_indicator),
                        0
                );
                indicators_data_bundle.putInt(
                        context.getResources().getString(R.string.strDB_distance_to_indicator),
                        0
                );
            } else {
                int distance = indicators_data_bundle.getInt(context.getResources().getString(R.string.strDB_distance_to_indicator));
                distance -= randomized.random(5) + 10;
                indicators_data_bundle.putInt(
                        context.getResources().getString(R.string.strDB_distance_to_indicator),
                        distance
                );
                if (distance <= 0) {
                    int defenders = indicators_data_bundle.getInt(context.getResources().getString(R.string.strDB_army_indicator));
                    int enemies = indicators_data_bundle.getInt(context.getResources().getString(R.string.strDB_aggressor_indicator));
                    if (enemies > defenders) {
                        //loss
                        eventsList.add(R.string.event_defeat_in_war);
                    } else {
                        //win
                        calc_deadInAttack(defenders, enemies, context);
                        eventsList.add(R.string.event_win_in_war);
                    }
                    indicators_data_bundle.putInt(
                            context.getResources().getString(R.string.strDB_aggressor_indicator),
                            0
                    );
                    indicators_data_bundle.putInt(
                            context.getResources().getString(R.string.strDB_distance_to_indicator),
                            0
                    );
                }
            }
        }
    }

    public void calc_raid(Context context, ArrayList<Integer> eventsList) {
        int
                year = resources_data_bundle.getInt(context.getResources().getString(R.string.strDB_years_resources)),
                yearOfReturn = accessory_data_bundle.getInt(context.getResources().getString(R.string.strDB_raid_year_of_return_accessory));
        if (yearOfReturn == year) {
            int
                    warriors = indicators_data_bundle.getInt(context.getResources().getString(R.string.strDB_warriors_in_a_Raid_indicator)),
                    population = resources_data_bundle.getInt(context.getResources().getString(R.string.strDB_population_resources));
            if (warriors > population * 2) {
                raid_victory(context, eventsList);
                return;
            }
            if (warriors < population / 2) {
                raid_defeat(context, eventsList);
                return;
            }
            if (randomized.random() > 0.5) {
                raid_victory(context, eventsList);
                return;
            }
            raid_defeat(context, eventsList);
        }
    }

    private void raid_victory(Context context, ArrayList<Integer> eventsList) {
        int
                population = resources_data_bundle.getInt(context.getResources().getString(R.string.strDB_population_resources)),
                warriors = indicators_data_bundle.getInt(context.getResources().getString(R.string.strDB_warriors_in_a_Raid_indicator)),
                landOwner = resources_data_bundle.getInt(context.getResources().getString(R.string.strDB_acreage_resources)),
                budget = resources_data_bundle.getInt(context.getResources().getString(R.string.strDB_budget_resources));
        int
                people = (int) (population * (randomized.random() / 3 + 0.3)),
                grain = (int) (warriors * randomized.random(10) + 4),
                land = (int) (landOwner * (randomized.random() / 2 + 0.3)),
                dead = (int) (warriors * (randomized.random() / 5 + 0.3));
        population += people + (warriors - dead);
        landOwner += land;
        budget += grain;
        accessory_data_bundle.putInt(context.getResources().getString(R.string.strDB_raid_dead_accessory), dead);
        accessory_data_bundle.putInt(context.getResources().getString(R.string.strDB_raid_capture_land_accessory), land);
        accessory_data_bundle.putInt(context.getResources().getString(R.string.strDB_raid_capture_people_accessory), people);
        accessory_data_bundle.putInt(context.getResources().getString(R.string.strDB_raid_capture_grain_accessory), grain);

        resources_data_bundle.putInt(context.getResources().getString(R.string.strDB_population_resources), population);
        resources_data_bundle.putInt(context.getResources().getString(R.string.strDB_acreage_resources), landOwner);
        resources_data_bundle.putInt(context.getResources().getString(R.string.strDB_budget_resources), budget);

//        indicators_data_bundle.putInt(context.getResources().getString(R.string.strDB_warriors_in_a_Raid_indicator), 0);
        accessory_data_bundle.putInt(context.getResources().getString(R.string.strDB_raid_year_of_return_accessory), 0);

        eventsList.add(R.string.event_victory_in_raid);
    }

    private void raid_defeat(Context context, ArrayList<Integer> eventsList){
        int
                population = resources_data_bundle.getInt(context.getResources().getString(R.string.strDB_population_resources)),
                warriors = indicators_data_bundle.getInt(context.getResources().getString(R.string.strDB_warriors_in_a_Raid_indicator));
        int dead = (int) (warriors*(randomized.random()/5+0.8));
        population-=dead;
        accessory_data_bundle.putInt(context.getResources().getString(R.string.strDB_raid_dead_accessory), dead);
        resources_data_bundle.putInt(context.getResources().getString(R.string.strDB_population_resources), population);


        indicators_data_bundle.putInt(context.getResources().getString(R.string.strDB_warriors_in_a_Raid_indicator), 0);
        accessory_data_bundle.putInt(context.getResources().getString(R.string.strDB_raid_year_of_return_accessory), 0);
        eventsList.add(R.string.event_defeat_in_raid);
    }

    private void calc_deadInAttack(int defenders, int enemies, Context context) {
        int dead = enemies / 3 + ((int) (randomized.random() * defenders / 6));
        int mortality = indicators_data_bundle.getInt(context.getResources().getString(R.string.strDB_mortality_indicator));
        mortality += dead;
        accessory_data_bundle.putInt(
                context.getResources().getString(R.string.strDB_dead_in_battles_accessory),
                dead
        );
        indicators_data_bundle.putInt(
                context.getResources().getString(R.string.strDB_mortality_indicator),
                mortality
        );
    }

    public void calc_resources_population(Context context, ArrayList<Integer> eventsList) {
        int population = resources_data_bundle.getInt(context.getResources().getString(R.string.strDB_population_resources));
        population +=
                indicators_data_bundle.getInt(context.getResources().getString(R.string.strDB_increaseInPopulation_indicator))
                        - indicators_data_bundle.getInt(context.getResources().getString(R.string.strDB_mortality_indicator))
                        + indicators_data_bundle.getInt(context.getResources().getString(R.string.strDB_migratoryIncreaseInPopulation_indicator));
        resources_data_bundle.putInt(
                context.getResources().getString(R.string.strDB_population_resources),
                population
        );
        if (population <= 0) {
            eventsList.add(R.string.event_defeat_dueTo_hunger);
        }
    }

    public void calc_indicator_landValue(Context context) {
        indicators_data_bundle.putInt(
                context.getResources().getString(R.string.strDB_landValue_indicator),
                (int) (randomized.random(40) + 10)
        );
    }

    public void end(Context context) {
        indicators_data_bundle.putInt(
                context.getResources().getString(R.string.strDB_grainReserve_indicator),
                resources_data_bundle.getInt(context.getResources().getString(R.string.strDB_budget_resources))
        );
        indicators_data_bundle.putInt(
                context.getResources().getString(R.string.strDB_landInOwnership_indicator),
                resources_data_bundle.getInt(context.getResources().getString(R.string.strDB_acreage_resources))
        );
        indicators_data_bundle.putInt(
                context.getResources().getString(R.string.strDB_unemployed_person_indicator),
                resources_data_bundle.getInt(context.getResources().getString(R.string.strDB_population_resources))
        );
        indicators_data_bundle.putInt(
                context.getResources().getString(R.string.strDB_army_indicator),
                0
        );

        if (indicators_data_bundle.getInt(context.getResources().getString(R.string.strDB_distance_to_indicator)) <= 0) {
            newAggressor(context);
        }
    }

    private void newAggressor(Context context) {
        if (randomized.random(1, 10) >= 3) {
            int distance = (int) (25 + randomized.random(20));
            int population = resources_data_bundle.getInt(context.getResources().getString(R.string.strDB_population_resources));
            int aggressor = (int) (population / 4 + randomized.random() * population / 5);
            indicators_data_bundle.putInt(
                    context.getResources().getString(R.string.strDB_distance_to_indicator),
                    distance
            );
            indicators_data_bundle.putInt(
                    context.getResources().getString(R.string.strDB_aggressor_indicator),
                    aggressor
            );
        }
    }

    public void postCalc_resources_grain_budget(Context context) {
        int budget, grainLoss;

        budget = resources_data_bundle.getInt(context.getResources().getString(R.string.strDB_budget_resources));
        grainLoss = indicators_data_bundle.getInt(context.getResources().getString(R.string.strDB_grainLoss_indicator));

        budget -= grainLoss;
        if (budget < 0) budget = 0;

        resources_data_bundle.putInt(
                context.getResources().getString(R.string.strDB_budget_resources),
                budget
        );
    }
}
package ru.zaar2.kingdom;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import ru.zaar2.kingdom.core_second.EntryToCore;

public class Events {

    private String
            eventInfo,
            specialEventInfo;
    private Bitmap eventImage;

    public Events(LinearLayout event, int step, Context context) {

        eventInfo = "";
        specialEventInfo = "";
//        calcEvent(step, context);
        calcEvents(step, context);
        viewEvent(event);
    }

    private void viewEvent(LinearLayout event) {
        ((ImageView) event.findViewById(R.id.event_image)).setImageBitmap(eventImage);
        ((TextView) event.findViewById(R.id.eventInfo_tv)).setText(eventInfo);
        ((TextView) event.findViewById(R.id.eventSpecialInfo_tv)).setText(specialEventInfo);
    }

    @SuppressLint("NonConstantResourceId")
    private void calcEvents(int eventNow_ID, Context context) {
        switch (eventNow_ID) {
            case R.string.event_fire:
                event_fire(context);
                break;
            case R.string.event_rats:
                event_rats(context);
                break;
            case R.string.event_migratory:
                event_migratory(context);
                break;
            case R.string.event_victory_over_rebellion:
                event_victory_over_rebellion(context);
                break;
            case R.string.event_epidemic:
                event_epidemic(context);
                break;
            case R.string.event_demographic_explosion:
                event_demographic_explosion(context);
                break;
            case R.string.event_diversion:
                event_diversion(context);
                break;
            case R.string.event_personCanHandle_increased:
                event_personCanHandle_increased(context);
                break;
            case R.string.event_personCanHandle_decline:
                event_personCanHandle_decline(context);
                break;
            case R.string.event_land_depletion:
                event_land_depletion(context);
                break;
            case R.string.event_improved_yields:
                event_improved_yields(context);
                break;
            case R.string.event_plunder:
                event_plunder(context);
                break;
            case R.string.event_aggressor:
                event_aggressor(context);
                break;
            case R.string.event_win_in_war:
                event_win_in_war(context);
                break;
            case R.string.event_defeat_in_war:
                event_defeat_in_war(context);
                break;
            case R.string.event_defeat_dueTo_hunger:
                event_defeat_dueTo_hunger(context);
                break;
            case R.string.event_defeat_dueTo_rebellion:
                event_defeat_dueTo_rebellion(context);
                break;
            case R.string.event_defeat_dueTo_starvation:
                event_defeat_dueTo_starvation(context);
                break;
            case R.string.event_defeat_in_raid:
                event_defeat_in_raid(context);
                break;
            case R.string.event_victory_in_raid:
                event_victory_in_raid(context);
                break;
            default:
                event_empty(context);
                break;
        }
    }

    private void event_empty(Context context) {
        eventImage = BitmapFactory.decodeResource(context.getResources(), R.drawable.events);
        eventInfo = "Значимых событий - не произошло";
        specialEventInfo = "";
    }

    private void event_fire(Context context) {
        int dead, burnt_down;
        dead = (new EntryToCore().findCurrentlyValue_ofSpecifiedParameter(
                context.getResources().getString(R.string.strDB_dead_in_fire_accessory),
                context.getResources().getString(R.string.strDB_accessory),
                context,
                1
        ));
        burnt_down = new EntryToCore().findCurrentlyValue_ofSpecifiedParameter(
                context.getResources().getString(R.string.strDB_grain_burnt_down_accessory),
                context.getResources().getString(R.string.strDB_accessory),
                context,
                1
        );

        eventImage = BitmapFactory.decodeResource(context.getResources(), R.drawable.events);
        eventInfo = "ПОЖАААР!";
        specialEventInfo = "Погибших в огне " + dead + ". " +
                "Также сгорело " + burnt_down + " пуд. зерна.";
    }

    private void event_rats(Context context) {
        eventImage = BitmapFactory.decodeResource(context.getResources(), R.drawable.rats);
        eventInfo = "Нашествие крыс!";
        specialEventInfo = "Собственные агенты докладывают, что данные сведения немного преувеличены." +
                "\nВозможно все уже было украдено ранее.";
    }

    private void event_migratory(Context context) {
        eventImage = BitmapFactory.decodeResource(context.getResources(), R.drawable.events);
        eventInfo = "На границе неспокойно, волна мигрантов заполонила город.";
        specialEventInfo = "";
    }

    private void event_victory_over_rebellion(Context context) {
        int rebels = new EntryToCore().findCurrentlyValue_ofSpecifiedParameter(
                context.getResources().getString(R.string.strDB_dead_in_rebellion_accessory),
                context.getResources().getString(R.string.strDB_accessory),
                context,
                1
        );
        eventImage = BitmapFactory.decodeResource(context.getResources(), R.drawable.events);
        eventInfo = "Народ поднялся на бунт!";
        specialEventInfo = "Верные Вам войска подавили бунт. " +
                "\nПотери среди горожан - " + rebels + " чел.";
    }

    private void event_epidemic(Context context) {
        int epidemic = new EntryToCore().findCurrentlyValue_ofSpecifiedParameter(
                context.getResources().getString(R.string.strDB_dead_epidemic_accessory),
                context.getResources().getString(R.string.strDB_accessory),
                context,
                1
        );
        eventImage = BitmapFactory.decodeResource(context.getResources(), R.drawable.events);
        eventInfo = "ЧУУМААА!!!";
        specialEventInfo = "В городе началась эпидемия заразной болезни. Множество горожан умерло." +
                "\nЭпидемия унесла жизни " + epidemic + "% горожан.";
    }

    private void event_demographic_explosion(Context context) {
        eventImage = BitmapFactory.decodeResource(context.getResources(), R.drawable.demographic);
        eventInfo = "Демографический взрыв.";
        specialEventInfo = "";
    }

    private void event_diversion(Context context) {
        int loss = new EntryToCore().findCurrentlyValue_ofSpecifiedParameter(
                context.getResources().getString(R.string.strDB_loss_grain_in_diversion_accessory),
                context.getResources().getString(R.string.strDB_accessory),
                context,
                1
        );
        eventImage = BitmapFactory.decodeResource(context.getResources(), R.drawable.events);
        eventInfo = "Диверсия!!";
        specialEventInfo = "Вражеский лазутчик поджег склады с продовольствием" +
                "Потеряно " + loss + " пуд.зерна.";
    }

    private void event_personCanHandle_increased(Context context) {
        eventImage = BitmapFactory.decodeResource(context.getResources(), R.drawable.events);
        eventInfo = "Увеличивается производительность Ваших подданных.";
        specialEventInfo = "Ваши длительные усилия по принуждению всех окружающих к труду, наконец-то принесли плоды." +
                "\nТеперь ваши подданные могут засеять больше земли.";
    }

    private void event_personCanHandle_decline(Context context) {
        eventImage = BitmapFactory.decodeResource(context.getResources(), R.drawable.events);
        eventInfo = "Большое количество праздношатающихся.";
        specialEventInfo = "Подданные начинают отвыкать от эффективной и усердной работы для вашего благополучия." +
                "\nИз-за этого, Вы теперь можете засеять куда меньшую часть своей земли.";
    }

    private void event_land_depletion(Context context) {
        eventImage = BitmapFactory.decodeResource(context.getResources(), R.drawable.events);
        eventInfo = "Интенсивное использование земель вызывает их истощение и снижает урожайность.";
        specialEventInfo = "";
    }

    private void event_improved_yields(Context context) {
        eventImage = BitmapFactory.decodeResource(context.getResources(), R.drawable.events);
        eventInfo = "Использование разумной системы земледелия повышает урожайность.";
        specialEventInfo = "";
    }

    private void event_plunder(Context context) {
        int plunderGrain = new EntryToCore().findCurrentlyValue_ofSpecifiedParameter(
                context.getResources().getString(R.string.strDB_plunder_grain_accessory),
                context.getResources().getString(R.string.strDB_accessory),
                context,
                1
        );
        eventImage = BitmapFactory.decodeResource(context.getResources(), R.drawable.events);
        eventInfo = "Вскрылось крупное хищение со складов.";
        specialEventInfo = "Виновные схвачены и уже наказаны...в лесу. " +
                "\nУщерб составил - " + plunderGrain + " пуд. зерна." +
                "\nОднако вернуть похищенное зерно - не удалось.";
    }

    private void event_aggressor(Context context) {
        eventImage = BitmapFactory.decodeResource(context.getResources(), R.drawable.events);
        eventInfo = "Неожиданный доклад от начальника охраны ворот!!";
        specialEventInfo = "Войска интервентов совершили неожиданный маневр и внезапно оказались у самых стен города." +
                "\nМы то думали они нормальные, а они вон какими ... оказались.";
    }

    private void event_win_in_war(Context context) {
        int dead_battle = new EntryToCore().findCurrentlyValue_ofSpecifiedParameter(
                context.getResources().getString(R.string.strDB_dead_in_battles_accessory),
                context.getResources().getString(R.string.strDB_accessory),
                context,
                1
        );
        eventImage = BitmapFactory.decodeResource(context.getResources(), R.drawable.events);
        eventInfo = "Город атакован!!";
        specialEventInfo = "Битва выиграна!!" +
                "\nБлагодаря вашему мудрому руководству, войска и горожане смогли дать достойный отпор интервентам." +
                "\nПогибло " + dead_battle + " воинов!";
    }

    private void event_defeat_in_war(Context context) {
        eventImage = BitmapFactory.decodeResource(context.getResources(), R.drawable.events);
        eventInfo = "Город атакован!!";
        specialEventInfo = "Вы побеждены!!" +
                "\nЗащитников было слишком мало." +
                "\nПозор и горе побежденным.";
    }

    private void event_defeat_dueTo_hunger(Context context) {
        eventImage = BitmapFactory.decodeResource(context.getResources(), R.drawable.events);
        eventInfo = "Вы остались без подданных!!";
        specialEventInfo = "Ну наконец-то!! " +
                "\nБольше никто не придет к Вам со своими проблемами." +
                "\nТеперь можно и для себя пожить.";
    }

    private void event_defeat_dueTo_rebellion(Context context) {
        eventImage = BitmapFactory.decodeResource(context.getResources(), R.drawable.exit);
        eventInfo = "Вы изгнаны!!";
        specialEventInfo = "Восставший народ решил избавить Вас от слишком большой кучи проблем и изгнал вас." +
                "\nС благодарностью в сердце и как можно быстрее - Вы удалились из города.";
    }

    private void event_defeat_dueTo_starvation(Context context) {
        eventImage = BitmapFactory.decodeResource(context.getResources(), R.drawable.events);
        eventInfo = "Вы уморили всех голодом!";
        specialEventInfo = "Ну наконец-то!! " +
                "\nБольше никто не придет к тебе со своими проблемами." +
                "\nТеперь можно и для себя пожить.";
    }

    private void event_defeat_in_raid(Context context) {
        int dead = new EntryToCore().findCurrentlyValue_ofSpecifiedParameter(
                context.getResources().getString(R.string.strDB_raid_dead_accessory),
                context.getResources().getString(R.string.strDB_accessory),
                context,
                1
        );
        eventImage = BitmapFactory.decodeResource(context.getResources(), R.drawable.events);
        eventInfo = "Набег окончился неудачей!";
        specialEventInfo = "Погибло " + dead + " воинов.";
    }

    private void event_victory_in_raid(Context context) {
        int
                captureGrain = new EntryToCore().findCurrentlyValue_ofSpecifiedParameter(
                context.getResources().getString(R.string.strDB_raid_capture_grain_accessory),
                context.getResources().getString(R.string.strDB_accessory),
                context,
                1
        ),
                capturePeople = new EntryToCore().findCurrentlyValue_ofSpecifiedParameter(
                        context.getResources().getString(R.string.strDB_raid_capture_people_accessory),
                        context.getResources().getString(R.string.strDB_accessory),
                        context,
                        1
                ),
                captureLand = new EntryToCore().findCurrentlyValue_ofSpecifiedParameter(
                        context.getResources().getString(R.string.strDB_raid_capture_land_accessory),
                        context.getResources().getString(R.string.strDB_accessory),
                        context,
                        1
                ),
                deadInRaid = new EntryToCore().findCurrentlyValue_ofSpecifiedParameter(
                        context.getResources().getString(R.string.strDB_raid_dead_accessory),
                        context.getResources().getString(R.string.strDB_accessory),
                        context,
                        1
                ),
                warriors = new EntryToCore().findCurrentlyValue_ofSpecifiedParameter(
                        context.getResources().getString(R.string.strDB_warriors_in_a_Raid_indicator),
                        context.getResources().getString(R.string.strDB_indicators),
                        context,
                        1
                );
        new EntryToCore().updateParameter(
                0,
                context.getResources().getString(R.string.strDB_warriors_in_a_Raid_indicator),
                context.getResources().getString(R.string.strDB_indicators),
                context,
                1
        );
        eventImage = BitmapFactory.decodeResource(context.getResources(), R.drawable.events);
        eventInfo = "Набег завершился победой!";
        specialEventInfo = "Захвачено:" +
                "\nЗерна - " + captureGrain + " пудов" +
                "\nЗемли - " + captureLand + " десятин" +
                "\nПленных - " + capturePeople + " человек" +
                "\nИз " + warriors + " воинов, в сражениях погибло " + deadInRaid + ".";
    }
}
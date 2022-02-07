package ru.zaar2.kingdom;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import ru.zaar2.kingdom.core_second.EntryToCore;
import ru.zaar2.kingdom.core_second.Randomized;

public class Events {

    private String
            eventInfo,
            specialEventInfo;
    private Bitmap eventImage;

    public Events(LinearLayout layout, int eventNow_ID, Context context) {

        eventInfo = "";
        specialEventInfo = "";
        calcEvents(eventNow_ID, context);
        viewEvent(layout);
    }

    private void viewEvent(LinearLayout layout) {
        ((ImageView) layout.findViewById(R.id.event_image)).setImageBitmap(eventImage);
        ((TextView) layout.findViewById(R.id.eventInfo_tv)).setText(eventInfo);
        ((TextView) layout.findViewById(R.id.eventSpecialInfo_tv)).setText(specialEventInfo);
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
            case R.string.event_saboteur_captured:
                event_saboteur_captured(context);
                break;
            case R.string.event_saboteur_makeHisWay:
                event_saboteur_makeHisWay(context);
                break;
            case R.string.event_natural_phenomena:
                event_natural_phenomena(context);
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
        int lossToRats = new EntryToCore().findCurrentlyValue_ofSpecifiedParameter(
                context.getResources().getString(R.string.strDB_loss_grain_to_rats_accessory),
                context.getResources().getString(R.string.strDB_accessory),
                context,
                1
        );
        eventImage = BitmapFactory.decodeResource(context.getResources(), R.drawable.rats);
        eventInfo = "Нашествие крыс!\nМерзкие твари сожрали - " + lossToRats + " пуд.зерна.";
        specialEventInfo = "Собственные агенты докладывают, что данные сведения немного преувеличены." +
                "\nВозможно, настоящие мерзкие твари, окопались немного в других местах.";
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
                "Безвозвратно потеряно " + loss + " пуд.зерна.\n" +
                "Публичная порка первых же попавших под руку мелких чинов, немного улучшила Ваше настроение\n" +
                " и неплохо развлекла толпу.";
    }

    private void event_personCanHandle_increased(Context context) {
        eventImage = BitmapFactory.decodeResource(context.getResources(), R.drawable.events);
        eventInfo = "Увеличивается производительность Ваших подданных.";
        specialEventInfo = "Ваши длительные усилия по принуждению всех окружающих к труду, наконец-то принесли плоды." +
                "\nТеперь ваши подданные могут принести Вам еще больше пользы.";
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
        specialEventInfo = "Главного агронома на всякий случай надо бы посадить в клетку.\n" +
                "Он конечно не причем, но ведь кто-то должен ответить за это безобразие.\n" +
                "Ну не Вы же - в самом деле!";
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
        specialEventInfo = "В боях полегло " + dead + " воинов.";
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

    private void event_saboteur_captured(Context context){
        eventImage = BitmapFactory.decodeResource(context.getResources(), R.drawable.events);
        eventInfo = "Вражеский лазутчик схвачен!";
        specialEventInfo = "Ураа! Оджной сволочью меньше!\n" +
                "Естессно бумага о наказании невиновных и награждении непричастных уже лежит перед Вами.\n" +
                "Со знанием об истиных виновниках торжества и осознанием факта зависимости от окружающего Вас ничтожества - вы подписываете эту бумагу.";
    }

    private void event_saboteur_makeHisWay(Context context){
        eventImage = BitmapFactory.decodeResource(context.getResources(), R.drawable.events);
        eventInfo = "Доклад от собственной агентуры!\n" +
                "Судя по косвенным данным, в город прибыл еще один вражеский лазутчик. \n" +
                "И уже мутит воду среди несознательного элемента.\n";
        specialEventInfo = "Работа по поимке негодяя ведется со всем возможным рвением.\n" +
                "Но честно говоря - надежды мало.";
    }

    private void event_natural_phenomena(Context context) {
        eventImage = BitmapFactory.decodeResource(context.getResources(), R.drawable.events);
        int grain_loss = new EntryToCore().findCurrentlyValue_ofSpecifiedParameter(
                context.getResources().getString(R.string.strDB_loss_grain_dueTo_natural_phenomena_accessory),
                context.getResources().getString(R.string.strDB_accessory),
                context,
                1
        );
        int a = (int) new Randomized().random(1, 6);
        switch (a) {
            case 1:
                eventInfo = "Засуха и суховей погубили часть урожая!";
                break;
            case 2:
                eventInfo = "Пустынная саранча погубила часть урожая!";
                break;
            case 3:
                eventInfo = "Сильные ливневые дожди нанесли существенный ущерб урожаю!";
                break;
            case 4:
                eventInfo = "Широкая полоса града прошла через все ваши поля. Ущерб значителен!";
                break;
            case 5:
                eventInfo = "Заморозки существенно повредили посевам.";
                break;
            case 6:
                eventInfo = "Резкое понижение температуры в сочетании с малоснежной зимой, привели к вымерзание посевов озимых.";
                break;
            default:
                break;
        }
        specialEventInfo = "Народ предчувствуя нехорошее - волнуется.\n" +
                "Толпа во главе с главным мракобесом требует наказать виновных.\n" +
                "Пришлось организовать отлов и наказательные процедуры для некоторого колличества ведьм и колдунов.\n" +
                "Ущерб однако от этого не уменьшился и составил - " + grain_loss + " пуд. зерна.";
    }
}
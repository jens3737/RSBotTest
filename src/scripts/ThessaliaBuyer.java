package scripts;
import org.powerbot.script.Condition;
import org.powerbot.script.PollingScript;
import org.powerbot.script.Script;
import org.powerbot.script.Tile;
import org.powerbot.script.rt4.*;
import utils.DelayUtil;

@Script.Manifest(name = "Hello, RSBot!", properties = "author=Jens; topic=1296203; client=4;", description = "A 'Hello, World' example for RSBot" )
public class ThessaliaBuyer extends PollingScript<ClientContext> {

    final static int THESSALIA_ID = 534;
    final static int GYPSY_ID = 5082;
    final static int BANKER_ID = 2897;
    final Component PinkSkirt = ctx.widgets.widget(300).component(2).component(6);
    Boolean switchWorld = false;
    final static int[] Worlds = {0, 6, 12, 18, 24, 36, 42, 48, 60, 66};
    static int indexWorlds = 0;
    final static int CLOSEDDOOR_ID = 11775;
    final static int OPENDOOR_ID = 11774;
    static Boolean firstSwitch = true;
    final static int PINKSKIRT_ID = 1013;
//    public static final int WIDGET_STORE_CLOSE_BUTTON = 86;
//    public static final int WIDGET_STORE = 300;
    @Override
    public void start() {
        log.info("script started, RSBot!");
        log.info(ctx.players.local().tile().toString());
    }

    @Override
    public void stop() {
        log.info("script stopped, RSBot!");
    }

    @Override
    public void poll() {
        while(!isInventoryFull()) {
            if (!isThessaliaInViewport()) {
                while (!moveToShop()){
                    moveToShop();
                }
                if(!isDoorOpen()){
                    delay(100);
                    openDoor();
                    delay(400);
                    buySkirt();
                    switchWorld();
                }
                else {
                    buySkirt();
                    switchWorld();
                }
            }
            else{
                if(!isDoorOpen()){
                    openDoor();
                    buySkirt();
                    switchWorld();
                }
                else {
                    buySkirt();
                    switchWorld();
                }
            }
        }

        while(isInventoryFull()){
            if(isDoorOpenExit()){
                while (!moveToBank()){
                    moveToBank();
                }
                depositToBank();
            }
            else{
                openDoor();
                while (!moveToBank()){
                    moveToBank();
                }
                depositToBank();
            }

        }
    }

    public void openDoor(){
//        //check if door isn't already open
//        if(!isDoorOpen()){
            GameObject shopClosedDoor = ctx.objects.select().id(11775).nearest().poll();
            shopClosedDoor.interact("Open");
            delay(500);
//        }
    }

    /*----------- Bools -----------*/
    public Boolean isShopOpen() {
        return PinkSkirt.visible();
    }

    public Boolean isInventoryFull() { return ctx.inventory.select().count() == 28; }

    public Boolean isDoorOpen(){
        final Npc Thessalia = ctx.npcs.select().id(THESSALIA_ID).nearest().poll();

        if(Thessalia.tile().matrix(ctx).reachable()){
            return true;
        }
        else{
            return false;
        }
    }

    public Boolean isDoorOpenExit(){
        Npc Gypsy = ctx.npcs.select().id(GYPSY_ID).nearest().poll();
        if(Gypsy.tile().matrix(ctx).reachable()){
            return true;
        }
        else {
            return false;
        }
    }

    public Boolean isThessaliaInViewport() {
        Npc Thessalia = ctx.npcs.select().id(THESSALIA_ID).nearest().poll();
        return Thessalia.inViewport();
    }


    /*----------- Actions -----------*/
    public void closeShop(int milisec){
        delay(milisec);
        Component widgetExit = ctx.widgets.widget(300).component(1).component(3);
        widgetExit.click();
    }

    public void buySkirt() {
        log.info("rokje kopen");
            //trade Thessalia if widget is closed
            if (!isShopOpen()) {
                //Trade Thessalia

                while(!isShopOpen()){
                    final Npc Thessalia = ctx.npcs.select().id(THESSALIA_ID).nearest().poll();
                    ctx.camera.turnTo(Thessalia);
                    Thessalia.interact("Trade");
                    delay(2000);
                }
                //Buy if stock > 0
                if (PinkSkirt.itemStackSize() > 0) {
                    delay(1500);
                    PinkSkirt.interact("Buy 5", "Pink skirt");

                    //hierna mag world switch
                    closeShop(800);
                }
                //if no stock --> close widget
                else{
                    closeShop(800);
                }
            }

            //Widget is open, close it
            else {
                closeShop(800);
            }
    }

    public void switchWorld(){
        log.info("wereldje switchen");
        //beweeg muis naar logout en klik
        Component logoutBtn = ctx.widgets.widget(161).component(38);
        delay(600);
        logoutBtn.click();

        //indien eerste keer world switch, extra knop drukken
        if(ctx.widgets.widget(182).component(1).visible()){
            delay(300);
            ctx.widgets.widget(182).component(1).click();
            delay(3000);
            ctx.widgets.widget(69).component(7).component(Worlds[indexWorlds]).click();
            if(indexWorlds == 9){
                indexWorlds = 0;
            }
            else {
                indexWorlds++;
            }
        }

        //indien niet eerste keer world switch, klik op een van de free worlds
        else {
            delay(300);
            ctx.widgets.widget(69).component(7).component(Worlds[indexWorlds]).click();
            if(indexWorlds == 9){
                indexWorlds = 0;
            }
            else {
                indexWorlds++;
            }
            delay(1500);
        }
    }

    public Boolean depositToBank(){
        log.info("Deposit inventory to bank");
        final Npc Banker = ctx.npcs.select().id(BANKER_ID).nearest().poll();
        // zolang bank tab niet open is, interact met banker
        while(!ctx.bank.open()){
            delay(200);
            Banker.interact("Bank");
            delay(300);
        }

        //Wanneer bank tab open is, deposit inventory
        while(ctx.bank.open()){
            ctx.bank.deposit(1013, 27);
            delay(200);
            ctx.bank.close();
            log.info("true");
            return true;
        }
        return false;
    }

    public Boolean moveToBank(){
        Tile[] routeBank = new Tile[] {
                new Tile(3209, 3415, 0),
                new Tile(3208, 3424, 0),
                new Tile(3203,3429, 0),
                new Tile(3197,3429, 0),
                new Tile(3188, 3430, 0),
                new Tile(3183,3435, 0)};
        TilePath pathToBank = ctx.movement.newTilePath(routeBank);
        final Npc Banker = ctx.npcs.select().id(BANKER_ID).nearest().poll();

        delay(100);

        //Indien bank nie tin viewPort, loop richting bank
        if(!ctx.bank.inViewport()){

            pathToBank.traverse();
            return false;
        }
        //indien bank wel in viewport, draai camera naar Banker
        else {
            pathToBank.traverse();
            final Npc Banker = ctx.npcs.select().id(BANKER_ID).nearest().poll();
            ctx.camera.turnTo(Banker);
            delay(2500);
            return true;
        }
    }

    public Boolean moveToShop(){
        Tile[] routeShop = new Tile[] {
                new Tile(3183,3435, 0),
                new Tile(3188, 3430, 0),
                new Tile(3197,3429, 0),
                new Tile(3203,3429, 0),
                new Tile(3208, 3424, 0),
                new Tile(3209, 3415, 0)};
        TilePath pathToShop = ctx.movement.newTilePath(routeShop);
        delay(100);


        if(!isThessaliaInViewport()){
            pathToShop.traverse();
            return false;
        }
        else {
            pathToShop.traverse();
            delay(2500);
            return true;
        }
    }

    public void delay(int milisec) {
        Condition.sleep(milisec);
    }
}
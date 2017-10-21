package scripts;
import org.powerbot.script.Condition;
import org.powerbot.script.PollingScript;
import org.powerbot.script.Script;
import org.powerbot.script.Area;
import org.powerbot.script.Tile;
import org.powerbot.script.rt4.*;

@Script.Manifest(name = "Thessalia buyer", properties = "author=Jens; topic=1296203; client=4;", description = "Buy & bank Thessalia's items" )
public class ThessaliaBuyer extends PollingScript<ClientContext> {
    final static int THESSALIA_ID = 534;
    final static Tile[] AREA_FRONTSHOP = new Tile[]{
            new Tile(3209, 3415, 0),
            new Tile(3209, 3416, 0),
            new Tile(3209, 3414, 0),
            new Tile(3210, 3415, 0),
            new Tile(3210, 3414, 0),
            new Tile(3210, 3416, 0),
    };
    final static Tile[] AREA_SHOP = new Tile[]{
            new Tile(3208, 3418, 0),
            new Tile(3208, 3416, 0),
            new Tile(3208, 3415, 0),
            new Tile(3207, 3419, 0),
            new Tile(3207, 3418, 0),
            new Tile(3207, 3417, 0),
            new Tile(3207, 3416, 0),
            new Tile(3207, 3415, 0),
            new Tile(3207, 3414, 0),
            new Tile(3207, 3413, 0),
            new Tile(3207, 3412, 0),
            new Tile(3206, 3419, 0),
            new Tile(3206, 3418, 0),
            new Tile(3206, 3417, 0),
            new Tile(3206, 3416, 0),
            new Tile(3206, 3415, 0),
            new Tile(3206, 3414, 0),
            new Tile(3205, 3418, 0),
            new Tile(3205, 3417, 0),
            new Tile(3205, 3416, 0),
            new Tile(3205, 3414, 0),
            new Tile(3204, 3418, 0),
            new Tile(3204, 3417, 0),
            new Tile(3204, 3416, 0),
            new Tile(3204, 3414, 0),
            new Tile(3203, 3417, 0),
            new Tile(3203, 3416, 0),
            new Tile(3203, 3414, 0),
            new Tile(3202, 3417, 0),
            new Tile(3202, 3416, 0),
            new Tile(3202, 3414, 0),
    };
    final static Tile[] AREA_BANK = new Tile[]{ new Tile(3185, 3433, 0),
            new Tile(3185, 3434, 0),
            new Tile(3185, 3435, 0),
            new Tile(3185, 3436, 0),
            new Tile(3185, 3437, 0),
            new Tile(3185, 3438, 0),
            new Tile(3185, 3439, 0),
            new Tile(3185, 3440, 0),
            new Tile(3185, 3441, 0),
            new Tile(3185, 3442, 0),
            new Tile(3185, 3443, 0),
            new Tile(3185, 3444, 0),
            new Tile(3185, 3445, 0),
            new Tile(3185, 3446, 0),
            new Tile(3185, 3447, 0),
            new Tile(3185, 3448, 0),
            new Tile(3184, 3433, 0),
            new Tile(3184, 3434, 0),
            new Tile(3184, 3435, 0),
            new Tile(3184, 3436, 0),
            new Tile(3184, 3437, 0),
            new Tile(3184, 3438, 0),
            new Tile(3184, 3439, 0),
            new Tile(3184, 3440, 0),
            new Tile(3184, 3441, 0),
            new Tile(3184, 3442, 0),
            new Tile(3184, 3443, 0),
            new Tile(3184, 3444, 0),
            new Tile(3184, 3445, 0),
            new Tile(3184, 3446, 0),
            new Tile(3183, 3433, 0),
            new Tile(3183, 3434, 0),
            new Tile(3183, 3435, 0),
            new Tile(3183, 3436, 0),
            new Tile(3183, 3437, 0),
            new Tile(3183, 3438, 0),
            new Tile(3183, 3439, 0),
            new Tile(3183, 3440, 0),
            new Tile(3183, 3441, 0),
            new Tile(3183, 3442, 0),
            new Tile(3183, 3443, 0),
            new Tile(3183, 3444, 0),
            new Tile(3183, 3445, 0),
            new Tile(3183, 3446, 0),
            new Tile(3183, 3447, 0),
            new Tile(3182, 3433, 0),
            new Tile(3182, 3434, 0),
            new Tile(3182, 3435, 0),
            new Tile(3182, 3436, 0),
            new Tile(3182, 3437, 0),
            new Tile(3182, 3438, 0),
            new Tile(3182, 3439, 0),
            new Tile(3182, 3440, 0),
            new Tile(3182, 3441, 0),
            new Tile(3182, 3442, 0),
            new Tile(3182, 3443, 0),
            new Tile(3182, 3444, 0),
            new Tile(3182, 3445, 0),
            new Tile(3182, 3446, 0),
            new Tile(3182, 3447, 0),
            new Tile(3181, 3433, 0),
            new Tile(3181, 3434, 0),
            new Tile(3181, 3435, 0),
            new Tile(3181, 3436, 0),
            new Tile(3181, 3437, 0),
            new Tile(3181, 3438, 0),
            new Tile(3181, 3439, 0),
            new Tile(3181, 3440, 0),
            new Tile(3181, 3441, 0),
            new Tile(3181, 3442, 0),
            new Tile(3181, 3443, 0),
            new Tile(3181, 3444, 0),
            new Tile(3181, 3445, 0),
            new Tile(3181, 3446, 0),
            new Tile(3180, 3434, 0),
            new Tile(3180, 3437, 0),
            new Tile(3180, 3439, 0),
            new Tile(3180, 3441, 0),
            new Tile(3180, 3445, 0),
            new Tile(3180, 3446, 0)
    };
    final static int GYPSY_ID = 5082;
    final static int BANKER_ID = 2897;

    //1013 skirt, 1757 apron, 1007 cape
    final static int[] BUY_IDS = {1013};

    final Component[] BuyComponents = {ctx.widgets.widget(300).component(2).component(6)
//            ctx.widgets.widget(300).component(2).component(9),
//            ctx.widgets.widget(300).component(2).component(5)
    };

    final Component PinkSkirt = ctx.widgets.widget(300).component(2).component(6);
    final Component RedCape = ctx.widgets.widget(300).component(2).component(9);
    final Component BrownApron = ctx.widgets.widget(300).component(2).component(5);

    final static int[] Worlds = {0, 6, 12, 18, 24, 36, 42, 48, 60, 66};
    static int indexWorlds = 0;

    @Override
    public void start() {
        log.info("script started, RSBot!");
        log.info(ctx.players.local().tile().toString());
    }

    @Override
    public void stop() {
        log.info("script stopped, RSBot!");
        start();
    }

    @Override
    public void poll() {
        while(!isInventoryFull()) {
            if (!isPlayerInArea(AREA_SHOP)) {
                if(moveToShop()){
                    if(!isDoorOpen()){
                        log.info("deur is toe");

                        delay(100);
                        openDoor(1);
                        delay(400);
                        buyItems();
                    }
                    else {
                        log.info("deur is open");
                        buyItems();
                        delay(200);
                    }
                }
            }
            else{
                buyItems();
            }
        }

        while(isInventoryFull()){
            //kijk of je in shop bent
            if(isPlayerInArea(AREA_SHOP)){
                log.info("player is in shop & inventory is full");
                if(!isDoorOpenExit()){
                    log.info("deur is toe");
                    openDoor(0);
                }
                else{
                    log.info("deur is open");
                    if(moveToBank()){
                        log.info("je bent in de bank");
                        depositToBank();
                    }
                }
            }
            //indien speler in bank is
            else if(isPlayerInArea(AREA_BANK)){
                log.info("je bent in de bank");
                depositToBank();
            }
            //indien ergens tussenin, beweeg naar bank
            else {
                if(moveToBank()){
                    log.info("je bent in de bank");
                    depositToBank();
                }
            }
        }
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

    public Boolean isPlayerInArea(Tile[] area){
        Boolean result = false;
        for (int i = 0; i < area.length; i++){
            if(area[i].toString().equals(ctx.players.local().tile().toString())){
                result = true;
            }
        }
        return result;
    }

    public Boolean isPlayerInFrontofShop(){
        if(isPlayerInArea(AREA_FRONTSHOP)){
            return true;
        }

        else  {
            log.info(".local().tile()"+ctx.players.local().tile().toString());
            return false;
        }
    }

    public Boolean isPlayerInBank(){
        if(isPlayerInArea(AREA_BANK)){
            return true;
        }

        else  {
            log.info(".local().tile()"+ctx.players.local().tile().toString());
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
        while(isShopOpen()){
            widgetExit.click();
        }

    }

    //je bent binnen  = 0, buiten == 1
    public void openDoor(int location){
//        //check if door isn't already open
//        if(!isDoorOpen()){
        switch (location) {
            case 1:
                while (!isDoorOpen()) {
                    GameObject shopClosedDoor = ctx.objects.select().id(11775).nearest().poll();
                    ctx.camera.turnTo(shopClosedDoor);
                    delay(500);
                    shopClosedDoor.interact("Open");
                    delay(1200);
                }
                break;
            case 0:
                while (!isDoorOpenExit()) {
                    GameObject shopClosedDoor = ctx.objects.select().id(11775).nearest().poll();
                    ctx.camera.turnTo(shopClosedDoor);
                    delay(500);
                    shopClosedDoor.interact("Open");
                    delay(1200);
                }
                break;

//        }
        }
    }

    public void interactNpc(Npc npc, String option){
        ctx.camera.turnTo(npc);
        npc.interact(option);
        delay(2000);
    }

    public void doBuy(Component[] buyComponents){
        for(int i = 0; i < buyComponents.length; i++){
            while (buyComponents[i].itemStackSize() > 0 && !isInventoryFull()) {
                delay(500);
                buyComponents[i].interact("Buy 5");
            }
        }
        if(!isInventoryFull()){
            closeShop(800);
            switchWorld();
        }
        else closeShop(800);

//            while (PinkSkirt.itemStackSize() > 0 && !isInventoryFull()) {
//                delay(1500);
//                PinkSkirt.interact("Buy 5", "Pink skirt");
//            }
//            while (RedCape.itemStackSize() > 0 && !isInventoryFull()) {
//                delay(500);
//                RedCape.interact("Buy 5", "Red cape");
//            }
//            while(BrownApron.itemStackSize()>0 && !isInventoryFull()){
//                delay(500);
//                BrownApron.interact("Buy 5", "Brown apron");
//            }
    }

    public void buyItems() {
        log.info("rokje kopen");
            //trade Thessalia if widget is closed
            if (!isShopOpen()) {
                //Trade Thessalia
                final Npc Thessalia = ctx.npcs.select().id(THESSALIA_ID).nearest().poll();
                while(!isShopOpen()) {
                    interactNpc(Thessalia, "Trade");
                }
                //Buy if stock > 0
                doBuy(BuyComponents);
                delay(5000);
            }

            //Widget is open, close it
            else if(isShopOpen()) {
                doBuy(BuyComponents);
                delay(5000);
            }
    }

    public void switchWorld(){
        log.info("wereldje switchen");
        //beweeg muis naar logout en klik
        //laptop
        Component logoutBtn = ctx.widgets.widget(161).component(38);
//        Component logoutBtn = ctx.widgets.widget(548).component(33);
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

    public void doDeposit(int[] itemIDs){
        for(int i = 0; i < itemIDs.length; i++){
            ctx.bank.deposit(itemIDs[i], 27);
            delay(200);
        }
        ctx.bank.close();
//
//
//        ctx.bank.deposit(PINKSKIRT_ID, 27);
//        delay(200);
//        ctx.bank.deposit(REDCAPE_ID, 27);
//        delay(200);
//        ctx.bank.deposit(BROWNAPRON_ID, 27);
//        delay(200);
//        ctx.bank.close();
    }

    public Boolean depositToBank(){
        log.info("Deposit inventory to bank");
        final Npc Banker = ctx.npcs.select().id(BANKER_ID).nearest().poll();
        // zolang bank tab niet open is, interact met banker
        while(!ctx.bank.open()){
            interactNpc(Banker, "Bank");
        }

        //Wanneer bank tab open is, deposit inventory
        while(ctx.bank.open()){
            doDeposit(BUY_IDS);
            delay(800);
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
        delay(100);

        while(!isPlayerInBank()){
            pathToBank.traverse();
            delay(500);
        }
        if(isPlayerInBank()) {
            log.info("ik ben in de bank");
            return true;
        }
        else return false;
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

        while(!isPlayerInFrontofShop()){
            pathToShop.traverse();
            delay(500);
        }
        if(isPlayerInFrontofShop()){
            return true;
        }
        else return false;


//        //
//        if(!isThessaliaInViewport()){
//            pathToShop.traverse();
//            return false;
//        }
//        else {
//            pathToShop.traverse();
//            delay(2500);
//            return true;
//        }
    }

    public void delay(int milisec) {
        Condition.sleep(milisec);
    }
}
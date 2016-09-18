/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thetapgame;

import java.util.HashMap;
import java.util.Random;

/**
 *
 * @author Kraugen
 */
public class TextChallengeDatabase {
    private HashMap<String,Boolean> textChallenges;
    private String[] textDatabase;
    
    public TextChallengeDatabase(){
         this.textChallenges = initChallenges();
    }
    
    public HashMap<String,Boolean> getTextChallenges(){
        return this.textChallenges;
    }
    
    public HashMap<String,Boolean> initChallenges(){
        
        HashMap<String,Boolean> textChallengeHashMap = new HashMap<String,Boolean>();
        
        String[] jokes = {
        "Tre slurker til den som ikke har tappet på over en mnd. ",
        //"Hvis det eksisterer en jomfru i salen: Venligst CHUG snarest.",
        "Gå bort til en person du ikke kjenner og si: Hei, 'næmmen så flott a gitt.' Forlat deretter personen uten å si noe mer..",    
        "Personen med minst matches på Tinder: Drikk 4 slurker. Den med flest matches: Gi bort 4 slurker. De i forhold er fritatt.",
        //"Alle som IKKE har tappet svenskt må drikke 2 slurker.",
        "Løp du maraton idag? Hvis ikke, drikk 5 slurker",
        "OPPKJØRING. Den med færrest alkoholenheter innabords må CHUGGE. Er du serr edru??? FOKUS.",
        //"De som aldri har hustlet i utlandet: 10 pushups.",
        "FOKUS!!!!! 10 SLURKER",
        "5 slurker til personen med MINST erfaring på sjekkern. Dette skal fellesskapet bli enige om. ",
        //"RUTINEKONTROLL. Når tappet du sist? Dersom du tappet for mindre enn et døgn siden slipper du bot. Et forelegg på 5 slurker vil bli tildelt dersom vedkommende ikke har tappet det siste døgnet.",
        "RUTINEONTROLL! Pek på personen du mener er mest gentleman. Alle andre må chugge, inkludert deg selv.",
        "Drink 10 zips",
        "RIM. Vedkommende som havnet på dette feltet starter. Retning: Med klokken. Rim på et ord du velger selv.",
        "DU BLE TRUFFET AV ET CUMSHOT. Drikk 3 slurker.",
        //"INSPEKSJON. Har du tappet flere enn 10? Hvis ikke, drikk 5 slurker.",
        "INSPEKSJON!!! Stemmer det at du er under 24? Vis leg og drikk 5 slurker om du er under 24.",
        "Personene som er i et forhold må drikke 3 slurker. De som har vært i et forhold i mer enn 2 år må drikke 5 slurker.",
        "FOKUS! Du er nå nødt til å servere TIDENES sjekkereplikk til salen. Dersom salen mener den er for tam må du drikke 5 slurker.",
        "Gikk du BI? Dersom dette er tilfelle, fyll opp en koppog chug. Gjenta prosessen to ganger.",
        "BLAZERBONUS. Har du på deg blazer: Del ut 5 slurker. Mangler du derimot blazer: Drikk 5 slurker. Dersom du kompenserer nypussede sko: Del ut 3 slurker.",
        "KRISEMØTE!!!! Det er blitt drukket for lite. Alle må drikke 3 slurker. ",
        //"Kategori! Retning: Med klokken. Si en kategori i forbindelse med tapping(f.eks kondom-brands). Neste person i rekken har så to sekunder på å nevne et ord tilsvarende kategorien. Failer denne personen må han drikke 5 slurker.",
        "Finn en kortstokk. Snu et kort. Er det høyere enn 9? Hvis ikke, drikke 5 slurker.",
        //"KNALLHARD doggy er observert på gutterommet. Del ut 5 slurker.",
        "Fortell en meningsløs vits. Dersom ingen i salen ler -> 3 slurker.",
        "Beertime for blondes. All players having blonde hair-> Drink 5 zips.",
        "ALLAHU AKBAR!!!!! Players go around the table and name off as many gun brands as they can until one player repeats a brand or fails. That player needs to drink an amount of zips decided by Kraugerud. ",
        "Going clockwise each player names a Pokemon. The player which repeats or cannot think of any Pokemon has to finish his/her drink.",
        "If there is any player around the table named Vigdis, Børre, Raymond or Ruth: finish your drinks.",
        "Triana-crush!!!! If you would have had sex with Triana from Paradise hotel choose another player to finish his/her drink.",
        "All players turn the wifi on and leave the phone on the table. Every time a person recieves a snapchat, that person must drink 5 zips",
        "GOLDDIGGER. All male players count how much money they have on them. The poor looser with the least amount of cash must finish his drink, then refill it and finish it again.",
        "All players having a beard with a length above 1cm have to finish their drink.",
        "Every player must perform a silly walk at the same time. The person drawing this card has to Snapchat it and put it on his/her MyStory. ",
        "SHITSTORM IS COMING. You have to say one degrading thing about all players around the table. If that person replies by saying fuck you, you need to drink 5 zips",
        "THE SALESMAN. If Erdalen is in the room, he has to finish his drink. If not, the drawer of this card needs to call him and ask him to choose a player in the room. That player must finish his/her drink.",
        "Snap, clap and point your fingers at the person sitting across you. You must both chug. Loser drinks another.",
        "If you have ever solved a rubics cube, DRINK!! 5 zips should do.",
        "All players that have Pokemon Go on their phone, finish your drink.",
        "All players wearing green must drink 3 zips. ",
        "Any time this player drinks when not ordered by a card, he/she has to finish the drink",
        "Dicaprio!!! going clockwise, mention a movie with Leonardo Dicaprio until a player fails. that player must take 3 zips.",
        "Players that have ever puked from drinking must drink 3 zips. ",
        "Next time you swear you have to chug. ",
        "If you have had sex with anbody around the table, drink 5 zips and kiss that person."
        };   
        
        this.textDatabase = jokes;
        
        for (int i = 0;i<jokes.length;i++){
            textChallengeHashMap.put(jokes[i],false);
        }
        
        return textChallengeHashMap;
    }
    
    public int returnRandNum(){
        Random randomizer = new Random();
        return randomizer.nextInt(this.textDatabase.length-1) + 1;
    }
    
    public String getRandChallenge(){
        String joke = "";
        boolean isFinished = false;
        while(!isFinished){
            if(!(this.textChallenges.get(this.textDatabase[returnRandNum()]))){
                this.textChallenges.put(this.textDatabase[returnRandNum()],true);
                isFinished = true;
                joke = this.textDatabase[returnRandNum()];
            }
        }
        
        return joke;
    }
}

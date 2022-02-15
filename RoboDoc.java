/*

RegEx konverzia dokumentácie do PHP (so zamlčanou príponou):


‼‼‼‼‼‼‼‼‼‼‼‼‼‼‼‼‼‼‼‼‼‼‼‼‼‼‼‼‼‼‼‼‼‼‼‼‼‼‼‼‼‼‼‼‼‼‼‼‼‼‼‼
‼ Premenuj všetky vygenerované html súbory na php. ‼
‼‼‼‼‼‼‼‼‼‼‼‼‼‼‼‼‼‼‼‼‼‼‼‼‼‼‼‼‼‼‼‼‼‼‼‼‼‼‼‼‼‼‼‼‼‼‼‼‼‼‼‼


Filter:
*.php
Priečinok:
C:\_Sync\Vyucba\Materialy\@Robot\Vývoj\robodoc\class-use\


Dočasné (dúfam) v podpriečinku class-use:

<body>
<body><div style="background-color: #fee; border: 2px solid red; color: #800; opacity: 0.95; margin: 0px; padding: 20px; position: fixed; top: 50px; right: 0px; z-index: 500;"><b>Upozornenie!</b> Použitie tried zatiaľ nie je spracované. Tento dokument je len informačný.</div>

(<a.*?)( href=".*?")(.*?>)
$1$3<!--$2 -->

"\.\./\.\./
"../


O úroveň vyššie:

<a href="class-use
<a target="_blank" href="class-use


"index\.html
".

"(?!http)([^"]+)\.html
"$1


Teoreticky už netreba (overiť):

href="GRobot\.
href="GRobot-

Overiť: href="GRobot\.(?!html)


Rôzne:

(href="[^"]+)(\(|\)|, +)
$1-


RegEx for error report:
^.*(unexpected text|self-closing element not allowed|warning: no @param|warning: no @return|GRobotDoc).*\n.*\n.*\n


Filter výstupu:
^(?:Generating |Kopírujem |Triedim kategórie |Počet zmien |Súbor ).*\n


(Hľadaj v celej dokumentácii:
<pre CLASS="example">[^<]+-
<pre CLASS="example">[^<]+<[^/]
<pre CLASS="example">[^<]+>
\{@code[^}]+&(gt|lt|#45);
)


RegEx for examples:
===================

Príprava na nahrádzanie odkazov na metódy v rámci rámca:
\b([\w\pL]+)\b
{@link __CLASS__#$1(__ARGS__) $1}

Alebo po nahradení všetkých kľúčových slov a literálov (ale hádže to
výnimky, ktorých sa nie je možné zbaviť):
(?<!\{@|\{@(code|link) |// )\b([\w\pL]+)\b
{@link __CLASS__#$1(__ARGS__) $1}

(Po takomto hromadnom nahradení by sa dala navrhnúť séria regulárnych
nahradení na odstránenie toho, čo sa nahradilo navyše, ako komentáre
a niektoré kľúčové slová - atribúty a premenné by sa však zrejme museli
nahrádzať ručne.)


Čísla (obyčajné)
\b([0-9][_0-9a-fA-F\.ex]*)\b
{@code num$1}

Tieto znaky musia byť nahradené entitami, okrem(!) výskytov v rámci
kódov {@code …} lebo by to mohlo robiť „neplechu“:

	RegEx to check: [-<>]

Pozor! Texty ako y&#45;ovú sa prevedú na y&amp;#45;ovú, takže ich treba
ponechať v tvare: y-ovú… Naopak, výrok typu (&#45;stred je ponechaný
nedotknutý, ale v tom prípade by nemusel prekážať ani pôvodný tvar (-stred,
ale pre istotu to treba previesť.

 - 
 &#45; 
<
&lt;
>
&gt;


Jednoriadkový komentár:
(//[^\n]+)$
{@code comm$1}

Reťazce a znaky (bez escape \")

	RegEx to check: ["']

("[^"]*")
{@code srg$1}
('[^']*')
{@code srg$1}

Rezervované slová Javy:
\b(new|public|protected|private|final|static|extends|implements|if|else|do|while|for|switch|case|default|break|continue|return|try|catch|finally|throws?|import|instanceof)\b
{@code kwd$1}

Údajové typy Javy:
\b(class|void|interface|byte|short|int|long|float|double|char|boolean)\b
{@code type$1}

Prekrytie (a iné anotácie):
@(Override|SuppressWarnings)
{@code kwd@}$1

Špeciálne hodnoty:
\b(super|this|null|true|false)\b
{@code val$1}

&#\{@code num45\}
&#45

Ďalšie:

\{@link __CLASS__#(ObsluhaUdalostí|ÚdajeUdalostí|GRobot|Kláves|Obrázok|Oblasť|Súbor|Svet|Spojnica|Bod|Farba|Častica|Plátno|Tlačidlo|RolovaciaLišta|Poloha|String|Double|Boolean|SVGPodpora|Roj|System|Math|Tlač)\(__ARGS__\) \1\}
{@link $1 $1}

\{@link (ObsluhaUdalostí|ÚdajeUdalostí|GRobot|Kláves|Obrázok|Oblasť|Súbor|Svet|Spojnica|Bod|Farba|Častica|Plátno|Tlačidlo|RolovaciaLišta|Poloha|String|Double|Boolean|SVGPodpora|Roj|System|Math|Tlač) \1\}\.\{@link __CLASS__
{@link $1 $1}.{@link $1

\{@link __CLASS__#(strop|podlaha)\(__ARGS__\) \1\}
{@link Plátno $1}

\{@link Plátno (strop|podlaha)\}\.\{@link __CLASS__
{@link Plátno $1}.{@link Plátno

#([^\(]+)\(__ARGS__\) \1\}\(\)
#$1() $1}()

__CLASS__#((?:svetlo|tmavo)?(?:biela|čierna|červená|hnedá|modrá|oranžová|purpurová|ružová|sivá|šedá|tyrkysová|zelená|žiadna|žltá|antracitová|papierová|uhlíková|snehová|akvamarínová|nebeská|atramentová))\(__ARGS__\)
Farebnosť#$1

__CLASS__#((?:otoč|skoč|odskoč|choď)(?:Na)?(?:PoOblúku)?)\(__ARGS__\) \1\}\(
GRobot#$1(double, double) $1}(
GRobot#$1(double) $1}(
GRobot#$1() $1}(

__CLASS__#((?:hrúbka|polož|poloha|predvolenáHrúbka|zdvihni)?(?:[Čč]iar[ya]|Per[oa])(?:Položené|Zdvihnuté)?)\(__ARGS__\) \1\}\(
GRobot#$1(double) $1}(
GRobot#$1() $1}(

__CLASS__#(veľkosť|vpravo|vľavo|vpred|vzad|do(?:predu|zadu|ľava|prava))\(__ARGS__\) \1\}\(
GRobot#$1(double) $1}(
GRobot#$1() $1}(

Math#([^\(]+)\(__ARGS__
Math#$1(double

Väčšinou toto platí:
\(__ARGS__\)( [_\pL\pN]+\})(?!\()
$1


Hľadanie dvojitých prázdnych riadkov:
<pre(?:\p{all}(?<!</pre))*\n\n\n
Treba nahradiť (tesne prilepené, lebo to aj tak zobrazuje voľný riadok
pred a za):
<hr/>


Kontrola chýb v komentároch:
\{@code comm//[^\}]+\{

Kontrola správneho uvedenia prekrytia reakcií – buď GRobot:

\{@code kwd@\}Override \{@code kwdpublic\} \{@code typevoid\} \b([\w\pL]+)\b
{@code kwd@}Override {@code kwdpublic} {@code typevoid} {@link GRobot#$1() $1}

alebo ObsluhaUdalostí:

\{@code kwd@\}Override \{@code kwdpublic\} \{@code typevoid\} \b([\w\pL]+)\b
{@code kwd@}Override {@code kwdpublic} {@code typevoid} {@link ObsluhaUdalostí#$1() $1}

Nesprávne uvedenie konštruktorov:
kwdnew\} +\{@link +\b([\w\pL]+)\b(?!#)
kwdnew} {@link GRobot.$1#GRobot.$1(__ARGS__)


{@link ÚdajeUdalostí ÚdajeUdalostí}
{@link ÚdajeUdalostí údajeUdalostí} –––
{@code currÚdajeUdalostí}
{@code currúdajeUdalostí} –––
{@link Súbor Súbor}
{@link Súbor súbor}
{@link Svet Svet}
{@link Svet svet} ---
{@code currSúbor}
{@code currsúbor}
{@code currSvet}
{@code currsvet} ---
{@link ObsluhaUdalostí#GRobot.ObsluhaUdalostí() ObsluhaUdalostí}


================================================================================


<p class="image"><img src="resources/(.*)" alt="(.*)" onerror="this\.onerror=null; this\.src='resources/(.*)';" /><br />
<p><image>$1<alt/>$2<onerror>$3</onerror></image>


<span class="imageSpace"> </span><br />


<p><image>matica-vseobecnej-transformacie.svg<alt/>Matica všeobecnej transformácie.<onerror>matica-vseobecnej-transformacie.png</onerror></image>
…</p>

<image>
<img src="resources/

</image>
" /><br /><span class="imageSpace"> </span><br />

<onerror>
" onerror="this.onerror=null; this.src='resources/

</onerror>
';

<alt/>
" alt="


<img src="resources/SVGTvarRobota.png" alt="Ukážka zmeny tvaru robota s pomocou SVG tvarov tesne po prvom spustení." /><br /><span class="imageSpace"> </span><br />
*/

import java.util.Map;
import java.util.TreeMap;
import java.util.Vector;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

import knižnica.GRobot;
import knižnica.Konštanty;
import knižnica.Súbor;
import knižnica.Svet;
import knižnica.Zoznam;

public class RoboDoc extends GRobot
{
	private boolean javadoc = true;
	private boolean robodoc = true;
	// private boolean htmlclean = false;

	/******************************************************************/

	private final Zoznam<String> obsahSúboru = new Zoznam<String>();
	private String názovSúboru;

	private String[] pridajDoHTML = {
		"</head>",
		"<!-- Translated and extended by RoboDoc -->",
		"<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" />",
	};

	private String[] premenujSúbory = {
		"constant-values.html",
		"hodnoty-konstant.html",
	};

	private String[] premenujSúboryBalíčka = {
		"Farebnosť.html",
		"Farebnostt.html",

		"Bod.html",
		"Bod.html",

		"Castica.html",
		"Castica.html",

		"Častica.html",
		"CCastica.html",

		"Farba.html",
		"Farba.html",

		"GRobot.Spojnica.html",
		"GRobot-Spojnica.html",

		"Kláves.html",
		"Klaaves.html",

		"Klaves.html",
		"Klaves.html",

		"Oblasť.html",
		"Oblastt.html",

		"Oblast.html",
		"Oblast.html",

		"Obrázok.html",
		"Obraazok.html",

		"Obrazok.html",
		"Obrazok.html",

		"ObsluhaUdalostí.html",
		"ObsluhaUdalostii.html",

		"ObsluhaUdalosti.html",
		"ObsluhaUdalosti.html",

		"Písmo.html",
		"Piismo.html",

		"Pismo.html",
		"Pismo.html",

		"Plátno.html",
		"Plaatno.html",

		"Platno.html",
		"Platno.html",

		"Plazma.html",
		"Plazma.html",

		"PoložkaPonuky.html",
		"PolozzkaPonuky.html",

		"PolozkaPonuky.html",
		"PolozkaPonuky.html",

		"RolovaciaLišta.html",
		"RolovaciaLissta.html",

		"RolovaciaLista.html",
		"RolovaciaLista.html",

		"Rozmer.html",
		"Rozmer.html",

		"Rozmery.html",
		"Rozmery.html",

		"PoznámkovýBlok.html",
		"PoznaamkovyyBlok.html",

		"PoznamkovyBlok.html",
		"PoznamkovyBlok.html",

		"KontextováPonuka.html",
		"KontextovaaPonuka.html",

		"KontextovaPonuka.html",
		"KontextovaPonuka.html",

		"KontextováPoložka.html",
		"KontextovaaPolozzka.html",

		"KontextovaPolozka.html",
		"KontextovaPolozka.html",

		"KreslenieTvaru.html",
		"KreslenieTvaru.html",

		"Súbor.html",
		"Suubor.html",

		"Subor.html",
		"Subor.html",

		"Svet.html",
		"Svet.html",

		"Schránka.html",
		"Schraanka.html",

		"Schranka.html",
		"Schranka.html",

		"SVGPodpora.html",
		"SVGPodpora.html",

		"SVGPodpora.Transformácia.html",
		"SVGPodpora-Transformaacia.html",

		"SVGPodpora.Transformacia.html",
		"SVGPodpora-Transformacia.html",


		"Tlač.html",
		"Tlacc.html",

		"Tlac.html",
		"Tlac.html",

		"Tlačidlo.html",
		"Tlaccidlo.html",

		"Tlač.Slovo.html",
		"Tlacc-Slovo.html",

		"Tlač.Fragment.html",
		"Tlacc-Fragment.html",

		"Tlač.Parametre.html",
		"Tlacc-Parametre.html",

		"Tlač.RiadokSlov.html",
		"Tlacc-RiadokSlov.html",

		"Tlač.BlokSlov.html",
		"Tlacc-BlokSlov.html",

		"Tlacidlo.html",
		"Tlacidlo.html",


		"ÚdajeUdalostí.html",
		"UUdajeUdalostii.html",

		"UdajeUdalosti.html",
		"UdajeUdalosti.html",

		"Zoznam.html",
		"Zoznam.html",

		"GRobotException.html",
		"GRobotException.html",

		"Zoznam.ObrátenýIterátor.html",
		"Zoznam-ObraatenyyIteraator.html",

		"Zoznam.ObratenyIterator.html",
		"Zoznam-ObratenyIterator.html",

		"Zvuk.html",
		"Zvuk.html",

		"Priehľadnosť.html",
		"Priehlladnostt.html",

		"Roj.Bod.html",
		"Roj-Bod.html",

		"Roj.Smerník.html",
		"Roj-Smerniik.html",

		"Roj.Smernik.html",
		"Roj-Smernik.html",

		"Archív.html",
		"Archiiv.html",


		// (package private):

		// "Bod.PanelPolohy.html",
		// "Bod-PanelPolohy.html",

		// "Farba.PanelFarieb.html",
		// "Farba-PanelFarieb.html",

		// "GRobot.UpravText.html",
		// "GRobot-UpravText.html",

		// "GRobot.Vrstva.html",
		// "GRobot-Vrstva.html",


		"GRobotException.Dennik.html",
		"GRobotException-Dennik.html",

		"GRobotException.Denník.html",
		"GRobotException-Denniik.html",

		"GRobotException.Chyba.html",
		"GRobotException-Chyba.html",

		"Konstanty.html",
		"Konstanty.html",

		"Konštanty.html",
		"Konsstanty.html",

		"package-use.html",
		"package-use.html",


		// (package private):

		// "Obrázok.VykonajVObrázku.html",
		// "Obraazok-VykonajVObraazku.html",

		// "Plátno.VnútornáKonzola.html",
		// "Plaatno-VnuutornaaKonzola.html",

		// "Plátno.ZálohaKonzoly.html",
		// "Plaatno-ZaalohaKonzoly.html",

		// "PoznámkovýBlok.RolovaniePoznámkovéhoBloku.html",
		// "PoznaamkovyyBlok-RolovaniePoznaamkoveehoBloku.html",


		"Rad.html",
		"Rad.html",

		"Skript.html",
		"Skript.html",

		"Skript.PremenneSkriptu.html",
		"Skript-PremenneSkriptu.html",

		"Skript.PremennéSkriptu.html",
		"Skript-PremenneeSkriptu.html",

		"Spojenie.html",
		"Spojenie.html",


		// (package private):

		// "Súbor.Sekcia.html",
		// "Suubor-Sekcia.html",

		// "Svet.KlávesováSkratka.html",
		// "Svet-KlaavesovaaSkratka.html",


		"Svet.PrikazovyRiadok.html",
		"Svet-PrikazovyRiadok.html",

		"Svet.PríkazovýRiadok.html",
		"Svet-PriikazovyyRiadok.html",

		"Svet.PríkazovýRiadok.PresmerovanieVýstupu.html",
		"Svet-PriikazovyyRiadok-PresmerovanieVyystupu.html",

		"Uhol.html",
		"Uhol.html",


		// (package private):

		// "Uhol.PanelSmeru.html",
		// "Uhol-PanelSmeru.html",

		// "Zvuk.ZoznamZvukov.html",
		// "Zvuk-ZoznamZvukov.html",


		"package-summary.html",
		"index.html",
	};


	private String[] premenujSúboryPoužitiaMetód = {
		"Archív.html",
		"Archiiv.html",

		"Častica.html",
		"CCastica.html",

		"Farba.PanelFarieb.html",
		"Farba-PanelFarieb.html",

		"Farebnosť.html",
		"Farebnostt.html",

		"GRobotException.Denník.html",
		"GRobotException-Denniik.html",

		"Kláves.html",
		"Klaaves.html",

		"Konštanty.html",
		"Konsstanty.html",

		"KontextováPoložka.html",
		"KontextovaaPolozzka.html",

		"KontextováPonuka.html",
		"KontextovaaPonuka.html",

		"Oblasť.html",
		"Oblastt.html",

		"Obrázok.html",
		"Obraazok.html",

		"Obrázok.VykonajVObrázku.html",
		"Obraazok-VykonajVObraazku.html",

		"ObsluhaUdalostí.html",
		"ObsluhaUdalostii.html",

		"Písmo.html",
		"Piismo.html",

		"Plátno.html",
		"Plaatno.html",

		"Plátno.VnútornáKonzola.html",
		"Plaatno-VnuutornaaKonzola.html",

		"Plátno.ZálohaKonzoly.html",
		"Plaatno-ZaalohaKonzoly.html",

		"PoložkaPonuky.html",
		"PolozzkaPonuky.html",

		"PoznámkovýBlok.html",
		"PoznaamkovyyBlok.html",

		"PoznámkovýBlok.RolovaniePoznámkovéhoBloku.html",
		"PoznaamkovyyBlok-RolovaniePoznaamkoveehoBloku.html",

		"Priehľadnosť.html",
		"Priehlladnostt.html",

		"Roj.Smerník.html",
		"Roj-Smerniik.html",

		"RolovaciaLišta.html",
		"RolovaciaLissta.html",

		"Schránka.html",
		"Schraanka.html",

		"Skript.PremennéSkriptu.html",
		"Skript-PremenneeSkriptu.html",

		"Súbor.html",
		"Suubor.html",

		"Súbor.Sekcia.html",
		"Suubor-Sekcia.html",

		"Svet.KlávesováSkratka.html",
		"Svet-KlaavesovaaSkratka.html",

		"Svet.PríkazovýRiadok.html",
		"Svet-PriikazovyyRiadok.html",

		"Svet.PríkazovýRiadok.PresmerovanieVýstupu.html",
		"Svet-PriikazovyyRiadok-PresmerovanieVyystupu.html",

		"SVGPodpora.Transformácia.html",
		"SVGPodpora-Transformaacia.html",


		"Tlač.html",
		"Tlacc.html",

		"Tlac.html",
		"Tlac.html",

		"Tlačidlo.html",
		"Tlaccidlo.html",

		"Tlač.Slovo.html",
		"Tlacc-Slovo.html",

		"Tlač.Fragment.html",
		"Tlacc-Fragment.html",

		"Tlač.Parametre.html",
		"Tlacc-Parametre.html",

		"Tlač.RiadokSlov.html",
		"Tlacc-RiadokSlov.html",

		"Tlač.BlokSlov.html",
		"Tlacc-BlokSlov.html",

		"Tlacidlo.html",
		"Tlacidlo.html",


		"ÚdajeUdalostí.html",
		"UUdajeUdalostii.html",

		"Zoznam.ObrátenýIterátor.html",
		"Zoznam-ObraatenyyIteraator.html",


		"Archiv.html",
		"Archiv.html",

		"Bod.html",
		"Bod.html",

		"Castica.html",
		"Castica.html",

		"Farba.html",
		"Farba.html",

		"Farebnost.html",
		"Farebnost.html",

		"GRobot.html",
		"GRobot.html",

		"GRobot.Spojnica.html",
		"GRobot-Spojnica.html",

		"GRobotException.html",
		"GRobotException.html",

		"GRobotException.Dennik.html",
		"GRobotException-Dennik.html",

		"GRobotException.Chyba.html",
		"GRobotException-Chyba.html",

		"Klaves.html",
		"Klaves.html",

		"Konstanty.html",
		"Konstanty.html",

		"KontextovaPolozka.html",
		"KontextovaPolozka.html",

		"KontextovaPonuka.html",
		"KontextovaPonuka.html",

		"KreslenieTvaru.html",
		"KreslenieTvaru.html",

		"Oblast.html",
		"Oblast.html",

		"Obrazok.html",
		"Obrazok.html",

		"ObsluhaUdalosti.html",
		"ObsluhaUdalosti.html",

		"Pismo.html",
		"Pismo.html",

		"Platno.html",
		"Platno.html",

		"Plazma.html",
		"Plazma.html",

		"Poloha.html",
		"Poloha.html",

		"PolozkaPonuky.html",
		"PolozkaPonuky.html",

		"PoznamkovyBlok.html",
		"PoznamkovyBlok.html",

		"Priehladnost.html",
		"Priehladnost.html",

		"Rad.html",
		"Rad.html",

		"Roj.html",
		"Roj.html",

		"Roj.Bod.html",
		"Roj-Bod.html",

		"Roj.Smernik.html",
		"Roj-Smernik.html",

		"RolovaciaLista.html",
		"RolovaciaLista.html",

		"Rozmer.html",
		"Rozmer.html",

		"Rozmery.html",
		"Rozmery.html",

		"Schranka.html",
		"Schranka.html",

		"Skript.html",
		"Skript.html",

		"Skript.PremenneSkriptu.html",
		"Skript-PremenneSkriptu.html",

		"Smer.html",
		"Smer.html",

		"Spojenie.html",
		"Spojenie.html",

		"Subor.html",
		"Subor.html",

		"Svet.html",
		"Svet.html",

		"Svet.PrikazovyRiadok.html",
		"Svet-PrikazovyRiadok.html",

		"SVGPodpora.html",
		"SVGPodpora.html",

		"SVGPodpora.Transformacia.html",
		"SVGPodpora-Transformacia.html",

		"UdajeUdalosti.html",
		"UdajeUdalosti.html",

		"Uhol.html",
		"Uhol.html",

		"Vlnenie.html",
		"Vlnenie.html",

		"ZmenaCelejObrazovky.html",
		"ZmenaCelejObrazovky.html",

		"Zoznam.html",
		"Zoznam.html",

		"Zoznam.ObratenyIterator.html",
		"Zoznam-ObratenyIterator.html",

		"Zvuk.html",
		"Zvuk.html",
	};


	private String[] ďalšieSúboryBalíčka = {
		"Farebnost.html",
		"Farebnost.html",

		"GRobot.html",
		"GRobot.html",

		"Poloha.html",
		"Poloha.html",

		"Priehladnost.html",
		"Priehladnost.html",

		"Roj.html",
		"Roj.html",

		"Smer.html",
		"Smer.html",

		"Vlnenie.html",
		"Vlnenie.html",

		"ZmenaCelejObrazovky.html",
		"ZmenaCelejObrazovky.html",

		"Archiv.html",
		"Archiv.html",
	};

	private String[] opravNázvySúborovADiakritiku = {
		"Farebnos%C5%A5.html",
		"Farebnostt.html",

		"Prieh%C4%BEadnos%C5%A5.html",
		"Priehlladnostt.html",

		"Roj.Smern%C3%ADk.html",
		"Roj-Smerniik.html",

		"%C4%8Castica.html",
		"CCastica.html",

		"Kl%C3%A1ves.html",
		"Klaaves.html",

		"Kontextov%C3%A1Polo%C5%BEka.html",
		"KontextovaaPolozzka.html",

		"Kontextov%C3%A1Ponuka.html",
		"KontextovaaPonuka.html",

		"Oblas%C5%A5.html",
		"Oblastt.html",

		"Obr%C3%A1zok.html",
		"Obraazok.html",

		"ObsluhaUdalost%C3%AD.html",
		"ObsluhaUdalostii.html",

		"P%C3%ADsmo.html",
		"Piismo.html",

		"Pl%C3%A1tno.html",
		"Plaatno.html",

		"Polo%C5%BEkaPonuky.html",
		"PolozzkaPonuky.html",

		"RolovaciaLi%C5%A1ta.html",
		"RolovaciaLissta.html",

		"Pozn%C3%A1mkov%C3%BDBlok.html",
		"PoznaamkovyyBlok.html",

		"Schr%C3%A1nka.html",
		"Schraanka.html",

		"SpracovanieUdalost%C3%AD.html",
		"SpracovanieUdalostii.html",

		"S%C3%BAbor.html",
		"Suubor.html",


		"Tla%C4%8D.html",
		"Tlacc.html",

		"Tla%C4%8Didlo.html",
		"Tlaccidlo.html",

		"Tla%C4%8D.Slovo.html",
		"Tlacc-Slovo.html",

		"Tla%C4%8D.Fragment.html",
		"Tlacc-Fragment.html",

		"Tla%C4%8D.Parametre.html",
		"Tlacc-Parametre.html",

		"Tla%C4%8D.RiadokSlov.html",
		"Tlacc-RiadokSlov.html",

		"Tla%C4%8D.BlokSlov.html",
		"Tlacc-BlokSlov.html",


		"%C3%9AdajeUdalost%C3%AD.html",
		"UUdajeUdalostii.html",

		"Zoznam.Obr%C3%A1ten%C3%BDIter%C3%A1tor.html",
		"Zoznam-ObraatenyyIteraator.html",

		"SVGPodpora.Transform%C3%A1cia.html",
		"SVGPodpora-Transformaacia.html",

		"Arch%C3%ADv.html",
		"Archiiv.html",


		"GRobotException.Denn%C3%ADk.html",
		"GRobotException-Denniik.html",

		"Kon%C5%A1tanty.html",
		"Konsstanty.html",

		"Obr%C3%A1zok.VykonajVObr%C3%A1zku.html",
		"Obraazok-VykonajVObraazku.html",

		"Pl%C3%A1tno.Vn%C3%BAtorn%C3%A1Konzola.html",
		"Plaatno-VnuutornaaKonzola.html",

		"Pl%C3%A1tno.Z%C3%A1lohaKonzoly.html",
		"Plaatno-ZaalohaKonzoly.html",

		"Pozn%C3%A1mkov%C3%BDBlok.RolovaniePozn%C3%A1mkov%C3%A9hoBloku.html",
		"PoznaamkovyyBlok-RolovaniePoznaamkoveehoBloku.html",

		"Skript.Premenn%C3%A9Skriptu.html",
		"Skript-PremenneeSkriptu.html",

		"S%C3%BAbor.Sekcia.html",
		"Suubor-Sekcia.html",

		"Svet.Kl%C3%A1vesov%C3%A1Skratka.html",
		"Svet-KlaavesovaaSkratka.html",

		"Svet.Pr%C3%ADkazov%C3%BDRiadok.html",
		"Svet-PriikazovyyRiadok.html",

		"Svet.Pr%C3%ADkazov%C3%BDRiadok.PresmerovanieV%C3%BDstupu.html",
		"Svet-PriikazovyyRiadok-PresmerovanieVyystupu.html",


		/*"%C3%A1",
		"aa", // á

		"%C3%A4",
		"aa", // ä

		"%C3%A9",
		"ee", // é

		"%C3%AD",
		"ii", // í

		"%C3%B3",
		"oo", // ó

		"%C3%BA",
		"uu", // ú

		"%C3%BD",
		"yy", // ý


		"%C4%8D",
		"cc", // č

		"%C4%BE",
		"ll", // ľ

		"%C5%A1",
		"ss", // š

		"%C5%A5",
		"tt", // ť

		"%C5%BE",
		"zz", // ž

		"%C4%8C",
		"CC", // Č

		"%C3%9A",
		"UU", // Ú*/
	};

	private String[] nahraďVHTML = {
		// "<?xml version=\"1.0\" encoding=\"UTF-8\"?>",
		// "<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">",


		"\" \"http://www.w3.org/TR/html4/loose.dtd\">",
		"\">",

		"knižnica/",
		"",

		"-knižnica.",
		"-",

		".knižnica.",
		".",

		"#knižnica.",
		"#",

		">knižnica.",
		">",

		"&nbsp;knižnica.<",
		" <",

		"knižnica\">*</",
		"\"></",

		"\"knižnica.",
		"\"",

		"<div class=\"subTitle\">knižnica</div>",
		"<div class=\"subTitle\">Programovací rámec GRobot</div>",

		// "Hlavná trieda definujúca metódy s&nbsp;funkciami kresliaceho robota.<h2>",
		"<p>Ústredná trieda definujúca všetky metódy grafického robota (ktoré reprezentujú jeho funkcionalitu) a viaceré konštanty, ktoré sú pri práci s programovacím rámcom s výhodou využiteľné.</p><h2>",
		"<h2>",

		"Serialized Form",
		"<small>Serializovateľnosť</small>",

		"<title><small>Serializovateľnosť</small></title>",
		"<title>Serializovateľnosť</title>",

		"parent.document.title=\"<small>Serializovateľnosť</small>\";",
		"parent.document.title=\"Serializovateľnosť\";",

		"<h1 title=\"<small>Serializovateľnosť</small>\" class=\"title\"><small>Serializovateľnosť</small></h1>",
		"<h1 title=\"Serializovateľnosť\" class=\"title\">Serializovateľnosť</h1>",

		"Serialized Fields",
		"Serializované atribúty",

		// "Generated Documentation (Untitled)",
		// "Dokumentácia programovacieho rámca GRobot",

		// "All classes and interfaces",
		// "Všetky triedy a rozhrania",

		// "except non-static nested types",
		// "okrem nestatických vnorených typov",

		// "Package, class and interface descriptions",
		// "Opisy balíčkov, tried a rozhraní",

		// "Frame Alert",
		// "Upozornenie k rámom",

		// "This document is designed to be viewed using the frames feature. If you see this message, you are using a non-frame-capable web client.",
		// "Tento dokument bol navrhnutý na prezeranie pomocou rámov. Ak vidíte túto správu, váš webový prehliadač nie je schopný interpretovať rámy.",

		// "Non-frame version.",
		// "verziu dokumentu bez rámov.",

		"All Implemented Interfaces:",
		"Všetky implementované rozhrania:",

		"All Known Implementing Classes:",
		"Všetky známe implementujúce triedy:",

		"Direct Known Subclasses:",
		"Známe priame odvodené triedy:",

		"All Known Subinterfaces:",
		"Všetky známe odvodené rozhrania:",

		"All Superinterfaces:",
		"Všetky nadradené rozhrania:",

		"class or interface in ",
		"trieda alebo rozhranie v ",

		// "trieda alebo rozhranie v knižnica",
		// "trieda alebo rozhranie v programovacom rámci GRobot",

		"Enclosing class:",
		"Nadradená trieda:",

		"Nested Class Summary table, listing nested classes, and an explanation",
		"Tabuľka prehľadu vnorených tried, výpis vnorených tried a ich stručný opis",

		"Nested Class Summary",
		"Prehľad vnorených tried",

		"Interface Summary table, listing interfaces, and an explanation",
		"Tabuľka prehľadu rozhraní, výpis rozhraní a ich stručný opis",

		"Interface Summary",
		"Prehľad rozhraní",

		"Class Summary table, listing classes, and an explanation",
		"Tabuľka prehľadu tried, výpis tried a ich stručný opis",

		"Class Summary",
		"Prehľad tried",

		"Nested classes/interfaces inherited from class",
		"Vnorené triedy/rozhrania odvodené od triedy",

		"Constant Field Values",
		"Hodnoty konštánt",

		"Field Summary table, listing fields, and an explanation",
		"Tabuľka prehľadu atribútov, výpis atribútov a ich stručný opis",

		"Field Summary",
		"Prehľad atribútov",

		"Fields inherited from class",
		"Atribúty zdedené z triedy",

		"Fields inherited from interface",
		"Atribúty zdedené z rozhrania",

		"Constructor Summary table, listing constructors, and an explanation",
		"Tabuľka prehľadu konštruktorov, výpis konštruktorov a ich stručný opis",

		"Constructor Summary",
		"Prehľad konštruktorov",

		"Method Summary table, listing methods, and an explanation",
		"Tabuľka prehľadu metód, výpis metód a ich stručný opis",

		"Method Summary",
		"Prehľad metód",

		"Methods inherited from class",
		"Metódy zdedené z triedy",

		"Methods inherited from interface",
		"Metódy zdedené z rozhrania",

		"Field Detail",
		"Podrobnosti atribútov",

		"Constructor Detail",
		"Podrobnosti konštruktorov",

		"Method Detail",
		"Podrobnosti metód",

		// "All&nbsp;Classes",
		// "Všetky triedy",

		// "All Classes",
		// "Všetky triedy",

		"All Methods",
		"Všetky metódy",

		"Static Methods",
		"Statické metódy",

		"Instance Methods",
		"Inštančné metódy",

		"Concrete Methods",
		"Skutočne definované metódy",

		"Deprecated Methods",
		"Zastarané metódy",

		"Exception Summary table, listing exceptions, and an explanation",
		"Tabuľka prehľadu výnimiek, výpis výnimiek a ich stručný opis",

		"Exception Summary",
		"Prehľad výnimiek",

		"<div>JavaScript is disabled on your browser.</div>",
		"<div>Vo Vašom prehliadači je vypnutý JavaScript.</div>",

		// "@deprecated",
		// "<span style=\"color:red\">zastarané</span>",

		// "<b>Deprecated.</b>",
		// "<b style=\"color:red\">Zastarané!</b>",

		"<td class=\"colLast\">Deprecated</td>",
		"<td class=\"colLast\"><div class=\"block\">Zastarané.</div></td>",

		"<span class=\"deprecatedLabel\">Deprecated.</span>",
		"<span class=\"deprecatedLabel\">Zastarané.</span>",

		"Skip navigation links",
		"Preskočiť navigáciu",

		// "&nbsp;Prev Class",
		// "Predchádzajúca trieda<!-- *1 -->",

		"Prev&nbsp;Class",
		"Predchádzajúca trieda",

		"Next&nbsp;Class",
		"Ďalšia trieda",

		"Prev&nbsp;Package",
		"Predchádzajúci balíček",

		"Next&nbsp;Package",
		"Ďalší balíček",

		// "&nbsp;Prev Package",
		// "Predchádzajúci balíček<!-- *1 -->",

		// "Next Package",
		// "Ďalší balíček",

		// "&nbsp;Prev",
		// "Predchádzajúce<!-- *1 -->",

		"Next",
		"Ďalšie",

		// "NO FRAMES",
		// "Bez rámov",

		// "FRAMES",
		// "Rámy",

		"Summary:",
		"Prehľady:",


		"Modifier and Type",
		"Modifikátor a typ",

		"Class and Description",
		"Trieda a opis",

		"Field and Description",
		"Atribút a opis",

		"Constructor and Description",
		"Konštruktor a opis",

		"Method and Description",
		"Metóda a opis",

		" scope=\"col\">Exception<",
		" scope=\"col\">Výnimka<",

		"Nested Classes",
		"Vnorené triedy",

		"Nested",
		"Vnorené triedy",

		"Fields",
		"Atribúty",

		"TextField",
		"Text++fld++",

		"Field",
		"Atribúty",

		"++fld++",
		"Field",

		"Constructors",
		"Konštruktory",

		"Constr",
		"Konštruktory",

		"<li><a href=\"#method.summary\">Method</a></li>",
		"<li><a href=\"#method.summary\">Metódy</a></li>",

		"<li><a href=\"#method.detail\">Method</a></li>",
		"<li><a href=\"#method.detail\">Metódy</a></li>",

		"Methods",
		"Metódy",

		"CycleMethod",
		"Cycle++mtd++",

		"InputMethod",
		"Input++mtd++",

		"Method",
		"Metóda",

		"++mtd++",
		"Method",


		"Detail:",
		"Podrobnosti:",

		"Specified by:",
		"Definované:",

		"Parameters:",
		"Parametre:",

		"Returns:",
		"Návratová hodnota:",

		// "Since:",
		// "Dostupnosť:",

		"in interface",
		"v rozhraní",

		// "extended by ",
		// "odvodené od ",

		"type parameter in ",
		"typový parameter v ",

		// "typový parameter v knižnica",
		// "typový parameter v programovacom rámci GRobot",

		"interface in ",
		"rozhranie v ",

		"rozhranie v knižnica",
		"rozhranie v programovacom rámci GRobot",

		"class in ",
		"trieda v ",

		"trieda v knižnica",
		"trieda v programovacom rámci GRobot",

		// "&lt;Unnamed&gt;",
		// // "«nepomenované»",
		// "GRobot",

		"See Also:",
		"Pozri tiež:",

		// "Link to",
		// "Odkaz na ",

		"Version:",
		"Verzia:",

		"Author:",
		"Autor:",

		"Throws:",
		"Výnimky:",

		"Package",
		"Balíček",

		"getClass",
		"get++cls++",

		"getUIClass",
		"getUI++cls++",

		"lang.Class",
		"lang.++cls++",

		">Class<",
		">++cls++<",

		"Class.html",
		"++cls++.html",

		"Classes",
		"Triedy",

		"Class",
		"Trieda",

		"++cls++",
		"Class",

		"scope=\"col\">Class",
		"scope=\"col\">Trieda",

		"Interface",
		"Rozhranie",

		"Description",
		"Opis",

		"Contents",
		"Obsah",

		" - ",
		" – ",

		"<code>note",
		"<code class=\"note\">",

		"<code>type",
		"<code class=\"type\">",

		"<code>kwd",
		"<code class=\"keyword\">",

		"<code>val",
		"<code class=\"value\">",

		"<code>num",
		"<code class=\"number\">",

		"command",
		"++cmnd++",

		"<code>comm",
		"<code class=\"comment\">",

		"&amp;#",
		"&#",

		"++cmnd++",
		"command",

		"<code>curr",
		"<code class=\"current\" title=\"aktuálny prvok – tento príklad " +
		"sa vzťahuje na túto položku\">",

		"title=\"Navigation\"",
		"title=\"Navigácia\"",

		">Use<",
		">(Použitie)<",

		// "strop",
		// "++strp++",

		// "stred",
		// "++strd++",

		// "stroke",
		// "++strk++",

		"<code>srg",
		"<code class=\"string\">",

		// "++strp++",
		// "strop",

		// "++strd++",
		// "stred",

		// "++strk++",
		// "stroke",

		"<p><image>",
		"<p class=\"image\"><img src=\"resources/",

		"<td><image>",
		"<td class=\"image\"><img src=\"resources/",

		"<image>",
		"<img src=\"resources/", // <img src="resources/

		"<pre CLASS",
		"<pre class",

		"<div CLASS",
		"<div class",

		"<onerror>",
		"\" onerror=\"this.onerror=null; this.src='resources/",

		"</onerror>",
		"';",

		"</image>",
		"\" /><br /><span class=\"imageSpace\"> </span><br />", // " /><br /><span class="imageSpace"> </span><br />

		"\" /><br /><span class=\"imageSpace\"> </span><br /></",
		"\" /></",

		"<alt/>",
		"\" alt=\"", // " alt="

		"<a class=\"getter\"></a>",
		"<a href=\"GRobot.html#getter.setter.alias\"><img src=\"resources/" +
		"getter.png\" alt=\"getter – pozri informácie v úvode triedy " +
		"GRobot – časť Metódy a konštruktory\" title=\"getter – pozri " +
		"informácie v úvode triedy GRobot – časť Metódy a " +
		"konštruktory\" /></a>",

		"<a class=\"setter\"></a>",
		"<a href=\"GRobot.html#getter.setter.alias\"><img src=\"resources/" +
		"setter.png\" alt=\"setter – pozri informácie v úvode triedy " +
		"GRobot – časť Metódy a konštruktory\" title=\"setter – pozri " +
		"informácie v úvode triedy GRobot – časť Metódy a " +
		"konštruktory\" /></a>",

		"<a class=\"alias\"></a>",
		"<a href=\"GRobot.html#getter.setter.alias\"><img src=\"resources/" +
		"alias.png\" alt=\"alias – pozri informácie v úvode triedy " +
		"GRobot – časť Metódy a konštruktory\" title=\"alias – pozri " +
		"informácie v úvode triedy GRobot – časť Metódy a " +
		"konštruktory\" /></a>",

		// "<dt><strong>Overrides:</strong><dd><code>",
		// "<dt><strong>Prekrývanie:</strong><dd>táto metóda prekrýva " +
		// "originálnu metódu <code>",

		"<dt><span class=\"overrideSpecifyLabel\">Overrides:</span></dt>",
		"<dt><span class=\"overrideSpecifyLabel\">Prekrývanie:</span></dt>\f<!-- Overrides: -->",

		"<!-- Overrides: --><dd><code>",
		"<dd>táto metóda prekrýva originálnu metódu <code>",

		"</code>&nbsp;in class&nbsp;<code>",
		"</code> v triede <code>",

		"href=\"https://docs.ORACLE.com",
		"target=\"_blank\" href=\"https://docs.oracle.com",

		/*
		"public&nbsp;",
		"<span class=\"keyword\">public</span>&nbsp;",

		"protected&nbsp;",
		"<span class=\"keyword\">protected</span>&nbsp;",

		"static&nbsp;",
		"<span class=\"keyword\">static</span>&nbsp;",


		"&nbsp;interface",
		"&nbsp;<span class=\"type\">interface</span>",

		"&nbsp;void",
		"&nbsp;<span class=\"type\">void</span>",

		"&nbsp;int",
		"&nbsp;<span class=\"type\">int</span>",

		"&nbsp;long",
		"&nbsp;<span class=\"type\">long</span>",

		"&nbsp;double",
		"&nbsp;<span class=\"type\">double</span>",

		"&nbsp;float",
		"&nbsp;<span class=\"type\">float</span>",

		"&nbsp;char",
		"&nbsp;<span class=\"type\">char</span>",

		"&nbsp;boolean",
		"&nbsp;<span class=\"type\">boolean</span>",

		"&nbsp;class",
		"&nbsp;<span class=\"type\">class</span>",

		"&nbsp;extends",
		"&nbsp;<span class=\"type\">extends</span>",

		"&nbsp;implements",
		"&nbsp;<span class=\"type\">implements</span>",


		"extends&nbsp;",
		"<span class=\"type\">extends</span>&nbsp;",

		"implements&nbsp;",
		"<span class=\"type\">implements</span>&nbsp;",


		"public ",
		"<span class=\"keyword\">public</span> ",

		"protected ",
		"<span class=\"keyword\">protected</span> ",

		"static ",
		"<span class=\"keyword\">static</span> ",

		"final ",
		"<span class=\"keyword\">final</span> ",


		" interface",
		" <span class=\"type\">interface</span>",

		" void",
		" <span class=\"type\">void</span>",

		" int",
		" <span class=\"type\">int</span>",

		" long",
		" <span class=\"type\">long</span>",

		" double",
		" <span class=\"type\">double</span>",

		" float",
		" <span class=\"type\">float</span>",

		" char",
		" <span class=\"type\">char</span>",

		" boolean",
		" <span class=\"type\">boolean</span>",

		" class",
		" <span class=\"type\">class</span>",


		" extends",
		" <span class=\"type\">extends</span>",

		" implements",
		" <span class=\"type\">implements</span>",


		"extends ",
		"<span class=\"type\">extends</span> ",

		"implements ",
		"<span class=\"type\">implements</span> ",
		*/


		// "table border=\"1\"",
		// "table class=\"summary\"",

		// " bgcolor=\"#FFFFFF\"",
		// "",

		// " bgcolor=\"#EEEEFF\"",
		// "",

		// " bgcolor=\"#CCCCFF\"",
		// "",

		// " bgcolor=\"white\"",
		// "",

		// "\t",
		// "    ",

		"<li><a href=\"index.html\">Balíček</a></li>",
		"<li><a href=\"index.html\">Úvod</a></li>\r\n<li><a href=\"kategorie-metod.html#$TRIEDA\">Kategórie metód</a></li>\r\n<li>  |  </li>\r\n<!-- $PREDĎALŠ -->",

		// "<frame src=\"Farebnost.html\"",
		// "<frame src=\"GRobot.html\"",

		"  allTriedyLink = document.getElementById(\"allclasses_navbar_top\");",
		"/*",

		"  allTriedyLink = document.getElementById(\"allclasses_navbar_bottom\");",
		"/*",

		"    allTriedyLink.style.display = \"none\";",
		"*/\r\n  {",

		"<ul class=\"navList\" id=\"allclasses_navbar_top\">",
		"<ul class=\"navList\" style=\"display: none\">",

		"<ul class=\"navList\" id=\"allclasses_navbar_bottom\">",
		"<ul class=\"navList\" style=\"display: none\">",


		// Tieto pravidlá sú archivované, pretože ich originálne informácie
		// (search strings) boli presunuté na úvodnú stránku:
		// 
		// // "<dd>" + Konštanty.majorVersion + "." +
		// // Integer.toString(Konštanty.minorVersion + 100).substring(1) +
		// // Konštanty.versionNote + ", " + Konštanty.yearsMonths + "</dd>",
		// "<dd>CURRENT_VERSION</dd>",
		// "<dd class=\"O-publikacii\">" + Konštanty.majorVersion + "." +
		// Integer.toString(Konštanty.minorVersion + 100).substring(1) +
		// Konštanty.versionNote + ", " + Konštanty.yearsMonths +
		// "</dd>",
		// 
		// // "<dd>" + Konštanty.mainDeveloperTitled + "</dd>",
		// "<dd>MAIN_DEVELOPER_TITLED</dd>",
		// "<dd class=\"O-publikacii\">" + Konštanty.mainDeveloperTitled + "</dd>",
	};

	private String[] pridajDoPackage = {
		"<!-- Generated by javadoc -->",

		"<title>Dokumentácia programovacieho rámca GRobot</title>",
		"<style> h1.main-title { text-align: center; font-size: 200%; margin-bottom: 30px; } .topNav, .subNav, .bottomNav { display: none; } </style>",
	};


	private final static String recenzenti = "«zatiaľ neurčené»";
		// "prof. Ing. Veronika Stoffová, CSc., Ing. Jana Jurinová, PhD.";
	private final static String ISBN = "???-??-????-???-?";
	private final static String rokVydania = "2021 (?)";
	private final static String vydanie = "tretie, prepracované";
	private final static String vydavateľ = "Pedagogická fakulta Trnavskej univerzity v Trnave";


	private String[] nahraďVPackage = {
		// "<li><a href=\"index.html\">Package</a></li>",
		// "<!-- Rekurzívny odkaz vymazaný -->",

		"<title>knižnica</title>",
		"",

		"<li>Class</li>",
		"",

		// "<h1 title=\"Package\" class=\"title\">Package&nbsp;&lt;Unnamed&gt;</h1>",
		"<h1 title=\"Package\" class=\"title\">Package&nbsp;knižnica</h1>",
		"<!-- h1 title=\"Balíček\" class=\"title\">Balíček programovacieho rámca GRobot</h1 -->\r\n<h1 class=\"main-title\">Dokumentácia programovacieho rámca GRobot</h1>\r\n\r\n" +
		"<p>Tento materiál bol vytvorený generovaním a úpravou <a href=\"https://docs.oracle.com/javase/8/docs/technotes/guides/javadoc/index.html\" target=\"_blank\">Javadoc</a> dokumentácie k programovaciemu rámcu GRobot. Tento rámec bol vytvorený na účely vyučovania predmetov programovania s použitím programovacieho jazyka Java.</p>\r\n\r\n" +
		"<table class=\"authors\">\r\n\r\n" +
		"<tr><th>Autor:</th><td>" + Konštanty.mainDeveloperTitled +
			"</td></tr>\r\n\r\n" +
		"<!--tr><th>Recenzenti:</th><td>" + recenzenti + "</td></tr>\r\n\r\n" +
		"<tr><th>ISBN:</th><td>" + ISBN + "</td></tr>\r\n\r\n" +
		"<tr><th>Vydavateľ:</th><td>" + vydavateľ + "</td></tr>\r\n\r\n" +
		"<tr><th>Rok:</th><td>" + rokVydania + "</td></tr>\r\n\r\n" +
		"<tr><th>Vydanie:</th><td>" + vydanie + "</td></tr-->\r\n\r\n" +
		"<tr><th>Pre verziu:</th><td>" + Konštanty.majorVersion + "." +
			Integer.toString(Konštanty.minorVersion + 100).substring(1) +
			" " + Konštanty.versionNote + "</td></tr>\r\n\r\n" +
		"<tr><th>Rozpätie vývoja:</th><td>" + Konštanty.yearsMonths +
			"</td></tr>\r\n\r\n" +
		"</table>\r\n\r\n" +
		// 
		"<p> </p><h2>Úvodné slovo</h2>\r\n\r\n" +
		//   ------------
		"<p>Programovací rámec GRobot je v skutočnosti nástrojom, ktorý okrem vyučovania programovania našiel využitie aj pri tvorbe viacerých súkromných projektov jeho autora. V úvode by sme sa radi pozastavili nad zmyslom tvorby frameworkov. Podobne ako vyššie programovacie jazyky uzatvárajú často používané funkcie nižších programovacích jazykov do ľahšie použiteľných celkov, poskytujú frameworky možnosť jednoduchšej a rýchlejšej tvorby aplikácií určitého charakteru. Analýzou frameworkov môžeme odhaliť, koľko úsilia by programátori museli vynaložiť, keby ich nemali k dispozícii.</p>\r\n\r\n" +
		"<p>Jeden reprezentačný príklad z tohto rámca ukazuje metóda <code><a href=\"GRobot.html#choďNaPoOblúku-double-double-\">choďNaPoOblúku</a></code>, ktorá funguje tak, že presunie robota na zadané súradnice „kráčajúc“ po kružnicovom oblúku. Úlohou autora tejto metódy bolo vyriešiť výpočet dráhy robota zo zdrojovej polohy do cieľovej tak, aby sa robot pohyboval po kružnicovom oblúku a aby sa dotyčnica ku kružnici v počiatočnom bode pohybu zhodovala so smerom robota v tomto bode.</p>\r\n\r\n" +
		"<p>To je dostatok parametrov na vyriešenie úlohy. Najskôr sa vypočíta polomer oblúka podľa aktuálneho smeru robota (čím je uhol medzi novými súradnicami a aktuálnym pootočením robota väčší, tým väčší bude polomer oblúka; jediný smer, ktorý v tejto súvislosti nedáva zmysel, je presne opačný smer od zadaných súradníc – doslovne vzaté z výpočtov vyplýva, že by robot mal prejsť nekonečnom; metóda to rieši vznikom <a href=\"GRobotException.html\">výnimky</a>). Potom sa určí stred kružnice, čím je úloha prakticky vyriešená. Ak je spustený <a href=\"GRobot.html#začniCestu--\">záznam cesty</a>, tak je vygenerovaný oblúk pridaný aj do cesty.</p>\r\n\r\n" +
		"<p>Na prvý pohľad by sa mohlo zdať, že je to jednoduchá úloha, lenže riešenie bolo potrebné prispôsobiť tak, aby metóda mohla využiť triedu Javy <code><a href=\"https://docs.oracle.com/javase/8/docs/api/java/awt/geom/Arc2D.Double.html?is-external=true\" target=\"_blank\">Arc2D.Double</a></code>, ktorá potrebuje zadanie ohraničujúceho obdĺžnika elipsy a počiatočný a koncový uhol elipsy. Z vlastnej skúsenosti môžem povedať, že človek, ktorý nepoužíva geometriu a geometrické myslenie každý deň, potrebuje na vyriešení tohto problému intenzívne popracovať asi týždeň.</p>\r\n\r\n" +
		"<p>Takéto situácie nie sú pri programovaní ojedinelé. Predstavte si, že by každý programátor musel namiesto použitia pripraveného príkazu (čo je otázka niekoľkých sekúnd potrebných na napísanie príkazu) stráviť týždeň riešením nejakého problému z matematiky, fyziky alebo inej oblasti. Cieľom frameworkov s vopred vyriešenými problémami je takýmto situáciám brániť.</p>\r\n\r\n" +
		"<p> </p>\r\n\r\n" +
		"<p>Toto je tretie vydanie dokumentácie. Predchádzajúce verzie sú umiestnené tu <a href=\"http://cec.truni.sk/horvath/Robot/\" target=\"_blank\">http://cec.truni.sk/horvath/Robot/</a> a tu <a href=\"http://cec.truni.sk/horvath/GRobot/\" target=\"_blank\">http://cec.truni.sk/horvath/GRobot/</a> (kde boli priebežne aktualizované). Ďalšie podrobnosti o tejto dokumentácii aj o grafickom robotovi nájdete v úvodných pasážach najrozsiahlejšej z „kapitol“ (stránok) tohto materiálu – <a href=\"GRobot.html\">GRobot</a>. Tam sú zároveň podrobnejšie vysvetlené dôvody použitia nasledujúcich symbolov:</p>\r\n\r\n" +
		"<table><tr>\r\n\r\n" +
		"<td rowspan=\"3\"> </td>\r\n\r\n" +
		"<td><a href=\"GRobot.html#getter.setter.alias\"><img src=\"resources/getter.png\" alt=\"getter – pozri informácie v úvode triedy GRobot – časť Metódy a konštruktory\" title=\"getter – pozri informácie v úvode triedy GRobot – časť Metódy a konštruktory\" /></a></td><td>–</td><td>getter (z anglického „get“ – vziať/dostať) číta hodnotu vlastnosti</td>\r\n\r\n" +
		"</tr><tr>\r\n\r\n" +
		"<td><a href=\"GRobot.html#getter.setter.alias\"><img src=\"resources/setter.png\" alt=\"setter – pozri informácie v úvode triedy GRobot – časť Metódy a konštruktory\" title=\"setter – pozri informácie v úvode triedy GRobot – časť Metódy a konštruktory\" /></a></td><td>–</td><td>setter (z anglického „set“ – nastaviť) zapisuje hodnotu vlastnosti</td>\r\n\r\n" +
		"</tr><tr>\r\n\r\n" +
		"<td><a href=\"GRobot.html#getter.setter.alias\"><img src=\"resources/alias.png\" alt=\"alias – pozri informácie v úvode triedy GRobot – časť Metódy a konštruktory\" title=\"alias – pozri informácie v úvode triedy GRobot – časť Metódy a konštruktory\" /></a></td><td>–</td><td>alias – prezývka triedy, rozhrania, metódy alebo atribútu</td>\r\n\r\n" +
		"</tr></table>\r\n\r\n" +
		"<p>Zoznamy, ktoré sú publikované nižšie, slúžia na vstup do jednotlivých „kapitol“ (stránok) materiálu, pričom každá z nich je v skutočnosti z veľkej časti dokumentáciou konkrétnej triedy alebo rozhrania rámca. Dokumentácia je, samozrejme, obohatená o rôzne komentáre, príklady, výsledky.</p>\r\n\r\n" +
		"<p>Na túto stránku sa môžete vrátiť kliknutím na odkaz <a href=\"index.html\">Úvod</a>, ktorý je permanentne umiestnený v hornej časti každej „kapitoly“ (stránky) tohto materiálu. Pri uvedenom odkaze zároveň nájdete odkaz na stránku <a href=\"kategorie-metod.html\">Kategórie metód</a>, ktorá zhŕňa relevantné metódy tried rámca do jedného spoločného priestoru rozdeleného podľa významových kategórií, napríklad pre samotného robota to sú: <a href=\"kategorie-metod.html#GRobot-Základný-pohyb\">Základný pohyb robota</a>, <a href=\"kategorie-metod.html#GRobot-Pero\">Vlastnosti pera (čiary)</a>, <a href=\"kategorie-metod.html#GRobot-Domov\">Domovská pozícia a domovské stavy</a> a podobne.</p>" +
		// 
		"<h2>Používanie diakritiky</h2>\r\n\r\n" +
		//   ---------------------
		"<p>Pri programovaní sa z historických dôvodov vždy tolerovalo nepoužívanie diakritiky. Java, Python a iné programovacie jazyky však v súčasnosti už prekonali bariéru nemožnosti používania diakritiky, takže je otázkou, prečo by sme sa stále mali jej používaniu brániť?</p>\r\n\r\n" +
		"<p>Možno si neuvedomujete, aká je diakritika v slovenčine dôležitá. Prečítajte nasledujúce slová bez diakritiky: bábka, krík, látka, pračka, koža, posuň, kópia, puška, plát, tvár, brať, zoznám, kočka, moč, Maťka, Máška. Hneď si uvedomíte rozdiel. Skúste bez pomoci vydedukovať, čo robí nasledujúca metóda: <code>vypisNaBode</code>? Je to <code>vypíšNaBode</code> (zápis) alebo <code>výpisNaBode</code> (čítanie)? Správna odpoveď v rámci tohto programovacieho rámca je práve tá druhá. Ďalší príklad: Z nasledujúcich štyroch variantov slov majú tri unikátny význam a jeden je obmenou jedného z trojice (ak ignorujeme homonymický význam): cela, celá, čela, čelá.</p>\r\n\r\n" +
		"<p>V záujme ústretovosti sme sa v tomto rámci pokúsili definovať aliasy všetkých tried, atribútov a metód aj bez diakritiky, no ich používanie neodporúčame. Niekedy nastávajú problémy s kompatibilitou. Niekedy dokonca definovanie kompatibilného aliasu nebolo možné, pretože k návratovej hodnote objektu s diakritikou (napr. <code>Obrázok</code>) bola prisúdená metóda, ktorá žiadnu diakritiku neobsahovala (<code>ikona()</code>), takže k objektu bez diakritiky (<code>Obrazok</code>) nemohla byť definovaná rovnomenná metóda (je to riešené bezdiakritickým „aliasom“ <code>obrazok()</code>).</p>\r\n\r\n" +
		"<p>O aliasoch a diakritike sa píše aj v spomínaných úvodných pasážach opisu triedy <a href=\"GRobot.html\">GRobot</a>.</p>\r\n\r\n" +
		"<h2></h2><p><small>(Od verzie 1.0 vyššie je pravidelne aktualizovaný <a href=\"zoznam-zmien.html\">zoznam zmien</a>.)</small></p>\r\n\r\n",

		// Varovanie‼ JavaDoc nechce vygenerovať záznamy o Bode a Smerníku‼
		// Všetky zmeny v prvej vete ich dokumentácie treba ručne preniesť
		// sem:

		"trojrozmernom priestore.</div>",
		"trojrozmernom priestore.<!-- modified due to javadoc error --></div>\r\n" +
		"</td>\r\n" +
		"</tr>\r\n" +
		"<tr class=\"altColor\">\r\n" +
		"<td class=\"colFirst\"><a href=\"Roj-Bod.html\" title=\"trieda v programovacom rámci GRobot\">Roj.Bod</a></td>\r\n" +
		"<td class=\"colLast\">\r\n" +
		"<div class=\"block\">Trieda uchovávajúca údaje o jednom bode roja.</div>\r\n" +
		"</td>\r\n" +
		"</tr>\r\n" +
		"<tr class=\"rowColor\">\r\n" +
		"<td class=\"colFirst\"><a href=\"Roj-Smerniik.html\" title=\"trieda v programovacom rámci GRobot\">Roj.Smerník</a></td>\r\n" +
		"<td class=\"colLast\">\r\n" +
		"<div class=\"block\">Pomocná trieda na riadené vkladanie bodov do roja.</div>",
	};


	// private String[] pridajDoCSS = {
		// 	// Prvý riadok je „identifikátor“ určujúci, kam má byt CSS pridané…
		// 	"/* Table colors */",
		// 
		// 	// Pridávané CSS:
		// 	"/* Information like “Parameters”, “See Also”, etc. */",
		// 	"dt { margin-top: 12px; }",
		// 	"",
		// 	"/* Preformated code */",
		// 	"code, pre { font-size: 125% }",
		// 	"pre code { font-size: 100% }",
		// 	"",
		// 	"/* Links */",
		// 	"a { color: #008D00 }",
		// 	"a:visited { color: #005000 }",
		// 	"a:hover, a:active { color: #8B8B00 }",
		// 	"",
		// 	"/* Horizontal line */",
		// 	"hr { border: 1px solid #DEDEDE; }",
		// 	"",
		// 	"/* Summary tables */",
		// 	"table.overviewSummary { border-collapse: collapse; }",
		// 	"table.overviewSummary, table.overviewSummary tr td, table.overviewSummary tr th { border: 2px solid #EEEEEE }",
		// 	"table.overviewSummary tr td, table.overviewSummary tr th { padding: 6px 12px; }",
		// 	"table.overviewSummary tr td { background-color: #ffffff; }",
		// 	"table.overviewSummary tr th { background-color: #FFEEE8; text-align: left; }",
		// 	"",
		// 	"/* References table */",
		// 	"table.references tr td { vertical-align: top; }",
		// 	"",
		// 	"/* Centered table */",
		// 	"table.centered { margin: 8px auto; }",
		// 	"table.centered tr td { padding: 0px 8px; }",
		// 	"",
		// 	"/* Syntax */",
		// 	"code.type { color: #FF0000; }",
		// 	"code.value { color: #8888FF; }",
		// 	"code.number { color: #008888; }",
		// 	"code.keyword { color: #880000; }",
		// 	"code.comment { color: #999999; }",
		// 	"code.string { color: #0000EE; }",
		// 	"code.current { color: #778877; cursor: no-drop; /*color: #884488;*/ }",
		// 	"code.note { font-style: italic; color: #EE44EE; font-family: sans-serif; font-size: 80%; }",
		// 	"span.note { font-style: italic; color: #EE44EE; font-family: sans-serif; }",
		// 	"pre.example { border: 1px dotted #DEDEDE; background-color: white; margin: 6px 40px; padding: 8px 24px; }",
		// 	"pre.example a { color: #558855; text-decoration: none; }",
		// 	"pre.example a:visited { color: #336633; text-decoration: none; }",
		// 	"pre.example a:hover, pre a:active { color: #8B8B6A; text-decoration: underline; }",
		// 	"div.red_block { background-color: #FFEEEE; padding: 8px 0px; border: 1px dotted #D0D0D0; }",
		// 	//"span.type { color: #FF0000; }",
		// 	//"span.keyword { color: #880000; }",
		// 	"",
		// 	"/* Images */",
		// 	"p.image { margin: 6px auto; text-align: center; font-size: 80%; }",
		// 	"",
		// 	"/* Navigation lists */",
		// 	"div.subNav ul { float: left; }",
		// 	"div.header { clear: both; padding-top: 24px; }",
		// 	"div.header h2 { margin-top: 0px; padding-top: 14px; border-top: 2px solid #dddddd; }",
		// 	"",
		// 	"ul.navList, ul.subNavList { border: 1px solid #eeeeee; background-color: #ffffff; margin: 0px; padding: 3px 6px; }",
		// 	"ul.navList li, ul.subNavList li { display: inline; font-size: 80%; }",
		// 	"ul.subNavList li:first-child { font-weight: bold }",
		// 	"",
		// 	"table caption { display: none; }",
		// 	"ul.inheritance { list-style: none; }",
		// 	"ul.inheritance ul.inheritance li:first-child { list-style: url(resources/inherit.gif); padding-top: 8px; }",
		// 	"ul.blocklist { list-style: none; margin: 0px; padding-left: 0px; }",
		// 	"div.block { padding-left: 12px; }",
		// 	"",
		// 	".strong { font-weight: bold; }",
		// 	"h3 { background-color: #FFEEDD; padding: 6px 12px; border: 2px solid #eeeeee; }",
		// 	"h4 { padding-top: 12px; border-top: 2px solid #eeeeee; }",
		// 	"",
		// 	"",
		// };
		// 
		// private String[] nahraďVCSS = {
		// 	"body { background-color: #FFFFFF; color:#000000 }",
		// 	"body { background-color: #FFFDFA; background-image: url(resources/texturaMramor1.png); color: #000000; font-family: Verdana, Arial, Helvetica, sans-serif }",
		// 
		// 	"h1 { font-size: 145% }",
		// 	"h1 { font-size: 100% }",
		// 
		// 	".TableHeadingColor     { background: #CCCCFF; color:#000000 } /* Dark mauve */",
		// 	".TableHeadingColor     { background: #FFEEDD; color: #000000 } /* Darker creme */",
		// 
		// 	".TableSubHeadingColor  { background: #EEEEFF; color:#000000 } /* Light mauve */",
		// 	".TableSubHeadingColor  { background: #FFEEE8; color: #000000 } /* Light creme */",
		// 
		// 	".NavBarCell1    { background-color:#EEEEFF; color:#000000} /* Light mauve */",
		// 	".NavBarCell1    { background-color: #FFEEE8; color: #000000 } /* Light creme */",
		// 	// ".NavBarCell1    { } /* No background; Default font color */",
		// 
		// 	".NavBarCell1Rev { background-color:#00008B; color:#FFFFFF} /* Dark Blue */",
		// 	// ".NavBarCell1Rev { background-color:#8B8B00; color:#FFFFFF} /* Yellow */",
		// 	".NavBarCell1Rev { } /* No background; Default font color */",
	// };

	private class Paths
	{
		private final String javadocPath;
		private final String classPath;

		private Paths(String javadocPath, String classPath)
		{
			this.javadocPath = javadocPath;
			this.classPath = classPath;
		}
	};

	private final String javadocPath;
	private final String classPath;

	private final java.util.HashMap<String, Paths> pathList =
		new java.util.HashMap<String, Paths>();
	{
		/*
		pathList.put("Windows 7", new Paths(
			"c:\\Program Files\\Java\\jdk1.6.0_35\\bin\\",
			"" // "-classpath \"C:\\Program Files\\BlueJ\\lib\\bluejcore.jar;C:\\Program Files\\BlueJ\\lib\\junit-4.8.2.jar\" "
			));*/

		// pathList.put("Mac OS X". new Paths("", ""));

		Paths paths = pathList.get(System.getProperty("os.name"));
		if (null == paths)
		{
			javadocPath = "";
			classPath = "";
		}
		else
		{
			javadocPath = paths.javadocPath;
			classPath = paths.classPath;
		}
	}

	private final static String separator = java.io.File.separator;
	private final static String priečinok = "robodoc";
	private final static String priečinokBalíčka =
		"robodoc" + separator + "knižnica";

	private String aktuálnaPoložka;

	/******************************************************************/

	private RoboDoc()
	{
		/* * /
		System.out.println("OS Name: " + System.getProperty("os.name"));
		System.out.println("OS Architecture: " + System.getProperty("os.arch"));
		System.out.println("OS Version: " + System.getProperty("os.version"));

		// Test kategórií:
		{
			String[] zoznam = null;

			try
			{
				zoznam = Súbor.zoznam(priečinok);
			}
			catch (Exception e)
			{
				System.err.println(e.getMessage());
			}

			for (String položka : zoznam)
			{
				aktuálnaPoložka = položka;

				if (aktuálnaPoložka.endsWith(".html"))
				{
					čítajSúbor(priečinok + separator + aktuálnaPoložka);
					// int zmien = aktualizujSúbor(pridajDoHTML, nahraďVHTML);
					// // zmien += aktualizujSúbor(pridajDoHTML, premenujSúbory);
					// System.out.println("Počet zmien " +
					// 	aktuálnaPoložka + ": " + zmien);
					// if (zmien > 0) uložSúbor();

					// Úprava kategórií
					roztrieďPrvky();
				}
			}


			vytvorSúborKategórií();
			naplňSúborKategórií();
			dokočiSúborKategórií();


			try
			{
				Súbor.kopíruj("kategorie-metod.html",
					priečinok + "/kategorie-metod.html", true);
				System.out.println("Súbor „kategorie-metod.html“ " +
					"úspešne skopírovaný…");
			}
			catch (Exception e)
			{
				System.err.println(e.getMessage());
			}
		}


		if (true) return;
		/* */


		if (javadoc)
		{
			System.out.println("Generovanie dokumentácie systémom javadoc…");

			/* */
			exec(javadocPath + "javadoc " +
				"-use -author -version -protected -source 1.8 " +
				"-noindex -notree -nohelp " + // -nonavbar 
				"-exclude podpora apacheAntZIP " +
				"-link https://docs.ORACLE.com/javase/8/docs/api/ " +
				"-d " + priečinok + " -encoding UTF-8 " +
				"-docencoding UTF-8 knižnica " + // GRobot.java
				"-notimestamp -nodeprecatedlist " +
				"-Xdoclint:-html " +
				"--allow-script-in-comments " +
				// accessibility, html, missing, reference, or syntax
				"-stylesheetfile stylesheet.css" +
				classPath);
			/* */


			// // TODO – vymaž (testovanie):
			// try
			// {
			// 	// Čo sa to do pekla deje?
			// 	Súbor.kopíruj(priečinok + "/knižnica/package-summary.html",
			// 		priečinok + "/index-bak.htm", true);
			// 	System.out.println("Súbor „package-summary.html“ " +
			// 		"bol zálohovaný do súboru „index-bak.htm“.");
			// }
			// catch (Exception e)
			// {
			// 	System.err.println(e.getMessage());
			// }


			try
			{
				// Súbor.kopíruj("inherit.gif", priečinok +
				// 	"/resources/inherit.gif", true);
				// System.out.println("Súbor „inherit.gif“ úspešne skopírovaný…");
				Súbor.vytvorPriečinok(priečinok + "/resources");

				try
				{
					Súbor.kopíruj("zoznam-zmien.html",
						priečinok + "/zoznam-zmien.html", true);
					System.out.println("Súbor „zoznam-zmien.html“ " +
						"úspešne skopírovaný…");
				}
				catch (Exception e)
				{
					System.out.println(" " + e.getMessage());
				}

				// Ďalšie súbory (neprepisované automaticky):
				for (String ďalší : new String[] {"apache-licence-2.0.html",
					"rastlinka-svg.html", "JScrollBar.7z", "test-roja.7z",
					"kolotoc-ikonky.7z", "vzdialenost-useciek.7z"})
				{
					try
					{
						Súbor.kopíruj("zdroje/" + ďalší,
							priečinok + "/resources/" + ďalší, false);
						System.out.println("Súbor „" + ďalší +
							"“ úspešne skopírovaný…");
					}
					catch (Exception e)
					{
						System.out.println(" " + e.getMessage());
					}
				}

				System.out.println("Kopírujem obrázky…");
				String obrázky[] = Súbor.zoznamSúborov("zdroje");
				for (String obrázok : obrázky)
				{
					if (obrázok.endsWith(".png") || obrázok.endsWith(".gif") ||
						obrázok.endsWith(".svg") || obrázok.endsWith(".jpeg"))
					{
						System.out.print("Kopírujem obrázok: " + obrázok);
						try
						{
							Súbor.kopíruj("zdroje/" + obrázok, priečinok +
								"/resources/" + obrázok, false);
							System.out.println(" OK");
						}
						catch (Exception e)
						{
							System.out.println(" " + e.getMessage());
						}
					}
				}

				System.out.println("Kopírujem zvuky…");
				String zvuky[] = Súbor.zoznamSúborov("zdroje");
				for (String zvuk : zvuky)
				{
					if (zvuk.endsWith(".wav"))
					{
						System.out.print("Kopírujem zvuk: " + zvuk);
						try
						{
							Súbor.kopíruj("zdroje/" + zvuk, priečinok +
								"/resources/" + zvuk, false);
							System.out.println(" OK");
						}
						catch (Exception e)
						{
							System.out.println(" " + e.getMessage());
						}
					}
				}

				súbor.otvorNaZápis(priečinok + "/curver.txt");
				súbor.zapíšRiadok(Konštanty.majorVersion + "." +
					Integer.toString(Konštanty.minorVersion + 100).
					substring(1));
				súbor.zavri();

				System.out.println("Súbor s aktuálnou verziou " +
					"bol vytvorený…");
			}
			catch (Exception e)
			{
				System.err.println(e.getMessage());
			}
		}

		if (!Súbor.existuje(priečinok))
		{
			if (new java.io.File(priečinok).mkdir())
				System.out.println("Priečinok " + priečinok + " vytvorený…");
			else
				System.out.println("Vytvorenie priečinka " +
					priečinok + " zlyhalo…");
		}

		if (!Súbor.existuje(priečinok + separator + "class-use"))
		{
			if (new java.io.File(priečinok + separator + "class-use").mkdir())
				System.out.println("Priečinok " + priečinok +
					"class-use" + " vytvorený…");
			else
				System.out.println("Vytvorenie priečinka " +
					priečinok + separator + "class-use" + " zlyhalo…");
		}

		String[] zoznam = null;

		// Rámový index bude nahradený upraveným package-summary.html
		Súbor.vymaž(priečinok + separator + "index.html");
		Súbor.vymaž(priečinok + separator + "allclasses-frame.html");
		Súbor.vymaž(priečinok + separator + "allclasses-noframe.html");
		Súbor.vymaž(priečinokBalíčka + separator + "package-frame.html");
		// package-summary.html nemažeme, ten sa ešte zíde
		Súbor.vymaž(priečinok + separator + "package-list");
		Súbor.vymaž(priečinok + separator + "serialized-form");

		for (int j = 0; j + 1 < premenujSúbory.length; j += 2)
		{
			String zdroj = priečinok + separator + premenujSúbory[j];
			String cieľ = priečinok + separator + premenujSúbory[j + 1];

			if ((Súbor.existuje(cieľ) && !Súbor.vymaž(cieľ)) ||
				!Súbor.premenuj(zdroj, cieľ))
			{
				System.err.println("Zlyhalo premenovanie súboru: " + zdroj);
				// obsahSúboru.nahraď(i, riadok.toString());
				// ++početZmien;
			}
		}

		for (int j = 0; j + 1 < premenujSúboryBalíčka.length; j += 2)
		{
			String zdroj = priečinokBalíčka + separator +
				premenujSúboryBalíčka[j];
			String cieľ = priečinok + separator +
				premenujSúboryBalíčka[j + 1];

			if (zdroj.equals(cieľ)) continue;

			if ((Súbor.existuje(cieľ) && !Súbor.vymaž(cieľ)) ||
				!Súbor.premenuj(zdroj, cieľ))
			{
				System.err.println("Zlyhalo premenovanie súboru: " + zdroj);
				// obsahSúboru.nahraď(i, riadok.toString());
				// ++početZmien;
			}
		}


		for (int j = 0; j + 1 < premenujSúboryPoužitiaMetód.length; j += 2)
		{
			String zdroj = priečinokBalíčka + separator + "class-use" +
				separator + premenujSúboryPoužitiaMetód[j];
			String cieľ = priečinok + separator + "class-use" +
				separator + premenujSúboryPoužitiaMetód[j + 1];

			if (zdroj.equals(cieľ)) continue;

			if ((Súbor.existuje(cieľ) && !Súbor.vymaž(cieľ)) ||
				!Súbor.premenuj(zdroj, cieľ))
			{
				System.err.println("Zlyhalo premenovanie súboru " +
					"použitia metód: " + zdroj);
				// obsahSúboru.nahraď(i, riadok.toString());
				// ++početZmien;
			}
		}


		for (int j = 0; j + 1 < ďalšieSúboryBalíčka.length; j += 2)
		{
			String zdroj = priečinokBalíčka + separator +
				ďalšieSúboryBalíčka[j];
			String cieľ = priečinok + separator +
				ďalšieSúboryBalíčka[j + 1];

			if ((Súbor.existuje(cieľ) && !Súbor.vymaž(cieľ)) ||
				!Súbor.premenuj(zdroj, cieľ))
			{
				System.err.println("Zlyhalo premenovanie súboru: " + zdroj);
				// obsahSúboru.nahraď(i, riadok.toString());
				// ++početZmien;
			}
		}

		Súbor.vymaž(priečinok + separator + "knižnica" +
			separator + "class-use");
		Súbor.vymaž(priečinok + separator + "knižnica");

		try
		{
			zoznam = Súbor.zoznam(priečinok);
		}
		catch (Exception e)
		{
			System.err.println(e.getMessage());
		}

		if (robodoc)
		{
			System.out.println("Preklad a doplnenie…");

			// Úprava indexu:
			{
				aktuálnaPoložka = "index.html";
				čítajSúbor(priečinok + separator + aktuálnaPoložka);
				int zmien = aktualizujSúbor(pridajDoPackage, nahraďVPackage);
				System.out.println("Počet zmien " +
					aktuálnaPoložka + ": " + zmien);
				if (zmien > 0) uložSúbor();
			}

			String aktuálnyPriečinok = priečinok + separator;

			for (int i = 0; i < 2; ++i)
			{
				// Úprava ostatných súborov:
				for (String položka : zoznam)
				{
					aktuálnaPoložka = položka;

					if (aktuálnaPoložka.endsWith(".html"))
					{
						čítajSúbor(aktuálnyPriečinok + aktuálnaPoložka);
						int zmien = aktualizujSúbor(
							pridajDoHTML, nahraďVHTML);
						// zmien += aktualizujSúbor(
						// 	pridajDoHTML, premenujSúbory);
						System.out.println("Počet zmien " + aktuálnyPriečinok +
							aktuálnaPoložka + ": " + zmien);
						if (zmien > 0) uložSúbor();

						// Úprava kategórií
						/*‼‼*/roztrieďPrvky();/*‼‼*/
					}
					// else if (aktuálnaPoložka.endsWith(".css"))
					// {
					// 	// čítajSúbor(aktuálnyPriečinok + aktuálnaPoložka);
					// 	// int zmien = aktualizujSúbor(pridajDoCSS, nahraďVCSS);
					// 	// System.out.println("Počet zmien " +
					// 	// 	aktuálnaPoložka + ": " + zmien);
					// 	// if (zmien > 0) uložSúbor();
					// }
				}

				if (0 != i) break;

				System.out.println("\nDruhá fáza\n");
				// Druhá iterácia bude so súbormi v podpriečinku „class-use“:
				try
				{
					aktuálnyPriečinok = priečinok + separator + "class-use";
					zoznam = Súbor.zoznam(aktuálnyPriečinok);
					// for (String položka : zoznam)
					// 	System.out.print(položka + " ");
					// System.out.println();
					aktuálnyPriečinok += separator;
				}
				catch (Exception e)
				{
					System.err.println(e.getMessage());
				}
				// System.out.println("\n");
			}


			System.out.println("vytvorSúborKategórií();");
			vytvorSúborKategórií();
			System.out.println("naplňSúborKategórií()");
			naplňSúborKategórií();
			System.out.println("dokočiSúborKategórií()");
			dokočiSúborKategórií();
			System.out.println("--");


			try
			{
				Súbor.kopíruj("kategorie-metod.html",
					priečinok + "/kategorie-metod.html", true);
				System.out.println("Súbor „kategorie-metod.html“ " +
					"úspešne skopírovaný…");
			}
			catch (Exception e)
			{
				System.err.println(e.getMessage());
			}


			try
			{
				zoznam = Súbor.zoznam(priečinok);
			}
			catch (Exception e)
			{
				System.err.println(e.getMessage());
			}
		}

		/*if (htmlclean)
		{
			System.out.println("Čistenie položiek nástrojom HtmlCleaner…");

			for (String položka : zoznam)
			{
				if (položka.endsWith(".html"))
				{
					System.out.print("Cleaning " + položka + ": ");

					exec("java -jar .." + separator + "HtmlCleaner" +
						separator + "htmlcleaner-2.2.jar src=" + priečinok +
						separator + položka + " incharset=UTF-8 " +
						" dest=" + priečinok + separator + položka +
						" outcharset=UTF-8 outputtype=htmlsimple");
				}
			}
		}*/

		System.out.println("Dokončené…\n\nKontrola štatistík:\n");

		int check = 0;
		for (Map.Entry<String, Integer> položka : štatistika.entrySet())
		{
			if (0 == položka.getValue())
			{
				System.out.println("    Nasledujúce pravidlo " +
					"nebolo ani raz použité: " + položka.getKey());
				++check;
			}
		}
		if (0 == check) System.out.println("    Žiadne nepoužité pravidlá.");

		System.out.println("\nKontrola dokončená…");
	}


	private void exec(String command)
	{
		try
		{
			System.out.println("Executing: " + command);
			Process p = Runtime.getRuntime().exec(command);

			java.io.BufferedReader stdInput = new java.io.BufferedReader(
				new java.io.InputStreamReader(p.getInputStream()));

			java.io.BufferedReader stdError = new java.io.BufferedReader(
				new java.io.InputStreamReader(p.getErrorStream()));

			String s = null;
			while ((s = stdInput.readLine()) != null)
			{
				System.out.println(s);
			}

			while ((s = stdError.readLine()) != null)
			{
				System.err.println(s);
			}
			System.out.println("Execution finished.");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}


	private void čítajSúbor(String názov)
	{
		názovSúboru = názov;
		try
		{
			obsahSúboru.vymaž();

			// System.out.println("Čítam súbor: " + názovSúboru);
			súbor.otvorNaČítanie(názovSúboru);

			String riadok; int počet = 0;
			while (null != (riadok = súbor.čítajRiadok()))
			{
				obsahSúboru.pridaj(riadok);
				++počet;
			}

			súbor.zavri();
			// System.out.println("Prečítaných " + počet +
			// 	" riadkov zo súboru: " + názovSúboru);
		}
		catch (Exception e)
		{
			System.err.println(e.getMessage());
		}
	}


	private TreeMap<String, Integer> štatistika =
		new TreeMap<String, Integer>();
	private String pridajNaĎalšíRiadok = null;

	private boolean nahraď(StringBuffer riadok, String čo, String čím)
	{ return nahraď(riadok, čo, čím, null); }

	private boolean nahraď(StringBuffer riadok, String čo, String čím,
		String IDŠtatistiky)
	{
		if (čo.equals(čím)) return false;

		if (null == IDŠtatistiky)
		{
			if (!štatistika.containsKey(čo)) štatistika.put(čo, 0);
		}
		else
		{
			if (!štatistika.containsKey(IDŠtatistiky))
				štatistika.put(IDŠtatistiky, 0);
		}

		boolean zmena = false;
		int pozícia = riadok.indexOf(čo),
			pozícia2 = -1, dĺžka;
		while (-1 != pozícia)
		{
			riadok.delete(pozícia, pozícia + čo.length());

			pozícia2 = čím.indexOf("\f");
			if (-1 == pozícia2)
			{
				riadok.insert(pozícia, čím);
				dĺžka = čím.length();
			}
			else
			{
				riadok.insert(pozícia, čím.substring(0, pozícia2));
				pridajNaĎalšíRiadok = čím.substring(pozícia2 + 1);
				dĺžka = pozícia2;
			}

			zmena = true;
			if (null == IDŠtatistiky)
			{
				štatistika.put(čo, 1 + štatistika.get(čo));
			}
			else
			{
				štatistika.put(IDŠtatistiky, 1 + štatistika.get(IDŠtatistiky));
			}

			pozícia2 = riadok.indexOf(čo);
			if (-1 != pozícia2 && pozícia2 <= pozícia + dĺžka)
			{
				System.out.println("  *** Vážna chyba! *** " +
					"Rekurzívne nahrádzanie: \n" +
					"    Riadok:    " + riadok + "\n" +
					"    Čo:        " + čo + "\n" +
					"    Čím:       " + čím + "\n" +
					"    Pozícia 1: " + pozícia + "\n" +
					"    Pozícia 2: " + pozícia2 + "\n" +
					"    Dĺžka:     " + dĺžka);
				break;
			}
			pozícia = pozícia2;
		}
		return zmena;
	}


	private final static Pattern rozpoznajPredĎalšTriedu =
		Pattern.compile("<li>(?:<a href=\"[^\"]+\" title=\"[^\"]+\"><span class=\"typeNameLink\">)?(?:Predchádzajúca|Ďalšia) trieda(?:</span></a>)?</li>");

	private int aktualizujSúbor(String[] pridaj, String[] nahraď)
	{
		final StringBuffer riadok = new StringBuffer();
		int početZmien = 0;
		int indexNaPridanie = -1;

		int zmažMedzery = 0;

		// System.out.println("DEBUG: položka: " + aktuálnaPoložka);

		for (int i = 0; i < obsahSúboru.veľkosť(); ++i)
		{
			if (obsahSúboru.daj(i).equals(pridaj[0]) && indexNaPridanie != -2)
			{
				indexNaPridanie = i;
				continue;
			}

			if (obsahSúboru.daj(i).equals(pridaj[1]))
			{
				indexNaPridanie = -2;
				continue;
			}

			riadok.setLength(0);

			if (null != pridajNaĎalšíRiadok)
			{
				riadok.append(pridajNaĎalšíRiadok);
				pridajNaĎalšíRiadok = null;
			}

			riadok.append(obsahSúboru.daj(i));


			for (int j = 0; j + 1 < premenujSúbory.length; j += 2)
			{
				if (nahraď(riadok, premenujSúbory[j], premenujSúbory[j + 1],
					"ID: renameFiles"))
				{
					obsahSúboru.nahraď(i, riadok.toString());
					++početZmien;
				}
			}


			// Fix na package-summary od Oracle…
			if (nahraď(riadok, "/package-summary.html?", "##PKG_SUM_ORA##"))
			{
				obsahSúboru.nahraď(i, riadok.toString());
				++početZmien;
			}

			for (int j = 0; j + 1 < premenujSúboryBalíčka.length; j += 2)
			{
				if (nahraď(riadok, premenujSúboryBalíčka[j],
					premenujSúboryBalíčka[j + 1], "ID: renamePkgFiles"))
				{
					obsahSúboru.nahraď(i, riadok.toString());
					++početZmien;
				}
			}

			// Fix na package-summary od Oracle…
			if (nahraď(riadok, "##PKG_SUM_ORA##", "/package-summary.html?"))
			{
				obsahSúboru.nahraď(i, riadok.toString());
				++početZmien;
			}


			for (int j = 0; j + 1 < opravNázvySúborovADiakritiku.
				length; j += 2)
			{
				if (nahraď(riadok, opravNázvySúborovADiakritiku[j],
					opravNázvySúborovADiakritiku[j + 1], "ID: repairFNames"))
				{
					obsahSúboru.nahraď(i, riadok.toString());
					++početZmien;
				}
			}


			// if (nahraď(riadok, "testovacie pravidlo " +
			// 	"(nebude ani raz použité)", ""))
			// {
			// 	obsahSúboru.nahraď(i, riadok.toString());
			// 	++početZmien;
			// }

			if (nahraď(riadok, "\"../../", "\"../"))
			{
				obsahSúboru.nahraď(i, riadok.toString());
				++početZmien;
			}
			else if (nahraď(riadok, "\"../", "\""))
			{
				obsahSúboru.nahraď(i, riadok.toString());
				++početZmien;
			}

			if (nahraď(riadok, "kni\\u017Enica", "knižnica"))
			{
				obsahSúboru.nahraď(i, riadok.toString());
				++početZmien;
			}

			if (nahraď(riadok, "/raubirius/GRobot/blob/master/kni%C5%BEnica/",
				"/GRobotAtGitHub/"))
			{
				obsahSúboru.nahraď(i, riadok.toString());
				++početZmien;
			}

			if (nahraď(riadok, "kni%C5%BEnica", "knižnica"))
			{
				obsahSúboru.nahraď(i, riadok.toString());
				++početZmien;
			}

			if (nahraď(riadok, "/GRobotAtGitHub/",
				"/raubirius/GRobot/blob/master/kni%C5%BEnica/"))
			{
				obsahSúboru.nahraď(i, riadok.toString());
				++početZmien;
			}

			if (nahraď(riadok, "parent.document.title=\"knižnica\";", ""))
			{
				obsahSúboru.nahraď(i, riadok.toString());
				++početZmien;
			}


			/* TODO – chyba‼ Toto nemôžeš robiť paušálne, treba to zlepšiť! */

			if (nahraď(riadok, "index.html?knižnica/", ""))
			{
				obsahSúboru.nahraď(i, riadok.toString());
				++početZmien;
			}

			if (nahraď(riadok, "index.html?", ""))
			{
				obsahSúboru.nahraď(i, riadok.toString());
				++početZmien;
			}

			/* — */


			//if (nahraď(riadok, "<li><a href=\"../index.html?" +
			//	aktuálnaPoložka + "\" target=\"_top\">Frames</a></li>",
			//	"<!-- Odkaz „Rámy“ zrušený -->"))
			//{
			//	obsahSúboru.nahraď(i, riadok.toString());
			//	++početZmien;
			//}

			if (nahraď(riadok, "<li><a href=\"" + //index.html?" +
				aktuálnaPoložka + "\" target=\"_top\">Frames</a></li>",
				"<!-- Odkaz „Rámy“ zrušený -->", "ID: Frames1"))
			{
				obsahSúboru.nahraď(i, riadok.toString());
				++početZmien;
			}

			//if (nahraď(riadok, "<li><a href=\"../index.html\" " +
			//	"target=\"_top\">Frames</a></li>",
			//	"<!-- Odkaz „Rámy“ zrušený -->"))
			//{
			//	obsahSúboru.nahraď(i, riadok.toString());
			//	++početZmien;
			//}

			//if (nahraď(riadok, "<li><a href=\"index.html\" " +
			//	"target=\"_top\">Frames</a></li>",
			//	"<!-- Odkaz „Rámy“ zrušený -->", "ID: Frames2"))
			//{
			//	obsahSúboru.nahraď(i, riadok.toString());
			//	++početZmien;
			//}

			if (nahraď(riadok, "<li><a href=\"" + aktuálnaPoložka +
				"\" target=\"_top\">No&nbsp;Frames</a></li>",
				"<!-- Odkaz „Bez rámov“ zrušený -->", "ID: NoFrames1"))
			{
				obsahSúboru.nahraď(i, riadok.toString());
				++početZmien;
			}

			//if (nahraď(riadok, "<li><a href=\"" + aktuálnaPoložka +
			//	"\" target=\"_top\">No Frames</a></li>",
			//	"<!-- Odkaz „Bez rámov“ zrušený *1 -->", "ID: NoFrames2"))
			//{
			//	obsahSúboru.nahraď(i, riadok.toString());
			//	++početZmien;
			//}

			//if (nahraď(riadok, "<li><a href=\"../allclasses-noframe.html" +
			//	"\">All&nbsp;Classes</a></li>",
			//	"<!-- Odkaz „Všetky triedy“ zrušený -->"))
			//{
			//	obsahSúboru.nahraď(i, riadok.toString());
			//	++početZmien;
			//}

			//if (nahraď(riadok, "<li><a href=\"../allclasses-noframe.html" +
			//	"\">All Classes</a></li>",
			//	"<!-- Odkaz „Všetky triedy“ zrušený -->"))
			//{
			//	obsahSúboru.nahraď(i, riadok.toString());
			//	++početZmien;
			//}

			if (nahraď(riadok, "<li><a href=\"allclasses-noframe.html" +
				"\">All&nbsp;Classes</a></li>",
				"<!-- Odkaz „Všetky triedy“ zrušený -->"))
			{
				obsahSúboru.nahraď(i, riadok.toString());
				++početZmien;
			}

			//if (nahraď(riadok, "<li><a href=\"allclasses-noframe.html" +
			//	"\">All Classes</a></li>",
			//	"<!-- Odkaz „Všetky triedy“ zrušený -->"))
			//{
			//	obsahSúboru.nahraď(i, riadok.toString());
			//	++početZmien;
			//}

			if (nahraď(riadok, "Prev&nbsp;Package",
				"<!-- Neaktívny odkaz „Predchádzajúci balíček“ zrušený -->"))
			{
				obsahSúboru.nahraď(i, riadok.toString());
				++početZmien;
			}

			//if (nahraď(riadok, "&nbsp;Prev Package&nbsp;",
			//	"<!-- Neaktívny odkaz „Predchádzajúci balíček“ zrušený *1 -->"))
			//{
			//	obsahSúboru.nahraď(i, riadok.toString());
			//	++početZmien;
			//}

			if (nahraď(riadok, "Next&nbsp;Package",
				"<!-- Neaktívny odkaz „Ďalší balíček“ zrušený -->"))
			{
				obsahSúboru.nahraď(i, riadok.toString());
				++početZmien;
			}

			//if (nahraď(riadok, "&nbsp;Next Package",
			//	"<!-- Neaktívny odkaz „Ďalší balíček“ zrušený *1 -->"))
			//{
			//	obsahSúboru.nahraď(i, riadok.toString());
			//	++početZmien;
			//}

			if (nahraď(riadok, ">Prev<",
				"><!-- Neaktívny odkaz „Predchádzajúce“ zrušený --><"))
			{
				obsahSúboru.nahraď(i, riadok.toString());
				++početZmien;
			}

			//if (nahraď(riadok, "&nbsp;Prev&nbsp;",
			//	"<!-- Neaktívny odkaz „Predchádzajúce“ zrušený *1 -->"))
			//{
			//	obsahSúboru.nahraď(i, riadok.toString());
			//	++početZmien;
			//}

			if (nahraď(riadok, ">Next<",
				"><!-- Neaktívny odkaz „Ďalšie“ zrušený --><"))
			{
				obsahSúboru.nahraď(i, riadok.toString());
				++početZmien;
			}

			//if (nahraď(riadok, "&nbsp;Next",
			//	"<!-- Neaktívny odkaz „Ďalšie“ zrušený *1 -->"))
			//{
			//	obsahSúboru.nahraď(i, riadok.toString());
			//	++početZmien;
			//}

			if (nahraď(riadok, "<li class=\"navBarCell1Rev\">Class</li>",
				"<!-- Neaktívny odkaz „Trieda“ zrušený -->"))
			{
				obsahSúboru.nahraď(i, riadok.toString());
				++početZmien;
			}

			if (nahraď(riadok, "<li>Class</li>",
				"<!-- Neaktívny odkaz „Trieda“ zrušený -->"))
			{
				obsahSúboru.nahraď(i, riadok.toString());
				++početZmien;
			}

			/***
			// ∄ anymore
			if (nahraď(riadok, "<FONT CLASS=\"NavBarFont1\">Class</FONT>",
				"<!-- Neaktívny odkaz „Trieda“ zrušený -->"))
			{
				obsahSúboru.nahraď(i, riadok.toString());
				++početZmien;
			}
			***/

			if (-1 == zmažMedzery)
			{
				zmažMedzery = 0;
				for (int j = 0; j < riadok.length(); ++j)
				{
					if (riadok.charAt(j) == ' ') ++zmažMedzery;
					else if (riadok.charAt(j) == '\t') zmažMedzery += 4;
					else break;
				}
			}

			if (zmažMedzery > 0)
			{
				// System.out.println(riadok.toString());
				int znakovNaZmazanie = 0;

				for (int j = 0; j < zmažMedzery && j < riadok.length(); ++j)
				{
					if (riadok.charAt(j) == ' ') ++znakovNaZmazanie;
					else if (riadok.charAt(j) == '\t')
					{
						++znakovNaZmazanie;
						j += 3;
					}
					else break;
				}

				if (-1 != riadok.indexOf("</pre>"))
				{
					// System.out.println("End: " + riadok.toString() + '\n');
					zmažMedzery = 0;
				}

				if (znakovNaZmazanie > 0)
				{
					riadok.delete(0, znakovNaZmazanie);

					znakovNaZmazanie = 0;
					for (int j = 0; j < riadok.length(); ++j)
					{
						if (riadok.charAt(j) == ' ') ++znakovNaZmazanie;
						else break;
					}
					znakovNaZmazanie /= 2;
					riadok.delete(0, znakovNaZmazanie);

					obsahSúboru.nahraď(i, riadok.toString());
					++početZmien;
					// System.out.println(riadok.toString() + '\n');
				}
			}
			else if (-1 != riadok.indexOf("<pre CLASS=\"example\">"))
			{
				// System.out.println("\nStart: " + riadok.toString());
				zmažMedzery = -1;
			}
			// else if (-1 != riadok.indexOf("<pre"))
			// {
			// 	// System.out.println("\nPRE: " + riadok.toString());
			// }


			for (int j = 0; j + 1 < nahraď.length; j += 2)
			{
				// System.out.print("DEBUG: nahraď: " + nahraď[j]);

				if (nahraď(riadok, nahraď[j], nahraď[j + 1]))
				{
					// System.out.print(" +");
					obsahSúboru.nahraď(i, riadok.toString());
					++početZmien;
				}
				// else System.out.print(" -");

				// System.out.println();
			}
		}


		// Presunutie odkazov Predchádzajúca/Ďalšia trieda
		Matcher match = null; String predĎalš = "";

		for (int i = 0; i < obsahSúboru.veľkosť(); ++i)
		{
			String tentoRiadok = obsahSúboru.daj(i);

			if (-1 != tentoRiadok.indexOf(">Predchádzajúca trieda<") ||
				-1 != tentoRiadok.indexOf(">Ďalšia trieda<"))
			{
				match = rozpoznajPredĎalšTriedu.matcher(tentoRiadok);
				if (match.matches())
				{
					String naPridanie = match.group(0).
						replace("<span class=\"typeNameLink\">", "").
						replace("</span>", "");
					if (-1 == predĎalš.indexOf(naPridanie))
						predĎalš += naPridanie;
					obsahSúboru.nahraď(i,
						"<!-- Odkaz navigácie tried presunutý. -->");
					++početZmien;
				}
			}
		}

		for (int i = 0; i < obsahSúboru.veľkosť(); ++i)
		{
			String tentoRiadok = obsahSúboru.daj(i);
			if (-1 != tentoRiadok.indexOf("<!-- $PREDĎALŠ -->"))
			{
				tentoRiadok = tentoRiadok.replace(
					"<!-- $PREDĎALŠ -->", predĎalš);
				obsahSúboru.nahraď(i, tentoRiadok);
				++početZmien;
			}
		}

		// Kategórie treba skúsiť prečítať aj tu, aby mohlo byť overené, či
		// pre túto triedu jestvujú…
		čítajKategórie();
		String názovTriedy = extrahujNázovTriedy(názovSúboru);

		if (null == všetkyKategórie.get(názovTriedy))
		{
			for (int i = 0; i < obsahSúboru.veľkosť(); ++i)
			{
				String tentoRiadok = obsahSúboru.daj(i);
				if (-1 != tentoRiadok.indexOf("#$TRIEDA"))
				{
					tentoRiadok = tentoRiadok.replace("#$TRIEDA", "");
					obsahSúboru.nahraď(i, tentoRiadok);
					++početZmien;
				}
			}
		}
		else
		{
			for (int i = 0; i < obsahSúboru.veľkosť(); ++i)
			{
				String tentoRiadok = obsahSúboru.daj(i);
				if (-1 != tentoRiadok.indexOf("#$TRIEDA"))
				{
					tentoRiadok = tentoRiadok.replace("#$TRIEDA",
						"#" + názovTriedy);
					obsahSúboru.nahraď(i, tentoRiadok);
					++početZmien;
				}
			}
		}


		if (indexNaPridanie == -1)
			indexNaPridanie = obsahSúboru.size() - 1;

		if (indexNaPridanie >= 0)
		{
			for (String pridajRiadok : pridaj)
			{
				if (pridajRiadok == pridaj[0]) continue;
				obsahSúboru.vlož(indexNaPridanie++, pridajRiadok);
			}
			početZmien += pridaj.length;
		}

		return početZmien;
	}


	@SuppressWarnings("serial")
	private class MyNullableTreeMap<K, V> extends TreeMap<K, V>
	{
		@Override public V get(Object key)
		{
			if (null == key) key = "«null»";
			return super.get(key);
		}

		@SuppressWarnings("unchecked")
		@Override public V put(K key, V value)
		{
			if (null == key)
				// if (key instanceof K)
					key = (K)"«null»";
			return super.put(key, value);
		}
	}

	@SuppressWarnings("serial")
	private class KategóriePodľaParametrov extends MyNullableTreeMap<String,
		String> {}

	@SuppressWarnings("serial")
	private class ParametrePodľaMetódy extends MyNullableTreeMap<String,
		KategóriePodľaParametrov>
	{
		// Nezotriedený zoznam kategórií
		public final Vector<String> zoznamKategórií = new Vector<String>();
	}

	@SuppressWarnings("serial")
	private class MetódyPodľaTried extends MyNullableTreeMap<String,
		ParametrePodľaMetódy> {}

	private final MetódyPodľaTried všetkyKategórie = new MetódyPodľaTried();

	private boolean čítajKategórie = true;

	private void čítajKategórie()
	{
		if (čítajKategórie) try
		{
			súbor.otvorNaČítanie("kategorie.cfg");
			String poslednáTrieda = null;
			String poslednáKategória = null;
			String poslednáMetóda = null;
			String poslednýZoznamParametrov = null;

			ParametrePodľaMetódy metódy = null;
			KategóriePodľaParametrov parametre = null;

			String riadok; int i = 1, početChýb = 0;
			while (null != (riadok = súbor.čítajRiadok()))
			{
				if (!riadok.startsWith("\t\t\t") &&
					(riadok.startsWith("\t\t") ||
						riadok.startsWith("\t")) &&
					null != poslednáMetóda)
				{
					if (null == poslednáTrieda)
					{
						++početChýb;
						System.out.println("  Varovanie! Chyba v súbore " +
							"s definíciami kategórií. Metóda " +
							poslednáMetóda + " nie je zaradená v žiadnom " +
							"súbore. Okolo riadka: " + (i - 1));
					}

					if (null == poslednáKategória)
					{
						++početChýb;
						System.out.println("  Varovanie! Chyba v súbore " +
							"s definíciami kategórií. Metóda " +
							poslednáMetóda + " nie je zaradená v žiadnej " +
							"kategórii. Okolo riadka: " + (i - 1));
					}

					metódy = všetkyKategórie.get(poslednáTrieda);
					if (null == metódy)
					{
						metódy = new ParametrePodľaMetódy();
						všetkyKategórie.put(poslednáTrieda, metódy);
					}

					parametre = metódy.get(poslednáMetóda);
					if (null == parametre)
					{
						parametre = new KategóriePodľaParametrov();
						metódy.put(poslednáMetóda, parametre);
					}

					String over = parametre.get(poslednýZoznamParametrov);

					if (null != over && null != poslednýZoznamParametrov)
					{
						++početChýb;
						System.out.println("  Varovanie! Chyba v súbore " +
							"s definíciami kategórií. Metóda " +
							poslednáMetóda + " s aktuálnymi parametrami " +
							"už je zaradená v tejto kategórii. Okolo " +
							"riadka: " + (i - 1) + "\n    Parametre: " +
							poslednýZoznamParametrov);
					}

					parametre.put(poslednýZoznamParametrov,
						poslednáKategória);

					poslednáMetóda = null;
					poslednýZoznamParametrov = null;
				}

				if (riadok.startsWith("\t\t\t"))
				{
					if (null == poslednáMetóda)
					{
						++početChýb;
						System.out.println("  Varovanie! Chyba v súbore " +
							"s definíciami kategórií. Parametre na riadku " +
							i + " nepatria žiadnej metóde");
					}
					else if (null != poslednýZoznamParametrov)
					{
						++početChýb;
						System.out.println("  Varovanie! Chyba v súbore " +
							"s definíciami kategórií. Metóda " +
							poslednáMetóda + " má definovaný zdvojený " +
							"zoznam parametrov. Riadok: " + i);
					}
					poslednýZoznamParametrov = riadok.trim();
				}
				else if (riadok.startsWith("\t\t"))
				{
					poslednáMetóda = riadok.trim();
				}
				else if (riadok.startsWith("\t"))
				{
					poslednáKategória = riadok.trim();

					if (null == poslednáTrieda)
					{
						++početChýb;
						System.out.println("  Varovanie! Chyba v súbore " +
							"s definíciami kategórií. Kategória " +
							poslednáKategória + " nie je zaradená v žiadnom " +
							"súbore. Okolo riadka: " + (i - 1));
					}

					metódy = všetkyKategórie.get(poslednáTrieda);
					if (null == metódy)
					{
						metódy = new ParametrePodľaMetódy();
						všetkyKategórie.put(poslednáTrieda, metódy);
					}

					metódy.zoznamKategórií.add(poslednáKategória);
				}
				else if (!riadok.isEmpty())
				{
					poslednáTrieda = riadok.trim();
				}

				++i;
			}

			if (0 == početChýb)
				System.out.println("Súbor s kategóriami bol " +
					"prečítaný bez chýb.");

			súbor.zavri();
			čítajKategórie = false;
		}
		catch (Exception e)
		{
			System.err.println(e.getMessage());
			e.printStackTrace();
		}
	}

	private class ZáznamOMetóde
	{
		private String kategória;

		private String typ;
		private String názov;
		private String odkaz;
		private String parametre;
		private String stručnýOpis;

		public ZáznamOMetóde(String kategória, String typ, String názov,
			String odkaz, String parametre, String stručnýOpis)
		{
			this.kategória = kategória;

			this.typ = typ;
			this.názov = názov;
			this.odkaz = odkaz;
			this.parametre = parametre;
			this.stručnýOpis = stručnýOpis;
		}
	}

	private final static Pattern nájdiZačiatokDefinície =
		Pattern.compile("<tr id=\"[^\"]+\" class=\"(row|alt)Color\">");

	private final static Pattern nájdiTyp =
		Pattern.compile("<td class=\"colFirst\"><code>((?:static )?(?:&lt;[^&]+&gt;&nbsp;)?<a(?: target=\"_blank\")? href=\"[^\"]+\" title=\"[^\"]+\">)?([^<]+)(</a>)?(&lt;<a(?: target=\"_blank\")? href=\"[^\"]+\" title=\"[^\"]+\">[^<]+</a>&gt;|&lt;[^&]+&gt;|\\[\\])?</code></td>");

	private final static Pattern rozpoznajUrčenie =
		Pattern.compile("<a href=\"([^\"]+)\"[^>]*>([^<]+)</a></span>\\(([^(]*)\\)");

	private final static Pattern extrahujNázovTriedy =
		Pattern.compile(priečinok + "[\\\\\\/]([-a-zA-Z]+).html");

	private String extrahujNázovTriedy(String názovSúboru)
	{
		Matcher match = extrahujNázovTriedy.matcher(názovSúboru);
		String názovTriedy = null;

		if (match.matches())
			názovTriedy = match.group(1).replace("-", ".")

				.replace("aa", "á").replace("ee", "é")
				.replace("ii", "í").replace("oo", "ó")
				.replace("uu", "ú").replace("yy", "ý")

				.replace("CC", "Č").replace("UU", "Ú")
				.replace("cc", "č").replace("ll", "ľ")
				.replace("ss", "š").replace("tt", "ť")
				.replace("zz", "ž");

		return názovTriedy;
	}

	@SuppressWarnings("serial")
	private class ZoznamMetód extends Vector<ZáznamOMetóde>
	{ public String id = null; }

	@SuppressWarnings("serial")
	private class KategórieMetód extends TreeMap<String, ZoznamMetód>
	{
		public String jadroNázvuSúboru = null;
		public String id = null;

		public void zaraď(String kategória, String typ, String názov,
			String odkaz, String parametre, String stručnýOpis)
		{
			ZoznamMetód zoznamMetód = get(kategória);
			if (null == zoznamMetód)
			{
				zoznamMetód = new ZoznamMetód();
				put(kategória, zoznamMetód);
			}

			ZáznamOMetóde záznam = new ZáznamOMetóde(kategória, typ,
				názov, odkaz, parametre, stručnýOpis);
			zoznamMetód.add(záznam);
		}
	}

	private final TreeMap<String, KategórieMetód> všetkyKategorizovanéSúbory =
		new TreeMap<String, KategórieMetód>();

	private int identifikátorRiadkaKategórie = 1, identifikátorKategórie = 1;


	private void roztrieďPrvky()
	{
		čítajKategórie();

		String jadroNázvuSúboru = názovSúboru.
			replaceAll(priečinok + "[\\\\\\/]", "");
		String názovTriedy = extrahujNázovTriedy(názovSúboru);

		if (null == všetkyKategórie.get(názovTriedy))
		{
			System.out.println("Súbor „" + jadroNázvuSúboru + "“ (trieda „" +
				názovTriedy + "“) nemá definované kategórie. (Preskakujem…)");
			return;
		}

		KategórieMetód kategórieMetód = new KategórieMetód();
		kategórieMetód.jadroNázvuSúboru = jadroNázvuSúboru;
		všetkyKategorizovanéSúbory.put(názovTriedy, kategórieMetód);


		int stav = 0, i = 1;
		Matcher match;
		String typ = null, určenie = null, odkaz = null, názov = null,
			parametre = null, stručnýOpis = null;

		System.out.println("Triedim kategórie súboru " + názovSúboru);
		// if (true) return;


		for (String riadok : obsahSúboru)
		{
			switch (stav)
			{
			case 1:
				if (riadok.equals("</table>")) stav = 0; else
				{
					match = nájdiZačiatokDefinície.matcher(riadok);
					if (match.matches())
					{
						typ = null;
						určenie = null;
						odkaz = null;
						názov = null;
						parametre = null;
						stručnýOpis = null;
						stav = 2;
					}
				}
				break;

			case 2:
				if (riadok.equals("</tr>"))
				{

					if (null != určenie)
					{
						match = rozpoznajUrčenie.matcher(
							určenie.replaceAll("  +", " "));
						if (match.matches())
						{
							if (null != odkaz)
							{
								System.out.println("  Varovanie! Nastalo " +
									"prepísanie odkazu. Riadok: " + i);
								System.out.println("    Pôvodný odkaz: " +
									odkaz);
							}
							odkaz = match.group(1);

							if (null != názov)
							{
								System.out.println("  Varovanie! Nastalo " +
									"prepísanie názvu. Riadok: " + i);
								System.out.println("    Pôvodný názov: " +
									názov);
							}
							názov = match.group(2);

							if (null != parametre)
							{
								System.out.println("  Varovanie! Nastalo " +
									"prepísanie parametrov. Riadok: " + i);
								System.out.println("    Pôvodné parametre: " +
									parametre);
							}
							parametre = match.group(3);
						}
						else
						{
							System.out.println("  Varovanie! Určenie " +
								"sa nepodarilo rozpoznať. (Koniec " +
								"definície je na riadku: " + i + ")");
							System.out.println("    Určenie: " + určenie);
						}
					}
					else
					{
						System.out.println("  Chyba, nenašlo sa určenie!");
					}

					if (null != stručnýOpis)
						stručnýOpis = stručnýOpis.replaceAll("  +", " ");

					if (null == typ || null == odkaz || null == názov ||
						null == parametre || null == stručnýOpis)
					{
						System.out.println("\n  Riadok: " + i);

						if (null != typ)
							System.out.println("    Typ: " + typ);
						if (null != odkaz)
							System.out.println("    Odkaz: " + odkaz);
						if (null != názov)
							System.out.println("    Názov: " + názov);
						if (null != parametre)
							System.out.println("    Parametre: " + parametre);
						if (null != stručnýOpis)
							System.out.println("    Stručný opis: " +
								stručnýOpis);

						if (null == typ)
							System.out.println("  Chyba, nenašiel sa typ!");
						if (null == odkaz)
							System.out.println("  Chyba, nenašiel sa odkaz!");
						if (null == názov)
							System.out.println("  Chyba, nenašiel sa názov!");
						if (null == parametre)
							System.out.println("  Chyba, nenašli sa " +
								"parametre!");
						if (null == stručnýOpis)
							System.out.println("  Chyba, nenašiel sa " +
								"stručný opis!");
						System.out.println();
					}

					// Zaraďovanie nájdených záznamov do kategórií
					else
					{
						// System.out.println(typ + "\n  " + názov);

						KategóriePodľaParametrov zoznamPodľaParametrov = null;
						ParametrePodľaMetódy metódy =
							všetkyKategórie.get(názovTriedy);

						if (null == metódy)
						{
							System.err.println("  Chyba! Trieda „" +
								názovTriedy + "“ nebola nájdená " +
								"v prečítanej konfigurácii.");
						}
						else
						{
							zoznamPodľaParametrov = metódy.get(názov);

							if (null == zoznamPodľaParametrov)
							{
								System.err.println("  Chyba! Metóda „" +
									názov + "“ nebola nájdená v prečítanej " +
									"konfigurácii triedy „" + názovTriedy +
									"“.");
							}
							else
							{
								String kategória =
									zoznamPodľaParametrov.get(parametre);
								if (null == kategória)
									kategória = zoznamPodľaParametrov.get(null);

								if (null == kategória)
								{
									System.err.println("  Chyba! Neboli " +
										"nájdené použiteľné parametre " +
										"v prečítanej konfigurácii metódy „" +
										názov + "“ triedy „" + názovTriedy +
										"“.\n    (Pokus o použitie " +
										"parametrov: „" + parametre + "“.)");
								}
								else
								{

									// ------------------- //
									// --- Vlož záznam --- //
									// ------------------- //

									kategórieMetód.zaraď(kategória, typ,
										názov, odkaz, parametre, stručnýOpis);
								}
							}
						}
					}

					stav = 1;
				}
				else if (riadok.equals("</td>")) ; else
				{
					if (riadok.startsWith("<td class=\"colLast\">" +
						"<code><span class=\"memberNameLink\">"))
					{
						if (null != určenie)
						{
							System.out.println("  Varovanie! Nastalo " +
								"prepísanie určenia. Riadok: " + i);
							System.out.println("    Pôvodné určenie: " +
								určenie);
						}
						určenie = riadok.substring(55);

						if (určenie.endsWith("</code>&nbsp;</td>"))
						{
							určenie = určenie.substring(0,
								určenie.length() - 18);
						}
						else if (určenie.endsWith("</code>"))
						{
							určenie = určenie.substring(0,
								určenie.length() - 7);
						}
						else stav = 3;
					}
					else if (riadok.startsWith("<div class=\"block\">"))
					{
						if (null != stručnýOpis)
						{
							System.out.println("  Varovanie! Nastalo " +
								"prepísanie stručného opisu. Riadok: " + i);
							System.out.println("    Pôvodný opis: " +
								stručnýOpis);
						}
						stručnýOpis = riadok.substring(19);

						if (stručnýOpis.endsWith("</div>"))
						{
							stručnýOpis = stručnýOpis.substring(0,
								stručnýOpis.length() - 6);
						}
						else stav = 4;
					}
					else
					{
						match = nájdiTyp.matcher(riadok);

						if (match.matches())
						{
							if (null != typ)
							{
								System.out.println("  Varovanie! Nastalo " +
									"prepísanie typu. Riadok: " + i);
								System.out.println("    Pôvodný typ: " + typ);
							}
							typ = "";
							if (null != match.group(1)) typ += match.group(1);
							if (null != match.group(2)) typ += match.group(2);
							if (null != match.group(3)) typ += match.group(3);
							if (null != match.group(4)) typ += match.group(4);
						}
						else
						{
							System.out.println("  Varovanie! Neznáme údaje " +
								"na riadku: " + i);
						}
					}
				}
				break;

			case 3:
				if (riadok.endsWith("</code>&nbsp;</td>"))
				{
					určenie += riadok.substring(0,
						riadok.length() - 18);
					stav = 2;
				}
				else if (riadok.endsWith("</code>"))
				{
					určenie += riadok.substring(0,
						riadok.length() - 7);
					stav = 2;
				}
				else určenie += riadok;
				break;

			case 4:
				if (riadok.endsWith("</div>"))
				{
					stručnýOpis += riadok.substring(0,
						riadok.length() - 6);
					stav = 2;
				}
				else stručnýOpis += riadok;
				break;

			default:
				if (riadok.equals("<h3>Prehľad metód</h3>")) stav = 1;
			}

			++i;
		}

		if (0 != stav)
			System.out.println("  Varovanie! " +
				"Predčasné ukončenie údajov. Riadok: " + i);
		else
			System.out.println("Kategórie súboru " +
				názovSúboru + " dokončené.");
	}


	private void vytvorSúborKategórií()
	{
		String zoznamTried = "<ul>\r\n";;

		for (Map.Entry<String, KategórieMetód> kategorizovanýSúbor :
			všetkyKategorizovanéSúbory.entrySet())
		{
			KategórieMetód kategórieMetód = kategorizovanýSúbor.getValue();
			if (kategórieMetód.isEmpty()) continue;

			kategórieMetód.id = kategorizovanýSúbor.getKey();

			zoznamTried += "<li><a href=\"#" + kategórieMetód.id + "\">" +
				kategorizovanýSúbor.getKey() + "</a></li>\r\n";

			// replace("$COREFILENAME",
			// 	kategórieMetód.jadroNázvuSúboru).
			// replace("$CLASSNAME", kategorizovanýSúbor.getKey()).
			// replace("$CATEGORYLIST", zoznamKategórií);
		}

		zoznamTried += "</ul>";

		try
		{
			identifikátorRiadkaKategórie = 1;
			identifikátorKategórie = 1;

			súbor.otvorNaZápis("kategorie-metod.html");

			// Súbor.pripoj("sablona-kategorie-zaciatok.txt",
			// 	"kategorie-metod.html");

			šablóna.otvorNaČítanie("sablona-kategorie-zaciatok.txt");

			String riadok;
			while (null != (riadok = šablóna.čítajRiadok()))
			{
				riadok = riadok.replace("$CLASSLIST", zoznamTried);
				súbor.zapíšRiadok(riadok);
			}

			súbor.zavri();
			šablóna.zavri();
		}
		catch (Exception e)
		{
			System.err.println(e.getMessage());
		}
	}

	private Súbor šablóna = new Súbor();

	private final static Pattern extrahujIDKategórie =
		Pattern.compile("‹([^›]+)›");
	private final static Pattern pomocPriTvorbeID =
		Pattern.compile("([^\\w\\pL]+)");
	private final static Pattern odstráňHTML = Pattern.compile("<[^>]+>");

	private void naplňSúborKategórií()
	{
		for (Map.Entry<String, KategórieMetód> kategorizovanýSúbor :
			všetkyKategorizovanéSúbory.entrySet())
		{
			KategórieMetód kategórieMetód = kategorizovanýSúbor.getValue();
			if (kategórieMetód.isEmpty())
			{
				System.err.println("  Chyba! Kategorizované údaje súboru „" +
					kategórieMetód.jadroNázvuSúboru + "“ neboli nájdené!");
				continue;
			}

			String zoznamKategórií = "<ul>\r\n";


			// ParametrePodľaMetódy metódy =
			// 	všetkyKategórie.get(kategorizovanýSúbor.getKey());
			// if (null != metódy)
			for (Map.Entry<String, ZoznamMetód>
				kategória : kategórieMetód.entrySet())
			{
				// for (String názovKategórie : metódy.zoznamKategórií)
				{
					String názovKategórie = kategória.getKey();
					if (názovKategórie.equals("Ignorované")) continue;
					String id; Matcher match;

					// System.out.println("pred: " + názovKategórie);
					match = odstráňHTML.matcher(názovKategórie);
					názovKategórie = match.replaceAll("");
					// System.out.println("po: " + názovKategórie);

					match = extrahujIDKategórie.matcher(názovKategórie);

					if (match.find())
					{
						id = kategórieMetód.id + "-" + match.group(1);
						názovKategórie = match.replaceFirst("");
					}
					else
					{
						if (názovKategórie.endsWith(" robota"))
							id = názovKategórie.substring(0,
								názovKategórie.length() - 7);
						else
							id = názovKategórie;

						id = id.replace(" a ", " ").replace(" alebo ", " ");

						match = pomocPriTvorbeID.matcher(id);

						id = kategórieMetód.id + "-" + match.replaceAll("-");
					}


					// ZoznamMetód zoznamMetód =
					// 	kategórieMetód.get(názovKategórie);
					// if (null == zoznamMetód)
					// {
					// 	System.out.println("CHYBA…");
					// }
					// else
					// {
					// 	zoznamMetód.id = id;
					// }

					kategória.getValue().id = id;


					zoznamKategórií += "<li><a href=\"#" + id + "\">" +
						názovKategórie + "</a></li>\r\n";

					++identifikátorKategórie;
				}
			}


			zoznamKategórií += "</ul>";

			// Vytvor novú tabuľku
			try
			{
				súbor.otvorNaZápis("kategorie-metod.html", true);
				šablóna.otvorNaČítanie("sablona-kategorie-tabulka-zaciatok.txt");

				String riadok;
				while (null != (riadok = šablóna.čítajRiadok()))
				{
					riadok = riadok.
						replace("$COREFILENAME",
							kategórieMetód.jadroNázvuSúboru).
						replace("$CLASSID", kategórieMetód.id).
						replace("$CLASSNAME", kategorizovanýSúbor.getKey()).
						replace("$CATEGORYLIST", zoznamKategórií);

					súbor.zapíšRiadok(riadok);
				}

				súbor.zavri();
				šablóna.zavri();
			}
			catch (Exception e)
			{
				System.err.println(e.getMessage());
			}

			boolean altRow = true;

			for (Map.Entry<String, ZoznamMetód>
				kategória : kategórieMetód.entrySet())
			{
				String názovKategórie = kategória.getKey();
				if (názovKategórie.equals("Ignorované")) continue;
				Matcher match = extrahujIDKategórie.matcher(názovKategórie);
				if (match.find()) názovKategórie = match.replaceFirst("");


				try
				{
					súbor.otvorNaZápis("kategorie-metod.html", true);
					šablóna.otvorNaČítanie("sablona-kategorie-tabulka-nova-kategoria.txt");

					String riadok, id = kategória.getValue().id;
					while (null != (riadok = šablóna.čítajRiadok()))
					{
						riadok = riadok.replace("$CATEGORYID", id).
							replace("$CATEGORY", názovKategórie);
						súbor.zapíšRiadok(riadok);
					}

					súbor.zavri();
					šablóna.zavri();
				}
				catch (Exception e)
				{
					System.err.println(e.getMessage());
				}

				for (ZáznamOMetóde metóda : kategória.getValue())
				{
					try
					{
						súbor.otvorNaZápis(
							"kategorie-metod.html", true);
						šablóna.otvorNaČítanie(
							"sablona-kategorie-tabulka-polozka.txt");

						String riadok;
						while (null != (riadok = šablóna.čítajRiadok()))
						{
							riadok = riadok.
								replace("$IDNUMBER", "id" +
									identifikátorRiadkaKategórie).
								replace("$ALTROW", (altRow ? "alt" : "row")).
								replace("$TYPE", metóda.typ).
								replace("$LINK", metóda.odkaz).
								replace("$NAME", metóda.názov).
								replace("$PARAMS", metóda.parametre).
								replace("$DESC", metóda.stručnýOpis);

							súbor.zapíšRiadok(riadok);
						}

						súbor.zavri();
						šablóna.zavri();

						++identifikátorRiadkaKategórie;
						altRow = !altRow;
					}
					catch (Exception e)
					{
						System.err.println(e.getMessage());
					}
				}
			}


			try
			{
				Súbor.pripoj("sablona-kategorie-tabulka-koniec.txt",
					"kategorie-metod.html");
			}
			catch (Exception e)
			{
				System.err.println(e.getMessage());
			}
		}
	}

	private void dokočiSúborKategórií()
	{
		try
		{
			Súbor.pripoj("sablona-kategorie-koniec.txt",
				"kategorie-metod.html");
		}
		catch (Exception e)
		{
			System.err.println(e.getMessage());
		}
	}


	private void uložSúbor()
	{
		try
		{
			súbor.otvorNaZápis(názovSúboru);

			for (String riadok : obsahSúboru)
			{
				súbor.zapíšRiadok(riadok);
			}

			súbor.zavri();
		}
		catch (Exception e)
		{
			System.err.println(e.getMessage());
		}
	}


	private final static Pattern preveďHTMLnaPHP_zmažHTMLPríponu =
		Pattern.compile("\"(?!http)([^\"]+)\\.html");
	private final static Pattern preveďHTMLnaPHP_vypniClassUseOkazy =
		Pattern.compile("(<a.*?)( href=\".*?\")(.*?>)");

	private static Súbor html = null, php = null;
	private static void preveďHTMLnaPHP(String názovSúboru)
		throws java.io.IOException
	{
		if (null == html) html = new Súbor();
		if (null == php) php = new Súbor();

		boolean classUse = -1 != názovSúboru.indexOf("class-use");
		String čítanie;

		html.otvorNaČítanie(názovSúboru + ".html");
		php.otvorNaZápis(názovSúboru + ".php");
		while (null != (čítanie = html.čítajRiadok()))
		{
			Matcher match = preveďHTMLnaPHP_zmažHTMLPríponu.matcher(čítanie);
			String zápis = match.replaceAll("\"$1");

			if (classUse)
			{
				zápis = zápis
					.replace("<body>", "<body><div style=\"background-" +
						"color: #fee; border: 2px solid red; color: #800; " +
						"opacity: 0.95; margin: 0px; padding: 20px; " +
						"position: fixed; top: 50px; right: 0px; z-index: " +
						"500;\"><b>Upozornenie!</b> Použitie tried <del " +
						"style=\"display:none\">zatiaľ </del>nie " +
						"je spracované. Tento dokument je len " +
						"informačný.</div>")
					.replace("../../", "../");
				match = preveďHTMLnaPHP_vypniClassUseOkazy.matcher(zápis);
				zápis = match.replaceAll("$1$3<!--$2 -->");
			}

			zápis = zápis
				.replace("<a href=\"class-use",
					"<a target=\"_blank\" href=\"class-use")
				.replace("\"index", "\".");

			php.zapíšRiadok(zápis);
		}

		html.zavri();
		php.zavri();

		Súbor.vymaž(názovSúboru + ".html");
	}


	public static void main(String[] args)
	{
		try {

		/*if (false)
		{
			System.err.println("Je mi ľúto, ale 9. 8. 2018, tesne po " +
				"obrovskej revízii programovacieho rámca (resp. po jeho " +
				"transformácii z knižnice na rámec) prestala táto " +
				"utilita reagovať po spracovaní triedy Konstanty.java.");
			Svet.koniec();
			return;
		}*/

		// Skontroluj zvuky. Čo to znamená „nepodporovaná“?
		// Uveď do VlastnýTvar aj odkaz na kreslenie preťažením…

		// Zvuk zvuk = Svet.čítajZvuk(
		// 	"/Users/romanhorvath/_Sync/Vyucba/Denne/2011 - " +
		// 	"Zima/ST1-odovzdané/Meliš/applause.wav");
		// System.out.println(zvuk.hlasitosťNepodporovaná());

		Svet.skry();
		new RoboDoc();
		poslednéPrevody(); // Finalizácia (implementovaná nižšie).
		} finally { Svet.koniec(); }
	}


	private static void poslednéPrevody()
	{
		for (String meno : new String[] {"kategorie-metod", "Zvuk",
			"Zoznam", "zoznam-zmien", "Zoznam-ObratenyIterator",
			"Zoznam-ObraatenyyIteraator", "ZmenaCelejObrazovky", "Vlnenie",
			"UUdajeUdalostii", "Uhol", "UdajeUdalosti", "Tlacidlo",
			"Tlaccidlo", "SVGPodpora", "SVGPodpora-Transformacia",
			"SVGPodpora-Transformaacia", "Svet", "Svet-PrikazovyRiadok",
			"Svet-PriikazovyyRiadok",
			"Svet-PriikazovyyRiadok-PresmerovanieVyystupu", "Suubor",
			"Subor", "Spojenie", "Smer", "Skript", "Skript-PremenneSkriptu",
			"Skript-PremenneeSkriptu", "serialized-form", "Schranka",
			"Schraanka", "RolovaciaLista", "RolovaciaLissta", "Rozmery",
			"Rozmer", "Roj", "Roj-Smernik", "Roj-Smerniik", "Roj-Bod",
			"Rad", "Priehlladnostt", "Priehladnost", "PoznamkovyBlok",
			"PoznaamkovyyBlok", "PolozzkaPonuky", "PolozkaPonuky",
			"Poloha", "Plazma", "Platno", "Plaatno", "Pismo", "Piismo",
			"package-use", "ObsluhaUdalostii",
			"ObsluhaUdalosti", "Obrazok", "Obraazok", "Oblastt", "Oblast",
			"KreslenieTvaru", "KontextovaPonuka", "KontextovaPolozka",
			"KontextovaaPonuka", "KontextovaaPolozzka", "Konstanty",
			"Konsstanty", "Klaves", "Klaaves", "index", "hodnoty-konstant",
			"GRobotException", "GRobotException-Dennik",
			"GRobotException-Denniik", "GRobotException-Chyba", "GRobot",
			"GRobot-Spojnica", "Farebnostt", "Farebnost", "Farba", "CCastica",
			"Castica", "Bod", "Archiv", "Archiiv", "Tlacc-Slovo",
			"Tlacc-Fragment", "Tlacc-Parametre", "Tlacc-RiadokSlov",
			"Tlacc-BlokSlov", "Tlacc", "Tlac"}) try
		{
			System.out.println("Dodatočné spracovanie súboru: robodoc/" +
				meno + " (prevod HTML súboru na PHP súbor, ktorý bude " +
				"dostupný „bez prípony“)");
			preveďHTMLnaPHP("robodoc/" + meno);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

		// class-use
		for (String meno : new String[] {"Archiv", "Archiiv", "Bod",
			"Castica", "Farba", "Farebnost", "Farebnostt",
			"GRobot-Spojnica", "GRobot", "GRobotException-Chyba",
			"GRobotException-Dennik", "GRobotException-Denniik",
			"GRobotException", "Klaves", "Klaaves", "Konstanty",
			"KontextovaPolozka", "KontextovaPonuka",
			"KontextovaaPolozzka", "KontextovaaPonuka", "Konsstanty",
			"KreslenieTvaru", "Oblast", "Oblastt", "Obrazok", "Obraazok",
			"ObsluhaUdalosti", "ObsluhaUdalostii", "Pismo", "Platno",
			"Plazma", "Plaatno", "Poloha", "PolozkaPonuky",
			"PolozzkaPonuky", "PoznamkovyBlok", "PoznaamkovyyBlok",
			"Priehladnost", "Priehlladnostt", "Piismo", "Rad",
			"Roj-Smernik", "Roj-Smerniik", "Roj-Bod", "Roj",
			"RolovaciaLista", "RolovaciaLissta", "Rozmery", "Rozmer",
			"Schranka", "Schraanka",
			"Skript-PremenneSkriptu", "Skript-PremenneeSkriptu",
			"Skript", "Smer", "Spojenie", "Subor",
			"Svet-PrikazovyRiadok",
			"Svet-PriikazovyyRiadok-PresmerovanieVyystupu",
			"Svet-PriikazovyyRiadok", "Svet", "SVGPodpora-Transformacia",
			"SVGPodpora-Transformaacia", "SVGPodpora", "Suubor",
			"Tlacidlo", "Tlaccidlo", "Tlacc-Slovo", "Tlacc-Fragment",
			"Tlacc-Parametre", "Tlacc-RiadokSlov", "Tlacc-BlokSlov",
			"Tlacc", "Tlac", "UdajeUdalosti", "Uhol", "Vlnenie",
			"ZmenaCelejObrazovky", "Zoznam-ObratenyIterator",
			"Zoznam-ObraatenyyIteraator", "Zoznam", "Zvuk",
			"UUdajeUdalostii", "CCastica"}) try
		{
			System.out.println("Dodatočné spracovanie súboru: " +
				"robodoc/class-use/" + meno + " (prevod HTML súboru " +
				"na PHP súbor, ktorý bude dostupný „bez prípony“)");
			preveďHTMLnaPHP("robodoc/class-use/" + meno);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

		try
		{
			if (!Súbor.premenuj(priečinok +
				"/resources/rastlinka-svg.html",
				priečinok + "/resources/rastlinka-svg.php"))
			{
				System.err.println("Zlyhalo premenovanie súboru: " +
					"rastlinka-svg.html" + ". Asi už jestvuje " +
					"prislúchajúci .php súbor. Overte, či má súbor " +
					"aktuálny obsah.");
				if (!Súbor.vymaž(priečinok +
					"/resources/rastlinka-svg.html"))
					System.err.println("  Súbor nemohol byť vymazaný. " +
						"Môže vznikať redundancia.");
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

		try
		{
			if (!Súbor.premenuj(priečinok +
				"/resources/apache-licence-2.0.html",
				priečinok + "/resources/apache-licence-2.0.php"))
			{
				System.err.println("Zlyhalo premenovanie súboru: " +
					"apache-licence-2.0.html" + ". Asi už jestvuje " +
					"prislúchajúci .php súbor. Overte, či má súbor " +
					"aktuálny obsah.");
				if (!Súbor.vymaž(priečinok +
					"/resources/apache-licence-2.0.html"))
					System.err.println("  Súbor nemohol byť vymazaný. " +
						"Môže vznikať redundancia.");
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}


// </?\pL([^>](?!$))+[^>]$

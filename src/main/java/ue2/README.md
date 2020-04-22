## Übung 2

### Doppelt verkettete Liste

#### Ziel:

Genau wie in Übung 1, soll eine verkettete Liste selbst implementiert werden, dieses Mal aber eine doppelt verkettete. Die Profilingergebnisse sollen mit denen aus Übung 1 verglichen werden.

### Profilingergebnisse:

![Profilingergebnisse](Profilingergebnisse2.png) 


#### Fazit:

Die doppelt verkettete Liste ist deutlich schneller als jede meiner Implementierungen der einfach verketteten Liste und sie verbraucht auch weniger Speicher. 
Zum einen liegt das sicherlich daran, dass beim Profiling das Ausgeben von Ende bis Anfang deutlich schneller geht, da man jetzt nicht mehr jedes Mal einen Zugriff mit der `get`-Methode machen muss, sondern die doppelt verkettete Liste rückwärts durchlaufen kann. 
Außerdem ist auch die `append`-Methode deutlich schneller. Beim Einfügen am Ende bei der einfach verketteten Liste muss jedes Mal die gesamte Liste mit einer Schleife durchlaufen werden, um das letzte Element zu finden. Bei der doppelt verketteten Liste können wir direkt auf das letzte Element zugreifen, was dementsprechend deutlich schneller geht. 
Um das Einfügen in die einfach verkettete Liste schneller zu machen, könnte man statt der `append`-Methode auch einfach die `prepend`-Methode verwenden und somit auch das ständige Durchlaufen der Liste vermeiden, aber auch dann ist die dopppelt Verkettete Liste immer noch schneller.

### Menge

#### Ziel:

Es soll nun basierend auf der einfach oder doppelt verketteten Liste ein Set implementiert werden sowie eine zweite optimierte Implementation davon. Beides soll wieder profiled und verglichen werden.

#### Optimierungsideen:

tbd

#### Profilingergebnisse:

tbd

#### Fazit:

tbd
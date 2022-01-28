# ws-swt-007
Ein JavaFx Bombermanspiel
Um das Spiele in Eclipse zu starten muessen Sie erstmal JavaFX in eclipse herunterladen und die Biblieotheken einbinden
in dieser Link finden Sie die Schritte fuer die JavaFx installation in Eclipse

https://youtu.be/Dbb69NiQHso

Das Spiel ist unter Windows mit der Bat-File runnable, Sie muessen nur sicherstellen, dass Sie die neuste Version von Java installiert haben (in CMD java -version)
Wenn Sie das Spiel im lockalen Netzwerk spielen moechtet, dann sollten Sie das Server.jar starten und die IP-Adresse des Hostes in der Datei "ServerIp.csv" oder "ServerIp.txt" schreiben (bei den anderen Spieler und nicht bei der Host)

wenn Sie das Spiel im Terminal starten, dann muessen sie ein Argument fuer die Biblieotheken  hinzufuegen:
java -jar --module-path "java\openjfx-17.0.1_windows-x64_bin-sdk\javafx-sdk-17.0.1\lib" --add-modules javafx.controls,javafx.fxml,javafx.media  Bomberman.jar
Hinweis:  bitte das Path richtig eingeben falls es bei Ihnen anders ist.

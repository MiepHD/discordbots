#1.Importstatements
import discord
from datetime import datetime
import json
#2.Methoden
#2.1.Überprüfe, ob Nummer richtig ist
def is_number(string):
    try:
        int(string)
        return True
    except ValueError:
        pass
    return False
#2.2.Überprüft, ob Teiler von
def istTeilerVon(zahl1, zahl2):
    rest = zahl1 % zahl2
    if rest == 0:
        return True
    else:
        return False
#2.3.Zeichne Spielfeld
def zeichneBoard():
    i = 0
    row = 2
    spielfeld_text = ":black_large_square::regional_indicator_a::regional_indicator_b::regional_indicator_c::regional_indicator_d::regional_indicator_e::regional_indicator_f::regional_indicator_g::regional_indicator_h::regional_indicator_i:\n:one:"
    for x in board:
        if istTeilerVon(i + 1, 9):
            spielfeld_text = str(spielfeld_text + x + "\n" + zahlen[row])
            row = row + 1
        else:
            spielfeld_text = str(spielfeld_text + x)
        i = i + 1
    return spielfeld_text
#3.Variablen deklarieren
#3.1.Veränderbare
white = ":white_large_square:"
old_message = 0
player1_skip = False
player2_skip = False
board = []
player1 = ""
player2 = ""
turn = ""
bigboard = ["p0", "p0", "p0", "p0", "p0", "p0", "p0", "p0", "p0"]
data = []
#3.2.Unveränderbare Variablen
green = ":green_square:"
kreis = ":o2:"
kreuz = ":regional_indicator_x:"
zahlen = ["", "", ":two:", ":three:", ":four:", ":five:", ":six:", ":seven:", ":eight:", ":nine:", ""]
#3.2.1.Feldwahl
feldgruppen = [[0, 3, 6, 27, 30, 33, 54, 57, 60],
               [1, 4, 7, 28, 31, 34, 55, 58, 61],
               [2, 5, 8, 29, 32, 35, 56, 59, 62],
               [9, 12, 15, 36, 39, 42, 63, 66, 69],
               [10, 13, 16, 37, 40, 43, 64, 67, 70],
               [11, 14, 17, 38, 41, 44, 65, 68, 71],
               [18, 21, 24, 45, 48, 51, 72, 75, 78],
               [19, 22, 25, 46, 49, 52, 73, 76, 79],
               [20, 23, 26, 47, 50, 53, 74, 77, 80]]
#3.2.2.Felder
map_feldgruppen = [[0, 1, 2,
         9, 10, 11,
         18, 19, 20],
        [3, 4, 5,
          12, 13, 14,
          21, 22, 23],
        [6, 7, 8,
         15, 16, 17,
         24, 25, 26],
        [27, 28, 29,
         36, 37, 38,
         45, 46, 47],
        [30, 31, 32,
         39, 40, 41,
         48, 49, 50],
        [33, 34, 35,
         42, 43, 44,
         51, 52, 53],
        [54, 55, 56,
         63, 64, 65,
         72, 73, 74],
        [57, 58, 59,
         66, 67, 68,
         75, 76, 77],
        [60, 61, 62,
         69, 70, 71,
         78, 79, 80]]
#3.2.3.Gewinnvorraussetzungen
winningConditions = [[0, 1, 2],
                    [3, 4, 5],
                    [6, 7, 8],
                    [0, 3, 6],
                    [1, 4, 7],
                    [2, 5, 8],
                    [0, 4, 8],
                    [2, 4, 6]]
class MyClient(discord.Client):
    #start
    async def on_ready(self):
        global data
        f = open("ttt.txt", "r")
        line = f.read()
        line = line.replace('[', '')
        line = line.replace(']', '')
        line = line.split(", ")
        for x in line:
            if x != '\n':
                data.append(int(x))
        f.close()
        print(data)
        print("Bot gestartet")
    async def on_message(self, message):
        #variablen globalisieren
        global old_message
        global player1_skip
        global player2_skip
        global player1
        global player2
        global turn
        global egal
        global board
        global bigboard
        global white
        global data
        #hat bot geschickt?
        if message.author == client.user:
            return
        #toggledarkmode
        if message.content.startswith("*to"):
            if board == []:
                if white == ":white_large_square:":
                    white = ":black_large_square:"
                else:
                    white = ":white_large_square:"
            else:
                await message.channel.send("Der Modus ist nur änderbar, solange kein Spiel läuft.")
        #Freigeben
        if message.content.startswith("*f"):
            if message.author == player1:
                await message.channel.send("Schreibe *ttt, um für <@" + str(player1.id) + "> weiterzuspielen.")
                player1 = "frei"
            elif message.author == player2:
                await message.channel.send("Schreibe *ttt, um für <@" + str(player2.id) + "> weiterzuspielen.")
                player2 = "frei"
            else:
                await message.channel.send("Du bist nicht im Spiel")
        if message.content.startswith("*anleitung"):
            await message.channel.send("Das Spielfeld ist in 9 Felder unterteilt, die wiederum in 9 Felder unterteilt sind.\nSetzt eine Person an eine Stelle, kann die andere Person im großen Feld an der selben Stelle, in der die andere Person im kleinen Feld gesetzt hat, etwas setzen. Wenn man in einem kleinen Feld gewinnt, bekommt man das kleine Feld.\nDamit man das Spiel gewinnt, muss man das große Spielfeld gewinnen.")
        if message.content.startswith("*help"):
            await message.channel.send("*tictactoe oder *ttt, um ein Spiel anzufragen oder einem offenen beizutreten.\n*place [Großbuchstabe][Zahl] (ohne Klammern)\n*abbrechen, um ein laufendes Spiel abzubrechen\n*help, um diese Hilfe aufzurufen\n*freigeben, um den eigenen Platz freizugeben. Dann kann eine andere Person mit *tictactoe oder *ttt beitreten oder selber wieder beitreten.\n*anleitung ruft die Anleitung auf.\n*toggledarkmode wechselt zwischen hell und dunkel.")
        #Abbrechen
        if message.content.startswith("*a"):
            if message.author == player1:
                if player1 == player2:
                    player2_skip = True
                player1_skip = True
                await message.channel.send("<@" + str(player2.id) + "> <@" + str(player1.id) + "> möchte abbrechen")
            elif message.author == player2:
                player2_skip = True
                await message.channel.send("<@" + str(player1.id) + "> <@" + str(player2.id) + "> möchte abbrechen")
            else:
                await message.channel.send("Du bist nicht im Spiel")
            if player1_skip and player2_skip:
                old_message = False
                player1_skip = False
                player2_skip = False
                old_message = 0
                board = []
                player1 = ""
                player2 = ""
                turn = ""
                bigboard = ["p0", "p0", "p0", "p0", "p0", "p0", "p0", "p0", "p0"]
                await message.channel.send("Unentschieden!")
        #Place-Befehl?
        if message.content.startswith("*p"):
            #Ist Spieler dran
            if turn == "p1" and message.author == player1:
                pass
            elif turn == "p2" and message.author == player2:
                pass
            else:
                await message.channel.send("Du bist nicht an der Reihe")
                return
            #Zahl aus Nachricht extrahieren
            eingabe = message.content.split(" ")#*p A2 => ["*p", "A2"]
            eingabe = eingabe[1]#["*p", "A2"] => "A2"
            buchstabe = eingabe[0]#Buchstabe
            buchstabenziffer = ord(buchstabe) - 65
            ziffer = int(eingabe[1])-1#Ziffer
            
            #number = int(number[1]) - 1
            number = ziffer * 9 + buchstabenziffer
            if board[number] == green:
                #in welchem Feld (target)
                for n in range(0, 9):
                    if number in feldgruppen[n]:
                        target = n
                spalte = (target % 3)
                zeile = (target - spalte)/3
                startfeld = zeile*27+spalte*3
                if bigboard[target] == "p0":
                    #Weiß zeichnen
                    for n in range(0, 81):
                        if board[n] == green:
                            board[n]= white
                    #Grün zeichnen
                    for x in range(0, 3):
                        for y in range(0, 3):
                            zielfeld = int(startfeld + x + y * 9)
                            if board[zielfeld] == white:
                                board[zielfeld] = green
                else:
                    #Weiß zeichnen
                    for n in range(0, 81):
                        if board[n] == white:
                            board[n]= green
                #Punkt setzen
                if turn == "p1" :
                   board[number] = kreis
                   turn = "p2"
                   symbol = kreis
                else:
                    board[number] = kreuz
                    turn = "p1"
                    symbol = kreuz
                #Überprüfe gewinn
                for n in range(0, 9):
                    if number in map_feldgruppen[n]:
                        new_target = n
                spalte = (new_target % 3)
                zeile = (new_target - spalte)/3
                startfeld = zeile*27+spalte*3
                kleinfeldgewonnen = False
                spielgewonnen = False
                for n in range(0, 8):
                    if board[int(startfeld) + map_feldgruppen[0][winningConditions[n][0]]] == symbol :
                        if board[int(startfeld) + map_feldgruppen[0][winningConditions[n][1]]] == symbol :
                            if board[int(startfeld) + map_feldgruppen[0][winningConditions[n][2]]] == symbol :
                                kleinfeldgewonnen = True
                                #gewonnen
                                print("Kleines Feld gewonnen")
                                if turn == "p1":
                                    bigboard[new_target] = "p2"
                                    symbol = "p2"
                                else:
                                    bigboard[new_target] = "p1"
                                    symbol = "p1"
                                if target == new_target:
                                    #Weiß zeichnen
                                    for n in range(0, 81):
                                        if board[n] == white:
                                            board[n]= green
                                #Feld ausfüllen
                                for x in range(0, 3):
                                    for y in range(0, 3):
                                        zielfeld = int(startfeld + x + y * 9)
                                        if board[zielfeld] == white or board[zielfeld] == green:
                                            if turn == "p2":
                                                board[zielfeld] = ":red_square:"
                                            else:
                                                board[zielfeld] = ":blue_square:"
                                #check  conditions
                                for n in range(0, 8):
                                    if bigboard[winningConditions[n][0]] ==  symbol:
                                        if bigboard[winningConditions[n][1]] == symbol :
                                            if bigboard[winningConditions[n][2]] == symbol:
                                                spielgewonnen = True
                                                spielfeld_text = zeichneBoard()
                                                if old_message != 0:
                                                    await old_message.delete()
                                                await message.channel.send(spielfeld_text)
                                                #Gewinnernachricht
                                                if symbol == "p1":
                                                    await message.channel.send("<@" + str(player1.id) + "> hat gewonnen")
                                                    p1_punkte = 6
                                                    p2_punkte = 0
                                                else:
                                                    await message.channel.send("<@" + str(player2.id) + "> hat gewonnen")
                                                    p2_punkte = 6
                                                    p1_punkte = 0
                                                if player1 == player2:
                                                    pass
                                                else:
                                                    for x in bigboard:
                                                        if x == "p1":
                                                            p1_punkte = p1_punkte + 1
                                                        elif x == "p2":
                                                            p2_punkte = p2_punkte + 1
                                                    if player1.id in data:
                                                        pass
                                                    else:
                                                        data.append(player1.id)
                                                        data.append(0)
                                                    if player2.id in data:
                                                        pass
                                                    else:
                                                        data.append(player2.id)
                                                        data.append(0)
                                                    for n in range(0, len(data)):
                                                            if player1.id == data[n]:
                                                                data[n + 1] = data[n + 1] + p1_punkte
                                                                p1_punkte_gesamt = data[n + 1]
                                                            if player2.id == data[n]:
                                                                data[n + 1] = data[n + 1] + p2_punkte
                                                                p2_punkte_gesamt = data[n + 1]
                                                    f = open("ttt.txt", "w")
                                                    f.write(str(data))
                                                    f.close
                                                    await message.channel.send("Player eins hat " + str(p1_punkte) + " Punkte erspielt und hat damit jetzt insgesamt " + str(p1_punkte_gesamt))
                                                    await message.channel.send("Player zwei hat " + str(p2_punkte) + " Punkte erspielt und hat damit jetzt insgesamt " + str(p2_punkte_gesamt))
                                                player1_skip = False
                                                player2_skip = False
                                                old_message = False
                                                player1 = ""
                                                player2 = ""
                                                board = []
                                                turn = ""
                                                bigboard = ["p0", "p0", "p0", "p0", "p0", "p0", "p0", "p0", "p0"]
                besetzt = 0
                if not spielgewonnen:
                    spielfeld_text = zeichneBoard()
                    for element in bigboard:
                        if element != "p0":
                            besetzt = besetzt + 1
                    if besetzt == 9:
                        player1_skip = False
                        player2_skip = False
                        old_message = False
                        board = []
                        player1 = ""
                        player2 = ""
                        turn = ""
                        bigboard = ["p0", "p0", "p0", "p0", "p0", "p0", "p0", "p0", "p0"]
                        await message.channel.send("Unentschieden!")
                besetzt = 0
                if not kleinfeldgewonnen:
                    for x in range(0, 3):
                        for y in range(0, 3):
                            zielfeld = int(startfeld + x + y * 9)
                            if board[zielfeld] == kreis or board[zielfeld] == kreuz:
                                besetzt = besetzt + 1
                    if besetzt == 9:
                        bigboard[new_target] = "/"
                        print("BigBoard: " + str(bigboard))
                if old_message != 0:
                    await old_message.delete()
                old_message = await message.channel.send(spielfeld_text)
            else:
                await message.channel.send("Feld "+ str(number) +" nicht auswählbar. \n Board: " + board[number])
        #Ist Befehl tiktaktoe?
        if message.content.startswith("*tictactoe") or message.content.startswith("*ttt"):
            if player1 == "frei":
                player1 = message.author
                await message.channel.send("Player eins ist <@" + str(message.author.id) + ">")
            elif player2 == "frei":
                player2 = message.author
                await message.channel.send("Player zwei ist <@" + str(message.author.id) + ">")
            #Spielerzuordnung
            elif player1 == "":
                player1 = message.author
                await message.channel.send("Player eins ist <@" + str(message.author.id) + ">")
            elif player2 == "":
                player2 = message.author
                await message.channel.send("Player zwei ist <@" + str(message.author.id) + ">")
                #Spielstart
                #Generieren von Array
                begonnen = True
                i = 0
                while i <= 80:
                    board.append(green)
                    i = i + 1
                #Setzen von Anfangsspieler
                turn = "p1"
                #Setzen, dass man Feld frei wählen kann
                egal = True
                #Schreiben des Spielfelds in String
                spielfeld_text = zeichneBoard()
                #Absenden des Spielfelds
                if old_message != 0:
                    await old_message.delete()
                old_message = await message.channel.send(spielfeld_text)
            #beide belegt
            else:
                await message.channel.send("Es sind bereits alle Plätze besetzt")
#Ausgabe mit Datum
old_print = print
def timestamped_print(*args, **kwargs):
    old_print(datetime.now(), *args, **kwargs)
print = timestamped_print
#Starte Client
client = MyClient()
client.run("OTQ3NzYxOTgzNjIzNjY3NzEy.Yhx-IQ.keibziV-IfwW_LZstWe_x2GeEyo")
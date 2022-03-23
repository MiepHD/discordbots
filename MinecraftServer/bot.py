import discord
import subprocess
import socket
from subprocess import Popen
from mctools import QUERYClient
from mctools import RCONClient
from mctools import PINGClient
class MyClient(discord.Client):
    #Login
    async def on_ready(self):
        print("Bot gestartet")

    #Nachricht erhalten
    async def on_message(self, message):
        standart = str("Nachricht von " + str(message.author) + " enthält " + str(message.content))
        server = 0
        command = 0
        if message.author == client.user:
            print(str(message.content))
            return
        if message.author.id == 000000000000000000:
            server = 1
        if message.author.id == 000000000000000000:
            server = 1
        if message.author.id == 000000000000000000:
            server = 1
        if message.author.id == 000000000000000000:
            server = 1
        if server == 1:
            #Help
            if message.content == "?help":
                print(standart)
                await message.channel.send("Starte Server: ?start\nStoppe Server: ?stop\nBackup: ?backup\nStatus des Server: ?status\nBefehl: ?server /BEFEHL\nPing: ?server ping\nSpielerliste: ?server stats players list\nSpieleranzahl: ?server stats players count\nIP: ?server stats ip\nVersion: ?server stats version\nBeende den Bot: ?exit\nDiese Liste: ?help\nFür Hilfe: ?msg NACHRICHT")
            #Server Commands
            elif message.content == "?backup":
                print(standart)
                P = Popen("backup.bat")
                await message.channel.send('Backup wird erneuert')
                P.wait()
                await message.channel.send('Backup fertig')
            elif message.content.startswith("?server"):
                print(standart)
                sock = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
                res = sock.connect_ex(('localhost', 25565))
                sock.close()
                print(res)
                if res == 0:
                #Server stats
                    if message.content.startswith("?server stats"):
                        query = QUERYClient('localhost', port=25565)
                        stats = str(query.get_full_stats())
                        print(str(stats))
                        splitted_stats = stats.split(",")
                        if message.content.startswith("?server stats players"):
                            #Spieleranzahl
                            if message.content == "?server stats players count":
                                splitted_online = str(splitted_stats[6]).split("'")
                                await message.channel.send(splitted_online[3])
                            #Spielerliste
                            elif message.content == "?server stats players list":
                                splitted_online = stats.split("'players': [")
                                splitted_online = str(splitted_online[1])
                                splitted_online = splitted_online.replace("'","")
                                splitted_online = splitted_online.replace("\\x1b[0m","")
                                splitted_online = splitted_online.replace("]}","")
                                splitted_online = splitted_online.replace(", ","\n")
                                await message.channel.send(splitted_online)
                            else:
                                await message.channel.send('Fehler. ?server stats players [count|list]')
                        #IP
                        elif message.content == "?server stats ip":
                            splitted_ip = str(splitted_stats[9]).split("'")
                            await message.channel.send(splitted_ip[3])
                        #Version
                        elif message.content == "?server stats version":
                            splitted_ip = str(splitted_stats[3]).split("'")
                            await message.channel.send(splitted_ip[3])
                        else:
                            await message.channel.send('Fehler. ?server stats [players|ip|version]')
                    #Befehl ausführen
                    elif message.content.startswith("?server /"):
                        command = str(message.content).split("/")
                        command = command[1]
                        await message.channel.send('RCON startet')
                        rcon = RCONClient('localhost', port=25575)
                        success = rcon.login('XXXXXXXXXXXXXXXXXXXX')
                        if success == True:
                            await message.channel.send('RCON verbunden')
                        else:
                            await message.channel.send('Fehler beim Verbinden')
                        auth = rcon.is_authenticated()
                        if auth == True:
                            await message.channel.send('RCON authentifiziert')
                            response = rcon.command(command, length_check=False)
                            print(response)
                            await message.channel.send("Befehl " + command + " wird ausgeführt")
                            response = response.replace('','')
                            response = response.replace('[33m','')
                            response = response.replace('[33;1m','')
                            response = response.replace('[0m','')
                            if response != "":
                                await message.channel.send(response)
                        else:
                            await message.channel.send("Fehler beim authentifizieren")
                    #Server ping/backup
                    elif message.content == "?server ping":
                        ping = PINGClient('miephds.hopto.org')
                        elapsed = ping.ping()
                        await message.channel.send(elapsed + "ms")
                    else:
                        await message.channel.send('Fehler. ?server [stats|/|ping]')
                else:
                    await message.channel.send('Server läuft nicht')
            elif message.content == "?start":
                print(standart)
                sock = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
                res = sock.connect_ex(('miephds.hopto.org', 25565))
                sock.close()
                print(res)
                if res == 0:
                    await message.channel.send('Server ist bereits gestartet')
                else:
                    P = Popen("backup.bat")
                    await message.channel.send('Backup wird erneuert')
                    P.wait()
                    await message.channel.send('Backup fertig')
                    P = Popen("..\start.bat")
                    await message.channel.send('Server startet')
            elif message.content == "?stop":
                print(standart)
                Popen("stopserver.bat")
                await message.channel.send('Server stoppt')
            elif message.content.startswith("?msg"):
                print(standart)
                msg = str(message.content).split("msg ")
                msg = msg[1]
                channel = client.get_channel(000000000000000000)
                await channel.send("Nachricht von " + str(message.author) + "\n" + msg)
            elif message.content == "?exit":
                print(standart)
                await message.channel.send("Script wird beendet")
                exit()
            elif message.content == "?status":
                print(standart)
                sock = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
                res = sock.connect_ex(('localhost', 25565))
                sock.close()
                print(res)
                if res == 0:
                    await message.channel.send("Server ist online")
                else:
                    await message.channel.send("Server ist offline")
            elif message.content.startswith("?send !"):
                print(standart)
                nachricht = str(message.content).split("!")
                nachricht = nachricht[1]
                channel = client.get_channel(000000000000000000)
                await channel.send(nachricht)
                channel = client.get_channel(000000000000000000)
                await channel.send(nachricht)
            else:
                if message.content != "?":
                    if message.content.startswith("?"):
                        print(standart)
                        await message.channel.send('Fehler. ?[msg|server|start|stop|status|backup|help]')
from datetime import datetime
old_print = print
def timestamped_print(*args, **kwargs):
  old_print(datetime.now(), *args, **kwargs)
print = timestamped_print
client = MyClient()
client.run("token")

import discord
import socket
from mctools import QUERYClient

class MyClient(discord.Client):
    async def on_ready(self):
        sock = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
        res = sock.connect_ex(('localhost', 25565))
        sock.close()
        print(res)
        if res == 0:
            query = QUERYClient('localhost', port=25565)
            print(query.get_full_stats())
            stats = str(query.get_full_stats())
            splitted_stats = stats.split(",")
            splitted_online = str(splitted_stats[6]).split("'")
            playernum = splitted_online[3]
            print("playernum: " + playernum)
            if playernum == "0":
                channel = client.get_channel(874677174882431067)
                await channel.send("Niemand ist auf dem Server. Um den Server zu stoppen ?stop")
                channel = client.get_channel(697105486180515870)
                await channel.send("Niemand ist auf dem Server. Um den Server zu stoppen ?stop")
                print("Niemand ist auf dem Server")
            else:
                print("Jemand ist auf dem Server")
                print("playernum: " + playernum)
        else:
            print("Der Server läuft nicht")
        exit()

client = MyClient()
client.run("token")

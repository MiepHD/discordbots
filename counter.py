import discord
from datetime import datetime
def is_number(string):
    try:
        int(string)
        return True
    except ValueError:
        pass
    return False
zahl = 0
old_author = ""
highscore = 0
class MyClient(discord.Client):
    async def on_ready(self):
        global zahl
        global highscore
        f = open("values.txt", "r")
        line = f.read()
        values = line.split(";")
        zahl = int(values[0])
        highscore = int(values[1])
        f.close()
        print("Bot gestartet")
    
    async def on_message(self, message):
        global zahl
        global old_author
        global highscore
        if message.author == client.user:
            print(str(message.content))
            return
        if is_number(message.content) == True:
            if message.author == old_author:
                emoji = "\N{WARNING SIGN}"
                await message.add_reaction(emoji)
                return
            else:
                if message.content == str(zahl + 1):
                    zahl = zahl + 1
                    old_author = message.author
                    if zahl <= highscore:
                        emoji = "\N{THUMBS UP SIGN}"
                    else:
                        emoji = "\U0001f44f"
                        highscore = zahl
                else:
                    emoji = "\N{THUMBS DOWN SIGN}"
                    zahl = 0
                    old_author = ""
                f = open("values.txt", "w")
                values = str(zahl) + ";" + str(highscore)
                f.write(values)
                f.close()
                await message.add_reaction(emoji)
old_print = print
def timestamped_print(*args, **kwargs):
    old_print(datetime.now(), *args, **kwargs)
print = timestamped_print
client = MyClient()
client.run("OTI4OTYzODEyNTg4MTM4NTE2.Ydga-A.A44Vq8uV5ww4EDCa-peSXSbrEMA")
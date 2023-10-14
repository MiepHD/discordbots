from selenium import webdriver
from selenium.webdriver.common.keys import Keys
from selenium.webdriver.common.by import By
from datetime import datetime
import time
import os
import glob
import hashlib
import discord

class MyClient(discord.Client):
    #Login
    async def on_ready(self):
        print("Bot gestartet")
        channel = client.get_channel(1011595712976400384)

        old_hex = ""
        PATH = "L:\Workspaces\epicbundle scraping\lib\chromedriver.exe"
        downloadPath = "L:\Workspaces\memebot\memes"
        """ Helper function that creates a new Selenium browser """
        options = webdriver.ChromeOptions()
        options.binary_location = "C:/Program Files/Google/Chrome Beta/Application/chrome.exe"
        prefs = {}
        prefs["profile.default_content_settings.popups"] = 0
        prefs["download.default_directory"] = downloadPath
        options.add_experimental_option("prefs", prefs)
        options.add_argument("--mute-audio")
        driver = webdriver.Chrome(PATH, options = options)
        driver.get("https://makeitmeme.com/de/")
        time.sleep(5)
        button = driver.find_element(By.CSS_SELECTOR, ".css-1hfoajl:nth-of-type(2)")
        button.click()
        name = driver.find_element(By.CSS_SELECTOR, '[type="text"]')
        name.send_keys("Zack");
        time.sleep(5)
        button = driver.find_element(By.CSS_SELECTOR, ".MuiButton-contained")
        button.click()
        exit = 0
        while (exit == 0):
            time.sleep(15)
            button = None
            try:
                button = driver.find_element(By.CSS_SELECTOR, ".MuiButton-text")
            except:
                print("Not Found")
            if button is not None:
                button.click()
                time.sleep(1)
                list_of_files = glob.glob(str(downloadPath + "\*"))
                latest_file = max(list_of_files, key=os.path.getctime)
                print(latest_file)
                hasher = hashlib.md5()
                with open(latest_file, 'rb') as afile:
                    buf = afile.read()
                    hasher.update(buf)
                    hex = hasher.hexdigest()
                if hex == old_hex:
                    os.remove(latest_file)
                else:
                    await channel.send(file=discord.File(latest_file))
                old_hex = hex
        driver.quit()
from datetime import datetime
old_print = print
def timestamped_print(*args, **kwargs):
    old_print(datetime.now(), *args, **kwargs)
print = timestamped_print
client = MyClient()
client.run("token")

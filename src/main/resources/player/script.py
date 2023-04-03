import os
from PIL import Image
folder = "runLeft"

for i in range(10):
    im = Image.open(folder + "/frame0" + str(i) + ".png")
    print("pp")
    im = im.crop((40, 40, 70, 80))
    im.save("frame0" + str(i) + ".png")
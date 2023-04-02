import os
folder = "runLeft"

for i in range(5):
    swap1 = folder + "/frame0" + str(i) + ".png"
    swap2 = folder + "/frame0" + str(9-i) + ".png"
    temp = folder + "/temp.png"
    os.rename(swap1, temp);
    os.rename(swap2, swap1);
    os.rename(temp, swap2);
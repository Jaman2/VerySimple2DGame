from split_image import split_image
from PIL import Image

fileName = "tile.png"
tilSize = 32

im = Image.open(fileName)
imWidth, imHeight = im.size
rows = int(imWidth / tilSize)
cols = int(imHeight / tilSize)
split_image(fileName, cols, rows, False, False)

import os


filenames = os.listdir('./data')
print()
with open('merged.txt', 'w') as outfile:
    for fname in filenames:
        if '.txt' in fname:
            with open('./data/' +fname) as infile:
                outfile.write(infile.read())

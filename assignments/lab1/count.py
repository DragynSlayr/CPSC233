def getLines(name):
    try:
        openFile = open(name, "r")
        lines = openFile.readlines()
        openFile.close()
        
        stripped = []
        for line in lines:
            shortened = line.strip()
            if len(shortened) != 0:
                if not shortened.startswith("//"):
                    stripped.append(shortened)
        
        return stripped
    except:
        print("Invalid file name")

name = input("Enter file name: ")
lines = getLines(name)

print(len(lines))
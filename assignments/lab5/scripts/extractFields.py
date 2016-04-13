import sys

def read_data(file_name):
    opened = open(file_name, "r")
    lines = opened.readlines()
    opened.close()

    return lines

def parse_data(data):
    required = []
    modifiers = ["public", "private", "protected"]
    
    for line in data:
        splitted = line.strip().split()
        if len(splitted) > 0:
            if splitted[0] in modifiers:
                required.append(line.lstrip())

    return required

def reverseParams(params):
    if len(params) == 0:
        return ""
    
    splitted = params.split(",")
    
    reverse = ""
    
    for i in range(len(splitted)):
        split2 = splitted[i].split()
        reverse += split2[1] +  " : " + split2[0]
        if i != len(splitted) - 1:
            reverse += ", "
            
    return reverse

def format_data(data):
    formatted = []

    constructed = False

    front = {"protected" : "#", "public" : "+", "private" : "-"}

    for i in range(len(data)):
        splitted = data[i].split()
        if i == 0:
            formatted.append(splitted[2] + "\n")
        elif "{" in data[i] and not constructed:
            constructed = True

            a = front[splitted[0]]

            b = " ".join(splitted[1:-1])
            
            left = b.index("(")
            right = b.index(")")
            
            b = " ".join(splitted[1:-1])[:left] + "(" + reverseParams(b[left + 1:right]) + ")"
            
            formatted.append(a + " " + b + "\n")
        elif "{" in data[i]:
            a = front[splitted[0]]

            b = " ".join(splitted[2:-1])
            
            left = b.index("(")
            right = b.index(")")
            
            b = " ".join(splitted[2:-1])[:left] + "(" + reverseParams(b[left + 1:right]) + ")"

            c = splitted[1]
            
            formatted.append(a + " " + b + " : " + c + "\n")
        elif ";" in data[i] and "," in data[i]:
            a = front[splitted[0]]
            c = splitted[1]

            b = data[i].split(",")
            
            for i in range(len(b)):
                if i == 0:
                    d = b[i].split()[-1].strip()
                elif i == len(b) - 1:
                    d = b[i].strip()[:-1]
                else:
                    d = b[i].strip()

                formatted.append(a + " " + d + " : " + c + "\n")
        elif ";" in data[i]:
            a = front[splitted[0]]

            b = ("".join(splitted[2]))[:-1]

            c = splitted[1]
            
            formatted.append(a + " " + b + " : " + c + "\n")
        else:
            formatted.append(" ".join(splitted) + "\n")
            
    return formatted

def write_data(data, file_name):
    dest = open(file_name, "w")
    dest.writelines(data)
    dest.close()

if __name__ == "__main__":
    data = read_data(sys.argv[1])
    
    parsed = parse_data(data)
    
    formatted = format_data(parsed)

    out_file = sys.argv[1].split(".")
    write_data(formatted, "out/" + out_file[0] + ".txt")

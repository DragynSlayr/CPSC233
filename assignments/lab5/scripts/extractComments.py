import sys

def main():
    file_name = sys.argv[1]
    
    opened = open(file_name, "r")
    lines = opened.readlines()
    opened.close()

    edited = []
    for line in lines:
        stripped = line.lstrip()
        if stripped.startswith("/"):
            edited.append(stripped + "\n")
        elif stripped.startswith("*"):
            edited.append(stripped)

        if stripped.endswith("/\n"):
            edited.append("\n")

    comments = open("comments.txt", "w")
    comments.writelines(edited)
    comments.close()

if __name__ == "__main__":
    main()

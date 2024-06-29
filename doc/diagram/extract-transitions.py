import re

sourceFile = "./../../src/main/java/io/github/freiheitstools/semver/parser/implementation/SemVerFiniteStateTransitionTable.java"

try:
    with open(sourceFile, 'r') as file:
        for line in file:
            match = re.match(r".*transitionTable\.from\((S\d\d[_A-Z]+)\)\.to\((S\d\d[_A-Z]+|END_OF_SEMVER|ERROR[_A-Z]+)\)\.when\((.*?)\)", line)
            if match:
                print(match.group(1) + " --> " + match.group(2) + " : " + match.group(3))

except FileNotFoundError:
    print(f"{sourceFile} not found")
except Exception as e:
    print(f"Error: {e}")

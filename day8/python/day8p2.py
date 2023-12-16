from math import lcm

def parseItemDict(elem):
    k = elem.split(" ")[0]
    v = tuple(map(lambda item: item.strip("(").strip(",").strip(")"), elem.split(" ")[2::]))
    return k, v

with open("../input.txt", "r") as file:
    lines = map(lambda line: line.strip("\n"), file.readlines())
    lines = filter(lambda line: len(line) != 0, lines)
    lines = list(lines)

instructions = lines.pop(0)
ways = {k: v for k, v in map(lambda line: parseItemDict(line), lines)}

START = "A"
FINAL = "Z"
DIRECTION = {
    "L": 0,
    "R": 1
}

currentLocations = list(filter(lambda way: START in way, ways.keys()))
walk_counts = [0 for num in range(len(currentLocations))]

for idx in range(len(currentLocations)):
    while not "Z" in currentLocations[idx]:
        for instruction in instructions:
            currentLocations[idx] = ways[currentLocations[idx]][DIRECTION[instruction]]
            walk_counts[idx] += 1

print(lcm(*walk_counts))
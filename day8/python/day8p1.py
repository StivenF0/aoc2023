
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

DIRECTION = {
    "L": 0,
    "R": 1
}

currentLocation = "AAA"
walk_count = 0
while currentLocation != "ZZZ":
    for instruction in instructions:
        currentLocation = ways[currentLocation][DIRECTION[instruction]]
        walk_count += 1

print(walk_count)
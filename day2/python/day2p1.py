MAX = {
    "red": 12,
    "green": 13,
    "blue": 14
}

def get_cubes(cube_str):
    return int(cube_str.lstrip(" ").split(" ")[0])

lines = []
with open("../input.txt", "r") as file:
    lines = [line.strip("\n") for line in file.readlines()]

games = []
for line in lines:
    id = int(line.split(":")[0].split(" ")[1])
    game_rounds = line.split(":")[1].split(";")
    red_max = 0
    green_max = 0
    blue_max = 0
    for game_round in game_rounds:
        cubes = game_round.split(",")
        for cube_round in cubes:
            if "red" in cube_round:
                red_max = get_cubes(cube_round) if get_cubes(cube_round) > red_max else red_max
            if "green" in cube_round:
                green_max = get_cubes(cube_round) if get_cubes(cube_round) > green_max else green_max
            if "blue" in cube_round:
                blue_max = get_cubes(cube_round) if get_cubes(cube_round) > blue_max else blue_max

    games.append({
        "id": id,
        "red": red_max,
        "green": green_max,
        "blue": blue_max
    })

games_possible = 0
for game in games:
    if game["red"] > MAX["red"] or game["green"] > MAX["green"] or game["blue"] > MAX["blue"]:
        continue
    games_possible += game["id"]

print(games_possible)
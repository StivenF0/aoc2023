# Get cubes in a game string
def get_cubes(cube_str):
    return int(cube_str.lstrip(" ").split(" ")[0])

# Reading the input file
lines = []
with open("../input.txt", "r") as file:
    lines = [line.strip("\n") for line in file.readlines()]

# Getting all the rounds maximum values in each game.
games = []
for line in lines:
    game_rounds = line.split(":")[1].split(";")
    red_max = 0
    green_max = 0
    blue_max = 0

    # Getting max values of cubes on each set of an round.
    for game_round in game_rounds:
        cubes = game_round.split(",")
        for cube_round in cubes:
            if "red" in cube_round:
                red_max = get_cubes(cube_round) if get_cubes(cube_round) > red_max else red_max
            if "green" in cube_round:
                green_max = get_cubes(cube_round) if get_cubes(cube_round) > green_max else green_max
            if "blue" in cube_round:
                blue_max = get_cubes(cube_round) if get_cubes(cube_round) > blue_max else blue_max
    
    # Appending to the games list.
    games.append({
        "red": red_max,
        "green": green_max,
        "blue": blue_max
    })

# Doing the operation of power for each game
power_sum = 0
for game in games:
    power_sum += game["red"] * game["green"] * game["blue"]

print(power_sum)